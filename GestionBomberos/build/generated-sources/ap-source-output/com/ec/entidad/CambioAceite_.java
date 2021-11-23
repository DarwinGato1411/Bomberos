package com.ec.entidad;

import com.ec.entidad.Usuario;
import com.ec.entidad.Vehiculo;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-22T13:32:17")
@StaticMetamodel(CambioAceite.class)
public class CambioAceite_ { 

    public static volatile SingularAttribute<CambioAceite, BigDecimal> camKilometraje;
    public static volatile SingularAttribute<CambioAceite, Vehiculo> idVehiculo;
    public static volatile SingularAttribute<CambioAceite, Date> camFecha;
    public static volatile SingularAttribute<CambioAceite, String> camObservacion;
    public static volatile SingularAttribute<CambioAceite, BigDecimal> camProximo;
    public static volatile SingularAttribute<CambioAceite, Usuario> idUsuario;
    public static volatile SingularAttribute<CambioAceite, Integer> idCambioAceite;

}