package co.edu.uniquindio.Controladores;

import co.edu.uniquindio.Servicios.Interfaces.ImagenesServicio;
import co.edu.uniquindio.dto.ImagenDTO;
import co.edu.uniquindio.dto.MensajeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/imagenes")
@RequiredArgsConstructor
public class ImagenesController {
    private final ImagenesServicio imagenesServicio;

    @PostMapping("/subir")
    public ResponseEntity<MensajeDTO<Map>> subir(@RequestParam("file") MultipartFile imagen)
            throws Exception{
        Map respuesta = imagenesServicio.subirImagen(imagen);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, respuesta ));
    }
    @DeleteMapping("/eliminar")
    public ResponseEntity<MensajeDTO<Map>> eliminar(@RequestBody ImagenDTO imagenDTO) throws
            Exception{
        Map respuesta = imagenesServicio.eliminarImagen( imagenDTO.id() );
        return ResponseEntity.ok().body(new MensajeDTO<>(false, respuesta ));
    }
}