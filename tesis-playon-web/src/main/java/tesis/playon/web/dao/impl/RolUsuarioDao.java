package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IRolUsuarioDao;
import tesis.playon.web.model.RolUsuario;

/**
 * 
 * @author gmorales
 * 
 */
public class RolUsuarioDao implements IRolUsuarioDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(RolUsuario rolUsuario) {
	getSessionFactory().getCurrentSession().save(rolUsuario);
    }

    public void update(RolUsuario rolUsuario) {
	getSessionFactory().getCurrentSession().update(rolUsuario);
    }

    public void delete(RolUsuario rolUsuario) {
	getSessionFactory().getCurrentSession().delete(rolUsuario);
    }

    public List<RolUsuario> findAll() {
	List<RolUsuario> listaRolUsuario = new ArrayList<RolUsuario>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from RolUsuario order by nombre").list();
	if (!list.isEmpty()) {
	    for (Object obj : list) {
		listaRolUsuario.add((RolUsuario) obj);
	    }
	    return listaRolUsuario;
	}
	return null;
    }

    public RolUsuario findByNombreRolUsuario(String nombreRolUsuario) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from RolUsuario where nombre=?")
		.setParameter(0, nombreRolUsuario).list();
	if (!list.isEmpty())
	    return (RolUsuario) list.get(0);
	return null;
    }

}
