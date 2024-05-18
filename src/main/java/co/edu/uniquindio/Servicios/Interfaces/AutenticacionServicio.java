package co.edu.uniquindio.Servicios.Interfaces;

import co.edu.uniquindio.dto.LoginDTO;
import co.edu.uniquindio.dto.TokenDTO;

public interface AutenticacionServicio {


    TokenDTO iniciarSesionCliente(LoginDTO loginDTO) throws Exception;

    TokenDTO iniciarSesionModerador(LoginDTO loginDTO) throws Exception;



}
