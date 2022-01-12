/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.SolicitudPermiso;
import com.ec.entidad.Tarifa;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioSolicitudPermiso;
import com.ec.servicio.ServicioTarifa;
import java.util.ArrayList;
import java.util.List;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

/**
 *
 * @author gato
 */
public class AsignarTarifa {

    @Wire
    Window wAsingatarifa;

    ServicioSolicitudPermiso servicio = new ServicioSolicitudPermiso();

    private SolicitudPermiso entidadSelected = new SolicitudPermiso();

    UserCredential credential = new UserCredential();

    private List<Tarifa> listTarifa = new ArrayList<Tarifa>();
    private Tarifa tarifaSelected = null;
    ServicioTarifa servicioTipoTarifa = new ServicioTarifa();

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") SolicitudPermiso valor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        entidadSelected = valor;
    }

    public AsignarTarifa() {
        listTarifa = servicioTipoTarifa.findLikeTariDecripcion("");
        Session sess = Sessions.getCurrent();
        UserCredential cre = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        credential = cre;

    }

    @Command
    @NotifyChange("entidadSelected")
    public void guardar() {
        if (tarifaSelected != null) {
            entidadSelected.setIdTarifa(tarifaSelected);
            servicio.modificar(entidadSelected);
            wAsingatarifa.detach();
        } else {
            Clients.showNotification("Verifique la informacion... ",
                    Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);
        }
    }

    public SolicitudPermiso getEntidadSelected() {
        return entidadSelected;
    }

    public void setEntidadSelected(SolicitudPermiso entidadSelected) {
        this.entidadSelected = entidadSelected;
    }

    public UserCredential getCredential() {
        return credential;
    }

    public void setCredential(UserCredential credential) {
        this.credential = credential;
    }

    public List<Tarifa> getListTarifa() {
        return listTarifa;
    }

    public void setListTarifa(List<Tarifa> listTarifa) {
        this.listTarifa = listTarifa;
    }

    public Tarifa getTarifaSelected() {
        return tarifaSelected;
    }

    public void setTarifaSelected(Tarifa tarifaSelected) {
        this.tarifaSelected = tarifaSelected;
    }

}