package tesis.playon.web.dao;

import java.util.Date;
import java.util.List;

import tesis.playon.web.model.CuentaPlaya;
import tesis.playon.web.model.Liquidacion;
import tesis.playon.web.model.TransaccionPlaya;

public interface ITransaccionPlayaDao {

    void save(TransaccionPlaya transaccionPlaya);

    void update(TransaccionPlaya transaccionPlaya);

    void delete(TransaccionPlaya transaccionPlaya);

    List<TransaccionPlaya> findAll();

    TransaccionPlaya findByTransaccionPlayaID(int id);

    List<TransaccionPlaya> findTransaccionesNoLiquidadas();

    List<TransaccionPlaya> findByCuentaPlaya(CuentaPlaya cuentaPlaya);

    List<TransaccionPlaya> findNoLiquidadasByCuentaPlaya(CuentaPlaya cuentaPlaya);

    // List<TransaccionPlaya> findByPlaya(Playa playa);

    List<TransaccionPlaya> findTransaccionesByCuentaPlaya(CuentaPlaya cuentaPlaya);
    
    List<TransaccionPlaya> findTransaccionesByFecha(CuentaPlaya cuentaPlaya, Date fechaD, Date fechaH);
    
    List<TransaccionPlaya> findNoLiquidadasByFechaDesdeHasta(Date fechaDesde, Date fechaHasta);

    List<TransaccionPlaya> findTransaccionesByLiquidacion(Liquidacion liquidacion);

}
