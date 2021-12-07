/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import static com.ec.controlador.CargarArchivoPermiso.FOLDER_IMG;
import com.ec.entidad.Bombero;
import com.ec.entidad.Parametrizar;
import com.ec.entidad.Parroquia;
import com.ec.entidad.Perfil;
import com.ec.entidad.Recinto;
import com.ec.entidad.SolicitudPermiso;
import com.ec.entidad.Tarifa;
import com.ec.entidad.TipoTarifa;
import com.ec.entidad.Usuario;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioBombero;
import com.ec.servicio.ServicioParametrizar;
import com.ec.servicio.ServicioParroquia;
import com.ec.servicio.ServicioPerfil;
import com.ec.servicio.ServicioRecinto;
import com.ec.servicio.ServicioSolicitudPermiso;
import com.ec.servicio.ServicioTarifa;
import com.ec.servicio.ServicioTipoTarifa;
import com.ec.servicio.ServicioUsuario;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.io.Files;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Image;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

/**
 *
 * @author gato
 */
public class AdministrarConsultas {

//    @Wire
////    Window wParametrizar;
    
    ServicioSolicitudPermiso servicioSolicitudPermiso = new ServicioSolicitudPermiso();
    private List<SolicitudPermiso> lstSolicitudPermiso = new ArrayList<SolicitudPermiso>();
    private String buscarSolicitud = "";
    private Date fechainicio = new Date();
    private Date fechafin = new Date();

    public AdministrarConsultas() {
        consultarSolicitudes();
        
    }
     @Command
    @NotifyChange({"lstsolicitudPermiso","buscarSolicitud"})
    public void buscarLikeSolicitudes() {

        consultarSolicitudes();

    }
    @Command
    @NotifyChange({"lstsolicitudPermiso", "fechafin", "fechainicio"})
    public void buscarFechas() {
        consultarFacturaFecha();
    }
    
     private void consultarSolicitudes() {
         lstSolicitudPermiso = (List<SolicitudPermiso>) servicioSolicitudPermiso.FindLikeBuscarSolicitud(buscarSolicitud);
    }
     
     private void consultarFacturaFecha() {
        lstSolicitudPermiso = servicioSolicitudPermiso.findSolFecha(fechainicio, fechafin);
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
