/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Cobro;
import com.ec.entidad.SolicitudPermiso;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioCobro {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Cobro cobro) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(cobro);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar cobro " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Cobro cobro) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(cobro));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  cobro " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Cobro cobro) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(cobro);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en modificar cobro " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<Cobro> findByPermiso(SolicitudPermiso idPermiso) {

        List<Cobro> listaDatos = new ArrayList<Cobro>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Cobro u WHERE u.idPermiso =:idPermiso");
            query.setParameter("idPermiso", idPermiso);
          
            listaDatos = (List<Cobro>) query.getResultList();
           
            em.getTransaction().commit();
             return listaDatos;
        } catch (Exception e) {
            System.out.println("Error en lsa consulta cobro  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return null;
    }

}
