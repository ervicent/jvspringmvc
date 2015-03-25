package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.EstadoPlaya;

/**
 * 
 * @author gmorales
 *
 */
public interface IEstadoPlayaService {

    void save(EstadoPlaya estadoPlaya);

    void update(EstadoPlaya estadoPlaya);

    void delete(EstadoPlaya estadoPlaya);

    List<EstadoPlaya> findAll();

    EstadoPlaya findByNombreEstadoPlaya(String nombreEstadoPlaya);

}