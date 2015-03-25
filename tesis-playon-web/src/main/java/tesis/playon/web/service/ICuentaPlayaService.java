package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.CuentaPlaya;
import tesis.playon.web.model.Playa;

/**
 * 
 * @author gmorales
 * 
 */
public interface ICuentaPlayaService {

    void save(CuentaPlaya cuentaPlaya);

    void update(CuentaPlaya cuentaPlaya);

    void delete(CuentaPlaya cuentaPlaya);

    List<CuentaPlaya> findAll();

    CuentaPlaya findByNroCuentaPlaya(Integer nroCuentaPlaya);

    CuentaPlaya findByPlaya(Playa playa);

}