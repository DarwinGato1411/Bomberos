/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.EstadoDocumento;
import com.ec.entidad.Inspeccion;
import com.ec.entidad.Parametrizar;
import com.ec.entidad.SolicitudPermiso;
import com.ec.servicio.ServicioEstadoDocumento;
import com.ec.servicio.ServicioInspeccion;
import com.ec.servicio.ServicioSolicitudPermiso;
import com.ec.utilitario.ArchivoUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.naming.NamingException;
import net.sf.jasperreports.engine.JRException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;

/**
 *
 * @author gato
 */
public class AdministrarPorInspeccionar {

    /*PERMISOS INGRESADOS*/
    ServicioSolicitudPermiso servicioPermiso = new ServicioSolicitudPermiso();
    ServicioEstadoDocumento servicioEstadoDocumento = new ServicioEstadoDocumento();
    private Inspeccion entidadInspeccion = new Inspeccion();
    private List<SolicitudPermiso> listaSolicitudPermisos = new ArrayList<SolicitudPermiso>();
    private Parametrizar parametrizar = new Parametrizar();
    private String buscarPorinspec = "INSPEC";
    private String buscar = "";

    /*enviar a insppeccion*/
    public AdministrarPorInspeccionar() {

        consultarPermisosPorInspec();
    }

    private void consultarPermisosPorInspec() {
        listaSolicitudPermisos = servicioPermiso.findLikePermisoForEstadoCedulaNombre(buscarPorinspec, buscar);
    }

      //buscart num solicitudes
    @Command
    @NotifyChange({"listaSolicitudPermisos", "buscar"})
    public void buscarLikeNumSolicitud() {
        consultarSolicitudNum();

    }
    private void consultarSolicitudNum() {
        listaSolicitudPermisos = servicioPermiso.FindLikeNumeroSolicitud(buscar,buscarPorinspec);
        //saldoPorCobrar();
    }
    
    /*Perfil*/
    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void agregarPermiso() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/permiso.zul", null, null);
        window.doModal();
        consultarPermisosPorInspec();
    }

    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void modificarPermiso(@BindingParam("valor") SolicitudPermiso valor) {
        final HashMap<String, SolicitudPermiso> map = new HashMap<String, SolicitudPermiso>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/permiso.zul", null, map);
        window.doModal();
        consultarPermisosPorInspec();
    }

    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void observacionpre(@BindingParam("valor") SolicitudPermiso valor) {
        if (valor.getSolpSubeArchivoPrevencion()) {
            final HashMap<String, SolicitudPermiso> map = new HashMap<String, SolicitudPermiso>();
            map.put("valor", valor);
            org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                    "/nuevo/observacionprevencion.zul", null, map);
            window.doModal();
            consultarPermisosPorInspec();
        } else {
            Clients.showNotification("Para aprobar la solicitud debe adjuntar un archivo",
                    Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);
        }
    }

    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void cambiarEstado(@BindingParam("valor") SolicitudPermiso valor
    ) {
        if (valor.getSolpSubeArchivoPrevencion()) {
            entidadInspeccion.setInsFecha(new Date());
            entidadInspeccion.setInsObservacion("");
        } else {
            Clients.showNotification("Para enviar a prevención de incendios debe adjuntar un archivo",
                    Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);
        }

//            EstadoDocumento estadoDocumento = servicioEstadoDocumento.findBySigla("APR");
//            valor.setIdEstadoDocumento(estadoDocumento);
//            servicioPermiso.modificar(valor);
//            consultarPermisosPorInspec();
    }

    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void EstadoReinspeccion(@BindingParam("valor") SolicitudPermiso valor
    ) {
        if (Messagebox.show("Enviar a reinspeccion?", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.OK) {
            EstadoDocumento estadoDocumento = servicioEstadoDocumento.findBySigla("REINS");
            valor.setIdEstadoDocumento(estadoDocumento);
            servicioPermiso.modificar(valor);
            consultarPermisosPorInspec();
        }
    }

    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void anularSolicitud(@BindingParam("valor") SolicitudPermiso valor
    ) {
        if (Messagebox.show("Anular documento", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.OK) {
            EstadoDocumento estadoDocumento = servicioEstadoDocumento.findBySigla("ANU");
            valor.setIdEstadoDocumento(estadoDocumento);
            servicioPermiso.modificar(valor);
            consultarPermisosPorInspec();
        }
    }

    @Command
    @NotifyChange("listaSolicitudPermisos")
    public void cargarArchivos(@BindingParam("valor") SolicitudPermiso valor
    ) {
        final HashMap<String, SolicitudPermiso> map = new HashMap<String, SolicitudPermiso>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/cargarArchivos.zul", null, map);
        window.doModal();
        consultarPermisosPorInspec();
    }

    @Command
    public void verPermiso(@BindingParam("valor") SolicitudPermiso valor
    ) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("numeracion", valor.getSolpNumeracion());
        try {
            String nombreReporte = "solicitudInspeccion.jasper";
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

    public List<SolicitudPermiso> getListaSolicitudPermisos() {
        return listaSolicitudPermisos;
    }

    public void setListaSolicitudPermisos(List<SolicitudPermiso> listaSolicitudPermisos) {
        this.listaSolicitudPermisos = listaSolicitudPermisos;
    }

    public Inspeccion getEntidadInspeccion() {
        return entidadInspeccion;
    }

    public void setEntidadInspeccion(Inspeccion entidadInspeccion) {
        this.entidadInspeccion = entidadInspeccion;
    }

    public String getBuscarPorinspec() {
        return buscarPorinspec;
    }

    public void setBuscarPorinspec(String buscarPorinspec) {
        this.buscarPorinspec = buscarPorinspec;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    /*EXPORTAR A EXCEL
    lstFacturas
     */
    @Command
    public void exportListboxToExcel() throws Exception {
        try {
            File dosfile = new File(exportarExcel());
            if (dosfile.exists()) {
                FileInputStream inputStream = new FileInputStream(dosfile);
                Filedownload.save(inputStream, new MimetypesFileTypeMap().getContentType(dosfile), dosfile.getName());
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR AL DESCARGAR EL ARCHIVO" + e.getMessage());
        }
    }

    private String exportarExcel() throws FileNotFoundException, IOException, ParseException {
        String directorioReportes = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/reportes");

        Date date = new Date();
        SimpleDateFormat fhora = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sm = new SimpleDateFormat("yyy-MM-dd");
        String strDate = sm.format(date);

        String pathSalida = directorioReportes + File.separator + "PorInspeccionar.xls";
        System.out.println("Direccion del reporte  " + pathSalida);
        try {
            int j = 0;
            File archivoXLS = new File(pathSalida);
            if (archivoXLS.exists()) {
                archivoXLS.delete();
            }
            archivoXLS.createNewFile();
            FileOutputStream archivo = new FileOutputStream(archivoXLS);
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet s = wb.createSheet("Por Inspeccionar");

            HSSFFont fuente = wb.createFont();
            fuente.setBoldweight((short) 700);
            HSSFCellStyle estiloCelda = wb.createCellStyle();
            estiloCelda.setWrapText(true);
            estiloCelda.setAlignment((short) 2);
            estiloCelda.setFont(fuente);

            HSSFCellStyle estiloCeldaInterna = wb.createCellStyle();
            estiloCeldaInterna.setWrapText(true);
            estiloCeldaInterna.setAlignment((short) 5);
            estiloCeldaInterna.setFont(fuente);

            HSSFCellStyle estiloCelda1 = wb.createCellStyle();
            estiloCelda1.setWrapText(true);
            estiloCelda1.setFont(fuente);

            HSSFRow r = null;

            HSSFCell c = null;
            r = s.createRow(0);

            HSSFCell chfe = r.createCell(j++);
            chfe.setCellValue(new HSSFRichTextString("Nº Solicitud"));
            chfe.setCellStyle(estiloCelda);

            HSSFCell chfe1 = r.createCell(j++);
            chfe1.setCellValue(new HSSFRichTextString("CI/RUC"));
            chfe1.setCellStyle(estiloCelda);

            HSSFCell chfe11 = r.createCell(j++);
            chfe11.setCellValue(new HSSFRichTextString("Nombre"));
            chfe11.setCellStyle(estiloCelda);

            HSSFCell ch1 = r.createCell(j++);
            ch1.setCellValue(new HSSFRichTextString("Fecha"));
            ch1.setCellStyle(estiloCelda);

            HSSFCell ch2 = r.createCell(j++);
            ch2.setCellValue(new HSSFRichTextString("Inspector"));
            ch2.setCellStyle(estiloCelda);

            HSSFCell ch22 = r.createCell(j++);
            ch22.setCellValue(new HSSFRichTextString("Tipo Solicitud"));
            ch22.setCellStyle(estiloCelda);

            int rownum = 1;
            int i = 0;

            for (SolicitudPermiso item : listaSolicitudPermisos) {
                i = 0;

                r = s.createRow(rownum);

                HSSFCell cf = r.createCell(i++);
                cf.setCellValue(new HSSFRichTextString(item.getSolpNumero().toString()));

                HSSFCell cf0 = r.createCell(i++);
                cf0.setCellValue(new HSSFRichTextString(item.getSolNumCedula().toString()));

                HSSFCell cf1 = r.createCell(i++);
                cf1.setCellValue(new HSSFRichTextString(item.getSolpNombreSol().toString()));

                HSSFCell cf2 = r.createCell(i++);
                cf2.setCellValue(new HSSFRichTextString(sm.format(item.getSolpFecha())));

                HSSFCell cf3 = r.createCell(i++);
                cf3.setCellValue(new HSSFRichTextString((item.getIdBombero() != null ? item.getIdBombero().getBomNombre() : "")));
               
                HSSFCell cf4 = r.createCell(i++);
                cf4.setCellValue(new HSSFRichTextString((item.getIdBombero() != null ? item.getIdBombero().getBomNombre() : "")));

                 HSSFCell cf5 = r.createCell(i++);
                cf5.setCellValue(new HSSFRichTextString((item.getIdTipoSolicitud()!= null ? item.getIdTipoSolicitud().getTipsDescripcion(): "")));

                /*autemta la siguiente fila*/
                rownum += 1;

            }

            for (int k = 0; k <= listaSolicitudPermisos.size(); k++) {
                s.autoSizeColumn(k);
            }
            wb.write(archivo);
            archivo.close();

        } catch (IOException e) {
            System.out.println("error " + e.getMessage());
        }
        return pathSalida;

    }
}
