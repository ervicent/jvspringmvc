package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.Usuario;

/**
 * @author Pablo
 * 
 */
public interface IClienteDao {

    void save(Cliente cliente);

    void update(Cliente cliente);

    void delete(Cliente cliente);

    Cliente findByNumeroCliente(Integer numeroCliente);

    Cliente findByNombreUsuario(String nombreUser);

    Cliente findByIdUsuario(Usuario usuario);

    List<Cliente> findAll();
}
