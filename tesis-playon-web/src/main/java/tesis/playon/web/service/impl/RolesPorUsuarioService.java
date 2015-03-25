package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IRolesPorUsuarioDao;
import tesis.playon.web.model.RolesPorUsuario;
import tesis.playon.web.service.IRolesPorUsuarioService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class RolesPorUsuarioService implements IRolesPorUsuarioService {

    IRolesPorUsuarioDao rolesPorUsuarioDao;
    

    @Transactional(readOnly = false)
    @Override
    public void save(RolesPorUsuario rolPorUser) {
	getRolesPorUsuarioDao().save(rolPorUser);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(RolesPorUsuario rolPorUser) {
	getRolesPorUsuarioDao().update(rolPorUser);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(RolesPorUsuario rolPorUser) {
	getRolesPorUsuarioDao().delete(rolPorUser);
    }

    @Override
    public List<RolesPorUsuario> findAll() {
	return getRolesPorUsuarioDao().findAll();
    }

    @Override
    public RolesPorUsuario findByNombreUsuario(String nombreUser) {
	return getRolesPorUsuarioDao().findByNombreUsuario(nombreUser);
    }

    public IRolesPorUsuarioDao getRolesPorUsuarioDao() {
        return rolesPorUsuarioDao;
    }

    public void setRolesPorUsuarioDao(IRolesPorUsuarioDao rolesPorUsuarioDao) {
        this.rolesPorUsuarioDao = rolesPorUsuarioDao;
    }
}