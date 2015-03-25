/**
 * 
 */
package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.Usuario;

/**
 * @author pablo
 *
 */
public interface IClienteService {

    void save(Cliente cliente);

    void update(Cliente cliente);

    void delete(Cliente cliente);

    Cliente findByNumeroCliente(Integer numeroCliente);
    
    List<Cliente> findAll();
    
    Cliente findByNombreUsuario(String nombreUser);
    
    Cliente findByUsuario(Usuario usuario);
}
