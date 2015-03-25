package tesis.playon.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.ILiquidacionDao;
import tesis.playon.web.model.Liquidacion;
import tesis.playon.web.service.ILiquidacionService;

@Transactional(readOnly = true)
public class LiquidacionService implements ILiquidacionService {

    ILiquidacionDao liquidacionDao;

    @Transactional(readOnly = false)
    @Override
    public void save(Liquidacion liquidacion) {
	getLiquidacionDao().save(liquidacion);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(Liquidacion liquidacion) {
	getLiquidacionDao().update(liquidacion);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Liquidacion liquidacion) {
	getLiquidacionDao().delete(liquidacion);
    }

    @Override
    public List<Liquidacion> findAll() {
	return getLiquidacionDao().findAll();
    }

    public ILiquidacionDao getLiquidacionDao() {
	return liquidacionDao;
    }

    public void setLiquidacionDao(ILiquidacionDao liquidacionDao) {
	this.liquidacionDao = liquidacionDao;
    }

    @Override
    public List<Liquidacion> findByFecha(Date fecha) {
	return getLiquidacionDao().findByFecha(fecha);
    }

    @Override
    public List<Liquidacion> findByFecha(Date fechaDesde, Date fechaHasta) {
	return getLiquidacionDao().findByFecha(fechaDesde, fechaHasta);
    }

    @Override
    public List<String[]> getEstadisticasComisiones(Date fechaDesde, Date fechaHasta) {
	return getLiquidacionDao().getEstadisticasComisiones(fechaDesde, fechaHasta);
    }
}
