package co.edu.uniquindio.Modelos.documentos;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.Modelos.entidades.Horario;
import co.edu.uniquindio.Modelos.entidades.Ubicacion;
import co.edu.uniquindio.Modelos.enums.CategoriaNegocio;
import co.edu.uniquindio.Modelos.enums.Estado;
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
    private  String id;
    private String idCliente;
    private  String nombre;
    private  String descripcion;
    private  String direccion;
    private CategoriaNegocio categoriaNegocio;
    private Estado estado;
    private Ubicacion ubicacion;
    private List<String> listaTelefonos = new ArrayList<>();
    private List<String> listaRutasImagenes = new ArrayList<>();
    private List<Horario> listaHorarios = new ArrayList<>();


    public Lugar(String id, String idCliente, String nombre, String descripcion, String direccion, CategoriaNegocio categoriaNegocio, Estado estado, Ubicacion ubicacion, List<String> listaTelefonos, List<String> listaRutasImagenes, List<Horario> listaHorarios) {
        this.id = id;
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.categoriaNegocio = categoriaNegocio;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.listaTelefonos = listaTelefonos;
        this.listaRutasImagenes = listaRutasImagenes;
        this.listaHorarios = listaHorarios;
    }
}
