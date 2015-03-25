package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IEstadoPromocionDao;
import tesis.playon.web.model.EstadoPromocion;

/**
 * 
 * @author garribere
 * 
 */
public class EstadoPromocionDao implements IEstadoPromocionDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(EstadoPromocion estadoPromocion) {
	getSessionFactory().getCurrentSession().save(estadoPromocion);
    }

    public void update(EstadoPromocion estadoPromocion) {
	getSessionFactory().getCurrentSession().update(estadoPromocion);
    }

    public void delete(EstadoPromocion estadoPromocion) {
	getSessionFactory().getCurrentSession().delete(estadoPromocion);
    }

    public EstadoPromocion findByNombreEstadoPromocion(String nombreEstadoPromocion) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from EstadoPromocion where nombre=?")
		.setParameter(0, nombreEstadoPromocion).list();
	if (!list.isEmpty())
	    return (EstadoPromocion) list.get(0);
	return null;
    }

    public List<EstadoPromocion> findAll() {
	List<EstadoPromocion> listaEstadoPromocion = new ArrayList<EstadoPromocion>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from EstadoPromocion").list();
	if (!list.isEmpty()) {
	    for (Object obj : list) {
		listaEstadoPromocion.add((EstadoPromocion) obj);
	    }
	    return listaEstadoPromocion;
	}
	return null;
    }

}
