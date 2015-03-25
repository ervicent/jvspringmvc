package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.ITarifaDao;
import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Tarifa;
import tesis.playon.web.model.TipoEstadia;
import tesis.playon.web.service.ITarifaService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class TarifaService implements ITarifaService {

    ITarifaDao tarifaDao;

    @Transactional(readOnly = false)
    @Override
    public void save(Tarifa tarifa) {
	getTarifaDao().save(tarifa);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(Tarifa tarifa) {
	getTarifaDao().update(tarifa);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Tarifa tarifa) {
	getTarifaDao().delete(tarifa);
    }

    @Transactional(readOnly = false)
    @Override
    public int deleteTarifasPlaya(Playa playa) {
	return getTarifaDao().deleteTarifasPlaya(playa);
    }
    
    @Override
    public List<Tarifa> findAll() {
	return getTarifaDao().findAll();
    }
    
    @Override
    public List<Tarifa> findByPlaya(Playa playa){
	return getTarifaDao().findByPlaya(playa);
    }
    
    @Override
    public List<Tarifa> findTarifaVigenteByPlaya(Playa playa){
	return  getTarifaDao().findTarifaVigenteByPlaya(playa);
    }
    
    @Override
    public List<Tarifa> findTarifaVigenteByPlayaAndCategoriaVehiculo(Playa playa, CategoriaVehiculo categoriaVehiculo) {
	return getTarifaDao().findTarifaVigenteByPlayaAndCategoriaVehiculo(playa, categoriaVehiculo);
    }
    
    @Override
    public Tarifa findTarifaVigenteByPlayaAndCategoriaAndTipoEstadia(Playa playa, CategoriaVehiculo categoriaVehiculo, TipoEstadia tipoEstadia){
	return getTarifaDao().findTarifaVigenteByPlayaAndCategoriaAndTipoEstadia(playa, categoriaVehiculo, tipoEstadia);
    }

    public ITarifaDao getTarifaDao() {
	return tarifaDao;
    }

    public void setTarifaDao(ITarifaDao tarifaDao) {
	this.tarifaDao = tarifaDao;
    }

}