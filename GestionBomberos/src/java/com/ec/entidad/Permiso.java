/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "permiso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permiso.findAll", query = "SELECT p FROM Permiso p")})
public class Permiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_permiso")
    private Integer idPermiso;
    @Column(name = "per_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date perFecha;
    @Column(name = "per_descripcion")
    private String perDescripcion;
    @Column(name = "per_texto_legal")
    private String perTextoLegal;
    @Column(name = "per_firma")
    private String perFirma;
    
    @Column(name = "per_cobro_manual")
    private Boolean perCobroManual;
    @Column(name = "per_pagado")
    private Boolean perPagado;
    
    @OneToMany(mappedBy = "idPermiso")
    private Collection<DocumentosAdjuntoPermiso> documentosAdjuntoPermisoCollection;
    @JoinColumn(name = "id_estado_documento", referencedColumnName = "id_estado_documento")
    @ManyToOne
    private EstadoDocumento idEstadoDocumento;
    @JoinColumn(name = "id_inspeccion", referencedColumnName = "id_inspeccion")
    @ManyToOne
    private Inspeccion idInspeccion;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;

    @OneToMany(mappedBy = "idPermiso")
    private Collection<Cobro> cobroCollection;

    public Permiso() {
    }

    public Permiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public Integer getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public Date getPerFecha() {
        return perFecha;
    }

    public void setPerFecha(Date perFecha) {
        this.perFecha = perFecha;
    }

    public String getPerDescripcion() {
        return perDescripcion;
    }

    public void setPerDescripcion(String perDescripcion) {
        this.perDescripcion = perDescripcion;
    }

    public String getPerTextoLegal() {
        return perTextoLegal;
    }

    public void setPerTextoLegal(String perTextoLegal) {
        this.perTextoLegal = perTextoLegal;
    }

    public String getPerFirma() {
        return perFirma;
    }

    public void setPerFirma(String perFirma) {
        this.perFirma = perFirma;
    }

    @XmlTransient
    public Collection<DocumentosAdjuntoPermiso> getDocumentosAdjuntoPermisoCollection() {
        return documentosAdjuntoPermisoCollection;
    }

    public void setDocumentosAdjuntoPermisoCollection(Collection<DocumentosAdjuntoPermiso> documentosAdjuntoPermisoCollection) {
        this.documentosAdjuntoPermisoCollection = documentosAdjuntoPermisoCollection;
    }

    public EstadoDocumento getIdEstadoDocumento() {
        return idEstadoDocumento;
    }

    public void setIdEstadoDocumento(EstadoDocumento idEstadoDocumento) {
        this.idEstadoDocumento = idEstadoDocumento;
    }

    public Inspeccion getIdInspeccion() {
        return idInspeccion;
    }

    public void setIdInspeccion(Inspeccion idInspeccion) {
        this.idInspeccion = idInspeccion;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Boolean getPerCobroManual() {
        return perCobroManual;
    }

    public void setPerCobroManual(Boolean perCobroManual) {
        this.perCobroManual = perCobroManual;
    }

    public Boolean getPerPagado() {
        return perPagado;
    }

    public void setPerPagado(Boolean perPagado) {
        this.perPagado = perPagado;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermiso != null ? idPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permiso)) {
            return false;
        }
        Permiso other = (Permiso) object;
        if ((this.idPermiso == null && other.idPermiso != null) || (this.idPermiso != null && !this.idPermiso.equals(other.idPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Permiso[ idPermiso=" + idPermiso + " ]";
    }

    public Collection<Cobro> getCobroCollection() {
        return cobroCollection;
    }

    public void setCobroCollection(Collection<Cobro> cobroCollection) {
        this.cobroCollection = cobroCollection;
    }

}
