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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Filedownload;
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
    private BigDecimal valorImpuesto = BigDecimal.ZERO;
    private BigDecimal valorCobroImpuesto = BigDecimal.ZERO;
    private BigDecimal valorAdicional = BigDecimal.ZERO;

    public AdministrarPermisoEntrega() {
        parametrizar = servicioParametrizar.findActivo();
        consultarPermisosPorentr();
    }

    private void consultarPermisosPorentr() {
        listaPermisos = servicioPermiso.findLikePermisoForEstadoCedulaNombre(buscarPorentr, buscar);
    }
 //buscart num solicitudes
    @Command
    @NotifyChange({"listaPermisos", "buscar"})
    public void buscarLikeNumSolicitud() {
        
        consultarSolicitudNum();

    }
    private void consultarSolicitudNum() {
        listaPermisos = servicioPermiso.FindLikeNumeroSolEntrega(buscar,buscarPorentr);
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

                    valorImpuesto = valor.getIdInspeccion().getIdSolcitudPer().getSolpImpuestoPredial();
                    valorCobroImpuesto = valor.getIdInspeccion().getIdSolcitudPer().getSolpImpuestoPredialValor();
                    valorAdicional = valor.getIdInspeccion().getIdSolcitudPer().getSolpValorAdicional();
                    BigDecimal valorTotal = valor.getIdInspeccion().getIdSolcitudPer().getIdTarifa().getTarValor().add(valorCobroImpuesto).add(valorAdicional);
                    System.out.println("TOTAL " + valorTotal);
                    cobro.setCobValor(valorTotal);
                    cobro.setCobImpuestoPredial(valorImpuesto);
                    cobro.setCobImpuestoPredialCobro(valorCobroImpuesto);
                    cobro.setCobValorAdicional(valorAdicional);
                    cobro.setCobFormaPago(valor.getIdInspeccion().getIdSolcitudPer().getSolpFormaPago());
                    valor.setPerPagado(Boolean.TRUE);
                    servicioPermiso.modificar(valor);
                    servicioCobro.crear(cobro);

                }
            } else {

                Cobro cobro = servicioCobro.findByPermiso(valor);
                if (cobro == null) {
                    cobro = new Cobro();
                }
                cobro.setIdPermiso(valor);
                cobro.setCobDetalle(valor.getIdInspeccion().getIdSolcitudPer().getIdTarifa().getTarDescripcion());

                cobro.setCobFecha(new Date());
                cobro.setCobCantidad(BigDecimal.ONE);
//                    ins_impuesto_Predial
                cobro.setCobImpuestoPredial(valor.getIdInspeccion().getIdSolcitudPer().getSolpImpuestoPredial());

                valorImpuesto = valor.getIdInspeccion().getIdSolcitudPer().getSolpImpuestoPredial();
                valorCobroImpuesto = valor.getIdInspeccion().getIdSolcitudPer().getSolpImpuestoPredialValor();
                valorAdicional = valor.getIdInspeccion().getIdSolcitudPer().getSolpValorAdicional();
                BigDecimal valorTotal = valor.getIdInspeccion().getIdSolcitudPer().getIdTarifa().getTarValor().add(valorCobroImpuesto).add(valorAdicional);
                System.out.println("Predial " + valorImpuesto);
                System.out.println("COBRO PREDIAL " + valorCobroImpuesto);
                System.out.println("TOTAL " + valorTotal);
                cobro.setCobValor(valorTotal);
                cobro.setCobImpuestoPredial(valorImpuesto);
                cobro.setCobImpuestoPredialCobro(valorCobroImpuesto);
                cobro.setCobValorAdicional(valorAdicional);
                valor.setPerPagado(Boolean.TRUE);
                servicioPermiso.modificar(valor);
                servicioCobro.modificar(cobro);
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

        String pathSalida = directorioReportes + File.separator + "EntregadosYPorEntregar.xls";
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
            HSSFSheet s = wb.createSheet("Solicitudes entregadas y pendientes por entregar");

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
            ch2.setCellValue(new HSSFRichTextString("Estado"));
            ch2.setCellStyle(estiloCelda);
            
            HSSFCell ch3 = r.createCell(j++);
            ch3.setCellValue(new HSSFRichTextString("Inspector"));
            ch3.setCellStyle(estiloCelda);

            HSSFCell ch4 = r.createCell(j++);
            ch4.setCellValue(new HSSFRichTextString("Tipo Solicitud"));
            ch4.setCellStyle(estiloCelda);

            int rownum = 1;
            int i = 0;

            for (Permiso item : listaPermisos) {
                i = 0;

                r = s.createRow(rownum);

                HSSFCell cf = r.createCell(i++);
                cf.setCellValue(new HSSFRichTextString(item.getIdInspeccion().getIdSolcitudPer().getSolpNumero().toString()));

                HSSFCell cf0 = r.createCell(i++);
                cf0.setCellValue(new HSSFRichTextString(item.getIdInspeccion().getIdSolcitudPer().getSolNumCedula().toString()));

                HSSFCell cf1 = r.createCell(i++);
                cf1.setCellValue(new HSSFRichTextString(item.getIdInspeccion().getIdSolcitudPer().getSolpNombreSol().toString()));

                HSSFCell cf2 = r.createCell(i++);
                cf2.setCellValue(new HSSFRichTextString(sm.format(item.getIdInspeccion().getIdSolcitudPer().getSolpFecha())));
                
                HSSFCell cf3 = r.createCell(i++);
                cf3.setCellValue(new HSSFRichTextString((item.getPerPagado() != null ? item.getPerPagado().toString():"False" )));
               
                HSSFCell cf4 = r.createCell(i++);
                cf4.setCellValue(new HSSFRichTextString((item.getIdInspeccion().getIdSolcitudPer().getIdBombero() != null ? item.getIdInspeccion().getIdSolcitudPer().getIdBombero().getBomNombre() : "")));

                 HSSFCell cf5 = r.createCell(i++);
                cf5.setCellValue(new HSSFRichTextString((item.getIdInspeccion().getIdSolcitudPer().getIdTipoSolicitud()!= null ? item.getIdInspeccion().getIdSolcitudPer().getIdTipoSolicitud().getTipsDescripcion(): "")));

                /*autemta la siguiente fila*/
                rownum += 1;

            }

            for (int k = 0; k <= listaPermisos.size(); k++) {
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
