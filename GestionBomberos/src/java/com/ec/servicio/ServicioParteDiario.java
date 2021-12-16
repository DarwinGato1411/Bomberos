/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.ParteDiario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioParteDiario {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(ParteDiario parteDiario) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(parteDiario);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar parteDiario " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(ParteDiario parteDiario) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(parteDiario));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  parteDiario " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(ParteDiario parteDiario) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(parteDiario);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en modificar parteDiario " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<ParteDiario> findByFecha(Date fecha) {

        List<ParteDiario> listaClientes = new ArrayList<ParteDiario>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM ParteDiario u WHERE u.cobFecha =:cobFecha ORDER BY u.cobDetalle ASC");
            query.setParameter("cobFecha", fecha);
            listaClientes = (List<ParteDiario>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta parteDiario  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }

}
