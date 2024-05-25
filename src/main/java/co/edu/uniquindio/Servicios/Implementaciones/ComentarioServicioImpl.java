package co.edu.uniquindio.Servicios.Implementaciones;
import co.edu.uniquindio.Modelos.documentos.Comentario;
import co.edu.uniquindio.Repositorio.ComentarioRepositorio;
import co.edu.uniquindio.Servicios.Interfaces.ClienteServicio;
import co.edu.uniquindio.Servicios.Interfaces.ComentarioServicio;
import co.edu.uniquindio.Servicios.Interfaces.CorreoServicio;
import co.edu.uniquindio.Servicios.Interfaces.PublicacionServicio;
import co.edu.uniquindio.dto.ComentarioDTO;
import co.edu.uniquindio.dto.ComentarioReacDTO;
import co.edu.uniquindio.dto.EmailDTO;
import co.edu.uniquindio.dto.ItemComentarioDTO;
import co.edu.uniquindio.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ComentarioRepositorio comentarioRepositorio;
    private final ClienteServicio clienteServicio;
    private final PublicacionServicio publicidadServicio;
    private final CorreoServicio correoServicio;

    @Override
    public String opinarPublicacion(ComentarioDTO opinarPublicacionDTO) throws Exception {

        if (!publicidadServicio.existePublicacion(opinarPublicacionDTO.Idcomentario())) {
            throw new ResourceNotFoundException(opinarPublicacionDTO.Idcomentario());
        }

        if (!clienteServicio.existeCliente(opinarPublicacionDTO.codigoCliente())) {
            throw new ResourceNotFoundException(opinarPublicacionDTO.codigoCliente());
        }

        Comentario comentario = new Comentario();

        comentario.setIdcomentario(opinarPublicacionDTO.Idcomentario());
        comentario.setCodigoCliente(opinarPublicacionDTO.codigoCliente());
        comentario.setFecha(LocalDateTime.now());
        comentario.setMensaje(opinarPublicacionDTO.mensaje());

        Comentario comentarioGuardada = comentarioRepositorio.save(comentario);

        //Codigo de enviar correos de prueba
        correoServicio.enviarCorreo(new EmailDTO(
                "Tu publicación ha sido comentada",
                "Tu publicación ha sido comentada por " + clienteServicio.obtenerCliente(opinarPublicacionDTO.codigoCliente()).nickname() + ": " + opinarPublicacionDTO.mensaje(),
                clienteServicio.obtenerCliente(
                        publicidadServicio.obtenerPublicacion(opinarPublicacionDTO.Idcomentario()).codigoCliente()
                ).email()
        ));

        return comentarioGuardada.getIdcomentario();
    }

    @Override
    public List<ItemComentarioDTO> listarOpinionesPublicacion(String idPublicacion) throws ResourceNotFoundException {
        return comentarioRepositorio.findAllByIdcomentario(idPublicacion);
    }

    @Override
    public void reaccionarOpinion(ComentarioReacDTO reaccionarOpinionDTO) throws Exception {

        if (!clienteServicio.existeCliente(reaccionarOpinionDTO.idCliente())) {
            throw new ResourceNotFoundException(reaccionarOpinionDTO.idCliente());
        }

        Comentario comentario = obtenerOpinionID(reaccionarOpinionDTO.idComentario());

        if (comentario.getListaMeGustas().contains(reaccionarOpinionDTO.idCliente())) {
            comentario.getListaMeGustas().remove(reaccionarOpinionDTO.idCliente());
        } else {
            comentario.getListaMeGustas().add(reaccionarOpinionDTO.idCliente());
        }

        comentarioRepositorio.save(comentario);
    }

    @Override
    public List<ItemComentarioDTO> listarOpinionesCliente(String idCliente) throws Exception {
        return comentarioRepositorio.findAllByCodigoCliente(idCliente);
    }

    //Metodos para verificar existencia de datos
   private Comentario obtenerOpinionID(String idComentario) throws ResourceNotFoundException {

        Optional<Comentario> optionalComentario = comentarioRepositorio.findById(idComentario);

        if (optionalComentario.isEmpty()) {
            throw new ResourceNotFoundException(idComentario);
        }

        return optionalComentario.get();
    }
}