package tesis.playon.web.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IPublicidadDao;
import tesis.playon.web.model.EstadoPublicidad;
import tesis.playon.web.model.Publicidad;

/**
 * Clase DAO de Publicidad
 * 
 * @author alejandro
 * @date 07/07/2012
 */
public class PublicidadDao implements IPublicidadDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Publicidad publicidad) {
	getSessionFactory().getCurrentSession().save(publicidad);
    }

    @Override
    public void update(Publicidad publicidad) {
	getSessionFactory().getCurrentSession().update(publicidad);
    }

    @Override
    public void delete(Publicidad publicidad) {
	getSessionFactory().getCurrentSession().delete(publicidad);
    }

    @Override
    public List<Publicidad> findAll() {
	List<Publicidad> publicidades = new ArrayList<Publicidad>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Publicidad").list();
	for (Object object : list) {
	    publicidades.add((Publicidad) object);
	}
	return publicidades;
    }

    @Override
    public List<Publicidad> findAllByEstadoVigente() {
	List<Publicidad> publicidades = new ArrayList<Publicidad>();
	List<?> list = getSessionFactory()
		.getCurrentSession()
		.createQuery(
			"from Publicidad WHERE estado.id=2 and now()>=fechaDesde and"
				+ " now()<=fechaHasta ORDER BY fechaDesde ASC").list();
	for (Object object : list) {
	    publicidades.add((Publicidad) object);
	}
	return publicidades;
    }

    @Override
    public List<Publicidad> findByEstado(EstadoPublicidad estado) {
	List<Publicidad> publicidades = new ArrayList<Publicidad>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from Publicidad WHERE estado=? and" + " now()<=fechaHasta ORDER BY fechaDesde ASC")
		.setParameter(0, estado).list();
	for (Object object : list) {
	    publicidades.add((Publicidad) object);
	}
	return publicidades;
    }

    @Override
    public List<String[]> getMontosDePublicidadByPeriodo(Date fechaDesde, Date fechaHasta) {
	List<String[]> resultQuery = new ArrayList<String[]>(4);
	String query = "SELECT CAST(YEAR(DATE(pub.fechaDesde)) AS CHAR(50)) AS 'AÑO'"
		+ ",CAST(MONTH(DATE(pub.fechaDesde)) AS CHAR(50)) AS 'MES'"
		+ ",CAST(SUM(pub.precio * DATEDIFF(DATE(pub.fechaHasta),DATE(pub.fechaDesde))) AS CHAR(50)) AS 'MONTO RECAUDADO'"
		+ ",CAST(COUNT(pub.publicidadID) AS CHAR(50)) AS 'CANTIDAD DE PUBLICIDADES' " + "FROM publicidad pub "
		+ "WHERE DATE(pub.fechaDesde) >= CONVERT(?, DATE) "
		+ "AND DATE(pub.fechaDesde) <= CONVERT(?, DATE) "
		+ "GROUP BY YEAR(DATE(pub.fechaDesde)), MONTH(DATE(pub.fechaDesde)) " + "ORDER BY 1,2";

	SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
	List<?> list = getSessionFactory().getCurrentSession().createSQLQuery(query)
		.setParameter(0, formato.format(fechaDesde)).setParameter(1, formato.format(fechaHasta)).list();
	if (!list.isEmpty()) {
	    String[] vMonto;
	    for (Object obj : list) {
		vMonto = new String[4];
		Object[] vObject = new Object[4];
		vObject = ((Object[]) obj);
		vMonto[0] = (String) vObject[0];
		vMonto[1] = (String) vObject[1];
		vMonto[2] = (String) vObject[2];
		vMonto[3] = (String) vObject[3];

		resultQuery.add(vMonto);
	    }
	}
	return resultQuery;
    }
}
