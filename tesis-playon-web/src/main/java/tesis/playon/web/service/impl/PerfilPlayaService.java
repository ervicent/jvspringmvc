package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IPerfilPlayaDao;
import tesis.playon.web.model.PerfilPlaya;
import tesis.playon.web.model.Playa;
import tesis.playon.web.service.IPerfilPlayaService;

@Transactional(readOnly = true)
public class PerfilPlayaService implements IPerfilPlayaService {

    IPerfilPlayaDao perfilPlayaDao;
    
    @Transactional(readOnly = false)
    @Override
    public void save(PerfilPlaya perfilPlaya) {
	// TODO Auto-generated method stub
	getPerfilPlayaDao().save(perfilPlaya);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(PerfilPlaya perfilPlaya) {
	// TODO Auto-generated method stub
	getPerfilPlayaDao().update(perfilPlaya);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(PerfilPlaya perfilPlaya) {
	// TODO Auto-generated method stub
	getPerfilPlayaDao().delete(perfilPlaya);
    }

    @Override
    public PerfilPlaya findByNombrePerfilPlaya(String nombrePerfilPlaya) {
	// TODO Auto-generated method stub
	return getPerfilPlayaDao().findByNombrePerfilPlaya(nombrePerfilPlaya);
    }

    @Override
    public PerfilPlaya findByPlaya(Playa playa) {
	// TODO Auto-generated method stub
	return getPerfilPlayaDao().findByPlaya(playa);
    }

    @Override
    public List<PerfilPlaya> findAll() {
	// TODO Auto-generated method stub
	return getPerfilPlayaDao().findAll();
    }

    public IPerfilPlayaDao getPerfilPlayaDao() {
        return perfilPlayaDao;
    }

    public void setPerfilPlayaDao(IPerfilPlayaDao perfilPlayaDao) {
        this.perfilPlayaDao = perfilPlayaDao;
    }
}