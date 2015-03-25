package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.EstadoPromocion;

public interface IEstadoPromocionDao {

    void save(EstadoPromocion estadoPromocion);

    void update(EstadoPromocion estadoPromocion);

    void delete(EstadoPromocion estadoPromocion);

    EstadoPromocion findByNombreEstadoPromocion(String nombreEstadoPromocion);

    List<EstadoPromocion> findAll();
}
