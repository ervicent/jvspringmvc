/**
 * 
 */
package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.ColorVehiculo;

/**
 * @author pablo
 *
 */
public interface IColorVehiculoService {

    void save(ColorVehiculo color);

    void update(ColorVehiculo color);

    void delete(ColorVehiculo color);

    ColorVehiculo findByNombreColorVehiculo(String nombreColor);
    
    List<ColorVehiculo> findAll();
}
