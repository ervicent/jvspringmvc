package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.ITipoDocDao;
import tesis.playon.web.model.TipoDoc;
import tesis.playon.web.service.ITipoDocService;

/**
 * 
 * @author gmorales
 *
 */
@Transactional(readOnly = true)
public class TipoDocService implements ITipoDocService {

    ITipoDocDao tipoDocDao;

    @Transactional(readOnly = false)
    @Override
    public void save(TipoDoc tipoDoc) {
	getTipoDocDao().save(tipoDoc);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(TipoDoc tipoDoc) {
	getTipoDocDao().update(tipoDoc);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(TipoDoc tipoDoc) {
	getTipoDocDao().delete(tipoDoc);
    }

    @Override
    public List<TipoDoc> findAll() {
	return getTipoDocDao().findAll();
    }

    @Override
    public TipoDoc findByNombreTipoDoc(String nombreTipoDoc) {
	return getTipoDocDao().findByNombreTipoDoc(nombreTipoDoc);
    }

    public ITipoDocDao getTipoDocDao() {
	return tipoDocDao;
    }

    public void setTipoDocDao(ITipoDocDao tipoDocDao) {
	this.tipoDocDao = tipoDocDao;
    }

}