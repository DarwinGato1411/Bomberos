package com.ec.entidad;

import com.ec.entidad.Emergencia;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-13T16:18:10")
@StaticMetamodel(SeguimientoEmergencia.class)
public class SeguimientoEmergencia_ { 

    public static volatile SingularAttribute<SeguimientoEmergencia, Integer> idSeguimientoEmergencia;
    public static volatile SingularAttribute<SeguimientoEmergencia, Date> segeFecha;
    public static volatile SingularAttribute<SeguimientoEmergencia, String> segeObservacion;
    public static volatile SingularAttribute<SeguimientoEmergencia, String> segeDescripcion;
    public static volatile SingularAttribute<SeguimientoEmergencia, Emergencia> idEmergencia;

}