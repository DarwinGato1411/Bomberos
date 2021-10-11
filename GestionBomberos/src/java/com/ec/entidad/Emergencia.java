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
@Table(name = "emergencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emergencia.findAll", query = "SELECT e FROM Emergencia e")})
public class Emergencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_emergencia")
    private Integer idEmergencia;
    @Column(name = "emer_descripcion")
    private String emerDescripcion;
    @Column(name = "emer_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date emerFecha;
    @Column(name = "emer_lugar")
    private String emerLugar;
    @Column(name = "emer_longitud")
    private String emerLongitud;
    @Column(name = "emer_latitud")
    private String emerLatitud;
    @Column(name = "emer_foto_prin")
    private String emerFotoPrin;
    @Column(name = "emer_medio_conocimiento")
    private String emerMedioConocimiento;
    @Column(name = "emer_cedula_reportante")
    private String emerCedulaReportante;
    @Column(name = "emer_nombre_reporta")
    private String emerNombreReporta;
    @OneToMany(mappedBy = "idEmergencia")
    private Collection<SeguimientoEmergencia> seguimientoEmergenciaCollection;
    @JoinColumn(name = "id_bombero", referencedColumnName = "id_bombero")
    @ManyToOne
    private Bombero idBombero;
    @JoinColumn(name = "id_tipo_emergencia", referencedColumnName = "id_tipo_emergencia")
    @ManyToOne
    private TipoEmergencia idTipoEmergencia;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;

    public Emergencia() {
    }

    public Emergencia(Integer idEmergencia) {
        this.idEmergencia = idEmergencia;
    }

    public Integer getIdEmergencia() {
        return idEmergencia;
    }

    public void setIdEmergencia(Integer idEmergencia) {
        this.idEmergencia = idEmergencia;
    }

    public String getEmerDescripcion() {
        return emerDescripcion;
    }

    public void setEmerDescripcion(String emerDescripcion) {
        this.emerDescripcion = emerDescripcion;
    }

    public Date getEmerFecha() {
        return emerFecha;
    }

    public void setEmerFecha(Date emerFecha) {
        this.emerFecha = emerFecha;
    }

    public String getEmerLugar() {
        return emerLugar;
    }

    public void setEmerLugar(String emerLugar) {
        this.emerLugar = emerLugar;
    }

    public String getEmerLongitud() {
        return emerLongitud;
    }

    public void setEmerLongitud(String emerLongitud) {
        this.emerLongitud = emerLongitud;
    }

    public String getEmerLatitud() {
        return emerLatitud;
    }

    public void setEmerLatitud(String emerLatitud) {
        this.emerLatitud = emerLatitud;
    }

    public String getEmerFotoPrin() {
        return emerFotoPrin;
    }

    public void setEmerFotoPrin(String emerFotoPrin) {
        this.emerFotoPrin = emerFotoPrin;
    }

    public String getEmerMedioConocimiento() {
        return emerMedioConocimiento;
    }

    public void setEmerMedioConocimiento(String emerMedioConocimiento) {
        this.emerMedioConocimiento = emerMedioConocimiento;
    }

    public String getEmerCedulaReportante() {
        return emerCedulaReportante;
    }

    public void setEmerCedulaReportante(String emerCedulaReportante) {
        this.emerCedulaReportante = emerCedulaReportante;
    }

    public String getEmerNombreReporta() {
        return emerNombreReporta;
    }

    public void setEmerNombreReporta(String emerNombreReporta) {
        this.emerNombreReporta = emerNombreReporta;
    }

    @XmlTransient
    public Collection<SeguimientoEmergencia> getSeguimientoEmergenciaCollection() {
        return seguimientoEmergenciaCollection;
    }

    public void setSeguimientoEmergenciaCollection(Collection<SeguimientoEmergencia> seguimientoEmergenciaCollection) {
        this.seguimientoEmergenciaCollection = seguimientoEmergenciaCollection;
    }

    public Bombero getIdBombero() {
        return idBombero;
    }

    public void setIdBombero(Bombero idBombero) {
        this.idBombero = idBombero;
    }

    public TipoEmergencia getIdTipoEmergencia() {
        return idTipoEmergencia;
    }

    public void setIdTipoEmergencia(TipoEmergencia idTipoEmergencia) {
        this.idTipoEmergencia = idTipoEmergencia;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmergencia != null ? idEmergencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emergencia)) {
            return false;
        }
        Emergencia other = (Emergencia) object;
        if ((this.idEmergencia == null && other.idEmergencia != null) || (this.idEmergencia != null && !this.idEmergencia.equals(other.idEmergencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Emergencia[ idEmergencia=" + idEmergencia + " ]";
    }
    
}
