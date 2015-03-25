package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.ICuentaPlayaDao;
import tesis.playon.web.model.CuentaPlaya;
import tesis.playon.web.model.Playa;
import tesis.playon.web.service.ICuentaPlayaService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class CuentaPlayaService implements ICuentaPlayaService {

    ICuentaPlayaDao cuentaPlayaDao;

    @Transactional(readOnly = false)
    @Override
    public void save(CuentaPlaya cuentaPlaya) {
	getCuentaPlayaDao().save(cuentaPlaya);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(CuentaPlaya cuentaPlaya) {
	getCuentaPlayaDao().update(cuentaPlaya);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(CuentaPlaya cuentaPlaya) {
	getCuentaPlayaDao().delete(cuentaPlaya);
    }

    @Override
    public List<CuentaPlaya> findAll() {
	return getCuentaPlayaDao().findAll();
    }

    @Override
    public CuentaPlaya findByNroCuentaPlaya(Integer nroCuentaPlaya) {
	return getCuentaPlayaDao().findByNroCuenta(nroCuentaPlaya);
    }

    @Override
    public CuentaPlaya findByPlaya(Playa playa) {
	return getCuentaPlayaDao().findByPlaya(playa);
    }

    public ICuentaPlayaDao getCuentaPlayaDao() {
	return cuentaPlayaDao;
    }

    public void setCuentaPlayaDao(ICuentaPlayaDao cuentaPlayaDao) {
	this.cuentaPlayaDao = cuentaPlayaDao;
    }

}