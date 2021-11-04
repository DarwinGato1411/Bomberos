package com.ec.entidad;

import com.ec.entidad.Bombero;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-13T16:18:10")
@StaticMetamodel(GradoBombero.class)
public class GradoBombero_ { 

    public static volatile SingularAttribute<GradoBombero, Boolean> graEstado;
    public static volatile SingularAttribute<GradoBombero, String> graAbreviatura;
    public static volatile CollectionAttribute<GradoBombero, Bombero> bomberoCollection;
    public static volatile SingularAttribute<GradoBombero, String> graDescripcion;
    public static volatile SingularAttribute<GradoBombero, Integer> idGradoBombero;

}