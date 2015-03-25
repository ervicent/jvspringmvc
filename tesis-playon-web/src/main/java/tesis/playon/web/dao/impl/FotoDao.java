package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IFotoDao;
import tesis.playon.web.model.Foto;
import tesis.playon.web.model.PerfilPlaya;

/**
 * @author Pablo
 * 
 */
public class FotoDao implements IFotoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(Foto foto) {
	getSessionFactory().getCurrentSession().save(foto);
    }

    public void update(Foto foto) {
	getSessionFactory().getCurrentSession().update(foto);
    }

    public void delete(Foto foto) {
	getSessionFactory().getCurrentSession().delete(foto);
    }

    public Foto findByLinkFoto(String link) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Foto where nombre=?")
		.setParameter(0, link).list();
	return (Foto) list.get(0);
    }

    public List<Foto> findAll() {
	List<Foto> fotos = new ArrayList<Foto>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Foto").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		fotos.add((Foto) object);
	    }
	    return fotos;
	}
	return null;
    }

    public Integer obtenerUltimoID() {
	List<?> list = getSessionFactory().getCurrentSession().createSQLQuery("SELECT MAX(fotoID) FROM foto").list();
	Integer ultimoID;
	if (list.get(0) == null)
	    ultimoID = 1;
	else
	    ultimoID = ((Integer) list.get(0)) + 1;
	return ultimoID;
    }

    public List<Foto> findByPlaya(PerfilPlaya perfil) {
	List<Foto> fotos = new ArrayList<Foto>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Foto where perfilPlaya=?")
		.setParameter(0, perfil).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		fotos.add((Foto) object);
	    }
	    return fotos;
	}
	return null;
    }
}
