package co.edu.uniquindio.Servicios.Interfaces;

import co.edu.uniquindio.dto.AgregarRevisionDTO;
import co.edu.uniquindio.dto.DetalleRevisionDTO;
import co.edu.uniquindio.dto.ItemRevisionDTO;
import co.edu.uniquindio.exception.ResourceNotFoundException;

import java.util.List;

public interface RevisionServicio {

    String agregarRevision(AgregarRevisionDTO agregarRevisionDTO) throws Exception;

    DetalleRevisionDTO obtenerRevision(String codigoRevision) throws ResourceNotFoundException;

    List<ItemRevisionDTO> listarRevisionModerador(String codigoModerador);

    List<ItemRevisionDTO> listarRevision();

    List<ItemRevisionDTO> listarRevisionNegocio(String codigoNegocio);
}
