package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IEmpleadoDao;
import tesis.playon.web.model.Empleado;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IEmpleadoService;

/**
 * 
 * @author gmorales
 * 
 */
@Transactional(readOnly = true)
public class EmpleadoService implements IEmpleadoService {

    IEmpleadoDao empleadoDao;

    @Transactional(readOnly = false)
    @Override
    public void save(Empleado empleado) {
	getEmpleadoDao().save(empleado);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(Empleado empleado) {
	getEmpleadoDao().update(empleado);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Empleado empleado) {
	getEmpleadoDao().delete(empleado);
    }

    @Override
    public List<Empleado> findAll() {
	return getEmpleadoDao().findAll();
    }

    @Override
    public List<Empleado> findAll(Integer idCargoEmpleado) {
	return getEmpleadoDao().findAll(idCargoEmpleado);
    }

    @Override
    public Empleado findByLegajoEmpleado(Integer legajoEmpleado) {
	return getEmpleadoDao().findByLegajo(legajoEmpleado);
    }
    
    @Override
    public Empleado findByUsuario(Usuario usuario) {
	return getEmpleadoDao().findByIdUsuario(usuario);
    }

    public IEmpleadoDao getEmpleadoDao() {
	return empleadoDao;
    }

    public void setEmpleadoDao(IEmpleadoDao empleadoDao) {
	this.empleadoDao = empleadoDao;
    }

}
