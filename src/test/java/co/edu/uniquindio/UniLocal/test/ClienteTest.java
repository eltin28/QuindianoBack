package co.edu.uniquindio.UniLocal.test;


/*
import co.edu.uniquindio.Modelos.documentos.Cliente;
import co.edu.uniquindio.Repositorio.ClienteRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ClienteTest {
    @Autowired
    private ClienteRepo clienteRepo;



    @Test
    public void registrarClienteTest(){
//Creamos el cliente con sus propiedades
        Cliente cliente = Cliente.builder()
                .cedula("1213444")
                .nombre("Frank Soto")
                .email("frank@email.com")
                .telefono( List.of("12121", "232323") ).build();
//Guardamos el cliente
        Cliente registro = clienteRepo.save( cliente );
//Verificamos que se haya guardado validando que no sea null
        Assertions.assertNotNull(registro);
    }


    @Test
    public void actualizarClienteTest(){
//Obtenemos el cliente con el id XXXXXXX
        Cliente cliente = clienteRepo.findById("660de1f977e03042d206a561").orElseThrow();
//Modificar el email del cliente

        cliente.setEmail("nuevoemail@email.com");
//Guardamos el cliente
        clienteRepo.save( cliente );
//Obtenemos el cliente con el id 660de1f977e03042d206a561 nuevamente
        Cliente clienteActualizado = clienteRepo.findById("660de1f977e03042d206a561").orElseThrow();;
//Verificamos que el email se haya actualizado
        Assertions.assertEquals("nuevoemail@email.com", clienteActualizado.getEmail());
    }




    @Test
    public void listarClienteTest(){
//Obtenemos la lista de todos los clientes (por ahora solo tenemos 1)
        List<Cliente> clientes = clienteRepo.findAll();
//Imprimimos los clientes, se hace uso de una función lambda
        clientes.forEach(System.out::println);
//Verificamos que solo exista un cliente
        Assertions.assertEquals(1, clientes.size());
    }


    @Test
    public void eliminarClienteTest(){
//Borramos el cliente con el id 660de1f977e03042d206a561
        clienteRepo.deleteById("660de1f977e03042d206a561");
        //Obtenemos el cliente con el id 660de1f977e03042d206a561
        Cliente cliente = clienteRepo.findById("660de1f977e03042d206a561").orElse(null);
//Verificamos que el cliente no exista
        Assertions.assertNull(cliente);
    }
}
*/
