package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.ITipoPagoDao;
import tesis.playon.web.model.TipoPago;
import tesis.playon.web.service.ITipoPagoService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class TipoPagoService implements ITipoPagoService {

    ITipoPagoDao tipoPagoDao;

    @Transactional(readOnly = false)
    @Override
    public void save(TipoPago tipoPago) {
	getTipoPagoDao().save(tipoPago);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(TipoPago tipoPago) {
	getTipoPagoDao().update(tipoPago);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(TipoPago tipoPago) {
	getTipoPagoDao().delete(tipoPago);
    }

    @Override
    public TipoPago findByNombreTipoPago(String nombreTipoPago) {
	return getTipoPagoDao().findByNameTipoPago(nombreTipoPago);
    }

    @Override
    public List<TipoPago> findAll() {
	return getTipoPagoDao().findAll();
    }

    public ITipoPagoDao getTipoPagoDao() {
	return tipoPagoDao;
    }

    public void setTipoPagoDao(ITipoPagoDao tipoPagoDao) {
	this.tipoPagoDao = tipoPagoDao;
    }

}