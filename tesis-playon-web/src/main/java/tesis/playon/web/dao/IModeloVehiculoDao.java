package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.MarcaVehiculo;
import tesis.playon.web.model.ModeloVehiculo;

/**
 * @author Pablo
 *
 */
public interface IModeloVehiculoDao {

    void save(ModeloVehiculo modelo);

    void update(ModeloVehiculo modelo);

    void delete(ModeloVehiculo modelo);

    ModeloVehiculo findByNombreModeloVehiculo(String nombreModelo);
    
    List<ModeloVehiculo> findByMarca(MarcaVehiculo marca);
    
    List<ModeloVehiculo> findAll();
}
