package tesis.playon.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.ITransaccionPlayaDao;
import tesis.playon.web.model.CuentaPlaya;
import tesis.playon.web.model.Liquidacion;
import tesis.playon.web.model.TransaccionPlaya;
import tesis.playon.web.service.ITransaccionPlayaService;

@Transactional(readOnly = true)
public class TransaccionPlayaService implements ITransaccionPlayaService {
    ITransaccionPlayaDao transaccionPlayaDao;

    @Transactional(readOnly = false)
    public void save(TransaccionPlaya TransaccionPlaya) {
	getTransaccionPlayaDao().save(TransaccionPlaya);
    }

    @Transactional(readOnly = false)
    public void update(TransaccionPlaya TransaccionPlaya) {
	getTransaccionPlayaDao().update(TransaccionPlaya);
    }

    @Transactional(readOnly = false)
    public void delete(TransaccionPlaya TransaccionPlaya) {
	getTransaccionPlayaDao().delete(TransaccionPlaya);
    }

    public List<TransaccionPlaya> findAll() {
	return getTransaccionPlayaDao().findAll();
    }

    public TransaccionPlaya findByTransaccionPlayaID(int transaccionPlayaID) {
	return getTransaccionPlayaDao().findByTransaccionPlayaID(transaccionPlayaID);
    }

    public ITransaccionPlayaDao getTransaccionPlayaDao() {
	return transaccionPlayaDao;
    }

    public void setTransaccionPlayaDao(ITransaccionPlayaDao transaccionPlayaDao) {
	this.transaccionPlayaDao = transaccionPlayaDao;
    }

    public TransaccionPlaya findByTransaccionPlayaid(int transaccionID) {
	return getTransaccionPlayaDao().findByTransaccionPlayaID(transaccionID);
    }

    public List<TransaccionPlaya> findTransaccionesNoLiquidadas() {
	return getTransaccionPlayaDao().findTransaccionesNoLiquidadas();
    }

    public List<TransaccionPlaya> findByCuentaPlaya(CuentaPlaya cuentaPlaya) {
	return getTransaccionPlayaDao().findByCuentaPlaya(cuentaPlaya);
    }

    public List<TransaccionPlaya> findNoLiquidadasByCuentaPlaya(CuentaPlaya cuentaPlaya) {
	return getTransaccionPlayaDao().findNoLiquidadasByCuentaPlaya(cuentaPlaya);
    }

    public List<TransaccionPlaya> findTransaccionesByCuentaPlaya(CuentaPlaya cuentaPlaya) {
	return getTransaccionPlayaDao().findTransaccionesByCuentaPlaya(cuentaPlaya);

    }
    
    public List<TransaccionPlaya> findNoLiquidadasByFechaDesdeHasta(Date fechaDesde, Date fechaHasta){
	return getTransaccionPlayaDao().findNoLiquidadasByFechaDesdeHasta(fechaDesde, fechaHasta);
    }

    @Override
    public List<TransaccionPlaya> findTransaccionesByLiquidacion(Liquidacion liquidacion) {
	return getTransaccionPlayaDao().findTransaccionesByLiquidacion(liquidacion);
    }

    public List<TransaccionPlaya> findTransaccionesByFecha(CuentaPlaya cuentaPlaya, Date fechaD, Date fechaH) {

	return getTransaccionPlayaDao().findTransaccionesByFecha(cuentaPlaya, fechaD, fechaH);
    }

}
