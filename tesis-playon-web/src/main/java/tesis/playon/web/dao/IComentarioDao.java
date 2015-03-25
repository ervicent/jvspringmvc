package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.Comentario;
import tesis.playon.web.model.Playa;

/**
 * @author garribere	
 *
 */
public interface IComentarioDao {

    void save(Comentario comentario);

    void update(Comentario comentario);

    void delete(Comentario comentario);

    List<Comentario> findByPlaya(Playa playa);
    
    List<Comentario> findAll();
}
