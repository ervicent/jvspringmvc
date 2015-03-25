package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.EstadoDenuncia;

/**
 * 
 * @author gonza
 */
public interface IEstadoDenunciaService {

    void save(EstadoDenuncia estadoDenuncia);

    void update(EstadoDenuncia estadoDenuncia);

    void delete(EstadoDenuncia estadoDenuncia);

    List<EstadoDenuncia> findAll();

    EstadoDenuncia findByNombreEstadoDenuncia(String nombreEstado);

}