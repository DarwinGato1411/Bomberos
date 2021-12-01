/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Tarifa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioTarifa {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Tarifa tarifa) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(tarifa);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar tarifa " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Tarifa tarifa) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(tarifa));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  tarifa " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Tarifa tarifa) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(tarifa);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en modificar tarifa " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<Tarifa> findLikeTariDecripcion(String valor) {

        List<Tarifa> listaClientes = new ArrayList<Tarifa>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Tarifa u WHERE u.tarDescripcion LIKE :tarDescripcion ORDER BY u.tarDescripcion ASC");
            query.setParameter("tarDescripcion", "%" + valor + "%");
            listaClientes = (List<Tarifa>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta parroquia  findLikeTariDecripcion  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }
}
