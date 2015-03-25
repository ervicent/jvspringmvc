/**
 * 
 */
package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.DenunciaVehiculo;
import tesis.playon.web.model.EstadoDenuncia;

/**
 * @author pablo
 * 
 */
public interface IDenunciaVehiculoService {

    void save(DenunciaVehiculo denuncia);

    void update(DenunciaVehiculo denuncia);

    void delete(DenunciaVehiculo denuncia);

    DenunciaVehiculo findByAsuntoDenunciaVehiculo(String asuntoDenunciaVehiculo);

    List<DenunciaVehiculo> findByEstadoDenunciaVehiculo(EstadoDenuncia estado);

    List<DenunciaVehiculo> findAll();

}
