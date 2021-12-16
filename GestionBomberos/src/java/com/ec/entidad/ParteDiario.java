/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Darwin Morocho
 */
@Entity
@Table(name = "partediario")
public class ParteDiario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_cobro")
    private Long idCobro;
    @Column(name = "cob_detalle")
    private String cobDetalle;
    @Column(name = "cob_valor")
    private BigDecimal cobValor;
    @Column(name = "cob_cantidad")
    private BigDecimal cobCantidad;
    @Column(name = "cob_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cobFecha;

    public ParteDiario() {
    }

    public Long getIdCobro() {
        return idCobro;
    }

    public void setIdCobro(Long idCobro) {
        this.idCobro = idCobro;
    }

    public String getCobDetalle() {
        return cobDetalle;
    }

    public void setCobDetalle(String cobDetalle) {
        this.cobDetalle = cobDetalle;
    }

    public BigDecimal getCobValor() {
        return cobValor == null ? BigDecimal.ZERO : cobValor;
    }

    public void setCobValor(BigDecimal cobValor) {
        this.cobValor = cobValor;
    }

    public BigDecimal getCobCantidad() {
        return cobCantidad == null ? BigDecimal.ZERO : cobCantidad;
    }

    public void setCobCantidad(BigDecimal cobCantidad) {
        this.cobCantidad = cobCantidad;
    }

    public Date getCobFecha() {
        return cobFecha;
    }

    public void setCobFecha(Date cobFecha) {
        this.cobFecha = cobFecha;
    }

}
