package tesis.playon.web.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import tesis.playon.web.dao.ITarifaDao;
import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Tarifa;
import tesis.playon.web.model.TipoEstadia;

/**
 * 
 * @author gmorales
 * 
 */
public class TarifaDao implements ITarifaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(Tarifa tarifa) {
	getSessionFactory().getCurrentSession().save(tarifa);
    }

    public void update(Tarifa tarifa) {
	getSessionFactory().getCurrentSession().update(tarifa);
    }

    public void delete(Tarifa tarifa) {
	//getSessionFactory().getCurrentSession().delete(tarifa);
	tarifa.setVigente(new Boolean(false));
	tarifa.setFechaBaja(new Timestamp(Calendar.getInstance().getTimeInMillis()));
	getSessionFactory().getCurrentSession().update(tarifa);
    }

    public int deleteTarifasPlaya(Playa playa) {
	Query query = getSessionFactory().getCurrentSession().createQuery(
		"update Tarifa set fechaBaja = :fechaBaja, vigente = 0 " + " where playa = :playa");
	query.setParameter("fechaBaja", new Timestamp(Calendar.getInstance().getTimeInMillis()));
	query.setParameter("playa", playa);
	int result = query.executeUpdate();
	return result;
    }

    public List<Tarifa> findAll() {
	List<Tarifa> tarifas = new ArrayList<Tarifa>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Tarifa where fechaBaja is null").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		tarifas.add((Tarifa) object);
	    }
	    return tarifas;
	}
	return null;
    }

    public List<Tarifa> findTarifaVigenteByPlaya(Playa playa) {
	List<Tarifa> tarifas = new ArrayList<Tarifa>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Tarifa where playa=? and vigente = '1'").setParameter(0, playa).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		tarifas.add((Tarifa) object);
	    }
	    return tarifas;
	}
	return null;
    }

    @Override
    public List<Tarifa> findTarifaVigenteByPlayaAndCategoriaVehiculo(Playa playa, CategoriaVehiculo categoriaVehiculo) {
	List<Tarifa> tarifas = new ArrayList<Tarifa>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Tarifa where playa=? and categoriaVehiculo=? and vigente = 1")
		.setParameter(0, playa).setParameter(1, categoriaVehiculo).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		if(!((Tarifa)object).getTipoEstadia().getNombre().equals("Por Mes"))
		    tarifas.add((Tarifa) object);
	    }
	    return tarifas;
	}
	return null;
    }

    @Override
    public List<Tarifa> findByPlaya(Playa playa) {
	List<Tarifa> tarifas = new ArrayList<Tarifa>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Tarifa where playa=? and fechaBaja is null").setParameter(0, playa).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		tarifas.add((Tarifa) object);
	    }
	    return tarifas;
	}
	return null;
    }
    
    @Override
    public Tarifa findTarifaVigenteByPlayaAndCategoriaAndTipoEstadia(Playa playa, CategoriaVehiculo categoriaVehiculo, TipoEstadia tipoEstadia){
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Tarifa where playa=? and fechaBaja is null and tipoEstadia=? and categoriaVehiculo=?")
			.setParameter(0, playa)
			.setParameter(1, tipoEstadia)
			.setParameter(2, categoriaVehiculo).list();
	if (!list.isEmpty()) {
	    return (Tarifa)list.get(0);
	}
	return null;
    }
}