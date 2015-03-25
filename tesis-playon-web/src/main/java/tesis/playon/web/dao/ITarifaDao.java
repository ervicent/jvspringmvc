package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Tarifa;
import tesis.playon.web.model.TipoEstadia;

public interface ITarifaDao {

    void save(Tarifa tarifa);

    void update(Tarifa tarifa);

    void delete(Tarifa tarifa);

    int deleteTarifasPlaya(Playa playa);
    
    List<Tarifa> findByPlaya(Playa playa);

    List<Tarifa> findAll();

    List<Tarifa> findTarifaVigenteByPlaya(Playa playa);

    List<Tarifa> findTarifaVigenteByPlayaAndCategoriaVehiculo(Playa playa, CategoriaVehiculo categoriaVehiculo);

    Tarifa findTarifaVigenteByPlayaAndCategoriaAndTipoEstadia(Playa playa, CategoriaVehiculo categoriaVehiculo, TipoEstadia tipoEstadia);
}
