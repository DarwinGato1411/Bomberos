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
@Table(name = "documentos_adjunto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentosAdjunto.findAll", query = "SELECT d FROM DocumentosAdjunto d")})
public class DocumentosAdjunto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_adjunto")
    private Integer idAdjunto;
    @Column(name = "adj_descripcion")
    private String adjDescripcion;
    @Column(name = "adj_path")
    private String adjPath;
    @Column(name = "adj_estado_archivo")
    private Boolean adjEstadoArchivo;
    @Column(name = "adj_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date adjFecha;
    @JoinColumn(name = "id_solcitud_per", referencedColumnName = "id_solcitud_per")
    @ManyToOne
    private SolicitudPermiso idSolcitudPer;

    public DocumentosAdjunto() {
    }

    public DocumentosAdjunto(Integer idAdjunto) {
        this.idAdjunto = idAdjunto;
    }

    public Integer getIdAdjunto() {
        return idAdjunto;
    }

    public void setIdAdjunto(Integer idAdjunto) {
        this.idAdjunto = idAdjunto;
    }

    public String getAdjDescripcion() {
        return adjDescripcion;
    }

    public void setAdjDescripcion(String adjDescripcion) {
        this.adjDescripcion = adjDescripcion;
    }

    public String getAdjPath() {
        return adjPath;
    }

    public void setAdjPath(String adjPath) {
        this.adjPath = adjPath;
    }

    public Date getAdjFecha() {
        return adjFecha;
    }

    public void setAdjFecha(Date adjFecha) {
        this.adjFecha = adjFecha;
    }

    public SolicitudPermiso getIdSolcitudPer() {
        return idSolcitudPer;
    }

    public void setIdSolcitudPer(SolicitudPermiso idSolcitudPer) {
        this.idSolcitudPer = idSolcitudPer;
    }

    public Boolean getAdjEstadoArchivo() {
        return adjEstadoArchivo;
    }

    public void setAdjEstadoArchivo(Boolean adjEstadoArchivo) {
        this.adjEstadoArchivo = adjEstadoArchivo;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdjunto != null ? idAdjunto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentosAdjunto)) {
            return false;
        }
        DocumentosAdjunto other = (DocumentosAdjunto) object;
        if ((this.idAdjunto == null && other.idAdjunto != null) || (this.idAdjunto != null && !this.idAdjunto.equals(other.idAdjunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.DocumentosAdjunto[ idAdjunto=" + idAdjunto + " ]";
    }
    
}
