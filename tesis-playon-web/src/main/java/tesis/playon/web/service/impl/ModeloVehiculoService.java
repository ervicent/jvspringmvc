/**
 * 
 */
package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IModeloVehiculoDao;
import tesis.playon.web.model.MarcaVehiculo;
import tesis.playon.web.model.ModeloVehiculo;
import tesis.playon.web.service.IModeloVehiculoService;

/**
 * @author pablo
 *
 */
@Transactional(readOnly = true)
public class ModeloVehiculoService implements IModeloVehiculoService {

    IModeloVehiculoDao modeloVehiculoDao;
    
    @Transactional(readOnly = false)
    @Override
    public void save(ModeloVehiculo modelo) {
	// TODO Auto-generated method stub
	getModeloVehiculoDao().save(modelo);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(ModeloVehiculo modelo) {
	// TODO Auto-generated method stub
	getModeloVehiculoDao().update(modelo);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(ModeloVehiculo modelo) {
	// TODO Auto-generated method stub
	getModeloVehiculoDao().delete(modelo);
    }

    @Override
    public ModeloVehiculo findByNombreModeloVehiculo(String nombreModelo) {
	// TODO Auto-generated method stub
	return getModeloVehiculoDao().findByNombreModeloVehiculo(nombreModelo);
    }

    @Override
    public List<ModeloVehiculo> findAll() {
	// TODO Auto-generated method stub
	return getModeloVehiculoDao().findAll();
    }
    
    @Override
    public List<ModeloVehiculo> findByMarca(MarcaVehiculo marca) {
	// TODO Auto-generated method stub
	return getModeloVehiculoDao().findByMarca(marca);
    }

    public IModeloVehiculoDao getModeloVehiculoDao() {
        return modeloVehiculoDao;
    }

    public void setModeloVehiculoDao(IModeloVehiculoDao modeloVehiculoDao) {
        this.modeloVehiculoDao = modeloVehiculoDao;
    }
}