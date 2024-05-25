package co.edu.uniquindio.Servicios.Implementaciones;

import co.edu.uniquindio.Modelos.documentos.Moderador;
import co.edu.uniquindio.Repositorio.ModeradorRepositorio;
import co.edu.uniquindio.Servicios.Interfaces.ModeradorServicio;
import co.edu.uniquindio.dto.ModeradorDTO;
import co.edu.uniquindio.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class ModeradorServicioImpl implements ModeradorServicio {

    private final ModeradorRepositorio moderadorRepo;

    @Override
    public ModeradorDTO buscarPorEmail(String email) {
        return null;
    }

    @Override
    public void actualizarModerador(ModeradorDTO actualizarModeradorDTO) throws Exception {
        Optional<Moderador> optionalModerador = moderadorRepo.findById(actualizarModeradorDTO.Id());
        if (optionalModerador.isEmpty()) {
            throw new ResourceNotFoundException(actualizarModeradorDTO.Id());
        }

        Moderador moderador = optionalModerador.get();
        moderador.setNombre(actualizarModeradorDTO.nombre());
        moderador.setEmail(actualizarModeradorDTO.email());

        moderadorRepo.save(moderador);
    }

    @Override
    public boolean existeModerador(String codigoModerador) {
        return moderadorRepo.findById(codigoModerador).isPresent();
    }
}
