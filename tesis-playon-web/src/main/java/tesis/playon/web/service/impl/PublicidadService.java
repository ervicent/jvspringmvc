/**
 * 
 */
package tesis.playon.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IPublicidadDao;
import tesis.playon.web.model.EstadoPublicidad;
import tesis.playon.web.model.Publicidad;
import tesis.playon.web.service.IPublicidadService;

/**
 * @author pablo
 *
 */
@Transactional(readOnly = true)
public class PublicidadService implements IPublicidadService {

    IPublicidadDao publicidadDao;
    
    /* (non-Javadoc)
     * @see tesis.playon.web.service.IPublicidadService#save(tesis.playon.web.model.Publicidad)
     */
    @Override
    @Transactional(readOnly = false)
    public void save(Publicidad publicidad) {
	// TODO Auto-generated method stub
	getPublicidadDao().save(publicidad);
    }

    /* (non-Javadoc)
     * @see tesis.playon.web.service.IPublicidadService#update(tesis.playon.web.model.Publicidad)
     */
    @Transactional(readOnly = false)
    @Override
    public void update(Publicidad publicidad) {
	// TODO Auto-generated method stub
	getPublicidadDao().update(publicidad);
    }

    /* (non-Javadoc)
     * @see tesis.playon.web.service.IPublicidadService#delete(tesis.playon.web.model.Publicidad)
     */
    @Transactional(readOnly = false)
    @Override
    public void delete(Publicidad publicidad) {
	// TODO Auto-generated method stub
	getPublicidadDao().delete(publicidad);
    }

    /* (non-Javadoc)
     * @see tesis.playon.web.service.IPublicidadService#findAll()
     */
    @Override
    public List<Publicidad> findAll() {
	// TODO Auto-generated method stub
	return getPublicidadDao().findAll();
    }
    
    @Override
    public List<Publicidad> findAllByEstadoVigente() {
	return getPublicidadDao().findAllByEstadoVigente();
    }
    
    @Override
    public List<Publicidad> findByEstado(EstadoPublicidad estado){
	return getPublicidadDao().findByEstado(estado);
    }
    
    @Override
    public List<String[]> getMontosDePublicidadByPeriodo(Date fechaDesde, Date fechaHasta){
	return getPublicidadDao().getMontosDePublicidadByPeriodo(fechaDesde, fechaHasta);
    }

    /**
     * @return the publicidadDao
     */
    public IPublicidadDao getPublicidadDao() {
        return publicidadDao;
    }

    /**
     * @param publicidadDao the publicidadDao to set
     */
    public void setPublicidadDao(IPublicidadDao publicidadDao) {
        this.publicidadDao = publicidadDao;
    }
}
