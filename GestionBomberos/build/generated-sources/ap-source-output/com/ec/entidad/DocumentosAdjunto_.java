package com.ec.entidad;

import com.ec.entidad.SolicitudPermiso;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-22T13:32:17")
@StaticMetamodel(DocumentosAdjunto.class)
public class DocumentosAdjunto_ { 

    public static volatile SingularAttribute<DocumentosAdjunto, String> adjPath;
    public static volatile SingularAttribute<DocumentosAdjunto, SolicitudPermiso> idSolcitudPer;
    public static volatile SingularAttribute<DocumentosAdjunto, Date> adjFecha;
    public static volatile SingularAttribute<DocumentosAdjunto, String> adjDescripcion;
    public static volatile SingularAttribute<DocumentosAdjunto, Integer> idAdjunto;
    public static volatile SingularAttribute<DocumentosAdjunto, Boolean> adjEstadoArchivo;

}