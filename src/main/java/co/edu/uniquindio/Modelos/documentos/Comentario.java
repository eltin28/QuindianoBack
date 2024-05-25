package co.edu.uniquindio.Modelos.documentos;

import co.edu.uniquindio.Modelos.enums.Estado;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document("comentarios")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comentario {

    private String Idcomentario;
    private String codigoCliente;
    private LocalDateTime fecha;
    private String mensaje;
    private List<String> listaMeGustas = new ArrayList<>();

}
