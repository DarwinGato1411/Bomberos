/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Bombero;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioBombero {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Bombero bombero) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(bombero);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar bombero " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Bombero bombero) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(bombero));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  bombero " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Bombero bombero) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(bombero);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en modificar bombero " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<Bombero> findLikeNombreBombero(String valor) {

        List<Bombero> listaClientes = new ArrayList<Bombero>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Bombero u WHERE u.bomNombre LIKE :bomNombre ORDER BY u.bomNombre ASC");
            query.setParameter("bomNombre", "%" + valor + "%");
            listaClientes = (List<Bombero>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta parroquia  findLikeNombreBombero  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }
}
