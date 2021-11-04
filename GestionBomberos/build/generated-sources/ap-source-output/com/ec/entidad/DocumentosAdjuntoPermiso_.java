package com.ec.entidad;

import com.ec.entidad.Permiso;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-13T16:18:10")
@StaticMetamodel(DocumentosAdjuntoPermiso.class)
public class DocumentosAdjuntoPermiso_ { 

    public static volatile SingularAttribute<DocumentosAdjuntoPermiso, Integer> idAdjuntoPermiso;
    public static volatile SingularAttribute<DocumentosAdjuntoPermiso, String> adjpDescripcion;
    public static volatile SingularAttribute<DocumentosAdjuntoPermiso, Date> adjpFecha;
    public static volatile SingularAttribute<DocumentosAdjuntoPermiso, Permiso> idPermiso;
    public static volatile SingularAttribute<DocumentosAdjuntoPermiso, String> adjpPath;

}