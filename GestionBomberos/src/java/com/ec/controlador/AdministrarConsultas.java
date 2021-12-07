/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.SolicitudPermiso;
import com.ec.servicio.ServicioSolicitudPermiso;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

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
        consultarSolicitudFecha();
    }
    
     private void consultarSolicitudes() {
         lstSolicitudPermiso = servicioSolicitudPermiso.findLikeBuscarSolicitudes(buscarSolicitud,fechainicio,fechafin );
    }
     
     private void consultarSolicitudFecha() {
        lstSolicitudPermiso =  servicioSolicitudPermiso.findSolFecha(fechainicio, fechafin, buscarSolicitud);
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
}
