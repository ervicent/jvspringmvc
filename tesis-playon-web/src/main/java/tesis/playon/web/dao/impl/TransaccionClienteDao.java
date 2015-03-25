package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.ITransaccionClienteDao;
import tesis.playon.web.model.CuentaCliente;
import tesis.playon.web.model.TransaccionCliente;

/**
 * @author Pablo
 * 
 */
public class TransaccionClienteDao implements ITransaccionClienteDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(TransaccionCliente transaccionCliente) {
	getSessionFactory().getCurrentSession().save(transaccionCliente);
    }

    @Override
    public void update(TransaccionCliente transaccionCliente) {
	getSessionFactory().getCurrentSession().update(transaccionCliente);
    }

    @Override
    public void delete(TransaccionCliente transaccionCliente) {
	getSessionFactory().getCurrentSession().delete(transaccionCliente);
    }

    @Override
    public TransaccionCliente findByCuentaCliente(CuentaCliente cuentaCliente) {
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from TransaccionCliente where cuentaCliente=?").setParameter(0, cuentaCliente).list();
	if (!list.isEmpty()) {
	    return (TransaccionCliente) list.get(0);
	}
	return null;
    }

    @Override
    public List<TransaccionCliente> findAll() {
	List<TransaccionCliente> transaccionesCliente = new ArrayList<TransaccionCliente>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from TransaccionCliente").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		transaccionesCliente.add((TransaccionCliente) object);
	    }
	    return transaccionesCliente;
	}
	return null;
    }

    @Override
    public List<TransaccionCliente> findTransaccionesByCuentaCliente(CuentaCliente cuentaCliente) {
	List<TransaccionCliente> transaccionesCliente = new ArrayList<TransaccionCliente>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from TransaccionCliente where cuentaCliente=?").setParameter(0, cuentaCliente).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		transaccionesCliente.add((TransaccionCliente) object);
	    }
	    return transaccionesCliente;
	}
	return null;
    }

    @Override
    public List<TransaccionCliente> findTransaccionesByFecha(CuentaCliente cuentaCliente, Date fechaD, Date fechaH) {
	List<TransaccionCliente> transaccionCliente = new ArrayList<TransaccionCliente>();
	List<?> list = getSessionFactory()
		.getCurrentSession()
		.createQuery(
			"from TransaccionCliente as tp where "
				+ "tp.cuentaCliente = ? and DATE(tp.fecha) >= DATE(?) and DATE(tp.fecha) <= DATE(?) order by tp.fecha DESC")
		.setParameter(0, cuentaCliente).setParameter(1, fechaD).setParameter(2, fechaH).list();

	if (!list.isEmpty()) {
	    for (Object object : list) {
		transaccionCliente.add(((TransaccionCliente) object));
	    }
	    return transaccionCliente;
	}
	return null;
    }

    @Override
    public TransaccionCliente getUltimaTransaccion(CuentaCliente cuentaCliente) {
	List<TransaccionCliente> transaccionCliente = new ArrayList<TransaccionCliente>();
	List<?> list = getSessionFactory()
		.getCurrentSession()
		.createQuery(
			"FROM TransaccionCliente WHERE "
				+ "cuentaCliente=? and tipoPago.nombre='DineroMail' ORDER BY fecha DESC")
		.setParameter(0, cuentaCliente).list();

	if (!list.isEmpty()) {
	    for (Object object : list) {
		transaccionCliente.add(((TransaccionCliente) object));
	    }
	    return transaccionCliente.get(0);
	}
	return null;
    }
}
