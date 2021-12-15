/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "cobro")
public class Cobro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cobro")
    private Integer idCobro;
    @Column(name = "cob_detalle")
    private String cobDetalle;
    @Column(name = "cob_valor")
    private BigDecimal cobValor;
    @Column(name = "cob_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cobFecha;
    @Column(name = "cob_estado")
    private boolean cobEstado;
    
    @JoinColumn(name = "id_permiso", referencedColumnName = "id_permiso")
    @ManyToOne
    private Permiso idPermiso;

    public Cobro() {
    }

    public Integer getIdCobro() {
        return idCobro;
    }

    public void setIdCobro(Integer idCobro) {
        this.idCobro = idCobro;
    }

    public String getCobDetalle() {
        return cobDetalle;
    }

    public void setCobDetalle(String cobDetalle) {
        this.cobDetalle = cobDetalle;
    }

    public BigDecimal getCobValor() {
        return cobValor;
    }

    public void setCobValor(BigDecimal cobValor) {
        this.cobValor = cobValor;
    }

    public Date getCobFecha() {
        return cobFecha;
    }

    public void setCobFecha(Date cobFecha) {
        this.cobFecha = cobFecha;
    }

    public boolean isCobEstado() {
        return cobEstado;
    }

    public void setCobEstado(boolean cobEstado) {
        this.cobEstado = cobEstado;
    }

    public Permiso getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Permiso idPermiso) {
        this.idPermiso = idPermiso;
    }

}
