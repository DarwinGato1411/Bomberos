/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Perfil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioPerfil {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Perfil perfil) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(perfil);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar perfil " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Perfil perfil) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(perfil));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  perfil " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Perfil perfil) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(perfil);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en modificar perfil " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<Perfil> findLikePerNombre(String perNombre) {

        List<Perfil> listaClientes = new ArrayList<Perfil>();        
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Perfil u WHERE u.perNombre LIKE :perNombre");
            query.setParameter("perNombre", "%" + perNombre + "%");
            listaClientes = (List<Perfil>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta perfil  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }

    public Perfil findByPerSigla(String perSigla) {

        List<Perfil> listaClientes = new ArrayList<Perfil>();
        Perfil perfilObtenido = new Perfil();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Perfil u WHERE u.perSigla =:perSigla");
            query.setParameter("perSigla", perSigla);
            listaClientes = (List<Perfil>) query.getResultList();
            if (listaClientes.size() > 0) {
                for (Perfil perfil : listaClientes) {
                    perfilObtenido = perfil;
                }
            } else {
                perfilObtenido = null;
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta perfil  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return perfilObtenido;
    }

}
