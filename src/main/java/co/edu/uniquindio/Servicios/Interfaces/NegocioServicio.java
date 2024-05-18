package co.edu.uniquindio.Servicios.Interfaces;


import co.edu.uniquindio.Modelos.enums.CategoriaNegocio;
import co.edu.uniquindio.Modelos.enums.EstadoNgocio;
import co.edu.uniquindio.dto.ActualizarNegocioDTO;
import co.edu.uniquindio.dto.AgregarNegocioDTO;
import co.edu.uniquindio.dto.DetalleNegocioDTO;
import co.edu.uniquindio.dto.ItemNegocioDTO;

import java.util.List;

public interface NegocioServicio {
    String agregarNegocio(AgregarNegocioDTO agregarNegocioDTO) throws Exception;

    DetalleNegocioDTO obtenerNegocio(String idNegocio) throws Exception;

    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception;

    void eliminarNegocio(String idNegocio) throws Exception;

    boolean existeNegocio(String idNegocio);

    void rechazarNegocio(String idNegocio) throws Exception;

    List<ItemNegocioDTO> buscarNegociosPorCategoria(CategoriaNegocio categoriaNegocio);

    List<ItemNegocioDTO> buscarNegociosPorNombreSimilar(String nombre);

    List<ItemNegocioDTO> filtrarPorEstado(EstadoNgocio estadoNegocio);

    List<ItemNegocioDTO> listarNegociosPropietario(String idPropietario) throws Exception;

    void aprobarNegocio(String idNegocio) throws Exception;

}
