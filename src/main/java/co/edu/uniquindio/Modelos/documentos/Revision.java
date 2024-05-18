package co.edu.uniquindio.Modelos.documentos;

import co.edu.uniquindio.Modelos.enums.Estado;
import co.edu.uniquindio.Modelos.enums.EstadoNgocio;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document("Revision")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Revision {


    @Id
    @EqualsAndHashCode.Include
    private String id;
    private Estado estado;
    private String descripcion;
    private LocalDateTime fecha;
    private Moderador moderador;
    private EstadoNgocio estadoNegocio;
    private String codigoNegocio;
    private String codigoModerador;


}
