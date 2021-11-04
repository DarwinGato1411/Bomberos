package com.ec.entidad;

import com.ec.entidad.Bombero;
import com.ec.entidad.Usuario;
import com.ec.entidad.Vehiculo;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-13T16:18:10")
@StaticMetamodel(SalidaVehiculo.class)
public class SalidaVehiculo_ { 

    public static volatile SingularAttribute<SalidaVehiculo, Date> salvFecha;
    public static volatile SingularAttribute<SalidaVehiculo, Vehiculo> idVehiculo;
    public static volatile SingularAttribute<SalidaVehiculo, Integer> idSalidaVehiculo;
    public static volatile SingularAttribute<SalidaVehiculo, BigDecimal> salvSalida;
    public static volatile SingularAttribute<SalidaVehiculo, BigDecimal> salvRetorno;
    public static volatile SingularAttribute<SalidaVehiculo, Usuario> idUsuario;
    public static volatile SingularAttribute<SalidaVehiculo, String> salvObservacion;
    public static volatile SingularAttribute<SalidaVehiculo, Bombero> idBombero;

}