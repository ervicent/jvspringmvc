package tesis.playon.web.managed.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import tesis.playon.web.model.CargoEmpleado;
import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.Empleado;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.RolesPorUsuario;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.ICargoEmpleadoService;
import tesis.playon.web.service.IClienteService;
import tesis.playon.web.service.IEmpleadoService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IRolesPorUsuarioService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.util.DatosUsuario;

@ManagedBean(name = "datosUsuarioMB")
@SessionScoped
public class DatosUsuariosManagedBean implements Serializable {

    private static final long serialVersionUID = -415333286990153266L;

    @ManagedProperty(value = "#{EmpleadoService}")
    IEmpleadoService empleadoService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{CargoEmpleadoService}")
    ICargoEmpleadoService cargoEmpleadoService;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    @ManagedProperty(value = "#{RolesPorUsuarioService}")
    IRolesPorUsuarioService rolesPorUsuarioService;

    @ManagedProperty(value = "#{ClienteService}")
    IClienteService clienteService;

    private Usuario usuario;

    private RolesPorUsuario rolesPorUsuario;

    private DatosUsuario datosUsuario;

    private Empleado empleado;

    private CargoEmpleado cargoEmpleado;

    private Playa playa;

    private String nombreUsuario;

    private Cliente cliente;

    public Usuario getUsuario() {
	if (this.usuario == null) {
	    if (datosUsuario == null) {
		datosUsuario = new DatosUsuario();
	    }
	    this.usuario = datosUsuario.getUsuario(usuarioService);
	}
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    public RolesPorUsuario getRolesPorUsuario() {
	if (this.rolesPorUsuario == null) {
	    if (datosUsuario == null) {
		datosUsuario = new DatosUsuario();
	    }
	    this.rolesPorUsuario = datosUsuario.getRolesPorUsuario(getRolesPorUsuarioService());
	}
	return rolesPorUsuario;
    }

    public void setRolesPorUsuario(RolesPorUsuario rolesPorUsuario) {
	this.rolesPorUsuario = rolesPorUsuario;
    }

    public Empleado getEmpleado() {
	if (this.empleado == null) {
	    if (datosUsuario == null) {
		datosUsuario = new DatosUsuario();
	    }
	    this.empleado = datosUsuario.getEmpleado(getUsuarioService(), getEmpleadoService());
	}
	return empleado;
    }

    public void setEmpleado(Empleado empleado) {
	this.empleado = empleado;
    }

    public CargoEmpleado getCargoEmpleado() {
	if (this.cargoEmpleado == null) {
	    if (datosUsuario == null) {
		datosUsuario = new DatosUsuario();
	    }
	    this.cargoEmpleado = datosUsuario.getCargoEmpleado(getUsuarioService(), getEmpleadoService());
	}
	return cargoEmpleado;
    }

    public void setCargoEmpleado(CargoEmpleado cargoEmpleado) {
	this.cargoEmpleado = cargoEmpleado;
    }

    public Playa getPlaya() {
	if (this.playa == null) {
	    if (datosUsuario == null) {
		datosUsuario = new DatosUsuario();
	    }
	    this.playa = datosUsuario.getPlaya(getUsuarioService());
	}
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public String getNombreUsuario() {
	if (this.nombreUsuario == null) {
	    if (datosUsuario == null) {
		datosUsuario = new DatosUsuario();
	    }
	    this.nombreUsuario = datosUsuario.getNombreUsuario();
	}
	return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
	this.nombreUsuario = nombreUsuario;
    }

    public Cliente getCliente() {
	if (this.cliente == null) {
	    if (datosUsuario == null) {
		datosUsuario = new DatosUsuario();
	    }
	    this.cliente = datosUsuario.getCliente(getClienteService());
	}
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

    // ===================================================
    // GETTERS Y SETTERS DE SERVICES
    public IEmpleadoService getEmpleadoService() {
	return empleadoService;
    }

    public void setEmpleadoService(IEmpleadoService empleadoService) {
	this.empleadoService = empleadoService;
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public ICargoEmpleadoService getCargoEmpleadoService() {
	return cargoEmpleadoService;
    }

    public void setCargoEmpleadoService(ICargoEmpleadoService cargoEmpleadoService) {
	this.cargoEmpleadoService = cargoEmpleadoService;
    }

    public IPlayaService getPlayaService() {
	return playaService;
    }

    public void setPlayaService(IPlayaService playaService) {
	this.playaService = playaService;
    }

    public IRolesPorUsuarioService getRolesPorUsuarioService() {
	return rolesPorUsuarioService;
    }

    public void setRolesPorUsuarioService(IRolesPorUsuarioService rolesPorUsuarioService) {
	this.rolesPorUsuarioService = rolesPorUsuarioService;
    }

    public IClienteService getClienteService() {
	return clienteService;
    }

    public void setClienteService(IClienteService clienteService) {
	this.clienteService = clienteService;
    }

}
