/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Cobro;
import com.ec.entidad.EstadoDocumento;
import com.ec.entidad.Inspeccion;
import com.ec.entidad.Permiso;
import com.ec.entidad.SolicitudPermiso;
import com.ec.entidad.TipoSolicitud;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioCobro;
import com.ec.servicio.ServicioEstadoDocumento;
import com.ec.servicio.ServicioPermiso;
import com.ec.servicio.ServicioSolicitudPermiso;
import com.ec.servicio.ServicioTipoSolicitud;
import com.ec.utilitario.ArchivoUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
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

    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);

    }

    public CobroManual() {

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
        if (cobro.getCobCantidad() != null
                && cobro.getCobDetalle() != null
                && cobro.getCobValor() != null) {
            Integer valor = generarNumeracion();
            SolicitudPermiso solicitud = null;
            for (int i = 0; i < cobro.getCobCantidad().intValue(); i++) {
                valor = valor + 1;
                solicitud = new SolicitudPermiso();
                solicitud.setSolpNumeracion(valor);
                solicitud.setSolpNumero(ArchivoUtils.numeroTexto(valor));
                solicitud.setIdEstadoDocumento(servicioEstadoDocumento.findBySigla("ING"));
                solicitud.setIdUsuario(credential.getUsuarioSistema());
                solicitud.setSolpNombreSol("CONSUMIDOR FINAL");
                solicitud.setSolpObservacion("CONSUMIDOR FINAL");
                solicitud.setSolNumCedula("9999999999999");
                servicioSolicitudPermiso.crear(solicitud);
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

}
