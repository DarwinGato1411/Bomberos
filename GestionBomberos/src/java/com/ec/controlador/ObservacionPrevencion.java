/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.EstadoDocumento;
import com.ec.entidad.Inspeccion;
import com.ec.entidad.SolicitudPermiso;
import com.ec.entidad.Tarifa;
import com.ec.servicio.ServicioEstadoDocumento;
import com.ec.servicio.ServicioInspeccion;
import com.ec.servicio.ServicioSolicitudPermiso;
import com.ec.servicio.ServicioTarifa;
import java.util.Date;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

/**
 *
 * @author gato
 */
public class ObservacionPrevencion extends SelectorComposer<Component> {

    @Wire
    Window wObservaPreve;
    ServicioInspeccion servicioInspeccion = new ServicioInspeccion();
    ServicioSolicitudPermiso servicioSolicitudPermiso = new ServicioSolicitudPermiso();
    ServicioEstadoDocumento servicioEstadoDocumento = new ServicioEstadoDocumento();
    private Inspeccion entidad = new Inspeccion();
    private SolicitudPermiso solicitudPermiso = new SolicitudPermiso();

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") SolicitudPermiso valor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        solicitudPermiso = valor;
        entidad.setInsFecha(new Date());
    }

    @Command
    public void guardar() {
        entidad.setIdSolcitudPer(solicitudPermiso);
        servicioInspeccion.crear(entidad);
        EstadoDocumento estadoDocumento = servicioEstadoDocumento.findBySigla("APR");
        solicitudPermiso.setIdEstadoDocumento(estadoDocumento);
        servicioSolicitudPermiso.modificar(solicitudPermiso);
        wObservaPreve.detach();
    }

    public Inspeccion getEntidad() {
        return entidad;
    }

    public void setEntidad(Inspeccion entidad) {
        this.entidad = entidad;
    }

    public SolicitudPermiso getSolicitudPermiso() {
        return solicitudPermiso;
    }

    public void setSolicitudPermiso(SolicitudPermiso solicitudPermiso) {
        this.solicitudPermiso = solicitudPermiso;
    }

}
