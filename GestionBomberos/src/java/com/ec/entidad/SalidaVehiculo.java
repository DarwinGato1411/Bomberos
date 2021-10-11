/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "salida_vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalidaVehiculo.findAll", query = "SELECT s FROM SalidaVehiculo s")})
public class SalidaVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_salida_vehiculo")
    private Integer idSalidaVehiculo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salv_salida")
    private BigDecimal salvSalida;
    @Column(name = "salv_retorno")
    private BigDecimal salvRetorno;
    @Column(name = "salv_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date salvFecha;
    @Column(name = "salv_observacion")
    private String salvObservacion;
    @JoinColumn(name = "id_bombero", referencedColumnName = "id_bombero")
    @ManyToOne
    private Bombero idBombero;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id_vehiculo")
    @ManyToOne
    private Vehiculo idVehiculo;

    public SalidaVehiculo() {
    }

    public SalidaVehiculo(Integer idSalidaVehiculo) {
        this.idSalidaVehiculo = idSalidaVehiculo;
    }

    public Integer getIdSalidaVehiculo() {
        return idSalidaVehiculo;
    }

    public void setIdSalidaVehiculo(Integer idSalidaVehiculo) {
        this.idSalidaVehiculo = idSalidaVehiculo;
    }

    public BigDecimal getSalvSalida() {
        return salvSalida;
    }

    public void setSalvSalida(BigDecimal salvSalida) {
        this.salvSalida = salvSalida;
    }

    public BigDecimal getSalvRetorno() {
        return salvRetorno;
    }

    public void setSalvRetorno(BigDecimal salvRetorno) {
        this.salvRetorno = salvRetorno;
    }

    public Date getSalvFecha() {
        return salvFecha;
    }

    public void setSalvFecha(Date salvFecha) {
        this.salvFecha = salvFecha;
    }

    public String getSalvObservacion() {
        return salvObservacion;
    }

    public void setSalvObservacion(String salvObservacion) {
        this.salvObservacion = salvObservacion;
    }

    public Bombero getIdBombero() {
        return idBombero;
    }

    public void setIdBombero(Bombero idBombero) {
        this.idBombero = idBombero;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Vehiculo getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Vehiculo idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSalidaVehiculo != null ? idSalidaVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalidaVehiculo)) {
            return false;
        }
        SalidaVehiculo other = (SalidaVehiculo) object;
        if ((this.idSalidaVehiculo == null && other.idSalidaVehiculo != null) || (this.idSalidaVehiculo != null && !this.idSalidaVehiculo.equals(other.idSalidaVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.SalidaVehiculo[ idSalidaVehiculo=" + idSalidaVehiculo + " ]";
    }
    
}
