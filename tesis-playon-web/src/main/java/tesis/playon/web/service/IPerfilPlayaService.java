/**
 * 
 */
package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.PerfilPlaya;
import tesis.playon.web.model.Playa;

/**
 * @author pablo
 *
 */
public interface IPerfilPlayaService {
    
    void save(PerfilPlaya perfilPlaya);

    void update(PerfilPlaya perfilPlaya);

    void delete(PerfilPlaya perfilPlaya);

    PerfilPlaya findByNombrePerfilPlaya(String nombrePerfilPlaya);
    
    PerfilPlaya findByPlaya(Playa playa);
    
    List<PerfilPlaya> findAll();

}
