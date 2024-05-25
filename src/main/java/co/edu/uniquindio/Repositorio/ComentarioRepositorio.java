package co.edu.uniquindio.Repositorio;

import java.util.List;
import java.util.Optional;

import co.edu.uniquindio.Modelos.documentos.Comentario;
import co.edu.uniquindio.dto.ItemComentarioDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepositorio extends MongoRepository<Comentario, String> {
    List<ItemComentarioDTO> findAllByCodigoCliente(String idCliente);

    List<ItemComentarioDTO> findAllByIdcomentario(String Idcomentario);

}