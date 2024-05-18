package co.edu.uniquindio.Servicios.Implementaciones;


import co.edu.uniquindio.Modelos.documentos.Lugar;
import co.edu.uniquindio.Modelos.enums.CategoriaNegocio;
import co.edu.uniquindio.Modelos.enums.Estado;
import co.edu.uniquindio.Modelos.enums.EstadoNgocio;
import co.edu.uniquindio.Repositorio.NegocioRepo;
import co.edu.uniquindio.Servicios.Interfaces.ClienteServicio;
import co.edu.uniquindio.Servicios.Interfaces.NegocioServicio;
import co.edu.uniquindio.dto.ActualizarNegocioDTO;
import co.edu.uniquindio.dto.AgregarNegocioDTO;
import co.edu.uniquindio.dto.DetalleNegocioDTO;
import co.edu.uniquindio.dto.ItemNegocioDTO;
import co.edu.uniquindio.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class NegocioServicioImpl implements NegocioServicio {

    private final NegocioRepo negocioRepo;
    private ClienteServicio clienteServicio;

    @Override
    public String agregarNegocio(AgregarNegocioDTO agregarNegocioDTO) throws Exception {
        if (!clienteServicio.existeCliente(agregarNegocioDTO.codigoCliente())){
            throw new ResourceNotFoundException(agregarNegocioDTO.codigoCliente());
        }
        //Se crea el negocio
        Lugar negocio = new Lugar();

        //Se asignan los datos
        negocio.setId(agregarNegocioDTO.codigoCliente());
        negocio.setNombre(agregarNegocioDTO.nombreNegocio());
        negocio.setDescripcion(agregarNegocioDTO.descripcion());
        negocio.setCategoriaNegocio(agregarNegocioDTO.categoriaNegocio());
        negocio.setListaTelefonos(agregarNegocioDTO.listaTelefonos());
        negocio.setListaRutasImagenes(agregarNegocioDTO.listaImagenesNegocio());
        negocio.setListaHorarios(agregarNegocioDTO.listaHorarios());
        negocio.setUbicacion(agregarNegocioDTO.ubicacion());
        negocio.setEstado(Estado.ESPERA);
        negocio.setEstado(Estado.INACTIVO);

        //Se guarda en la base de datos
        Lugar negocioGuardado = negocioRepo.save(negocio);

        //Se obtiene el código del negocio guardado
        return negocioGuardado.getId();

    }

    @Override
    public void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception {

        Lugar lugar = obtenerNegocioID(actualizarNegocioDTO.codigoNegocio());

        lugar.setNombre(actualizarNegocioDTO.nombreNegocio());
        lugar.setDescripcion(actualizarNegocioDTO.descripcion());
        lugar.setCategoriaNegocio(actualizarNegocioDTO.categoriaNegocio());
        lugar.setUbicacion(actualizarNegocioDTO.ubicacion());
        lugar.setListaRutasImagenes(actualizarNegocioDTO.listaImagenesNegocio());
        lugar.setListaTelefonos(actualizarNegocioDTO.listaTelefonos());
        lugar.setListaHorarios(actualizarNegocioDTO.listaHorarios());
        lugar.setEstado(Estado.ESPERA);

        //Como el objeto cliente ya tiene un id, el save() no crea un nuevo registro sino que
        // actualiza el que ya existe
        negocioRepo.save(lugar);
    }

    @Override
    public void eliminarNegocio(String idNegocio) throws Exception {

        Lugar lugar = obtenerNegocioID(idNegocio);

        lugar.setEstado(Estado.INACTIVO);

        negocioRepo.save(lugar);
    }

    @Override
    public void rechazarNegocio(String idNegocio) throws Exception {

        Lugar lugar = obtenerNegocioID(idNegocio);

        lugar.setEstado(Estado.RECHAZADO);

        negocioRepo.save(lugar);
    }

    @Override
    public void aprobarNegocio(String idNegocio) throws Exception {

        Lugar lugar = obtenerNegocioID(idNegocio);

        if (lugar.getEstado().equals(Estado.APROBADO)) {
            throw new Exception("El negocio ya se encuentra aprobado ");
        }

        lugar.setEstado(Estado.APROBADO);

        //Se actualiza el negocio en la base de datos
        negocioRepo.save(lugar);
    }

    @Override
    public DetalleNegocioDTO obtenerNegocio(String idNegocio) throws Exception {

        Lugar lugar = obtenerNegocioID(idNegocio);

        return new DetalleNegocioDTO(
                lugar.getId(),
                lugar.getIdCliente(),
                lugar.getNombre(),
                lugar.getDescripcion(),
                lugar.getCategoriaNegocio(),
                lugar.getEstado(),
                lugar.getUbicacion(),
                lugar.getListaTelefonos(),
                lugar.getListaRutasImagenes(),
                lugar.getListaHorarios(),
                lugar.getEstado()
        );
    }

    @Override
    public List<ItemNegocioDTO> buscarNegociosPorCategoria(CategoriaNegocio categoriaNegocio) {
        List<Lugar> lugar = negocioRepo.findAllByCategoriaNegocio(categoriaNegocio);

        return listarNegocios(lugar);
    }

    //En caso de que no se dé un nombre exacto, busca por aquellos que tengan
    //coincidencias
    @Override
    public List<ItemNegocioDTO> buscarNegociosPorNombreSimilar(String nombre) {

        List<Lugar> Lugares = negocioRepo.findAllByNombreLikeIgnoreCase(nombre);

        //Creamos una lista de DTOs de negocios

        return listarNegocios(Lugares);
    }

    @Override
    public List<ItemNegocioDTO> filtrarPorEstado(EstadoNgocio estadoNegocio) {

        List<Lugar> Lugares = negocioRepo.findAllByEstado(estadoNegocio);

        //Creamos una lista de DTOs de negocios

        return listarNegocios(Lugares);

    }

    @Override
    public List<ItemNegocioDTO> listarNegociosPropietario(String idPropietario) throws Exception {
        return negocioRepo.findAllByidCliente(idPropietario);
    }

    //Metodos para verificar existencia de datos
    @Override
    public boolean existeNegocio(String idNegocio) {
        return negocioRepo.findById(idNegocio).isPresent();
    }


    private Lugar obtenerNegocioID(String idNegocio) throws ResourceNotFoundException {

        Optional<Lugar> optionalNegocio = negocioRepo.findById(idNegocio);

        if (optionalNegocio.isEmpty()) {
            throw new ResourceNotFoundException(idNegocio);
        }

        return optionalNegocio.get();
    }

    private List<ItemNegocioDTO> listarNegocios(List<Lugar> Lugares) {

        //Creamos una lista de DTOs de negocios
        List<ItemNegocioDTO> itemNegocioDTOS = new ArrayList<>();

        for (Lugar lugar : Lugares) {
            if (lugar.getEstado().equals(Estado.ACTIVO) &&
                    lugar.getEstado().equals(Estado.APROBADO)) {
                itemNegocioDTOS.add(
                        new ItemNegocioDTO(
                                lugar.getId(),
                                lugar.getIdCliente(),
                                lugar.getNombre(),
                                lugar.getDescripcion(),
                                lugar.getCategoriaNegocio(),
                                lugar.getEstado(),
                                lugar.getUbicacion(),
                                lugar.getListaTelefonos(),
                                lugar.getListaRutasImagenes(),
                                lugar.getListaHorarios(),
                                lugar.getEstado()
                        )
                );
            }
        }
        return itemNegocioDTOS;
    }
}
