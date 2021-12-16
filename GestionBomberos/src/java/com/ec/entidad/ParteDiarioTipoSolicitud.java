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
@Table(name = "parte_tipo_solicitud")
public class ParteDiarioTipoSolicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_cobro")
    private Long idCobro;
    @Column(name = "tips_drescripcion")
    private String tipsDescripcion;
    @Column(name = "cob_valor")
    private BigDecimal cobValor;
    @Column(name = "cob_cantidad")
    private BigDecimal cobCantidad;
    @Column(name = "cob_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cobFecha;

    public ParteDiarioTipoSolicitud() {
    }

    public Long getIdCobro() {
        return idCobro;
    }

    public void setIdCobro(Long idCobro) {
        this.idCobro = idCobro;
    }

    public String getTipsDescripcion() {
        return tipsDescripcion;
    }

    public void setTipsDescripcion(String tipsDescripcion) {
        this.tipsDescripcion = tipsDescripcion;
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
