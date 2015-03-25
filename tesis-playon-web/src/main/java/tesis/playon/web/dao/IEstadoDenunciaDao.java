package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.EstadoDenuncia;

/**
 * @author Pablo
 *
 */
public interface IEstadoDenunciaDao {

    void save(EstadoDenuncia estadoDenuncia);

    void update(EstadoDenuncia estadoDenuncia);

    void delete(EstadoDenuncia estadoDenuncia);

    EstadoDenuncia findByNombreEstadoDenuncia(String nombreDenuncia);
    
    List<EstadoDenuncia> findAll();
}
