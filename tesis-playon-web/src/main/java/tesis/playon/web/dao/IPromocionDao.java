package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.model.EstadoPromocion;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Promocion;
import tesis.playon.web.model.Tarifa;
import tesis.playon.web.model.TipoEstadia;

/**
 * @author Pablo
 *
 */
public interface IPromocionDao {

    void save(Promocion promocion);

    void update(Promocion promocion);

    void delete(Promocion promocion);

    Promocion findByNombrePromocion(String nombrePromocion);

    List<Promocion> findAll();
    
    List<Promocion> findByEstado(EstadoPromocion estado);
    
    List<Promocion> findByPlaya(Playa playa, EstadoPromocion estado);
    
    List<Promocion> findByPlaya(Playa playa);
    
    List<Promocion> findByCategoria(CategoriaVehiculo categoria, Playa playa);
    
    List<Promocion> findByTipoEstadiaAndPlaya(TipoEstadia tipoEstadia, CategoriaVehiculo categoria, Playa playa);
    
    List<Promocion> findByPlayaAndTarifa(Playa playa, Tarifa tarifa);
}
