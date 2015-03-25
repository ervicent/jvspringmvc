package tesis.playon.web.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.ILocalidadDao;
import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.Localidad;
import tesis.playon.web.service.ILocalidadService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class LocalidadService implements ILocalidadService {

    ILocalidadDao localidadDao;

    @Transactional(readOnly = false)
    @Override
    public void save(Localidad localidad) {
	getLocalidadDao().save(localidad);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(Localidad localidad) {
	getLocalidadDao().update(localidad);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Localidad localidad) {
	getLocalidadDao().delete(localidad);
    }

    @Override
    public List<Localidad> findAll() {
	return getLocalidadDao().findAll();
    }

    @Override
    public Localidad findByNombreLocalidad(String nombreLocalidad) {
	return getLocalidadDao().findByNombreLocalidad(nombreLocalidad);
    }
    
    @Override
    public Set<Barrio> findBarrio(Localidad localidad){
	return getLocalidadDao().findBarrio(localidad);
    }

    public ILocalidadDao getLocalidadDao() {
	return localidadDao;
    }

    public void setLocalidadDao(ILocalidadDao localidadDao) {
	this.localidadDao = localidadDao;
    }
}