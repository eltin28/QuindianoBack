package co.edu.uniquindio.dto;

import jakarta.validation.constraints.NotBlank;

public record ItemClienteDTO(


        @NotBlank String Codigo,


        String nombre, String fotoPerfil, String nickname, String email, String ciudad,
        java.util.List<String> listaNegociosFavoritos) {





}
