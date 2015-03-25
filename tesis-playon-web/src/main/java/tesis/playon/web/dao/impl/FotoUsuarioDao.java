/**
 * 
 */
package tesis.playon.web.dao.impl;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IFotoUsuarioDao;
import tesis.playon.web.model.FotoUsuario;

/**
 * @author pablo
 *
 */
public class FotoUsuarioDao implements IFotoUsuarioDao {

    private SessionFactory sessionFactory;
    
    /* (non-Javadoc)
     * @see tesis.playon.web.dao.IFotoUsuarioDao#save(tesis.playon.web.model.FotoUsuario)
     */
    @Override
    public void save(FotoUsuario foto) {
	// TODO Auto-generated method stub
	getSessionFactory().getCurrentSession().save(foto);
    }

    /* (non-Javadoc)
     * @see tesis.playon.web.dao.IFotoUsuarioDao#update(tesis.playon.web.model.FotoUsuario)
     */
    @Override
    public void update(FotoUsuario foto) {
	// TODO Auto-generated method stub
	getSessionFactory().getCurrentSession().update(foto);
    }

    /* (non-Javadoc)
     * @see tesis.playon.web.dao.IFotoUsuarioDao#delete(tesis.playon.web.model.FotoUsuario)
     */
    @Override
    public void delete(FotoUsuario foto) {
	// TODO Auto-generated method stub
	getSessionFactory().getCurrentSession().delete(foto);
    }

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }
    
}
