package co.edu.uniquindio.dto;

import jakarta.validation.constraints.NotBlank;

public record AgregarNegocioFavoritosDTO(

        @NotBlank String idCliente,
        @NotBlank String idNegocio
) {
}
