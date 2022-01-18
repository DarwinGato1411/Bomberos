/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.ParteDiarioTipoSolicitud;
import com.ec.entidad.SolicitudPermiso;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioSolicitudPermiso {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(SolicitudPermiso solicitudPermiso) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(solicitudPermiso);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar solicitudPermiso " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }

    }

    public void eliminar(SolicitudPermiso solicitudPermiso) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(solicitudPermiso));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  solicitudPermiso " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(SolicitudPermiso solicitudPermiso) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(solicitudPermiso);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en modificar solicitudPermiso " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<SolicitudPermiso> findLikePermiso(String valor) {

        List<SolicitudPermiso> listaClientes = new ArrayList<SolicitudPermiso>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM SolicitudPermiso u WHERE u.solNumCedula LIKE :solNumCedula OR u.solpNombreSol LIKE :solpNombreSol OR u.solpApellidoSol LIKE :solpApellidoSol");
            query.setParameter("solNumCedula", "%" + valor + "%");
            query.setParameter("solpNombreSol", "%" + valor + "%");
            query.setParameter("solpApellidoSol", "%" + valor + "%");
            listaClientes = (List<SolicitudPermiso>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta solicitudPermiso  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }

    /*PERMISOS INGRESADOS*/
    public List<SolicitudPermiso> findLikePermisoForEstadoCedulaNombre(String valor, String buscar) {

        List<SolicitudPermiso> listaClientes = new ArrayList<SolicitudPermiso>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM SolicitudPermiso u WHERE u.idEstadoDocumento.estSigla =:estSigla AND (u.solNumCedula LIKE :solNumCedula OR u.solpNombreSol LIKE :solpNombreSol ) ORDER BY u.solpNumeracion DESC");
//            query.setParameter("solNumCedula", "%" + valor + "%");
//            query.setParameter("solpNombreSol", "%" + valor + "%");
//            query.setParameter("solpApellidoSol", "%" + valor + "%");
            query.setParameter("estSigla", valor);
            query.setParameter("solNumCedula", "%" + buscar + "%");
            query.setParameter("solpNombreSol", "%" + buscar + "%");
            listaClientes = (List<SolicitudPermiso>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta solicitudPermiso  findLikePermisoIng  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }

    public SolicitudPermiso findByPerSigla(String perSigla) {

        List<SolicitudPermiso> listaClientes = new ArrayList<SolicitudPermiso>();
        SolicitudPermiso solicitudPermisoObtenido = new SolicitudPermiso();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM SolicitudPermiso u WHERE u.opcSigla =:perSigla");
            query.setParameter("perSigla", perSigla);
            listaClientes = (List<SolicitudPermiso>) query.getResultList();
            if (listaClientes.size() > 0) {
                for (SolicitudPermiso solicitudPermiso : listaClientes) {
                    solicitudPermisoObtenido = solicitudPermiso;
                }
            } else {
                solicitudPermisoObtenido = null;
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta solicitudPermiso  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return solicitudPermisoObtenido;
    }

    public SolicitudPermiso findUltimoPermiso() {

        SolicitudPermiso retorno = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM SolicitudPermiso u ORDER BY u.solpNumeracion desc");
            query.setMaxResults(1);

            List<SolicitudPermiso> lista = (List<SolicitudPermiso>) query.getResultList();
            if (lista.isEmpty()) {
                return null;
            } else {
                retorno = lista.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta solicitudPermiso  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return retorno;
    }

    public SolicitudPermiso findLikeRuc(String valor) {

        List<SolicitudPermiso> listaClientes = new ArrayList<SolicitudPermiso>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM SolicitudPermiso u WHERE u.solNumCedula =:solNumCedula ORDER BY u.idSolcitudPer DESC");
            query.setParameter("solNumCedula", valor);

            listaClientes = (List<SolicitudPermiso>) query.getResultList();
            em.getTransaction().commit();
            if (!listaClientes.isEmpty()) {
                return listaClientes.get(0);
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Error en lsa consulta solicitudPermiso  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }
        return null;
    }

    public List<SolicitudPermiso> findLikeBuscarSolicitudes(String valor, Date fechaInicio, Date fechaFin) {

        List<SolicitudPermiso> listaClientes = new ArrayList<SolicitudPermiso>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM SolicitudPermiso u WHERE (u.solpFecha BETWEEN :inicio and :fin) and ( u.solpNumero LIKE :solpNumero OR u.solNumCedula LIKE :solNumCedula OR u.solpNombreSol LIKE :solpNombreSol) ORDER BY u.solpFecha DESC");
            query.setParameter("solNumCedula", "%" + valor + "%");
            query.setParameter("solpNombreSol", "%" + valor + "%");
            query.setParameter("solpNumero", "%" + valor + "%");
            query.setParameter("inicio", fechaInicio);
            query.setParameter("fin", fechaFin);
            listaClientes = (List<SolicitudPermiso>) query.getResultList();
            System.out.println("numero  de elementos  " + listaClientes.size());
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta solicitudPermiso  findLikeBuscarSolicitudes  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }

    public List<SolicitudPermiso> findSolFecha(Date inicio, Date fin, String estado) {

        List<SolicitudPermiso> lstSolicitudPermiso = new ArrayList<SolicitudPermiso>();
        try {
            Query query;

//            String SQL = "SELECT f FROM Factura f WHERE f.facFecha BETWEEN :inicio and :fin ORDER BY f.facFecha DESC";
            //Connection connection = em.unwrap(Connection.class);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            if (!estado.equals("TODO")) {
                query = em.createQuery("SELECT f FROM Factura f WHERE f.facFecha BETWEEN :inicio and :fin AND f.facEstado=:facEstado AND f.facTipo='FACT' ORDER BY f.facFecha DESC");
                query.setParameter("inicio", inicio);
                query.setParameter("fin", fin);
                query.setParameter("facEstado", estado);
            } else {
                query = em.createQuery("SELECT f FROM Factura f WHERE f.facFecha BETWEEN :inicio and :fin  AND f.facTipo='FACT' ORDER BY f.facFecha DESC");
                query.setParameter("inicio", inicio);
                query.setParameter("fin", fin);
            }

//            query.setMaxResults(400);
            lstSolicitudPermiso = (List<SolicitudPermiso>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta factura " + e.getMessage());
        } finally {
            em.close();
        }

        return lstSolicitudPermiso;
    }

    public List<SolicitudPermiso> FindLikeNumeroSolicitud(String valor, String sigla) {

        List<SolicitudPermiso> lstSolicitudPermiso = new ArrayList<SolicitudPermiso>();
        try {
            //Connection connection = em.unwrap(Connection.class);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM SolicitudPermiso u WHERE  (u.solpNumero LIKE :solpNumero OR u.solNumCedula LIKE :solNumCedula OR u.solpNombreSol LIKE :solpNombreSol ) AND u.idEstadoDocumento.estSigla =:estSigla   ORDER BY u.solpNumero DESC");
//            query.setMaxResults(400);
            query.setParameter("solpNumero", "%" + valor + "%");
            query.setParameter("estSigla", sigla);
            query.setParameter("solNumCedula", "%" + valor + "%");
            query.setParameter("solpNombreSol", "%" + valor + "%");
            lstSolicitudPermiso = (List<SolicitudPermiso>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta num solicitud " + e.getMessage());
        } finally {
            em.close();
        }

        return lstSolicitudPermiso;
    }

    public List<SolicitudPermiso> findByFecha(Date fecha) {

        List<SolicitudPermiso> listaClientes = new ArrayList<SolicitudPermiso>();
        try {
            String pattern = "yyyy MMMMM dd  HH:mm:ss";
            SimpleDateFormat simpleDateFormat
                    = new SimpleDateFormat(pattern);
            //Connection connection = em.unwrap(Connection.class);
            Date inicio = fecha;
            inicio.setHours(0);
            inicio.setMinutes(0);
            String inicioText = simpleDateFormat.format(inicio);

            Date fin = fecha;
            fin.setHours(11);
            fin.setMinutes(59);
            String finText = simpleDateFormat.format(fin);

            Date paramInicio = simpleDateFormat.parse(inicioText);
            Date paramFin = simpleDateFormat.parse(finText);

            System.out.println("inicio " + paramInicio);
            System.out.println("fin " + paramFin);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM SolicitudPermiso u WHERE  u.solpFecha BETWEEN :inicio and :fin");
            query.setParameter("inicio", paramInicio);
            query.setParameter("fin", paramFin);
            listaClientes = (List<SolicitudPermiso>) query.getResultList();
            System.out.println("CANTIDAD SOLICIU¿TUDES " + listaClientes.size());
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta parteDiario  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }
}
