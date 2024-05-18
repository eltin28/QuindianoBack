package co.edu.uniquindio.Repositorio;


import co.edu.uniquindio.Modelos.documentos.Cliente;
import co.edu.uniquindio.Modelos.documentos.Moderador;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ModeradorRepositorio extends MongoRepository<Moderador, String> {

    Optional<Moderador> findByEmail(String email);
}