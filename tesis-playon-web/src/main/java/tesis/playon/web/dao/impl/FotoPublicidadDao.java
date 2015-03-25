/**
 * 
 */
package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IFotoPublicidadDao;
import tesis.playon.web.model.FotoPublicidad;

/**
 * @author pablo
 *
 */
public class FotoPublicidadDao implements IFotoPublicidadDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }
    
    /* (non-Javadoc)
     * @see tesis.playon.web.dao.IFotoPublicidadDao#save(tesis.playon.web.model.FotoPublicidad)
     */
    @Override
    public void save(FotoPublicidad foto) {
	// TODO Auto-generated method stub
	getSessionFactory().getCurrentSession().save(foto);
    }

    /* (non-Javadoc)
     * @see tesis.playon.web.dao.IFotoPublicidadDao#update(tesis.playon.web.model.FotoPublicidad)
     */
    @Override
    public void update(FotoPublicidad foto) {
	// TODO Auto-generated method stub
	getSessionFactory().getCurrentSession().update(foto);
    }

    /* (non-Javadoc)
     * @see tesis.playon.web.dao.IFotoPublicidadDao#delete(tesis.playon.web.model.FotoPublicidad)
     */
    @Override
    public void delete(FotoPublicidad foto) {
	// TODO Auto-generated method stub
	getSessionFactory().getCurrentSession().delete(foto);
    }

    /* (non-Javadoc)
     * @see tesis.playon.web.dao.IFotoPublicidadDao#findAll()
     */
    @Override
    public List<FotoPublicidad> findAll() {
	// TODO Auto-generated method stub
	List<FotoPublicidad> fotos = new ArrayList<FotoPublicidad>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from FotoPublicidad").list();
	if (!list.isEmpty()) {
	    for (Object obj : list) {
		fotos.add((FotoPublicidad) obj);
	    }
	    return fotos;
	}
	return null;
    }

    /* (non-Javadoc)
     * @see tesis.playon.web.dao.IFotoPublicidadDao#findByNombre(java.lang.String)
     */
    @Override
    public FotoPublicidad findByNombre(String nombre) {
	// TODO Auto-generated method stub
	List<FotoPublicidad> fotos = new ArrayList<FotoPublicidad>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from FotoPublicidad where nombre=?")
		.setParameter(0, nombre).list();
	if (!list.isEmpty()) {
	    for (Object obj : list) {
		fotos.add((FotoPublicidad) obj);
	    }
	    return fotos.get(0);
	}
	return null;
    }

}
