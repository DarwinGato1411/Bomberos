/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Parroquia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioParroquia {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Parroquia parroquia) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(parroquia);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar parroquia " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Parroquia parroquia) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(parroquia));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  parroquia " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Parroquia parroquia) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(parroquia);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en modificar parroquia " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<Parroquia> findLikeParrDecripcion(String valor) {

        List<Parroquia> listaClientes = new ArrayList<Parroquia>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Parroquia u WHERE u.parrDescripcion LIKE :parrDescripcion ORDER BY u.parrDescripcion ASC");
            query.setParameter("parrDescripcion", "%" + valor + "%");
            listaClientes = (List<Parroquia>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta parroquia  findLikeParrDecripcion  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }

}
