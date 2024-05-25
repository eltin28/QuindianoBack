package co.edu.uniquindio.Servicios.Interfaces;

import co.edu.uniquindio.dto.ItemOpinionDTO;
import co.edu.uniquindio.dto.OpinarPublicacionDTO;
import co.edu.uniquindio.dto.ReaccionarOpinionDTO;
import co.edu.uniquindio.exception.ResourceNotFoundException;

import java.util.List;

public interface OpinionServicio {

    String opinarPublicacion(OpinarPublicacionDTO opinarPublicacionDTO) throws Exception;

    List<ItemOpinionDTO> listarOpinionesPublicacion(String idPublicacion) throws ResourceNotFoundException;

    void reaccionarOpinion(ReaccionarOpinionDTO reaccionarOpinionDTO) throws Exception;

    List<ItemOpinionDTO> listarOpinionesCliente(String idCliente) throws Exception;
}
