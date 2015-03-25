package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.RolesPorUsuario;

public interface IRolesPorUsuarioDao {

    void save(RolesPorUsuario rolesPorUsuario);

    void update(RolesPorUsuario rolesPorUsuario);

    void delete(RolesPorUsuario rolesPorUsuario);

    RolesPorUsuario findByNombreUsuario(String nombreUsuario);

    List<RolesPorUsuario> findAll();
}
