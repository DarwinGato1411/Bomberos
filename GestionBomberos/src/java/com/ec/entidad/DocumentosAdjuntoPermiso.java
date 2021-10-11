/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "documentos_adjunto_permiso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentosAdjuntoPermiso.findAll", query = "SELECT d FROM DocumentosAdjuntoPermiso d")})
public class DocumentosAdjuntoPermiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_adjunto_permiso")
    private Integer idAdjuntoPermiso;
    @Column(name = "adjp_descripcion")
    private String adjpDescripcion;
    @Column(name = "adjp_path")
    private String adjpPath;
    @Column(name = "adjp_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date adjpFecha;
    @JoinColumn(name = "id_permiso", referencedColumnName = "id_permiso")
    @ManyToOne
    private Permiso idPermiso;

    public DocumentosAdjuntoPermiso() {
    }

    public DocumentosAdjuntoPermiso(Integer idAdjuntoPermiso) {
        this.idAdjuntoPermiso = idAdjuntoPermiso;
    }

    public Integer getIdAdjuntoPermiso() {
        return idAdjuntoPermiso;
    }

    public void setIdAdjuntoPermiso(Integer idAdjuntoPermiso) {
        this.idAdjuntoPermiso = idAdjuntoPermiso;
    }

    public String getAdjpDescripcion() {
        return adjpDescripcion;
    }

    public void setAdjpDescripcion(String adjpDescripcion) {
        this.adjpDescripcion = adjpDescripcion;
    }

    public String getAdjpPath() {
        return adjpPath;
    }

    public void setAdjpPath(String adjpPath) {
        this.adjpPath = adjpPath;
    }

    public Date getAdjpFecha() {
        return adjpFecha;
    }

    public void setAdjpFecha(Date adjpFecha) {
        this.adjpFecha = adjpFecha;
    }

    public Permiso getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Permiso idPermiso) {
        this.idPermiso = idPermiso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdjuntoPermiso != null ? idAdjuntoPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentosAdjuntoPermiso)) {
            return false;
        }
        DocumentosAdjuntoPermiso other = (DocumentosAdjuntoPermiso) object;
        if ((this.idAdjuntoPermiso == null && other.idAdjuntoPermiso != null) || (this.idAdjuntoPermiso != null && !this.idAdjuntoPermiso.equals(other.idAdjuntoPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.DocumentosAdjuntoPermiso[ idAdjuntoPermiso=" + idAdjuntoPermiso + " ]";
    }
    
}
