package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.Posicion;

public interface IPosicionDao {

    void save(Posicion posicion);

    void update(Posicion posicion);

    void delete(Posicion posicion);

    Posicion findByUbicacion(String ubicacion);

    List<Posicion> findAll();

}
