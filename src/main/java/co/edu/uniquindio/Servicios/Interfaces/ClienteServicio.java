package co.edu.uniquindio.Servicios.Interfaces;

import co.edu.uniquindio.dto.*;
import jakarta.validation.Valid;

import java.util.List;

public interface ClienteServicio extends CuentaServicio {


    String registrarCliente(RegistroClienteDTO registroClienteDTO) throws Exception;

    void editarPerfil(ActualizarClienteDTO actualizarClienteDTO) throws Exception;

    void eliminarCliente(String idCuenta) throws Exception;

    DetalleClienteDTO obtenerCliente(String idCliente) throws Exception;

    boolean existeCliente(String idCliente);

    List<ItemClienteDTO> listarClientes();

    String agregarNegocioFavorito(@Valid AgregarNegocioFavoritosDTO FavoritoNegoDTO) throws Exception;

    void cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception;

}
