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
@Table(name = "seguimiento_emergencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeguimientoEmergencia.findAll", query = "SELECT s FROM SeguimientoEmergencia s")})
public class SeguimientoEmergencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_seguimiento_emergencia")
    private Integer idSeguimientoEmergencia;
    @Column(name = "sege_descripcion")
    private String segeDescripcion;
    @Column(name = "sege_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date segeFecha;
    @Column(name = "sege_observacion")
    private String segeObservacion;
    @JoinColumn(name = "id_emergencia", referencedColumnName = "id_emergencia")
    @ManyToOne
    private Emergencia idEmergencia;

    public SeguimientoEmergencia() {
    }

    public SeguimientoEmergencia(Integer idSeguimientoEmergencia) {
        this.idSeguimientoEmergencia = idSeguimientoEmergencia;
    }

    public Integer getIdSeguimientoEmergencia() {
        return idSeguimientoEmergencia;
    }

    public void setIdSeguimientoEmergencia(Integer idSeguimientoEmergencia) {
        this.idSeguimientoEmergencia = idSeguimientoEmergencia;
    }

    public String getSegeDescripcion() {
        return segeDescripcion;
    }

    public void setSegeDescripcion(String segeDescripcion) {
        this.segeDescripcion = segeDescripcion;
    }

    public Date getSegeFecha() {
        return segeFecha;
    }

    public void setSegeFecha(Date segeFecha) {
        this.segeFecha = segeFecha;
    }

    public String getSegeObservacion() {
        return segeObservacion;
    }

    public void setSegeObservacion(String segeObservacion) {
        this.segeObservacion = segeObservacion;
    }

    public Emergencia getIdEmergencia() {
        return idEmergencia;
    }

    public void setIdEmergencia(Emergencia idEmergencia) {
        this.idEmergencia = idEmergencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSeguimientoEmergencia != null ? idSeguimientoEmergencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeguimientoEmergencia)) {
            return false;
        }
        SeguimientoEmergencia other = (SeguimientoEmergencia) object;
        if ((this.idSeguimientoEmergencia == null && other.idSeguimientoEmergencia != null) || (this.idSeguimientoEmergencia != null && !this.idSeguimientoEmergencia.equals(other.idSeguimientoEmergencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.SeguimientoEmergencia[ idSeguimientoEmergencia=" + idSeguimientoEmergencia + " ]";
    }
    
}
