package com.ec.entidad;

import com.ec.entidad.PerfilOpcion;
import com.ec.entidad.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-13T16:18:10")
@StaticMetamodel(Perfil.class)
public class Perfil_ { 

    public static volatile SingularAttribute<Perfil, String> perNombre;
    public static volatile SingularAttribute<Perfil, Integer> idPerfil;
    public static volatile CollectionAttribute<Perfil, PerfilOpcion> perfilOpcionCollection;
    public static volatile CollectionAttribute<Perfil, Usuario> usuarioCollection;
    public static volatile SingularAttribute<Perfil, String> perSigla;

}