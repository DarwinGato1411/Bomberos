/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "recinto")
public class Recinto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_recinto")
    private Integer idRecinto;
    @Column(name = "rec_descripcion")
    private String recDescripcion;
    @Column(name = "rec_sigla")
    private String recSigla;
    @Column(name = "rec_peso")
    private Integer recPeso;
    @OneToMany(mappedBy = "idRecinto")
    private Collection<SolicitudPermiso> solicitudPermisoCollection;

    public Recinto() {
    }

    public Integer getIdRecinto() {
        return idRecinto;
    }

    public void setIdRecinto(Integer idRecinto) {
        this.idRecinto = idRecinto;
    }

    public String getRecDescripcion() {
        return recDescripcion;
    }

    public void setRecDescripcion(String recDescripcion) {
        this.recDescripcion = recDescripcion;
    }

    public String getRecSigla() {
        return recSigla;
    }

    public void setRecSigla(String recSigla) {
        this.recSigla = recSigla;
    }

    public Collection<SolicitudPermiso> getSolicitudPermisoCollection() {
        return solicitudPermisoCollection;
    }

    public void setSolicitudPermisoCollection(Collection<SolicitudPermiso> solicitudPermisoCollection) {
        this.solicitudPermisoCollection = solicitudPermisoCollection;
    }

    

}
