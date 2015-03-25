package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IEstadoPlayaDao;
import tesis.playon.web.model.EstadoPlaya;
import tesis.playon.web.service.IEstadoPlayaService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class EstadoPlayaService implements IEstadoPlayaService {

    IEstadoPlayaDao estadoPlayaDao;
    
    @Transactional(readOnly = false)
    @Override
    public void save(EstadoPlaya estadoPlaya) {
	getEstadoPlayaDao().save(estadoPlaya);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(EstadoPlaya estadoPlaya) {
	getEstadoPlayaDao().update(estadoPlaya);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(EstadoPlaya estadoPlaya) {
	getEstadoPlayaDao().delete(estadoPlaya);
    }

    @Override
    public List<EstadoPlaya> findAll() {
	return getEstadoPlayaDao().findAll();
    }

    @Override
    public EstadoPlaya findByNombreEstadoPlaya(String nombreEstadoPlaya) {
	return getEstadoPlayaDao().findByNombreEstadoPlaya(nombreEstadoPlaya);
    }

    public IEstadoPlayaDao getEstadoPlayaDao() {
        return estadoPlayaDao;
    }

    public void setEstadoPlayaDao(IEstadoPlayaDao estadoPlayaDao) {
        this.estadoPlayaDao = estadoPlayaDao;
    }

}