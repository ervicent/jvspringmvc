/**
 * 
 */
package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.Calificacion;
import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.Playa;

/**
 * @author pablo
 *
 */
public interface ICalificacionService {

    void save(Calificacion calificacion);

    void update(Calificacion calificacion);

    void delete(Calificacion calificacion);

    List<Calificacion> findByPlaya(Playa playa);
    
    List<Calificacion> findAll();
    
    boolean isRate(Playa playa, Cliente cliente);
    
}
