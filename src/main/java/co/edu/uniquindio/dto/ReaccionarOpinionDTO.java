package co.edu.uniquindio.dto;

import jakarta.validation.constraints.NotBlank;

public record ReaccionarOpinionDTO(
        @NotBlank String idOpinion,
        @NotBlank String idCliente) {
}
