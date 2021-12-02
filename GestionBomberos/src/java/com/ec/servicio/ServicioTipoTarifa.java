/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.TipoTarifa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioTipoTarifa {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(TipoTarifa tipoTarifa) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(tipoTarifa);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar tipoTarifa " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(TipoTarifa tipoTarifa) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(tipoTarifa));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  tipoTarifa " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(TipoTarifa tipoTarifa) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(tipoTarifa);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en modificar tipoTarifa " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<TipoTarifa> findLikeTariDecripcion(String valor) {

        List<TipoTarifa> listaClientes = new ArrayList<TipoTarifa>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM TipoTarifa u WHERE u.tiptDescripcion LIKE :tiptDescripcion ORDER BY u.tiptDescripcion ASC");
            query.setParameter("tiptDescripcion", "%" + valor + "%");
            listaClientes = (List<TipoTarifa>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta parroquia  findLikeTariDecripcion  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }
}
