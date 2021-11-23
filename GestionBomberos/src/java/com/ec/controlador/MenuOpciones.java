/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menuitem;

/**
 *
 * @author gato
 */
public class MenuOpciones extends SelectorComposer<Component> {

    @Wire("#btnNuevaSolicitud")
    Menuitem btnNuevaSolicitud;
    @Wire("#btnPerIngresados")
    Menuitem btnPerIngresados;
//    @Wire("#btnPorRevisar")
//    Menuitem btnPorRevisar;
    @Wire("#btnPerRevisados")
    Menuitem btnPerRevisados;
    @Wire("#btnPerAprobados")
    Menuitem btnPerAprobados;
    @Wire("#btnEntregaPermiso")
    Menuitem btnEntregaPermiso;
    @Wire("#btnParteDiario")
    Menuitem btnParteDiario;
    @Wire("#btnPorInspeccionar")
    Menuitem btnPorInspeccionar;
    @Wire("#btnPerReinspeccion")
    Menuitem btnPerReinspeccion;
    @Wire("#btnPerRechazados")
    Menuitem btnPerRechazados;
    @Wire("#btnPerAnulados")
    Menuitem btnPerAnulados;
    @Wire("#btnConfigurar")
    Menuitem btnConfigurar;
    UserCredential credential = new UserCredential();
    private String acceso = "";

    public MenuOpciones() {
        Session sess = Sessions.getCurrent();
        UserCredential cre = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        credential = cre;

    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        if (credential.getUsuarioSistema() != null) {
            if (credential.getUsuarioSistema().getUsuNivel() == 1) {
                btnPerIngresados.setVisible(Boolean.TRUE);
//                btnPorRevisar.setVisible(Boolean.TRUE);
                btnPerRevisados.setVisible(Boolean.TRUE);
                btnPerAprobados.setVisible(Boolean.TRUE);
                btnEntregaPermiso.setVisible(Boolean.TRUE);
                btnParteDiario.setVisible(Boolean.TRUE);
                btnPorInspeccionar.setVisible(Boolean.TRUE);
                btnPerReinspeccion.setVisible(Boolean.TRUE);
               
                btnPerAnulados.setVisible(Boolean.TRUE);
                btnConfigurar.setVisible(Boolean.TRUE);
            } else if (credential.getUsuarioSistema().getUsuNivel() == 2) {

                btnPerIngresados.setVisible(Boolean.TRUE);
//                btnPorRevisar.setVisible(Boolean.TRUE);
                btnPerRevisados.setVisible(Boolean.TRUE);
                btnPerAprobados.setVisible(Boolean.TRUE);
                btnEntregaPermiso.setVisible(Boolean.TRUE);
                btnParteDiario.setVisible(Boolean.FALSE);
                btnPorInspeccionar.setVisible(Boolean.FALSE);
                btnPerReinspeccion.setVisible(Boolean.FALSE);
               
                btnPerAnulados.setVisible(Boolean.TRUE);
                btnConfigurar.setVisible(Boolean.FALSE);

            } else if (credential.getUsuarioSistema().getUsuNivel() == 3) {
                btnPerIngresados.setVisible(Boolean.FALSE);
//                btnPorRevisar.setVisible(Boolean.FALSE);
                btnPerRevisados.setVisible(Boolean.TRUE);
                btnPerAprobados.setVisible(Boolean.FALSE);
                btnEntregaPermiso.setVisible(Boolean.FALSE);
                btnParteDiario.setVisible(Boolean.TRUE);
                btnPorInspeccionar.setVisible(Boolean.FALSE);
                btnPerReinspeccion.setVisible(Boolean.FALSE);
                btnPerAnulados.setVisible(Boolean.TRUE);
                btnConfigurar.setVisible(Boolean.FALSE);

            } else if (credential.getUsuarioSistema().getUsuNivel() == 4) {
                btnPerIngresados.setVisible(Boolean.FALSE);
//                btnPorRevisar.setVisible(Boolean.FALSE);
                btnPerRevisados.setVisible(Boolean.FALSE);
                btnPerAprobados.setVisible(Boolean.FALSE);
                btnEntregaPermiso.setVisible(Boolean.FALSE);
                btnParteDiario.setVisible(Boolean.FALSE);
                btnPorInspeccionar.setVisible(Boolean.TRUE);
                btnPerReinspeccion.setVisible(Boolean.TRUE);
                btnPerAnulados.setVisible(Boolean.TRUE);
                btnConfigurar.setVisible(Boolean.FALSE);

            }

        }
    }

    @Listen("onClick = #buttonConsultar")
    public void buttonConsultar() {
        Executions.sendRedirect("/consultas.zul");
    }

    @Listen("onClick = #btnCierreCajaUsu")
    public void btnCierreCaja() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/cierrecaja.zul", null, null);
        window.doModal();
    }

    @Listen("onClick = #btnCierreCajaUsu")
    public void btn() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/cierrecaja.zul", null, null);
        window.doModal();
    }

    @Command
    public void nuevoProducto() {

        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/producto.zul", null, null);
        window.doModal();

    }

    @Command
    public void nuevoCliente() {

        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/cliente.zul", null, null);
        window.doModal();

    }

    @Listen("onClick = #btnNuevaSolicitud")
    public void btnNuevaSolicitud() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/permiso.zul", null, null);
        window.doModal();
    }

    @Listen("onClick = #btnPerIngresados")
    public void btnPerIngresados() {
        Executions.sendRedirect("/menus/permiso_ingresado.zul");
    }

//    @Listen("onClick = #btnPorRevisar")
//    public void btnPorRevisar() {
//        Executions.sendRedirect("/menus/permiso_por_revisar.zul");
//    }

    @Listen("onClick = #btnPerRevisados")
    public void btnPerRevisados() {
        Executions.sendRedirect("/menus/permiso_revisado.zul");
    }

    @Listen("onClick = #btnPerAprobados")
    public void btnPerAprobados() {
        Executions.sendRedirect("/menus/permiso_aprobado.zul");
    }

    @Listen("onClick = #btnEntregaPermiso")
    public void btnEntregaPermiso() {
        Executions.sendRedirect("/menus/permiso_entrega.zul");
    }

    @Listen("onClick = #btnParteDiario")
    public void btnParteDiario() {
        Executions.sendRedirect("/menus/permiso_parte_diario.zul");
    }

    @Listen("onClick = #btnPorInspeccionar")
    public void btnPorInspeccionar() {
        Executions.sendRedirect("/menus/permiso_por_inspeccionar.zul");
    }

    @Listen("onClick = #btnPerReinspeccion")
    public void btnPerReinspeccion() {
        Executions.sendRedirect("/menus/permiso_por_reinspeccion.zul");
    }

    @Listen("onClick = #btnPerRechazados")
    public void btnPerRechazados() {
        Executions.sendRedirect("/menus/permiso_rechazado.zul");
    }

    @Listen("onClick = #btnPerAnulados")
    public void btnPerAnulados() {
        Executions.sendRedirect("/menus/permiso_anulado.zul");
    }

    @Listen("onClick = #btnConfigurar")
    public void btnConfigurar() {
        Executions.sendRedirect("/configuracion/administrar.zul");
    }

    public UserCredential getCredential() {
        return credential;
    }

    public void setCredential(UserCredential credential) {
        this.credential = credential;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }
}
