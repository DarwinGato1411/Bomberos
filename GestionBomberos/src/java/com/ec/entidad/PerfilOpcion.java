/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "perfil_opcion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerfilOpcion.findAll", query = "SELECT p FROM PerfilOpcion p")})
public class PerfilOpcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PerfilOpcionPK perfilOpcionPK;
    @Column(name = "perop_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date peropFecha;
    @Column(name = "perop_observacion")
    private String peropObservacion;
    @JoinColumn(name = "id_opcion", referencedColumnName = "id_opcion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Opciones opciones;
    @JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Perfil perfil;

    public PerfilOpcion() {
    }

    public PerfilOpcion(PerfilOpcionPK perfilOpcionPK) {
        this.perfilOpcionPK = perfilOpcionPK;
    }

    public PerfilOpcion(int idOpcion, int idPerfil) {
        this.perfilOpcionPK = new PerfilOpcionPK(idOpcion, idPerfil);
    }

    public PerfilOpcionPK getPerfilOpcionPK() {
        return perfilOpcionPK;
    }

    public void setPerfilOpcionPK(PerfilOpcionPK perfilOpcionPK) {
        this.perfilOpcionPK = perfilOpcionPK;
    }

    public Date getPeropFecha() {
        return peropFecha;
    }

    public void setPeropFecha(Date peropFecha) {
        this.peropFecha = peropFecha;
    }

    public String getPeropObservacion() {
        return peropObservacion;
    }

    public void setPeropObservacion(String peropObservacion) {
        this.peropObservacion = peropObservacion;
    }

    public Opciones getOpciones() {
        return opciones;
    }

    public void setOpciones(Opciones opciones) {
        this.opciones = opciones;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perfilOpcionPK != null ? perfilOpcionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerfilOpcion)) {
            return false;
        }
        PerfilOpcion other = (PerfilOpcion) object;
        if ((this.perfilOpcionPK == null && other.perfilOpcionPK != null) || (this.perfilOpcionPK != null && !this.perfilOpcionPK.equals(other.perfilOpcionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.PerfilOpcion[ perfilOpcionPK=" + perfilOpcionPK + " ]";
    }
    
}
