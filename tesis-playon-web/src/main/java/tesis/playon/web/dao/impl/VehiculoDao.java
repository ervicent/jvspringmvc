package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IVehiculoDao;
import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.Vehiculo;

/**
 * @author Pablo
 * 
 */
public class VehiculoDao implements IVehiculoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(Vehiculo vehiculo) {
	getSessionFactory().getCurrentSession().save(vehiculo);
    }

    public void update(Vehiculo vehiculo) {
	getSessionFactory().getCurrentSession().update(vehiculo);
    }

    public void delete(Vehiculo vehiculo) {
	getSessionFactory().getCurrentSession().delete(vehiculo);
    }

    public Vehiculo findByPatenteVehiculo(String patenteVehiculo) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Vehiculo where patente=?")
		.setParameter(0, patenteVehiculo).list();
	if (!list.isEmpty()) {
	    return (Vehiculo) list.get(0);
	} else {
	    return null;
	}
    }

    public List<Vehiculo> findByCliente(int idCliente) {
	List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Vehiculo where cliente.id=? and habilitado=true order by patente")
		.setParameter(0, idCliente).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		vehiculos.add((Vehiculo) object);
	    }
	}
	return vehiculos;
    }

    public List<Vehiculo> findAll() {
	List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Vehiculo").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		vehiculos.add((Vehiculo) object);
	    }
	    return vehiculos;
	}
	return null;

    }
    
    public boolean isPropietario(String patente, Cliente cliente){
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Vehiculo where patente=? and habilitado=true and cliente=?")
		.setParameter(0, patente).setParameter(1, cliente).list();
	if (list.isEmpty()) {
	    return false;
	}
	return true;
    }
    
    public boolean isHabilitado(String patente){
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Vehiculo where patente=? and habilitado=true")
		.setParameter(0, patente).list();
	if (list.isEmpty()) {
	    return false;
	}
	return true;
    }
}