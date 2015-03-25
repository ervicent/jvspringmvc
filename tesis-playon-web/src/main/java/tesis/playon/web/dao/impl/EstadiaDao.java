package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import tesis.playon.web.dao.IEstadiaDao;
import tesis.playon.web.model.Estadia;
import tesis.playon.web.model.Playa;

@Repository("estadiaDao")
public class EstadiaDao implements IEstadiaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(Estadia estadia) {
	getSessionFactory().getCurrentSession().save(estadia);
    }

    public void update(Estadia estadia) {
	getSessionFactory().getCurrentSession().update(estadia);
    }

    public void delete(Estadia estadia) {
	getSessionFactory().getCurrentSession().delete(estadia);
    }

    public List<Estadia> findAll() {
	List<Estadia> estadias = new ArrayList<Estadia>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Estadia").list();
	for (Object object : list) {
	    estadias.add((Estadia) object);
	}
	return estadias;
    }

    public Estadia findByPlaya(Playa playa) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Estadia where playa=?")
		.setParameter(0, playa).list();
	if (!list.isEmpty()) {
	    return (Estadia) list.get(0);
	}
	return null;
    }

}
