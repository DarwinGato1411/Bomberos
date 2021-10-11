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
@Table(name = "mantenimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mantenimiento.findAll", query = "SELECT m FROM Mantenimiento m")})
public class Mantenimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mantenimiento")
    private Integer idMantenimiento;
    @Column(name = "man_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date manFecha;
    @Column(name = "man_descripcion")
    private String manDescripcion;
    @Column(name = "man_proximo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date manProximo;
    @Column(name = "man_estado")
    private Boolean manEstado;
    @JoinColumn(name = "id_estado_man", referencedColumnName = "id_estado_man")
    @ManyToOne
    private EstadoMantenimiento idEstadoMan;
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id_vehiculo")
    @ManyToOne
    private Vehiculo idVehiculo;

    public Mantenimiento() {
    }

    public Mantenimiento(Integer idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public Integer getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(Integer idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public Date getManFecha() {
        return manFecha;
    }

    public void setManFecha(Date manFecha) {
        this.manFecha = manFecha;
    }

    public String getManDescripcion() {
        return manDescripcion;
    }

    public void setManDescripcion(String manDescripcion) {
        this.manDescripcion = manDescripcion;
    }

    public Date getManProximo() {
        return manProximo;
    }

    public void setManProximo(Date manProximo) {
        this.manProximo = manProximo;
    }

    public Boolean getManEstado() {
        return manEstado;
    }

    public void setManEstado(Boolean manEstado) {
        this.manEstado = manEstado;
    }

    public EstadoMantenimiento getIdEstadoMan() {
        return idEstadoMan;
    }

    public void setIdEstadoMan(EstadoMantenimiento idEstadoMan) {
        this.idEstadoMan = idEstadoMan;
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
        hash += (idMantenimiento != null ? idMantenimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mantenimiento)) {
            return false;
        }
        Mantenimiento other = (Mantenimiento) object;
        if ((this.idMantenimiento == null && other.idMantenimiento != null) || (this.idMantenimiento != null && !this.idMantenimiento.equals(other.idMantenimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Mantenimiento[ idMantenimiento=" + idMantenimiento + " ]";
    }
    
}
