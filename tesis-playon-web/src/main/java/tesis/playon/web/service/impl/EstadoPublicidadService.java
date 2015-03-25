/**
 * 
 */
package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IEstadoPublicidadDao;
import tesis.playon.web.model.EstadoPublicidad;
import tesis.playon.web.service.IEstadoPublicidadService;

/**
 * @author pablo
 *
 */
@Transactional(readOnly = true)
public class EstadoPublicidadService implements IEstadoPublicidadService {

    IEstadoPublicidadDao estadoPublicidadDao;
    
    /* (non-Javadoc)
     * @see tesis.playon.web.service.IEstadoPublicidadService#save(tesis.playon.web.model.EstadoPublicidad)
     */
    @Override
    @Transactional(readOnly = false)
    public void save(EstadoPublicidad estadoPublicidad) {
	// TODO Auto-generated method stub
	getEstadoPublicidadDao().save(estadoPublicidad);
    }

    /* (non-Javadoc)
     * @see tesis.playon.web.service.IEstadoPublicidadService#update(tesis.playon.web.model.EstadoPublicidad)
     */
    @Override
    @Transactional(readOnly = false)
    public void update(EstadoPublicidad estadoPublicidad) {
	// TODO Auto-generated method stub
	getEstadoPublicidadDao().update(estadoPublicidad);
    }

    /* (non-Javadoc)
     * @see tesis.playon.web.service.IEstadoPublicidadService#delete(tesis.playon.web.model.EstadoPublicidad)
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(EstadoPublicidad estadoPublicidad) {
	// TODO Auto-generated method stub
	getEstadoPublicidadDao().delete(estadoPublicidad);
    }

    /* (non-Javadoc)
     * @see tesis.playon.web.service.IEstadoPublicidadService#findByNombreEstadoPublicidad(java.lang.String)
     */
    @Override
    public EstadoPublicidad findByNombreEstadoPublicidad(String nombreEstadoPublicidad) {
	// TODO Auto-generated method stub
	return getEstadoPublicidadDao().findByNombreEstadoPublicidad(nombreEstadoPublicidad);
    }

    /* (non-Javadoc)
     * @see tesis.playon.web.service.IEstadoPublicidadService#findAll()
     */
    @Override
    public List<EstadoPublicidad> findAll() {
	// TODO Auto-generated method stub
	return getEstadoPublicidadDao().findAll();
    }

    /**
     * @return the estadoPublicidadDao
     */
    public IEstadoPublicidadDao getEstadoPublicidadDao() {
        return estadoPublicidadDao;
    }

    /**
     * @param estadoPublicidadDao the estadoPublicidadDao to set
     */
    public void setEstadoPublicidadDao(IEstadoPublicidadDao estadoPublicidadDao) {
        this.estadoPublicidadDao = estadoPublicidadDao;
    }

}
