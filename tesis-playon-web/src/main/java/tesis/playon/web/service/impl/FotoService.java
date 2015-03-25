package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IFotoDao;
import tesis.playon.web.model.Foto;
import tesis.playon.web.model.PerfilPlaya;
import tesis.playon.web.service.IFotoService;
/**
 * 
 * @author pablo
 *
 */
@Transactional(readOnly = true)
public class FotoService implements IFotoService {
    
    IFotoDao fotoDao;
    
    @Transactional(readOnly = false)
    @Override
    public void save(Foto foto) {
	// TODO Auto-generated method stub
	getFotoDao().save(foto);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(Foto foto) {
	// TODO Auto-generated method stub
	getFotoDao().update(foto);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Foto foto) {
	// TODO Auto-generated method stub
	getFotoDao().delete(foto);
    }

    @Override
    public Foto findByLinkFoto(String link) {
	// TODO Auto-generated method stub
	return getFotoDao().findByLinkFoto(link);
    }

    @Override
    public List<Foto> findAll() {
	// TODO Auto-generated method stub
	return getFotoDao().findAll();
    }

    @Override
    public Integer obtenerUltimoID() {
	// TODO Auto-generated method stub
	return getFotoDao().obtenerUltimoID();
    }
    
    @Override
    public List<Foto> findByPlaya(PerfilPlaya perfil){
	return getFotoDao().findByPlaya(perfil);
    }

    public IFotoDao getFotoDao() {
        return fotoDao;
    }

    public void setFotoDao(IFotoDao fotoDao) {
        this.fotoDao = fotoDao;
    }
}