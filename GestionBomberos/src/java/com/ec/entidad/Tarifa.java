/*
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Best
 */
@Entity
@Table(name = "tarifa")
public class Tarifa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tarifa")
    private Integer idTarifa;
    @Column(name = "tar_codigo")
    private String tarCodigo;
    @Column(name = "tar_descripcion")
    private String tarDescripcion;
    @Column(name = "tar_valor")
    private BigDecimal tarValor;
    @Column(name = "tar_estado")
    private Boolean tarEstado;
    @OneToMany(mappedBy = "idTarifa")
    private Collection<SolicitudPermiso> solicitudPermisoCollection;
    @JoinColumn(name = "id_tipo_tarifa", referencedColumnName = "id_tipo_tarifa")
    @ManyToOne
    private TipoTarifa idTipoTarifa;

    public Tarifa() {
    }

    public Tarifa(String tarCodigo, String tarDescripcion, BigDecimal tarValor) {
        this.tarCodigo = tarCodigo;
        this.tarDescripcion = tarDescripcion;
        this.tarValor = tarValor;
    }

    public Integer getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(Integer idTarifa) {
        this.idTarifa = idTarifa;
    }

    public String getTarCodigo() {
        return tarCodigo;
    }

    public void setTarCodigo(String tarCodigo) {
        this.tarCodigo = tarCodigo;
    }

    public String getTarDescripcion() {
        return tarDescripcion;
    }

    public void setTarDescripcion(String tarDescripcion) {
        this.tarDescripcion = tarDescripcion;
    }

    public BigDecimal getTarValor() {
        return tarValor;
    }

    public void setTarValor(BigDecimal tarValor) {
        this.tarValor = tarValor;
    }

    public Boolean getTarEstado() {
        return tarEstado;
    }

    public void setTarEstado(Boolean tarEstado) {
        this.tarEstado = tarEstado;

    }

    public Collection<SolicitudPermiso> getSolicitudPermisoCollection() {
        return solicitudPermisoCollection;
    }

    public void setSolicitudPermisoCollection(Collection<SolicitudPermiso> solicitudPermisoCollection) {
        this.solicitudPermisoCollection = solicitudPermisoCollection;
    }

    public TipoTarifa getIdTipoTarifa() {
        return idTipoTarifa;
    }

    public void setIdTipoTarifa(TipoTarifa idTipoTarifa) {
        this.idTipoTarifa = idTipoTarifa;
    }

}
