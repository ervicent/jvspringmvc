package tesis.playon.web.dao;

import java.util.Date;
import java.util.List;

import tesis.playon.web.model.Liquidacion;

public interface ILiquidacionDao {

    void save(Liquidacion liquidacion);

    void update(Liquidacion liquidacion);

    void delete(Liquidacion liquidacion);

    List<Liquidacion> findAll();

    List<Liquidacion> findByFecha(Date fecha);

    List<Liquidacion> findByFecha(Date fechaDesde, Date fechaHasta);
    
    List<String[]> getEstadisticasComisiones(Date fechaDesde, Date fechaHasta);
}
