package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IAbonoDao;
import tesis.playon.web.model.Abono;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Vehiculo;
import tesis.playon.web.service.IAbonoService;

@Transactional(readOnly = true)
public class AbonoService implements IAbonoService {

    IAbonoDao abonoDao;

    @Transactional(readOnly = false)
    @Override
    public void save(Abono abono) {
	getAbonoDao().save(abono);

    }

    @Transactional(readOnly = false)
    @Override
    public void update(Abono abono) {
	getAbonoDao().update(abono);

    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Abono abono) {
	getAbonoDao().delete(abono);

    }

    @Override
    public List<Abono> findAll() {

	return getAbonoDao().findAll();
    }

    public List<Abono> findByPlaya(Playa playa) {

	return getAbonoDao().findByPlaya(playa);
    }
    
    @Override
    public boolean existeAbonoVehiculo(Vehiculo vehiculo, Playa playa){
	return getAbonoDao().existeAbonoVehiculo(vehiculo, playa);
    }

    public IAbonoDao getAbonoDao() {
	return abonoDao;
    }

    public void setAbonoDao(IAbonoDao abonoDao) {
	this.abonoDao = abonoDao;
    }

}
