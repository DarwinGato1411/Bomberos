/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "archivo_historico")
public class ArchivoHistorico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_archivo_historico")
    private Integer idArchivoHistorico;
    @Column(name = "arc_descripcion")
    private String arcDescripcion;
    @Column(name = "arc_path_archivo")
    private String arcPathArchivo;
    @Column(name = "carc_estado")
    private boolean carcEstado;

    public ArchivoHistorico() {
    }

    public Integer getIdArchivoHistorico() {
        return idArchivoHistorico;
    }

    public void setIdArchivoHistorico(Integer idArchivoHistorico) {
        this.idArchivoHistorico = idArchivoHistorico;
    }

    public String getArcDescripcion() {
        return arcDescripcion;
    }

    public void setArcDescripcion(String arcDescripcion) {
        this.arcDescripcion = arcDescripcion;
    }

    public String getArcPathArchivo() {
        return arcPathArchivo;
    }

    public void setArcPathArchivo(String arcPathArchivo) {
        this.arcPathArchivo = arcPathArchivo;
    }

    public boolean isCarcEstado() {
        return carcEstado;
    }

    public void setCarcEstado(boolean carcEstado) {
        this.carcEstado = carcEstado;
    }
    

}
