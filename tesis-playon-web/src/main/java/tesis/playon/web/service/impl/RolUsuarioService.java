package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IRolUsuarioDao;
import tesis.playon.web.model.RolUsuario;
import tesis.playon.web.service.IRolUsuarioService;

@Transactional(readOnly = true)
public class RolUsuarioService implements IRolUsuarioService {

    IRolUsuarioDao rolUsuarioDao;
    
    @Transactional(readOnly = false)
    @Override
    public void save(RolUsuario rolUsuario) {
	getRolUsuarioDao().save(rolUsuario);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(RolUsuario rolUsuario) {
	getRolUsuarioDao().update(rolUsuario);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(RolUsuario rolUsuario) {
	getRolUsuarioDao().delete(rolUsuario);
    }

    @Override
    public List<RolUsuario> findAll() {
	return getRolUsuarioDao().findAll();
    }

    @Override
    public RolUsuario findByNombreRolUsuario(String nombreRolUsuario) {
	return getRolUsuarioDao().findByNombreRolUsuario(nombreRolUsuario);
    }

    public IRolUsuarioDao getRolUsuarioDao() {
        return rolUsuarioDao;
    }

    public void setRolUsuarioDao(IRolUsuarioDao rolUsuarioDao) {
        this.rolUsuarioDao = rolUsuarioDao;
    }

}