package co.edu.uniquindio.Servicios.Interfaces;

import co.edu.uniquindio.dto.*;

import java.util.List;

public interface CalificacionServicio {

    String agregarCalificacion(AgregarCalificacionDTO agregarCalificacionDTO) throws Exception;

    void actualizarCalificacion(ActualizarCalificacionDTO actualizarCalificacionDTO) throws Exception;

    List<ItemCalificacionDTO> listarCalificacionesNegocio(String idNegocio) throws Exception;

    void responderCalificacion(ResponderCalificacionDTO responderCalificacionDTO) throws Exception;

    float obtenerCalificacionPromedioNegocio(String codigoNegocio);

    DetalleCalificacionDTO obtenerCalificacion(String codigoCalificacion) throws Exception;

    List<ItemCalificacionDTO> listarCalificaciones() throws Exception;
}
