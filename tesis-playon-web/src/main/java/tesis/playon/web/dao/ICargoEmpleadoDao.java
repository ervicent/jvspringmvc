/**
 * 
 */
package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.CargoEmpleado;

/**
 * @author Pablo
 *
 */
public interface ICargoEmpleadoDao {
    
    void save(CargoEmpleado cargo);

    void update(CargoEmpleado cargo);

    void delete(CargoEmpleado cargo);

    CargoEmpleado findByNombreCargo(String nombreCargo);

    List<CargoEmpleado> findAll();
    
    CargoEmpleado findById(Integer id);
}
