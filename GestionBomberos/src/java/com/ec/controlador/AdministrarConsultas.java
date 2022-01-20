/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.SolicitudPermiso;
import com.ec.servicio.ServicioSolicitudPermiso;
import java.util.ArrayList;
import java.util.Date;
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
public class AdministrarConsultas {

//    @Wire
////    Window wParametrizar;
    
    ServicioSolicitudPermiso servicioSolicitudPermiso = new ServicioSolicitudPermiso();
//    private List<SolicitudPermiso> lstSolicitudPermiso = new ArrayList<SolicitudPermiso>();
    private List<SolicitudPermiso> lstSolicitudPermiso = new ArrayList<SolicitudPermiso>();
    private List<SolicitudPermiso> listaSolicitud = new ArrayList();
    private String buscarSolicitud = "";
    private Date fechainicio = new Date();
    private Date fechafin = new Date();

    public AdministrarConsultas() {

            fechainicio.setMonth(0);
            fechainicio.setDate(01);
               consultarSolicitudes();
        
    }
     @Command
    @NotifyChange({"lstSolicitudPermiso","buscarSolicitud"})
    public void buscarLikeSolicitudes() {

        consultarSolicitudes();

    }
    @Command
    @NotifyChange({"lstsolicitudPermiso","buscarSolicitud", "fechafin", "fechainicio"})
    public void buscarFechas() {
        consultarSolicitudes();
    }
    
    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void modificarHistoSolicitud(@BindingParam("valor") SolicitudPermiso valor) {
        final HashMap<String, SolicitudPermiso> map = new HashMap<String, SolicitudPermiso>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/consulta/modificar_historial.zul", null, map);
        window.doModal();
        consultarSolicitudes();
    }
    
    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void cargarArchivos(@BindingParam("valor") SolicitudPermiso valor
    ) {
        final HashMap<String, SolicitudPermiso> map = new HashMap<String, SolicitudPermiso>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/consulta/historial_archivos.zul", null, map);
        window.doModal();
        consultarSolicitudes();
    }
    
     private void consultarSolicitudes() {
         lstSolicitudPermiso = servicioSolicitudPermiso.findLikeBuscarSolicitudes(buscarSolicitud,fechainicio,fechafin );
    }
    
    public String getBuscarSolicitud() {
        return buscarSolicitud;
    }

    public void setBuscarSolicitud(String buscarSolicitud) {
        this.buscarSolicitud = buscarSolicitud;
    }

    public ServicioSolicitudPermiso getServicioSolicitudPermiso() {
        return servicioSolicitudPermiso;
    }

    public void setServicioSolicitudPermiso(ServicioSolicitudPermiso servicioSolicitudPermiso) {
        this.servicioSolicitudPermiso = servicioSolicitudPermiso;
    }

    public List<SolicitudPermiso> getLstSolicitudPermiso() {
        return lstSolicitudPermiso;
    }

    public void setLstSolicitudPermiso(List<SolicitudPermiso> lstSolicitudPermiso) {
        this.lstSolicitudPermiso = lstSolicitudPermiso;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public List<SolicitudPermiso> getListaSolicitud() {
        return listaSolicitud;
    }

    public void setListaSolicitud(List<SolicitudPermiso> listaSolicitud) {
        this.listaSolicitud = listaSolicitud;
    }
    
}
