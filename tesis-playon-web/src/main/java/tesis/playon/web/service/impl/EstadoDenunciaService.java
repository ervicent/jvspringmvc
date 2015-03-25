package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IEstadoDenunciaDao;
import tesis.playon.web.model.EstadoDenuncia;
import tesis.playon.web.service.IEstadoDenunciaService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class EstadoDenunciaService implements IEstadoDenunciaService {

    IEstadoDenunciaDao EstadoDenunciaDao;

    @Transactional(readOnly = false)
    @Override
    public void save(EstadoDenuncia EstadoDenuncia) {
	getEstadoDenunciaDao().save(EstadoDenuncia);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(EstadoDenuncia EstadoDenuncia) {
	getEstadoDenunciaDao().update(EstadoDenuncia);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(EstadoDenuncia EstadoDenuncia) {
	getEstadoDenunciaDao().delete(EstadoDenuncia);
    }

    @Override
    public List<EstadoDenuncia> findAll() {
	return getEstadoDenunciaDao().findAll();
    }

    @Override
    public EstadoDenuncia findByNombreEstadoDenuncia(String nombreEstadoDenuncia) {
	return getEstadoDenunciaDao().findByNombreEstadoDenuncia(nombreEstadoDenuncia);
    }

    public IEstadoDenunciaDao getEstadoDenunciaDao() {
	return EstadoDenunciaDao;
    }

    public void setEstadoDenunciaDao(IEstadoDenunciaDao EstadoDenunciaDao) {
	this.EstadoDenunciaDao = EstadoDenunciaDao;
    }

}