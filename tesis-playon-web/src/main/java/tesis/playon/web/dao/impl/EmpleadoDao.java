package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IEmpleadoDao;
import tesis.playon.web.model.Empleado;
import tesis.playon.web.model.Usuario;

/**
 * @author garribere
 * 
 */
public class EmpleadoDao implements IEmpleadoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(Empleado empleado) {
	getSessionFactory().getCurrentSession().save(empleado);
    }

    public void update(Empleado empleado) {
	getSessionFactory().getCurrentSession().update(empleado);
    }

    public void delete(Empleado empleado) {
	getSessionFactory().getCurrentSession().delete(empleado);
    }

    public List<Empleado> findAll() {
	List<Empleado> empleados = new ArrayList<Empleado>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Empleado").list();
	for (Object object : list) {
	    empleados.add((Empleado) object);
	}
	return empleados;
    }

    public List<Empleado> findAll(Integer idCargoEmpleado) {
	List<Empleado> empleados = new ArrayList<Empleado>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Empleado where cargoEmpleado.id=?")
		.setParameter(0, idCargoEmpleado).list();
	for (Object object : list) {
	    empleados.add((Empleado) object);
	}
	return empleados;
    }

    public Empleado findById(Integer id) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Empleado where id=?")
		.setParameter(0, id).list();
	if (!list.isEmpty()) {
	    return (Empleado) list.get(0);
	}
	return null;
    }

    public Empleado findByIdUsuario(Usuario usuario) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Empleado where usuario=?")
		.setParameter(0, usuario).list();
	if (!list.isEmpty()) {
	    return (Empleado) list.get(0);
	}
	return null;
    }

    public Empleado findByLegajo(Integer legajo) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Empleado where legajo=?")
		.setParameter(0, legajo).list();
	if (!list.isEmpty()) {
	    return (Empleado) list.get(0);
	}
	return null;
    }
}
