package tesis.playon.web.dao;

import java.util.Date;
import java.util.List;

import tesis.playon.web.model.EstadoPlaya;
import tesis.playon.web.model.Playa;

public interface IPlayaDao {

    void save(Playa playa);

    void update(Playa playa);

    void delete(Playa playa);

    Playa findByNombreComercial(String nombreComercial);

    Playa findByRazonSocial(String razonSocial);

    Playa findById(int idPlaya);
    
    List<Playa> findAll();
    
    List<Playa> findByPlayasCercanas(Double longitud, Double latitud
	    , int categoriaParameter, int tipoEstadiaParameter
	    ,String nombreComercial, int checkPromociones);

    List<Playa> findByEstado(EstadoPlaya estado);

    boolean existeEmail(String email);
    
    List<Playa> findByFechaDesdeHasta(Date fechaDesde, Date fechaHasta);

	List<Playa> findByLikeNombreComercial(String nombreComercial);
	
	List<Playa> findByLikeNombreComercialEstado(String nombreComercial, EstadoPlaya estado);
}
