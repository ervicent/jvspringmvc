package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IModeloVehiculoDao;
import tesis.playon.web.model.MarcaVehiculo;
import tesis.playon.web.model.ModeloVehiculo;

/**
 * 
 * @author Pablo
 * 
 */
public class ModeloVehiculoDao implements IModeloVehiculoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(ModeloVehiculo modelo) {
	getSessionFactory().getCurrentSession().save(modelo);
    }

    public void update(ModeloVehiculo modelo) {
	getSessionFactory().getCurrentSession().update(modelo);
    }

    public void delete(ModeloVehiculo modelo) {
	getSessionFactory().getCurrentSession().delete(modelo);
    }

    public ModeloVehiculo findByNombreModeloVehiculo(String nombreModelo) {
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from ModeloVehiculo where nombre=? ORDER BY nombre").setParameter(0, nombreModelo).list();
	return (ModeloVehiculo) list.get(0);
    }

    public List<ModeloVehiculo> findByMarca(MarcaVehiculo marca) {
	List<ModeloVehiculo> modelos = new ArrayList<ModeloVehiculo>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from ModeloVehiculo where marcaVehiculo=? ORDER BY nombre").setParameter(0, marca).list();
	for (Object object : list) {
	    modelos.add((ModeloVehiculo) object);
	}
	return modelos;
    }

    public List<ModeloVehiculo> findAll() {
	List<ModeloVehiculo> modelos = new ArrayList<ModeloVehiculo>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from ModeloVehiculo ORDER BY nombre")
		.list();
	for (Object object : list) {
	    modelos.add((ModeloVehiculo) object);
	}
	return modelos;
    }
}
