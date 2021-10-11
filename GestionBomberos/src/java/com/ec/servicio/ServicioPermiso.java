/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

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

    public void crear(SolicitudPermiso solicitudPermiso) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(solicitudPermiso);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar solicitudPermiso " + e.getMessage());
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

}
