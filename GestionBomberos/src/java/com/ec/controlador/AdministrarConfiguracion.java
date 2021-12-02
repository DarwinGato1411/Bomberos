/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import static com.ec.controlador.CargarArchivoPermiso.FOLDER_IMG;
import com.ec.entidad.Bombero;
import com.ec.entidad.Parametrizar;
import com.ec.entidad.Parroquia;
import com.ec.entidad.Perfil;
import com.ec.entidad.Recinto;
import com.ec.entidad.Tarifa;
import com.ec.entidad.TipoTarifa;
import com.ec.entidad.Usuario;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioBombero;
import com.ec.servicio.ServicioParametrizar;
import com.ec.servicio.ServicioParroquia;
import com.ec.servicio.ServicioPerfil;
import com.ec.servicio.ServicioRecinto;
import com.ec.servicio.ServicioTarifa;
import com.ec.servicio.ServicioTipoTarifa;
import com.ec.servicio.ServicioUsuario;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.io.Files;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Image;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

/**
 *
 * @author gato
 */
public class AdministrarConfiguracion {

    @Wire
    Window wParametrizar;
    ServicioPerfil servicioPerfil = new ServicioPerfil();
    ServicioUsuario servicioUsuario = new ServicioUsuario();
    ServicioParametrizar servicioParametrizar = new ServicioParametrizar();
    private List<Usuario> listaUsuarios = new ArrayList<Usuario>();
    private List<Perfil> listaPerfil = new ArrayList<Perfil>();
    private Parametrizar parametrizarselected = new Parametrizar();
    private List<String> listaDicos = new ArrayList<String>();
    private String tipoAccion = "update";

    ServicioParroquia servicioParroquia = new ServicioParroquia();
    ServicioRecinto servicioRecinto = new ServicioRecinto();
    ServicioTarifa servicioTarifa = new ServicioTarifa();
    ServicioBombero servicioBombero = new ServicioBombero();
    private List<Parroquia> listaParroquia = new ArrayList<Parroquia>();
    private List<Recinto> listaRecinto = new ArrayList<Recinto>();
    private List<Tarifa> listaTarifa = new ArrayList<Tarifa>();
    private List<Bombero> listaBombero = new ArrayList<Bombero>();
    private List<TipoTarifa> listaCatgoria = new ArrayList<TipoTarifa>();
    ServicioTipoTarifa servicioTipoTarifa = new ServicioTipoTarifa();
    private Bombero bombero = new Bombero();
    private String buscarParroquia = "";
    private String buscarRecinto = "";
    private String buscarTarifa = "";
    private String buscarBombero = "";
    private String buscartipoTarifa = "";

    UserCredential credential = new UserCredential();

    public static String FOLDER_IMG = "";

    public AdministrarConfiguracion() {
        listaDiscos();
        cosultarUsuarios("");
        cosultarPerfiles("");
        consultarParroquia();
        consultarRecinto();
        consultarTarifa();
        consultarBombero();
        consultarTipoTarifa();
        parametrizarselected = servicioParametrizar.findActivo();

        FOLDER_IMG = parametrizarselected != null ? parametrizarselected.getParDisco() + parametrizarselected.getParCarpeta() : "";
        File folderAu = new File(FOLDER_IMG);
        if (!folderAu.exists()) {
            folderAu.mkdirs();
        }
    }

    private void consultarParroquia() {
        listaParroquia = servicioParroquia.findLikeParrDecripcion(buscarParroquia);
    }

    private void consultarRecinto() {
        listaRecinto = servicioRecinto.findLikeDescripcion(buscarRecinto);
    }

    private void consultarTarifa() {
        listaTarifa = servicioTarifa.findLikeTariDecripcion(buscarTarifa);
    }

    private void consultarBombero() {
        listaBombero = servicioBombero.findLikeNombreBombero(buscarBombero);
    }

    private void cosultarUsuarios(String buscar) {
        listaUsuarios = servicioUsuario.FindALlUsuarioPorLikeNombre(buscar);

    }

    private void consultarTipoTarifa() {
        listaCatgoria = servicioTipoTarifa.findLikeTariDecripcion(buscartipoTarifa);

    }

    private void cosultarPerfiles(String buscar) {

        listaPerfil = servicioPerfil.findLikePerNombre("");
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    /*Perfil*/
    @Command
    @NotifyChange("listaPerfil")
    public void agregarPerfil() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/perfil.zul", null, null);
        window.doModal();
        cosultarPerfiles("");
    }

    @Command
    @NotifyChange("listaUsuarios")
    public void modificarPerfil(@BindingParam("valor") Perfil valor) {
        final HashMap<String, Perfil> map = new HashMap<String, Perfil>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/perfil.zul", null, map);
        window.doModal();
        cosultarUsuarios("");
    }

    //usuarios
    @Command
    @NotifyChange("listaUsuarios")
    public void agregarUsario() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/usuario.zul", null, null);
        window.doModal();
        cosultarUsuarios("");
    }

    @Command
    @NotifyChange("listaUsuarios")
    public void modificarUsario(@BindingParam("valor") Usuario usuario) {
        final HashMap<String, Usuario> map = new HashMap<String, Usuario>();
        map.put("valor", usuario);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/usuario.zul", null, map);
        window.doModal();
        cosultarUsuarios("");
    }

    private void listaDiscos() {
        File[] files = File.listRoots();
        if (files != null) {
            for (File f : files) {
                listaDicos.add(f.getPath());
                System.out.println("getAbsolutePath " + f.getAbsolutePath());
                System.out.println("getPath " + f.getPath());
            }
        }
    }
    //Imagen ruta 
    private String filePathImg = "";

    @Command
    @NotifyChange({"fileContent", "parametrizarselected"})
    public void subirPathImagen() throws InterruptedException, IOException {

        org.zkoss.util.media.Media media = Fileupload.get();
        if (media instanceof org.zkoss.image.Image) {
            String nombre = media.getName();
            if (media instanceof org.zkoss.image.Image) {

                System.out.println(" Valida ");
                if (media.getByteData().length > 10 * 1024 * 1024) {
                    Messagebox.show("El arhivo seleccionado sobrepasa el tamaño de 10Mb.\n Por favor seleccione un archivo más pequeño.", "Atención", Messagebox.OK, Messagebox.ERROR);
                    System.out.println(" retorna ");
                    return;
                }
                filePathImg = FOLDER_IMG + File.separator + media.getName();
                System.out.println(" filePathImg " + filePathImg);
                File baseDir = new File(filePathImg);
                if (!baseDir.exists()) {
                    baseDir.mkdirs();
                }
                Files.copy(new File(filePathImg + media.getName()),
                        media.getStreamData());
                parametrizarselected.setParPathLogo(filePathImg);
                servicioParametrizar.modificar(parametrizarselected);
            }
            System.out.println(" no es imagen ");
        }
    }

    @Command
    @NotifyChange("parametrizarselected")
    public void guardar() {
        try {
            if (parametrizarselected != null && parametrizarselected.getParEmpresa() != null
                    && parametrizarselected.getParRucEmpresa() != null) {
                if (tipoAccion.equals("new")) {
                    servicioParametrizar.crear(parametrizarselected);
                } else {
                    servicioParametrizar.modificar(parametrizarselected);
                }
                Clients.showNotification("Registrado correctamente",
                        Clients.NOTIFICATION_TYPE_INFO, null, "end_center", 2000, true);
            } else {
                Clients.showNotification("Verifique la informacion... ",
                        Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);
            }
        } catch (Exception e) {
        }
    }

    public List<Perfil> getListaPerfil() {
        return listaPerfil;
    }

    public void setListaPerfil(List<Perfil> listaPerfil) {
        this.listaPerfil = listaPerfil;
    }

    public Parametrizar getParametrizarselected() {
        return parametrizarselected;
    }

    public void setParametrizarselected(Parametrizar parametrizarselected) {
        this.parametrizarselected = parametrizarselected;
    }

    public List<String> getListaDicos() {
        return listaDicos;
    }

    public void setListaDicos(List<String> listaDicos) {
        this.listaDicos = listaDicos;
    }

    public Window getwParametrizar() {
        return wParametrizar;
    }

    public void setwParametrizar(Window wParametrizar) {
        this.wParametrizar = wParametrizar;
    }

    public ServicioUsuario getServicioUsuario() {
        return servicioUsuario;
    }

    public void setServicioUsuario(ServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    public ServicioParametrizar getServicioParametrizar() {
        return servicioParametrizar;
    }

    public void setServicioParametrizar(ServicioParametrizar servicioParametrizar) {
        this.servicioParametrizar = servicioParametrizar;
    }

    public String getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(String tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    public UserCredential getCredential() {
        return credential;
    }

    public void setCredential(UserCredential credential) {
        this.credential = credential;
    }

    public String getFilePathImg() {
        return filePathImg;
    }

    public void setFilePathImg(String filePathImg) {
        this.filePathImg = filePathImg;
    }

    public List<Parroquia> getListaParroquia() {
        return listaParroquia;
    }

    public void setListaParroquia(List<Parroquia> listaParroquia) {
        this.listaParroquia = listaParroquia;
    }

    public List<Recinto> getListaRecinto() {
        return listaRecinto;
    }

    public void setListaRecinto(List<Recinto> listaRecinto) {
        this.listaRecinto = listaRecinto;
    }

    public String getBuscarParroquia() {
        return buscarParroquia;
    }

    public void setBuscarParroquia(String buscarParroquia) {
        this.buscarParroquia = buscarParroquia;
    }

    public String getBuscarRecinto() {
        return buscarRecinto;
    }

    public void setBuscarRecinto(String buscarRecinto) {
        this.buscarRecinto = buscarRecinto;
    }

    public List<Tarifa> getListaTarifa() {
        return listaTarifa;
    }

    public void setListaTarifa(List<Tarifa> listaTarifa) {
        this.listaTarifa = listaTarifa;
    }

    public String getBuscarTarifa() {
        return buscarTarifa;
    }

    public void setBuscarTarifa(String buscarTarifa) {
        this.buscarTarifa = buscarTarifa;
    }

    public List<Bombero> getListaBombero() {
        return listaBombero;
    }

    public void setListaBombero(List<Bombero> listaBombero) {
        this.listaBombero = listaBombero;
    }

    public String getBuscarBombero() {
        return buscarBombero;
    }

    public void setBuscarBombero(String buscarBombero) {
        this.buscarBombero = buscarBombero;
    }

    public Bombero getBombero() {
        return bombero;
    }

    public void setBombero(Bombero bombero) {
        this.bombero = bombero;
    }

    @Command
    @NotifyChange("listaParroquia")
    public void agregarParroquia() {
        Parroquia nueva = new Parroquia("", "", 1);
        servicioParroquia.crear(nueva);
        consultarParroquia();

    }

    @Command
    @NotifyChange("listaUsuarios")
    public void modificarParroquia(@BindingParam("valor") Parroquia valor) {
        servicioParroquia.modificar(valor);
        Clients.showNotification("Registrado correctamente",
                Clients.NOTIFICATION_TYPE_INFO, null, "end_center", 2000, true);
        consultarParroquia();
    }

    @Command
    @NotifyChange("listaRecinto")
    public void agregarRecinto() {
        Recinto nueva = new Recinto("", "", 1);
        servicioRecinto.crear(nueva);
        consultarRecinto();
    }

    @Command
    @NotifyChange("listaUsuarios")
    public void modificarRecinto(@BindingParam("valor") Recinto valor) {
        servicioRecinto.modificar(valor);
        Clients.showNotification("Registrado correctamente",
                Clients.NOTIFICATION_TYPE_INFO, null, "end_center", 2000, true);
        consultarRecinto();
    }

    @Command
    @NotifyChange("listaTarifa")
    public void agregarTarifa() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/tarifa.zul", null, null);
        window.doModal();
        consultarTarifa();
    }

    @Command
    @NotifyChange("listaTarifa")
    public void modificarTarifa(@BindingParam("valor") Tarifa valor) {
        final HashMap<String, Tarifa> map = new HashMap<String, Tarifa>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/tarifa.zul", null, map);
        window.doModal();
        consultarTarifa();
    }

    @Command
    @NotifyChange("listaBombero")
    public void agregarBombero() {
        Bombero nueva = new Bombero("", "", "");
        servicioBombero.crear(nueva);
        consultarBombero();
    }

    @Command
    @NotifyChange("listaUsuarios")
    public void modificarBombero(@BindingParam("valor") Bombero valor) {

        if (valor.getBomCedula() == null
                || valor.getBomNombre() == null
                || valor.getBomDireccion() == null) {
            Clients.showNotification("Datos solicitados incompletos",
                    Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 2000, true);
        } else {

            Clients.showNotification("Registrado correctamente",
                    Clients.NOTIFICATION_TYPE_INFO, null, "end_center", 2000, true);
            servicioBombero.modificar(valor);
        }
        consultarBombero();
    }

    @Command
    @NotifyChange("listaCatgoria")
    public void modificarCategoria(@BindingParam("valor") TipoTarifa valor) {

        if (valor.getTiptDescripcion() != null) {

            servicioTipoTarifa.modificar(valor);
            Clients.showNotification("Modificado correctamente",
                    Clients.NOTIFICATION_TYPE_INFO, null, "end_center", 2000, true);
        } else {
            Clients.showNotification("Datos solicitados incompletos",
                    Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 2000, true);

        }
        consultarTipoTarifa();
    }

    @Command
    @NotifyChange("listaCatgoria")
    public void agregarCategoria() {
        TipoTarifa nueva = new TipoTarifa("");
        servicioTipoTarifa.crear(nueva);
        consultarTipoTarifa();
    }

    public List<TipoTarifa> getListaCatgoria() {
        return listaCatgoria;
    }

    public void setListaCatgoria(List<TipoTarifa> listaCatgoria) {
        this.listaCatgoria = listaCatgoria;
    }

    public String getBuscartipoTarifa() {
        return buscartipoTarifa;
    }

    public void setBuscartipoTarifa(String buscartipoTarifa) {
        this.buscartipoTarifa = buscartipoTarifa;
    }

}
