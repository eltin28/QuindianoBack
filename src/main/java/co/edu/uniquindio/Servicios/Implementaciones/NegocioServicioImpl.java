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
        Lugar lugar = new Lugar();

        //Se asignan los datos
        lugar.setCodigoNegocio(agregarNegocioDTO.codigoCliente());
        lugar.setNombre(agregarNegocioDTO.nombreNegocio());
        lugar.setDescripcion(agregarNegocioDTO.descripcion());
        lugar.setCategoriaNegocio(agregarNegocioDTO.categoriaNegocio());
        lugar.setListaTelefonos(agregarNegocioDTO.listaTelefonos());
        lugar.setListaRutasImagenes(agregarNegocioDTO.listaImagenesNegocio());
        lugar.setListaHorarios(agregarNegocioDTO.listaHorarios());
        lugar.setUbicacion(agregarNegocioDTO.ubicacion());
        lugar.setEstadoNegocio(EstadoNgocio.PENDIENTE);
        lugar.setEstado(Estado.INACTIVO);

        //Se guarda en la base de datos
        Lugar negocioGuardado = negocioRepo.save(lugar);

        //Se obtiene el código del negocio guardado
        return negocioGuardado.getCodigoNegocio();

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
        lugar.setEstadoNegocio(EstadoNgocio.PENDIENTE);

        //Como el objeto cliente ya tiene un id, el save() no crea un nuevo registro sino que
        // actualiza el que ya existe
        negocioRepo.save(lugar);
    }

    @Override
    public void eliminarNegocio(String codigoNegocio) throws Exception {

        Lugar lugar = obtenerNegocioID(codigoNegocio);

        lugar.setEstado(Estado.INACTIVO);

        negocioRepo.save(lugar);
    }

    @Override
    public void rechazarNegocio(String codigoNegocio) throws Exception {

        Lugar lugar = obtenerNegocioID(codigoNegocio);

        lugar.setEstado(Estado.RECHAZADO);

        negocioRepo.save(lugar);
    }

    @Override
    public void aprobarNegocio(String codigoNegocio) throws Exception {

        Lugar lugar = obtenerNegocioID(codigoNegocio);

        if (lugar.getEstado().equals(Estado.APROBADO)) {
            throw new Exception("El negocio ya se encuentra aprobado ");
        }

        lugar.setEstado(Estado.APROBADO);

        //Se actualiza el negocio en la base de datos
        negocioRepo.save(lugar);
    }

    @Override
    public DetalleNegocioDTO obtenerNegocio(String codigoNegocio) throws Exception {

        Lugar lugar = obtenerNegocioID(codigoNegocio);

        return new DetalleNegocioDTO(
                lugar.getCodigoNegocio(),
                lugar.getCodigoCliente(),
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


    @Override
    public List<ItemNegocioDTO> buscarNegociosPorNombreSimilar(String nombre) {

        List<Lugar> lugar = negocioRepo.findAllByNombreLikeIgnoreCase(nombre);

        return listarNegocios(lugar);
    }

    @Override
    public List<ItemNegocioDTO> filtrarPorEstado(EstadoNgocio estadoNegocio) {

        List<Lugar> lugar = negocioRepo.findAllByEstadoNegocio(estadoNegocio);

        //Creamos una lista de DTOs de negocios

        return listarNegocios(lugar);

    }

    @Override
    public List<ItemNegocioDTO> listarNegociosPropietario(String idPropietario) throws Exception {
        return negocioRepo.findAllBycodigoCliente(idPropietario);
    }

    //Metodos para verificar existencia de datos
    @Override
    public boolean existeNegocio(String codigoNegocio) {
        return negocioRepo.findById(codigoNegocio).isPresent();
    }


    private Lugar obtenerNegocioID(String codigoNegocio) throws ResourceNotFoundException {

        Optional<Lugar> optionalNegocio = negocioRepo.findById(codigoNegocio);

        if (optionalNegocio.isEmpty()) {
            throw new ResourceNotFoundException(codigoNegocio);
        }

        return optionalNegocio.get();
    }

    private List<ItemNegocioDTO> listarNegocios(List<Lugar> lugares) {

        //Creamos una lista de DTOs de negocios
        List<ItemNegocioDTO> itemNegocioDTOS = new ArrayList<>();

        for (Lugar lugar : lugares) {
            if (lugar.getEstado().equals(Estado.ACTIVO) &&
                    lugar.getEstadoNegocio().equals(Estado.APROBADO)) {
                itemNegocioDTOS.add(
                        new ItemNegocioDTO(
                                lugar.getCodigoNegocio(),
                                lugar.getCodigoCliente(),
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
