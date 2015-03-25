package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.ICalificacionDao;
import tesis.playon.web.model.Calificacion;
import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.Playa;
import tesis.playon.web.service.ICalificacionService;

@Transactional(readOnly = true)
public class CalificacionService implements ICalificacionService {

    ICalificacionDao calificacionDao;
    
    @Transactional(readOnly = false)
    @Override
    public void save(Calificacion calificacion) {
	// TODO Auto-generated method stub
	getCalificacionDao().save(calificacion);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(Calificacion calificacion) {
	// TODO Auto-generated method stub
	getCalificacionDao().update(calificacion);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Calificacion calificacion) {
	// TODO Auto-generated method stub
	getCalificacionDao().delete(calificacion);
    }

    @Override
    public List<Calificacion> findByPlaya(Playa playa) {
	// TODO Auto-generated method stub
	return getCalificacionDao().findByPlaya(playa);
    }

    @Override
    public List<Calificacion> findAll() {
	// TODO Auto-generated method stub
	return getCalificacionDao().findAll();
    }
    
    @Override
    public boolean isRate(Playa playa, Cliente cliente) {
	return getCalificacionDao().isRate(playa, cliente);
    }

    public ICalificacionDao getCalificacionDao() {
        return calificacionDao;
    }

    public void setCalificacionDao(ICalificacionDao calificacionDao) {
        this.calificacionDao = calificacionDao;
    }

}
