package co.edu.uniquindio.Servicios.Interfaces;

import co.edu.uniquindio.dto.ActualizarPublicacionDTO;
import co.edu.uniquindio.dto.AgregarPublicacionDTO;
import co.edu.uniquindio.dto.ItemPublicacionDTO;
import co.edu.uniquindio.dto.ReaccionarPublicacionDTO;
import co.edu.uniquindio.exception.ResourceNotFoundException;

public interface PublicacionServicio {
    String agregarPublicacion(AgregarPublicacionDTO agregarPublicacionDTO) throws Exception;

    String actualizarPublicacion(ActualizarPublicacionDTO actualizarPublicacionDTO) throws Exception;

    void eliminarPublicacion(String idPublicacion) throws Exception;

    ItemPublicacionDTO obtenerPublicacion(String idPublicacion) throws ResourceNotFoundException;

    boolean existePublicacion(String idPublicacion);

    void reaccionarPublicacion(ReaccionarPublicacionDTO reaccionarPublicacionDTO) throws Exception;
}