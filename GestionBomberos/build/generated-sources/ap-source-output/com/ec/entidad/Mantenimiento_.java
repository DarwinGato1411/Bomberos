package com.ec.entidad;

import com.ec.entidad.EstadoMantenimiento;
import com.ec.entidad.Vehiculo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-13T16:18:10")
@StaticMetamodel(Mantenimiento.class)
public class Mantenimiento_ { 

    public static volatile SingularAttribute<Mantenimiento, String> manDescripcion;
    public static volatile SingularAttribute<Mantenimiento, Vehiculo> idVehiculo;
    public static volatile SingularAttribute<Mantenimiento, Date> manProximo;
    public static volatile SingularAttribute<Mantenimiento, Integer> idMantenimiento;
    public static volatile SingularAttribute<Mantenimiento, Date> manFecha;
    public static volatile SingularAttribute<Mantenimiento, Boolean> manEstado;
    public static volatile SingularAttribute<Mantenimiento, EstadoMantenimiento> idEstadoMan;

}