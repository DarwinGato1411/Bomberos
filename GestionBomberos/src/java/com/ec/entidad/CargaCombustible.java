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
@Table(name = "carga_combustible")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CargaCombustible.findAll", query = "SELECT c FROM CargaCombustible c")})
public class CargaCombustible implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_carga_combustible")
    private Integer idCargaCombustible;
    @Column(name = "car_descripcion")
    private String carDescripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "car_valor")
    private BigDecimal carValor;
    @Column(name = "car_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date carFecha;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id_vehiculo")
    @ManyToOne
    private Vehiculo idVehiculo;

    public CargaCombustible() {
    }

    public CargaCombustible(Integer idCargaCombustible) {
        this.idCargaCombustible = idCargaCombustible;
    }

    public Integer getIdCargaCombustible() {
        return idCargaCombustible;
    }

    public void setIdCargaCombustible(Integer idCargaCombustible) {
        this.idCargaCombustible = idCargaCombustible;
    }

    public String getCarDescripcion() {
        return carDescripcion;
    }

    public void setCarDescripcion(String carDescripcion) {
        this.carDescripcion = carDescripcion;
    }

    public BigDecimal getCarValor() {
        return carValor;
    }

    public void setCarValor(BigDecimal carValor) {
        this.carValor = carValor;
    }

    public Date getCarFecha() {
        return carFecha;
    }

    public void setCarFecha(Date carFecha) {
        this.carFecha = carFecha;
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
        hash += (idCargaCombustible != null ? idCargaCombustible.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CargaCombustible)) {
            return false;
        }
        CargaCombustible other = (CargaCombustible) object;
        if ((this.idCargaCombustible == null && other.idCargaCombustible != null) || (this.idCargaCombustible != null && !this.idCargaCombustible.equals(other.idCargaCombustible))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.CargaCombustible[ idCargaCombustible=" + idCargaCombustible + " ]";
    }
    
}
