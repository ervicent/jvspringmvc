package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.HistorialDeCambio;

public interface IHistorialDeCambioDao {
    
    void save(HistorialDeCambio estadia);

    void update(HistorialDeCambio estadia);

    void delete(HistorialDeCambio estadia);

    List<HistorialDeCambio> findAll();

}
