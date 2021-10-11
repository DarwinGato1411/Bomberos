/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.EstadoDocumento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioEstadoDocumento {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(EstadoDocumento estadoDocumento) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(estadoDocumento);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar estadoDocumento " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(EstadoDocumento estadoDocumento) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(estadoDocumento));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  estadoDocumento " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(EstadoDocumento estadoDocumento) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(estadoDocumento);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en modificar estadoDocumento " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public EstadoDocumento findBySigla(String valor) {

        List<EstadoDocumento> listaDatos = new ArrayList<EstadoDocumento>();
        EstadoDocumento documento = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM EstadoDocumento u WHERE u.estSigla =:estSigla");
            query.setParameter("estSigla", valor);
            listaDatos = (List<EstadoDocumento>) query.getResultList();
            if (listaDatos.size() > 0) {
                documento = listaDatos.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta estadoDocumento  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return documento;
    }

}
