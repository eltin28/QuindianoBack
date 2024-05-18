package co.edu.uniquindio.Servicios.Interfaces;

import co.edu.uniquindio.Modelos.documentos.Comentario;
import co.edu.uniquindio.dto.ComentarioDTO;
import co.edu.uniquindio.dto.ComentarioReacDTO;
import co.edu.uniquindio.dto.ItemComentarioDTO;
import co.edu.uniquindio.exception.ResourceNotFoundException;

import java.util.List;

public interface ComentarioServicio {

    String opinarPublicacion(ComentarioDTO opinarPublicacionDTO) throws Exception;

    List<ItemComentarioDTO> listarOpinionesPublicacion(String idPublicacion) throws ResourceNotFoundException;

    void reaccionarOpinion(ComentarioReacDTO reaccionarOpinionDTO) throws Exception;

    List<ItemComentarioDTO> listarOpinionesCliente(String idCliente) throws Exception;
}
