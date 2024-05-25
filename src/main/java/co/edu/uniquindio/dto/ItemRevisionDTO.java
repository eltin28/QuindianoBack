package co.edu.uniquindio.dto;

import co.edu.uniquindio.Modelos.enums.EstadoNgocio;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record ItemRevisionDTO(

        @NotBlank String codigoRevision,
        @NotBlank LocalDateTime fecha,
        @NotBlank String codigoModerador,
        @NotBlank String codigoNegocio,
        @NotBlank String descripcion,
        @NotBlank EstadoNgocio estadoNegocio
) {
}
