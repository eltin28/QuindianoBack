package co.edu.uniquindio.dto;

import co.edu.uniquindio.Modelos.entidades.Horario;
import co.edu.uniquindio.Modelos.enums.EstadoEvento;
import co.edu.uniquindio.Modelos.enums.TipoEvento;
import jakarta.validation.constraints.*;

import java.util.List;

public record DetalleEventoDTO(
        @NotBlank String codigoEvento,
        @NotBlank String codigoNegocio,
        @NotBlank @Min(10) @Max(30) String nombre,
        @NotBlank @Min(30) @Max(300) String descripcion,
        @NotBlank TipoEvento tipoEvento,
        @NotEmpty List<Horario> diasDisponible,
        @NotNull EstadoEvento estadoEvento
) {
}