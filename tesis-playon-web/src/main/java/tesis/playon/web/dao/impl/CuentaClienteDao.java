package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.ICuentaClienteDao;
import tesis.playon.web.model.CuentaCliente;

public class CuentaClienteDao implements ICuentaClienteDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(CuentaCliente cuentaCliente) {
	getSessionFactory().getCurrentSession().save(cuentaCliente);
    }

    public void update(CuentaCliente cuentaCliente) {
	getSessionFactory().getCurrentSession().update(cuentaCliente);
    }

    public void delete(CuentaCliente cuentaCliente) {
	getSessionFactory().getCurrentSession().delete(cuentaCliente);
    }

    public List<CuentaCliente> findAll() {
	List<CuentaCliente> colores = new ArrayList<CuentaCliente>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from CuentaCliente").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		colores.add((CuentaCliente) object);
	    }
	    return colores;
	}
	return null;
    }

    public CuentaCliente findByNroCuenta(Integer nroCuenta) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from CuentaCliente where nroCuenta=?")
		.setParameter(0, nroCuenta).list();
	if (!list.isEmpty()) {
	    return (CuentaCliente) list.get(0);
	}
	return null;
    }
}
