package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IColorVehiculoDao;
import tesis.playon.web.model.ColorVehiculo;

/**
 * @author Pablo
 * 
 */
public class ColorVehiculoDao implements IColorVehiculoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(ColorVehiculo color) {
	getSessionFactory().getCurrentSession().save(color);
    }

    public void update(ColorVehiculo color) {
	getSessionFactory().getCurrentSession().update(color);
    }

    public void delete(ColorVehiculo color) {
	getSessionFactory().getCurrentSession().delete(color);
    }

    public ColorVehiculo findByNombreColorVehiculo(String colorVehiculo) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from ColorVehiculo where nombre=? order by nombre")
		.setParameter(0, colorVehiculo).list();
	return (ColorVehiculo) list.get(0);
    }

    public List<ColorVehiculo> findAll() {
	List<ColorVehiculo> colores = new ArrayList<ColorVehiculo>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from ColorVehiculo order by nombre").list();
	for (Object object : list) {
	    colores.add((ColorVehiculo) object);
	}
	return colores;
    }
}
