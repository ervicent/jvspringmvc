package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.Barrio;

public interface IBarrioDao {

    void save(Barrio barrio);

    void update(Barrio barrio);

    void delete(Barrio barrio);

    Barrio findByNombreBarrio(String nombreBarrio);

    List<Barrio> findAll();
}
