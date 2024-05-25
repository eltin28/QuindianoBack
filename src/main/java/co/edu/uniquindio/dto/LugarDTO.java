package co.edu.uniquindio.dto;

import java.util.List;

public record LugarDTO(
        String id,

        String nombre,
        String descripcion,
        String direccion,
        List<String> imagenes,


        List<String> telefonos,
        String categoria,
        boolean autorizado

) {
}
