package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IUsuarioDao;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IUsuarioService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class UsuarioService implements IUsuarioService {

    IUsuarioDao usuarioDao;

    @Transactional(readOnly = false)
    @Override
    public void save(Usuario usuario) {
	getUsuarioDao().save(usuario);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(Usuario usuario) {
	getUsuarioDao().update(usuario);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Usuario usuario) {
	getUsuarioDao().delete(usuario);
    }

    @Override
    public List<Usuario> findAll() {
	return getUsuarioDao().findAll();
    }

    @Override
    public Usuario findByNombreUsuario(String nombreUsuario) {
	return getUsuarioDao().findByNombreUsuario(nombreUsuario);
    }

    public IUsuarioDao getUsuarioDao() {
	return usuarioDao;
    }

    public void setUsuarioDao(IUsuarioDao usuarioDao) {
	this.usuarioDao = usuarioDao;
    }

    @Override
    public List<Usuario> findByPlaya(Playa playa) {
	return getUsuarioDao().findByPlaya(playa);
    }

    @Override
    public Usuario findGerenteByPlaya(Playa playa) {
	return getUsuarioDao().findGerenteByPlaya(playa);
    }
    
    @Override
    public boolean existeEmail(String email){
	return getUsuarioDao().existeEmail(email);
    }
    
    @Override
    public boolean existeUserName(String userName){
	return getUsuarioDao().existeUserName(userName);
    }
    
    @Override
    public boolean existeEmail(String email,String userName){
	return getUsuarioDao().existeEmail(email, userName);
    }
}