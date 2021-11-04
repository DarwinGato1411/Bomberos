package com.ec.entidad;

import com.ec.entidad.Opciones;
import com.ec.entidad.Perfil;
import com.ec.entidad.PerfilOpcionPK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-13T16:18:10")
@StaticMetamodel(PerfilOpcion.class)
public class PerfilOpcion_ { 

    public static volatile SingularAttribute<PerfilOpcion, Opciones> opciones;
    public static volatile SingularAttribute<PerfilOpcion, String> peropObservacion;
    public static volatile SingularAttribute<PerfilOpcion, PerfilOpcionPK> perfilOpcionPK;
    public static volatile SingularAttribute<PerfilOpcion, Date> peropFecha;
    public static volatile SingularAttribute<PerfilOpcion, Perfil> perfil;

}