/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Cobro;
import com.ec.entidad.DocumentosAdjunto;
import com.ec.entidad.EstadoDocumento;
import com.ec.entidad.Parametrizar;
import com.ec.entidad.Permiso;
import com.ec.entidad.SolicitudPermiso;
import com.ec.servicio.ServicioCobro;
import com.ec.servicio.ServicioEstadoDocumento;
import com.ec.servicio.ServicioParametrizar;
import com.ec.servicio.ServicioPermiso;
import com.ec.servicio.ServicioSolicitudPermiso;
import com.ec.utilitario.ArchivoUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
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
    ServicioSolicitudPermiso servicioSolicitudPermiso = new ServicioSolicitudPermiso();
    ServicioEstadoDocumento servicioEstadoDocumento = new ServicioEstadoDocumento();
    private List<Permiso> listaPermisos = new ArrayList<Permiso>();
    private String buscarPorentr = "PORENTR";
    private String buscar = "";
    AMedia fileContent = null;
    
    ServicioParametrizar servicioParametrizar = new ServicioParametrizar();
    private Parametrizar parametrizar = new Parametrizar();

    /*cobro del permiso*/
    ServicioCobro servicioCobro = new ServicioCobro();
    
    public AdministrarPermisoEntrega() {
        parametrizar = servicioParametrizar.findActivo();
        consultarPermisosPorentr();
    }
    
    private void consultarPermisosPorentr() {
        listaPermisos = servicioPermiso.findLikePermisoForEstadoCedulaNombre(buscarPorentr, buscar);
    }
    
    @Command
    @NotifyChange("listaPermisos")
    public void cambiarEstado(@BindingParam("valor") Permiso valor) {
        if (Messagebox.show("Confirmar cambio de estado?", "Confirmar", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.OK) {
            EstadoDocumento estadoDocumento = servicioEstadoDocumento.findBySigla("ENTR");
            SolicitudPermiso solicitud = valor.getIdInspeccion().getIdSolcitudPer();
            solicitud.setIdEstadoDocumento(estadoDocumento);
            servicioSolicitudPermiso.modificar(solicitud);
            consultarPermisosPorentr();
        }
    }
    
    @Command
    @NotifyChange("listaPermisos")
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
    
    @Command
    @NotifyChange("listaPermisos")
    public void agregarcobromanual() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/cobromanual.zul", null, null);
        window.doModal();
        consultarPermisosPorentr();
    }
    
    @Command
    @NotifyChange("listaPermisos")
    public void modificarPermiso(@BindingParam("valor") Permiso valor) {
        final HashMap<String, SolicitudPermiso> map = new HashMap<String, SolicitudPermiso>();
        map.put("valor", valor.getIdInspeccion().getIdSolcitudPer());
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/asignartarifa.zul", null, map);
        window.doModal();
//        consultarPermisosIng();
    }
    
    public String getBuscarPorentr() {
        return buscarPorentr;
    }
    
    public void setBuscarPorentr(String buscarPorentr) {
        this.buscarPorentr = buscarPorentr;
    }
    
    public String getBuscar() {
        return buscar;
    }
    
    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }
    
    public List<Permiso> getListaPermisos() {
        return listaPermisos;
    }
    
    public void setListaPermisos(List<Permiso> listaPermisos) {
        this.listaPermisos = listaPermisos;
    }
    
    @Command
    public void verPermiso(@BindingParam("valor") Permiso valor) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("numeracion", valor.getIdInspeccion().getIdSolcitudPer().getSolpNumeracion());
        
        try {
            if (valor.getIdInspeccion().getIdSolcitudPer().getIdTipoSolicitud().getTipsSigla().equals("CC")) {
                String nombreReporteConstruccion = "certificadoConstruccion.jasper";
                ArchivoUtils.reporteGeneral(parametros, parametrizar, nombreReporteConstruccion);
            } else if (valor.getIdInspeccion().getIdSolcitudPer().getIdTipoSolicitud().getTipsSigla().equals("VA")) {
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
    
    @Command
    @NotifyChange("listaPermisos")
    public void cobrar(@BindingParam("valor") Permiso valor) {
        
        try {
            if (!valor.getPerPagado()) {
                if (Messagebox.show("Desea realizar el cobro", "Pregunta", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.OK) {
//                   cob_impuesto_predial
                    Cobro cobro = new Cobro();
                    cobro.setIdPermiso(valor);
                    cobro.setCobDetalle(valor.getIdInspeccion().getIdSolcitudPer().getIdTarifa().getTarDescripcion());
                    
                    cobro.setCobFecha(new Date());
                    cobro.setCobCantidad(BigDecimal.ONE);
//                    ins_impuesto_Predial
                    cobro.setCobImpuestoPredial(valor.getIdInspeccion().getIdSolcitudPer().getSolpImpuestoPredial());
                    BigDecimal valorTotal = valor.getIdInspeccion().getIdSolcitudPer().getIdTarifa().getTarValor().add(valor.getIdInspeccion().getIdSolcitudPer().getSolpImpuestoPredial());
                    cobro.setCobValor(valorTotal);
                    cobro.setCobImpuestoPredial(valorTotal);
                    valor.setPerPagado(Boolean.TRUE);
                    servicioPermiso.modificar(valor);
                    servicioCobro.crear(cobro);
                    
                }
            }
            Map<String, Object> parametros = new HashMap<String, Object>();
//                parametros.put("numeracion", valor.getSolpNumeracion());
            parametros.put("numeracion", valor.getIdInspeccion().getIdSolcitudPer().getSolpNumeracion());
            String nombreReporte = "reciboCobro.jasper";
            
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
    
}
