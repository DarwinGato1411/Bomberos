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
@Table(name = "cambio_aceite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CambioAceite.findAll", query = "SELECT c FROM CambioAceite c")})
public class CambioAceite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cambio_aceite")
    private Integer idCambioAceite;
    @Column(name = "cam_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date camFecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cam_kilometraje")
    private BigDecimal camKilometraje;
    @Column(name = "cam_proximo")
    private BigDecimal camProximo;
    @Column(name = "cam_observacion")
    private String camObservacion;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id_vehiculo")
    @ManyToOne
    private Vehiculo idVehiculo;

    public CambioAceite() {
    }

    public CambioAceite(Integer idCambioAceite) {
        this.idCambioAceite = idCambioAceite;
    }

    public Integer getIdCambioAceite() {
        return idCambioAceite;
    }

    public void setIdCambioAceite(Integer idCambioAceite) {
        this.idCambioAceite = idCambioAceite;
    }

    public Date getCamFecha() {
        return camFecha;
    }

    public void setCamFecha(Date camFecha) {
        this.camFecha = camFecha;
    }

    public BigDecimal getCamKilometraje() {
        return camKilometraje;
    }

    public void setCamKilometraje(BigDecimal camKilometraje) {
        this.camKilometraje = camKilometraje;
    }

    public BigDecimal getCamProximo() {
        return camProximo;
    }

    public void setCamProximo(BigDecimal camProximo) {
        this.camProximo = camProximo;
    }

    public String getCamObservacion() {
        return camObservacion;
    }

    public void setCamObservacion(String camObservacion) {
        this.camObservacion = camObservacion;
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
        hash += (idCambioAceite != null ? idCambioAceite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CambioAceite)) {
            return false;
        }
        CambioAceite other = (CambioAceite) object;
        if ((this.idCambioAceite == null && other.idCambioAceite != null) || (this.idCambioAceite != null && !this.idCambioAceite.equals(other.idCambioAceite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.CambioAceite[ idCambioAceite=" + idCambioAceite + " ]";
    }
    
}
