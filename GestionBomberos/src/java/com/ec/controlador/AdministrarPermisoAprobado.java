/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.EstadoDocumento;
import com.ec.entidad.Inspeccion;
import com.ec.entidad.Opciones;
import com.ec.entidad.Permiso;
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
public class AdministrarPermisoAprobado {

   

    /*PERMISOS INGRESADOS*/
     ServicioSolicitudPermiso servicioPermiso = new ServicioSolicitudPermiso();
     ServicioEstadoDocumento servicioEstadoDocumento = new ServicioEstadoDocumento();
    private List<Inspeccion> listaInspeccion = new ArrayList<Inspeccion>();
    
    ServicioInspeccion servicioInspeccion=new ServicioInspeccion();
    private String buscarApr = "APR";
    private String buscar = "";

    public AdministrarPermisoAprobado() {

        consultarPermisosApr();
    }

   
    private void consultarPermisosApr() {
        listaInspeccion = servicioInspeccion.findLikePermisoForEstadoCedulaNombre(buscarApr , buscar);
    }
    /*Perfil*/
    @Command
    @NotifyChange("listaInspeccion")
    public void agregarPermiso() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/permiso.zul", null, null);
        window.doModal();
        consultarPermisosApr();
    }

    @Command
    @NotifyChange("listaInspeccion")
    public void modificarPermiso(@BindingParam("valor") SolicitudPermiso valor) {
        final HashMap<String, SolicitudPermiso> map = new HashMap<String, SolicitudPermiso>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/permiso.zul", null, map);
        window.doModal();
     consultarPermisosApr();
    }
    @Command
    @NotifyChange("listaInspeccion")
    public void cambiarEstado(@BindingParam("valor") SolicitudPermiso valor) {
        if (Messagebox.show("Enviar a entrega de permisos", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.OK) {
            EstadoDocumento estadoDocumento = servicioEstadoDocumento.findBySigla("PORENTR");
            valor.setIdEstadoDocumento(estadoDocumento);
            servicioPermiso.modificar(valor);
            consultarPermisosApr();
        }
    }
      @Command
    @NotifyChange("listaInspeccion")
    public void observacionper(@BindingParam("valor") Inspeccion valor) {
        final HashMap<String, Inspeccion> map = new HashMap<String, Inspeccion>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/observacionpermiso.zul", null, map);
        window.doModal();
        consultarPermisosApr();
    }
    @Command
    @NotifyChange("listaInspeccion")
    public void cargarArchivos(@BindingParam("valor") Inspeccion valor) {
        final HashMap<String, SolicitudPermiso> map = new HashMap<String, SolicitudPermiso>();
        map.put("valor", valor.getIdSolcitudPer());
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
        "/nuevo/cargarArchivos.zul", null, map);
        window.doModal();
        consultarPermisosApr();
    }
    @Command
    @NotifyChange("listaInspeccion")
    public void anularSolicitud(@BindingParam("valor") Inspeccion valor) {
        if (Messagebox.show("Desea anular la solicitud", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.OK) {
            EstadoDocumento estadoDocumento = servicioEstadoDocumento.findBySigla("ANU");
            SolicitudPermiso solicitud=valor.getIdSolcitudPer();
            solicitud.setIdEstadoDocumento(estadoDocumento);
            servicioPermiso.modificar(solicitud);
            consultarPermisosApr();
        }
    }

    public List<Inspeccion> getListaInspeccion() {
        return listaInspeccion;
    }

    public void setListaInspeccion(List<Inspeccion> listaInspeccion) {
        this.listaInspeccion = listaInspeccion;
    }

    public String getBuscarApr() {
        return buscarApr;
    }

    public void setBuscarApr(String buscarApr) {
        this.buscarApr = buscarApr;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }
   

}
