/**
 * 
 */
package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.Pais;
import tesis.playon.web.model.Provincia;

/**
 * @author pablo
 *
 */
public interface IPaisService {

    void save(Pais pais);

    void update(Pais pais);

    void delete(Pais pais);

    Pais findByNombrePais(String nombrePais);
    
    Pais findByPaisId(Integer id);

    List<Pais> findAll();
    
    List<Provincia> getProvincias(Integer idPais);
}
