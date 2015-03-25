package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.Favorito;
import tesis.playon.web.model.Playa;

/**
 * @author Pablo
 *
 */
public interface IFavoritoDao {

    void save(Favorito favorito);

    void update(Favorito favorito);

    void delete(Favorito favorito);
    
    List<Favorito> findAll();
    
    List<Favorito> findByCliente(Cliente cliente);
    
    boolean isFavorito(Cliente cliente, Playa playa);
}
