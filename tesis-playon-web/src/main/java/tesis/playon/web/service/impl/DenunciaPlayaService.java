package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IDenunciaPlayaDao;
import tesis.playon.web.model.DenunciaPlaya;
import tesis.playon.web.model.EstadoDenuncia;
import tesis.playon.web.service.IDenunciaPlayaService;

/**
 * 
 * @author pablo
 * 
 */
@Transactional(readOnly = true)
public class DenunciaPlayaService implements IDenunciaPlayaService {

	IDenunciaPlayaDao denunciaPlayaDao;

	@Transactional(readOnly = false)
	@Override
	public void save(DenunciaPlaya denuncia) {
		getDenunciaPlayaDao().save(denuncia);
	}

	@Transactional(readOnly = false)
	@Override
	public void update(DenunciaPlaya denuncia) {
		getDenunciaPlayaDao().update(denuncia);
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(DenunciaPlaya denuncia) {
		getDenunciaPlayaDao().delete(denuncia);
	}

	@Override
	public DenunciaPlaya findByAsuntoDenunciaPlaya(String asuntoDenunciaPlaya) {
		return getDenunciaPlayaDao().findByAsuntoDenunciaPlaya(
				asuntoDenunciaPlaya);
	}

	@Override
	public List<DenunciaPlaya> findAll() {
		return getDenunciaPlayaDao().findAll();
	}

	public IDenunciaPlayaDao getDenunciaPlayaDao() {
		return denunciaPlayaDao;
	}

	public void setDenunciaPlayaDao(IDenunciaPlayaDao denuncia) {
		this.denunciaPlayaDao = denuncia;
	}

	@Override
	public DenunciaPlaya findByEstadoDenunciaPlaya(EstadoDenuncia estado) {
		return getDenunciaPlayaDao().findByEstadoDenunciaPlaya(estado);

	}

	public List<DenunciaPlaya> findByEstadoDenunciaPlayas(EstadoDenuncia estado) {
		return getDenunciaPlayaDao().findByEstadoDenunciaPlayas(estado);

	}
}
