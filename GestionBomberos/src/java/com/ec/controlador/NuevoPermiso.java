/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Parametrizar;
import com.ec.entidad.Parroquia;
import com.ec.entidad.SolicitudPermiso;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioEstadoDocumento;
import com.ec.servicio.ServicioParametrizar;
import com.ec.servicio.ServicioParroquia;
import com.ec.servicio.ServicioPermiso;
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
public class NuevoPermiso {

    @Wire
    Window wOpcion;
    ServicioPermiso servicio = new ServicioPermiso();
    ServicioEstadoDocumento servicioEstadoDocumento = new ServicioEstadoDocumento();
    private SolicitudPermiso entidadSelected = new SolicitudPermiso();
    private String tipoAccion = "new";

    ServicioParametrizar servicioParametrizar = new ServicioParametrizar();
//    para cargar el pdf de la cedula
    private String filePath;
    byte[] buffer = new byte[1024 * 1024];
    private AImage fotoCedula = null;
    public static String FOLDER_IMG = "";
    AMedia fileContent = null;

    UserCredential credential = new UserCredential();

    ServicioParroquia servicioParroquia = new ServicioParroquia();
    private List<Parroquia> listaParrquia = new ArrayList<Parroquia>();
    private Parroquia parroquiaSelected;

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") SolicitudPermiso valor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        if (valor != null) {
            try {
                tipoAccion = "update";
                entidadSelected = valor;
                parroquiaSelected = valor.getIdParroquia();
                if (valor.getSolPathSolicitud() != null) {
                    fileContent = new AMedia("Visor", "pdf", "application/pdf", ArchivoUtils.Imagen_A_Bytes(valor.getSolPathSolicitud()));
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NuevoPermiso.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            tipoAccion = "new";
            entidadSelected = new SolicitudPermiso();
        }
        listaParrquia = servicioParroquia.findLikeParrDecripcion("");
    }

    public NuevoPermiso() {
        Parametrizar param = servicioParametrizar.findActivo();
        FOLDER_IMG = param != null ? param.getParDisco() + File.separator + param.getParCarpeta() : "";
        File folderAu = new File(FOLDER_IMG);
        if (!folderAu.exists()) {
            folderAu.mkdirs();
        }
        System.out.println("FOLDER_IMG CONSTRuCT " + FOLDER_IMG);

        Session sess = Sessions.getCurrent();
        UserCredential cre = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        credential = cre;
    }

    @Command
    @NotifyChange("entidadSelected")
    public void guardar() {
        if (entidadSelected != null && entidadSelected.getSolNumCedula() != null
                && entidadSelected.getSolNumCedula() != null
                && entidadSelected.getSolpNombreSol() != null
                && entidadSelected.getSolpApellidoSol() != null
//                && entidadSelected.getSolNombreSolicitud() != null
//                && entidadSelected.getSolPathSolicitud()!= null
//                && entidadSelected.getSolpNota()!= null
//                && entidadSelected.getSolpFechaReinspeccion()!= null
                && entidadSelected.getSolpFecha() != null) {
//                   entidadSelected.setSolpFecha(new Date());
            if (tipoAccion.equals("new")) {

                entidadSelected.setIdEstadoDocumento(servicioEstadoDocumento.findBySigla("ING"));
                if (credential.getNivelUsuario() != null) {
                    entidadSelected.setIdUsuario(credential.getUsuarioSistema());
                }
                servicio.crear(entidadSelected);

            } else {
                servicio.modificar(entidadSelected);
            }
            wOpcion.detach();
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

    public String getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(String tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    @Command
    @NotifyChange({"entidadSelected", "fileContent"})
    public void subirSolicitud() {

        try {
            org.zkoss.util.media.Media media = Fileupload.get();
//            if (media instanceof org.zkoss.util.media.AMedia) {
            String nombre = media != null ? media.getName() : "";
            if (nombre.contains("pdf")) {

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
                entidadSelected.setSolPathSolicitud(filePath);
                entidadSelected.setSolNombreSolicitud(nombre);
                fileContent = new AMedia("report", "pdf", "application/pdf", media.getStreamData());
//                valor.setPathImgPedido(filePath + nombre);
                Clients.showNotification("Solicitud cargada", Clients.NOTIFICATION_TYPE_INFO, null, "end_before", 1000, true);
            } else {
                Clients.showNotification("Debe cargar un documento PDF", Clients.NOTIFICATION_TYPE_ERROR, null, "end_before", 1000, true);
            }
        } catch (IOException e) {
            System.out.println("ERROR al subir la imagen " + e.getMessage());
        }
    }

    public UserCredential getCredential() {
        return credential;
    }

    public void setCredential(UserCredential credential) {
        this.credential = credential;
    }

    public List<Parroquia> getListaParrquia() {
        return listaParrquia;
    }

    public void setListaParrquia(List<Parroquia> listaParrquia) {
        this.listaParrquia = listaParrquia;
    }

    public Parroquia getParroquiaSelected() {
        return parroquiaSelected;
    }

    public void setParroquiaSelected(Parroquia parroquiaSelected) {
        this.parroquiaSelected = parroquiaSelected;
    }

    public AMedia getFileContent() {
        return fileContent;
    }

    public void setFileContent(AMedia fileContent) {
        this.fileContent = fileContent;
    }

}
