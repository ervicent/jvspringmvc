package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.ITipoEstadiaDao;
import tesis.playon.web.model.TipoEstadia;

/**
 * 
 * @author garribere
 * 
 */
public class TipoEstadiaDao implements ITipoEstadiaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(TipoEstadia tipoEstadia) {
	getSessionFactory().getCurrentSession().save(tipoEstadia);
    }

    public void update(TipoEstadia tipoEstadia) {
	getSessionFactory().getCurrentSession().update(tipoEstadia);
    }

    public void delete(TipoEstadia tipoEstadia) {
	getSessionFactory().getCurrentSession().delete(tipoEstadia);
    }

    public TipoEstadia findByNombreTipoEstadia(String nombreTipoEstadia) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from TipoEstadia where nombre=?")
		.setParameter(0, nombreTipoEstadia).list();
	return (TipoEstadia) list.get(0);
    }
    
    public TipoEstadia findByIdTipoEstadia(int idTipoEstadia) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from TipoEstadia where id=?")
		.setParameter(0, idTipoEstadia).list();
	return (TipoEstadia) list.get(0);
    }

    public List<TipoEstadia> findAll() {
	List<TipoEstadia> tipos = new ArrayList<TipoEstadia>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from TipoEstadia order by nombre").list();
	for (Object object : list) {
	    tipos.add((TipoEstadia) object);
	}
	return tipos;
    }

}
