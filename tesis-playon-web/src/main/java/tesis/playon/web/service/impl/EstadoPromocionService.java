package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IEstadoPromocionDao;
import tesis.playon.web.model.EstadoPromocion;
import tesis.playon.web.service.IEstadoPromocionService;

/**
 * 
 * @author pablo
 *
 */
@Transactional(readOnly = true)
public class EstadoPromocionService implements IEstadoPromocionService {

    IEstadoPromocionDao estadoPromocionDao;
    
    @Override
    @Transactional(readOnly = false)
    public void save(EstadoPromocion estadoPromocion) {
	// TODO Auto-generated method stub
	getEstadoPromocionDao().save(estadoPromocion);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(EstadoPromocion estadoPromocion) {
	// TODO Auto-generated method stub
	getEstadoPromocionDao().update(estadoPromocion);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(EstadoPromocion estadoPromocion) {
	// TODO Auto-generated method stub
	getEstadoPromocionDao().delete(estadoPromocion);
    }

    @Override
    public EstadoPromocion findByNombreEstadoPromocion(String nombreEstadoPromocion) {
	// TODO Auto-generated method stub
	return getEstadoPromocionDao().findByNombreEstadoPromocion(nombreEstadoPromocion);
    }

    @Override
    public List<EstadoPromocion> findAll() {
	// TODO Auto-generated method stub
	return getEstadoPromocionDao().findAll();
    }

    public IEstadoPromocionDao getEstadoPromocionDao() {
        return estadoPromocionDao;
    }

    public void setEstadoPromocionDao(IEstadoPromocionDao estadoPromocionDao) {
        this.estadoPromocionDao = estadoPromocionDao;
    }

}
