/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Permiso;
import com.ec.entidad.SolicitudPermiso;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioPermiso {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Permiso permiso) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(permiso);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar permiso " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }

    }

    public void eliminar(Permiso permiso) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(permiso));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  permiso " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Permiso permiso) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(permiso);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en modificar permiso " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<Permiso> findLikePermiso(String valor) {

        List<Permiso> listaClientes = new ArrayList<Permiso>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Permiso u WHERE u.idInspeccion.idSolcitudPer.solNumCedula LIKE :solNumCedula OR u.idInspeccion.idSolcitudPer.solpNombreSol LIKE :solpNombreSol ");
            query.setParameter("solNumCedula", "%" + valor + "%");
            query.setParameter("solpNombreSol", "%" + valor + "%");
//            query.setParameter("solpApellidoSol", "%" + valor + "%");
            listaClientes = (List<Permiso>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta permiso  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }

    /*PERMISOS INGRESADOS*/
    public List<Permiso> findLikePermisoForEstadoCedulaNombre(String valor, String buscar) {

        List<Permiso> listaClientes = new ArrayList<Permiso>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Permiso u WHERE  (u.idInspeccion.idSolcitudPer.solNumCedula LIKE :solNumCedula OR u.idInspeccion.idSolcitudPer.solpNombreSol LIKE :solpNombreSol ) ORDER BY u.idInspeccion.idSolcitudPer.solpNumero DESC");
//            query.setParameter("solNumCedula", "%" + valor + "%");
//            query.setParameter("solpNombreSol", "%" + valor + "%");
//            query.setParameter("solpApellidoSol", "%" + valor + "%");
//            query.setParameter("estSigla", valor);
            query.setParameter("solNumCedula", "%" + buscar + "%");
            query.setParameter("solpNombreSol", "%" + buscar + "%");
            listaClientes = (List<Permiso>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta permiso  findLikePermisoIng  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }

    public Permiso findByPerSigla(String perSigla) {

        List<Permiso> listaClientes = new ArrayList<Permiso>();
        Permiso permisoObtenido = new Permiso();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Permiso u WHERE u.opcSigla =:perSigla");
            query.setParameter("perSigla", perSigla);
            listaClientes = (List<Permiso>) query.getResultList();
            if (listaClientes.size() > 0) {
                for (Permiso permiso : listaClientes) {
                    permisoObtenido = permiso;
                }
            } else {
                permisoObtenido = null;
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta permiso  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return permisoObtenido;
    }

    public Permiso findUltimoPermiso() {

        Permiso retorno = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Permiso u WHERE u.perNumero IS NOT NULL ORDER BY u.perNumero desc");
            query.setMaxResults(1);

            List<Permiso> lista = (List<Permiso>) query.getResultList();
            if (lista.isEmpty()) {
                return null;
            } else {
                retorno = lista.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta permiso  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return retorno;
    }

    public Permiso findLikeRuc(String valor) {

        List<Permiso> listaClientes = new ArrayList<Permiso>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Permiso u WHERE u.solNumCedula =:solNumCedula ORDER BY u.idSolcitudPer DESC");
            query.setParameter("solNumCedula", valor);

            listaClientes = (List<Permiso>) query.getResultList();
            em.getTransaction().commit();
            if (!listaClientes.isEmpty()) {
                return listaClientes.get(0);
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Error en lsa consulta permiso  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }
        return null;
    }

    public List<Permiso> FindLikeNumeroSolicitud(String valor, String sigla) {

        List<Permiso> listaPermisos = new ArrayList<Permiso>();
        try {
            //Connection connection = em.unwrap(Connection.class);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Permiso u WHERE  (u.idInspeccion.idSolcitudPer.solNumCedula LIKE :solNumCedula OR u.idInspeccion.idSolcitudPer.solpNombreSol LIKE :solpNombreSol ) ORDER BY u.idInspeccion.idSolcitudPer.solpNumero DESC");
//            query.setMaxResults(400);
            query.setParameter("solpNumero", "%" + valor + "%");
            query.setParameter("solNumCedula", "%" + valor + "%");
            query.setParameter("solpNombreSol", "%" + valor + "%");
            listaPermisos = (List<Permiso>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta num solicitud " + e.getMessage());
        } finally {
            em.close();
        }

        return listaPermisos;
    }

    public List<Permiso> FindLikeNumeroSolEntrega(String valor, String sigla) {

        List<Permiso> lstBuscarSolEntrega = new ArrayList<Permiso>();
        try {
            //Connection connection = em.unwrap(Connection.class);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Permiso u WHERE (u.idInspeccion.idSolcitudPer.solpNumero LIKE :solpNumero OR u.idInspeccion.idSolcitudPer.solNumCedula LIKE :solNumCedula OR u.idInspeccion.idSolcitudPer.solpNombreSol LIKE :solpNombreSol ) AND u.idInspeccion.idSolcitudPer.idEstadoDocumento.estSigla =:estSigla   ORDER BY u.idInspeccion.idSolcitudPer.solpNumero DESC");
//            query.setMaxResults(400);
            query.setParameter("solpNumero", "%" + valor + "%");
            query.setParameter("estSigla", sigla);
            query.setParameter("solNumCedula", "%" + valor + "%");
            query.setParameter("solpNombreSol", "%" + valor + "%");
            lstBuscarSolEntrega = (List<Permiso>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta  FindLikeNumeroInspeccion " + e.getMessage());
        } finally {
            em.close();
        }

        return lstBuscarSolEntrega;
    }

}
