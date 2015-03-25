package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.ITipoPagoDao;
import tesis.playon.web.model.TipoPago;

public class TipoPagoDao implements ITipoPagoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(TipoPago tipoPago) {
	getSessionFactory().getCurrentSession().save(tipoPago);
    }

    public void update(TipoPago tipoPago) {
	getSessionFactory().getCurrentSession().update(tipoPago);
    }

    public void delete(TipoPago tipoPago) {
	getSessionFactory().getCurrentSession().delete(tipoPago);
    }

    public TipoPago findByNameTipoPago(String nombreTipoPago) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from TipoPago where nombre=?")
		.setParameter(0, nombreTipoPago).list();
	return (TipoPago) list.get(0);
    }

    public List<TipoPago> findAll() {
	List<TipoPago> tipos = new ArrayList<TipoPago>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from TipoPago order by nombre").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		tipos.add((TipoPago) object);
	    }
	}
	return tipos;
    }
}
