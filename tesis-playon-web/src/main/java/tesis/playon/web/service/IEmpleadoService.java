package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.Empleado;
import tesis.playon.web.model.Usuario;

/**
 * 
 * @author gmorales
 * 
 */
public interface IEmpleadoService {

    void save(Empleado empleado);

    void update(Empleado empleado);

    void delete(Empleado empleado);

    List<Empleado> findAll();

    List<Empleado> findAll(Integer cargoEmpleado);

    Empleado findByLegajoEmpleado(Integer legajoEmpleado);

    Empleado findByUsuario(Usuario usuario);

}