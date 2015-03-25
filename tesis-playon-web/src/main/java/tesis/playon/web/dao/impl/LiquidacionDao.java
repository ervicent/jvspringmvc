package tesis.playon.web.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.ILiquidacionDao;
import tesis.playon.web.model.Liquidacion;

public class LiquidacionDao implements ILiquidacionDao {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(Liquidacion liquidacion) {
		getSessionFactory().getCurrentSession().save(liquidacion);
	}

	public void update(Liquidacion liquidacion) {
		getSessionFactory().getCurrentSession().update(liquidacion);
	}

	public void delete(Liquidacion liquidacion) {
		getSessionFactory().getCurrentSession().delete(liquidacion);
	}

	public List<Liquidacion> findAll() {
		List<Liquidacion> liquidaciones = new ArrayList<Liquidacion>();
		List<?> list = getSessionFactory().getCurrentSession()
				.createQuery("from Liquidacion").list();
		for (Object object : list) {
			liquidaciones.add((Liquidacion) object);
		}
		return liquidaciones;
	}

	public List<Liquidacion> findByFecha(Date fecha) {
		List<Liquidacion> liquidaciones = new ArrayList<Liquidacion>();
		List<?> list = getSessionFactory().getCurrentSession()
				.createQuery("from Liquidacion where fecha>=?")
				.setParameter(0, fecha).list();
		for (Object object : list) {
			liquidaciones.add((Liquidacion) object);
		}
		return liquidaciones;
	}

	public List<Liquidacion> findByFecha(Date fechaDesde, Date fechaHasta) {
		List<Liquidacion> liquidaciones = new ArrayList<Liquidacion>();
		List<?> list = getSessionFactory().getCurrentSession()
				.createQuery("from Liquidacion where DATE(fecha) >= DATE(?) and DATE(fecha) <= DATE(?)")
				.setParameter(0, fechaDesde).setParameter(1, fechaHasta).list();
		for (Object object : list) {
			liquidaciones.add((Liquidacion) object);
		}
		return liquidaciones;
	}
	
	@Override
	public List<String[]> getEstadisticasComisiones(Date fechaDesde, Date fechaHasta){
	    List<String[]> resultQuery = new ArrayList<String[]>(4);
		String query = "SELECT CAST(YEAR(DATE(liq.fecha)) AS CHAR(50)) AS 'AÑO'"
			+ ",CAST(MONTH(DATE(liq.fecha)) AS CHAR(50)) AS 'MES'"
			+ ",CAST(SUM(transaccionPlaya.transacciones) AS CHAR(20)) AS 'Cant. transacciones'"
			+ ",CAST(SUM(transaccionPlaya.comision) AS CHAR(20)) AS 'Comisión' "
			+ "FROM (SELECT txPlaya.liquidacionID AS 'liquidacionID'"
			+ ",COUNT(txPlaya.transaccionPlayaID) AS 'transacciones'"
			+ ",ROUND((SUM(txPlaya.importe) - liq.importeTotal),2) AS 'comision' "
			+ "FROM tesis_playon.transaccion_playa txPlaya "
			+ "INNER JOIN tesis_playon.liquidacion AS liq ON txPlaya.liquidacionID=liq.liquidacionID "
			+ "WHERE txPlaya.importe > 0  "
			+ "GROUP BY txPlaya.liquidacionID) AS transaccionPlaya "
			+ "INNER JOIN liquidacion AS liq ON liq.liquidacionID=transaccionPlaya.liquidacionID "
			+ "WHERE DATE(liq.fecha) >= DATE(?) "
			+ "AND DATE(liq.fecha) < DATE_ADD(?, INTERVAL 1 DAY) "
			+ "GROUP BY YEAR(DATE(liq.fecha)), MONTH(DATE(liq.fecha)) "
			+ "ORDER BY 1,2";
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		List<?> list = getSessionFactory().getCurrentSession().createSQLQuery(query)
			.setParameter(0, formato.format(fechaDesde))
			.setParameter(1, formato.format(fechaHasta)).list();
		if (!list.isEmpty()) {
		    String[] vComision;
		    for (Object obj : list) {
			vComision = new String[4];
			Object[] vObject = new Object[4];
			vObject = ((Object[]) obj);
			vComision[0] =(String) vObject[0];
			vComision[1] = (String) vObject[1];
			vComision[2] = (String) vObject[2];
			vComision[3] = (String) vObject[3];
			
			resultQuery.add(vComision);
		    }
		}
		return resultQuery;
	}
}
