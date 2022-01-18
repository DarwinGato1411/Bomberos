/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import static com.ec.controlador.NuevoPermiso.FOLDER_IMG;
import com.ec.entidad.DocumentosAdjunto;
import com.ec.entidad.EstadoDocumento;
import com.ec.entidad.Inspeccion;
import com.ec.entidad.Parametrizar;
import com.ec.entidad.Parroquia;
import com.ec.entidad.SolicitudPermiso;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioArchivoAdjunto;
import com.ec.servicio.ServicioEstadoDocumento;
import com.ec.servicio.ServicioParametrizar;
import com.ec.servicio.ServicioParroquia;
import com.ec.servicio.ServicioInspeccion;
import com.ec.servicio.ServicioSolicitudPermiso;
import com.ec.utilitario.ArchivoUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.io.Files;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

/**
 *
 * @author gato
 */
public class CargarArchivoPermiso {

    @Wire
    Window wCargaArchivos;
    ServicioInspeccion servicio = new ServicioInspeccion();
    ServicioArchivoAdjunto servicioArchivoAdjunto = new ServicioArchivoAdjunto();
    private List<DocumentosAdjunto> listadoAdjuntos = new ArrayList<DocumentosAdjunto>();
    private DocumentosAdjunto adjunto = new DocumentosAdjunto();
    private SolicitudPermiso permiso = new SolicitudPermiso();
    private Inspeccion inspeccion = new Inspeccion();
    UserCredential credential = new UserCredential();
//    para cargar el pdf de la cedula
    private String filePath;
    byte[] buffer = new byte[1024 * 1024];
    private AImage fotoCedula = null;
    public static String FOLDER_IMG = "";
    AMedia fileContent = null;

    ServicioParametrizar servicioParametrizar = new ServicioParametrizar();
    ServicioSolicitudPermiso servicioPermiso = new ServicioSolicitudPermiso();
    ServicioInspeccion servicioInspeccion = new ServicioInspeccion();

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") SolicitudPermiso valor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        if (valor != null) {
            permiso = valor;
            cargarLista();
        }
    }

    private void cargarLista() {
        listadoAdjuntos = servicioArchivoAdjunto.findBySolicitud(permiso, Boolean.TRUE);
    }

    public CargarArchivoPermiso() {
        Parametrizar param = servicioParametrizar.findActivo();
        FOLDER_IMG = param != null ? param.getParDisco() + File.separator + param.getParCarpeta() : "";
        File folderAu = new File(FOLDER_IMG);
        if (!folderAu.exists()) {
            folderAu.mkdirs();
        }
    }

    @Command
    @NotifyChange({"listadoAdjuntos", "fileContent"})
    public void cargarArchivo() {

        try {
            org.zkoss.util.media.Media media = Fileupload.get();
//            if (media instanceof org.zkoss.util.media.AMedia) {
            String nombre = media != null ? media.getName() : "";
            if (nombre.contains("pdf")) {
                adjunto = new DocumentosAdjunto();
                if (media.getByteData().length > 2 * 1024 * 1024) {
                    Messagebox.show("El arhivo seleccionado sobrepasa el tamaño de 2Mb.\n Por favor seleccione un archivo más pequeño.", "Atención", Messagebox.OK, Messagebox.ERROR);

                    return;
                }
                File baseDir = new File(FOLDER_IMG);
                if (!baseDir.exists()) {
                    baseDir.mkdirs();
                }
//                    String reportFile = Executions.getCurrent().getDesktop().getWebApp()
//                    .getRealPath("/img_pedido");
                filePath = FOLDER_IMG + File.separator + media.getName();
//                    filePath = reportFile + File.separator;

                System.out.println("PATHH COPIAR ARCHIVO SOLICITUD " + filePath);
                Files.copy(new File(filePath),
                        media.getStreamData());
                //fotoCedula = new AImage("fotoCedula", media.getStreamData());
                adjunto.setAdjPath(filePath);
                adjunto.setAdjDescripcion(nombre);
                adjunto.setAdjFecha(new Date());
                adjunto.setIdSolcitudPer(permiso);
                adjunto.setAdjEstadoArchivo(Boolean.TRUE);
                servicioArchivoAdjunto.crear(adjunto);
                if (permiso.getIdEstadoDocumento().getEstSigla().equals("INSPEC")) {
//                    Boolean.TRUE;
                    permiso.setSolpSubeArchivoPrevencion(Boolean.TRUE);
                    servicioPermiso.modificar(permiso);
                } 
                cargarLista();
//                valor.setPathImgPedido(filePath + nombre);
                Clients.showNotification("Solicitud cargada", Clients.NOTIFICATION_TYPE_INFO, null, "end_before", 1000, true);
            } else {
                Clients.showNotification("Debe cargar un documento PDF", Clients.NOTIFICATION_TYPE_ERROR, null, "end_before", 1000, true);
            }
        } catch (IOException e) {
            System.out.println("ERROR al subir el archivo" + e.getMessage());
        }
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
    @NotifyChange({"listadoAdjuntos", "fileContent"})
    public void EstadoArchivo(@BindingParam("valor") DocumentosAdjunto valor) {
        if (Messagebox.show("Eliminar archivo", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.OK) {
            valor.setAdjEstadoArchivo(Boolean.FALSE);
            servicioArchivoAdjunto.modificar(valor);
            cargarLista();
        }
    }

    public UserCredential getCredential() {
        return credential;
    }

    public void setCredential(UserCredential credential) {
        this.credential = credential;
    }

    public List<DocumentosAdjunto> getListadoAdjuntos() {
        return listadoAdjuntos;
    }

    public void setListadoAdjuntos(List<DocumentosAdjunto> listadoAdjuntos) {
        this.listadoAdjuntos = listadoAdjuntos;
    }

    public DocumentosAdjunto getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(DocumentosAdjunto adjunto) {
        this.adjunto = adjunto;
    }

    public SolicitudPermiso getPermiso() {
        return permiso;
    }

    public void setPermiso(SolicitudPermiso permiso) {
        this.permiso = permiso;
    }

    public Inspeccion getInspeccion() {
        return inspeccion;
    }

    public void setInspeccion(Inspeccion inspeccion) {
        this.inspeccion = inspeccion;
    }

    public static String getFOLDER_IMG() {
        return FOLDER_IMG;
    }

    public static void setFOLDER_IMG(String FOLDER_IMG) {
        CargarArchivoPermiso.FOLDER_IMG = FOLDER_IMG;
    }

    public AMedia getFileContent() {
        return fileContent;
    }

    public void setFileContent(AMedia fileContent) {
        this.fileContent = fileContent;
    }

}
