/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Opciones;
import com.ec.entidad.SolicitudPermiso;
import com.ec.servicio.ServicioPermiso;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;

/**
 *
 * @author gato
 */
public class AdministrarPermiso {

    ServicioPermiso servicioPermiso = new ServicioPermiso();
    private List<SolicitudPermiso> listaSolicitudPermisos = new ArrayList<SolicitudPermiso>();
    private String buscar = "";

    public AdministrarPermiso() {

        consultar();
    }

    private void consultar() {

        listaSolicitudPermisos = servicioPermiso.findLikePermiso(buscar);
    }

    /*Perfil*/
    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void agregarPermiso() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/permiso.zul", null, null);
        window.doModal();
         consultar();
    }

    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void modificarPermiso(@BindingParam("valor") SolicitudPermiso valor) {
        final HashMap<String, SolicitudPermiso> map = new HashMap<String, SolicitudPermiso>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/permiso.zul", null, map);
        window.doModal();
        consultar();
    }

    public List<SolicitudPermiso> getListaSolicitudPermisos() {
        return listaSolicitudPermisos;
    }

    public void setListaSolicitudPermisos(List<SolicitudPermiso> listaSolicitudPermisos) {
        this.listaSolicitudPermisos = listaSolicitudPermisos;
    }


}
