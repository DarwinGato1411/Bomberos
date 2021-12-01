/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.TipoSolicitud;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioTipoSolicitud {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(TipoSolicitud tipoSolicitud) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(tipoSolicitud);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar tipoSolicitud " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(TipoSolicitud tipoSolicitud) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(tipoSolicitud));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  tipoSolicitud " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(TipoSolicitud tipoSolicitud) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(tipoSolicitud);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en modificar tipoSolicitud " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<TipoSolicitud> findBySigla(String sigla) {

        List<TipoSolicitud> listaClientes = new ArrayList<TipoSolicitud>();        
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM TipoSolicitud u WHERE u.tipsSigla =:tipsSigla");
            query.setParameter("tipsSigla", sigla);
            listaClientes = (List<TipoSolicitud>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta tipoSolicitud  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }
    
    public List<TipoSolicitud> findByAll() {

        List<TipoSolicitud> listaClientes = new ArrayList<TipoSolicitud>();        
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM TipoSolicitud u ORDER BY u.tipsDescripcion ASC");
//            query.setParameter("tipsSigla", sigla);
            listaClientes = (List<TipoSolicitud>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta tipoSolicitud  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }

   public List<TipoSolicitud> findLikeSigla(String sigla) {

        List<TipoSolicitud> listaClientes = new ArrayList<TipoSolicitud>();        
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM TipoSolicitud u WHERE u.tipsSigla  LIKE :tipsSigla  ORDER BY u.tipsDescripcion ASC");
            query.setParameter("tipsSigla", "%"+sigla+"%");
            listaClientes = (List<TipoSolicitud>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta tipoSolicitud  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }

}
