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
    private List<SolicitudPermiso> lstsolicitudPermiso = new ArrayList<SolicitudPermiso>();
    private String buscarSolicitud = "";

    public AdministrarConsultas() {
        
    }
     @Command
    @NotifyChange({"buscarSolicitud"})
    public void buscarLikeSolicitudes() {

        consultarSolicitudes();

    }
     private void consultarSolicitudes() {
         lstsolicitudPermiso = (List<SolicitudPermiso>) servicioSolicitudPermiso.FindLikeBuscarSolicitud(buscarSolicitud);
    }

    public String getBuscarSolicitud() {
        return buscarSolicitud;
    }

    public void setBuscarSolicitud(String buscarSolicitud) {
        this.buscarSolicitud = buscarSolicitud;
    }

    public List<SolicitudPermiso> getLstsolicitudPermiso() {
        return lstsolicitudPermiso;
    }

    public void setLstsolicitudPermiso(List<SolicitudPermiso> lstsolicitudPermiso) {
        this.lstsolicitudPermiso = lstsolicitudPermiso;
    }
    
    public ServicioSolicitudPermiso getServicioSolicitudPermiso() {
        return servicioSolicitudPermiso;
    }

    public void setServicioSolicitudPermiso(ServicioSolicitudPermiso servicioSolicitudPermiso) {
        this.servicioSolicitudPermiso = servicioSolicitudPermiso;
    }
    
    
}
