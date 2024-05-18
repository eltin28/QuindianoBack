package co.edu.uniquindio.Modelos.entidades;
import co.edu.uniquindio.Modelos.enums.Estado;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document("ubicacion")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Ubicacion {


    @Id
    @EqualsAndHashCode.Include
    private String id;
    private float latitud;
    private float longitud;

}
