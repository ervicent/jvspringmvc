package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IComentarioDao;
import tesis.playon.web.model.Comentario;
import tesis.playon.web.model.Playa;
import tesis.playon.web.service.IComentarioService;

@Transactional(readOnly = true)
public class ComentarioService implements IComentarioService {

    IComentarioDao comentarioDao;
    
    @Transactional(readOnly = false)
    @Override
    public void save(Comentario comentario) {
	// TODO Auto-generated method stub
	getComentarioDao().save(comentario);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(Comentario comentario) {
	// TODO Auto-generated method stub
	getComentarioDao().update(comentario);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Comentario comentario) {
	// TODO Auto-generated method stub
	getComentarioDao().delete(comentario);
    }

    @Override
    public List<Comentario> findByPlaya(Playa playa) {
	// TODO Auto-generated method stub
	return getComentarioDao().findByPlaya(playa);
    }

    @Override
    public List<Comentario> findAll() {
	// TODO Auto-generated method stub
	return getComentarioDao().findAll();
    }

    public IComentarioDao getComentarioDao() {
        return comentarioDao;
    }

    public void setComentarioDao(IComentarioDao comentarioDao) {
        this.comentarioDao = comentarioDao;
    }

}
