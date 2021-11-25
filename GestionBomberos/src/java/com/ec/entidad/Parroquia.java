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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "parroquia")
public class Parroquia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_parroquia")
    private Integer idParroquia;
    @Column(name = "parr_descripcion")
    private String parrDescripcion;
    @Column(name = "parr_sigla")
    private String parrSigla;
    @Column(name = "parr_peso")
    private Integer parrPeso;
    @OneToMany(mappedBy = "idParroquia")
    private Collection<SolicitudPermiso> solicitudPermisoCollection;

    public Parroquia() {
    }

    public Parroquia(String parrDescripcion, String parrSigla, Integer parrPeso) {
        this.parrDescripcion = parrDescripcion;
        this.parrSigla = parrSigla;
        this.parrPeso = parrPeso;
    }

    public Parroquia(Integer idParroquia) {
        this.idParroquia = idParroquia;
    }

    public Integer getIdParroquia() {
        return idParroquia;
    }

    public void setIdParroquia(Integer idParroquia) {
        this.idParroquia = idParroquia;
    }

    public String getParrDescripcion() {
        return parrDescripcion;
    }

    public void setParrDescripcion(String parrDescripcion) {
        this.parrDescripcion = parrDescripcion;
    }

    public String getParrSigla() {
        return parrSigla;
    }

    public void setParrSigla(String parrSigla) {
        this.parrSigla = parrSigla;
    }

    public Integer getParrPeso() {
        return parrPeso;
    }

    public void setParrPeso(Integer parrPeso) {
        this.parrPeso = parrPeso;
    }

    
    @XmlTransient
    public Collection<SolicitudPermiso> getSolicitudPermisoCollection() {
        return solicitudPermisoCollection;
    }

    public void setSolicitudPermisoCollection(Collection<SolicitudPermiso> solicitudPermisoCollection) {
        this.solicitudPermisoCollection = solicitudPermisoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParroquia != null ? idParroquia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parroquia)) {
            return false;
        }
        Parroquia other = (Parroquia) object;
        if ((this.idParroquia == null && other.idParroquia != null) || (this.idParroquia != null && !this.idParroquia.equals(other.idParroquia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidades.Parroquia[ idParroquia=" + idParroquia + " ]";
    }

}
