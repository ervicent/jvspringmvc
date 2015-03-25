package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Usuario;

public interface IUsuarioDao {

    void save(Usuario usuario);

    void update(Usuario usuario);

    void delete(Usuario usuario);

    Usuario findByNombreUsuario(String usuario);

    List<Usuario> findByPlaya(Playa playa);
    
    Usuario findGerenteByPlaya(Playa playa);
    
    List<Usuario> findAll();

    boolean existeEmail(String email);
    
    boolean existeUserName(String userName);
    
    boolean existeEmail(String email,String userName);
}