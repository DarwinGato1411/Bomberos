/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "solicitud_permiso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudPermiso.findAll", query = "SELECT s FROM SolicitudPermiso s")})
public class SolicitudPermiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_solcitud_per")
    private Integer idSolcitudPer;
    @Column(name = "solp_descripcion")
    private String solpDescripcion;
    @Column(name = "solp_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date solpFecha;
    @Column(name = "sol_num_cedula")
    private String solNumCedula;
    @Column(name = "solp_nombre_sol")
    private String solpNombreSol;
    @Column(name = "solp_apellido_sol")
    private String solpApellidoSol;
    @Column(name = "solp_direccion")
    private String solpDireccion;
    @Column(name = "solp_referencia")
    private String solpReferencia;
    @Column(name = "solp_path_fotografia")
    private String solpPathFotografia;
    @Column(name = "solp_observacion")
    private String solpObservacion;
    @Column(name = "solp_path_ruc")
    private String solpPathRuc;
    @Column(name = "solp_path_cedula")
    private String solpPathCedula;
    @Column(name = "solp_pago_impuesto")
    private String solpPagoImpuesto;
    @Column(name = "solp_actividad")
    private String solpActividad;
    @Column(name = "solp_nombre_negocio")
    private String solpNombreNegocio;
    @Column(name = "solp_barrio_urbanizacion")
    private String solpBarrioUrbanizacion;
    @Column(name = "solp_calle")
    private String solpCalle;
    @Column(name = "solp_numero")
    private String solpNumero;
    @Column(name = "solp_interseccion")
    private String solpInterseccion;
    @Column(name = "solp_edificio")
    private String solpEdificio;
    @Column(name = "solp_piso")
    private Integer solpPiso;
    @Column(name = "solp_departamento")
    private String solpDepartamento;
    @Column(name = "solp_persona_contacto")
    private String solpPersonaContacto;
    @Column(name = "solp_telefono_contacto")
    private String solpTelefonoContacto;
    @Column(name = "solp_telefono")
    private String solpTelefono;
    @Column(name = "solp_archivo_ruc")
    private String solpArchivoRuc;
    @Column(name = "solp_archivo_impuesto")
    private String solpArchivoImpuesto;
    @Column(name = "solp_path_croquis")
    private String solpPathCroquis;
    @Column(name = "solp_archivo_croquis")
    private String solpArchivoCroquis;
    @Column(name = "solp_numero_ocupantes")
    private Integer solpNumeroOcupantes;
    @Column(name = "solp_area_construccion")
    private BigDecimal solpAreaConstruccion;
    @OneToMany(mappedBy = "idSolcitudPer")
    private Collection<DocumentosAdjunto> documentosAdjuntoCollection;
    @OneToMany(mappedBy = "idSolcitudPer")
    private Collection<Inspeccion> inspeccionCollection;
    @JoinColumn(name = "id_estado_documento", referencedColumnName = "id_estado_documento")
    @ManyToOne
    private EstadoDocumento idEstadoDocumento;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;

    @JoinColumn(name = "id_parroquia", referencedColumnName = "id_parroquia")
    @ManyToOne
    private Parroquia idParroquia;

    public SolicitudPermiso() {
    }

    public SolicitudPermiso(Integer idSolcitudPer) {
        this.idSolcitudPer = idSolcitudPer;
    }

    public Integer getIdSolcitudPer() {
        return idSolcitudPer;
    }

    public void setIdSolcitudPer(Integer idSolcitudPer) {
        this.idSolcitudPer = idSolcitudPer;
    }

    public String getSolpDescripcion() {
        return solpDescripcion;
    }

    public void setSolpDescripcion(String solpDescripcion) {
        this.solpDescripcion = solpDescripcion;
    }

    public Date getSolpFecha() {
        return solpFecha;
    }

    public void setSolpFecha(Date solpFecha) {
        this.solpFecha = solpFecha;
    }

    public String getSolNumCedula() {
        return solNumCedula;
    }

    public void setSolNumCedula(String solNumCedula) {
        this.solNumCedula = solNumCedula;
    }

    public String getSolpNombreSol() {
        return solpNombreSol;
    }

    public void setSolpNombreSol(String solpNombreSol) {
        this.solpNombreSol = solpNombreSol;
    }

    public String getSolpApellidoSol() {
        return solpApellidoSol;
    }

    public void setSolpApellidoSol(String solpApellidoSol) {
        this.solpApellidoSol = solpApellidoSol;
    }

    public String getSolpDireccion() {
        return solpDireccion;
    }

    public void setSolpDireccion(String solpDireccion) {
        this.solpDireccion = solpDireccion;
    }

    public String getSolpReferencia() {
        return solpReferencia;
    }

    public void setSolpReferencia(String solpReferencia) {
        this.solpReferencia = solpReferencia;
    }

    public String getSolpPathFotografia() {
        return solpPathFotografia;
    }

    public void setSolpPathFotografia(String solpPathFotografia) {
        this.solpPathFotografia = solpPathFotografia;
    }

    public String getSolpObservacion() {
        return solpObservacion;
    }

    public void setSolpObservacion(String solpObservacion) {
        this.solpObservacion = solpObservacion;
    }

    public String getSolpPathRuc() {
        return solpPathRuc;
    }

    public void setSolpPathRuc(String solpPathRuc) {
        this.solpPathRuc = solpPathRuc;
    }

    public String getSolpPathCedula() {
        return solpPathCedula;
    }

    public void setSolpPathCedula(String solpPathCedula) {
        this.solpPathCedula = solpPathCedula;
    }

    public String getSolpPagoImpuesto() {
        return solpPagoImpuesto;
    }

    public void setSolpPagoImpuesto(String solpPagoImpuesto) {
        this.solpPagoImpuesto = solpPagoImpuesto;
    }

    public Parroquia getIdParroquia() {
        return idParroquia;
    }

    public void setIdParroquia(Parroquia idParroquia) {
        this.idParroquia = idParroquia;
    }

    @XmlTransient
    public Collection<DocumentosAdjunto> getDocumentosAdjuntoCollection() {
        return documentosAdjuntoCollection;
    }

    public void setDocumentosAdjuntoCollection(Collection<DocumentosAdjunto> documentosAdjuntoCollection) {
        this.documentosAdjuntoCollection = documentosAdjuntoCollection;
    }

    @XmlTransient
    public Collection<Inspeccion> getInspeccionCollection() {
        return inspeccionCollection;
    }

    public void setInspeccionCollection(Collection<Inspeccion> inspeccionCollection) {
        this.inspeccionCollection = inspeccionCollection;
    }

    public EstadoDocumento getIdEstadoDocumento() {
        return idEstadoDocumento;
    }

    public void setIdEstadoDocumento(EstadoDocumento idEstadoDocumento) {
        this.idEstadoDocumento = idEstadoDocumento;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getSolpActividad() {
        return solpActividad;
    }

    public void setSolpActividad(String solpActividad) {
        this.solpActividad = solpActividad;
    }

    public String getSolpBarrioUrbanizacion() {
        return solpBarrioUrbanizacion;
    }

    public void setSolpBarrioUrbanizacion(String solpBarrioUrbanizacion) {
        this.solpBarrioUrbanizacion = solpBarrioUrbanizacion;
    }

    public String getSolpCalle() {
        return solpCalle;
    }

    public void setSolpCalle(String solpCalle) {
        this.solpCalle = solpCalle;
    }

    public String getSolpNumero() {
        return solpNumero;
    }

    public void setSolpNumero(String solpNumero) {
        this.solpNumero = solpNumero;
    }

    public String getSolpInterseccion() {
        return solpInterseccion;
    }

    public void setSolpInterseccion(String solpInterseccion) {
        this.solpInterseccion = solpInterseccion;
    }

    public String getSolpEdificio() {
        return solpEdificio;
    }

    public void setSolpEdificio(String solpEdificio) {
        this.solpEdificio = solpEdificio;
    }

    public Integer getSolpPiso() {
        return solpPiso;
    }

    public void setSolpPiso(Integer solpPiso) {
        this.solpPiso = solpPiso;
    }

    public String getSolpDepartamento() {
        return solpDepartamento;
    }

    public void setSolpDepartamento(String solpDepartamento) {
        this.solpDepartamento = solpDepartamento;
    }

    public String getSolpPersonaContacto() {
        return solpPersonaContacto;
    }

    public void setSolpPersonaContacto(String solpPersonaContacto) {
        this.solpPersonaContacto = solpPersonaContacto;
    }

    public String getSolpTelefonoContacto() {
        return solpTelefonoContacto;
    }

    public void setSolpTelefonoContacto(String solpTelefonoContacto) {
        this.solpTelefonoContacto = solpTelefonoContacto;
    }

    public Integer getSolpNumeroOcupantes() {
        return solpNumeroOcupantes;
    }

    public void setSolpNumeroOcupantes(Integer solpNumeroOcupantes) {
        this.solpNumeroOcupantes = solpNumeroOcupantes;
    }

    public BigDecimal getSolpAreaConstruccion() {
        return solpAreaConstruccion;
    }

//    cambios
    public void setSolpAreaConstruccion(BigDecimal solpAreaConstruccion) {
        this.solpAreaConstruccion = solpAreaConstruccion;
    }

    public String getSolpNombreNegocio() {
        return solpNombreNegocio;
    }

    public void setSolpNombreNegocio(String solpNombreNegocio) {
        this.solpNombreNegocio = solpNombreNegocio;
    }

    public String getSolpTelefono() {
        return solpTelefono;
    }

    public void setSolpTelefono(String solpTelefono) {
        this.solpTelefono = solpTelefono;
    }

    public String getSolpArchivoRuc() {
        return solpArchivoRuc;
    }

    public void setSolpArchivoRuc(String solpArchivoRuc) {
        this.solpArchivoRuc = solpArchivoRuc;
    }

    public String getSolpArchivoImpuesto() {
        return solpArchivoImpuesto;
    }

    public void setSolpArchivoImpuesto(String solpArchivoImpuesto) {
        this.solpArchivoImpuesto = solpArchivoImpuesto;
    }

    public String getSolpPathCroquis() {
        return solpPathCroquis;
    }

    public void setSolpPathCroquis(String solpPathCroquis) {
        this.solpPathCroquis = solpPathCroquis;
    }

    public String getSolpArchivoCroquis() {
        return solpArchivoCroquis;
    }

    public void setSolpArchivoCroquis(String solpArchivoCroquis) {
        this.solpArchivoCroquis = solpArchivoCroquis;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSolcitudPer != null ? idSolcitudPer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudPermiso)) {
            return false;
        }
        SolicitudPermiso other = (SolicitudPermiso) object;
        if ((this.idSolcitudPer == null && other.idSolcitudPer != null) || (this.idSolcitudPer != null && !this.idSolcitudPer.equals(other.idSolcitudPer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.SolicitudPermiso[ idSolcitudPer=" + idSolcitudPer + " ]";
    }

}
