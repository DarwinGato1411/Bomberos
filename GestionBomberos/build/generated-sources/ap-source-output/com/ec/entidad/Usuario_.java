package com.ec.entidad;

import com.ec.entidad.CambioAceite;
import com.ec.entidad.CargaCombustible;
import com.ec.entidad.Emergencia;
import com.ec.entidad.Inspeccion;
import com.ec.entidad.Perfil;
import com.ec.entidad.Permiso;
import com.ec.entidad.SalidaVehiculo;
import com.ec.entidad.SolicitudPermiso;
import com.ec.entidad.Vehiculo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-22T13:32:17")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> usuCorreo;
    public static volatile CollectionAttribute<Usuario, CambioAceite> cambioAceiteCollection;
    public static volatile CollectionAttribute<Usuario, Emergencia> emergenciaCollection;
    public static volatile SingularAttribute<Usuario, Date> usuCaduca;
    public static volatile SingularAttribute<Usuario, Integer> idUsuario;
    public static volatile CollectionAttribute<Usuario, Vehiculo> vehiculoCollection;
    public static volatile CollectionAttribute<Usuario, Permiso> permisoCollection;
    public static volatile CollectionAttribute<Usuario, Inspeccion> inspeccionCollection;
    public static volatile SingularAttribute<Usuario, String> usuNombre;
    public static volatile SingularAttribute<Usuario, String> usuDocumento;
    public static volatile SingularAttribute<Usuario, String> usuLogin;
    public static volatile SingularAttribute<Usuario, Integer> usuNivel;
    public static volatile SingularAttribute<Usuario, String> usuFoto;
    public static volatile SingularAttribute<Usuario, String> usuContacto;
    public static volatile SingularAttribute<Usuario, Perfil> idPerfil;
    public static volatile CollectionAttribute<Usuario, SalidaVehiculo> salidaVehiculoCollection;
    public static volatile SingularAttribute<Usuario, String> usuTipoUsuario;
    public static volatile CollectionAttribute<Usuario, SolicitudPermiso> solicitudPermisoCollection;
    public static volatile SingularAttribute<Usuario, String> usuEmail;
    public static volatile SingularAttribute<Usuario, String> usuPassword;
    public static volatile CollectionAttribute<Usuario, CargaCombustible> cargaCombustibleCollection;

}