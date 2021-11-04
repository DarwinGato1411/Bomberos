package com.ec.entidad;

import com.ec.entidad.Inspeccion;
import com.ec.entidad.Permiso;
import com.ec.entidad.SolicitudPermiso;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-13T16:18:10")
@StaticMetamodel(EstadoDocumento.class)
public class EstadoDocumento_ { 

    public static volatile SingularAttribute<EstadoDocumento, Integer> idEstadoDocumento;
    public static volatile SingularAttribute<EstadoDocumento, String> estDescripcion;
    public static volatile SingularAttribute<EstadoDocumento, String> estSigla;
    public static volatile CollectionAttribute<EstadoDocumento, SolicitudPermiso> solicitudPermisoCollection;
    public static volatile SingularAttribute<EstadoDocumento, Integer> estPeso;
    public static volatile CollectionAttribute<EstadoDocumento, Permiso> permisoCollection;
    public static volatile CollectionAttribute<EstadoDocumento, Inspeccion> inspeccionCollection;

}