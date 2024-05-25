package co.edu.uniquindio.Modelos.entidades;

import co.edu.uniquindio.Modelos.enums.Estado;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document("horario")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Horario {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private LocalDateTime dia;
    private LocalDateTime HoraApertura;
    private LocalDateTime HoraCierre;

}
