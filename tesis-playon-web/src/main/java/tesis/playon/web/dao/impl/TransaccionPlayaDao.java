package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.ITransaccionPlayaDao;
import tesis.playon.web.model.CuentaPlaya;
import tesis.playon.web.model.Liquidacion;
import tesis.playon.web.model.TransaccionPlaya;

/**
 * 
 * @author alejandro
 * 
 */
public class TransaccionPlayaDao implements ITransaccionPlayaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(TransaccionPlaya transaccionPlaya) {
	getSessionFactory().getCurrentSession().save(transaccionPlaya);
    }

    public void update(TransaccionPlaya transaccionPlaya) {
	getSessionFactory().getCurrentSession().update(transaccionPlaya);
    }

    public void delete(TransaccionPlaya transaccionPlaya) {
	getSessionFactory().getCurrentSession().delete(transaccionPlaya);
    }

    public List<TransaccionPlaya> findAll() {
	List<TransaccionPlaya> transaccionPlaya = new ArrayList<TransaccionPlaya>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from TransaccionPlaya").list();
	for (Object object : list) {
	    transaccionPlaya.add((TransaccionPlaya) object);
	}
	return transaccionPlaya;
    }

    public TransaccionPlaya findByTransaccionPlayaID(int transaccionPlayaID) {
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from TransaccionPlaya where transaccionPlayaID=?").setParameter(0, transaccionPlayaID)
		.list();
	return (TransaccionPlaya) list.get(0);
    }

    @Override
    public List<TransaccionPlaya> findTransaccionesNoLiquidadas() {
	List<TransaccionPlaya> transaccionPlaya = new ArrayList<TransaccionPlaya>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from TransaccionPlaya where liquidacion is null").list();
	for (Object object : list) {
	    transaccionPlaya.add((TransaccionPlaya) object);
	}
	return transaccionPlaya;
    }

    public List<TransaccionPlaya> findNoLiquidadasByFechaDesdeHasta(Date fechaDesde, Date fechaHasta) {
	List<TransaccionPlaya> transaccionPlaya = new ArrayList<TransaccionPlaya>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from TransaccionPlaya where liquidacion is null and DATE(fecha) >= DATE(?) and DATE(fecha) <= DATE(?)")
		.setParameter(0, fechaDesde).setParameter(1, fechaHasta).list();
	for (Object object : list) {
	    transaccionPlaya.add((TransaccionPlaya) object);
	}
	return transaccionPlaya;
    }

    @Override
    public List<TransaccionPlaya> findByCuentaPlaya(CuentaPlaya cuentaPlaya) {
	List<TransaccionPlaya> transaccionPlaya = new ArrayList<TransaccionPlaya>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from TransaccionPlaya where cuentaPlaya = ?").setParameter(0, cuentaPlaya).list();
	for (Object object : list) {
	    transaccionPlaya.add((TransaccionPlaya) object);
	}
	return transaccionPlaya;
    }

    @Override
    public List<TransaccionPlaya> findNoLiquidadasByCuentaPlaya(CuentaPlaya cuentaPlaya) {
	List<TransaccionPlaya> transaccionPlaya = new ArrayList<TransaccionPlaya>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from TransaccionPlaya where cuentaPlaya = ? and liquidacion is null")
		.setParameter(0, cuentaPlaya).list();
	for (Object object : list) {
	    transaccionPlaya.add((TransaccionPlaya) object);
	}
	return transaccionPlaya;
    }

    @Override
    public List<TransaccionPlaya> findTransaccionesByCuentaPlaya(CuentaPlaya cuentaPlaya) {

	List<TransaccionPlaya> transaccionPlaya = new ArrayList<TransaccionPlaya>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from TransaccionPlaya where cuentaPlaya = ?").setParameter(0, cuentaPlaya).list();
	for (Object object : list) {
	    transaccionPlaya.add((TransaccionPlaya) object);
	}
	return transaccionPlaya;

    }

    @Override
    public List<TransaccionPlaya> findTransaccionesByLiquidacion(Liquidacion liquidacion) {

	List<TransaccionPlaya> transaccionPlaya = new ArrayList<TransaccionPlaya>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from TransaccionPlaya where liquidacion = ?").setParameter(0, liquidacion).list();
	for (Object object : list) {
	    transaccionPlaya.add((TransaccionPlaya) object);
	}
	return transaccionPlaya;

    }

    public List<TransaccionPlaya> findTransaccionesByFecha(CuentaPlaya cuentaPlaya, Date fechaD, Date fechaH) {
	List<TransaccionPlaya> transaccionPlaya = new ArrayList<TransaccionPlaya>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from TransaccionPlaya as tp where " + 
				"tp.cuentaPlaya=? and DATE(tp.fecha) >= DATE(?) and DATE(tp.fecha)<= DATE(?) " +
				"order by tp.fecha DESC")
		.setParameter(0, cuentaPlaya).setParameter(1, fechaD).setParameter(2, fechaH).list();

	if (!list.isEmpty()) {
	    for (Object object : list) {
		transaccionPlaya.add(((TransaccionPlaya) object));
	    }
	    return transaccionPlaya;
	}
	return null;
    }

}
