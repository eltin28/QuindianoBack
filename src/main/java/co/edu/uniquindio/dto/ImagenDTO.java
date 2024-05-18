package co.edu.uniquindio.dto;

import jakarta.validation.constraints.NotBlank;

public record ImagenDTO(
        @NotBlank String id,
        @NotBlank String url
) {
}
