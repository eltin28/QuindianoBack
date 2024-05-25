package co.edu.uniquindio.Servicios.Interfaces;

import co.edu.uniquindio.Modelos.documentos.Lugar;
import co.edu.uniquindio.dto.EmailDTO;

public interface CorreoServicio {

    void enviarCorreo(EmailDTO emailDTO) throws Exception;

}
