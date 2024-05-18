package co.edu.uniquindio.Repositorio;


import co.edu.uniquindio.Modelos.documentos.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepo extends MongoRepository<Cliente, String> {

    List<Cliente> findAllByEmail(String email);

    List<Cliente> findAllByNickname(String nickname);

    Optional<Cliente> findByEmail(String email);

    Optional<Cliente> findByNickname(String nickname);


}

