package co.edu.uniquindio.dto;

import java.time.LocalTime;

public record HorarioDTO(
        LocalTime horaApertura,
        LocalTime horaCierre) {
}
