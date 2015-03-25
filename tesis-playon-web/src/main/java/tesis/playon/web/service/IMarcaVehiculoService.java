/**
 * 
 */
package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.MarcaVehiculo;

/**
 * @author pablo
 *
 */
public interface IMarcaVehiculoService {

    void save(MarcaVehiculo marcaVehiculo);

    void update(MarcaVehiculo marcaVehiculo);

    void delete(MarcaVehiculo marcaVehiculo);

    MarcaVehiculo findByNombreMarcaVehiculo(String nombreMarcaVehiculo);
    
    List<MarcaVehiculo> findAll();
}
