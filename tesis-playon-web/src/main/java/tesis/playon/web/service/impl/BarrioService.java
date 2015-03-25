/**
 * 
 */
package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IBarrioDao;
import tesis.playon.web.model.Barrio;
import tesis.playon.web.service.IBarrioService;

/**
 * @author pablo
 *
 */
@Transactional(readOnly = true)
public class BarrioService implements IBarrioService {

    IBarrioDao barrioDao;
    
    @Transactional(readOnly = false)
    @Override
    public void save(Barrio barrio) {
	getBarrioDao().save(barrio);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(Barrio barrio) {
	getBarrioDao().update(barrio);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Barrio barrio) {
	getBarrioDao().delete(barrio);
    }

    @Override
    public Barrio findByNombreBarrio(String nombreBarrio) {
	return getBarrioDao().findByNombreBarrio(nombreBarrio);
    }

    @Override
    public List<Barrio> findAll() {
	return getBarrioDao().findAll();
    }

    public IBarrioDao getBarrioDao() {
        return barrioDao;
    }

    public void setBarrioDao(IBarrioDao barrioDao) {
        this.barrioDao = barrioDao;
    }
}