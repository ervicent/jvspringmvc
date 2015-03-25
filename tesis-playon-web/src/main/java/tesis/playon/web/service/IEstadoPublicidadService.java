/**
 * 
 */
package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.EstadoPublicidad;

/**
 * @author pablo
 *
 */
public interface IEstadoPublicidadService {

    void save(EstadoPublicidad estadoPublicidad);

    void update(EstadoPublicidad estadoPublicidad);

    void delete(EstadoPublicidad estadoPublicidad);

    EstadoPublicidad findByNombreEstadoPublicidad(String nombreEstadoPublicidad);
    
    List<EstadoPublicidad> findAll();
    
}
