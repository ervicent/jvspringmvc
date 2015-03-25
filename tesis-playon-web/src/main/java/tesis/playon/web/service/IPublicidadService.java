/**
 * 
 */
package tesis.playon.web.service;

import java.util.Date;
import java.util.List;

import tesis.playon.web.model.EstadoPublicidad;
import tesis.playon.web.model.Publicidad;

/**
 * @author pablo
 *
 */
public interface IPublicidadService {

    void save(Publicidad publicidad);

    void update(Publicidad publicidad);

    void delete(Publicidad publicidad);
    
    List<Publicidad> findAll();
    
    List<Publicidad> findAllByEstadoVigente();

    List<Publicidad> findByEstado(EstadoPublicidad estado);
    
    List<String[]> getMontosDePublicidadByPeriodo(Date fechaDesde, Date fechaHasta) ;
}
