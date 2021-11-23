/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.SolicitudPermiso;
import com.ec.servicio.ServicioPermiso;
import java.util.ArrayList;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;

/**
 *
 * @author Darwin
 */
public class MenuAdministrador extends SelectorComposer<Component> {
    
    private List<SolicitudPermiso> listaSolicitudPermisos = new ArrayList<SolicitudPermiso>();
    ServicioPermiso servicioPermiso = new ServicioPermiso();
    private String buscar = "";
    
    private void consultar() {

        listaSolicitudPermisos = servicioPermiso.findLikePermiso(buscar);
    }

    @Command
    public void btnPermisos() {
        Executions.sendRedirect("/configuracion/permiso.zul");

    }

    @Command
    public void Emergencias() {
        Executions.sendRedirect("/configuracion/administrar.zul");

    }

    @Command
    public void Operacion() {
//        Executions.sendRedirect("/configuracion/administrar.zul");
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/menusolicitudes.zul", null, null);
         window.doModal();
         consultar();
    }

    @Command
    public void Configuracion() {
        Executions.sendRedirect("/configuracion/administrar.zul");

    }

}
