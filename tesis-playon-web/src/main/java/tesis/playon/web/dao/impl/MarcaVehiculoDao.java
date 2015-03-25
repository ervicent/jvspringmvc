package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IMarcaVehiculoDao;
import tesis.playon.web.model.MarcaVehiculo;

/**
 * 
 * @author gmorales
 * 
 */
public class MarcaVehiculoDao implements IMarcaVehiculoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(MarcaVehiculo marcaVehiculo) {
	getSessionFactory().getCurrentSession().save(marcaVehiculo);
    }

    public void update(MarcaVehiculo marcaVehiculo) {
	getSessionFactory().getCurrentSession().update(marcaVehiculo);
    }

    public void delete(MarcaVehiculo marcaVehiculo) {
	getSessionFactory().getCurrentSession().delete(marcaVehiculo);
    }

    public MarcaVehiculo findByNombreMarcaVehiculo(String nombreMarcaVehiculo) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from MarcaVehiculo where nombre=? order by nombre asc")
		.setParameter(0, nombreMarcaVehiculo).list();
	return (MarcaVehiculo) list.get(0);
    }

    public List<MarcaVehiculo> findAll() {
	List<MarcaVehiculo> marcas = new ArrayList<MarcaVehiculo>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from MarcaVehiculo marca order by marca.nombre").list();
	for (Object object : list) {
	    marcas.add((MarcaVehiculo) object);
	}
	return marcas;
    }
}
