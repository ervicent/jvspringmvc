/**
 * 
 */
package tesis.playon.web.service;

import tesis.playon.web.model.FotoUsuario;

/**
 * @author pablo
 *
 */
public interface IFotoUsuarioService {

    void save(FotoUsuario foto);

    void update(FotoUsuario foto);

    void delete(FotoUsuario foto);
    
}
