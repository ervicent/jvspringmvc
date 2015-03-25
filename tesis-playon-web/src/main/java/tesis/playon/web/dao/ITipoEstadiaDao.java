package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.TipoEstadia;

public interface ITipoEstadiaDao {

    void save(TipoEstadia tipoEstadia);

    void update(TipoEstadia tipoEstadia);

    void delete(TipoEstadia tipoEstadia);

    TipoEstadia findByNombreTipoEstadia(String nombreTipoEstadia);
    
    TipoEstadia findByIdTipoEstadia(int idTipoEstadia);

    List<TipoEstadia> findAll();

}
