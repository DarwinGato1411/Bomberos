package com.ec.entidad;

import com.ec.entidad.PerfilOpcion;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-22T13:32:17")
@StaticMetamodel(Opciones.class)
public class Opciones_ { 

    public static volatile SingularAttribute<Opciones, Integer> idOpcion;
    public static volatile SingularAttribute<Opciones, Integer> opcPadre;
    public static volatile CollectionAttribute<Opciones, PerfilOpcion> perfilOpcionCollection;
    public static volatile SingularAttribute<Opciones, String> opcObservacion;
    public static volatile SingularAttribute<Opciones, Boolean> opcEstado;
    public static volatile SingularAttribute<Opciones, String> opcSigla;
    public static volatile SingularAttribute<Opciones, String> opcDescripcion;

}