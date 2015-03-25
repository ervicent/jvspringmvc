/**
 * 
 */
package tesis.playon.web.service;

import java.util.Date;
import java.util.List;

import tesis.playon.web.model.CuentaCliente;
import tesis.playon.web.model.TransaccionCliente;

/**
 * @author gmorales
 * 
 */
public interface ITransaccionClienteService {

    public void save(TransaccionCliente transaccionCliente);

    public void update(TransaccionCliente transaccionCliente);

    public void delete(TransaccionCliente transaccionCliente);

    public TransaccionCliente findByCuentaCliente(CuentaCliente cuentaCliente);

    public List<TransaccionCliente> findAll();

    public List<TransaccionCliente> findTransaccionesByCuentaCliente(CuentaCliente cuentaCliente);

    public List<TransaccionCliente> findTransaccionesByFecha(CuentaCliente cuentaCliente, Date fechaD, Date fechaH);
    
    TransaccionCliente getUltimaTransaccion(CuentaCliente cuentaCliente);
}