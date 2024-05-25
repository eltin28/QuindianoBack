package co.edu.uniquindio.Repositorio;

import co.edu.uniquindio.Modelos.documentos.Opinion;
import co.edu.uniquindio.dto.ItemOpinionDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpinionRepo extends MongoRepository<Opinion, String> {
    List<ItemOpinionDTO> findAllByCodigoCliente(String idCliente);

    List<ItemOpinionDTO> findAllByCodigoPublicacion(String idPublicacion);
}