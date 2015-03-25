/**
 * 
 */
package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.CargoEmpleado;

/**
 * @author Pablo
 *
 */
public interface ICargoEmpleadoService {
    
    void save(CargoEmpleado cargo);

    void update(CargoEmpleado cargo);

    void delete(CargoEmpleado cargo);

    CargoEmpleado findByNombreCargo(String nombreCargo);

    List<CargoEmpleado> findAll();
    
    CargoEmpleado findById(Integer id);
}