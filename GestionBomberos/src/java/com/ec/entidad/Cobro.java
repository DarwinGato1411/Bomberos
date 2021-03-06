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
    @Column(name = "cob_cantidad")
    private BigDecimal cobCantidad;
    @Column(name = "cob_valor_adicional")
    private BigDecimal cobValorAdicional;
    @Column(name = "cob_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cobFecha;
    @Column(name = "cob_estado")
    private boolean cobEstado;
    

    @JoinColumn(name = "id_permiso", referencedColumnName = "id_permiso")
    @ManyToOne
    private Permiso idPermiso;

    @JoinColumn(name = "id_solcitud_per", referencedColumnName = "id_solcitud_per")
    @ManyToOne
    private SolicitudPermiso idSolicitudPermiso;
    
    @Column(name = "cob_impuesto_predial")
    private BigDecimal cobImpuestoPredial;
    
    @Column(name = "cob_impuesto_predial_cobro")
    private BigDecimal cobImpuestoPredialCobro;
    
     @Column(name = "cob_forma_pago")
    private String cobFormaPago;


    public Cobro() {
    }

    public Integer getIdCobro() {
        return idCobro;
    }

    public void setIdCobro(Integer idCobro) {
        this.idCobro = idCobro;
    }

    public BigDecimal getCobCantidad() {
        return cobCantidad;
    }

    public void setCobCantidad(BigDecimal cobCantidad) {
        this.cobCantidad = cobCantidad;
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

    public SolicitudPermiso getIdSolicitudPermiso() {
        return idSolicitudPermiso;
    }

    public void setIdSolicitudPermiso(SolicitudPermiso idSolicitudPermiso) {
        this.idSolicitudPermiso = idSolicitudPermiso;
    }

    public BigDecimal getCobImpuestoPredial() {
        return cobImpuestoPredial;
    }

    public void setCobImpuestoPredial(BigDecimal cobImpuestoPredial) {
        this.cobImpuestoPredial = cobImpuestoPredial;
    }

    public BigDecimal getCobImpuestoPredialCobro() {
        return cobImpuestoPredialCobro;
    }

    public void setCobImpuestoPredialCobro(BigDecimal cobImpuestoPredialCobro) {
        this.cobImpuestoPredialCobro = cobImpuestoPredialCobro;
    }

    public String getCobFormaPago() {
        return cobFormaPago;
    }

    public void setCobFormaPago(String cobFormaPago) {
        this.cobFormaPago = cobFormaPago;
    }

    public BigDecimal getCobValorAdicional() {
        return cobValorAdicional;
    }

    public void setCobValorAdicional(BigDecimal cobValorAdicional) {
        this.cobValorAdicional = cobValorAdicional;
    }
    
}
