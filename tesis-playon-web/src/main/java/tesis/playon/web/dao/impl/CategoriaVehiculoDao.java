package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.ICategoriaVehiculoDao;
import tesis.playon.web.model.CategoriaVehiculo;

/**
 * @author Pablo
 * 
 */
public class CategoriaVehiculoDao implements ICategoriaVehiculoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(CategoriaVehiculo categoria) {
	getSessionFactory().getCurrentSession().save(categoria);
    }

    public void update(CategoriaVehiculo categoria) {
	getSessionFactory().getCurrentSession().update(categoria);
    }

    public void delete(CategoriaVehiculo categoria) {
	getSessionFactory().getCurrentSession().delete(categoria);
    }

    public CategoriaVehiculo findByNombreCategoriaVehiculo(String nombreCategoria) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from CategoriaVehiculo where nombre=?")
		.setParameter(0, nombreCategoria).list();
	return (CategoriaVehiculo) list.get(0);
    }

    public List<CategoriaVehiculo> findAll() {
	List<CategoriaVehiculo> categorias = new ArrayList<CategoriaVehiculo>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from CategoriaVehiculo order by nombre")
		.list();
	for (Object object : list) {
	    categorias.add((CategoriaVehiculo) object);
	}
	return categorias;
    }
}
