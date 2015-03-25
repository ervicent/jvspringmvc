package tesis.playon.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IDetalleEstadiaDao;
import tesis.playon.web.model.DetalleEstadia;
import tesis.playon.web.model.Estadia;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Vehiculo;
import tesis.playon.web.service.IDetalleEstadiaService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class DetalleEstadiaService implements IDetalleEstadiaService {

    IDetalleEstadiaDao detalleEstadiaDao;

    @Transactional(readOnly = false)
    @Override
    public void save(DetalleEstadia detalleEstadia) {
	getDetalleEstadiaDao().save(detalleEstadia);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(DetalleEstadia detalleEstadia) {
	getDetalleEstadiaDao().update(detalleEstadia);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(DetalleEstadia detalleEstadia) {
	getDetalleEstadiaDao().delete(detalleEstadia);
    }

    @Override
    public List<DetalleEstadia> findAll() {
	return getDetalleEstadiaDao().findAll();
    }

    public List<DetalleEstadia> findByEstadia(Estadia estadia) {
	return getDetalleEstadiaDao().findByEstadia(estadia);
    }

    @Override
    public DetalleEstadia findByVehiculoDetalleEstadia(Vehiculo vehiculo) {
	return getDetalleEstadiaDao().findByVehiculoDetalleEstadia(vehiculo);
    }

    public List<DetalleEstadia> findByHorarios(Estadia estadia, Date fechaInicio, Date fechaFin) {
	return getDetalleEstadiaDao().findByHorarios(estadia, fechaInicio, fechaFin);

    }
    
    @Override
    public Integer[] findEstadiasByPlaya(Playa playa, Date fechaDesde, Date fechaHasta){
	return getDetalleEstadiaDao().findEstadiasByPlaya(playa, fechaDesde, fechaHasta);
    }
    
    @Override
    public List<String[]> findEstadiasByVehiculoByPeriodo(Vehiculo vehiculo, Date fechaDesde, Date fechaHasta){
	return getDetalleEstadiaDao().findEstadiasByVehiculoByPeriodo(vehiculo, fechaDesde, fechaHasta);
    }

    public IDetalleEstadiaDao getDetalleEstadiaDao() {
	return detalleEstadiaDao;
    }

    public void setDetalleEstadiaDao(IDetalleEstadiaDao detalleEstadiaDao) {
	this.detalleEstadiaDao = detalleEstadiaDao;
    }

}