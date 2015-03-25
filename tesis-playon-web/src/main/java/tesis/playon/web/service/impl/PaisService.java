package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IPaisDao;
import tesis.playon.web.model.Pais;
import tesis.playon.web.model.Provincia;
import tesis.playon.web.service.IPaisService;
/**
 * 
 * @author pablo
 *
 */
@Transactional(readOnly = true)
public class PaisService implements IPaisService {

    IPaisDao paisDao;
    
    @Transactional(readOnly = false)
    @Override
    public void save(Pais pais) {
	// TODO Auto-generated method stub
	getPaisDao().save(pais);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(Pais pais) {
	// TODO Auto-generated method stub
	getPaisDao().update(pais);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Pais pais) {
	// TODO Auto-generated method stub
	getPaisDao().delete(pais);
    }

    @Override
    public Pais findByNombrePais(String nombrePais) {
	// TODO Auto-generated method stub
	return getPaisDao().findByNombrePais(nombrePais);
    }

    @Override
    public List<Pais> findAll() {
	// TODO Auto-generated method stub
	return getPaisDao().findAll();
    }
    
    @Override
    public Pais findByPaisId(Integer id){
	return getPaisDao().findByPaisId(id);
    }
    
    public List<Provincia> getProvincias(Integer idPais){
	return getPaisDao().getProvincias(idPais);
    }

    public IPaisDao getPaisDao() {
        return paisDao;
    }

    public void setPaisDao(IPaisDao paisDao) {
        this.paisDao = paisDao;
    }

}
