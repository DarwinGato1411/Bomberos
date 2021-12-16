/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.ParteDiarioTipoSolicitud;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioParteDiarioTipoSolicitud {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(ParteDiarioTipoSolicitud parteDiario) {

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

    public void eliminar(ParteDiarioTipoSolicitud parteDiario) {

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

    public void modificar(ParteDiarioTipoSolicitud parteDiario) {

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

    public List<ParteDiarioTipoSolicitud> findByFecha(Date fecha) {

        List<ParteDiarioTipoSolicitud> listaClientes = new ArrayList<ParteDiarioTipoSolicitud>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM ParteDiarioTipoSolicitud u WHERE u.cobFecha =:cobFecha ORDER BY u.tipsDescripcion ASC");
            query.setParameter("cobFecha", fecha);
            listaClientes = (List<ParteDiarioTipoSolicitud>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta parteDiario  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }

}
