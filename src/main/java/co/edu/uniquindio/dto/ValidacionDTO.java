package co.edu.uniquindio.dto;

import jakarta.validation.constraints.NotBlank;

public record ValidacionDTO(
        @NotBlank String campo,
        @NotBlank String error
) {
}
