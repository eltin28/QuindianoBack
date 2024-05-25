package co.edu.uniquindio.dto;

import jakarta.validation.constraints.NotBlank;

public record ReaccionarPublicacionDTO(
        @NotBlank String idPublicacion,
        @NotBlank String idCliente) {
}
