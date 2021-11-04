package com.ec.entidad;

import com.ec.entidad.Mantenimiento;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-13T16:18:10")
@StaticMetamodel(EstadoMantenimiento.class)
public class EstadoMantenimiento_ { 

    public static volatile SingularAttribute<EstadoMantenimiento, String> estmSigla;
    public static volatile CollectionAttribute<EstadoMantenimiento, Mantenimiento> mantenimientoCollection;
    public static volatile SingularAttribute<EstadoMantenimiento, String> estmDescripcion;
    public static volatile SingularAttribute<EstadoMantenimiento, Integer> idEstadoMan;
    public static volatile SingularAttribute<EstadoMantenimiento, Boolean> estmEstado;

}