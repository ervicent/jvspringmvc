package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.EstadoPlaya;

public interface IEstadoPlayaDao {

    void save(EstadoPlaya estadoPlaya);

    void update(EstadoPlaya estadoPlaya);

    void delete(EstadoPlaya estadoPlaya);

    EstadoPlaya findByNombreEstadoPlaya(String nombre);

    List<EstadoPlaya> findAll();
}
