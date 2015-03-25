package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IColorVehiculoDao;
import tesis.playon.web.model.ColorVehiculo;
import tesis.playon.web.service.IColorVehiculoService;
/**
 * 
 * @author pablo
 *
 */
@Transactional(readOnly = true)
public class ColorVehiculoService implements IColorVehiculoService {

    IColorVehiculoDao colorVehiculoDao;
    
    @Transactional(readOnly = false)
    @Override
    public void save(ColorVehiculo color) {
	// TODO Auto-generated method stub
	getColorVehiculoDao().save(color);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(ColorVehiculo color) {
	// TODO Auto-generated method stub
	getColorVehiculoDao().update(color);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(ColorVehiculo color) {
	// TODO Auto-generated method stub
	getColorVehiculoDao().delete(color);
    }

    @Override
    public ColorVehiculo findByNombreColorVehiculo(String nombreColor) {
	// TODO Auto-generated method stub
	return getColorVehiculoDao().findByNombreColorVehiculo(nombreColor);
    }

    @Override
    public List<ColorVehiculo> findAll() {
	// TODO Auto-generated method stub
	return getColorVehiculoDao().findAll();
    }

    public IColorVehiculoDao getColorVehiculoDao() {
        return colorVehiculoDao;
    }

    public void setColorVehiculoDao(IColorVehiculoDao colorVehiculoDao) {
        this.colorVehiculoDao = colorVehiculoDao;
    }

}
