package tesis.playon.web.dao;

import java.util.Date;
import java.util.List;

import tesis.playon.web.model.EstadoPublicidad;
import tesis.playon.web.model.Publicidad;

/**
 * Interfaz DAO de Publicidad
 * 
 * @author alejandro
 * @date 07/07/2012
 */
public interface IPublicidadDao {

    void save(Publicidad publicidad);

    void update(Publicidad publicidad);

    void delete(Publicidad publicidad);

    List<Publicidad> findAll();

    List<Publicidad> findAllByEstadoVigente();

    List<Publicidad> findByEstado(EstadoPublicidad estado);

    List<String[]> getMontosDePublicidadByPeriodo(Date fechaDesde, Date fechaHasta);
}
