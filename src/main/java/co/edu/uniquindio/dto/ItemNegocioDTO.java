package co.edu.uniquindio.dto;

import co.edu.uniquindio.Modelos.entidades.Horario;
import co.edu.uniquindio.Modelos.entidades.Ubicacion;
import co.edu.uniquindio.Modelos.enums.CategoriaNegocio;
import co.edu.uniquindio.Modelos.enums.Estado;
import jakarta.validation.constraints.*;

import java.util.List;

public record ItemNegocioDTO(@NotBlank String codigoNegocio,
                             @NotBlank String codigoCliente,
                             @NotBlank @Min(10) @Max(50) String nombre,
                             @NotBlank @Min(50) @Max(300) String descripcion,
                             @NotNull CategoriaNegocio categoriaNegocio,
                             @NotNull Estado estadoNegocio,
                             @NotNull Ubicacion ubicacion,
                             List<String> listaTelefonos,
                             @NotEmpty List<String> listaRutasImagenes,
                             @NotEmpty List<Horario> listaHorarios,
                             @NotNull Estado estadoRegistro) {
}
