/**
 * 
 */
package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IFotoPublicidadDao;
import tesis.playon.web.model.FotoPublicidad;
import tesis.playon.web.service.IFotoPublicidadService;

/**
 * @author pablo
 *
 */
@Transactional(readOnly = true)
public class FotoPublicidadService implements IFotoPublicidadService {

    IFotoPublicidadDao fotoPublicidadDao;
    
    /* (non-Javadoc)
     * @see tesis.playon.web.service.IFotoPublicidadService#save(tesis.playon.web.model.FotoPublicidad)
     */
    @Transactional(readOnly = false)
    @Override
    public void save(FotoPublicidad foto) {
	// TODO Auto-generated method stub
	getFotoPublicidadDao().save(foto);
    }

    /* (non-Javadoc)
     * @see tesis.playon.web.service.IFotoPublicidadService#update(tesis.playon.web.model.FotoPublicidad)
     */
    @Transactional(readOnly = false)
    @Override
    public void update(FotoPublicidad foto) {
	// TODO Auto-generated method stub
	getFotoPublicidadDao().update(foto);
    }

    /* (non-Javadoc)
     * @see tesis.playon.web.service.IFotoPublicidadService#delete(tesis.playon.web.model.FotoPublicidad)
     */
    @Transactional(readOnly = false)
    @Override
    public void delete(FotoPublicidad foto) {
	// TODO Auto-generated method stub
	getFotoPublicidadDao().delete(foto);
    }

    /* (non-Javadoc)
     * @see tesis.playon.web.service.IFotoPublicidadService#findAll()
     */
    @Override
    public List<FotoPublicidad> findAll() {
	// TODO Auto-generated method stub
	return getFotoPublicidadDao().findAll();
    }

    /* (non-Javadoc)
     * @see tesis.playon.web.service.IFotoPublicidadService#findByNombre(java.lang.String)
     */
    @Override
    public FotoPublicidad findByNombre(String nombre) {
	// TODO Auto-generated method stub
	return getFotoPublicidadDao().findByNombre(nombre);
    }

    /**
     * @return the fotoPublicidadDao
     */
    public IFotoPublicidadDao getFotoPublicidadDao() {
        return fotoPublicidadDao;
    }

    /**
     * @param fotoPublicidadDao the fotoPublicidadDao to set
     */
    public void setFotoPublicidadDao(IFotoPublicidadDao fotoPublicidadDao) {
        this.fotoPublicidadDao = fotoPublicidadDao;
    }

}
