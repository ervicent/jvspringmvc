package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.TipoPago;

/**
 * 
 * @author gmorales
 *
 */
public interface ITipoPagoService {

    void save(TipoPago tipoPago);

    void update(TipoPago tipoPago);

    void delete(TipoPago tipoPago);

    TipoPago findByNombreTipoPago(String nombreTipoPago);

    List<TipoPago> findAll();

}