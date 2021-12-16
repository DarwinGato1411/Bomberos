/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Parametrizar;
import com.ec.entidad.ParteDiario;
import com.ec.entidad.ParteDiarioTipoSolicitud;
import com.ec.entidad.SolicitudPermiso;
import com.ec.servicio.ServicioParametrizar;
import com.ec.servicio.ServicioParteDiario;
import com.ec.servicio.ServicioParteDiarioTipoSolicitud;
import com.ec.utilitario.ArchivoUtils;
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
import org.zkoss.zk.ui.Executions;

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
    private BigDecimal totalizar = BigDecimal.ZERO;
    ServicioParteDiarioTipoSolicitud servicioParteDiarioTipoSolicitud = new ServicioParteDiarioTipoSolicitud();
    private List<ParteDiarioTipoSolicitud> listaDiarioTipoSolicituds = new ArrayList<ParteDiarioTipoSolicitud>();

    ServicioParametrizar servicioParametrizar = new ServicioParametrizar();
    private Parametrizar parametrizar = new Parametrizar();

    public AdministrarParteDiario() {
        parametrizar = servicioParametrizar.findActivo();
        consultarPermisosEntr();
    }

    private void consultarPermisosEntr() {
        totalizar = BigDecimal.ZERO;
        listaParteDiarios = servicioParteDiario.findByFecha(fecha);
        listaDiarioTipoSolicituds = servicioParteDiarioTipoSolicitud.findByFecha(fecha);
        for (ParteDiario listaParteDiario : listaParteDiarios) {
            totalizar = totalizar.add(listaParteDiario.getCobValor());
        }
    }

    @Command
    @NotifyChange({"listaParteDiarios", "listaDiarioTipoSolicituds", "totalizar"})
    public void buscarFechas() {
        consultarPermisosEntr();
    }

    @Command
    public void imprimir() {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("fecha", fecha);
        try {
            String nombreReporte = "reporteDiario.jasper";
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

    public BigDecimal getTotalizar() {
        return totalizar;
    }

    public void setTotalizar(BigDecimal totalizar) {
        this.totalizar = totalizar;
    }

}
