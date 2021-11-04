package com.ec.entidad;

import com.ec.entidad.Emergencia;
import com.ec.entidad.GradoBombero;
import com.ec.entidad.Inspeccion;
import com.ec.entidad.SalidaVehiculo;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-13T16:18:10")
@StaticMetamodel(Bombero.class)
public class Bombero_ { 

    public static volatile SingularAttribute<Bombero, String> bomApellido;
    public static volatile CollectionAttribute<Bombero, Emergencia> emergenciaCollection;
    public static volatile SingularAttribute<Bombero, String> bomCorreo;
    public static volatile CollectionAttribute<Bombero, SalidaVehiculo> salidaVehiculoCollection;
    public static volatile SingularAttribute<Bombero, String> bomCedula;
    public static volatile SingularAttribute<Bombero, String> bomDireccion;
    public static volatile SingularAttribute<Bombero, String> bomNombre;
    public static volatile SingularAttribute<Bombero, GradoBombero> idGradoBombero;
    public static volatile SingularAttribute<Bombero, Integer> idBombero;
    public static volatile SingularAttribute<Bombero, String> bomMovil;
    public static volatile CollectionAttribute<Bombero, Inspeccion> inspeccionCollection;

}