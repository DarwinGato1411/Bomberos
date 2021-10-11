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
@Table(name = "grado_bombero")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GradoBombero.findAll", query = "SELECT g FROM GradoBombero g")})
public class GradoBombero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_grado_bombero")
    private Integer idGradoBombero;
    @Column(name = "gra_descripcion")
    private String graDescripcion;
    @Column(name = "gra_abreviatura")
    private String graAbreviatura;
    @Column(name = "gra_estado")
    private Boolean graEstado;
    @OneToMany(mappedBy = "idGradoBombero")
    private Collection<Bombero> bomberoCollection;

    public GradoBombero() {
    }

    public GradoBombero(Integer idGradoBombero) {
        this.idGradoBombero = idGradoBombero;
    }

    public Integer getIdGradoBombero() {
        return idGradoBombero;
    }

    public void setIdGradoBombero(Integer idGradoBombero) {
        this.idGradoBombero = idGradoBombero;
    }

    public String getGraDescripcion() {
        return graDescripcion;
    }

    public void setGraDescripcion(String graDescripcion) {
        this.graDescripcion = graDescripcion;
    }

    public String getGraAbreviatura() {
        return graAbreviatura;
    }

    public void setGraAbreviatura(String graAbreviatura) {
        this.graAbreviatura = graAbreviatura;
    }

    public Boolean getGraEstado() {
        return graEstado;
    }

    public void setGraEstado(Boolean graEstado) {
        this.graEstado = graEstado;
    }

    @XmlTransient
    public Collection<Bombero> getBomberoCollection() {
        return bomberoCollection;
    }

    public void setBomberoCollection(Collection<Bombero> bomberoCollection) {
        this.bomberoCollection = bomberoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGradoBombero != null ? idGradoBombero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GradoBombero)) {
            return false;
        }
        GradoBombero other = (GradoBombero) object;
        if ((this.idGradoBombero == null && other.idGradoBombero != null) || (this.idGradoBombero != null && !this.idGradoBombero.equals(other.idGradoBombero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.GradoBombero[ idGradoBombero=" + idGradoBombero + " ]";
    }
    
}
