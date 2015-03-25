package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IFavoritoDao;
import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.Favorito;
import tesis.playon.web.model.Playa;
import tesis.playon.web.service.IFavoritoService;

@Transactional(readOnly = true)
public class FavoritoService implements IFavoritoService {

    IFavoritoDao favoritoDao;
    
    @Transactional(readOnly = false)
    @Override
    public void save(Favorito favorito) {
	// TODO Auto-generated method stub
	getFavoritoDao().save(favorito);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(Favorito favorito) {
	// TODO Auto-generated method stub
	getFavoritoDao().update(favorito);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Favorito favorito) {
	// TODO Auto-generated method stub
	getFavoritoDao().delete(favorito);
    }

    @Override
    public List<Favorito> findAll() {
	// TODO Auto-generated method stub
	return getFavoritoDao().findAll();
    }

    @Override
    public List<Favorito> findByCliente(Cliente cliente) {
	// TODO Auto-generated method stub
	return getFavoritoDao().findByCliente(cliente);
    }
    
    @Override
    public boolean isFavorito(Cliente cliente, Playa playa){
	return getFavoritoDao().isFavorito(cliente, playa);
    }

    public IFavoritoDao getFavoritoDao() {
        return favoritoDao;
    }

    public void setFavoritoDao(IFavoritoDao favoritoDao) {
        this.favoritoDao = favoritoDao;
    }
}
