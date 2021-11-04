package com.ec.entidad;

import com.ec.entidad.Usuario;
import com.ec.entidad.Vehiculo;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-13T16:18:10")
@StaticMetamodel(CargaCombustible.class)
public class CargaCombustible_ { 

    public static volatile SingularAttribute<CargaCombustible, BigDecimal> carValor;
    public static volatile SingularAttribute<CargaCombustible, Vehiculo> idVehiculo;
    public static volatile SingularAttribute<CargaCombustible, String> carDescripcion;
    public static volatile SingularAttribute<CargaCombustible, Usuario> idUsuario;
    public static volatile SingularAttribute<CargaCombustible, Date> carFecha;
    public static volatile SingularAttribute<CargaCombustible, Integer> idCargaCombustible;

}