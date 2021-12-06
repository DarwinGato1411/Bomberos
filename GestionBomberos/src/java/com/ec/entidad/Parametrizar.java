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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "parametrizar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametrizar.findAll", query = "SELECT p FROM Parametrizar p")
    , @NamedQuery(name = "Parametrizar.findByCodParametrizar", query = "SELECT p FROM Parametrizar p WHERE p.codParametrizar = :codParametrizar")
    , @NamedQuery(name = "Parametrizar.findByParContactoEmpresa", query = "SELECT p FROM Parametrizar p WHERE p.parContactoEmpresa = :parContactoEmpresa")
    , @NamedQuery(name = "Parametrizar.findByParEmpresa", query = "SELECT p FROM Parametrizar p WHERE p.parEmpresa = :parEmpresa")
    , @NamedQuery(name = "Parametrizar.findByParRucEmpresa", query = "SELECT p FROM Parametrizar p WHERE p.parRucEmpresa = :parRucEmpresa")
    , @NamedQuery(name = "Parametrizar.findByParCodigoIva", query = "SELECT p FROM Parametrizar p WHERE p.parCodigoIva = :parCodigoIva")
    , @NamedQuery(name = "Parametrizar.findByParIvaActual", query = "SELECT p FROM Parametrizar p WHERE p.parIvaActual = :parIvaActual")
    , @NamedQuery(name = "Parametrizar.findByParCaduca", query = "SELECT p FROM Parametrizar p WHERE p.parCaduca = :parCaduca")
    , @NamedQuery(name = "Parametrizar.findByParHost", query = "SELECT p FROM Parametrizar p WHERE p.parHost = :parHost")
    , @NamedQuery(name = "Parametrizar.findByParPort", query = "SELECT p FROM Parametrizar p WHERE p.parPort = :parPort")
    , @NamedQuery(name = "Parametrizar.findByParProtocol", query = "SELECT p FROM Parametrizar p WHERE p.parProtocol = :parProtocol")
    , @NamedQuery(name = "Parametrizar.findByParUsuarioSmpt", query = "SELECT p FROM Parametrizar p WHERE p.parUsuarioSmpt = :parUsuarioSmpt")
    , @NamedQuery(name = "Parametrizar.findByParPassword", query = "SELECT p FROM Parametrizar p WHERE p.parPassword = :parPassword")
    , @NamedQuery(name = "Parametrizar.findByParImpAutomatico", query = "SELECT p FROM Parametrizar p WHERE p.parImpAutomatico = :parImpAutomatico")
    , @NamedQuery(name = "Parametrizar.findByParNombreImpresora", query = "SELECT p FROM Parametrizar p WHERE p.parNombreImpresora = :parNombreImpresora")
    , @NamedQuery(name = "Parametrizar.findByParCiudad", query = "SELECT p FROM Parametrizar p WHERE p.parCiudad = :parCiudad")
    , @NamedQuery(name = "Parametrizar.findByParCorreoDefecto", query = "SELECT p FROM Parametrizar p WHERE p.parCorreoDefecto = :parCorreoDefecto")})
public class Parametrizar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_parametrizar")
    private Integer codParametrizar;
    @Column(name = "par_contacto_empresa")
    private String parContactoEmpresa;
    @Column(name = "par_empresa")
    private String parEmpresa;
    @Column(name = "par_ruc_empresa")
    private String parRucEmpresa;
    @Column(name = "par_codigo_iva")
    private String parCodigoIva;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "par_iva_actual")
    private BigDecimal parIvaActual;
    @Column(name = "par_caduca")
    @Temporal(TemporalType.DATE)
    private Date parCaduca;
    @Column(name = "par_host")
    private String parHost;
    @Column(name = "par_port")
    private String parPort;
    @Column(name = "par_protocol")
    private String parProtocol;
    @Column(name = "par_usuario_smpt")
    private String parUsuarioSmpt;
    @Column(name = "par_password")
    private String parPassword;
    @Column(name = "par_imp_automatico")
    private Boolean parImpAutomatico;
    @Column(name = "par_nombre_impresora")
    private String parNombreImpresora;
    @Column(name = "par_ciudad")
    private String parCiudad;
    @Column(name = "par_correo_defecto")
    private String parCorreoDefecto;
    @Column(name = "isprincipal")
    private Boolean isprincipal;
    @Column(name = "par_disco")
    private String parDisco;
    @Column(name = "par_carpeta")
    private String parCarpeta;
    @Column(name = "par_folder_adicional")
    private String parFolderAdicional;
    @Column(name = "par_path_logo")
    private String parPathLogo;
    @Column(name = "par_firma_uno")
    private String parFirmaUno;
    @Column(name = "par_respon_firma_uno")
    private String parResponFirmaUno;
    @Column(name = "par_firma_dos")
    private String parFirmaDos;
    @Column(name = "par_respon_firma_dos")
    private String parResponFirmaDos;
    
   @OneToMany(mappedBy = "idParametrizar")
    private Collection<SolicitudPermiso> solicitudPermisoCollection;
    public Parametrizar() {
    }

    public Parametrizar(Integer codParametrizar) {
        this.codParametrizar = codParametrizar;
    }

    public Integer getCodParametrizar() {
        return codParametrizar;
    }

    public void setCodParametrizar(Integer codParametrizar) {
        this.codParametrizar = codParametrizar;
    }

    public String getParContactoEmpresa() {
        return parContactoEmpresa;
    }

    public void setParContactoEmpresa(String parContactoEmpresa) {
        this.parContactoEmpresa = parContactoEmpresa;
    }

    public String getParEmpresa() {
        return parEmpresa;
    }

    public void setParEmpresa(String parEmpresa) {
        this.parEmpresa = parEmpresa;
    }

    public String getParRucEmpresa() {
        return parRucEmpresa;
    }

    public void setParRucEmpresa(String parRucEmpresa) {
        this.parRucEmpresa = parRucEmpresa;
    }

    public String getParCodigoIva() {
        return parCodigoIva;
    }

    public void setParCodigoIva(String parCodigoIva) {
        this.parCodigoIva = parCodigoIva;
    }

    public BigDecimal getParIvaActual() {
        return parIvaActual;
    }

    public void setParIvaActual(BigDecimal parIvaActual) {
        this.parIvaActual = parIvaActual;
    }

    public Date getParCaduca() {
        return parCaduca;
    }

    public void setParCaduca(Date parCaduca) {
        this.parCaduca = parCaduca;
    }

    public String getParHost() {
        return parHost;
    }

    public void setParHost(String parHost) {
        this.parHost = parHost;
    }

    public String getParPort() {
        return parPort;
    }

    public void setParPort(String parPort) {
        this.parPort = parPort;
    }

    public String getParProtocol() {
        return parProtocol;
    }

    public void setParProtocol(String parProtocol) {
        this.parProtocol = parProtocol;
    }

    public String getParUsuarioSmpt() {
        return parUsuarioSmpt;
    }

    public void setParUsuarioSmpt(String parUsuarioSmpt) {
        this.parUsuarioSmpt = parUsuarioSmpt;
    }

    public String getParPassword() {
        return parPassword;
    }

    public void setParPassword(String parPassword) {
        this.parPassword = parPassword;
    }

    public Boolean getParImpAutomatico() {
        return parImpAutomatico == null ? Boolean.FALSE : Boolean.TRUE;
    }

    public void setParImpAutomatico(Boolean parImpAutomatico) {
        this.parImpAutomatico = parImpAutomatico;
    }

    public String getParNombreImpresora() {
        return parNombreImpresora;
    }

    public void setParNombreImpresora(String parNombreImpresora) {
        this.parNombreImpresora = parNombreImpresora;
    }

    public String getParCiudad() {
        return parCiudad;
    }

    public void setParCiudad(String parCiudad) {
        this.parCiudad = parCiudad;
    }

    public String getParCorreoDefecto() {
        return parCorreoDefecto;
    }

    public void setParCorreoDefecto(String parCorreoDefecto) {
        this.parCorreoDefecto = parCorreoDefecto;
    }

    public Boolean getIsprincipal() {
        return isprincipal;
    }

    public void setIsprincipal(Boolean isprincipal) {
        this.isprincipal = isprincipal;
    }

    public String getParDisco() {
        return parDisco;
    }

    public void setParDisco(String parDisco) {
        this.parDisco = parDisco;
    }

    public String getParCarpeta() {
        return parCarpeta;
    }

    public void setParCarpeta(String parCarpeta) {
        this.parCarpeta = parCarpeta;
    }

    public String getParFolderAdicional() {
        return parFolderAdicional;
    }

    public void setParFolderAdicional(String parFolderAdicional) {
        this.parFolderAdicional = parFolderAdicional;
    }

    public String getParPathLogo() {
        return parPathLogo;
    }

    public void setParPathLogo(String parPathLogo) {
        this.parPathLogo = parPathLogo;
    }   

    public String getParFirmaUno() {
        return parFirmaUno;
    }

    public void setParFirmaUno(String parFirmaUno) {
        this.parFirmaUno = parFirmaUno;
    }

    public String getParResponFirmaUno() {
        return parResponFirmaUno;
    }

    public void setParResponFirmaUno(String parResponFirmaUno) {
        this.parResponFirmaUno = parResponFirmaUno;
    }

    public String getParFirmaDos() {
        return parFirmaDos;
    }

    public void setParFirmaDos(String parFirmaDos) {
        this.parFirmaDos = parFirmaDos;
    }

    public String getParResponFirmaDos() {
        return parResponFirmaDos;
    }

    public void setParResponFirmaDos(String parResponFirmaDos) {
        this.parResponFirmaDos = parResponFirmaDos;
    }
    
    public Collection<SolicitudPermiso> getSolicitudPermisoCollection() {
        return solicitudPermisoCollection;
    }

    public void setSolicitudPermisoCollection(Collection<SolicitudPermiso> solicitudPermisoCollection) {
        this.solicitudPermisoCollection = solicitudPermisoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codParametrizar != null ? codParametrizar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametrizar)) {
            return false;
        }
        Parametrizar other = (Parametrizar) object;
        if ((this.codParametrizar == null && other.codParametrizar != null) || (this.codParametrizar != null && !this.codParametrizar.equals(other.codParametrizar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Parametrizar[ codParametrizar=" + codParametrizar + " ]";
    }

    public void setIdUsuario(Usuario usuarioSistema) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
