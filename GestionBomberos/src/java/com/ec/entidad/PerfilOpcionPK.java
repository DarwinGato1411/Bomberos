/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Darwin
 */
@Embeddable
public class PerfilOpcionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_opcion")
    private int idOpcion;
    @Basic(optional = false)
    @Column(name = "id_perfil")
    private int idPerfil;

    public PerfilOpcionPK() {
    }

    public PerfilOpcionPK(int idOpcion, int idPerfil) {
        this.idOpcion = idOpcion;
        this.idPerfil = idPerfil;
    }

    public int getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(int idOpcion) {
        this.idOpcion = idOpcion;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idOpcion;
        hash += (int) idPerfil;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerfilOpcionPK)) {
            return false;
        }
        PerfilOpcionPK other = (PerfilOpcionPK) object;
        if (this.idOpcion != other.idOpcion) {
            return false;
        }
        if (this.idPerfil != other.idPerfil) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.PerfilOpcionPK[ idOpcion=" + idOpcion + ", idPerfil=" + idPerfil + " ]";
    }
    
}
