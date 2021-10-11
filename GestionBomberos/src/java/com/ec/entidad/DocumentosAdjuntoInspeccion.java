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
@Table(name = "documentos_adjunto_inspeccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentosAdjuntoInspeccion.findAll", query = "SELECT d FROM DocumentosAdjuntoInspeccion d")})
public class DocumentosAdjuntoInspeccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_adjunto_inspeccion")
    private Integer idAdjuntoInspeccion;
    @Column(name = "adji_descripcion")
    private String adjiDescripcion;
    @Column(name = "adji_path")
    private String adjiPath;
    @Column(name = "adji_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date adjiFecha;
    @JoinColumn(name = "id_inspeccion", referencedColumnName = "id_inspeccion")
    @ManyToOne
    private Inspeccion idInspeccion;

    public DocumentosAdjuntoInspeccion() {
    }

    public DocumentosAdjuntoInspeccion(Integer idAdjuntoInspeccion) {
        this.idAdjuntoInspeccion = idAdjuntoInspeccion;
    }

    public Integer getIdAdjuntoInspeccion() {
        return idAdjuntoInspeccion;
    }

    public void setIdAdjuntoInspeccion(Integer idAdjuntoInspeccion) {
        this.idAdjuntoInspeccion = idAdjuntoInspeccion;
    }

    public String getAdjiDescripcion() {
        return adjiDescripcion;
    }

    public void setAdjiDescripcion(String adjiDescripcion) {
        this.adjiDescripcion = adjiDescripcion;
    }

    public String getAdjiPath() {
        return adjiPath;
    }

    public void setAdjiPath(String adjiPath) {
        this.adjiPath = adjiPath;
    }

    public Date getAdjiFecha() {
        return adjiFecha;
    }

    public void setAdjiFecha(Date adjiFecha) {
        this.adjiFecha = adjiFecha;
    }

    public Inspeccion getIdInspeccion() {
        return idInspeccion;
    }

    public void setIdInspeccion(Inspeccion idInspeccion) {
        this.idInspeccion = idInspeccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdjuntoInspeccion != null ? idAdjuntoInspeccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentosAdjuntoInspeccion)) {
            return false;
        }
        DocumentosAdjuntoInspeccion other = (DocumentosAdjuntoInspeccion) object;
        if ((this.idAdjuntoInspeccion == null && other.idAdjuntoInspeccion != null) || (this.idAdjuntoInspeccion != null && !this.idAdjuntoInspeccion.equals(other.idAdjuntoInspeccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.DocumentosAdjuntoInspeccion[ idAdjuntoInspeccion=" + idAdjuntoInspeccion + " ]";
    }
    
}
