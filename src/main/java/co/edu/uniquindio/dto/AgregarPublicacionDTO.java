package co.edu.uniquindio.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgregarPublicacionDTO(

        @NotBlank @Min(30) @Max(500) String descripcion,
        @NotBlank String rutaImagen,
        @NotBlank String idCliente,
        @NotNull LocalDateTime fechaPublicacion
) {
}
