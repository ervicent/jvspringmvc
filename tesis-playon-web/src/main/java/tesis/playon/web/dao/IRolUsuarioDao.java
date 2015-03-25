package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.RolUsuario;

public interface IRolUsuarioDao {

    void save(RolUsuario rolUsuario);

    void update(RolUsuario rolUsuario);

    void delete(RolUsuario rolUsuario);

    List<RolUsuario> findAll();
    
    RolUsuario findByNombreRolUsuario(String nombreRolUsuario);

}
