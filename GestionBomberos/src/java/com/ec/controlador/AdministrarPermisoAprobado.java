/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.EstadoDocumento;
import com.ec.entidad.Inspeccion;
import com.ec.entidad.SolicitudPermiso;
import com.ec.servicio.ServicioEstadoDocumento;
import com.ec.servicio.ServicioInspeccion;
import com.ec.servicio.ServicioSolicitudPermiso;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.activation.MimetypesFileTypeMap;
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
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;

/**
 *
 * @author gato
 */
public class AdministrarPermisoAprobado {

   

    /*PERMISOS INGRESADOS*/
     ServicioSolicitudPermiso servicioPermiso = new ServicioSolicitudPermiso();
     ServicioEstadoDocumento servicioEstadoDocumento = new ServicioEstadoDocumento();
    private List<Inspeccion> listaInspeccion = new ArrayList<Inspeccion>();
    private List<SolicitudPermiso> listaSolicitudPermisos = new ArrayList<SolicitudPermiso>();
    ServicioInspeccion servicioInspeccion=new ServicioInspeccion();
    private String buscarApr = "APR";
    private String buscar = "";

    public AdministrarPermisoAprobado() {

        consultarPermisosApr();
    }

   
    private void consultarPermisosApr() {
        listaInspeccion = servicioInspeccion.findLikePermisoForEstadoCedulaNombre(buscarApr , buscar);
    }
      //buscart num solicitudes
    @Command
    @NotifyChange({"listaInspeccion", "buscar"})
    public void buscarLikeNumSolicitud() {
        
        consultarSolicitudNum();

    }
    private void consultarSolicitudNum() {
        listaInspeccion = servicioInspeccion.FindLikeNumeroInspeccion(buscar,buscarApr);
    }
    
    /*Perfil*/
    @Command
    @NotifyChange("listaInspeccion")
    public void agregarPermiso() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/permiso.zul", null, null);
        window.doModal();
        consultarPermisosApr();
    }

    @Command
    @NotifyChange("listaInspeccion")
    public void modificarPermiso(@BindingParam("valor") SolicitudPermiso valor) {
        final HashMap<String, SolicitudPermiso> map = new HashMap<String, SolicitudPermiso>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/permiso.zul", null, map);
        window.doModal();
     consultarPermisosApr();
    }
    @Command
    @NotifyChange("listaInspeccion")
    public void cambiarEstado(@BindingParam("valor") SolicitudPermiso valor) {
        if (Messagebox.show("Enviar a entrega de permisos", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.OK) {
            EstadoDocumento estadoDocumento = servicioEstadoDocumento.findBySigla("PORENTR");
            valor.setIdEstadoDocumento(estadoDocumento);
            servicioPermiso.modificar(valor);
            consultarPermisosApr();
        }
    }
      @Command
    @NotifyChange("listaInspeccion")
    public void observacionper(@BindingParam("valor") Inspeccion valor) {
       
        
        final HashMap<String, Inspeccion> map = new HashMap<String, Inspeccion>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/observacionpermiso.zul", null, map);
        window.doModal();
        consultarPermisosApr();
        
    }
    @Command
    @NotifyChange("listaInspeccion")
    public void cargarArchivos(@BindingParam("valor") Inspeccion valor) {
        final HashMap<String, SolicitudPermiso> map = new HashMap<String, SolicitudPermiso>();
        map.put("valor", valor.getIdSolcitudPer());
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
        "/nuevo/cargarArchivos.zul", null, map);
        window.doModal();
        consultarPermisosApr();
    }
    @Command
    @NotifyChange("listaInspeccion")
    public void anularSolicitud(@BindingParam("valor") Inspeccion valor) {
        if (Messagebox.show("Desea anular la solicitud", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.OK) {
            EstadoDocumento estadoDocumento = servicioEstadoDocumento.findBySigla("ANU");
            SolicitudPermiso solicitud=valor.getIdSolcitudPer();
            solicitud.setIdEstadoDocumento(estadoDocumento);
            servicioPermiso.modificar(solicitud);
            consultarPermisosApr();
        }
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

        String pathSalida = directorioReportes + File.separator + "Aprobados.xls";
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
            HSSFSheet s = wb.createSheet("Solicitudes Aprobadas");

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

    public List<Inspeccion> getListaInspeccion() {
        return listaInspeccion;
    }

    public void setListaInspeccion(List<Inspeccion> listaInspeccion) {
        this.listaInspeccion = listaInspeccion;
    }

    public String getBuscarApr() {
        return buscarApr;
    }

    public void setBuscarApr(String buscarApr) {
        this.buscarApr = buscarApr;
    }
 
    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public List<SolicitudPermiso> getListaSolicitudPermisos() {
        return listaSolicitudPermisos;
    }

    public void setListaSolicitudPermisos(List<SolicitudPermiso> listaSolicitudPermisos) {
        this.listaSolicitudPermisos = listaSolicitudPermisos;
    }
   

}
