package co.edu.uniquindio.dto;

import co.edu.uniquindio.Modelos.enums.EstadoNgocio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgregarRevisionDTO(
        @NotNull LocalDateTime fecha,
        @NotBlank String codigoModerador,
        @NotBlank String codigoNegocio,
        @NotBlank String descripcion,
        @NotNull EstadoNgocio estadoNegocio
) {
}