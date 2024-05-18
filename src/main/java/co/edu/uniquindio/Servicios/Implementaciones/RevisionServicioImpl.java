package co.edu.uniquindio.Servicios.Implementaciones;

import co.edu.uniquindio.Modelos.documentos.Revision;
import co.edu.uniquindio.Modelos.enums.Estado;
import co.edu.uniquindio.Modelos.enums.EstadoNgocio;
import co.edu.uniquindio.Repositorio.RevisionRepo;
import co.edu.uniquindio.Servicios.Interfaces.*;
import co.edu.uniquindio.dto.AgregarRevisionDTO;
import co.edu.uniquindio.dto.DetalleRevisionDTO;
import co.edu.uniquindio.dto.EmailDTO;
import co.edu.uniquindio.dto.ItemRevisionDTO;
import co.edu.uniquindio.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RevisionServicioImpl implements RevisionServicio {

    private final RevisionRepo revisionRepo;
    private final ModeradorServicio moderadorServicio;
    private final NegocioServicio negocioServicio;
    private final CorreoServicio emailServicio;
    private final ClienteServicio clienteServicio;

    @Override
    public String agregarRevision(AgregarRevisionDTO agregarRevisionDTO) throws Exception {
        if (!moderadorServicio.existeModerador(agregarRevisionDTO.codigoModerador())) {
            throw new ResourceNotFoundException(agregarRevisionDTO.codigoModerador());
        }
        if (!negocioServicio.existeNegocio(agregarRevisionDTO.codigoNegocio())) {
            throw new ResourceNotFoundException(agregarRevisionDTO.codigoNegocio());
        }
        if (agregarRevisionDTO.estadoNegocio() == EstadoNgocio.PENDIENTE) {
            throw new Exception("No se puede asignar una revisión como pendiente");
        }
        if (negocioServicio.obtenerNegocio(agregarRevisionDTO.codigoNegocio()).estadoNegocio() == Estado.APROBADO) {
            throw new Exception("No se puede asignar una revisión a un negocio que ya fue aprobado");
        }
        Revision revision = new Revision();
        revision.setFecha(agregarRevisionDTO.fecha());
        revision.setId(agregarRevisionDTO.codigoModerador());
        revision.setId(agregarRevisionDTO.codigoNegocio());
        revision.setDescripcion(agregarRevisionDTO.descripcion());
       /// revision.setEstadoNgocio(agregarRevisionDTO.estadoNegocio());

        Revision revisionGuardada = revisionRepo.save(revision);

        if (agregarRevisionDTO.estadoNegocio() == EstadoNgocio.APROBADO) {
            negocioServicio.aprobarNegocio(agregarRevisionDTO.codigoNegocio());
        }
        if (agregarRevisionDTO.estadoNegocio() == EstadoNgocio.RECHAZADO) {
            negocioServicio.rechazarNegocio(agregarRevisionDTO.codigoNegocio());
        }


        //Codigo envio correo
        emailServicio.enviarCorreo(new EmailDTO(
                "Tu negocio ha sido recibido una revisión",
                "Tu negocio ha sido recibido una revisión el día " + agregarRevisionDTO.fecha() + ": " +
                        "\n\nEstado: " + agregarRevisionDTO.estadoNegocio() +
                        "\n Descripcion: " + agregarRevisionDTO.descripcion(),
                clienteServicio.obtenerCliente(
                        negocioServicio.obtenerNegocio(agregarRevisionDTO.codigoNegocio()).codigoCliente()
                ).email()
        ));

        return revisionGuardada.getId();
    }

    @Override
    public DetalleRevisionDTO obtenerRevision(String codigoRevision) throws ResourceNotFoundException {
        Revision revision = obtenerRevisionID(codigoRevision);
        return new DetalleRevisionDTO(
                revision.getId(),
                revision.getFecha(),
                revision.getId(),
                revision.getId(),
                revision.getDescripcion(),
                revision.getEstadoNegocio()
        );
    }

    @Override
    public List<ItemRevisionDTO> listarRevisionModerador(String codigoModerador) {
        List<Revision> listaRevision = revisionRepo.findAllByCodigoModerador(codigoModerador);
        List<ItemRevisionDTO> itemsRevision = new ArrayList<>();
        for (Revision revision : listaRevision) {
            itemsRevision.add(new ItemRevisionDTO(
                    revision.getId(),
                    revision.getFecha(),
                    revision.getId(),
                    revision.getId(),
                    revision.getDescripcion(),
                    revision.getEstadoNegocio()
            ));
        }
        return itemsRevision;
    }

    @Override
    public List<ItemRevisionDTO> listarRevision() {
        List<Revision> listaRevision = revisionRepo.findAll();
        List<ItemRevisionDTO> itemsRevision = new ArrayList<>();
        for (Revision revision : listaRevision) {
            itemsRevision.add(new ItemRevisionDTO(
                    revision.getId(),
                    revision.getFecha(),
                    revision.getId(),
                    revision.getId(),
                    revision.getDescripcion(),
                    revision.getEstadoNegocio()
            ));
        }
        return itemsRevision;
    }

    @Override
    public List<ItemRevisionDTO> listarRevisionNegocio(String codigoNegocio) {
        List<Revision> listaRevision = revisionRepo.findAllByCodigoNegocio(codigoNegocio);
        List<ItemRevisionDTO> itemsRevision = new ArrayList<>();
        for (Revision revision : listaRevision) {
            itemsRevision.add(new ItemRevisionDTO(
                    revision.getId(),
                    revision.getFecha(),
                    revision.getId(),
                    revision.getId(),
                    revision.getDescripcion(),
                    revision.getEstadoNegocio()
            ));
        }
        return itemsRevision;
    }

    private Revision obtenerRevisionID(String codigoRevision) throws ResourceNotFoundException {
        Optional<Revision> revision = revisionRepo.findById(codigoRevision);
        if (revision.isEmpty()) {
            throw new ResourceNotFoundException(codigoRevision);
        }
        return revision.get();

    }
}