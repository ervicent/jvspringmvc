package tesis.playon.web.service;

import java.util.List;
import java.util.Set;

import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.Localidad;

/**
 * 
 * @author gmorales
 *
 */
public interface ILocalidadService {

    void save(Localidad localidad);

    void update(Localidad localidad);

    void delete(Localidad localidad);

    List<Localidad> findAll();

    Localidad findByNombreLocalidad(String nombreLocalidad);

    Set<Barrio> findBarrio(Localidad localidad);
}