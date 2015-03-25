package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.ICalificacionDao;
import tesis.playon.web.model.Calificacion;
import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.Playa;

public class CalificacionDao implements ICalificacionDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Calificacion calificacion) {
	// TODO Auto-generated method stub
	getSessionFactory().getCurrentSession().save(calificacion);
    }

    @Override
    public void update(Calificacion calificacion) {
	// TODO Auto-generated method stub
	getSessionFactory().getCurrentSession().update(calificacion);
    }

    @Override
    public void delete(Calificacion calificacion) {
	// TODO Auto-generated method stub
	getSessionFactory().getCurrentSession().delete(calificacion);
    }

    @Override
    public List<Calificacion> findByPlaya(Playa playa) {
	List<Calificacion> calificaciones = new ArrayList<Calificacion>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Calificacion where playa=?")
		.setParameter(0, playa).list();
	if (!list.isEmpty()) {
	    for (Object obj : list) {
		calificaciones.add((Calificacion) obj);
	    }
	    return calificaciones;
	}
	return null;
    }

    @Override
    public List<Calificacion> findAll() {
	List<Calificacion> calificaciones = new ArrayList<Calificacion>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Calificacion").list();
	if (!list.isEmpty()) {
	    for (Object obj : list) {
		calificaciones.add((Calificacion) obj);
	    }
	    return calificaciones;
	}
	return null;
    }

    @Override
    public boolean isRate(Playa playa, Cliente cliente) {
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Calificacion where playa=? and cliente=?").setParameter(0, playa)
		.setParameter(1, cliente).list();
	if (!list.isEmpty()) {
	    return true;
	}
	return false;
    }
}