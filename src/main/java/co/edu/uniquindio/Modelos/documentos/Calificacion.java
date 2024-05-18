
package co.edu.uniquindio.Modelos.documentos;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "calificaciones")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Calificacion {
    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private String codigoNegocio;
    private String codigoCliente;
    private String id;
    private LocalDateTime fecha;
    private String mensaje;
    private int valor;
    private String respuesta;

    public Calificacion(String codigo, String codigoNegocio, String id, LocalDateTime fecha, String mensaje, int valor, String respuesta) {
        this.codigo = codigo;
        this.codigoNegocio = codigoNegocio;
        this.id = id;
        this.fecha = fecha;
        this.mensaje = mensaje;
        this.valor = valor;
        this.respuesta = respuesta;
    }
}