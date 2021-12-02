/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "bd_recaudacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BdRecaudacion.findAll", query = "SELECT b FROM BdRecaudacion b")})
public class BdRecaudacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bd_recaudacion")
    private Integer idBdRecaudacion;
    @Column(name = "bd_cedula")
    private String bdCedula;
    @Column(name = "bd_num_recibo")
    private Integer bdNumRecibo;
    @Column(name = "bd_num_permiso")
    private String bdNumPermiso;
    @Column(name = "bd_razon_social")
    private String bdRazonSocial;
    @Column(name = "bd_barrio")
    private String bdBarrio;
    @Column(name = "bd_direccion")
    private String bdDireccion;
    @Column(name = "bd_telefono")
    private Integer bdTelefono;
    @Column(name = "bd_fecha_emision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bdFechaEmision;
    @Column(name = "bd_valor")
    private String bdValor;
    @Column(name = "bd_tipo_permiso")
    private String bdTipoPermiso;
    @Column(name = "bd_categoria")
    private String bdCategoria;
    @Column(name = "bd_observaciones")
    private String bdObservaciones;
    
    public BdRecaudacion() {
    }

    public Integer getIdBdRecaudacion() {
        return idBdRecaudacion;
    }

    public void setIdBdRecaudacion(Integer idBdRecaudacion) {
        this.idBdRecaudacion = idBdRecaudacion;
    }

    public String getBdCedula() {
        return bdCedula;
    }

    public void setBdCedula(String bdCedula) {
        this.bdCedula = bdCedula;
    }

    public Integer getBdNumRecibo() {
        return bdNumRecibo;
    }

    public void setBdNumRecibo(Integer bdNumRecibo) {
        this.bdNumRecibo = bdNumRecibo;
    }

    public String getBdNumPermiso() {
        return bdNumPermiso;
    }

    public void setBdNumPermiso(String bdNumPermiso) {
        this.bdNumPermiso = bdNumPermiso;
    }

    public String getBdRazonSocial() {
        return bdRazonSocial;
    }

    public void setBdRazonSocial(String bdRazonSocial) {
        this.bdRazonSocial = bdRazonSocial;
    }

    public String getBdBarrio() {
        return bdBarrio;
    }

    public void setBdBarrio(String bdBarrio) {
        this.bdBarrio = bdBarrio;
    }

    public String getBdDireccion() {
        return bdDireccion;
    }

    public void setBdDireccion(String bdDireccion) {
        this.bdDireccion = bdDireccion;
    }

    public Integer getBdTelefono() {
        return bdTelefono;
    }

    public void setBdTelefono(Integer bdTelefono) {
        this.bdTelefono = bdTelefono;
    }

    public Date getBdFechaEmision() {
        return bdFechaEmision;
    }

    public void setBdFechaEmision(Date bdFechaEmision) {
        this.bdFechaEmision = bdFechaEmision;
    }

    public String getBdValor() {
        return bdValor;
    }

    public void setBdValor(String bdValor) {
        this.bdValor = bdValor;
    }

    public String getBdTipoPermiso() {
        return bdTipoPermiso;
    }

    public void setBdTipoPermiso(String bdTipoPermiso) {
        this.bdTipoPermiso = bdTipoPermiso;
    }

    public String getBdCategoria() {
        return bdCategoria;
    }

    public void setBdCategoria(String bdCategoria) {
        this.bdCategoria = bdCategoria;
    }

    public String getBdObservaciones() {
        return bdObservaciones;
    }

    public void setBdObservaciones(String bdObservaciones) {
        this.bdObservaciones = bdObservaciones;
    }
}
