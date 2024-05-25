package co.edu.uniquindio.Repositorio;

import co.edu.uniquindio.Modelos.documentos.Lugar;
import co.edu.uniquindio.Modelos.enums.CategoriaNegocio;
import co.edu.uniquindio.Modelos.enums.EstadoNgocio;
import co.edu.uniquindio.dto.ItemNegocioDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NegocioRepo extends MongoRepository<Lugar, String> {

    List<Lugar> findAllByCategoriaNegocio(CategoriaNegocio categoriaNegocio);

    List<Lugar> findAllByEstadoNegocio(EstadoNgocio estadoNegocio);

    List<Lugar> findAllByNombre(String nombre);

    List<Lugar> findAllByNombreLikeIgnoreCase(String nombre);

    List<ItemNegocioDTO> findAllBycodigoCliente(String codigoCliente);

}
