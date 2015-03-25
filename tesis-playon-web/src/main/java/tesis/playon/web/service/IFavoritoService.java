/**
 * 
 */
package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.Favorito;
import tesis.playon.web.model.Playa;

/**
 * @author pablo
 *
 */
public interface IFavoritoService {
    
    void save(Favorito favorito);

    void update(Favorito favorito);

    void delete(Favorito favorito);
    
    List<Favorito> findAll();
    
    List<Favorito> findByCliente(Cliente cliente);
    
    boolean isFavorito(Cliente cliente, Playa playa);

}
