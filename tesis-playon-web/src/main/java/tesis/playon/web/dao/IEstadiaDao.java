package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.Estadia;
import tesis.playon.web.model.Playa;

public interface IEstadiaDao {

    void save(Estadia estadia);

    void update(Estadia estadia);

    void delete(Estadia estadia);

    List<Estadia> findAll();

    Estadia findByPlaya(Playa playa);

}