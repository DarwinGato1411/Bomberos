package com.ec.entidad;

import com.ec.entidad.Emergencia;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-22T13:32:17")
@StaticMetamodel(TipoEmergencia.class)
public class TipoEmergencia_ { 

    public static volatile SingularAttribute<TipoEmergencia, String> tipeDrescripcion;
    public static volatile CollectionAttribute<TipoEmergencia, Emergencia> emergenciaCollection;
    public static volatile SingularAttribute<TipoEmergencia, String> tipeSigla;
    public static volatile SingularAttribute<TipoEmergencia, Integer> idTipoEmergencia;

}