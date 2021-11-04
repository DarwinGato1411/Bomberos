package com.ec.entidad;

import com.ec.entidad.Bombero;
import com.ec.entidad.SeguimientoEmergencia;
import com.ec.entidad.TipoEmergencia;
import com.ec.entidad.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-13T16:18:10")
@StaticMetamodel(Emergencia.class)
public class Emergencia_ { 

    public static volatile SingularAttribute<Emergencia, String> emerLongitud;
    public static volatile SingularAttribute<Emergencia, String> emerFotoPrin;
    public static volatile SingularAttribute<Emergencia, String> emerNombreReporta;
    public static volatile SingularAttribute<Emergencia, String> emerCedulaReportante;
    public static volatile SingularAttribute<Emergencia, Usuario> idUsuario;
    public static volatile SingularAttribute<Emergencia, Date> emerFecha;
    public static volatile SingularAttribute<Emergencia, TipoEmergencia> idTipoEmergencia;
    public static volatile CollectionAttribute<Emergencia, SeguimientoEmergencia> seguimientoEmergenciaCollection;
    public static volatile SingularAttribute<Emergencia, String> emerLugar;
    public static volatile SingularAttribute<Emergencia, String> emerDescripcion;
    public static volatile SingularAttribute<Emergencia, String> emerLatitud;
    public static volatile SingularAttribute<Emergencia, String> emerMedioConocimiento;
    public static volatile SingularAttribute<Emergencia, Integer> idEmergencia;
    public static volatile SingularAttribute<Emergencia, Bombero> idBombero;

}