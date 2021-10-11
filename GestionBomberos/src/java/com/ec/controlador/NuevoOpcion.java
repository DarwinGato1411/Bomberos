/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Opciones;
import com.ec.servicio.ServicioOpcion;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

/**
 *
 * @author gato
 */
public class NuevoOpcion {

    @Wire
    Window wOpcion;
    ServicioOpcion servicio = new ServicioOpcion();
    private Opciones entidadSelected = new Opciones();
    private String tipoAccion = "new";

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") Opciones valor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        if (valor != null) {
            tipoAccion = "update";
            entidadSelected = valor;
            
        } else {
            tipoAccion = "new";
            entidadSelected = new Opciones();
        }
    }

    @Command
    @NotifyChange("entidadSelected")
    public void guardar() {
        if (entidadSelected != null && !entidadSelected.getOpcDescripcion().equals("")) {
            if (tipoAccion.equals("new")) {
                servicio.crear(entidadSelected);
            } else {
                servicio.modificar(entidadSelected);
            }
            wOpcion.detach();
        } else {
            Clients.showNotification("Verifique la informacion... ",
                    Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);
        }
    }

    public Opciones getEntidadSelected() {
        return entidadSelected;
    }

    public void setEntidadSelected(Opciones entidadSelected) {
        this.entidadSelected = entidadSelected;
    }

  

    public String getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(String tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

}
