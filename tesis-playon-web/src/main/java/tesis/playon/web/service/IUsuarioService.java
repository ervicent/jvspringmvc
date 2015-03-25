package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Usuario;
/**
 * 
 * @author pablo
 *
 */
public interface IUsuarioService {

    void save(Usuario usuario);

    void update(Usuario usuario);

    void delete(Usuario usuario);

    List<Usuario> findAll();

    Usuario findByNombreUsuario(String nombreUsuario);
    
    List<Usuario> findByPlaya(Playa playa);
    
    Usuario findGerenteByPlaya(Playa playa);
    
    boolean existeEmail(String email);

    boolean existeUserName(String userName);
    
    boolean existeEmail(String email,String userName);
}