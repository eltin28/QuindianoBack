package co.edu.uniquindio.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record ComentarioDTO(
        @NotBlank String codigoCliente,
        @NotBlank String Idcomentario,
        @NotBlank @Min(1) @Max(300) String mensaje
) {
}
