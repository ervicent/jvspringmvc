package tesis.playon.web.service;

import java.util.Date;
import java.util.List;

import tesis.playon.web.model.DetalleEstadia;
import tesis.playon.web.model.Estadia;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Vehiculo;

/**
 * 
 * @author gmorales
 * 
 */
public interface IDetalleEstadiaService {

    void save(DetalleEstadia detalleEstadia);

    void update(DetalleEstadia detalleEstadia);

    void delete(DetalleEstadia detalleEstadia);

    List<DetalleEstadia> findAll();

    List<DetalleEstadia> findByEstadia(Estadia estadia);

    List<DetalleEstadia> findByHorarios(Estadia estadia, Date fechaInicio, Date fechaFin);

    DetalleEstadia findByVehiculoDetalleEstadia(Vehiculo vehiculo);
    
    Integer[] findEstadiasByPlaya(Playa playa, Date fechaDesde, Date fechaHasta);

    List<String[]> findEstadiasByVehiculoByPeriodo(Vehiculo vehiculo, Date fechaDesde, Date fechaHasta);
}