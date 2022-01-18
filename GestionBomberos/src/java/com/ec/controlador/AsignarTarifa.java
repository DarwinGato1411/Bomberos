/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Inspeccion;
import com.ec.entidad.SolicitudPermiso;
import com.ec.entidad.Tarifa;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioSolicitudPermiso;
import com.ec.servicio.ServicioTarifa;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

/**
 *
 * @author gato
 */
public class AsignarTarifa {

    @Wire
    Window wAsingatarifa;

    ServicioSolicitudPermiso servicio = new ServicioSolicitudPermiso();

    private SolicitudPermiso entidadSelected = new SolicitudPermiso();

    UserCredential credential = new UserCredential();

    private List<Tarifa> listTarifa = new ArrayList<Tarifa>();
    private Tarifa tarifaSelected = null;
    ServicioTarifa servicioTipoTarifa = new ServicioTarifa();

    private Inspeccion entidadInspeccion = new Inspeccion();

    private BigDecimal valorImpuesto = BigDecimal.ZERO;
    private BigDecimal valorCobroImpuesto = BigDecimal.ZERO;
    private BigDecimal totalCobrar = BigDecimal.ZERO;
    private BigDecimal valorAdicional = BigDecimal.ZERO;
//    private String solpFormaPago = "EFECTIVO";

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") SolicitudPermiso valor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        entidadSelected = valor;
        tarifaSelected = valor.getIdTarifa() == null ? null : valor.getIdTarifa();
        valorCobroImpuesto = valor.getSolpImpuestoPredialValor() != null ? valor.getSolpImpuestoPredialValor() : BigDecimal.ZERO;
        valorImpuesto = valor.getSolpImpuestoPredial() != null ? valor.getSolpImpuestoPredial() : BigDecimal.ZERO;
        valorAdicional = valor.getSolpValorAdicional() != null ? valor.getSolpValorAdicional() : BigDecimal.ZERO;
        totalCobrar = valorAdicional.add(valorCobroImpuesto.add(tarifaSelected != null ? tarifaSelected.getTarValor() : BigDecimal.ZERO));
//        solpFormaPago = valor.getSolpFormaPago() != null ? valor.getSolpFormaPago() : "EFECTIVO";
    }

    public AsignarTarifa() {
        listTarifa = servicioTipoTarifa.findLikeTariDecripcion("");
        Session sess = Sessions.getCurrent();
        UserCredential cre = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        credential = cre;

    }

    @Command
    @NotifyChange({"valorCobroImpuesto", "totalCobrar"})
    public void calcularValor() {
        valorCobroImpuesto = (valorImpuesto.multiply(BigDecimal.valueOf(0.15)).setScale(6)).divide(BigDecimal.valueOf(1000), 4, RoundingMode.FLOOR);

        totalCobrar = valorAdicional.add(valorCobroImpuesto.add(tarifaSelected.getTarValor() != null ? tarifaSelected.getTarValor() : BigDecimal.ZERO));
    }

    @Command
    @NotifyChange("entidadSelected")
    public void guardar() {
        if (tarifaSelected != null && entidadSelected.getSolpFormaPago()!=null) {
//            entidadSelected.setSolpFormaPago(solpFormaPago);
            entidadSelected.setIdTarifa(tarifaSelected);
            entidadSelected.setSolpImpuestoPredial(valorImpuesto);
            entidadSelected.setSolpValorAdicional(valorAdicional);
            entidadSelected.setSolpImpuestoPredialValor(valorCobroImpuesto);
            servicio.modificar(entidadSelected);
            wAsingatarifa.detach();
        } else {
            Clients.showNotification("Verifique la informacion... ",
                    Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);
        }
    }

    public SolicitudPermiso getEntidadSelected() {
        return entidadSelected;
    }

    public void setEntidadSelected(SolicitudPermiso entidadSelected) {
        this.entidadSelected = entidadSelected;
    }

    public UserCredential getCredential() {
        return credential;
    }

    public void setCredential(UserCredential credential) {
        this.credential = credential;
    }

    public List<Tarifa> getListTarifa() {
        return listTarifa;
    }

    public void setListTarifa(List<Tarifa> listTarifa) {
        this.listTarifa = listTarifa;
    }

    public Tarifa getTarifaSelected() {
        return tarifaSelected;
    }

    public void setTarifaSelected(Tarifa tarifaSelected) {
        this.tarifaSelected = tarifaSelected;
    }

    public BigDecimal getValorImpuesto() {
        return valorImpuesto;
    }

    public void setValorImpuesto(BigDecimal valorImpuesto) {
        this.valorImpuesto = valorImpuesto;
    }

    public BigDecimal getValorCobroImpuesto() {
        return valorCobroImpuesto;
    }

    public void setValorCobroImpuesto(BigDecimal valorCobroImpuesto) {
        this.valorCobroImpuesto = valorCobroImpuesto;
    }

    public BigDecimal getTotalCobrar() {
        return totalCobrar;
    }

    public void setTotalCobrar(BigDecimal totalCobrar) {
        this.totalCobrar = totalCobrar;
    }

    public BigDecimal getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(BigDecimal valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

//    public String getSolpFormaPago() {
//        return solpFormaPago;
//    }
//
//    public void setSolpFormaPago(String solpFormaPago) {
//        this.solpFormaPago = solpFormaPago;
//    }



}
