/**
 * 
 */
package tesis.playon.web.service.impl;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IFotoUsuarioDao;
import tesis.playon.web.model.FotoUsuario;
import tesis.playon.web.service.IFotoUsuarioService;

/**
 * @author pablo
 *
 */
@Transactional(readOnly = true)
public class FotoUsuarioService implements IFotoUsuarioService {

    IFotoUsuarioDao fotoUsuarioDao;
    
    /* (non-Javadoc)
     * @see tesis.playon.web.service.IFotoUsuarioService#save(tesis.playon.web.model.FotoUsuario)
     */
    @Transactional(readOnly = false)
    @Override
    public void save(FotoUsuario foto) {
	// TODO Auto-generated method stub
	getFotoUsuarioDao().save(foto);
    }

    /* (non-Javadoc)
     * @see tesis.playon.web.service.IFotoUsuarioService#update(tesis.playon.web.model.FotoUsuario)
     */
    @Transactional(readOnly = false)
    @Override
    public void update(FotoUsuario foto) {
	// TODO Auto-generated method stub
	getFotoUsuarioDao().update(foto);
    }

    /* (non-Javadoc)
     * @see tesis.playon.web.service.IFotoUsuarioService#delete(tesis.playon.web.model.FotoUsuario)
     */
    @Transactional(readOnly = false)
    @Override
    public void delete(FotoUsuario foto) {
	// TODO Auto-generated method stub
	getFotoUsuarioDao().delete(foto);
    }

    /**
     * @return the fotoUsuarioDao
     */
    public IFotoUsuarioDao getFotoUsuarioDao() {
        return fotoUsuarioDao;
    }

    /**
     * @param fotoUsuarioDao the fotoUsuarioDao to set
     */
    public void setFotoUsuarioDao(IFotoUsuarioDao fotoUsuarioDao) {
        this.fotoUsuarioDao = fotoUsuarioDao;
    }

}
