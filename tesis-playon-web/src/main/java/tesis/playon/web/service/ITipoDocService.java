package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.TipoDoc;

public interface ITipoDocService {

    void save(TipoDoc tipoDoc);

    void update(TipoDoc tipoDoc);

    void delete(TipoDoc tipoDoc);

    List<TipoDoc> findAll();

    TipoDoc findByNombreTipoDoc(String nombreTipoDoc);

}