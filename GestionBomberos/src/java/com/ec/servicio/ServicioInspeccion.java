/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Inspeccion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioInspeccion {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Inspeccion inspeccion) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(inspeccion);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar inspeccion " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Inspeccion inspeccion) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(inspeccion));
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en eliminar  inspeccion " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Inspeccion inspeccion) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(inspeccion);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en modificar inspeccion " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<Inspeccion> findLikeDescripcion(String valor) {

        List<Inspeccion> listaClientes = new ArrayList<Inspeccion>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Inspeccion u WHERE u.insDescripcion LIKE :insDescripcion ORDER BY u.idInspeccion ASC");
            query.setParameter("insDescripcion", "%" + valor + "%");

            listaClientes = (List<Inspeccion>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta inspeccion  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }

    /*PERMISOS INGRESADOS*/
    public List<Inspeccion> findLikePermisoForEstadoCedulaNombre(String valor, String buscar) {

        List<Inspeccion> listaClientes = new ArrayList<Inspeccion>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Inspeccion u WHERE u.idEstadoDocumento.estSigla =:estSigla AND (u.solNumCedula LIKE :solNumCedula OR u.solpNombreSol LIKE :solpNombreSol )");
//            query.setParameter("solNumCedula", "%" + valor + "%");
//            query.setParameter("solpNombreSol", "%" + valor + "%");
//            query.setParameter("solpApellidoSol", "%" + valor + "%");
            query.setParameter("estSigla", valor);
            query.setParameter("solNumCedula", "%" + buscar + "%");
            query.setParameter("solpNombreSol", "%" + buscar + "%");
            listaClientes = (List<Inspeccion>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta inspeccion  findLikePermisoIng  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }

    public Inspeccion findByPerSigla(String perSigla) {

        List<Inspeccion> listaClientes = new ArrayList<Inspeccion>();
        Inspeccion inspeccionObtenido = new Inspeccion();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Inspeccion u WHERE u.opcSigla =:perSigla");
            query.setParameter("perSigla", perSigla);
            listaClientes = (List<Inspeccion>) query.getResultList();
            if (listaClientes.size() > 0) {
                for (Inspeccion inspeccion : listaClientes) {
                    inspeccionObtenido = inspeccion;
                }
            } else {
                inspeccionObtenido = null;
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta inspeccion  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return inspeccionObtenido;
    }

    public Inspeccion findUltimoPermiso() {

        Inspeccion retorno = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Inspeccion u ORDER BY u.solpNumeracion desc");
            query.setMaxResults(1);

            List<Inspeccion> lista = (List<Inspeccion>) query.getResultList();
            if (lista.isEmpty()) {
                return null;
            } else {
                retorno = lista.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta inspeccion  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return retorno;
    }

    public List<Inspeccion> findLikeDescripcionRev(String valor, String buscar) {

        List<Inspeccion> listaClientes = new ArrayList<Inspeccion>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Inspeccion u WHERE u.insObservacion LIKE :insObservacion AND u.idSolcitudPer.idEstadoDocumento.estSigla =:estSigla ORDER BY u.idInspeccion ASC");
            query.setParameter("insObservacion", "%" + buscar + "%");
            query.setParameter("estSigla", valor);
            listaClientes = (List<Inspeccion>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta inspeccion  findLikePerNombre  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaClientes;
    }

}
