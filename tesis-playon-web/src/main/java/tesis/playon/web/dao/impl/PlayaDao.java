package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IPlayaDao;
import tesis.playon.web.model.EstadoPlaya;
import tesis.playon.web.model.Playa;

public class PlayaDao implements IPlayaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(Playa playa) {
	getSessionFactory().getCurrentSession().save(playa);
    }

    public void update(Playa playa) {
	getSessionFactory().getCurrentSession().update(playa);
    }

    public void delete(Playa playa) {
	getSessionFactory().getCurrentSession().delete(playa);
    }

    public Playa findByNombreComercial(String nombreComercial) {
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Playa where nombreComercial=?  order by razonSocial")
		.setParameter(0, nombreComercial).list();
	if (!list.isEmpty())
	    return (Playa) list.get(0);
	return null;
    }

    /**
     * Busca las playas cuyo nombre comercial es 'LIKE [nombreComercial]'
     * 
     * @param nombreComercial
     *            El nombre de la playa
     * @return Todas las playas cuyo nombre contienen 'nombreComercial'.
     */
    public List<Playa> findByLikeNombreComercial(String nombreComercial) {

	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Playa where nombreComercial LIKE ? order by nombreComercial")
		.setParameter(0, "%" + nombreComercial + "%").list();
	if (!list.isEmpty()) {
	    List<Playa> listPlayas = new ArrayList<Playa>();
	    for (Object playa : list) {
		listPlayas.add((Playa) playa);
	    }
	    return listPlayas;
	}
	return null;
    }

    /**
     * Busca las playas cuyo nombre comercial es 'LIKE [nombreComercial]' y estado.
     * 
     * @param nombreComercial
     *            El nombre de la playa
     * @return Todas las playas cuyo nombre contienen 'nombreComercial' y estado.
     */
    public List<Playa> findByLikeNombreComercialEstado(String nombreComercial, EstadoPlaya estado) {

	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Playa where nombreComercial LIKE ? and estado=? order by nombreComercial")
		.setParameter(0, "%" + nombreComercial + "%")
		.setParameter(1, estado).list();
	if (!list.isEmpty()) {
	    List<Playa> listPlayas = new ArrayList<Playa>();
	    for (Object playa : list) {
		listPlayas.add((Playa) playa);
	    }
	    return listPlayas;
	}
	return null;
    }

    public Playa findByRazonSocial(String razonSocial) {
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Playa where razonSocial=?  order by razonSocial").setParameter(0, razonSocial)
		.list();
	if (!list.isEmpty())
	    return (Playa) list.get(0);
	return null;
    }

    public Playa findById(int idPlaya) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Playa where id=?")
		.setParameter(0, idPlaya).list();
	if (!list.isEmpty())
	    return (Playa) list.get(0);
	return null;
    }

    public List<Playa> findPlayasCercanas(Double longitud, Double latitud, int distancia) {
	List<?> list = getSessionFactory().getCurrentSession().getNamedQuery("callPlayasStoreProcedure")
		.setParameter("platitud", latitud).setParameter("plongitud", longitud)
		.setParameter("pdistancia", distancia).list();
	List<Playa> playas = new ArrayList<Playa>();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		playas.add((Playa) object);
	    }
	    return playas;
	}
	return null;
    }

    public List<Playa> findByPlayasCercanas(Double longitud, Double latitud, int categoriaParameter,
	    int tipoEstadiaParameter, String nombreComercial, int checkPromociones) {

	Query query = getSessionFactory().getCurrentSession().getNamedQuery("callPlayasSP")
		.setParameter("platitud", latitud).setParameter("plongitud", longitud)
		.setParameter("pTipoEstadia", tipoEstadiaParameter)
		.setParameter("pCategoriaVehiculo", categoriaParameter)
		.setParameter("pNombreComercial", nombreComercial).setParameter("pPromociones", checkPromociones);
	List<?> list = query.list();
	List<Playa> playas = new ArrayList<Playa>();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		playas.add((Playa) object);
	    }
	    return playas;
	}
	return null;
    }

    public List<Playa> findAll() {
	List<Playa> playa = new ArrayList<Playa>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Playa order by razonSocial").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		playa.add((Playa) object);
	    }
	    return playa;
	}
	return null;
    }

    public List<Playa> findByEstado(EstadoPlaya estado) {
	List<Playa> playa = new ArrayList<Playa>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Playa where estado=?")
		.setParameter(0, estado).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		playa.add((Playa) object);
	    }
	    return playa;
	}
	return null;
    }

    @Override
    public boolean existeEmail(String email) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Playa where email=?")
		.setParameter(0, email).list();
	if (list.isEmpty()) {
	    return false;
	}
	return true;
    }

    @Override
    public List<Playa> findByFechaDesdeHasta(Date fechaDesde, Date fechaHasta) {
	List<Playa> playas = new ArrayList<Playa>();
	List<?> list = getSessionFactory()
		.getCurrentSession()
		.createQuery(
			"select distinct tp.cuentaPlaya.playa from TransaccionPlaya as tp "
				+ "where (tp.liquidacion is null) and tp.fecha>=? and tp.fecha<=?")
		.setParameter(0, fechaDesde).setParameter(1, fechaHasta).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		playas.add(((Playa) object));
	    }
	    return playas;
	}
	return null;
    }

}