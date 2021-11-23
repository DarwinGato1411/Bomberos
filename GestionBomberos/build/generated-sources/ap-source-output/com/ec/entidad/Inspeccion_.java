package com.ec.entidad;

import com.ec.entidad.Bombero;
import com.ec.entidad.DocumentosAdjuntoInspeccion;
import com.ec.entidad.EstadoDocumento;
import com.ec.entidad.Permiso;
import com.ec.entidad.SolicitudPermiso;
import com.ec.entidad.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-22T13:32:17")
@StaticMetamodel(Inspeccion.class)
public class Inspeccion_ { 

    public static volatile CollectionAttribute<Inspeccion, DocumentosAdjuntoInspeccion> documentosAdjuntoInspeccionCollection;
    public static volatile SingularAttribute<Inspeccion, String> insObservacion;
    public static volatile SingularAttribute<Inspeccion, EstadoDocumento> idEstadoDocumento;
    public static volatile SingularAttribute<Inspeccion, SolicitudPermiso> idSolcitudPer;
    public static volatile SingularAttribute<Inspeccion, String> insDescripcion;
    public static volatile SingularAttribute<Inspeccion, Integer> idInspeccion;
    public static volatile SingularAttribute<Inspeccion, Usuario> idUsuario;
    public static volatile SingularAttribute<Inspeccion, String> insReferencia;
    public static volatile CollectionAttribute<Inspeccion, Permiso> permisoCollection;
    public static volatile SingularAttribute<Inspeccion, String> insLocalidad;
    public static volatile SingularAttribute<Inspeccion, Date> insFecha;
    public static volatile SingularAttribute<Inspeccion, Bombero> idBombero;

}