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
@Table(name = "tipo_emergencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEmergencia.findAll", query = "SELECT t FROM TipoEmergencia t")})
public class TipoEmergencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_emergencia")
    private Integer idTipoEmergencia;
    @Column(name = "tipe_drescripcion")
    private String tipeDrescripcion;
    @Column(name = "tipe_sigla")
    private String tipeSigla;
    @OneToMany(mappedBy = "idTipoEmergencia")
    private Collection<Emergencia> emergenciaCollection;

    public TipoEmergencia() {
    }

    public TipoEmergencia(Integer idTipoEmergencia) {
        this.idTipoEmergencia = idTipoEmergencia;
    }

    public Integer getIdTipoEmergencia() {
        return idTipoEmergencia;
    }

    public void setIdTipoEmergencia(Integer idTipoEmergencia) {
        this.idTipoEmergencia = idTipoEmergencia;
    }

    public String getTipeDrescripcion() {
        return tipeDrescripcion;
    }

    public void setTipeDrescripcion(String tipeDrescripcion) {
        this.tipeDrescripcion = tipeDrescripcion;
    }

    public String getTipeSigla() {
        return tipeSigla;
    }

    public void setTipeSigla(String tipeSigla) {
        this.tipeSigla = tipeSigla;
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
        hash += (idTipoEmergencia != null ? idTipoEmergencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEmergencia)) {
            return false;
        }
        TipoEmergencia other = (TipoEmergencia) object;
        if ((this.idTipoEmergencia == null && other.idTipoEmergencia != null) || (this.idTipoEmergencia != null && !this.idTipoEmergencia.equals(other.idTipoEmergencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.TipoEmergencia[ idTipoEmergencia=" + idTipoEmergencia + " ]";
    }
    
}
