package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.impl.CargoEmpleadoDao;
import tesis.playon.web.model.CargoEmpleado;
import tesis.playon.web.service.ICargoEmpleadoService;

/**
 * 
 * @author Pablo
 * 
 */
@Transactional(readOnly = true)
public class CargoEmpleadoService implements ICargoEmpleadoService {

    CargoEmpleadoDao cargoEmpleadoDao;

    @Transactional(readOnly = false)
    @Override
    public void save(CargoEmpleado cargo) {
	getCargoEmpleadoDao().save(cargo);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(CargoEmpleado cargo) {
	getCargoEmpleadoDao().update(cargo);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(CargoEmpleado cargo) {
	getCargoEmpleadoDao().delete(cargo);
    }

    @Override
    public CargoEmpleado findByNombreCargo(String nombreCargo) {
	return getCargoEmpleadoDao().findByNombreCargo(nombreCargo);
    }

    @Override
    public List<CargoEmpleado> findAll() {
	return getCargoEmpleadoDao().findAll();
    }

    @Override
    public CargoEmpleado findById(Integer id) {
	return getCargoEmpleadoDao().findById(id);
    }

    public CargoEmpleadoDao getCargoEmpleadoDao() {
	return cargoEmpleadoDao;
    }

    public void setCargoEmpleadoDao(CargoEmpleadoDao cargoEmpleadoDao) {
	this.cargoEmpleadoDao = cargoEmpleadoDao;
    }

}
