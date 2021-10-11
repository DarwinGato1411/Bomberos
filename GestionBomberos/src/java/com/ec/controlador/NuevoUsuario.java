/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Usuario;
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
public class NuevoUsuario {

    @Wire
    Window wUsuario;
    ServicioUsuario servicioUsuario = new ServicioUsuario();
    private Usuario entidadSelected = new Usuario();
    private String tipoUSuario = "1";
    private String tipoAccion = "new";

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("usuario") Usuario usuarioSistema, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        if (usuarioSistema != null) {
            this.entidadSelected = usuarioSistema;
            tipoUSuario = this.entidadSelected.getUsuNivel().toString();
        } else {
            this.entidadSelected = new Usuario();

        }
    }

    @Command
    @NotifyChange("usuarioSistema")
    public void guardar() {
        if (entidadSelected != null && !entidadSelected.getUsuNombre().equals("")
                && !entidadSelected.getUsuLogin().equals("")
                && !tipoUSuario.equals("")) {
            entidadSelected.setUsuNivel(Integer.valueOf(tipoUSuario));

            if (tipoAccion.equals("new")) {
                servicioUsuario.crear(entidadSelected);
            } else {
                servicioUsuario.modificar(entidadSelected);
            }

        } else {
            Clients.showNotification("Verifique la informacion... ",
                    Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);
        }
    }
}
