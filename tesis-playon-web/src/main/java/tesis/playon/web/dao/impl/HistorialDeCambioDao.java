package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IHistorialDeCambioDao;
import tesis.playon.web.model.HistorialDeCambio;

public class HistorialDeCambioDao implements IHistorialDeCambioDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(HistorialDeCambio historialDeCambio) {
	getSessionFactory().getCurrentSession().save(historialDeCambio);
    }

    public void update(HistorialDeCambio historialDeCambio) {
	getSessionFactory().getCurrentSession().update(historialDeCambio);
    }

    public void delete(HistorialDeCambio historialDeCambio) {
	getSessionFactory().getCurrentSession().delete(historialDeCambio);
    }

    public List<HistorialDeCambio> findAll() {
	List<HistorialDeCambio> historialDeCambio = new ArrayList<HistorialDeCambio>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from HistorialDeCambio").list();
	for (Object object : list) {
	    historialDeCambio.add((HistorialDeCambio) object);
	}
	return historialDeCambio;
    }

}
