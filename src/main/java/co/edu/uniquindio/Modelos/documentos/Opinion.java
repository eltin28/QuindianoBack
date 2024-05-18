package co.edu.uniquindio.Modelos.documentos;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("opiniones")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Opinion extends Comentario {
    @Id
    @EqualsAndHashCode.Include
    private String codigoOpinion;
    private String codigoPublicacion;

}
