package co.edu.uniquindio.dto;

import co.edu.uniquindio.Modelos.enums.Estado;

import java.util.List;

public record DetalleClienteDTO(


        String id,
        String nombre,
        String fotoPerfil,
        String nickname,
        String email,
        String ciudadResidencia,
        List<String> listaFavoritos,
        Estado estado
) {



}
