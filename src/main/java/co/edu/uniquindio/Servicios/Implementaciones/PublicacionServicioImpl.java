package co.edu.uniquindio.Servicios.Implementaciones;

import co.edu.uniquindio.Modelos.documentos.Publicacion;
import co.edu.uniquindio.Modelos.enums.Estado;
import co.edu.uniquindio.Repositorio.PublicidadRepo;
import co.edu.uniquindio.Servicios.Interfaces.ClienteServicio;
import co.edu.uniquindio.Servicios.Interfaces.PublicacionServicio;
import co.edu.uniquindio.dto.ActualizarPublicacionDTO;
import co.edu.uniquindio.dto.AgregarPublicacionDTO;
import co.edu.uniquindio.dto.ItemPublicacionDTO;
import co.edu.uniquindio.dto.ReaccionarPublicacionDTO;
import co.edu.uniquindio.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PublicacionServicioImpl implements PublicacionServicio {

    private final PublicidadRepo publicidadRepo;
    private final ClienteServicio clienteServicio;

    @Override
    public String agregarPublicacion(AgregarPublicacionDTO agregarPublicacionDTO) throws ResourceNotFoundException {
        if (!clienteServicio.existeCliente(agregarPublicacionDTO.idCliente())){
            throw new ResourceNotFoundException(agregarPublicacionDTO.idCliente());
        }

        Publicacion publicacion = new Publicacion();

        publicacion.setDescripcion(agregarPublicacionDTO.descripcion());
        publicacion.setRutaImagen(agregarPublicacionDTO.rutaImagen());
        publicacion.setCodigoCliente(agregarPublicacionDTO.idCliente());
        publicacion.setFechaPublicacion(agregarPublicacionDTO.fechaPublicacion());

        Publicacion publicacionGuardada = publicidadRepo.save(publicacion);

        return publicacionGuardada.getCodigoPublicacion();

    }

    @Override
    public ItemPublicacionDTO obtenerPublicacion(String idPublicacion) throws ResourceNotFoundException {
        Publicacion publicacion = obtenerPublicacionID(idPublicacion);

        return new ItemPublicacionDTO(
                publicacion.getCodigoPublicacion(),
                publicacion.getCodigoCliente(),
                publicacion.getDescripcion(),
                publicacion.getListaMeGustas(),
                publicacion.getRutaImagen(),
                publicacion.getFechaPublicacion()
        );
    }

    @Override
    public String actualizarPublicacion(ActualizarPublicacionDTO actualizarPublicacionDTO) throws Exception {
        Publicacion publicacion = obtenerPublicacionID(actualizarPublicacionDTO.idPublicacion());

        publicacion.setDescripcion(actualizarPublicacionDTO.descripcion());
        publicacion.setRutaImagen(actualizarPublicacionDTO.rutaImagen());

        Publicacion publicacionGuardada = publicidadRepo.save(publicacion);

        return publicacionGuardada.getCodigoPublicacion();
    }

    @Override
    public void eliminarPublicacion(String idPublicacion) throws Exception {

        Publicacion publicacion = obtenerPublicacionID(idPublicacion);
        if (publicacion.getEstadoRegistro() == Estado.INACTIVO){
            throw new Exception("Ya se encuentra eliminada");
        }
        publicacion.setEstadoRegistro(Estado.INACTIVO);

        publicidadRepo.save(publicacion);
    }

    //Metodos para verificar la existencia de un recurso
    private Publicacion obtenerPublicacionID(String idPublicacion) throws ResourceNotFoundException {

        Optional<Publicacion> optionalPublicacion = publicidadRepo.findById(idPublicacion);

        if (optionalPublicacion.isEmpty()) {
            throw new ResourceNotFoundException(idPublicacion);
        }

        return optionalPublicacion.get();
    }

    @Override
    public boolean existePublicacion(String idPublicacion) {
        return publicidadRepo.findById(idPublicacion).isPresent();
    }

    @Override
    public void reaccionarPublicacion(ReaccionarPublicacionDTO reaccionarPublicacionDTO) throws Exception {

        if (!clienteServicio.existeCliente(reaccionarPublicacionDTO.idCliente())) {
            throw new ResourceNotFoundException(reaccionarPublicacionDTO.idCliente());
        }

        Publicacion publicacion = obtenerPublicacionID(reaccionarPublicacionDTO.idPublicacion());

        if (publicacion.getListaMeGustas().contains(reaccionarPublicacionDTO.idCliente())) {
            publicacion.getListaMeGustas().remove(reaccionarPublicacionDTO.idCliente());
        } else {
            publicacion.getListaMeGustas().add(reaccionarPublicacionDTO.idCliente());
        }

        publicidadRepo.save(publicacion);
    }
}