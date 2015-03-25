package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.TipoEstadia;

/**
 * 
 * @author gmorales
 *
 */
public interface ITipoEstadiaService {

    void save(TipoEstadia tipoEstadia);

    void update(TipoEstadia tipoEstadia);

    void delete(TipoEstadia tipoEstadia);

    List<TipoEstadia> findAll();

    TipoEstadia findByNombreTipoEstadia(String nombreTipoEstadia);
    
    TipoEstadia findByIdTipoEstadia(int idTipoEstadia);

}