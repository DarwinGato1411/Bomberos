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
@Table(name = "estado_documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoDocumento.findAll", query = "SELECT e FROM EstadoDocumento e")})
public class EstadoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_documento")
    private Integer idEstadoDocumento;
    @Column(name = "est_descripcion")
    private String estDescripcion;
    @Column(name = "est_sigla")
    private String estSigla;
    @Column(name = "est_peso")
    private Integer estPeso;
    @OneToMany(mappedBy = "idEstadoDocumento")
    private Collection<Inspeccion> inspeccionCollection;
    @OneToMany(mappedBy = "idEstadoDocumento")
    private Collection<SolicitudPermiso> solicitudPermisoCollection;
    @OneToMany(mappedBy = "idEstadoDocumento")
    private Collection<Permiso> permisoCollection;

    public EstadoDocumento() {
    }

    public EstadoDocumento(Integer idEstadoDocumento) {
        this.idEstadoDocumento = idEstadoDocumento;
    }

    public Integer getIdEstadoDocumento() {
        return idEstadoDocumento;
    }

    public void setIdEstadoDocumento(Integer idEstadoDocumento) {
        this.idEstadoDocumento = idEstadoDocumento;
    }

    public String getEstDescripcion() {
        return estDescripcion;
    }

    public void setEstDescripcion(String estDescripcion) {
        this.estDescripcion = estDescripcion;
    }

    public String getEstSigla() {
        return estSigla;
    }

    public void setEstSigla(String estSigla) {
        this.estSigla = estSigla;
    }

    public Integer getEstPeso() {
        return estPeso;
    }

    public void setEstPeso(Integer estPeso) {
        this.estPeso = estPeso;
    }

    @XmlTransient
    public Collection<Inspeccion> getInspeccionCollection() {
        return inspeccionCollection;
    }

    public void setInspeccionCollection(Collection<Inspeccion> inspeccionCollection) {
        this.inspeccionCollection = inspeccionCollection;
    }

    @XmlTransient
    public Collection<SolicitudPermiso> getSolicitudPermisoCollection() {
        return solicitudPermisoCollection;
    }

    public void setSolicitudPermisoCollection(Collection<SolicitudPermiso> solicitudPermisoCollection) {
        this.solicitudPermisoCollection = solicitudPermisoCollection;
    }

    @XmlTransient
    public Collection<Permiso> getPermisoCollection() {
        return permisoCollection;
    }

    public void setPermisoCollection(Collection<Permiso> permisoCollection) {
        this.permisoCollection = permisoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoDocumento != null ? idEstadoDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoDocumento)) {
            return false;
        }
        EstadoDocumento other = (EstadoDocumento) object;
        if ((this.idEstadoDocumento == null && other.idEstadoDocumento != null) || (this.idEstadoDocumento != null && !this.idEstadoDocumento.equals(other.idEstadoDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.EstadoDocumento[ idEstadoDocumento=" + idEstadoDocumento + " ]";
    }
    
}
