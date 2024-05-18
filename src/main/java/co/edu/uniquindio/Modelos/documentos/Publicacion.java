package co.edu.uniquindio.Modelos.documentos;

import co.edu.uniquindio.Modelos.enums.Estado;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document("publicaciones")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Publicacion {
    @Id
    @EqualsAndHashCode.Include
    private String codigoPublicacion;
    private String codigoCliente;
    private String descripcion;
    private List<String> listaMeGustas = new ArrayList<>();
    private String rutaImagen;
    private LocalDateTime fechaPublicacion;
    private Estado estadoRegistro;
}