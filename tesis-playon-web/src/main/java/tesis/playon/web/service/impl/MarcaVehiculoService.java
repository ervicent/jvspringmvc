package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IMarcaVehiculoDao;
import tesis.playon.web.model.MarcaVehiculo;
import tesis.playon.web.service.IMarcaVehiculoService;

/**
 * 
 * @author pablo
 * 
 */
@Transactional(readOnly = true)
public class MarcaVehiculoService implements IMarcaVehiculoService {

    IMarcaVehiculoDao marcaVehiculoDao;

    @Transactional(readOnly = false)
    @Override
    public void save(MarcaVehiculo marcaVehiculo) {
	// TODO Auto-generated method stub
	getMarcaVehiculoDao().save(marcaVehiculo);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(MarcaVehiculo marcaVehiculo) {
	// TODO Auto-generated method stub
	getMarcaVehiculoDao().update(marcaVehiculo);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(MarcaVehiculo marcaVehiculo) {
	// TODO Auto-generated method stub
	getMarcaVehiculoDao().delete(marcaVehiculo);
    }

    @Override
    public MarcaVehiculo findByNombreMarcaVehiculo(String nombreMarcaVehiculo) {
	// TODO Auto-generated method stub
	return getMarcaVehiculoDao().findByNombreMarcaVehiculo(nombreMarcaVehiculo);
    }

    @Override
    public List<MarcaVehiculo> findAll() {
	// TODO Auto-generated method stub
	return getMarcaVehiculoDao().findAll();
    }

    public IMarcaVehiculoDao getMarcaVehiculoDao() {
        return marcaVehiculoDao;
    }

    public void setMarcaVehiculoDao(IMarcaVehiculoDao marcaVehiculoDao) {
        this.marcaVehiculoDao = marcaVehiculoDao;
    }

}
