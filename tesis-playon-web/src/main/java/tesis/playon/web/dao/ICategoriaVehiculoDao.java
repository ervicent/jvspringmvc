package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.CategoriaVehiculo;

/**
 * @author Pablo
 *
 */
public interface ICategoriaVehiculoDao {

    void save(CategoriaVehiculo categoria);

    void update(CategoriaVehiculo categoria);

    void delete(CategoriaVehiculo categoria);

    CategoriaVehiculo findByNombreCategoriaVehiculo(String nombreCategoria);
    
    List<CategoriaVehiculo> findAll();
}
