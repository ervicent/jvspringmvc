package tesis.playon.web.service;

import java.util.Date;
import java.util.List;

import tesis.playon.web.model.CuentaPlaya;
import tesis.playon.web.model.Liquidacion;
import tesis.playon.web.model.TransaccionPlaya;

public interface ITransaccionPlayaService {

    public void save(TransaccionPlaya transaccionPlaya);

    public void update(TransaccionPlaya transaccionPlaya);

    public void delete(TransaccionPlaya transaccionPlaya);

    public TransaccionPlaya findByTransaccionPlayaid(int transaccionID);

    public List<TransaccionPlaya> findAll();

    public List<TransaccionPlaya> findTransaccionesNoLiquidadas();

    public List<TransaccionPlaya> findByCuentaPlaya(CuentaPlaya cuentaPlaya);

    public List<TransaccionPlaya> findNoLiquidadasByCuentaPlaya(CuentaPlaya cuentaPlaya);

    public List<TransaccionPlaya> findTransaccionesByCuentaPlaya(CuentaPlaya cuentaPlaya);
    
    public List<TransaccionPlaya> findNoLiquidadasByFechaDesdeHasta(Date fechaDesde, Date fechaHasta);

    public List<TransaccionPlaya> findTransaccionesByFecha(CuentaPlaya cuentaPlaya, Date fechaD, Date fechaH);
    
    public List<TransaccionPlaya> findTransaccionesByLiquidacion(Liquidacion liquidacion);
}
