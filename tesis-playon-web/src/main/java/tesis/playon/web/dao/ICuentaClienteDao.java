package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.CuentaCliente;

public interface ICuentaClienteDao {

    void save(CuentaCliente cuentaCliente);
    
    void update(CuentaCliente cuentaCliente);
    
    void delete(CuentaCliente cuentaCliente);
    
    CuentaCliente findByNroCuenta(Integer nroCuenta);
    
    List<CuentaCliente> findAll();
    
}
