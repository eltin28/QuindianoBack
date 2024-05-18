package co.edu.uniquindio.Servicios.Interfaces;

import co.edu.uniquindio.dto.CambioPasswordDTO;
import co.edu.uniquindio.dto.SesionDTO;

public interface CuentaServicio {
    void iniciarSesion(SesionDTO sesionDTO)throws Exception;
    void eliminarCuenta(String idCuenta)throws Exception;
    void enviarLinkRecuperacion(String email)throws Exception;
    void cambiarPassword(CambioPasswordDTO cambioPasswordDTO)throws Exception;
}
