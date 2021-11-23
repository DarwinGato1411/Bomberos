package com.ec.entidad;

import com.ec.entidad.SolicitudPermiso;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-22T13:32:17")
@StaticMetamodel(Parroquia.class)
public class Parroquia_ { 

    public static volatile SingularAttribute<Parroquia, String> parrSigla;
    public static volatile SingularAttribute<Parroquia, Integer> parrPeso;
    public static volatile CollectionAttribute<Parroquia, SolicitudPermiso> solicitudPermisoCollection;
    public static volatile SingularAttribute<Parroquia, Integer> idParroquia;
    public static volatile SingularAttribute<Parroquia, String> parrDescripcion;

}