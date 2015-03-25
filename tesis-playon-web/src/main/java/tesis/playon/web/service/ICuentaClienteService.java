package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.CuentaCliente;

/**
 * 
 * @author gmorales
 *
 */
public interface ICuentaClienteService {

    void save(CuentaCliente cuentaCliente);

    void update(CuentaCliente cuentaCliente);

    void delete(CuentaCliente cuentaCliente);

    List<CuentaCliente> findAll();

    CuentaCliente findByNroCuentaCliente(Integer nroCuentaCliente);

}