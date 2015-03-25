package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IPerfilPlayaDao;
import tesis.playon.web.model.PerfilPlaya;
import tesis.playon.web.model.Playa;

/**
 * 
 * @author gmorales
 * 
 */
public class PerfilPlayaDao implements IPerfilPlayaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(PerfilPlaya perfilPlaya) {
	getSessionFactory().getCurrentSession().save(perfilPlaya);
    }

    public void update(PerfilPlaya perfilPlaya) {
	getSessionFactory().getCurrentSession().update(perfilPlaya);
    }

    public void delete(PerfilPlaya perfilPlaya) {
	getSessionFactory().getCurrentSession().delete(perfilPlaya);
    }

    public PerfilPlaya findByNombrePerfilPlaya(String nombrePerfilPlaya) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from PerfilPlaya where nombre=?")
		.setParameter(0, nombrePerfilPlaya).list();
	if (!list.isEmpty())
	    return (PerfilPlaya) list.get(0);
	return null;
    }

    public List<PerfilPlaya> findAll() {
	List<PerfilPlaya> perfiles = new ArrayList<PerfilPlaya>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from PerfilPlaya").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		perfiles.add((PerfilPlaya) object);
	    }
	    return perfiles;
	}
	return null;
    }

    public PerfilPlaya findByPlaya(Playa playa) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from PerfilPlaya where playa=?")
		.setParameter(0, playa).list();
	if (!list.isEmpty())
	    return (PerfilPlaya) list.get(0);
	return null;
    }
}
