/**
 * 
 */
package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.EstadoPromocion;

/**
 * @author pablo
 *
 */
public interface IEstadoPromocionService {

    void save(EstadoPromocion estadoPromocion);

    void update(EstadoPromocion estadoPromocion);

    void delete(EstadoPromocion estadoPromocion);

    EstadoPromocion findByNombreEstadoPromocion(String nombreEstadoPromocion);

    List<EstadoPromocion> findAll();
}
