/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.SolicitudPermiso;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            Query query = em.createQuery("SELECT u FROM SolicitudPermiso u WHERE u.idEstadoDocumento.estSigla =:estSigla AND (u.solNumCedula LIKE :solNumCedula OR u.solpNombreSol LIKE :solpNombreSol )");
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
    public SolicitudPermiso FindLikeBuscarSolicitud(String valor) {

        List<SolicitudPermiso> lstSolicitudPermiso = new ArrayList<SolicitudPermiso>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM SolicitudPermiso u WHERE u.solNumCedula LIKE :solNumCedula");
            query.setParameter("solNumCedula", valor );
//            query.setParameter("solNumCedula", "%" + valor + "%" );

            lstSolicitudPermiso = (List<SolicitudPermiso>) query.getResultList();
            em.getTransaction().commit();
            if (!lstSolicitudPermiso.isEmpty()) {
                return lstSolicitudPermiso.get(0);
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Error en lsa consulta solicitudPermiso  FindLikeBuscarSolicitud  " + e.getMessage());
        } finally {
            em.close();
        }
        return null;
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

    public List<SolicitudPermiso> findSolFecha(Date fechainicio, Date fechafin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
