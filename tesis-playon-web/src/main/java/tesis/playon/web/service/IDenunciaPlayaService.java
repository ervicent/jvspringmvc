/**
 * 
 */
package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.DenunciaPlaya;
import tesis.playon.web.model.EstadoDenuncia;

/**
 * @author pablo
 * 
 */
public interface IDenunciaPlayaService {

	void save(DenunciaPlaya denuncia);

	void update(DenunciaPlaya denuncia);

	void delete(DenunciaPlaya denuncia);

	DenunciaPlaya findByAsuntoDenunciaPlaya(String asuntoDenuncia);

	DenunciaPlaya findByEstadoDenunciaPlaya(EstadoDenuncia estado);

	List<DenunciaPlaya> findByEstadoDenunciaPlayas(EstadoDenuncia estado);

	List<DenunciaPlaya> findAll();

}