/*
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Best
 */
@Entity
@Table(name = "tipo_solicitud")
public class Tarifa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_solicitud")
    private Integer idTipoSolicitud;
    @Column(name = "tips_drescripcion")
    private String tipsDescripcion;
    @Column(name = "tips_sigla")
    private String tipsSigla;
    @OneToMany(mappedBy = "idTipoSolicitud")
    private Collection<SolicitudPermiso> solicitudPermisoCollection;

    public Tarifa() {
    }

    public Integer getIdTipoSolicitud() {
        return idTipoSolicitud;
    }

    public void setIdTipoSolicitud(Integer idTipoSolicitud) {
        this.idTipoSolicitud = idTipoSolicitud;
    }

    public String getTipsDescripcion() {
        return tipsDescripcion;
    }

    public void setTipsDescripcion(String tipsDescripcion) {
        this.tipsDescripcion = tipsDescripcion;
    }

    public String getTipsSigla() {
        return tipsSigla;
    }

    public void setTipsSigla(String tipsSigla) {
        this.tipsSigla = tipsSigla;
    }

    public Collection<SolicitudPermiso> getSolicitudPermisoCollection() {
        return solicitudPermisoCollection;
    }

    public void setSolicitudPermisoCollection(Collection<SolicitudPermiso> solicitudPermisoCollection) {
        this.solicitudPermisoCollection = solicitudPermisoCollection;
    }

}
