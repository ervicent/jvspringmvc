package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IDenunciaVehiculoDao;
import tesis.playon.web.model.DenunciaVehiculo;
import tesis.playon.web.model.EstadoDenuncia;
import tesis.playon.web.service.IDenunciaVehiculoService;

/**
 * 
 * @author pablo
 * 
 */
@Transactional(readOnly = true)
public class DenunciaVehiculoService implements IDenunciaVehiculoService {

    IDenunciaVehiculoDao denunciaVehiculoDao;

    @Transactional(readOnly = false)
    @Override
    public void save(DenunciaVehiculo denuncia) {
	getDenunciaVehiculoDao().save(denuncia);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(DenunciaVehiculo denuncia) {
	getDenunciaVehiculoDao().update(denuncia);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(DenunciaVehiculo denuncia) {
	getDenunciaVehiculoDao().delete(denuncia);
    }

    @Override
    public DenunciaVehiculo findByAsuntoDenunciaVehiculo(String asuntoDenunciaVehiculo) {
	return getDenunciaVehiculoDao().findByAsuntoDenunciaVehiculo(asuntoDenunciaVehiculo);
    }

    @Override
    public List<DenunciaVehiculo> findAll() {
	return getDenunciaVehiculoDao().findAll();
    }

    public IDenunciaVehiculoDao getDenunciaVehiculoDao() {
	return denunciaVehiculoDao;
    }

    public void setDenunciaVehiculoDao(IDenunciaVehiculoDao denuncia) {
	this.denunciaVehiculoDao = denuncia;
    }

    @Override
    public List<DenunciaVehiculo> findByEstadoDenunciaVehiculo(EstadoDenuncia estado) {
	return getDenunciaVehiculoDao().findByEstadoDenunciaVehiculo(estado);

    }

}
