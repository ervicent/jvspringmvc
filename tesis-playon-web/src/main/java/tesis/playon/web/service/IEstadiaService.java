package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.Estadia;
import tesis.playon.web.model.Playa;

/**
 * 
 * @author gmorales
 * 
 */
public interface IEstadiaService {

    void save(Estadia estadia);

    void update(Estadia estadia);

    void delete(Estadia estadia);

    List<Estadia> findAll();

    Estadia findByPlaya(Playa playa);

}