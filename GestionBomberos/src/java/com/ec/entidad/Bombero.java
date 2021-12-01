/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
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
@Table(name = "bombero")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bombero.findAll", query = "SELECT b FROM Bombero b")})
public class Bombero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bombero")
    private Integer idBombero;
    @Column(name = "bom_cedula")
    private String bomCedula;
    @Column(name = "bom_nombre")
    private String bomNombre;
    @Column(name = "bom_apellido")
    private String bomApellido;
    @Column(name = "bom_movil")
    private String bomMovil;
    @Column(name = "bom_direccion")
    private String bomDireccion;
    @Column(name = "bom_correo")
    private String bomCorreo;
    @OneToMany(mappedBy = "idBombero")
    private Collection<Inspeccion> inspeccionCollection;
    @OneToMany(mappedBy = "idBombero")
    private Collection<SalidaVehiculo> salidaVehiculoCollection;
    @JoinColumn(name = "id_grado_bombero", referencedColumnName = "id_grado_bombero")
    @ManyToOne
    private GradoBombero idGradoBombero;
    @OneToMany(mappedBy = "idBombero")
    private Collection<Emergencia> emergenciaCollection;

    public Bombero() {
    }
    
    public Bombero(String bomCedula, String bomNombre, String bomDireccion) {
        this.bomCedula = bomCedula;
        this.bomNombre = bomNombre;
        this.bomDireccion = bomDireccion;
    }

    public Bombero(Integer idBombero) {
        this.idBombero = idBombero;
    }

    public Integer getIdBombero() {
        return idBombero;
    }

    public void setIdBombero(Integer idBombero) {
        this.idBombero = idBombero;
    }

    public String getBomCedula() {
        return bomCedula;
    }

    public void setBomCedula(String bomCedula) {
        this.bomCedula = bomCedula;
    }

    public String getBomNombre() {
        return bomNombre;
    }

    public void setBomNombre(String bomNombre) {
        this.bomNombre = bomNombre;
    }

    public String getBomApellido() {
        return bomApellido;
    }

    public void setBomApellido(String bomApellido) {
        this.bomApellido = bomApellido;
    }

    public String getBomMovil() {
        return bomMovil;
    }

    public void setBomMovil(String bomMovil) {
        this.bomMovil = bomMovil;
    }

    public String getBomDireccion() {
        return bomDireccion;
    }

    public void setBomDireccion(String bomDireccion) {
        this.bomDireccion = bomDireccion;
    }

    public String getBomCorreo() {
        return bomCorreo;
    }

    public void setBomCorreo(String bomCorreo) {
        this.bomCorreo = bomCorreo;
    }

    @XmlTransient
    public Collection<Inspeccion> getInspeccionCollection() {
        return inspeccionCollection;
    }

    public void setInspeccionCollection(Collection<Inspeccion> inspeccionCollection) {
        this.inspeccionCollection = inspeccionCollection;
    }

    @XmlTransient
    public Collection<SalidaVehiculo> getSalidaVehiculoCollection() {
        return salidaVehiculoCollection;
    }

    public void setSalidaVehiculoCollection(Collection<SalidaVehiculo> salidaVehiculoCollection) {
        this.salidaVehiculoCollection = salidaVehiculoCollection;
    }

    public GradoBombero getIdGradoBombero() {
        return idGradoBombero;
    }

    public void setIdGradoBombero(GradoBombero idGradoBombero) {
        this.idGradoBombero = idGradoBombero;
    }

    @XmlTransient
    public Collection<Emergencia> getEmergenciaCollection() {
        return emergenciaCollection;
    }

    public void setEmergenciaCollection(Collection<Emergencia> emergenciaCollection) {
        this.emergenciaCollection = emergenciaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBombero != null ? idBombero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bombero)) {
            return false;
        }
        Bombero other = (Bombero) object;
        if ((this.idBombero == null && other.idBombero != null) || (this.idBombero != null && !this.idBombero.equals(other.idBombero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Bombero[ idBombero=" + idBombero + " ]";
    }
    
}
