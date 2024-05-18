package co.edu.uniquindio.Servicios.Interfaces;
import co.edu.uniquindio.Modelos.documentos.Lugar;
import co.edu.uniquindio.Modelos.documentos.Moderador;
import co.edu.uniquindio.dto.LugarDTO;
import co.edu.uniquindio.dto.ModeradorDTO;

import java.util.List;

public interface ModeradorServicio { ModeradorDTO buscarPorEmail(String email);


    void actualizarModerador(ModeradorDTO actualizarModeradorDTO) throws Exception;

    boolean existeModerador(String codigoModerador);
}
