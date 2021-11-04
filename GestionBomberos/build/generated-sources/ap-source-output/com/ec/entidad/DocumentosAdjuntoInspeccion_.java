package com.ec.entidad;

import com.ec.entidad.Inspeccion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-13T16:18:10")
@StaticMetamodel(DocumentosAdjuntoInspeccion.class)
public class DocumentosAdjuntoInspeccion_ { 

    public static volatile SingularAttribute<DocumentosAdjuntoInspeccion, String> adjiDescripcion;
    public static volatile SingularAttribute<DocumentosAdjuntoInspeccion, Date> adjiFecha;
    public static volatile SingularAttribute<DocumentosAdjuntoInspeccion, String> adjiPath;
    public static volatile SingularAttribute<DocumentosAdjuntoInspeccion, Inspeccion> idInspeccion;
    public static volatile SingularAttribute<DocumentosAdjuntoInspeccion, Integer> idAdjuntoInspeccion;

}