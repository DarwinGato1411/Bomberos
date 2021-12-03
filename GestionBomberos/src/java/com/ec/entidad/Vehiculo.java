/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehiculo.findAll", query = "SELECT v FROM Vehiculo v")})
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_vehiculo")
    private Integer idVehiculo;
    @Column(name = "veh_placa")
    private String vehPlaca;
    @Column(name = "veh_chasis")
    private String vehChasis;
    @Column(name = "veh_seguro")
    private String vehSeguro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "veh_kilometraje")
    private BigDecimal vehKilometraje;
    @Column(name = "veh_registro_municipal")
    private String vehRegistroMunicipal;
    @Column(name = "veh_anio")
    private Integer vehAnio;
    @Column(name = "veh_color")
    private String vehColor;
    @Column(name = "veh_modelo")
    private String vehModelo;
    @Column(name = "veh_capacidad")
    private String vehCapacidad;
    @Column(name = "veh_marca")
    private String vehMarca;
    @Column(name = "veh_fecha_caduca")
    private String vehFechaCaduca;
    @OneToMany(mappedBy = "idVehiculo")
    private Collection<SalidaVehiculo> salidaVehiculoCollection;
    @OneToMany(mappedBy = "idVehiculo")
    private Collection<Mantenimiento> mantenimientoCollection;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;
    @OneToMany(mappedBy = "idVehiculo")
    private Collection<CargaCombustible> cargaCombustibleCollection;
    @OneToMany(mappedBy = "idVehiculo")
    private Collection<CambioAceite> cambioAceiteCollection;

    public Vehiculo() {
    }

    public Vehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getVehPlaca() {
        return vehPlaca;
    }

    public void setVehPlaca(String vehPlaca) {
        this.vehPlaca = vehPlaca;
    }

    public String getVehChasis() {
        return vehChasis;
    }

    public void setVehChasis(String vehChasis) {
        this.vehChasis = vehChasis;
    }

    public String getVehSeguro() {
        return vehSeguro;
    }

    public void setVehSeguro(String vehSeguro) {
        this.vehSeguro = vehSeguro;
    }

    public BigDecimal getVehKilometraje() {
        return vehKilometraje;
    }

    public void setVehKilometraje(BigDecimal vehKilometraje) {
        this.vehKilometraje = vehKilometraje;
    }

    public String getVehRegistroMunicipal() {
        return vehRegistroMunicipal;
    }

    public void setVehRegistroMunicipal(String vehRegistroMunicipal) {
        this.vehRegistroMunicipal = vehRegistroMunicipal;
    }

    public Integer getVehAnio() {
        return vehAnio;
    }

    public void setVehAnio(Integer vehAnio) {
        this.vehAnio = vehAnio;
    }

    public String getVehColor() {
        return vehColor;
    }

    public void setVehColor(String vehColor) {
        this.vehColor = vehColor;
    }

    public String getVehModelo() {
        return vehModelo;
    }

    public void setVehModelo(String vehModelo) {
        this.vehModelo = vehModelo;
    }

    public String getVehCapacidad() {
        return vehCapacidad;
    }

    public void setVehCapacidad(String vehCapacidad) {
        this.vehCapacidad = vehCapacidad;
    }

    public String getVehMarca() {
        return vehMarca;
    }

    public void setVehMarca(String vehMarca) {
        this.vehMarca = vehMarca;
    }

    public String getVehFechaCaduca() {
        return vehFechaCaduca;
    }

    public void setVehFechaCaduca(String vehFechaCaduca) {
        this.vehFechaCaduca = vehFechaCaduca;
    }
    
    
    @XmlTransient
    public Collection<SalidaVehiculo> getSalidaVehiculoCollection() {
        return salidaVehiculoCollection;
    }

    public void setSalidaVehiculoCollection(Collection<SalidaVehiculo> salidaVehiculoCollection) {
        this.salidaVehiculoCollection = salidaVehiculoCollection;
    }

    @XmlTransient
    public Collection<Mantenimiento> getMantenimientoCollection() {
        return mantenimientoCollection;
    }

    public void setMantenimientoCollection(Collection<Mantenimiento> mantenimientoCollection) {
        this.mantenimientoCollection = mantenimientoCollection;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public Collection<CargaCombustible> getCargaCombustibleCollection() {
        return cargaCombustibleCollection;
    }

    public void setCargaCombustibleCollection(Collection<CargaCombustible> cargaCombustibleCollection) {
        this.cargaCombustibleCollection = cargaCombustibleCollection;
    }

    @XmlTransient
    public Collection<CambioAceite> getCambioAceiteCollection() {
        return cambioAceiteCollection;
    }

    public void setCambioAceiteCollection(Collection<CambioAceite> cambioAceiteCollection) {
        this.cambioAceiteCollection = cambioAceiteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVehiculo != null ? idVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.idVehiculo == null && other.idVehiculo != null) || (this.idVehiculo != null && !this.idVehiculo.equals(other.idVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Vehiculo[ idVehiculo=" + idVehiculo + " ]";
    }
    
}
