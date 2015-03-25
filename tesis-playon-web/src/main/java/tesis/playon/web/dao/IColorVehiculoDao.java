package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.ColorVehiculo;

/**
 * @author Pablo
 *
 */
public interface IColorVehiculoDao {
    
    void save(ColorVehiculo color);

    void update(ColorVehiculo color);

    void delete(ColorVehiculo color);

    ColorVehiculo findByNombreColorVehiculo(String nombreColor);
    
    List<ColorVehiculo> findAll();
}
