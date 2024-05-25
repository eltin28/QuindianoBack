package co.edu.uniquindio.dto;

import co.edu.uniquindio.Modelos.entidades.Horario;
import co.edu.uniquindio.Modelos.enums.TipoEvento;
import jakarta.validation.constraints.*;

import java.util.List;

public record ActualizarEventoDTO(

        @NotBlank String codigoEvento,
        @NotEmpty List<Horario> horario,
        @NotBlank @Min(10) @Max(30) String nombre,
        @NotBlank @Min(30) @Max(300) String descripcion,
        @NotNull TipoEvento tipoEvento
) {
}
