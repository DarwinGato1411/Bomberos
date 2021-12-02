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
@Table(name = "tipo_tarifa")
public class TipoTarifa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_tarifa")
    private Integer idTipoTarifa;
    @Column(name = "tipt_codigo")
    private String tiptCodigo;
    @Column(name = "tipt_descripcion")
    private String tiptDescripcion;
    @Column(name = "tipt_estado")
    private Boolean tiptEstado;
     @OneToMany(mappedBy = "idTipoTarifa")
    private Collection<Tarifa> usuarioTarifa;

    public TipoTarifa() {
    }

    public TipoTarifa(String tiptDescripcion) {
        this.tiptDescripcion = tiptDescripcion;
    }

    public Integer getIdTipoTarifa() {
        return idTipoTarifa;
    }

    public void setIdTipoTarifa(Integer idTipoTarifa) {
        this.idTipoTarifa = idTipoTarifa;
    }

    public String getTiptCodigo() {
        return tiptCodigo;
    }

    public void setTiptCodigo(String tiptCodigo) {
        this.tiptCodigo = tiptCodigo;
    }

    public String getTiptDescripcion() {
        return tiptDescripcion;
    }

    public void setTiptDescripcion(String tiptDescripcion) {
        this.tiptDescripcion = tiptDescripcion;
    }

    public Boolean getTiptEstado() {
        return tiptEstado;
    }

    public void setTiptEstado(Boolean tiptEstado) {
        this.tiptEstado = tiptEstado;
    }

    public Collection<Tarifa> getUsuarioTarifa() {
        return usuarioTarifa;
    }

    public void setUsuarioTarifa(Collection<Tarifa> usuarioTarifa) {
        this.usuarioTarifa = usuarioTarifa;
    }

   
}
