/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Perfil;
import com.ec.entidad.Usuario;
import com.ec.servicio.ServicioPerfil;
import com.ec.servicio.ServicioUsuario;
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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

/**
 *
 * @author gato
 */
public class NuevoPerfil {

    @Wire
    Window wPerfil;
    ServicioPerfil servicioPerfil = new ServicioPerfil();
    private Perfil entidadSelected = new Perfil();
    private String tipoAccion = "new";

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") Perfil valor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        if (valor != null) {
            tipoAccion = "update";
            entidadSelected = valor;
        } else {
            tipoAccion = "new";
            entidadSelected = new Perfil();
        }
    }

    @Command
    @NotifyChange("entidadSelected")
    public void guardar() {
        if (entidadSelected != null && !entidadSelected.getPerNombre().equals("")
                && !entidadSelected.getPerNombre().equals("")
                && !entidadSelected.getPerSigla().equals("")) {
            if (tipoAccion.equals("new")) {
                servicioPerfil.crear(entidadSelected);
            } else {
                servicioPerfil.modificar(entidadSelected);
            }
            wPerfil.detach();
        } else {
            Clients.showNotification("Verifique la informacion... ",
                    Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);
        }
    }

    public Perfil getEntidadSelected() {
        return entidadSelected;
    }

    public void setEntidadSelected(Perfil entidadSelected) {
        this.entidadSelected = entidadSelected;
    }

    public String getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(String tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

}
