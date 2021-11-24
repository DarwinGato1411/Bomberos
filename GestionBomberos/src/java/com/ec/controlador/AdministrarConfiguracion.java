/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Opciones;
import com.ec.entidad.Parametrizar;
import com.ec.entidad.Perfil;
import com.ec.entidad.Usuario;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioParametrizar;
import com.ec.servicio.ServicioPerfil;
import com.ec.servicio.ServicioUsuario;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.io.Files;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
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
    private String tipoAccion = "new";

    UserCredential credential = new UserCredential();

    public AdministrarConfiguracion() {
//        Selectors.wireComponents(view, this, false);
//        if (valor != null) {
//            tipoAccion = "update";
//            parametrizarselected = valor;
//            
//        } else {
//            tipoAccion = "new";
//            parametrizarselected = new Parametrizar();
//        }
        cosultarUsuarios("");
        cosultarPerfiles("");
    }

    private void cosultarUsuarios(String buscar) {
        listaUsuarios = servicioUsuario.FindALlUsuarioPorLikeNombre(buscar);

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
    private String filePathImg;

    @Command
    @NotifyChange({"fileContent", "tipoambiente"})
    public void subirPathImagen() throws InterruptedException, IOException {

        org.zkoss.util.media.Media media = Fileupload.get();
        if (media instanceof org.zkoss.image.Image) {
            String nombre = media.getName();
            if (media instanceof Image) {

                if (media.getByteData().length > 10 * 1024 * 1024) {
                    Messagebox.show("El arhivo seleccionado sobrepasa el tamaño de 10Mb.\n Por favor seleccione un archivo más pequeño.", "Atención", Messagebox.OK, Messagebox.ERROR);

                    return;
                }
                filePathImg = parametrizarselected.getParCarpeta() + File.separator + parametrizarselected.getParFolderAdicional() + File.separator;

                File baseDir = new File(filePathImg);
                if (!baseDir.exists()) {
                    baseDir.mkdirs();
                }
                Files.copy(new File(filePathImg + media.getName()),
                        media.getStreamData());
                parametrizarselected.setParCarpeta(filePathImg + File.separator + nombre);
            }

        }
    }

    @Command
    @NotifyChange("parametrizarselected")
    public void guardar() {
        if (parametrizarselected != null && parametrizarselected.getParEmpresa() != null
                && parametrizarselected.getParRucEmpresa() != null) {
            if (tipoAccion.equals("new")) {
                servicioParametrizar.crear(parametrizarselected);
            } else {
                servicioParametrizar.modificar(parametrizarselected);
            }
            wParametrizar.detach();
        } else {
            Clients.showNotification("Verifique la informacion... ",
                    Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);
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

}
