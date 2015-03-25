package tesis.playon.web.util;

import javax.faces.context.FacesContext;

import tesis.playon.web.model.CargoEmpleado;
import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.Empleado;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.RolesPorUsuario;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IClienteService;
import tesis.playon.web.service.IEmpleadoService;
import tesis.playon.web.service.IRolesPorUsuarioService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.service.impl.ClienteService;
import tesis.playon.web.service.impl.EmpleadoService;
import tesis.playon.web.service.impl.RolesPorUsuarioService;
import tesis.playon.web.service.impl.UsuarioService;

/**
 * Clase que retorna diferentes tipos de datos del usuario logueado. Para usarla se debe instanciar la clase:
 * <code>DatosUsuario datosUsuario = new DatosUsuario;</code> Luego se debe llamar al método:
 * <code>datosUsuario.getNombreUsuario();</code> y enviarle el Service en caso de requerirlo.
 * 
 * @author alejandro
 * @see tesis.playon.web.managed.bean.DatosUsuarioManagedBean.java
 * @see webapp/testdatosusuario.xhtml
 */
// @ManagedBean(name = "datosUsuarioGetterMB")
// @SessionScoped
public class DatosUsuario {

    // @ManagedProperty(value = "#{EmpleadoService}")
    IEmpleadoService empleadoService;

    // @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    // @ManagedProperty(value = "#{CargoEmpleadoService}")
    // ICargoEmpleadoService cargoEmpleadoService;

    // @ManagedProperty(value = "#{PlayaService}")
    // IPlayaService playaService;

    // @ManagedProperty(value = "#{RolesPorUsuarioService}")
    IRolesPorUsuarioService rolesPorUsuarioService;

    // @ManagedProperty(value = "#{ClienteService}")
    IClienteService clienteService;

    private Usuario usuario;

    private Empleado empleado;

    private CargoEmpleado cargoEmpleado;

    private Playa playa;

    private String nombreUsuario;

    private RolesPorUsuario rolesPorUsuario;

    private Cliente cliente;

    /**
     * Devuelve el nombre del usuario logueado.
     * 
     * @return El nombre del usuario logueado.
     */
    public String getNombreUsuario() {
	if (this.nombreUsuario == null) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    this.nombreUsuario = facesContext.getExternalContext().getRemoteUser();
	}
	return this.nombreUsuario;
    }

    /**
     * Devuelve el objeto Usuario del usuario logueado.
     * 
     * @param usuarioService
     *            El service de la clase Usuario.
     * @return El objeto Usuario del usuario logueado.
     */
    public Usuario getUsuario(IUsuarioService usuarioService) {
	if (this.usuario == null) {
	    this.setUsuarioService(usuarioService);

	    IUsuarioService us = getUsuarioService();
	    String nombreUsuario = this.getNombreUsuario();
	    Usuario u = us.findByNombreUsuario(nombreUsuario);
	    this.usuario = u;
	    // this.usuario = getUsuarioService().findByNombreUsuario(this.getNombreUsuario());
	}
	return this.usuario;
    }

    /**
     * Devuelve el objeto Empleado del usuario logueado.
     * 
     * @param usuarioService
     *            El service de la clase Usuario.
     * @param empleadoService
     *            El service de la clase Empleado.
     * @return El objeto Empleado del usuario logueado.
     */
    public Empleado getEmpleado(IUsuarioService usuarioService, IEmpleadoService empleadoService) {
	if (this.empleado == null) {
	    this.setEmpleadoService(empleadoService);
	    this.empleado = getEmpleadoService().findByUsuario(this.getUsuario(usuarioService));
	}
	return this.empleado;
    }

    /**
     * Devuelve el objeto Playa del usuario logueado.
     * 
     * @param usuarioService
     *            El service de la clase Usuario.
     * @param playaService
     *            El service de la clase Playa.
     * @return El objeto Playa del usuario logueado.
     */
    public Playa getPlaya(IUsuarioService usuarioService) {
	if (this.playa == null) {
	    // this.setPlayaService(playaService);
	    this.getUsuario(usuarioService).getPlaya();
	}
	return this.playa;
    }

    /**
     * Devuelve el objeto CargoEmpleado del usuario logueado.
     * 
     * @param usuarioService
     *            El service de la clase Usuario.
     * @param empleadoService
     *            El service de la clase Empleado.
     * @return El objeto CargoEmpleado del usuario logueado.
     */
    public CargoEmpleado getCargoEmpleado(IUsuarioService usuarioService, IEmpleadoService empleadoService) {
	if (this.cargoEmpleado == null) {
	    this.cargoEmpleado = this.getEmpleado(usuarioService, empleadoService).getCargoEmpleado();
	}
	return this.cargoEmpleado;
    }

    /**
     * Devuelve el objeto RolesPorUsuario con el rol del usuario logueado.
     * 
     * @param rolesPorUsuarioService
     *            El service de la clase RolesPorUsuario.
     * @return El objeto RolesPorUsuario con el rol del usuario logueado.
     */
    public RolesPorUsuario getRolesPorUsuario(IRolesPorUsuarioService rolesPorUsuarioService) {
	if (this.rolesPorUsuario == null) {
	    this.setRolesPorUsuarioService(rolesPorUsuarioService);
	    this.rolesPorUsuario = getRolesPorUsuarioService().findByNombreUsuario(this.getNombreUsuario());
	}
	return this.rolesPorUsuario;
    }

    /**
     * Devuelve el objeto Cliente del usuario logueado.
     * 
     * @param clienteService
     *            El service de la clase Cliente.
     * @return El objeto Cliente del usuario logueado.
     */
    public Cliente getCliente(IClienteService clienteService) {
	if (this.cliente == null) {
	    this.setClienteService(clienteService);
	    this.cliente = getClienteService().findByNombreUsuario(this.getNombreUsuario());
	}
	return this.cliente;
    }

    // ===================================================
    // GETTERS Y SETTERS DE SERVICES
    private IEmpleadoService getEmpleadoService() {
	if (this.empleadoService == null) {
	    this.empleadoService = new EmpleadoService();
	}
	return empleadoService;
    }

    private void setEmpleadoService(IEmpleadoService empleadoService) {
	this.empleadoService = empleadoService;
    }

    private IUsuarioService getUsuarioService() {
	if (this.usuarioService == null) {
	    this.usuarioService = new UsuarioService();
	}
	return usuarioService;
    }

    private void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    // private ICargoEmpleadoService getCargoEmpleadoService() {
    // if (this.cargoEmpleadoService == null){
    // this.cargoEmpleadoService = new CargoEmpleadoService();
    // }
    // return cargoEmpleadoService;
    // }
    //
    // private void setCargoEmpleadoService(ICargoEmpleadoService cargoEmpleadoService) {
    // this.cargoEmpleadoService = cargoEmpleadoService;
    // }

    // private IPlayaService getPlayaService() {
    // if (this.playaService == null){
    // this.playaService = new PlayaService();
    // }
    // return playaService;
    // }
    //
    // private void setPlayaService(IPlayaService playaService) {
    // this.playaService = playaService;
    // }

    private IRolesPorUsuarioService getRolesPorUsuarioService() {
	if (this.rolesPorUsuarioService == null) {
	    this.rolesPorUsuarioService = new RolesPorUsuarioService();
	}
	return rolesPorUsuarioService;
    }

    private void setRolesPorUsuarioService(IRolesPorUsuarioService rolesPorUsuarioService) {
	this.rolesPorUsuarioService = rolesPorUsuarioService;
    }

    private IClienteService getClienteService() {
	if (this.clienteService == null) {
	    this.clienteService = new ClienteService();
	}
	return clienteService;
    }

    private void setClienteService(IClienteService clienteService) {
	this.clienteService = clienteService;
    }

}
