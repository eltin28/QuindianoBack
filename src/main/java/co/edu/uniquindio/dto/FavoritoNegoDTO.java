package co.edu.uniquindio.dto;

import jakarta.validation.constraints.NotBlank;

public record FavoritoNegoDTO(

        @NotBlank String idCliente,
        @NotBlank String idNegocio
) {
}
