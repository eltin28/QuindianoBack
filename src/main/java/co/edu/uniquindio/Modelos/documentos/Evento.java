package co.edu.uniquindio.Modelos.documentos;

import co.edu.uniquindio.Modelos.entidades.Horario;
import co.edu.uniquindio.Modelos.enums.EstadoEvento;
import co.edu.uniquindio.Modelos.enums.TipoEvento;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("eventos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Evento {
    @Id
    @EqualsAndHashCode.Include
    private String codigoEvento;
    private String codigoNegocio;

    private String nombre;
    private String descripcion;
    private TipoEvento tipoEvento;
    private List<Horario> diasDisponible;
    private EstadoEvento estadoEvento;
}