package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.DenunciaPlaya;
import tesis.playon.web.model.DenunciaVehiculo;
import tesis.playon.web.model.EstadoDenuncia;

/**
 * @author garribere
 * 
 */
public interface IDenunciaPlayaDao {

    void save(DenunciaPlaya denunciaPlaya);

    void update(DenunciaPlaya denunciaPlaya);

    void delete(DenunciaPlaya denunciaPlaya);

    DenunciaPlaya findByAsuntoDenunciaPlaya(String asuntoDenunciaPlaya);

    DenunciaPlaya findByEstadoDenunciaPlaya(EstadoDenuncia estado);
    
    List<DenunciaPlaya> findByEstadoDenunciaPlayas(EstadoDenuncia estado);

    List<DenunciaPlaya> findAll();
}
