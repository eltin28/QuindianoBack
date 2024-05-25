package co.edu.uniquindio.dto;

import jakarta.validation.constraints.NotBlank;

public record ComentarioReacDTO(

        @NotBlank String idComentario,
        @NotBlank String idCliente
) {
}
