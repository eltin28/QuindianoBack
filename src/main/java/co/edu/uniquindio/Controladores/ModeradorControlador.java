package co.edu.uniquindio.Controladores;

import co.edu.uniquindio.Modelos.enums.EstadoNgocio;
import co.edu.uniquindio.Servicios.Interfaces.ModeradorServicio;
import co.edu.uniquindio.Servicios.Interfaces.NegocioServicio;
import co.edu.uniquindio.Servicios.Interfaces.RevisionServicio;
import co.edu.uniquindio.dto.*;
import co.edu.uniquindio.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moderadores")
@RequiredArgsConstructor
public class ModeradorControlador {

    private final ModeradorServicio moderadorServicio;
    private final NegocioServicio negocioServicio;
    private final RevisionServicio revisionServicio;

    @PutMapping("/editar-perfil")
    public ResponseEntity<MensajeDTO<String>>
    actualizarModerador(@Valid @RequestBody ModeradorDTO actualizarModeradorDTO) throws Exception {
        moderadorServicio.actualizarModerador(actualizarModeradorDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                "Moderador actualizado correctamente"));
    }

    //Acciones que puede realizar un moderador respecto a los negocios

    @GetMapping("/listar-negocios-estado/{estadoNegocio}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>>
    filtrarPorEstado(@PathVariable EstadoNgocio estadoNegocio) {
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                negocioServicio.filtrarPorEstado(estadoNegocio)));
    }

    //Acciones que realiza un moderador respecto a las revisiones de los negocios

    @PostMapping("/agregar-revision")
    public ResponseEntity<MensajeDTO<String>>
    agregarRevision(@Valid @RequestBody AgregarRevisionDTO agregarRevisionDTO) throws Exception {
        revisionServicio.agregarRevision(agregarRevisionDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                "Revisión registrada correctamente"));
    }

    @GetMapping("/obtener-revision/{codigoRevision}")
    public ResponseEntity<MensajeDTO<DetalleRevisionDTO>>
    obtenerRevision(@PathVariable String codigoRevision) throws ResourceNotFoundException {
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                revisionServicio.obtenerRevision(codigoRevision)));
    }

    @GetMapping("/listar-revisiones-moderador/{codigoModerador}")
    public ResponseEntity<MensajeDTO<List<ItemRevisionDTO>>>
    listarRevisionModerador(@PathVariable String codigoModerador) {
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                revisionServicio.listarRevisionModerador(codigoModerador)));
    }

    @GetMapping("/listar-revisiones-negocio/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<List<ItemRevisionDTO>>>
    listarRevisionNegocio(@PathVariable String codigoNegocio) {
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                revisionServicio.listarRevisionNegocio(codigoNegocio)));
    }

}