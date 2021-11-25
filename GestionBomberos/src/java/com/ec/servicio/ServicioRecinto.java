/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Recinto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioRecinto {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Recinto recinto) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(recinto);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar recinto " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Recinto recinto) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(recinto));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  recinto " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Recinto recinto) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(recinto);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en modificar recinto " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<Recinto> findBySigla(String sigla) {

        List<Recinto> listaClientes = new ArrayList<Recinto>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Recinto u WHERE u.tipsSigla =:tipsSigla");
            query.setParameter("tipsSigla", sigla);
            listaClientes = (List<Recinto>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta recinto  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }

    public List<Recinto> findByAll() {

        List<Recinto> listaClientes = new ArrayList<Recinto>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Recinto u ORDER BY u.recDescripcion ASC");
//            query.setParameter("tipsSigla", sigla);
            listaClientes = (List<Recinto>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta recinto  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }

    public List<Recinto> findLikeDescripcion(String descripcion) {

        List<Recinto> listaClientes = new ArrayList<Recinto>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Recinto u WHERE u.recDescripcion LIKE :recDescripcion ORDER BY u.recDescripcion ASC");
            query.setParameter("recDescripcion", "%" + descripcion + "%");
            listaClientes = (List<Recinto>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta recinto  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }

}
