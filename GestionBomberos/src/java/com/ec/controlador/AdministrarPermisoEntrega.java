/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.DocumentosAdjunto;
import com.ec.entidad.EstadoDocumento;
import com.ec.entidad.Opciones;
import com.ec.entidad.Parametrizar;
import com.ec.entidad.SolicitudPermiso;
import com.ec.entidad.TipoSolicitud;
import com.ec.servicio.ServicioEstadoDocumento;
import com.ec.servicio.ServicioParametrizar;
import com.ec.servicio.ServicioPermiso;
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
import org.zkoss.zul.Messagebox;

/**
 *
 * @author gato
 */
public class AdministrarPermisoEntrega {

    /*PERMISOS INGRESADOS*/
    ServicioPermiso servicioPermiso = new ServicioPermiso();
    ServicioEstadoDocumento servicioEstadoDocumento = new ServicioEstadoDocumento();
    private List<SolicitudPermiso> listaSolicitudPermisos = new ArrayList<SolicitudPermiso>();
    private String buscarPorentr = "PORENTR";
    private String buscar = "";
    AMedia fileContent = null;

    ServicioParametrizar servicioParametrizar = new ServicioParametrizar();
    private Parametrizar parametrizar = new Parametrizar();

    public AdministrarPermisoEntrega() {
        parametrizar = servicioParametrizar.findActivo();
        consultarPermisosPorentr();
    }

    private void consultarPermisosPorentr() {
        listaSolicitudPermisos = servicioPermiso.findLikePermisoForEstadoCedulaNombre(buscarPorentr, buscar);
    }

    /*Perfil*/
//    @Command
//    @NotifyChange("listaSolicitudPermisos")
//    public void agregarPermiso() {
//        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
//                "/nuevo/permiso.zul", null, null);
//        window.doModal();
//        consultarPermisosIng();
//    }
//    @Command
//    @NotifyChange("listaSolicitudPermisos")
//    public void modificarPermiso(@BindingParam("valor") SolicitudPermiso valor) {
//        final HashMap<String, SolicitudPermiso> map = new HashMap<String, SolicitudPermiso>();
//        map.put("valor", valor);
//        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
//                "/nuevo/permiso.zul", null, map);
//        window.doModal();
//     consultarPermisosIng();
//    }
    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void cambiarEstado(@BindingParam("valor") SolicitudPermiso valor) {
        if (Messagebox.show("Confirmar cambio de estado?", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.OK) {
            EstadoDocumento estadoDocumento = servicioEstadoDocumento.findBySigla("ENTR");
            valor.setIdEstadoDocumento(estadoDocumento);
            servicioPermiso.modificar(valor);
            consultarPermisosPorentr();
        }
    }

    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void cargarArchivos(@BindingParam("valor") SolicitudPermiso valor) {
        final HashMap<String, SolicitudPermiso> map = new HashMap<String, SolicitudPermiso>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/cargarArchivos.zul", null, map);
        window.doModal();
        consultarPermisosPorentr();
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

    @Command
    public void verPermiso(@BindingParam("valor") SolicitudPermiso valor) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("numeracion", valor.getSolpNumeracion());

        try {
            if (valor.getIdTipoSolicitud().getTipsSigla().equals("CC")) {
                String nombreReporteConstruccion = "certificadoConstruccion.jasper";
                ArchivoUtils.reporteGeneral(parametros, parametrizar, nombreReporteConstruccion);
            } else if (valor.getIdTipoSolicitud().getTipsSigla().equals("VA")) {
                String nombreReporteVehiculo = "certificadoVehiculo.jasper";
                ArchivoUtils.reporteGeneral(parametros, parametrizar, nombreReporteVehiculo);
            } else {
                String nombreReporte = "permiso.jasper";
                ArchivoUtils.reporteGeneral(parametros, parametrizar, nombreReporte);
            }
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

}
