/**
 * 
 */
package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.Vehiculo;

/**
 * @author pablo
 * 
 */
public interface IVehiculoService {

    void save(Vehiculo vehiculo);

    void update(Vehiculo vehiculo);

    void delete(Vehiculo vehiculo);

    Vehiculo findByPatenteVehiculo(String patenteVehiculo);

    List<Vehiculo> findAll();

    List<Vehiculo> findByCliente(int idCliente);
    
    boolean isPropietario(String patente, Cliente cliente);
    
    boolean isHabilitado(String patente);
}