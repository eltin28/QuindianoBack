package co.edu.uniquindio.Servicios.Implementaciones;
import co.edu.uniquindio.Modelos.documentos.Cliente;
import co.edu.uniquindio.Modelos.enums.Estado;
import co.edu.uniquindio.Repositorio.ClienteRepo;
import co.edu.uniquindio.Servicios.Interfaces.ClienteServicio;
import co.edu.uniquindio.Servicios.Interfaces.CorreoServicio;
import co.edu.uniquindio.Servicios.Interfaces.NegocioServicio;
import co.edu.uniquindio.dto.*;
import co.edu.uniquindio.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClienteServicioImpl implements ClienteServicio {

    private final ClienteRepo clienteRepo;
    private final NegocioServicio negocioServicio;
    private final CorreoServicio correoServicio;

    @Override
    public String registrarCliente(RegistroClienteDTO registroClienteDTO) throws Exception {

        if (existeEmail(registroClienteDTO.email())) {
            throw new Exception("El correo ya se encuentra creado");
        }

        if (existeNickname(registroClienteDTO.nickname())) {
            throw new Exception("El id nombre se encuentra registrado por otro usuario");
        }

        //Se crea el objeto Cliente
        Cliente cliente = new Cliente();

        //Se le asignan sus campos
        cliente.setNombre(registroClienteDTO.nombre());
        cliente.setNickname(registroClienteDTO.nickname());
        cliente.setCiudad(registroClienteDTO.ciudadResidencia());
        cliente.setFotoPerfil(registroClienteDTO.fotoPerfil());
        cliente.setEmail(registroClienteDTO.email());
        cliente.setEstado(Estado.ACTIVO);

        //Para la contraseña
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode(registroClienteDTO.password());
        cliente.setPassword(passwordEncriptada);

        //Se guarda en la base de datos y obtenemos el objeto registrado
        Cliente clienteGuardado = clienteRepo.save(cliente);


        correoServicio.enviarCorreo(new EmailDTO(
                "Bienvenido a Unilocal",
                "Bienvenido a unilocal " + registroClienteDTO.nickname() + " disfruta de tu instancia :)",
                registroClienteDTO.email()
        ));

        //Retornamos el id (código) del cliente registrado
        return clienteGuardado.getCodigo();
    }

    @Override
    public String agregarNegocioFavorito(@Valid AgregarNegocioFavoritosDTO agregarNegocioFavoritosDTO) throws Exception {

        // se busca el cliente para su negocio favorito
        Cliente cliente = obtenerClienteID(agregarNegocioFavoritosDTO.idCliente());

        /*
        Validacion para cuando el negocio es null
         */
        if (!negocioServicio.existeNegocio(agregarNegocioFavoritosDTO.idNegocio())) {
            throw new ResourceNotFoundException(agregarNegocioFavoritosDTO.idNegocio());
        }
        if (negocioServicio.obtenerNegocio(agregarNegocioFavoritosDTO.idNegocio()).estadoNegocio() != Estado.APROBADO && negocioServicio.obtenerNegocio(agregarNegocioFavoritosDTO.idNegocio()).estadoRegistro() == Estado.INACTIVO){
            throw new Exception("No se puede añadir a favoritos un negocio no aprobado o inactivo");
        }
        if (cliente.getEstado() == Estado.INACTIVO){
            throw new Exception("Un usuario inactivo no puede agregar negocios a favoritos");
        }
        //En caso de ya estar en la lista de favoritos, se elimina
        if (cliente.getListaNegociosFavoritos().contains(agregarNegocioFavoritosDTO.idNegocio())) {
            cliente.getListaNegociosFavoritos().remove(agregarNegocioFavoritosDTO.idNegocio());
        }
        //En caso contrario, se quita de la lista
        else {
            cliente.getListaNegociosFavoritos().add(agregarNegocioFavoritosDTO.idNegocio());
        }

        //Se actualiza la información del cliente en el repositorio
        clienteRepo.save(cliente);

        //Se retorna el código del negocio para verificar en los tests
        return agregarNegocioFavoritosDTO.idNegocio();

    }

    @Override
    public void editarPerfil(ActualizarClienteDTO actualizarClienteDTO) throws Exception {
        if (existeEmail(actualizarClienteDTO.email())) {
            throw new Exception("El email ya está en uso");
        }

        //Obtenemos el cliente que se quiere actualizar y le asignamos los nuevos valores (el
        //nickname no se puede cambiar)
        Cliente cliente = obtenerClienteID(actualizarClienteDTO.id());

        if (cliente.getEstado() == Estado.INACTIVO){
            throw new Exception("Un usuario inactivo no puede editar el perfil");
        }
        cliente.setNombre(actualizarClienteDTO.nombre());
        cliente.setFotoPerfil(actualizarClienteDTO.fotoPerfil());
        cliente.setEmail(actualizarClienteDTO.email());
        cliente.setCiudad(actualizarClienteDTO.ciudadResidencia());

        //Como el objeto cliente ya tiene un id, el save() no crea un nuevo registro sino que
        // actualiza el que ya existe
        clienteRepo.save(cliente);
    }

    @Override
    public DetalleClienteDTO obtenerCliente(String idCliente) throws Exception {

        Cliente cliente = obtenerClienteID(idCliente);

        if (cliente.getEstado().equals(Estado.INACTIVO)) {
            throw new Exception("El cliente con el id " + idCliente + " tiene su cuenta inactiva");
        }

        //Retornamos el cliente en formato DTO
        return new DetalleClienteDTO(cliente.getCodigo(), cliente.getNombre(),
                cliente.getFotoPerfil(), cliente.getNickname(), cliente.getEmail(),
                cliente.getCiudad(), cliente.getListaNegociosFavoritos(),cliente.getEstado());
    }

    @Override
    public boolean existeCliente(String idCliente) {
        return clienteRepo.findById(idCliente).isPresent();
    }

    @Override
    public List<ItemClienteDTO> listarClientes() {

        //Obtenemos todos los clientes de la base de datos
        List<Cliente> clientes = clienteRepo.findAll();

        //Creamos una lista de DTOs de clientes
        List<ItemClienteDTO> listaItemClienteDTO = new ArrayList<>();

        //Recorremos la lista de clientes y por cada uno creamos un DTO y lo agregamos a la lista
        for (Cliente cliente : clientes) {
            //Se verifica que la cuenta del cliente esté activa, para listarlo
            if (cliente.getEstado().equals(Estado.ACTIVO)) {
                listaItemClienteDTO.add(new ItemClienteDTO(cliente.getCodigo(),
                        cliente.getNombre(), cliente.getFotoPerfil(), cliente.getNickname(),
                        cliente.getEmail(), cliente.getCiudad(),
                        cliente.getListaNegociosFavoritos()));
            }
        }
        return listaItemClienteDTO;
    }


    @Override
    public void eliminarCliente(String idCuenta) throws Exception {

        Cliente cliente = obtenerClienteID(idCuenta);

        cliente.setEstado(Estado.INACTIVO);

        //Como el objeto cliente ya tiene un id, el save() no crea un nuevo registro
        // sino que actualiza el que ya existe
        clienteRepo.save(cliente);
    }


    @Override
    public void iniciarSesion(SesionDTO sesionDTO) throws Exception {

    }

    @Override
    public void eliminarCuenta(String idCuenta) throws Exception {

    }

    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {

    }

    @Override
    public void cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception {
        Cliente cliente = obtenerClienteID(cambioPasswordDTO.id());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(cambioPasswordDTO.passwordAntigua(), cliente.getPassword())) {
            throw new Exception("La contraseña es incorrecta");
        }
        String passwordEncriptada = passwordEncoder.encode(cambioPasswordDTO.passwordNueva());
        cliente.setPassword(passwordEncriptada);

        clienteRepo.save(cliente);
    }


    //Metodos para verificar existencia de datos
    public Cliente obtenerClienteID(String idCliente) throws ResourceNotFoundException {
        Optional<Cliente> optionalCliente = clienteRepo.findById(idCliente);

        if (optionalCliente.isEmpty()) {
            throw new ResourceNotFoundException(idCliente);
        }

        return optionalCliente.get();
    }


    private boolean existeEmail(String email) {
        return clienteRepo.findByEmail(email).isPresent();
    }

    private boolean existeNickname(String nickname) {
        return clienteRepo.findByNickname(nickname).isPresent();
    }}

