package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.TipoDoc;

public interface ITipoDocDao {

    void save(TipoDoc tipoDoc);

    void update(TipoDoc tipoDoc);

    void delete(TipoDoc tipoDoc);

    TipoDoc findByNombreTipoDoc(String nombreTipoDoc);

    List<TipoDoc> findAll();
}
