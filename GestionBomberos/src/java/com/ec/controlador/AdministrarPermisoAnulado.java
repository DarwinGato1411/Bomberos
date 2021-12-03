/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.EstadoDocumento;
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
public class AdministrarPermisoAnulado {

   

    /*PERMISOS INGRESADOS*/
     ServicioSolicitudPermiso servicioPermiso = new ServicioSolicitudPermiso();
     ServicioEstadoDocumento servicioEstadoDocumento = new ServicioEstadoDocumento();
    private List<SolicitudPermiso> listaSolicitudPermisos = new ArrayList<SolicitudPermiso>();
    private String buscarAnu = "ANU";
    private String buscar = "";

    public AdministrarPermisoAnulado() {

        consultarPermisosAnu();
    }

   
    private void consultarPermisosAnu() {
        listaSolicitudPermisos = servicioPermiso.findLikePermisoForEstadoCedulaNombre(buscarAnu , buscar);
    }
    /*Perfil*/
    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void agregarPermiso() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/permiso.zul", null, null);
        window.doModal();
        consultarPermisosAnu();
    }

    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void modificarPermiso(@BindingParam("valor") SolicitudPermiso valor) {
        final HashMap<String, SolicitudPermiso> map = new HashMap<String, SolicitudPermiso>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/permiso.zul", null, map);
        window.doModal();
     consultarPermisosAnu();
    }

    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void cambiarEstado(@BindingParam("valor") SolicitudPermiso valor) {
        if (Messagebox.show("Desea activar el permiso?", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.OK) {
            EstadoDocumento estadoDocumento = servicioEstadoDocumento.findBySigla("ING");
            valor.setIdEstadoDocumento(estadoDocumento);
            servicioPermiso.modificar(valor);
            consultarPermisosAnu();
        }
    }
    public List<SolicitudPermiso> getListaSolicitudPermisos() {
        return listaSolicitudPermisos;
    }

    public void setListaSolicitudPermisos(List<SolicitudPermiso> listaSolicitudPermisos) {
        this.listaSolicitudPermisos = listaSolicitudPermisos;
    }

}
