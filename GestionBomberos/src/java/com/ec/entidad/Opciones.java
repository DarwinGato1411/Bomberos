/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "opciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opciones.findAll", query = "SELECT o FROM Opciones o")})
public class Opciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_opcion")
    private Integer idOpcion;
    @Column(name = "opc_descripcion")
    private String opcDescripcion;
    @Column(name = "opc_sigla")
    private String opcSigla;
    @Column(name = "opc_estado")
    private Boolean opcEstado;
    @Column(name = "opc_padre")
    private Integer opcPadre;
    @Column(name = "opc_observacion")
    private String opcObservacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opciones")
    private Collection<PerfilOpcion> perfilOpcionCollection;

    public Opciones() {
    }

    public Opciones(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }

    public Integer getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }

    public String getOpcDescripcion() {
        return opcDescripcion;
    }

    public void setOpcDescripcion(String opcDescripcion) {
        this.opcDescripcion = opcDescripcion;
    }

    public String getOpcSigla() {
        return opcSigla;
    }

    public void setOpcSigla(String opcSigla) {
        this.opcSigla = opcSigla;
    }

    public Boolean getOpcEstado() {
        return opcEstado;
    }

    public void setOpcEstado(Boolean opcEstado) {
        this.opcEstado = opcEstado;
    }

    public Integer getOpcPadre() {
        return opcPadre;
    }

    public void setOpcPadre(Integer opcPadre) {
        this.opcPadre = opcPadre;
    }

    public String getOpcObservacion() {
        return opcObservacion;
    }

    public void setOpcObservacion(String opcObservacion) {
        this.opcObservacion = opcObservacion;
    }

    @XmlTransient
    public Collection<PerfilOpcion> getPerfilOpcionCollection() {
        return perfilOpcionCollection;
    }

    public void setPerfilOpcionCollection(Collection<PerfilOpcion> perfilOpcionCollection) {
        this.perfilOpcionCollection = perfilOpcionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOpcion != null ? idOpcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opciones)) {
            return false;
        }
        Opciones other = (Opciones) object;
        if ((this.idOpcion == null && other.idOpcion != null) || (this.idOpcion != null && !this.idOpcion.equals(other.idOpcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Opciones[ idOpcion=" + idOpcion + " ]";
    }
    
}
