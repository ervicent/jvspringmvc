package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.PerfilPlaya;
import tesis.playon.web.model.Playa;

public interface IPerfilPlayaDao {

    void save(PerfilPlaya perfilPlaya);

    void update(PerfilPlaya perfilPlaya);

    void delete(PerfilPlaya perfilPlaya);

    PerfilPlaya findByNombrePerfilPlaya(String nombrePerfilPlaya);
    
    PerfilPlaya findByPlaya(Playa playa);
    
    List<PerfilPlaya> findAll();

}
