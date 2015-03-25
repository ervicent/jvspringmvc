package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.Abono;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Vehiculo;

public interface IAbonoService {

    void save(Abono abono);

    void update(Abono abono);

    void delete(Abono abono);

    List<Abono> findAll();

    List<Abono> findByPlaya(Playa playa);
    
    boolean existeAbonoVehiculo(Vehiculo vehiculo, Playa playa);
}