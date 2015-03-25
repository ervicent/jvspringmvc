package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.MarcaVehiculo;

public interface IMarcaVehiculoDao {

    void save(MarcaVehiculo marcaVehiculo);

    void update(MarcaVehiculo marcaVehiculo);

    void delete(MarcaVehiculo marcaVehiculo);

    MarcaVehiculo findByNombreMarcaVehiculo(String nombreMarcaVehiculo);
    
    List<MarcaVehiculo> findAll();
}
