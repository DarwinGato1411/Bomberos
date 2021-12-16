/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.DocumentosAdjunto;
import com.ec.entidad.EstadoDocumento;
import com.ec.entidad.Parametrizar;
import com.ec.entidad.SolicitudPermiso;
import com.ec.servicio.ServicioEstadoDocumento;
import com.ec.servicio.ServicioParametrizar;
import com.ec.servicio.ServicioSolicitudPermiso;
import com.ec.utilitario.ArchivoUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import net.sf.jasperreports.engine.JRException;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

/**
 *
 * @author gato
 */
public class AdministrarPermiso {

    /*PERMISOS INGRESADOS*/
    ServicioSolicitudPermiso servicioPermiso = new ServicioSolicitudPermiso();
    ServicioEstadoDocumento servicioEstadoDocumento = new ServicioEstadoDocumento();
    private List<SolicitudPermiso> listaSolicitudPermisos = new ArrayList<SolicitudPermiso>();
    private String buscarIng = "ING";
    private String buscar = "";
    AMedia fileContent = null;

    ServicioParametrizar servicioParametrizar = new ServicioParametrizar();
    private Parametrizar parametrizar = new Parametrizar();

    public AdministrarPermiso() {
        parametrizar = servicioParametrizar.findActivo();
        consultarPermisosIng();
    }

    private void consultarPermisosIng() {
        listaSolicitudPermisos = servicioPermiso.findLikePermisoForEstadoCedulaNombre(buscarIng, buscar);
    }

    /*Perfil*/
    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void agregarPermiso() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/permiso.zul", null, null);
        window.doModal();
        consultarPermisosIng();
    }

    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void modificarPermiso(@BindingParam("valor") SolicitudPermiso valor) {
        final HashMap<String, SolicitudPermiso> map = new HashMap<String, SolicitudPermiso>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/permiso.zul", null, map);
        window.doModal();
        consultarPermisosIng();
    }

    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void cambiarEstado(@BindingParam("valor") SolicitudPermiso valor) {
        if (valor.getSolPathSolicitud() != null) {

            if (Messagebox.show("Enviar a prevencion de incendios?", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.OK) {
                EstadoDocumento estadoDocumento = servicioEstadoDocumento.findBySigla("INSPEC");
                valor.setIdEstadoDocumento(estadoDocumento);
                servicioPermiso.modificar(valor);
                consultarPermisosIng();
            }
        } else {
            Clients.showNotification("Para enviar a prevención de incendios debe adjuntar la solicitud",
                    Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);
        }
    }

    @Command
    public void verPermiso(@BindingParam("valor") SolicitudPermiso valor) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("numeracion", valor.getSolpNumeracion());
        try {
            String nombreReporte = "solicitudInspeccion.jasper";
            ArchivoUtils.reporteGeneral(parametros, parametrizar, nombreReporte);
        } catch (JRException ex) {
            Logger.getLogger(NuevoPermiso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NuevoPermiso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NuevoPermiso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(NuevoPermiso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(NuevoPermiso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NuevoPermiso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(NuevoPermiso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void anularSolicitud(@BindingParam("valor") SolicitudPermiso valor) {
        if (Messagebox.show("Desea anular la solicitud", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.OK) {
            EstadoDocumento estadoDocumento = servicioEstadoDocumento.findBySigla("ANU");
            valor.setIdEstadoDocumento(estadoDocumento);
            servicioPermiso.modificar(valor);
            consultarPermisosIng();
        }
    }

    @Command
    @NotifyChange({"listadoAdjuntos", "fileContent"})
    public void verArchivo(@BindingParam("valor") DocumentosAdjunto valor) {
        try {
            fileContent = new AMedia("Visor", "pdf", "application/pdf", ArchivoUtils.Imagen_A_Bytes(valor.getAdjPath()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CargarArchivoPermiso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<SolicitudPermiso> getListaSolicitudPermisos() {
        return listaSolicitudPermisos;
    }

    public void setListaSolicitudPermisos(List<SolicitudPermiso> listaSolicitudPermisos) {
        this.listaSolicitudPermisos = listaSolicitudPermisos;
    }

    /*cobro de permiso*/
    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void agregarcobromanual() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/cobromanual.zul", null, null);
        window.doModal();
        consultarPermisosIng();
    }

}
