/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Opciones;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioOpcion {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Opciones opciones) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(opciones);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar opciones " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Opciones opciones) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(opciones));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  opciones " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Opciones opciones) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(opciones);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en modificar opciones " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<Opciones> findLikeOpcDecripcion(String valor) {

        List<Opciones> listaClientes = new ArrayList<Opciones>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Opciones u WHERE u.opcDescripcion LIKE :opcDescripcion");
            query.setParameter("opcDescripcion", "%" + valor + "%");
            listaClientes = (List<Opciones>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta opciones  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }

    public Opciones findByPerSigla(String perSigla) {

        List<Opciones> listaClientes = new ArrayList<Opciones>();
        Opciones opcionesObtenido = new Opciones();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Opciones u WHERE u.opcSigla =:perSigla");
            query.setParameter("perSigla", perSigla);
            listaClientes = (List<Opciones>) query.getResultList();
            if (listaClientes.size() > 0) {
                for (Opciones opciones : listaClientes) {
                    opcionesObtenido = opciones;
                }
            } else {
                opcionesObtenido = null;
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta opciones  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return opcionesObtenido;
    }

}
