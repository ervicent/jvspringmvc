/**
 * 
 */
package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.Comentario;
import tesis.playon.web.model.Playa;

/**
 * @author pablo
 *
 */
public interface IComentarioService {
    
    void save(Comentario comentario);

    void update(Comentario comentario);

    void delete(Comentario comentario);

    List<Comentario> findByPlaya(Playa playa);
    
    List<Comentario> findAll();

}
