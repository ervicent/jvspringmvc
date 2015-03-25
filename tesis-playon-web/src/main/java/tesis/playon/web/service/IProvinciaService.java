package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.Pais;
import tesis.playon.web.model.Provincia;

/**
 * 
 * @author gmorales
 *
 */
public interface IProvinciaService {

    void save(Provincia provincia);

    void update(Provincia provincia);

    void delete(Provincia provincia);

    List<Provincia> findAll();

    Provincia findByNombreProvincia(String nombreProvincia);
    
    Provincia findByProvinciaId(Integer id);
    
    List<Provincia> findProvincias(Pais pais);
}