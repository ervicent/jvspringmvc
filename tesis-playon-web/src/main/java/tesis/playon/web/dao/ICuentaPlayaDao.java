package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.CuentaPlaya;
import tesis.playon.web.model.Playa;

public interface ICuentaPlayaDao {

    void save(CuentaPlaya cuentaPlaya);

    void update(CuentaPlaya cuentaPlaya);

    void delete(CuentaPlaya cuentaPlaya);

    List<CuentaPlaya> findAll();

    CuentaPlaya findByNroCuenta(Integer nroCuenta);

    CuentaPlaya findByPlaya(Playa playa);

}
