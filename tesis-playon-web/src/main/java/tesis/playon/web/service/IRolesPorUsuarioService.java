package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.RolesPorUsuario;

public interface IRolesPorUsuarioService {

    void save(RolesPorUsuario rol);

    void update(RolesPorUsuario rol);

    void delete(RolesPorUsuario rol);

    List<RolesPorUsuario> findAll();

    RolesPorUsuario findByNombreUsuario(String nombreUsuario);

}