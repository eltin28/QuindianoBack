package co.edu.uniquindio.Modelos.documentos;

import co.edu.uniquindio.Modelos.enums.Estado;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document("clientes")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private String cedula;
    private String Codigo;
    private String Nickname;
    private String Ciudad;
    private String FotoPerfil;
    private String password;
    private String nombre;
    private String email;
    private List<String> telefono;
    private Estado estado;
    private List<String> listaNegociosFavoritos = new ArrayList<>();
    private List<String> listaPublicacionesFavoritas = new ArrayList<>();



    //@Builder
    public Cliente(String codigo, String cedula, String codigo1, String nickname, String ciudad, String fotoPerfil, String password, String nombre, String email, List<String> telefono, Estado estado, List<String> listaNegociosFavoritos, List<String> listaPublicacionesFavoritas) {
        this.codigo = codigo;
        this.cedula = cedula;
        Codigo = codigo1;
        Nickname = nickname;
        Ciudad = ciudad;
        FotoPerfil = fotoPerfil;
        this.password = password;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.estado = estado;
        this.listaNegociosFavoritos = listaNegociosFavoritos;
        this.listaPublicacionesFavoritas = listaPublicacionesFavoritas;
    }
}