/**
 * 
 */
package tesis.playon.web.dao;

import tesis.playon.web.model.FotoUsuario;

/**
 * @author pablo
 *
 */
public interface IFotoUsuarioDao {

    void save(FotoUsuario foto);

    void update(FotoUsuario foto);

    void delete(FotoUsuario foto);
    
}
