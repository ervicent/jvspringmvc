package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.Pais;
import tesis.playon.web.model.Provincia;

public interface IProvinciaDao {

    void save(Provincia provincia);

    void update(Provincia provincia);

    void delete(Provincia provincia);

    Provincia findByNombreProvincia(String nombreProvincia);
    
    Provincia findByProvinciaId(Integer id);

    List<Provincia> findAll();
    
    List<Provincia> findProvincias(Pais pais);
}
