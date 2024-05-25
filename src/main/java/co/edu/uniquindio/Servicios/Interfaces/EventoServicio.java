package co.edu.uniquindio.Servicios.Interfaces;

import co.edu.uniquindio.dto.ActualizarEventoDTO;
import co.edu.uniquindio.dto.AgregarEventoDTO;
import co.edu.uniquindio.dto.DetalleEventoDTO;
import co.edu.uniquindio.dto.ItemEventoDTO;

import java.util.List;

public interface EventoServicio {

    String agregarEvento(AgregarEventoDTO agregarEventoDTO) throws Exception;

    void actualizarEvento(ActualizarEventoDTO actualizarEventoDTO) throws Exception;

    void terminarEvento(String codigoEvento) throws Exception;

    List<ItemEventoDTO> listarEventosNegocio(String idNegocio) throws Exception;

    DetalleEventoDTO obtenerEvento(String idEvento) throws Exception;
}
