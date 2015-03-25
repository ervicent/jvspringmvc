/**
 * 
 */
package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.FotoPublicidad;

/**
 * @author pablo
 *
 */
public interface IFotoPublicidadService {

    void save(FotoPublicidad foto);

    void update(FotoPublicidad foto);

    void delete(FotoPublicidad foto);

    List<FotoPublicidad> findAll();
    
    FotoPublicidad findByNombre(String nombre);
    
}
