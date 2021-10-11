/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;

/**
 *
 * @author Darwin
 */
public class MenuAdministrador extends SelectorComposer<Component> {

    @Command
    public void btnPermisos() {
        Executions.sendRedirect("/configuracion/permiso.zul");

    }

    @Command
    public void Emergencias() {
        Executions.sendRedirect("/configuracion/administrar.zul");

    }

    @Command
    public void Operacion() {
        Executions.sendRedirect("/configuracion/administrar.zul");

    }

    @Command
    public void Configuracion() {
        Executions.sendRedirect("/configuracion/administrar.zul");

    }

}
