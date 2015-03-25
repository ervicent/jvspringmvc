package tesis.playon.web.managed.bean;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.CargoEmpleado;
import tesis.playon.web.model.Empleado;
import tesis.playon.web.model.Estadia;
import tesis.playon.web.model.EstadoPlaya;
import tesis.playon.web.model.Mail;
import tesis.playon.web.model.PerfilPlaya;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.RolesPorUsuario;
import tesis.playon.web.model.TipoDoc;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IBarrioService;
import tesis.playon.web.service.ICargoEmpleadoService;
import tesis.playon.web.service.IEmpleadoService;
import tesis.playon.web.service.IEstadiaService;
import tesis.playon.web.service.IEstadoPlayaService;
import tesis.playon.web.service.IPerfilPlayaService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IRolUsuarioService;
import tesis.playon.web.service.IRolesPorUsuarioService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.util.NotificadorUtil;

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
@ManagedBean(name = "playaMB")
@ViewScoped
public class PlayaManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    private static final String LISTA_PLAYAS = "playalist";

    private static final String SOLICITUD_PLAYA_END = "solicitudplayaend";

    private static final String ERROR = "error";

    private MapModel simpleModel;
    private final MapModel advancedModel = new DefaultMapModel();
    private Marker marker;
    private Mail mail;
    private NotificadorUtil notificador;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    @ManagedProperty(value = "#{EstadoPlayaService}")
    IEstadoPlayaService estadoPlayaService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{BarrioService}")
    IBarrioService barrioService;

    @ManagedProperty(value = "#{RolUsuarioService}")
    IRolUsuarioService rolUsuarioService;

    @ManagedProperty(value = "#{RolesPorUsuarioService}")
    IRolesPorUsuarioService rolesPorUsuarioService;

    @ManagedProperty(value = "#{EstadiaService}")
    IEstadiaService estadiaService;

    @ManagedProperty(value = "#{PerfilPlayaService}")
    IPerfilPlayaService perfilPlayaService;

    @ManagedProperty(value = "#{CargoEmpleadoService}")
    ICargoEmpleadoService cargoEmpleadoService;

    @ManagedProperty(value = "#{EmpleadoService}")
    IEmpleadoService empleadoService;

    private static List<Playa> playaList;

    private String cuit;

    private Integer disponibilidad;

    private String direccionBusqueda;

    private String domicilio;

    private String nombreComercial;

    private String razonSocial;

    private Barrio barrio;

    private String telefono;

    private String emailPlaya;

    private EstadoPlaya estado;

    private String url;

    // Atributos del encargado
    private String apellido;

    private String nombre;

    private String email;

    private Integer nroDoc;

    private String password;

    private String nombreUser;

    private TipoDoc tipoDoc;

    private Playa playa;

    private String acTxtPlaya;

    // para modificar una playa
    private static Playa playaSelected;

    @PostConstruct
    private void init() {
	playaList = getPlayaService().findAll();
    }

    public String addPlayaAdmin() {
	try {

	    Usuario usuario = addUsuario();
	    CargoEmpleado cargo = getCargoEmpleadoService().findByNombreCargo("Gerente General");

	    Playa playa = new Playa();
	    playa.setBarrio(getBarrio());
	    playa.setCuit(getCuit());
	    playa.setDisponibilidad(getDisponibilidad());
	    playa.setDomicilio(getDomicilio());
	    playa.setEstado(getEstado());
	    playa.setNombreComercial(getNombreComercial());
	    playa.setRazonSocial(getRazonSocial());
	    playa.setTelefono(getTelefono());
	    playa.setEmail(getEmailPlaya());
	    playa.setUrl(getUrl());

	    getPlayaService().save(playa);

	    PerfilPlaya perfil = new PerfilPlaya();
	    perfil.setPlaya(playa);

	    getPerfilPlayaService().save(perfil);

	    Estadia estadia = new Estadia(playa);
	    getEstadiaService().save(estadia);

	    usuario.setPlaya(playa);
	    getUsuarioService().save(usuario);

	    Empleado empleado = new Empleado();
	    empleado.setUsuario(usuario);
	    empleado.setLegajo(new Integer(1001));
	    empleado.setCargoEmpleado(cargo);
	    getEmpleadoService().save(empleado);

	    RolesPorUsuario rp = new RolesPorUsuario(usuario.getNombreUser(), "ROLE_PLAYA_GERENTE");
	    getRolesPorUsuarioService().save(rp);

	    String asunto = "Notificación equipo de Playón";
	    String mensaje = "Su solicitud esta en el área de auditoria, en breve nos comunicaremos con usted.\n\n ¡Muchas gracias!";
	    mail = new Mail();
	    mail.setAsunto(asunto);
	    mail.setMensaje(mensaje);
	    mail.setDestinatario(getEmail());
	    notificador = new NotificadorUtil();
	    notificador.enviar(mail);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se agregó correctamente : "
		    + playa.getNombreComercial(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);

	    return LISTA_PLAYAS;
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, no se pudo agregar: "
		    + playa.getNombreComercial(), "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();

	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo agregar la Playa. Nombre de usuario o mail Duplicados", "Usuario duplicado");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}
	return ERROR;
    }

    public String addSolicitudPlaya() {
	try {
	    EstadoPlaya estado = new EstadoPlaya();
	    estado = getEstadoPlayaService().findByNombreEstadoPlaya("Pendiente");

	    Usuario usuario = addUsuario();

	    CargoEmpleado cargo = getCargoEmpleadoService().findByNombreCargo("Gerente General");

	    Playa playa = new Playa();
	    playa.setBarrio(getBarrio());
	    playa.setCuit(getCuit());
	    playa.setDisponibilidad(getDisponibilidad());
	    playa.setDomicilio(getDomicilio());
	    playa.setEstado(estado);
	    playa.setNombreComercial(getNombreComercial());
	    playa.setRazonSocial(getRazonSocial());
	    playa.setTelefono(getTelefono());
	    playa.setEmail(getEmailPlaya());
	    playa.setUrl(url);

	    getPlayaService().save(playa);

	    PerfilPlaya perfil = new PerfilPlaya();
	    perfil.setPlaya(playa);

	    getPerfilPlayaService().save(perfil);

	    Estadia estadia = new Estadia(playa);
	    getEstadiaService().save(estadia);

	    usuario.setPlaya(playa);
	    getUsuarioService().save(usuario);

	    Empleado empleado = new Empleado();
	    empleado.setUsuario(usuario);
	    empleado.setLegajo(new Integer(1001));
	    empleado.setCargoEmpleado(cargo);
	    getEmpleadoService().save(empleado);

	    RolesPorUsuario rp = new RolesPorUsuario(usuario.getNombreUser(), "ROLE_PLAYA_GERENTE");
	    getRolesPorUsuarioService().save(rp);

	    String asunto = "Notificación equipo de Playón";
	    String mensaje = "Su solicitud esta en el área de auditoria, en breve nos comunicaremos con usted.\n\n ¡Muchas gracias!";
	    mail = new Mail();
	    mail.setAsunto(asunto);
	    mail.setMensaje(mensaje);
	    mail.setDestinatario(getEmail());
	    notificador = new NotificadorUtil();
	    notificador.enviar(mail);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se agregó correctamente : "
		    + playa.getNombreComercial(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);

	    return SOLICITUD_PLAYA_END;
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, no se pudo agregar: "
		    + playa.getNombreComercial(), "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();

	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo agregar la Playa. Nombre de usuario o mail Duplicados", "Usuario duplicado");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
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
	    usuario.setEnable(new Boolean(false));
	    return usuario;
	} catch (DataAccessException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo agregar la Playa. Nombre de usuario o mail Duplicados", "Usuario duplicado");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}
	return null;
    }

    public void deletePlayaAdmin() {
	try {
	    EstadoPlaya estado = getEstadoPlayaService().findByNombreEstadoPlaya("De Baja");

	    if (playaSelected != null) {
		playaSelected.setEstado(estado);

		List<Usuario> userList = getUsuarioService().findByPlaya(playaSelected);

		if (userList != null) {
		    for (Usuario usuario : userList) {
			usuario.setEnable(new Boolean(false));
			getUsuarioService().update(usuario);
		    }
		}

		getPlayaService().update(playaSelected);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se dió de baja la playa: "
			+ playaSelected.getNombreComercial(), null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	    }

	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo dar de baja la playa: " + playaSelected.getNombreComercial(),
		    "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}
    }

    public String updatePlayaAdmin() {
	try {
	    if (playaSelected != null) {
		if (playaSelected.getEstado().getNombre().equals("De Baja")) {
		    List<Usuario> userList = getUsuarioService().findByPlaya(playaSelected);
		    for (Usuario usuario : userList) {
			usuario.setEnable(new Boolean(false));
			getUsuarioService().update(usuario);
		    }
		}
		getPlayaService().update(playaSelected);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, playaSelected.getNombreComercial()
			+ " se actualizó correctamente", "");
		FacesContext.getCurrentInstance().addMessage(null, message);
		return LISTA_PLAYAS;
	    }
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, "
		    + playaSelected.getNombreComercial() + " no se pudo modificar", "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}
	return ERROR;
    }

    public List<String> complete(String query) {
	List<String> results = new ArrayList<String>();

	List<Playa> playas = this.getPlayaService().findByLikeNombreComercial(query);

	for (Playa nombrePlaya : playas) {
	    results.add(nombrePlaya.getNombreComercial());
	}

	return results;
    }

    public void addHttp() {
	if (!this.url.isEmpty() && this.url.indexOf("http://") == -1)
	    this.url = "http://" + this.url;
    }

    public void addHttpEdit() {
	if (playaSelected != null) {
	    if (!playaSelected.getUrl().isEmpty() && playaSelected.getUrl().indexOf("http://") == -1)
		playaSelected.setUrl("http://" + playaSelected.getUrl());
	}
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public void deletePlaya(Playa playa) {
	getPlayaService().delete(playa);
    }

    public IPlayaService getPlayaService() {
	return playaService;
    }

    public void setPlayaService(IPlayaService playaService) {
	this.playaService = playaService;
    }

    public IEstadoPlayaService getEstadoPlayaService() {
	return estadoPlayaService;
    }

    public void setEstadoPlayaService(IEstadoPlayaService estadoPlayaService) {
	this.estadoPlayaService = estadoPlayaService;
    }

    public IBarrioService getBarrioService() {
	return barrioService;
    }

    public void setBarrioService(IBarrioService barrioService) {
	this.barrioService = barrioService;
    }

    public IRolUsuarioService getRolUsuarioService() {
	return rolUsuarioService;
    }

    public void setRolUsuarioService(IRolUsuarioService rolUsuarioService) {
	this.rolUsuarioService = rolUsuarioService;
    }

    public IRolesPorUsuarioService getRolesPorUsuarioService() {
	return rolesPorUsuarioService;
    }

    public void setRolesPorUsuarioService(IRolesPorUsuarioService rolesPorUsuarioService) {
	this.rolesPorUsuarioService = rolesPorUsuarioService;
    }

    public IEstadiaService getEstadiaService() {
	return estadiaService;
    }

    public void setEstadiaService(IEstadiaService estadiaService) {
	this.estadiaService = estadiaService;
    }

    public IPerfilPlayaService getPerfilPlayaService() {
	return perfilPlayaService;
    }

    public void setPerfilPlayaService(IPerfilPlayaService perfilPlayaService) {
	this.perfilPlayaService = perfilPlayaService;
    }

    public ICargoEmpleadoService getCargoEmpleadoService() {
	return cargoEmpleadoService;
    }

    public void setCargoEmpleadoService(ICargoEmpleadoService cargoEmpleadoService) {
	this.cargoEmpleadoService = cargoEmpleadoService;
    }

    public IEmpleadoService getEmpleadoService() {
	return empleadoService;
    }

    public void setEmpleadoService(IEmpleadoService empleadoService) {
	this.empleadoService = empleadoService;
    }

    public List<Playa> getPlayaList() {
	return playaList;
    }

    public void setPlayaList(List<Playa> playaList) {
	PlayaManagedBean.playaList = playaList;
    }

    public String getCuit() {
	return cuit;
    }

    public void setCuit(String cuit) {
	this.cuit = cuit;
    }

    public Integer getDisponibilidad() {
	return disponibilidad;
    }

    public void setDisponibilidad(Integer disponibilidad) {
	this.disponibilidad = disponibilidad;
    }

    public String getDomicilio() {
	return domicilio;
    }

    public void setDomicilio(String domicilio) {
	this.domicilio = domicilio;
    }

    public String getNombreComercial() {
	return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
	this.nombreComercial = nombreComercial;
    }

    public Barrio getBarrio() {
	return barrio;
    }

    public void setBarrio(Barrio barrio) {
	this.barrio = barrio;
    }

    public EstadoPlaya getEstado() {
	return estado;
    }

    public void setEstado(EstadoPlaya estado) {
	this.estado = estado;
    }

    public String getRazonSocial() {
	return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
	this.razonSocial = razonSocial;
    }

    public String getDireccionBusqueda() {
	return direccionBusqueda;
    }

    public void setDireccionBusqueda(String direccionBusqueda) {
	this.direccionBusqueda = direccionBusqueda;
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

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public String getTelefono() {
	return telefono;
    }

    public void setTelefono(String telefono) {
	this.telefono = telefono;
    }

    public String getEmailPlaya() {
	return emailPlaya;
    }

    public void setEmailPlaya(String emailPlaya) {
	this.emailPlaya = emailPlaya;
    }

    /**
     * @return the url
     */
    public String getUrl() {
	return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
	this.url = url;
    }

    public void setSimpleModel(MapModel simpleModel) {
	this.simpleModel = simpleModel;
    }

    public MapModel getSimpleModel() {
	return simpleModel;
    }

    public Playa getPlayaSelected() {
	return playaSelected;
    }

    public void setPlayaSelected(Playa playaSelected) {
	PlayaManagedBean.playaSelected = playaSelected;
    }

    public void findPlayaById() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	int idPlayaSelected = Integer.parseInt(facesContext.getExternalContext().getRequestParameterMap().get("id"));
	PlayaManagedBean.playaSelected = getPlayaService().findById(idPlayaSelected);
    }

    public MapModel getAdvancedModel() {
	return advancedModel;
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
	marker = (Marker) event.getOverlay();
    }

    public Marker getMarker() {
	return marker;
    }

    public String getAcTxtPlaya() {
	return acTxtPlaya;
    }

    public void setAcTxtPlaya(String acTxtPlaya) {
	this.acTxtPlaya = acTxtPlaya;
    }

    public void listadoPlayasPDF(Object document) throws IOException, BadElementException, DocumentException {

	Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 18, Font.BOLD, Color.BLACK);

	Paragraph titulo = new Paragraph();
	titulo.add(new Paragraph("Listado de playas de estacionamiento registradas  ", fuenteNegra18));
	agregarLineasEnBlanco(titulo, 2);
	titulo.setAlignment(Element.ALIGN_CENTER);
	String sep = File.separator;
	Document pdf = (Document) document;
	pdf.open();
	pdf.setPageSize(PageSize.A4);
	ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
	String logo = extContext.getRealPath("resources" + sep + "images" + sep + "transacciones.png");
	pdf.addTitle("Listado de playas");
	pdf.add(Image.getInstance(logo));
	pdf.add(titulo);
    }

    private static void agregarLineasEnBlanco(Paragraph parrafo, int nLineas) {
	for (int i = 0; i < nLineas; i++)
	    parrafo.add(new Paragraph(" "));
    }

    @SuppressWarnings("unused")
    private String fechaActual() {
	Date hoy = new Date();
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	String fecha = formato.format(hoy);
	return fecha;

    }
}