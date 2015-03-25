/**
 * 
 */
package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IClienteDao;
import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IClienteService;

/**
 * @author pablo
 * 
 */
@Transactional(readOnly = true)
public class ClienteService implements IClienteService {

    IClienteDao clienteDao;

    @Transactional(readOnly = false)
    @Override
    public void save(Cliente cliente) {
	getClienteDao().save(cliente);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(Cliente cliente) {
	getClienteDao().update(cliente);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Cliente cliente) {
	getClienteDao().delete(cliente);
    }

    @Override
    public List<Cliente> findAll() {
	return getClienteDao().findAll();
    }

    @Override
    public Cliente findByNumeroCliente(Integer numeroCliente) {
	return getClienteDao().findByNumeroCliente(numeroCliente);
    }

    @Override
    public Cliente findByUsuario(Usuario usuario) {
	return getClienteDao().findByIdUsuario(usuario);
    }

    @Override
    public Cliente findByNombreUsuario(String nombreUser) {
	return getClienteDao().findByNombreUsuario(nombreUser);
    }

    public IClienteDao getClienteDao() {
	return clienteDao;
    }

    public void setClienteDao(IClienteDao clienteDao) {
	this.clienteDao = clienteDao;
    }
}