package co.edu.uniquindio.Modelos.documentos;
import co.edu.uniquindio.Modelos.enums.Estado;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Document("moderador")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Moderador {



    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String nombre;
    private String email;
    private String fotoPerfil;
    private String password;

    public Moderador(String id, String nombre, String email, String fotoPerfil, String password) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.fotoPerfil = fotoPerfil;
        this.password = password;
    }
}
