/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.DocumentosAdjunto;
import com.ec.entidad.SolicitudPermiso;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioArchivoAdjunto {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(DocumentosAdjunto documentosAdjunto) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(documentosAdjunto);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar documentosAdjunto " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(DocumentosAdjunto documentosAdjunto) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(documentosAdjunto));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  documentosAdjunto " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(DocumentosAdjunto documentosAdjunto) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(documentosAdjunto);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en modificar documentosAdjunto " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<DocumentosAdjunto> findBySolicitud(SolicitudPermiso valor, Boolean adjEstadoArchivo) {

        List<DocumentosAdjunto> listaDatos = new ArrayList<DocumentosAdjunto>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM DocumentosAdjunto u WHERE u.idSolcitudPer =:idSolcitudPer AND u.adjEstadoArchivo =:adjEstadoArchivo");
            query.setParameter("idSolcitudPer", valor);
            query.setParameter("adjEstadoArchivo", adjEstadoArchivo);
            listaDatos = (List<DocumentosAdjunto>) query.getResultList();
           
            em.getTransaction().commit();
             return listaDatos;
        } catch (Exception e) {
            System.out.println("Error en lsa consulta documentosAdjunto  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return null;
    }

}
