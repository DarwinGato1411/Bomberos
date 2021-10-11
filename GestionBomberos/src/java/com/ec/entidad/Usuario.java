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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "usu_nombre")
    private String usuNombre;
    @Column(name = "usu_login")
    private String usuLogin;
    @Column(name = "usu_password")
    private String usuPassword;
    @Column(name = "usu_correo")
    private String usuCorreo;
    @Column(name = "usu_nivel")
    private Integer usuNivel;
    @Column(name = "usu_tipo_usuario")
    private String usuTipoUsuario;
    @Column(name = "usu_contacto")
    private String usuContacto;
    @Column(name = "usu_email")
    private String usuEmail;
    @Column(name = "usu_caduca")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuCaduca;
    @Column(name = "usu_foto")
    private String usuFoto;
    @Column(name = "usu_documento")
    private String usuDocumento;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<Inspeccion> inspeccionCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<SalidaVehiculo> salidaVehiculoCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<Vehiculo> vehiculoCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<SolicitudPermiso> solicitudPermisoCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<CargaCombustible> cargaCombustibleCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<CambioAceite> cambioAceiteCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<Permiso> permisoCollection;
    @JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil")
    @ManyToOne
    private Perfil idPerfil;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<Emergencia> emergenciaCollection;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuNombre() {
        return usuNombre;
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }

    public String getUsuLogin() {
        return usuLogin;
    }

    public void setUsuLogin(String usuLogin) {
        this.usuLogin = usuLogin;
    }

    public String getUsuPassword() {
        return usuPassword;
    }

    public void setUsuPassword(String usuPassword) {
        this.usuPassword = usuPassword;
    }

    public String getUsuCorreo() {
        return usuCorreo;
    }

    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo = usuCorreo;
    }

    public Integer getUsuNivel() {
        return usuNivel;
    }

    public void setUsuNivel(Integer usuNivel) {
        this.usuNivel = usuNivel;
    }

    public String getUsuTipoUsuario() {
        return usuTipoUsuario;
    }

    public void setUsuTipoUsuario(String usuTipoUsuario) {
        this.usuTipoUsuario = usuTipoUsuario;
    }

    public String getUsuContacto() {
        return usuContacto;
    }

    public void setUsuContacto(String usuContacto) {
        this.usuContacto = usuContacto;
    }

    public String getUsuEmail() {
        return usuEmail;
    }

    public void setUsuEmail(String usuEmail) {
        this.usuEmail = usuEmail;
    }

    public Date getUsuCaduca() {
        return usuCaduca;
    }

    public void setUsuCaduca(Date usuCaduca) {
        this.usuCaduca = usuCaduca;
    }

    public String getUsuFoto() {
        return usuFoto;
    }

    public void setUsuFoto(String usuFoto) {
        this.usuFoto = usuFoto;
    }

    public String getUsuDocumento() {
        return usuDocumento;
    }

    public void setUsuDocumento(String usuDocumento) {
        this.usuDocumento = usuDocumento;
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

    @XmlTransient
    public Collection<Vehiculo> getVehiculoCollection() {
        return vehiculoCollection;
    }

    public void setVehiculoCollection(Collection<Vehiculo> vehiculoCollection) {
        this.vehiculoCollection = vehiculoCollection;
    }

    @XmlTransient
    public Collection<SolicitudPermiso> getSolicitudPermisoCollection() {
        return solicitudPermisoCollection;
    }

    public void setSolicitudPermisoCollection(Collection<SolicitudPermiso> solicitudPermisoCollection) {
        this.solicitudPermisoCollection = solicitudPermisoCollection;
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

    @XmlTransient
    public Collection<Permiso> getPermisoCollection() {
        return permisoCollection;
    }

    public void setPermisoCollection(Collection<Permiso> permisoCollection) {
        this.permisoCollection = permisoCollection;
    }

    public Perfil getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Perfil idPerfil) {
        this.idPerfil = idPerfil;
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
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Usuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
