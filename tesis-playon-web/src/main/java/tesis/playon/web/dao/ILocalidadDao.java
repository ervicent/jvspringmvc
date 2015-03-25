package tesis.playon.web.dao;

import java.util.List;
import java.util.Set;

import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.Localidad;

public interface ILocalidadDao {

    void save(Localidad localidad);

    void update(Localidad localidad);

    void delete(Localidad localidad);

    Localidad findByNombreLocalidad(String nombreLocalidad);

    List<Localidad> findAll();
    
    Set<Barrio> findBarrio(Localidad localidad);
}
