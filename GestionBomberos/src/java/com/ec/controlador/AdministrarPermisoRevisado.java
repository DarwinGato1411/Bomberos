/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.EstadoDocumento;
import com.ec.entidad.Inspeccion;
import com.ec.entidad.Opciones;
import com.ec.entidad.SolicitudPermiso;
import com.ec.servicio.ServicioEstadoDocumento;
import com.ec.servicio.ServicioInspeccion;
import com.ec.servicio.ServicioSolicitudPermiso;
import java.util.ArrayList;
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
public class AdministrarPermisoRevisado {

    /*PERMISOS INGRESADOS*/
    ServicioSolicitudPermiso servicioPermiso = new ServicioSolicitudPermiso();
    ServicioEstadoDocumento servicioEstadoDocumento = new ServicioEstadoDocumento();
    ServicioInspeccion servicioInspeccion = new ServicioInspeccion();
    private List<SolicitudPermiso> listaSolicitudPermisos = new ArrayList<SolicitudPermiso>();
    private List<Inspeccion> listaInspeccion = new ArrayList<Inspeccion>();
    private String buscarRev = "REV";
    private String buscar = "";

    public AdministrarPermisoRevisado() {

        consultarPermisosRev();
    }

    private void consultarPermisosRev() {
//        listaSolicitudPermisos = servicioPermiso.findLikePermisoForEstadoCedulaNombre(buscarRev, buscar);
        listaInspeccion = servicioInspeccion.findLikeDescripcionRev(buscarRev, buscar);
    }

    /*Perfil*/
    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void agregarPermiso() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/permiso.zul", null, null);
        window.doModal();
        consultarPermisosRev();
    }

    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void modificarPermiso(@BindingParam("valor") SolicitudPermiso valor) {
        final HashMap<String, SolicitudPermiso> map = new HashMap<String, SolicitudPermiso>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/permiso.zul", null, map);
        window.doModal();
        consultarPermisosRev();
    }

    @Command
    @NotifyChange("listaInspeccion")
    public void cambiarEstado(@BindingParam("valor") Inspeccion valor) {
        final HashMap<String, Inspeccion> map = new HashMap<String, Inspeccion>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/observacionpermiso.zul", null, map);
        window.doModal();
        consultarPermisosRev();

    }

    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void anularSolicitud(@BindingParam("valor") SolicitudPermiso valor) {
        if (Messagebox.show("Desea anular la solicitud", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.OK) {
            EstadoDocumento estadoDocumento = servicioEstadoDocumento.findBySigla("ANU");
            valor.setIdEstadoDocumento(estadoDocumento);
            servicioPermiso.modificar(valor);
            consultarPermisosRev();
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
        consultarPermisosRev();
    }

    public List<SolicitudPermiso> getListaSolicitudPermisos() {
        return listaSolicitudPermisos;
    }

    public void setListaSolicitudPermisos(List<SolicitudPermiso> listaSolicitudPermisos) {
        this.listaSolicitudPermisos = listaSolicitudPermisos;
    }

    public List<Inspeccion> getListaInspeccion() {
        return listaInspeccion;
    }

    public void setListaInspeccion(List<Inspeccion> listaInspeccion) {
        this.listaInspeccion = listaInspeccion;
    }

    public String getBuscarRev() {
        return buscarRev;
    }

    public void setBuscarRev(String buscarRev) {
        this.buscarRev = buscarRev;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

}
