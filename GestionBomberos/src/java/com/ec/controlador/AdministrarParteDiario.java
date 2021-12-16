/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.EstadoDocumento;
import com.ec.entidad.ParteDiario;
import com.ec.entidad.ParteDiarioTipoSolicitud;
import com.ec.entidad.SolicitudPermiso;
import com.ec.servicio.ServicioEstadoDocumento;
import com.ec.servicio.ServicioInspeccion;
import com.ec.servicio.ServicioParteDiario;
import com.ec.servicio.ServicioParteDiarioTipoSolicitud;
import com.ec.servicio.ServicioSolicitudPermiso;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.Messagebox;

/**
 *
 * @author gato
 */
public class AdministrarParteDiario {

    /*total por rubro*/
    ServicioParteDiario servicioParteDiario = new ServicioParteDiario();
    private List<ParteDiario> listaParteDiarios = new ArrayList<ParteDiario>();
    private Date fecha = new Date();
/*total tipo de solicitud*/
    
    ServicioParteDiarioTipoSolicitud servicioParteDiarioTipoSolicitud= new ServicioParteDiarioTipoSolicitud();
    private List<ParteDiarioTipoSolicitud> listaDiarioTipoSolicituds = new ArrayList<ParteDiarioTipoSolicitud>();
    public AdministrarParteDiario() {

        consultarPermisosEntr();
    }

    private void consultarPermisosEntr() {
        listaParteDiarios = servicioParteDiario.findByFecha(fecha);
        listaDiarioTipoSolicituds=servicioParteDiarioTipoSolicitud.findByFecha(fecha);
    }

    public List<ParteDiario> getListaParteDiarios() {
        return listaParteDiarios;
    }

    public void setListaParteDiarios(List<ParteDiario> listaParteDiarios) {
        this.listaParteDiarios = listaParteDiarios;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<ParteDiarioTipoSolicitud> getListaDiarioTipoSolicituds() {
        return listaDiarioTipoSolicituds;
    }

    public void setListaDiarioTipoSolicituds(List<ParteDiarioTipoSolicitud> listaDiarioTipoSolicituds) {
        this.listaDiarioTipoSolicituds = listaDiarioTipoSolicituds;
    }

    

}
