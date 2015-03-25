package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IEstadoPublicidadDao;
import tesis.playon.web.model.EstadoPublicidad;

public class EstadoPublicidadDao implements IEstadoPublicidadDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(EstadoPublicidad estadoPublicidad) {
	getSessionFactory().getCurrentSession().save(estadoPublicidad);
    }

    public void update(EstadoPublicidad estadoPublicidad) {
	getSessionFactory().getCurrentSession().update(estadoPublicidad);
    }

    public void delete(EstadoPublicidad estadoPublicidad) {
	getSessionFactory().getCurrentSession().delete(estadoPublicidad);
    }

    public EstadoPublicidad findByNombreEstadoPublicidad(String nombreEstadoPublicidad) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from EstadoPublicidad where nombre=?")
		.setParameter(0, nombreEstadoPublicidad).list();
	if(!list.isEmpty())
	    return (EstadoPublicidad) list.get(0);
	return null;
    }

    public List<EstadoPublicidad> findAll() {
	List<EstadoPublicidad> listaEstadoPublicidad = new ArrayList<EstadoPublicidad>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from EstadoPublicidad").list();
	for (Object obj : list) {
	    listaEstadoPublicidad.add((EstadoPublicidad) obj);
	}
	return listaEstadoPublicidad;
    }

}
