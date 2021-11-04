package com.ec.entidad;

import com.ec.entidad.DocumentosAdjuntoPermiso;
import com.ec.entidad.EstadoDocumento;
import com.ec.entidad.Inspeccion;
import com.ec.entidad.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-13T16:18:10")
@StaticMetamodel(Permiso.class)
public class Permiso_ { 

    public static volatile SingularAttribute<Permiso, EstadoDocumento> idEstadoDocumento;
    public static volatile SingularAttribute<Permiso, String> perTextoLegal;
    public static volatile CollectionAttribute<Permiso, DocumentosAdjuntoPermiso> documentosAdjuntoPermisoCollection;
    public static volatile SingularAttribute<Permiso, Integer> idPermiso;
    public static volatile SingularAttribute<Permiso, Inspeccion> idInspeccion;
    public static volatile SingularAttribute<Permiso, Usuario> idUsuario;
    public static volatile SingularAttribute<Permiso, Date> perFecha;
    public static volatile SingularAttribute<Permiso, String> perFirma;
    public static volatile SingularAttribute<Permiso, String> perDescripcion;

}