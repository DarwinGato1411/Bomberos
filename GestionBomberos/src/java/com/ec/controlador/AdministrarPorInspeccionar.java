/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.EstadoDocumento;
import com.ec.entidad.Inspeccion;
import com.ec.entidad.SolicitudPermiso;
import com.ec.servicio.ServicioEstadoDocumento;
import com.ec.servicio.ServicioInspeccion;
import com.ec.servicio.ServicioSolicitudPermiso;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Messagebox;

/**
 *
 * @author gato
 */
public class AdministrarPorInspeccionar {

    /*PERMISOS INGRESADOS*/
    ServicioSolicitudPermiso servicioPermiso = new ServicioSolicitudPermiso();
    ServicioEstadoDocumento servicioEstadoDocumento = new ServicioEstadoDocumento();
    private Inspeccion entidadInspeccion = new Inspeccion();
    private List<SolicitudPermiso> listaSolicitudPermisos = new ArrayList<SolicitudPermiso>();
    private String buscarPorinspec = "INSPEC";
    private String buscar = "";

    public AdministrarPorInspeccionar() {

        consultarPermisosPorInspec();
    }

    private void consultarPermisosPorInspec() {
        listaSolicitudPermisos = servicioPermiso.findLikePermisoForEstadoCedulaNombre(buscarPorinspec, buscar);
    }

    /*Perfil*/
    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void agregarPermiso() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/permiso.zul", null, null);
        window.doModal();
        consultarPermisosPorInspec();
    }

    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void modificarPermiso(@BindingParam("valor") SolicitudPermiso valor) {
        final HashMap<String, SolicitudPermiso> map = new HashMap<String, SolicitudPermiso>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/permiso.zul", null, map);
        window.doModal();
        consultarPermisosPorInspec();
    }

    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void observacionpre(@BindingParam("valor") SolicitudPermiso valor) {
        final HashMap<String, SolicitudPermiso> map = new HashMap<String, SolicitudPermiso>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/observacionprevencion.zul", null, map);
        window.doModal();
        consultarPermisosPorInspec();
    }
    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void cambiarEstado(@BindingParam("valor") SolicitudPermiso valor) {
            entidadInspeccion.setInsFecha(new Date());
            entidadInspeccion.setInsObservacion("");
//            EstadoDocumento estadoDocumento = servicioEstadoDocumento.findBySigla("APR");
//            valor.setIdEstadoDocumento(estadoDocumento);
//            servicioPermiso.modificar(valor);
//            consultarPermisosPorInspec();
    }
    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void EstadoReinspeccion(@BindingParam("valor") SolicitudPermiso valor) {
        if (Messagebox.show("Enviar a reinspeccion?", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.OK) {
            EstadoDocumento estadoDocumento = servicioEstadoDocumento.findBySigla("REINS");
            valor.setIdEstadoDocumento(estadoDocumento);
            servicioPermiso.modificar(valor);
            consultarPermisosPorInspec();
        }
    }

    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void anularSolicitud(@BindingParam("valor") SolicitudPermiso valor) {
        if (Messagebox.show("Anular documento", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.OK) {
            EstadoDocumento estadoDocumento = servicioEstadoDocumento.findBySigla("ANU");
            valor.setIdEstadoDocumento(estadoDocumento);
            servicioPermiso.modificar(valor);
            consultarPermisosPorInspec();
        }
    }

    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void cargarArchivos(@BindingParam("valor") SolicitudPermiso valor) {
        final HashMap<String, SolicitudPermiso> map = new HashMap<String, SolicitudPermiso>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/cargarArchivos.zul", null, map);
        window.doModal();
        consultarPermisosPorInspec();
    }

    public List<SolicitudPermiso> getListaSolicitudPermisos() {
        return listaSolicitudPermisos;
    }

    public void setListaSolicitudPermisos(List<SolicitudPermiso> listaSolicitudPermisos) {
        this.listaSolicitudPermisos = listaSolicitudPermisos;
    }

    public Inspeccion getEntidadInspeccion() {
        return entidadInspeccion;
    }

    public void setEntidadInspeccion(Inspeccion entidadInspeccion) {
        this.entidadInspeccion = entidadInspeccion;
    }
}
