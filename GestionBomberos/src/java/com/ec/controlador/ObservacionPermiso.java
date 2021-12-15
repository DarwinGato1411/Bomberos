/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.EstadoDocumento;
import com.ec.entidad.Inspeccion;
import com.ec.entidad.Permiso;
import com.ec.entidad.SolicitudPermiso;
import com.ec.servicio.ServicioEstadoDocumento;
import com.ec.servicio.ServicioPermiso;
import com.ec.servicio.ServicioSolicitudPermiso;
import java.util.Date;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

/**
 *
 * @author gato
 */
public class ObservacionPermiso extends SelectorComposer<Component> {

    @Wire
    Window wObservaPreve;
    ServicioPermiso servicioInspeccion = new ServicioPermiso();
    ServicioSolicitudPermiso servicioSolicitudPermiso = new ServicioSolicitudPermiso();
    ServicioEstadoDocumento servicioEstadoDocumento = new ServicioEstadoDocumento();
    private Permiso entidad = new Permiso();
    private SolicitudPermiso solicitudPermiso = new SolicitudPermiso();
    private Inspeccion inspeccion = new Inspeccion();

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") Inspeccion valor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        inspeccion = valor;
        entidad.setPerFecha(new Date());
        solicitudPermiso = valor.getIdSolcitudPer();
    }

    @Command
    public void guardar() {
        entidad.setIdInspeccion(inspeccion);
        servicioInspeccion.crear(entidad);
        EstadoDocumento estadoDocumento = servicioEstadoDocumento.findBySigla("PORENTR");
        solicitudPermiso.setIdEstadoDocumento(estadoDocumento);
        servicioSolicitudPermiso.modificar(solicitudPermiso);
        wObservaPreve.detach();
    }

    public SolicitudPermiso getSolicitudPermiso() {
        return solicitudPermiso;
    }

    public void setSolicitudPermiso(SolicitudPermiso solicitudPermiso) {
        this.solicitudPermiso = solicitudPermiso;
    }

    public Permiso getEntidad() {
        return entidad;
    }

    public void setEntidad(Permiso entidad) {
        this.entidad = entidad;
    }

    public Inspeccion getInspeccion() {
        return inspeccion;
    }

    public void setInspeccion(Inspeccion inspeccion) {
        this.inspeccion = inspeccion;
    }

}
