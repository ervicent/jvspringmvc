/**
 * 
 */
package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.Barrio;

/**
 * @author pablo
 *
 */
public interface IBarrioService {

    void save(Barrio barrio);

    void update(Barrio barrio);

    void delete(Barrio barrio);

    Barrio findByNombreBarrio(String nombreBarrio);

    List<Barrio> findAll();
}
