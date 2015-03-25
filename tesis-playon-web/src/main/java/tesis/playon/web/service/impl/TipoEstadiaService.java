package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.ITipoEstadiaDao;
import tesis.playon.web.model.TipoEstadia;
import tesis.playon.web.service.ITipoEstadiaService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class TipoEstadiaService implements ITipoEstadiaService {

	ITipoEstadiaDao tipoEstadiaDao;

	@Transactional(readOnly = false)
	@Override
	public void save(TipoEstadia tipoEstadia) {
		getTipoEstadiaDao().save(tipoEstadia);
	}

	@Transactional(readOnly = false)
	@Override
	public void update(TipoEstadia tipoEstadia) {
		getTipoEstadiaDao().update(tipoEstadia);
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(TipoEstadia tipoEstadia) {
		getTipoEstadiaDao().delete(tipoEstadia);
	}

	@Override
	public List<TipoEstadia> findAll() {
		return getTipoEstadiaDao().findAll();
	}

	@Override
	public TipoEstadia findByNombreTipoEstadia(String nombreTipoEstadia) {
		return getTipoEstadiaDao().findByNombreTipoEstadia(nombreTipoEstadia);
	}

	@Override
	public TipoEstadia findByIdTipoEstadia(int idTipoEstadia) {
		return getTipoEstadiaDao().findByIdTipoEstadia(idTipoEstadia);
	}

	public ITipoEstadiaDao getTipoEstadiaDao() {
		return tipoEstadiaDao;
	}

	public void setTipoEstadiaDao(ITipoEstadiaDao tipoEstadiaDao) {
		this.tipoEstadiaDao = tipoEstadiaDao;
	}

}