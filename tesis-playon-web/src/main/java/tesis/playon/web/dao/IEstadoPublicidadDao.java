package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.EstadoPublicidad;

public interface IEstadoPublicidadDao {

    void save(EstadoPublicidad estadoPublicidad);

    void update(EstadoPublicidad estadoPublicidad);

    void delete(EstadoPublicidad estadoPublicidad);

    EstadoPublicidad findByNombreEstadoPublicidad(String nombreEstadoPublicidad);
    
    List<EstadoPublicidad> findAll();
}
