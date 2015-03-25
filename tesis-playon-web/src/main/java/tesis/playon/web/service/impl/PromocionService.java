package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IPromocionDao;
import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.model.EstadoPromocion;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Promocion;
import tesis.playon.web.model.Tarifa;
import tesis.playon.web.model.TipoEstadia;
import tesis.playon.web.service.IPromocionService;
/**
 * 
 * @author pablo
 *
 */
@Transactional(readOnly = true)
public class PromocionService implements IPromocionService {

    IPromocionDao promocionDao;
    
    @Override
    @Transactional(readOnly = false)
    public void save(Promocion promocion) {
	// TODO Auto-generated method stub
	getPromocionDao().save(promocion);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Promocion promocion) {
	// TODO Auto-generated method stub
	getPromocionDao().update(promocion);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Promocion promocion) {
	// TODO Auto-generated method stub
	getPromocionDao().delete(promocion);
    }

    @Override
    public Promocion findByNombrePromocion(String nombrePromocion) {
	// TODO Auto-generated method stub
	return getPromocionDao().findByNombrePromocion(nombrePromocion);
    }

    @Override
    public List<Promocion> findAll() {
	// TODO Auto-generated method stub
	return getPromocionDao().findAll();
    }

    @Override
    public List<Promocion> findByEstado(EstadoPromocion estado) {
	// TODO Auto-generated method stub
	return getPromocionDao().findByEstado(estado);
    }

    @Override
    public List<Promocion> findByPlaya(Playa playa, EstadoPromocion estado) {
	// TODO Auto-generated method stub
	return getPromocionDao().findByPlaya(playa, estado);
    }

    @Override
    public List<Promocion> findByPlaya(Playa playa) {
	// TODO Auto-generated method stub
	return getPromocionDao().findByPlaya(playa);
    }
    
    @Override
    public List<Promocion> findByCategoria(CategoriaVehiculo categoria, Playa playa){
	return getPromocionDao().findByCategoria(categoria, playa);
    }
    
    @Override
    public List<Promocion> findByTipoEstadiaAndPlaya(TipoEstadia tipoEstadia, CategoriaVehiculo categoria, Playa playa){
	return getPromocionDao().findByTipoEstadiaAndPlaya(tipoEstadia, categoria, playa);
    }
    
    @Override
    public List<Promocion> findByPlayaAndTarifa(Playa playa, Tarifa tarifa){
	return getPromocionDao().findByPlayaAndTarifa(playa, tarifa);
    }

    public IPromocionDao getPromocionDao() {
        return promocionDao;
    }

    public void setPromocionDao(IPromocionDao promocionDao) {
        this.promocionDao = promocionDao;
    }

}
