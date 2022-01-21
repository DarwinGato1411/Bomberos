/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Parametrizar;
import com.ec.entidad.Permiso;
import com.ec.entidad.SolicitudPermiso;
import com.ec.servicio.ServicioParametrizar;
import com.ec.servicio.ServicioSolicitudPermiso;
import com.ec.utilitario.ArchivoUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
    ServicioParametrizar servicioParametrizar = new ServicioParametrizar();
    private Parametrizar parametrizar = new Parametrizar();
    private List<Permiso> listaPermisos = new ArrayList<Permiso>();

    public AdministrarConsultas() {
        parametrizar = servicioParametrizar.findActivo();
        fechainicio.setMonth(0);
        fechainicio.setDate(01);
        consultarSolicitudes();

    }

    @Command
    @NotifyChange({"lstSolicitudPermiso", "buscarSolicitud"})
    public void buscarLikeSolicitudes() {

        consultarSolicitudes();

    }

    @Command
    @NotifyChange({"lstsolicitudPermiso", "buscarSolicitud", "fechafin", "fechainicio"})
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

    private void consultarSolicitudes() {
        lstSolicitudPermiso = servicioSolicitudPermiso.findLikeBuscarSolicitudes(buscarSolicitud, fechainicio, fechafin);
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

    public Parametrizar getParametrizar() {
        return parametrizar;
    }

    public void setParametrizar(Parametrizar parametrizar) {
        this.parametrizar = parametrizar;
    }

    public List<Permiso> getListaPermisos() {
        return listaPermisos;
    }

    public void setListaPermisos(List<Permiso> listaPermisos) {
        this.listaPermisos = listaPermisos;
    }

}
