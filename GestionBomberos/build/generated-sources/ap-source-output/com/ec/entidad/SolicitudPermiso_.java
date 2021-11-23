package com.ec.entidad;

import com.ec.entidad.DocumentosAdjunto;
import com.ec.entidad.EstadoDocumento;
import com.ec.entidad.Inspeccion;
import com.ec.entidad.Parroquia;
import com.ec.entidad.Usuario;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-22T13:32:17")
@StaticMetamodel(SolicitudPermiso.class)
public class SolicitudPermiso_ { 

    public static volatile SingularAttribute<SolicitudPermiso, String> solpEdificio;
    public static volatile SingularAttribute<SolicitudPermiso, Integer> solpPiso;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpRecinto;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpNombreNegocio;
    public static volatile SingularAttribute<SolicitudPermiso, Usuario> idUsuario;
    public static volatile CollectionAttribute<SolicitudPermiso, DocumentosAdjunto> documentosAdjuntoCollection;
    public static volatile SingularAttribute<SolicitudPermiso, String> solNumCedula;
    public static volatile SingularAttribute<SolicitudPermiso, BigDecimal> solpAreaConstruccion;
    public static volatile CollectionAttribute<SolicitudPermiso, Inspeccion> inspeccionCollection;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpTelefono;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpDepartamento;
    public static volatile SingularAttribute<SolicitudPermiso, Date> solpFecha;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpPathCroquis;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpInterseccion;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpArchivoRuc;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpPathFotografia;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpPathRuc;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpPathCedula;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpArchivoImpuesto;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpActividad;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpLote;
    public static volatile SingularAttribute<SolicitudPermiso, EstadoDocumento> idEstadoDocumento;
    public static volatile SingularAttribute<SolicitudPermiso, Integer> idSolcitudPer;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpPagoImpuesto;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpArchivoCroquis;
    public static volatile SingularAttribute<SolicitudPermiso, Integer> solpNumeroOcupantes;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpBarrioUrbanizacion;
    public static volatile SingularAttribute<SolicitudPermiso, Parroquia> idParroquia;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpPersonaContacto;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpNombreSol;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpNumCalle;
    public static volatile SingularAttribute<SolicitudPermiso, String> solNombreSolicitud;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpTelefonoContacto;
    public static volatile SingularAttribute<SolicitudPermiso, String> solPathSolicitud;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpDireccion;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpObservacion;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpCalle;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpDescripcion;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpApellidoSol;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpNumero;
    public static volatile SingularAttribute<SolicitudPermiso, String> solpReferencia;

}