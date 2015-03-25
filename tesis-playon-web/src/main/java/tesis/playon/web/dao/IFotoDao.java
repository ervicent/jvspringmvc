package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.Foto;
import tesis.playon.web.model.PerfilPlaya;

/**
 * @author Pablo
 *
 */
public interface IFotoDao {
    
    void save(Foto foto);

    void update(Foto foto);

    void delete(Foto foto);

    Foto findByLinkFoto(String link);
    
    List<Foto> findAll();
    
    Integer obtenerUltimoID();
    
    List<Foto> findByPlaya(PerfilPlaya perfil);
}
