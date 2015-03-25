package tesis.playon.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.ITransaccionClienteDao;
import tesis.playon.web.model.CuentaCliente;
import tesis.playon.web.model.TransaccionCliente;
import tesis.playon.web.service.ITransaccionClienteService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class TransaccionClienteService implements ITransaccionClienteService {

    ITransaccionClienteDao transaccionClienteDao;

    @Transactional(readOnly = false)
    @Override
    public void save(TransaccionCliente TransaccionCliente) {
	getTransaccionClienteDao().save(TransaccionCliente);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(TransaccionCliente TransaccionCliente) {
	getTransaccionClienteDao().update(TransaccionCliente);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(TransaccionCliente TransaccionCliente) {
	getTransaccionClienteDao().delete(TransaccionCliente);
    }

    @Override
    public List<TransaccionCliente> findAll() {
	return getTransaccionClienteDao().findAll();
    }

    public List<TransaccionCliente> findTransaccionesByCuentaCliente(CuentaCliente cuentaCliente) {
	return getTransaccionClienteDao().findTransaccionesByCuentaCliente(cuentaCliente);
    }

    @Override
    public TransaccionCliente findByCuentaCliente(CuentaCliente cuentaCliente) {
	return getTransaccionClienteDao().findByCuentaCliente(cuentaCliente);
    }

    @Override
    public TransaccionCliente getUltimaTransaccion(CuentaCliente cuentaCliente) {
	return getTransaccionClienteDao().getUltimaTransaccion(cuentaCliente);
    }

    public ITransaccionClienteDao getTransaccionClienteDao() {
	return transaccionClienteDao;
    }

    public void setTransaccionClienteDao(ITransaccionClienteDao transaccionClienteDao) {
	this.transaccionClienteDao = transaccionClienteDao;
    }

    public List<TransaccionCliente> findTransaccionesByFecha(CuentaCliente cuentacliente, Date fechaD, Date fechaH) {
	return getTransaccionClienteDao().findTransaccionesByFecha(cuentacliente, fechaD, fechaH);

    }

}