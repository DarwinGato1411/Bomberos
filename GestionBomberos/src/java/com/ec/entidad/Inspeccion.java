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
@Table(name = "inspeccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inspeccion.findAll", query = "SELECT i FROM Inspeccion i")})
public class Inspeccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_inspeccion")
    private Integer idInspeccion;
    @Column(name = "ins_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insFecha;
    @Column(name = "ins_descripcion")
    private String insDescripcion;
    @Column(name = "ins_observacion")
    private String insObservacion;
    @Column(name = "ins_localidad")
    private String insLocalidad;
    @Column(name = "ins_referencia")
    private String insReferencia;
    @Column(name = "ins_num_doc_por_agente")
    private Integer insNumDocPorAgente;
    
    @JoinColumn(name = "id_bombero", referencedColumnName = "id_bombero")
    @ManyToOne
    private Bombero idBombero;
    @JoinColumn(name = "id_estado_documento", referencedColumnName = "id_estado_documento")
    @ManyToOne
    private EstadoDocumento idEstadoDocumento;
    @JoinColumn(name = "id_solcitud_per", referencedColumnName = "id_solcitud_per")
    @ManyToOne
    private SolicitudPermiso idSolcitudPer;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;
    @OneToMany(mappedBy = "idInspeccion")
    private Collection<DocumentosAdjuntoInspeccion> documentosAdjuntoInspeccionCollection;
    @OneToMany(mappedBy = "idInspeccion")
    private Collection<Permiso> permisoCollection;

    public Inspeccion() {
    }

    public Inspeccion(Integer idInspeccion) {
        this.idInspeccion = idInspeccion;
    }

    public Integer getIdInspeccion() {
        return idInspeccion;
    }

    public void setIdInspeccion(Integer idInspeccion) {
        this.idInspeccion = idInspeccion;
    }

    public Date getInsFecha() {
        return insFecha;
    }

    public void setInsFecha(Date insFecha) {
        this.insFecha = insFecha;
    }

    public String getInsDescripcion() {
        return insDescripcion;
    }

    public void setInsDescripcion(String insDescripcion) {
        this.insDescripcion = insDescripcion;
    }

    public String getInsObservacion() {
        return insObservacion;
    }

    public void setInsObservacion(String insObservacion) {
        this.insObservacion = insObservacion;
    }

    public String getInsLocalidad() {
        return insLocalidad;
    }

    public void setInsLocalidad(String insLocalidad) {
        this.insLocalidad = insLocalidad;
    }

    public String getInsReferencia() {
        return insReferencia;
    }

    public void setInsReferencia(String insReferencia) {
        this.insReferencia = insReferencia;
    }

    public Integer getInsNumDocPorAgente() {
        return insNumDocPorAgente;
    }

    public void setInsNumDocPorAgente(Integer insNumDocPorAgente) {
        this.insNumDocPorAgente = insNumDocPorAgente;
    }
    
    public Bombero getIdBombero() {
        return idBombero;
    }

    public void setIdBombero(Bombero idBombero) {
        this.idBombero = idBombero;
    }

    public EstadoDocumento getIdEstadoDocumento() {
        return idEstadoDocumento;
    }

    public void setIdEstadoDocumento(EstadoDocumento idEstadoDocumento) {
        this.idEstadoDocumento = idEstadoDocumento;
    }

    public SolicitudPermiso getIdSolcitudPer() {
        return idSolcitudPer;
    }

    public void setIdSolcitudPer(SolicitudPermiso idSolcitudPer) {
        this.idSolcitudPer = idSolcitudPer;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public Collection<DocumentosAdjuntoInspeccion> getDocumentosAdjuntoInspeccionCollection() {
        return documentosAdjuntoInspeccionCollection;
    }

    public void setDocumentosAdjuntoInspeccionCollection(Collection<DocumentosAdjuntoInspeccion> documentosAdjuntoInspeccionCollection) {
        this.documentosAdjuntoInspeccionCollection = documentosAdjuntoInspeccionCollection;
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
        hash += (idInspeccion != null ? idInspeccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inspeccion)) {
            return false;
        }
        Inspeccion other = (Inspeccion) object;
        if ((this.idInspeccion == null && other.idInspeccion != null) || (this.idInspeccion != null && !this.idInspeccion.equals(other.idInspeccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Inspeccion[ idInspeccion=" + idInspeccion + " ]";
    }
    
}
