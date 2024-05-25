package co.edu.uniquindio.Repositorio;

import co.edu.uniquindio.Modelos.documentos.Publicacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PublicidadRepo extends MongoRepository<Publicacion, String> {
}
