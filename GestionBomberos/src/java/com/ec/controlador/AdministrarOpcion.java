/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Opciones;
import com.ec.entidad.Perfil;
import com.ec.servicio.ServicioOpcion;
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
public class AdministrarOpcion {

    ServicioOpcion servicioOpcion = new ServicioOpcion(); 
    private List<Opciones> listaOpciones = new ArrayList<Opciones>();

    public AdministrarOpcion() {
        
        cosultarOpciones("");
    }

   
    private void cosultarOpciones(String buscar) {

        listaOpciones = servicioOpcion.findLikeOpcDecripcion("");
    }

   

    /*Perfil*/
    @Command
    @NotifyChange("listaOpciones")
    public void agregarPerfil() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/opcion.zul", null, null);
        window.doModal();
        cosultarOpciones("");
    }

    @Command
    @NotifyChange("listaOpciones")
    public void modificarPerfil(@BindingParam("valor") Opciones valor) {
        final HashMap<String, Opciones> map = new HashMap<String, Opciones>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/opcion.zul", null, map);
        window.doModal();
        cosultarOpciones("");
    }

    public List<Opciones> getListaOpciones() {
        return listaOpciones;
    }

    public void setListaOpciones(List<Opciones> listaOpciones) {
        this.listaOpciones = listaOpciones;
    }

    

}
