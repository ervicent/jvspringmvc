package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IFavoritoDao;
import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.Favorito;
import tesis.playon.web.model.Playa;

/**
 * @author Pablo
 * 
 */
public class FavoritoDao implements IFavoritoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(Favorito favorito) {
	getSessionFactory().getCurrentSession().save(favorito);
    }

    public void update(Favorito favorito) {
	getSessionFactory().getCurrentSession().update(favorito);
    }

    public void delete(Favorito favorito) {
	getSessionFactory().getCurrentSession().delete(favorito);
    }

    public List<Favorito> findAll() {
	List<Favorito> favoritos = new ArrayList<Favorito>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Favorito").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		favoritos.add((Favorito) object);
	    }
	    return favoritos;
	}
	return null;
    }

    public List<Favorito> findByCliente(Cliente cliente) {
	List<Favorito> favoritos = new ArrayList<Favorito>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Favorito where cliente=?")
		.setParameter(0, cliente).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		favoritos.add((Favorito) object);
	    }
	    return favoritos;
	}
	return null;
    }
    
    public boolean isFavorito(Cliente cliente, Playa playa){
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Favorito where cliente=? and playa=?")
		.setParameter(0, cliente)
		.setParameter(1, playa).list();
	if (list.isEmpty()) {
	    return false;
	}
	return true;
    }
}
