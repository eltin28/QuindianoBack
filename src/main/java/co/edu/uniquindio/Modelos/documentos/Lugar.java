package co.edu.uniquindio.Modelos.documentos;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.Modelos.entidades.Horario;
import co.edu.uniquindio.Modelos.entidades.Ubicacion;
import co.edu.uniquindio.Modelos.enums.CategoriaNegocio;
import co.edu.uniquindio.Modelos.enums.Estado;
import co.edu.uniquindio.Modelos.enums.EstadoNgocio;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lugares")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Lugar {

    @Id
    @EqualsAndHashCode.Include
    private String codigoNegocio;

    private String codigoCliente;
    private String nombre;
    private String descripcion;
    private CategoriaNegocio categoriaNegocio;
    private EstadoNgocio estadoNegocio;
    private Ubicacion ubicacion;
    private List<String> listaTelefonos = new ArrayList<>();
    private List<String> listaRutasImagenes = new ArrayList<>();
    private List<Horario> listaHorarios = new ArrayList<>();
    private Estado Estado;
}
