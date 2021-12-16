/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Bombero;
import com.ec.entidad.Parametrizar;
import com.ec.entidad.Parroquia;
import com.ec.entidad.Recinto;
import com.ec.entidad.SolicitudPermiso;
import com.ec.entidad.Tarifa;
import com.ec.entidad.TipoSolicitud;
import com.ec.entidad.Vehiculo;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioBombero;
import com.ec.servicio.ServicioEstadoDocumento;
import com.ec.servicio.ServicioParametrizar;
import com.ec.servicio.ServicioParroquia;
import com.ec.servicio.ServicioRecinto;
import com.ec.servicio.ServicioSolicitudPermiso;
import com.ec.servicio.ServicioTarifa;
import com.ec.servicio.ServicioTipoSolicitud;
import com.ec.utilitario.ArchivoUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.zkoss.zul.Combobox;
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
    @Wire
    Combobox idTipoSolCmb;
    ServicioSolicitudPermiso servicio = new ServicioSolicitudPermiso();
    ServicioEstadoDocumento servicioEstadoDocumento = new ServicioEstadoDocumento();
    private SolicitudPermiso entidadSelected = new SolicitudPermiso();
    private Vehiculo entidadSelectedvehiculo = new Vehiculo();
    private String tipoAccion = "new";
    private List<TipoSolicitud> listaTipoSolicitud = new ArrayList<TipoSolicitud>();
    private TipoSolicitud tipoSoliSelected = null;
    ServicioTipoSolicitud servicioTipoSolicitud = new ServicioTipoSolicitud();

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

    private List<Recinto> listaRecintos = new ArrayList<Recinto>();
    private Recinto recintoSelected = null;
    ServicioRecinto servicioRecinto = new ServicioRecinto();
    private Parametrizar parametrizar = new Parametrizar();
    private Boolean activaOtro = Boolean.FALSE;
    private Boolean activaConstruccion = Boolean.FALSE;
    private Boolean activaVehiculo = Boolean.FALSE;

    private List<Tarifa> listTarifa = new ArrayList<Tarifa>();
    private Tarifa tarifaSelected = null;
    ServicioTarifa servicioTipoTarifa = new ServicioTarifa();

    private List<Bombero> listBomberos = new ArrayList<Bombero>();
    private Bombero bomberoSelected = new Bombero();
    ServicioBombero servicioBombero = new ServicioBombero();

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") SolicitudPermiso valor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        if (valor != null) {
            try {
                tipoAccion = "update";
                entidadSelected = valor;
                parroquiaSelected = valor.getIdParroquia() != null ? valor.getIdParroquia() : null;
                tarifaSelected = entidadSelected.getIdTarifa() != null ? entidadSelected.getIdTarifa() : null;
                tipoSoliSelected = entidadSelected.getIdTipoSolicitud() != null ? entidadSelected.getIdTipoSolicitud() : null;
                bomberoSelected = entidadSelected.getIdBombero() != null ? entidadSelected.getIdBombero() : null;
                if (valor.getSolPathSolicitud() != null) {
                    fileContent = new AMedia("Visor", "pdf", "application/pdf", ArchivoUtils.Imagen_A_Bytes(valor.getSolPathSolicitud()));
                }
                if (tipoSoliSelected != null) {
                    tipoPermiso();
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(NuevoPermiso.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            tipoAccion = "new";
            entidadSelected = new SolicitudPermiso();

            Date finAno = new Date();
            finAno.setMonth(11);
            finAno.setDate(31);
            
            
            entidadSelected.setSolpAnio((finAno.getYear() + 1900));
            entidadSelected.setSolpFecha(new Date());
            entidadSelected.setSolpFechaReinspeccion(finAno);
            entidadSelected.setSolpBarrioUrbanizacion("PEDRO VICENTE MALDONADO");

        }
        listaParrquia = servicioParroquia.findLikeParrDecripcion("");
        listaTipoSolicitud = servicioTipoSolicitud.findLikeSigla("");
        listaRecintos = servicioRecinto.findByAll();
        listTarifa = servicioTipoTarifa.findLikeTariDecripcion("");
        listBomberos = servicioBombero.findLikeNombreBombero("");
    }

    public NuevoPermiso() {
        parametrizar = servicioParametrizar.findActivo();
        FOLDER_IMG = parametrizar != null ? parametrizar.getParDisco() + File.separator + parametrizar.getParCarpeta() : "";
        File folderAu = new File(FOLDER_IMG);
        if (!folderAu.exists()) {
            folderAu.mkdirs();
        }
        System.out.println("FOLDER_IMG CONSTRuCT " + FOLDER_IMG);

        Session sess = Sessions.getCurrent();
        UserCredential cre = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        credential = cre;

    }

    private Integer generarNumeracion() {
        SolicitudPermiso recuperada = servicio.findUltimoPermiso();
        Integer numeracion = 0;
        if (recuperada != null) {
            // System.out.println("numero de factura " + recuperada);
            numeracion = recuperada.getSolpNumeracion() + 1;

        } else {
            numeracion = 1;

        }
        return numeracion;
    }

    @Command
    @NotifyChange({"activaOtro", "activaConstruccion", "activaVehiculo"})
    public void tipoPermiso() {
        activaOtro = Boolean.FALSE;
        activaConstruccion = Boolean.FALSE;
        activaVehiculo = Boolean.FALSE;

        if (tipoSoliSelected.getTipsSigla().equals("CC")) {
            entidadSelected.setSolpEsinspeccion(Boolean.FALSE);
            entidadSelected.setSolpEsotro(Boolean.FALSE);
            entidadSelected.setSolpEsplanos(Boolean.TRUE);
            entidadSelected.setSolpEsvehiculo(Boolean.FALSE);
            activaOtro = Boolean.FALSE;
            activaConstruccion = Boolean.TRUE;
            activaVehiculo = Boolean.FALSE;

        } else if (tipoSoliSelected.getTipsSigla().equals("OT")) {
            entidadSelected.setSolpEsinspeccion(Boolean.FALSE);
            entidadSelected.setSolpEsotro(Boolean.TRUE);
            entidadSelected.setSolpEsplanos(Boolean.FALSE);
            entidadSelected.setSolpEsvehiculo(Boolean.FALSE);
            activaOtro = Boolean.TRUE;
            activaConstruccion = Boolean.FALSE;
            activaVehiculo = Boolean.FALSE;
        } else if (tipoSoliSelected.getTipsSigla().equals("VA")) {
            entidadSelected.setSolpEsinspeccion(Boolean.FALSE);
            entidadSelected.setSolpEsotro(Boolean.FALSE);
            entidadSelected.setSolpEsplanos(Boolean.FALSE);
            entidadSelected.setSolpEsvehiculo(Boolean.TRUE);
            activaOtro = Boolean.FALSE;
            activaConstruccion = Boolean.FALSE;
            activaVehiculo = Boolean.TRUE;
        } else {
            entidadSelected.setSolpEsinspeccion(Boolean.TRUE);
            entidadSelected.setSolpEsotro(Boolean.FALSE);
            entidadSelected.setSolpEsplanos(Boolean.FALSE);
            activaOtro = Boolean.FALSE;
            activaConstruccion = Boolean.FALSE;
            activaVehiculo = Boolean.FALSE;
        }

        System.out.println("ingresa a verificar " + tipoSoliSelected.getTipsSigla());
    }

    @Command
    @NotifyChange({"entidadSelected", "parroquiaSelected", "recintoSelected"})
    public void buscarInpeccion() {
        SolicitudPermiso recuperada = servicio.findLikeRuc(entidadSelected.getSolNumCedula());
        if (recuperada != null) {
            entidadSelected.setSolpNombreSol(recuperada.getSolpNombreSol());
            entidadSelected.setSolpNombreNegocio(recuperada.getSolpNombreNegocio());
            entidadSelected.setSolpTelefono(recuperada.getSolpTelefono());
            entidadSelected.setSolpActividad(recuperada.getSolpActividad());
            entidadSelected.setSolpBarrioUrbanizacion(recuperada.getSolpBarrioUrbanizacion());
            entidadSelected.setSolpCalle(recuperada.getSolpCalle());
            entidadSelected.setSolpNumCalle(recuperada.getSolpNumCalle());
            entidadSelected.setSolpLote(recuperada.getSolpLote());
            entidadSelected.setSolpInterseccion(recuperada.getSolpInterseccion());
//            entidadSelected.setIdParroquia(); 
//            entidadSelected.setIdRecinto(); 
            entidadSelected.setIdParroquia(recuperada.getIdParroquia());
            parroquiaSelected = recuperada.getIdParroquia();
            recintoSelected = recuperada.getIdRecinto();
        }
    }

    @Command
    @NotifyChange("entidadSelected")
    public void guardar() {
        if (entidadSelected != null && entidadSelected.getSolNumCedula() != null
                && entidadSelected.getSolNumCedula() != null
                && entidadSelected.getSolpNombreSol() != null
                && entidadSelected.getSolpFecha() != null) {
            if (tipoSoliSelected == null) {
                Clients.showNotification("Seleccione un tipo de solicitud... ",
                        Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);
                return;
            }
//            if (tarifaSelected == null) {
//                Clients.showNotification("Seleccione una tarifa... ",
//                        Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);
//                return;
//            }
            if (bomberoSelected == null) {
                Clients.showNotification("Seleccione una agente de inspeccion... ",
                        Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);
                return;
            }

            if (tipoSoliSelected.getTipsSigla().equals("CC")) {
                if (entidadSelected.getSolpProyecto() == null || entidadSelected.getSolpTetefonoProyecto() == null) {
                    Clients.showNotification("Debe llenar los campos Nombre proyecto y Telefono del proyecto ",
                            Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);
                    return;
                }

            } else if (tipoSoliSelected.getTipsSigla().equals("OT")) {
                if (entidadSelected.getSolpOtro() == null) {
                    Clients.showNotification("Debe llenar el campo Descripcion de otros permisos ",
                            Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);
                    return;
                }
            } else {

            }

//                   entidadSelected.setSolpFecha(new Date());
            if (tarifaSelected != null) {
                entidadSelected.setIdTarifa(tarifaSelected);
            }

            entidadSelected.setIdBombero(bomberoSelected);
            entidadSelected.setSolpNombreLocal(entidadSelected.getSolpNombreNegocio());
            entidadSelected.setSolpTelefonoInspeccion(entidadSelected.getSolpTelefonoContacto() != null ? entidadSelected.getSolpTelefonoContacto() : "");
            entidadSelected.setIdTipoSolicitud(tipoSoliSelected);
            if (tipoAccion.equals("new")) {
                Integer numeracion = generarNumeracion();
                entidadSelected.setSolpNumeracion(numeracion);
                entidadSelected.setIdParametrizar(parametrizar);
                entidadSelected.setSolpNumero(ArchivoUtils.numeroTexto(numeracion));
                if (recintoSelected != null) {
                    entidadSelected.setIdRecinto(recintoSelected);
                }

                if (parroquiaSelected != null) {
                    entidadSelected.setIdParroquia(parroquiaSelected);
                }

                entidadSelected.setIdEstadoDocumento(servicioEstadoDocumento.findBySigla("ING"));
                if (credential.getNivelUsuario() != null) {
                    entidadSelected.setIdUsuario(credential.getUsuarioSistema());
                }
                servicio.crear(entidadSelected);

                Map<String, Object> parametros = new HashMap<String, Object>();
                parametros.put("numeracion", entidadSelected.getSolpNumeracion());
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

    public Vehiculo getEntidadSelectedvehiculo() {
        return entidadSelectedvehiculo;
    }

    public void setEntidadSelectedvehiculo(Vehiculo entidadSelectedvehiculo) {
        this.entidadSelectedvehiculo = entidadSelectedvehiculo;
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

    public List<TipoSolicitud> getListaTipoSolicitud() {
        return listaTipoSolicitud;
    }

    public void setListaTipoSolicitud(List<TipoSolicitud> listaTipoSolicitud) {
        this.listaTipoSolicitud = listaTipoSolicitud;
    }

    public TipoSolicitud getTipoSoliSelected() {
        return tipoSoliSelected;
    }

    public void setTipoSoliSelected(TipoSolicitud tipoSoliSelected) {
        this.tipoSoliSelected = tipoSoliSelected;
    }

    public List<Recinto> getListaRecintos() {
        return listaRecintos;
    }

    public void setListaRecintos(List<Recinto> listaRecintos) {
        this.listaRecintos = listaRecintos;
    }

    public Recinto getRecintoSelected() {
        return recintoSelected;
    }

    public void setRecintoSelected(Recinto recintoSelected) {
        this.recintoSelected = recintoSelected;
    }

    public Boolean getActivaOtro() {
        return activaOtro;
    }

    public void setActivaOtro(Boolean activaOtro) {
        this.activaOtro = activaOtro;
    }

    public Boolean getActivaConstruccion() {
        return activaConstruccion;
    }

    public void setActivaConstruccion(Boolean activaConstruccion) {
        this.activaConstruccion = activaConstruccion;
    }

    public Boolean getActivaVehiculo() {
        return activaVehiculo;
    }

    public void setActivaVehiculo(Boolean activaVehiculo) {
        this.activaVehiculo = activaVehiculo;
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

    public List<Bombero> getListBomberos() {
        return listBomberos;
    }

    public void setListBomberos(List<Bombero> listBomberos) {
        this.listBomberos = listBomberos;
    }

    public Bombero getBomberoSelected() {
        return bomberoSelected;
    }

    public void setBomberoSelected(Bombero bomberoSelected) {
        this.bomberoSelected = bomberoSelected;
    }

}
