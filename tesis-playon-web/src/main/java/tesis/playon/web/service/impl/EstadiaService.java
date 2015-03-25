package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IEstadiaDao;
import tesis.playon.web.model.Estadia;
import tesis.playon.web.model.Playa;
import tesis.playon.web.service.IEstadiaService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class EstadiaService implements IEstadiaService {

    IEstadiaDao estadiaDao;

    @Transactional(readOnly = false)
    @Override
    public void save(Estadia estadia) {
	getEstadiaDao().save(estadia);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(Estadia estadia) {
	getEstadiaDao().update(estadia);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Estadia estadia) {
	getEstadiaDao().delete(estadia);
    }

    @Override
    public List<Estadia> findAll() {
	return getEstadiaDao().findAll();
    }

    @Override
    public Estadia findByPlaya(Playa playa) {
	return getEstadiaDao().findByPlaya(playa);
    }

    public IEstadiaDao getEstadiaDao() {
	return estadiaDao;
    }

    public void setEstadiaDao(IEstadiaDao estadiaDao) {
	this.estadiaDao = estadiaDao;
    }

}