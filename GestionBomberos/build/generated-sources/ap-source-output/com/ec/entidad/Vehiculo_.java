package com.ec.entidad;

import com.ec.entidad.CambioAceite;
import com.ec.entidad.CargaCombustible;
import com.ec.entidad.Mantenimiento;
import com.ec.entidad.SalidaVehiculo;
import com.ec.entidad.Usuario;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-22T13:32:17")
@StaticMetamodel(Vehiculo.class)
public class Vehiculo_ { 

    public static volatile SingularAttribute<Vehiculo, Integer> idVehiculo;
    public static volatile CollectionAttribute<Vehiculo, CambioAceite> cambioAceiteCollection;
    public static volatile SingularAttribute<Vehiculo, String> vehModelo;
    public static volatile CollectionAttribute<Vehiculo, Mantenimiento> mantenimientoCollection;
    public static volatile SingularAttribute<Vehiculo, Usuario> idUsuario;
    public static volatile SingularAttribute<Vehiculo, String> vehRegistroMunicipal;
    public static volatile SingularAttribute<Vehiculo, String> vehColor;
    public static volatile CollectionAttribute<Vehiculo, SalidaVehiculo> salidaVehiculoCollection;
    public static volatile SingularAttribute<Vehiculo, String> vehSeguro;
    public static volatile SingularAttribute<Vehiculo, String> vehPlaca;
    public static volatile SingularAttribute<Vehiculo, String> vehChasis;
    public static volatile CollectionAttribute<Vehiculo, CargaCombustible> cargaCombustibleCollection;
    public static volatile SingularAttribute<Vehiculo, BigDecimal> vehKilometraje;
    public static volatile SingularAttribute<Vehiculo, Integer> vehAnio;

}