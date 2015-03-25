/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.CargoEmpleado;
import tesis.playon.web.model.Empleado;
import tesis.playon.web.model.RolesPorUsuario;
import tesis.playon.web.model.TipoDoc;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.ICargoEmpleadoService;
import tesis.playon.web.service.IEmpleadoService;
import tesis.playon.web.service.IRolUsuarioService;
import tesis.playon.web.service.IRolesPorUsuarioService;
import tesis.playon.web.service.IUsuarioService;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "empleadoMB")
@RequestScoped
public class EmpleadoManagedBean implements Serializable {

	private static final long serialVersionUID = -1085389423375986168L;

	private static final String LISTA_EMPLEADOS = "empleadolist";

	private static final String ERROR = "error";

	@ManagedProperty(value = "#{EmpleadoService}")
	IEmpleadoService empleadoService;

	@ManagedProperty(value = "#{UsuarioService}")
	IUsuarioService usuarioService;

	@ManagedProperty(value = "#{RolUsuarioService}")
	IRolUsuarioService rolUsuarioService;

	@ManagedProperty(value = "#{CargoEmpleadoService}")
	ICargoEmpleadoService cargoEmpleadoService;

	@ManagedProperty(value = "#{RolesPorUsuarioService}")
	IRolesPorUsuarioService rolesPorUsuarioService;

	private List<Empleado> empleadoList;

	private List<Empleado> filteredEmploy;

	private String apellido;

	private String nombre;

	private String email;

	private Integer nroDoc;

	private String password;

	private String nombreUser;

	private TipoDoc tipoDoc;

	private Integer legajo;

	private String domicilio;

	private String telefono;

	// private Integer nroCuenta;

	private Date fechaCreacion;

	private Empleado empleado;

	private CargoEmpleado cargoEmpleado;

	private Usuario usuario;

	private static Empleado empleadoSelected;

	@SuppressWarnings("unused")
	private SelectItem[] cargosOptions;

	@SuppressWarnings("unused")
	private Date fechaActual = new Date();

	public String addEmpleado() {
		Empleado empleado = new Empleado();
		Usuario usuario = addUsuario();
		try {
			empleado.setCargoEmpleado(getCargoEmpleado());
			empleado.setLegajo(getLegajo());
			empleado.setUsuario(usuario);

			getUsuarioService().save(usuario);
			getEmpleadoService().save(empleado);

			RolesPorUsuario rp;
			if ((empleado.getCargoEmpleado()).getNombre().equals("Playero"))
				rp = new RolesPorUsuario(usuario.getNombreUser(),
						"ROLE_PLAYA_EMPLEADO");
			else {
				if ((empleado.getCargoEmpleado()).getNombre().equals(
						"Gerente General"))
					rp = new RolesPorUsuario(usuario.getNombreUser(),
							"ROLE_PLAYA_GERENTE");
				else {
					rp = new RolesPorUsuario(usuario.getNombreUser(),
							"ROLE_PLAYA_EMPLEADO");
				}
			}
			getRolesPorUsuarioService().save(rp);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Se agregó correctamente el empleado: "
							+ empleado.getUsuario().getApellido() + " "
							+ empleado.getUsuario().getNombre(), "");
			FacesContext.getCurrentInstance().addMessage(null, message);

			return "empleadoend";
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, no se pudo agregar el empleado: "
							+ empleado.getUsuario().getApellido() + " "
							+ empleado.getUsuario().getNombre(),
					"Por favor, inténtelo más tarde.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, no se pudo agregar el Empleado. Nombre de usuario o mail Duplicados",
					"Usuario duplicado");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return ERROR;
	}

	public Usuario addUsuario() {
		try {
			Usuario usuario = new Usuario();
			usuario.setNombre(getNombre());
			usuario.setApellido(getApellido());
			usuario.setEmail(getEmail());
			usuario.setNroDoc(getNroDoc());
			usuario.setPassword(getPassword());
			usuario.setNombreUser(getNombreUser());
			usuario.setTipoDoc(getTipoDoc());
			usuario.setEnable(new Boolean(true));

			FacesContext facesContext = FacesContext.getCurrentInstance();
			String userName = facesContext.getExternalContext().getRemoteUser();
			Usuario user = getUsuarioService().findByNombreUsuario(userName);

			usuario.setPlaya(user.getPlaya());

			return usuario;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, no se pudo agregar el Empleado. Nombre de usuario o mail Duplicados",
					"Usuario duplicado");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return null;
	}

	public String deleteEmpleado() {
		try {
			empleadoSelected.getUsuario().setEnable(new Boolean(false));

			getUsuarioService().update(empleadoSelected.getUsuario());

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Se dió de baja el empleado: "
							+ empleadoSelected.getUsuario().getApellido() + " "
							+ empleadoSelected.getUsuario().getNombre(), "");
			FacesContext.getCurrentInstance().addMessage(null, message);
			reset();
			return "/playa/gerencia/empleadolist";
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, no se pudo dar de baja el empleado: "
							+ empleadoSelected.getUsuario().getApellido() + " "
							+ empleadoSelected.getUsuario().getNombre(),
					"Por favor, inténtelo más tarde.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			reset();
		}
		return ERROR;
	}

	public String updateEmpleadoAdmin() {
		try {
			Usuario usuario = empleadoSelected.getUsuario();
			getUsuarioService().update(usuario);
			getEmpleadoService().update(empleadoSelected);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"El empleado se modificó correctamente", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return LISTA_EMPLEADOS;
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error,el cliente no se pudo modificar",
					"Por favor, inténtelo más tarde.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, no se pudo agregar el Empleado. Nombre de usuario o mail Duplicados",
					"Usuario duplicado");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return ERROR;
	}

	public String updateEmpleado() {
		Usuario usuario = empleadoSelected.getUsuario();
		try {
			getUsuarioService().update(usuario);
			getEmpleadoService().update(empleadoSelected);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"El empleado se modificó correctamente", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
			reset();
			return "/playa/gerencia/empleadolist";
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error,el cliente no se pudo modificar",
					"Por favor, inténtelo más tarde.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			reset();
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, no se pudo agregar el Empleado. Nombre de usuario o mail Duplicados",
					"Usuario duplicado");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return ERROR;
	}

	public void reset() {
		this.setCargoEmpleado(null);
		this.setUsuario(null);
		this.setNombre("");
		this.setApellido("");
		this.setEmail("");
		this.setNroDoc(null);
		this.setPassword("");
		this.setNombreUser("");
		EmpleadoManagedBean.empleadoSelected = null;
	}

	public IUsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public IEmpleadoService getEmpleadoService() {
		return empleadoService;
	}

	public void setEmpleadoService(IEmpleadoService empleadoService) {
		this.empleadoService = empleadoService;
	}

	public ICargoEmpleadoService getCargoEmpleadoService() {
		return cargoEmpleadoService;
	}

	public void setCargoEmpleadoService(
			ICargoEmpleadoService cargoEmpleadoService) {
		this.cargoEmpleadoService = cargoEmpleadoService;
	}

	public IRolesPorUsuarioService getRolesPorUsuarioService() {
		return rolesPorUsuarioService;
	}

	public void setRolesPorUsuarioService(
			IRolesPorUsuarioService rolesPorUsuarioService) {
		this.rolesPorUsuarioService = rolesPorUsuarioService;
	}

	public IRolUsuarioService getRolUsuarioService() {
		return rolUsuarioService;
	}

	public void setRolUsuarioService(IRolUsuarioService rolUsuarioService) {
		this.rolUsuarioService = rolUsuarioService;
	}

	public List<Empleado> getEmpleadoList() {
		empleadoList = new ArrayList<Empleado>();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String userName = facesContext.getExternalContext().getRemoteUser();
		Usuario user = getUsuarioService().findByNombreUsuario(userName);

		List<Usuario> users = new ArrayList<Usuario>();
		users.addAll(getUsuarioService().findByPlaya(user.getPlaya()));
		for (Usuario usuario : users) {
			empleadoList.add(getEmpleadoService().findByUsuario(usuario));
		}
		return empleadoList;
	}

	public void setEmpleadoList(List<Empleado> empleadoList) {
		this.empleadoList = empleadoList;
	}

	public CargoEmpleado getCargoEmpleado() {
		return cargoEmpleado;
	}

	public void setCargoEmpleado(CargoEmpleado cargoEmpleado) {
		this.cargoEmpleado = cargoEmpleado;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getNroDoc() {
		return nroDoc;
	}

	public void setNroDoc(Integer nroDoc) {
		this.nroDoc = nroDoc;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombreUser() {
		return nombreUser;
	}

	public void setNombreUser(String nombreUser) {
		this.nombreUser = nombreUser;
	}

	public TipoDoc getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(TipoDoc tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public Integer getLegajo() {
		return legajo;
	}

	public void setLegajo(Integer legajo) {
		this.legajo = legajo;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public SelectItem[] getCargosOptions() {
		List<CargoEmpleado> cargos = new ArrayList<CargoEmpleado>();
		cargos.addAll(getCargoEmpleadoService().findAll());
		cargosOptions = new SelectItem[cargos.size() + 1];
		SelectItem[] options = new SelectItem[cargos.size() + 1];
		options[0] = new SelectItem("", "Todos");

		for (int i = 0; i < cargos.size(); i++) {
			options[i + 1] = new SelectItem(cargos.get(i), cargos.get(i)
					.getNombre());
		}
		return options;
	}

	public void setCargosOptions(SelectItem[] cargosOptions) {
		this.cargosOptions = cargosOptions;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public List<Empleado> getFilteredEmploy() {
		return filteredEmploy;
	}

	public void setFilteredEmploy(List<Empleado> filteredEmploy) {
		this.filteredEmploy = filteredEmploy;
	}

	public CargoEmpleado addCargoEmpleado() {
		try {
			CargoEmpleado cargo = new CargoEmpleado();
			cargo.setNombre(cargo.getNombre());
			cargo.setId(cargo.getId());
			return cargo;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Empleado getEmpleadoSelected() {
		return empleadoSelected;
	}

	public void setEmpleadoSelected(Empleado empleadoSelected) {
		EmpleadoManagedBean.empleadoSelected = empleadoSelected;
	}

	public void listadoEmpleadosPDF(Object document) throws IOException,
			BadElementException, DocumentException {

		Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 18, Font.BOLD,
				Color.BLACK);

		Paragraph titulo = new Paragraph();
		titulo.add(new Paragraph("Listado de empleados al dí	a "
				+ fechaActual(), fuenteNegra18));
		agregarLineasEnBlanco(titulo, 1);
		titulo.setAlignment(Element.ALIGN_CENTER);
		String sep = File.separator;
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String logo = extContext.getRealPath("resources" + sep + "images" + sep
				+ "transacciones.png");
		pdf.addTitle("Listado de empleados");
		pdf.add(Image.getInstance(logo));
		pdf.add(titulo);
	}

	public void listadoAbonadosPDF(Object document) throws IOException,
			BadElementException, DocumentException {

		Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 18, Font.BOLD,
				Color.BLACK);

		Paragraph titulo = new Paragraph();
		titulo.add(new Paragraph("Listado de abonados al día " + fechaActual(),
				fuenteNegra18));
		agregarLineasEnBlanco(titulo, 2);
		titulo.setAlignment(Element.ALIGN_CENTER);
		String sep = File.separator;
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String logo = extContext.getRealPath("resources" + sep + "images" + sep
				+ "transacciones.png");
		pdf.addTitle("Listado de empleados");
		pdf.add(Image.getInstance(logo));
		pdf.add(titulo);
	}

	public void listadoTransaccionesPDF(Object document) throws IOException,
			BadElementException, DocumentException {

		Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 18, Font.BOLD,
				Color.BLACK);

		Paragraph titulo = new Paragraph();
		titulo.add(new Paragraph("Listado de transacciones emitido el "
				+ fechaActual(), fuenteNegra18));
		agregarLineasEnBlanco(titulo, 2);
		titulo.setAlignment(Element.ALIGN_CENTER);
		String sep = File.separator;
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String logo = extContext.getRealPath("resources" + sep + "images" + sep
				+ "transacciones.png");
		pdf.addTitle("Listado de transacciones");
		pdf.add(Image.getInstance(logo));
		pdf.add(titulo);
	}

	public void listadoTarifasPDF(Object document) throws IOException,
			BadElementException, DocumentException {

		Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 18, Font.BOLD,
				Color.BLACK);

		Paragraph titulo = new Paragraph();
		titulo.add(new Paragraph("Listado de Tarifas emitido el: "
				+ fechaActual(), fuenteNegra18));
		agregarLineasEnBlanco(titulo, 2);
		titulo.setAlignment(Element.ALIGN_CENTER);
		String sep = File.separator;
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String logo = extContext.getRealPath("resources" + sep + "images" + sep
				+ "transacciones.png");
		pdf.addTitle("Listado de empleados");
		pdf.add(Image.getInstance(logo));
		pdf.add(titulo);
	}

	private static void agregarLineasEnBlanco(Paragraph parrafo, int nLineas) {
		for (int i = 0; i < nLineas; i++)
			parrafo.add(new Paragraph(" "));
	}

	private String fechaActual() {
		Date hoy = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = formato.format(hoy);
		return fecha;

	}

}