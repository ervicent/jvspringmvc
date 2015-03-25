package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.ICategoriaVehiculoDao;
import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.service.ICategoriaVehiculoService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class CategoriaVehiculoService implements ICategoriaVehiculoService {

    ICategoriaVehiculoDao categoriaVehiculoDao;
    
    @Transactional(readOnly = false)
    @Override
    public void save(CategoriaVehiculo categoriaVehiculo) {
	getCategoriaVehiculoDao().save(categoriaVehiculo);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(CategoriaVehiculo categoriaVehiculo) {
	getCategoriaVehiculoDao().update(categoriaVehiculo);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(CategoriaVehiculo categoriaVehiculo) {
	getCategoriaVehiculoDao().delete(categoriaVehiculo);
    }

    @Override
    public List<CategoriaVehiculo> findAll() {
	return getCategoriaVehiculoDao().findAll();
    }

    @Override
    public CategoriaVehiculo findByNombreCategoriaVehiculo(String nombreCategoriaVehiculo) {
	return getCategoriaVehiculoDao().findByNombreCategoriaVehiculo(nombreCategoriaVehiculo);
    }

    public ICategoriaVehiculoDao getCategoriaVehiculoDao() {
        return categoriaVehiculoDao;
    }

    public void setCategoriaVehiculoDao(ICategoriaVehiculoDao categoriaVehiculoDao) {
        this.categoriaVehiculoDao = categoriaVehiculoDao;
    }

}