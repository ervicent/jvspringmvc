/**
 * 
 */
package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.MarcaVehiculo;
import tesis.playon.web.model.ModeloVehiculo;

/**
 * @author pablo
 *
 */
public interface IModeloVehiculoService {

    void save(ModeloVehiculo modelo);

    void update(ModeloVehiculo modelo);

    void delete(ModeloVehiculo modelo);

    ModeloVehiculo findByNombreModeloVehiculo(String nombreModelo);
    
    List<ModeloVehiculo> findByMarca(MarcaVehiculo marca);
    
    List<ModeloVehiculo> findAll();
}
