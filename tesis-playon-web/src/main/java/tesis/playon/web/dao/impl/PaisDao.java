package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IPaisDao;
import tesis.playon.web.model.Pais;
import tesis.playon.web.model.Provincia;

/**
 * 
 * @author gmorales
 * 
 */
public class PaisDao implements IPaisDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }
    
    public void save(Pais pais) {
	getSessionFactory().getCurrentSession().save(pais);
    }

    public void update(Pais pais) {
	getSessionFactory().getCurrentSession().update(pais);
    }

    public void delete(Pais pais) {
	getSessionFactory().getCurrentSession().delete(pais);
    }

    public Pais findByNombrePais(String nombrePais) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Pais where nombre=?")
		.setParameter(0, nombrePais).list();
	return (Pais) list.get(0);
    }

    public List<Pais> findAll() {
	List<Pais> paises = new ArrayList<Pais>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Pais order by nombre").list();
	for (Object object : list) {
	    paises.add((Pais) object);
	}
	return paises;
    }
    
    public Pais findByPaisId(Integer id) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Pais where id=?")
		.setParameter(0, id).list();
	return (Pais) list.get(0);
    }
    
    public List<Provincia> getProvincias(Integer idPais){
	List<Provincia> provincias = new ArrayList<Provincia>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Provincia where pais.id=?")
		.setParameter(0, idPais).list();
	for (Object object : list) {
	    provincias.add((Provincia)object);
	}
	return provincias;
    }
}