package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IDenunciaPlayaDao;
import tesis.playon.web.model.DenunciaPlaya;
import tesis.playon.web.model.DenunciaVehiculo;
import tesis.playon.web.model.EstadoDenuncia;

/**
 * @author garribere
 * 
 */
public class DenunciaPlayaDao implements IDenunciaPlayaDao {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(DenunciaPlaya denunciaPlaya) {
		getSessionFactory().getCurrentSession().save(denunciaPlaya);
	}

	public void update(DenunciaPlaya denunciaPlaya) {
		getSessionFactory().getCurrentSession().update(denunciaPlaya);
	}

	public void delete(DenunciaPlaya denunciaPlaya) {
		getSessionFactory().getCurrentSession().delete(denunciaPlaya);
	}

	public DenunciaPlaya findByAsuntoDenunciaPlaya(String asuntoDenunciaPlaya) {
		List<?> list = getSessionFactory().getCurrentSession()
				.createQuery("from DenunciaPlaya where asunto=?")
				.setParameter(0, asuntoDenunciaPlaya).list();
		return (DenunciaPlaya) list.get(0);
	}

	public List<DenunciaPlaya> findAll() {
		List<DenunciaPlaya> denuncias = new ArrayList<DenunciaPlaya>();
		List<?> list = getSessionFactory().getCurrentSession()
				.createQuery("from DenunciaPlaya").list();
		for (Object object : list) {
			denuncias.add((DenunciaPlaya) object);
		}
		return denuncias;
	}

	public DenunciaPlaya findByEstadoDenunciaPlaya(EstadoDenuncia estado) {
		List<?> list = getSessionFactory().getCurrentSession()
				.createQuery("from DenunciaPlaya where estado=?")
				.setParameter(0, estado).list();
		return (DenunciaPlaya) list.get(0);

	}

	public List<DenunciaPlaya> findByEstadoDenunciaPlayas(EstadoDenuncia estado) {
		List<DenunciaPlaya> denuncias = new ArrayList<DenunciaPlaya>();
		List<?> list = getSessionFactory().getCurrentSession()
				.createQuery("from DenunciaPlaya where estado=?")
				.setParameter(0, estado).list();
		for (Object object : list) {
			denuncias.add((DenunciaPlaya) object);
		}
		return denuncias;
	}

}
