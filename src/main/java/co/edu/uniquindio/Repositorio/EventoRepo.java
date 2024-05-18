package co.edu.uniquindio.Repositorio;

import co.edu.uniquindio.Modelos.documentos.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepo extends MongoRepository<Evento, String> {
    List<Evento> findAllByCodigoNegocio(String codigoNegocio);
}