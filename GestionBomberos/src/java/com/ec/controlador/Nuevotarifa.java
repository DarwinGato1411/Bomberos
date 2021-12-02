/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Tarifa;
import com.ec.entidad.TipoTarifa;
import com.ec.servicio.ServicioTarifa;
import com.ec.servicio.ServicioTipoTarifa;
import java.util.ArrayList;
import java.util.List;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

/**
 *
 * @author gato
 */
public class Nuevotarifa extends SelectorComposer<Component> {

    @Wire
    Window wTarifa;
    ServicioTarifa servicioTarifa = new ServicioTarifa();
    private Tarifa entidadSelected = new Tarifa();
    private String tipoAccion = "new";
    private List<TipoTarifa> listaCatgoria = new ArrayList<TipoTarifa>();
    private TipoTarifa tipoTarifaSelected = new TipoTarifa();
    ServicioTipoTarifa servicioTipoTarifa = new ServicioTipoTarifa();

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") Tarifa valor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        if (valor != null) {
            tipoAccion = "update";
            System.out.println("this.entidadSelected.getIdTipoTarifa() " + this.entidadSelected.getIdTipoTarifa());
            this.tipoTarifaSelected = valor.getIdTipoTarifa() != null ? valor.getIdTipoTarifa() : null;
            setTipoTarifaSelected(valor.getIdTipoTarifa());

            System.out.println("tipoTarifaSelected " + tipoTarifaSelected.getTiptDescripcion());
            this.entidadSelected = valor;

        } else {
            this.entidadSelected = new Tarifa();
            tipoAccion = "new";
        }

    }

    public Nuevotarifa() {
        listaCatgoria = servicioTipoTarifa.findLikeTariDecripcion("");
    }

    @Command
    @NotifyChange("usuarioSistema")
    public void guardar() {
        if (entidadSelected != null && !entidadSelected.getTarDescripcion().equals("")
                && tipoTarifaSelected != null) {
            entidadSelected.setIdTipoTarifa(tipoTarifaSelected);

            if (tipoAccion.equals("new")) {
                servicioTarifa.crear(entidadSelected);
            } else {
                servicioTarifa.modificar(entidadSelected);
            }

        } else {
            Clients.showNotification("Verifique la informacion... ",
                    Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);
        }
        wTarifa.detach();
    }

    public Tarifa getEntidadSelected() {
        return entidadSelected;
    }

    public void setEntidadSelected(Tarifa entidadSelected) {
        this.entidadSelected = entidadSelected;
    }

    public String getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(String tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    public List<TipoTarifa> getListaCatgoria() {
        return listaCatgoria;
    }

    public void setListaCatgoria(List<TipoTarifa> listaCatgoria) {
        this.listaCatgoria = listaCatgoria;
    }

    public TipoTarifa getTipoTarifaSelected() {
        return tipoTarifaSelected;
    }

    public void setTipoTarifaSelected(TipoTarifa tipoTarifaSelected) {
        this.tipoTarifaSelected = tipoTarifaSelected;
    }

}
