package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.ICuentaClienteDao;
import tesis.playon.web.model.CuentaCliente;
import tesis.playon.web.service.ICuentaClienteService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class CuentaClienteService implements ICuentaClienteService {

    ICuentaClienteDao cuentaClienteDao;

    @Transactional(readOnly = false)
    @Override
    public void save(CuentaCliente cuentaCliente) {
	getCuentaClienteDao().save(cuentaCliente);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(CuentaCliente cuentaCliente) {
	getCuentaClienteDao().update(cuentaCliente);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(CuentaCliente cuentaCliente) {
	getCuentaClienteDao().delete(cuentaCliente);
    }

    @Override
    public List<CuentaCliente> findAll() {
	return getCuentaClienteDao().findAll();
    }

    @Override
    public CuentaCliente findByNroCuentaCliente(Integer nroCuentaCliente) {
	return getCuentaClienteDao().findByNroCuenta(nroCuentaCliente);
    }

    public ICuentaClienteDao getCuentaClienteDao() {
	return cuentaClienteDao;
    }

    public void setCuentaClienteDao(ICuentaClienteDao cuentaClienteDao) {
	this.cuentaClienteDao = cuentaClienteDao;
    }

}