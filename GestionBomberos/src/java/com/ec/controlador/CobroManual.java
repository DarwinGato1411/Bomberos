/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Cobro;
import com.ec.entidad.Parametrizar;
import com.ec.entidad.SolicitudPermiso;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioCobro;
import com.ec.servicio.ServicioEstadoDocumento;
import com.ec.servicio.ServicioParametrizar;
import com.ec.servicio.ServicioSolicitudPermiso;
import com.ec.utilitario.ArchivoUtils;
import java.math.BigDecimal;
import java.util.Date;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

/**
 *
 * @author Best
 */
public class CobroManual {
    
    @Wire
    Window wCobroManual;
    ServicioCobro servicioCobro = new ServicioCobro();
    ServicioSolicitudPermiso servicioSolicitudPermiso = new ServicioSolicitudPermiso();
    ServicioEstadoDocumento servicioEstadoDocumento = new ServicioEstadoDocumento();
    private Cobro cobro = new Cobro();
    UserCredential credential = new UserCredential();
    private Integer cantidadSolicitud=1;
    ServicioParametrizar servicioParametrizar = new ServicioParametrizar();
      private Parametrizar parametrizar = new Parametrizar();
    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        cobro = new Cobro();
        cobro.setCobDetalle("SOLICITUD DE INSPECCION");
        cobro.setCobValor(BigDecimal.ONE);
    }
    
    public CobroManual() {
         parametrizar = servicioParametrizar.findActivo();
        Session sess = Sessions.getCurrent();
        UserCredential cre = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        credential = cre;
    }
    
    private Integer generarNumeracion() {
        SolicitudPermiso recuperada = servicioSolicitudPermiso.findUltimoPermiso();
        Integer numeracion = 0;
        if (recuperada != null) {
            // System.out.println("numero de factura " + recuperada);
            numeracion = recuperada.getSolpNumeracion() + 1;
            
        } else {
            numeracion = 1;
            
        }
        return numeracion;
    }
    
    @Command
    public void guardar() {
        if (cobro.getCobDetalle() != null
                && cobro.getCobValor() != null) {
            Integer valor = generarNumeracion();
            SolicitudPermiso solicitud = null;
            for (int i = 0; i < cantidadSolicitud; i++) {
                valor = valor + 1;
                solicitud = new SolicitudPermiso();
                solicitud.setIdParametrizar(parametrizar);
                solicitud.setSolpNumeracion(valor);
                solicitud.setSolpNumero(ArchivoUtils.numeroTexto(valor));
                solicitud.setIdEstadoDocumento(servicioEstadoDocumento.findBySigla("ING"));
                solicitud.setIdUsuario(credential.getUsuarioSistema());
                solicitud.setSolpNombreSol("CONSUMIDOR FINAL");
                solicitud.setSolpObservacion("");
                solicitud.setSolNumCedula("9999999999999");
                Date finAno = new Date();
                finAno.setMonth(11);
                finAno.setDate(31);
                
                System.out.println("ANIO " + (finAno.getYear() + 1900));
                solicitud.setSolpAnio((finAno.getYear() + 1900));
                solicitud.setSolpFecha(new Date());
                solicitud.setSolpFechaReinspeccion(finAno);
                solicitud.setSolpBarrioUrbanizacion("PEDRO VICENTE MALDONADO");
                servicioSolicitudPermiso.crear(solicitud);
                cobro.setCobCantidad(BigDecimal.ONE);
                cobro.setIdSolicitudPermiso(solicitud);
                cobro.setCobFecha(new Date());
                servicioCobro.crear(cobro);
                wCobroManual.detach();
            }
            
            Clients.showNotification("Cobro correcto",
                    Clients.NOTIFICATION_TYPE_INFO, null, "end_center", 2000, true);
        } else {
            Clients.showNotification("Verifique la informacion ingresada",
                    Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);
        }
    }
    
    public Cobro getCobro() {
        return cobro;
    }
    
    public void setCobro(Cobro cobro) {
        this.cobro = cobro;
    }

    public Integer getCantidadSolicitud() {
        return cantidadSolicitud;
    }

    public void setCantidadSolicitud(Integer cantidadSolicitud) {
        this.cantidadSolicitud = cantidadSolicitud;
    }

    public Parametrizar getParametrizar() {
        return parametrizar;
    }

    public void setParametrizar(Parametrizar parametrizar) {
        this.parametrizar = parametrizar;
    }
    
}
