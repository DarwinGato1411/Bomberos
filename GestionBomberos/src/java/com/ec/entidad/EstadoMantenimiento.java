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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "estado_mantenimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoMantenimiento.findAll", query = "SELECT e FROM EstadoMantenimiento e")})
public class EstadoMantenimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_man")
    private Integer idEstadoMan;
    @Column(name = "estm_descripcion")
    private String estmDescripcion;
    @Column(name = "estm_sigla")
    private String estmSigla;
    @Column(name = "estm_estado")
    private Boolean estmEstado;
    @OneToMany(mappedBy = "idEstadoMan")
    private Collection<Mantenimiento> mantenimientoCollection;

    public EstadoMantenimiento() {
    }

    public EstadoMantenimiento(Integer idEstadoMan) {
        this.idEstadoMan = idEstadoMan;
    }

    public Integer getIdEstadoMan() {
        return idEstadoMan;
    }

    public void setIdEstadoMan(Integer idEstadoMan) {
        this.idEstadoMan = idEstadoMan;
    }

    public String getEstmDescripcion() {
        return estmDescripcion;
    }

    public void setEstmDescripcion(String estmDescripcion) {
        this.estmDescripcion = estmDescripcion;
    }

    public String getEstmSigla() {
        return estmSigla;
    }

    public void setEstmSigla(String estmSigla) {
        this.estmSigla = estmSigla;
    }

    public Boolean getEstmEstado() {
        return estmEstado;
    }

    public void setEstmEstado(Boolean estmEstado) {
        this.estmEstado = estmEstado;
    }

    @XmlTransient
    public Collection<Mantenimiento> getMantenimientoCollection() {
        return mantenimientoCollection;
    }

    public void setMantenimientoCollection(Collection<Mantenimiento> mantenimientoCollection) {
        this.mantenimientoCollection = mantenimientoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoMan != null ? idEstadoMan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoMantenimiento)) {
            return false;
        }
        EstadoMantenimiento other = (EstadoMantenimiento) object;
        if ((this.idEstadoMan == null && other.idEstadoMan != null) || (this.idEstadoMan != null && !this.idEstadoMan.equals(other.idEstadoMan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.EstadoMantenimiento[ idEstadoMan=" + idEstadoMan + " ]";
    }
    
}
