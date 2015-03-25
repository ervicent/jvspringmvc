package tesis.playon.web.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import tesis.playon.web.dao.IDetalleEstadiaDao;
import tesis.playon.web.model.DetalleEstadia;
import tesis.playon.web.model.Estadia;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Vehiculo;

@Repository("detalleEstadiaDao")
public class DetalleEstadiaDao implements IDetalleEstadiaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(DetalleEstadia detalleEstadia) {
	getSessionFactory().getCurrentSession().save(detalleEstadia);
    }

    public void update(DetalleEstadia detalleEstadia) {
	getSessionFactory().getCurrentSession().update(detalleEstadia);
    }

    public void delete(DetalleEstadia detalleEstadia) {
	getSessionFactory().getCurrentSession().delete(detalleEstadia);
    }

    public List<DetalleEstadia> findAll() {
	List<DetalleEstadia> detalleEstadia = new ArrayList<DetalleEstadia>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from DetalleEstadia").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		detalleEstadia.add((DetalleEstadia) object);
	    }
	}
	return detalleEstadia;
    }

    public List<DetalleEstadia> findByEstadia(Estadia estadia) {
	List<DetalleEstadia> detallesEstadia = new ArrayList<DetalleEstadia>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from DetalleEstadia where estadia=?")
		.setParameter(0, estadia).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		detallesEstadia.add((DetalleEstadia) object);
	    }
	}
	return detallesEstadia;
    }

    public List<DetalleEstadia> findBy(Estadia estadia) {
	List<DetalleEstadia> detallesEstadia = new ArrayList<DetalleEstadia>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from DetalleEstadia where estadia=?")
		.setParameter(0, estadia).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		detallesEstadia.add((DetalleEstadia) object);
	    }
	}
	return detallesEstadia;
    }

    public List<DetalleEstadia> findByHorarios(Estadia estadia, Date horaInicio, Date horaFin) {
	List<DetalleEstadia> detallesEstadia = new ArrayList<DetalleEstadia>();
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from DetalleEstadia where estadia=? and DATE(fechaHoraIngreso) >= DATE(?) and DATE(fechaHoraEgreso) <= DATE(?)")
		.setParameter(0, estadia).setParameter(1, horaInicio).setParameter(2, horaFin).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		detallesEstadia.add((DetalleEstadia) object);
	    }
	}
	return detallesEstadia;
    }

    @Override
    public DetalleEstadia findByVehiculoDetalleEstadia(Vehiculo vehiculo) {
	List<?> list = getSessionFactory().getCurrentSession()
		.createQuery("from DetalleEstadia where vehiculo=? and cobrado=0").setParameter(0, vehiculo).list();
	if (!list.isEmpty()) {
	    return (DetalleEstadia) list.get(0);
	}
	return null;
    }

    @Override
    public Integer[] findEstadiasByPlaya(Playa playa, Date fechaDesde, Date fechaHasta) {
	Integer[] horas = new Integer[24];
	for (int i = 0; i < horas.length; i++)
	    horas[i] = 0;
	String query = "SELECT HOUR(fechaHoraIngreso) " + "FROM tesis_playon.detalle_estadia de "
		+ "INNER JOIN tesis_playon.estadia e ON e.estadiaID=de.estadiaID "
		+ "WHERE playaID=? AND DATE(fechaHoraIngreso) >= DATE(?) AND DATE(fechaHoraIngreso) <= DATE(?)";
	List<?> list = getSessionFactory().getCurrentSession().createSQLQuery(query).setParameter(0, playa.getId())
		.setParameter(1, fechaDesde).setParameter(2, fechaHasta).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		switch (Integer.parseInt(object.toString())) {
		    case 00:
			horas[0]++;
			break;
		    case 01:
			horas[1]++;
			break;
		    case 02:
			horas[2]++;
			break;
		    case 03:
			horas[3]++;
			break;
		    case 04:
			horas[4]++;
			break;
		    case 05:
			horas[5]++;
			break;
		    case 06:
			horas[6]++;
			break;
		    case 07:
			horas[7]++;
			break;
		    case 8:
			horas[8]++;
			break;
		    case 9:
			horas[9]++;
			break;
		    case 10:
			horas[10]++;
			break;
		    case 11:
			horas[11]++;
			break;
		    case 12:
			horas[12]++;
			break;
		    case 13:
			horas[13]++;
			break;
		    case 14:
			horas[14]++;
			break;
		    case 15:
			horas[15]++;
			break;
		    case 16:
			horas[16]++;
			break;
		    case 17:
			horas[17]++;
			break;
		    case 18:
			horas[18]++;
			break;
		    case 19:
			horas[19]++;
			break;
		    case 20:
			horas[20]++;
			break;
		    case 21:
			horas[21]++;
			break;
		    case 22:
			horas[22]++;
			break;
		    case 23:
			horas[23]++;
			break;
		    default:
			break;
		}
	    }
	}
	return horas;
    }

    @Override
    public List<String[]> findEstadiasByVehiculoByPeriodo(Vehiculo vehiculo, Date fechaDesde, Date fechaHasta) {
	List<String[]> resultQuery = new ArrayList<String[]>(3);
	String query = "SELECT CAST(MONTH(DATE(fechaHoraEgreso)) AS CHAR(50)) AS 'MES'"
		+ ", CAST(YEAR(DATE(fechaHoraEgreso)) AS CHAR(50)) AS 'AÑO'"
		+ ", CAST(SUM(importeTotal) AS CHAR(50)) AS 'TOTAL CONSUMIDO'" + "FROM detalle_estadia "
		+ "WHERE vehiculoID = ? AND fechaHoraEgreso is not null "
		+ "AND DATE(fechaHoraEgreso) >= CONVERT(?, DATE) " + "AND DATE(fechaHoraEgreso) <= CONVERT(?, DATE) "
		+ "GROUP BY MONTH(DATE(fechaHoraEgreso)), YEAR(DATE(fechaHoraEgreso)) " + "ORDER BY 2, 1";
	SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
	List<?> list = getSessionFactory().getCurrentSession().createSQLQuery(query).setParameter(0, vehiculo.getId())
		.setParameter(1, formato.format(fechaDesde)).setParameter(2, formato.format(fechaHasta)).list();
	if (!list.isEmpty()) {
	    String[] vConsumo;
	    for (Object obj : list) {
		vConsumo = new String[3];
		Object[] vObject = new Object[3];
		vObject = ((Object[]) obj);
		vConsumo[0] =(String) vObject[0];
		vConsumo[1] = (String) vObject[1];
		vConsumo[2] = (String) vObject[2];
		resultQuery.add(vConsumo);
	    }
	}
	return resultQuery;
    }
}
