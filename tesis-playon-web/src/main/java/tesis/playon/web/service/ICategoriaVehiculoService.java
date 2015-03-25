package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.CategoriaVehiculo;

/**
 * 
 * @author gmorales
 *
 */
public interface ICategoriaVehiculoService {

    void save(CategoriaVehiculo categoriaVehiculo);

    void update(CategoriaVehiculo categoriaVehiculo);

    void delete(CategoriaVehiculo categoriaVehiculo);

    List<CategoriaVehiculo> findAll();

    CategoriaVehiculo findByNombreCategoriaVehiculo(String nombreCategoriaVehiculo);

}