/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.EstadoPlaya;
import tesis.playon.web.model.Mail;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.RolesPorUsuario;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IBarrioService;
import tesis.playon.web.service.IEstadoPlayaService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IRolesPorUsuarioService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.util.NotificadorUtil;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "auditoriaPlayaMB")
@ViewScoped
public class AuditoriaPlayaManagedBean implements Serializable {

    private static final long serialVersionUID = -4263708877363627431L;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    @ManagedProperty(value = "#{EstadoPlayaService}")
    IEstadoPlayaService estadoPlayaService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{RolesPorUsuarioService}")
    IRolesPorUsuarioService rolesPorUsuarioService;

    @ManagedProperty(value = "#{BarrioService}")
    IBarrioService barrioService;

    private EstadoPlaya estadoPendiente, estadoAprobada, estadoRechazada, estadoDeBaja;

    private static List<Playa> playasPendientesList;

    private static List<Playa> playasAprobadasList;

    private static List<Playa> playasRechazadasList;

    private static List<Playa> playasDeBajaList;

    public static Playa playaSeleccionada;

    private NotificadorUtil notificador;

    private Mail mail;

    private String asunto;

    private String mensaje;

    private List<Playa> filteredPlayas;

    private SelectItem[] barriosOptions;

    private SelectItem[] estadosOptions;

    // atributos para la edición
    private String cuit;

    private Integer disponibilidad;

    private String direccionBusqueda;

    private String domicilio;

    private String nombreComercial;

    private String razonSocial;

    private Barrio barrio;

    private String telefono;

    private String emailPlaya;

    private String url;

    @PostConstruct
    private void init() {
	estadoPendiente = getEstadoPlayaService().findByNombreEstadoPlaya("Pendiente");
	playasPendientesList = getPlayaService().findByEstado(estadoPendiente);
	
	estadoAprobada = getEstadoPlayaService().findByNombreEstadoPlaya("Aprobada");
	playasAprobadasList = getPlayaService().findByEstado(estadoAprobada);
	
	estadoRechazada = getEstadoPlayaService().findByNombreEstadoPlaya("Rechazada");
	playasRechazadasList = getPlayaService().findByEstado(estadoRechazada);

	estadoDeBaja = getEstadoPlayaService().findByNombreEstadoPlaya("De Baja");
	playasDeBajaList = getPlayaService().findByEstado(estadoDeBaja);
    }

    public String updatePlayaAuditor() {
	try {
	    if (playaSeleccionada.getEstado().getNombre().equals("De Baja")) {
		List<Usuario> userList = getUsuarioService().findByPlaya(playaSeleccionada);
		for (Usuario usuario : userList) {
		    usuario.setEnable(new Boolean(false));
		    getUsuarioService().update(usuario);
		}
	    }
	    getPlayaService().update(playaSeleccionada);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, playaSeleccionada.getNombreComercial()
		    + " se actualizó correctamente", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return "playaslist";

	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, "
		    + playaSeleccionada.getNombreComercial() + " no se pudo modificar",
		    "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}
	return "null";
    }

    public void rejectPlayaAuditoria() {
	try {
	    playaSeleccionada.setEstado(estadoRechazada);
	    getPlayaService().update(playaSeleccionada);
	    if (!StringUtils.isEmpty(playaSeleccionada.getEmail()) || playaSeleccionada.getEmail() != null) {

		notificador = new NotificadorUtil();
		mail = new Mail();
		mail.setDestinatario(playaSeleccionada.getEmail());
		asunto = "Su solicitud ha sido rechazada";
		mail.setAsunto(asunto);
		mensaje = " Los datos de su solicitud de playa no son correctos o verdaderos, por lo tanto hemos rechazado su solicitud,"
			+ " comuníquese con el equipo de Playón mediante el siguiente link http://localhost:8080/tesis-playon-web/contact.html";
		mail.setMensaje(mensaje);

		notificador.enviarMailAuditor(mail);

	    }

	    //estadoPendiente = getEstadoPlayaService().findByNombreEstadoPlaya("Pendiente");
	    playasPendientesList = getPlayaService().findByEstado(estadoPendiente);

	    //estadoRechazada = getEstadoPlayaService().findByNombreEstadoPlaya("Rechazada");
	    playasRechazadasList = getPlayaService().findByEstado(estadoRechazada);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se rechazó la playa: "
		    + playaSeleccionada.getNombreComercial(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);

	    // return "playalist";
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo rechazar la playa: " + playaSeleccionada.getNombreComercial(),
		    "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}
	// return null;
    }

    public void approvePlayaAuditoria() {
	try {

	    playaSeleccionada.setEstado(estadoAprobada);

	    List<Usuario> userList = getUsuarioService().findByPlaya(playaSeleccionada);

	    for (Usuario usuario : userList) {

		RolesPorUsuario rolUsuario = getRolesPorUsuarioService().findByNombreUsuario(usuario.getNombreUser());

		if (rolUsuario.getRol().equals("ROLE_PLAYA_GERENTE")) {
		    usuario.setEnable(new Boolean(true));
		    getUsuarioService().update(usuario);
		}
	    }
	    getPlayaService().update(playaSeleccionada);
	    if (!StringUtils.isEmpty(playaSeleccionada.getEmail()) || playaSeleccionada.getEmail() != null) {

		mail = new Mail();
		notificador = new NotificadorUtil();
		asunto = " PLAYON - RED DE PLAYAS DE ESTACIONAMIENTO ";
		mensaje = "¡Felicitaciones la playa de estacionamiento "
			+ playaSeleccionada.getNombreComercial().toUpperCase() + " ha sido aprobada"
			+ " para formar parte nuestro sistema!";
		mail.setAsunto(asunto);
		mail.setMensaje(mensaje);
		mail.setDestinatario(playaSeleccionada.getEmail());
		notificador.enviarMailAuditor(mail);
	    }

	    //estadoPendiente = getEstadoPlayaService().findByNombreEstadoPlaya("Pendiente");
	    playasPendientesList = getPlayaService().findByEstado(estadoPendiente);

	    //estadoAprobada = getEstadoPlayaService().findByNombreEstadoPlaya("Aprobada");
	    playasAprobadasList = getPlayaService().findByEstado(estadoAprobada);

	    //estadoRechazada = getEstadoPlayaService().findByNombreEstadoPlaya("Rechazada");
	    playasRechazadasList = getPlayaService().findByEstado(estadoRechazada);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se aprobó la playa: "
		    + playaSeleccionada.getNombreComercial(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);

	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, no se pudo aprobar la playa: "
		    + playaSeleccionada.getNombreComercial(), "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}
    }

    /**
     * @return the playaService
     */
    public IPlayaService getPlayaService() {
	return playaService;
    }

    /**
     * @param playaService
     *            the playaService to set
     */
    public void setPlayaService(IPlayaService playaService) {
	this.playaService = playaService;
    }

    /**
     * @return the estadoPlayaService
     */
    public IEstadoPlayaService getEstadoPlayaService() {
	return estadoPlayaService;
    }

    /**
     * @param estadoPlayaService
     *            the estadoPlayaService to set
     */
    public void setEstadoPlayaService(IEstadoPlayaService estadoPlayaService) {
	this.estadoPlayaService = estadoPlayaService;
    }

    /**
     * @return the usuarioService
     */
    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    /**
     * @param usuarioService
     *            the usuarioService to set
     */
    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    /**
     * @return the rolesPorUsuarioService
     */
    public IRolesPorUsuarioService getRolesPorUsuarioService() {
	return rolesPorUsuarioService;
    }

    /**
     * @param rolesPorUsuarioService
     *            the rolesPorUsuarioService to set
     */
    public void setRolesPorUsuarioService(IRolesPorUsuarioService rolesPorUsuarioService) {
	this.rolesPorUsuarioService = rolesPorUsuarioService;
    }

    /**
     * @return the barrioService
     */
    public IBarrioService getBarrioService() {
	return barrioService;
    }

    /**
     * @param barrioService
     *            the barrioService to set
     */
    public void setBarrioService(IBarrioService barrioService) {
	this.barrioService = barrioService;
    }

    /**
     * @return the playasPendientesList
     */
    public List<Playa> getPlayasPendientesList() {
	return playasPendientesList;
    }

    /**
     * @param pPlayasPendientesList
     *            the playasPendientesList to set
     */
    public void setPlayasPendientesList(List<Playa> playasPendientesList) {
	AuditoriaPlayaManagedBean.playasPendientesList = playasPendientesList;
    }

    /**
     * @return the playasApobadasList
     */
    public List<Playa> getPlayasAprobadasList() {
	return playasAprobadasList;
    }

    /**
     * @param playasApobadasList
     *            the playasApobadasList to set
     */
    public void setPlayasAprobadasList(List<Playa> playasAprobadasList) {
	AuditoriaPlayaManagedBean.playasAprobadasList = playasAprobadasList;
    }

    /**
     * @return the playasRechazadasList
     */
    public List<Playa> getPlayasRechazadasList() {
	return playasRechazadasList;
    }

    /**
     * @param pPlayasRechazadasList
     *            the playasRechazadasList to set
     */
    public void setPlayasRechazadasList(List<Playa> playasRechazadasList) {
	AuditoriaPlayaManagedBean.playasRechazadasList = playasRechazadasList;
    }

    /**
     * @return the playasDeBajaList
     */
    public List<Playa> getPlayasDeBajaList() {
	return playasDeBajaList;
    }

    /**
     * @param pPlayasDeBajaList
     *            the playasDeBajaList to set
     */
    public void setPlayasDeBajaList(List<Playa> playasDeBajaList) {
	AuditoriaPlayaManagedBean.playasDeBajaList = playasDeBajaList;
    }

    /**
     * @return the estadoPendiente
     */
    public EstadoPlaya getEstadoPendiente() {
	return estadoPendiente;
    }

    /**
     * @param estadoPendiente
     *            the estadoPendiente to set
     */
    public void setEstadoPendiente(EstadoPlaya estadoPendiente) {
	this.estadoPendiente = estadoPendiente;
    }

    /**
     * @return the estadoAprobada
     */
    public EstadoPlaya getEstadoAprobada() {
	return estadoAprobada;
    }

    /**
     * @param estadoAprobada
     *            the estadoAprobada to set
     */
    public void setEstadoAprobada(EstadoPlaya estadoAprobada) {
	this.estadoAprobada = estadoAprobada;
    }

    /**
     * @return the estadoRechazada
     */
    public EstadoPlaya getEstadoRechazada() {
	return estadoRechazada;
    }

    /**
     * @param estadoRechazada
     *            the estadoRechazada to set
     */
    public void setEstadoRechazada(EstadoPlaya estadoRechazada) {
	this.estadoRechazada = estadoRechazada;
    }

    /**
     * @return the estadoDeBaja
     */
    public EstadoPlaya getEstadoDeBaja() {
	return estadoDeBaja;
    }

    /**
     * @param estadoDeBaja
     *            the estadoDeBaja to set
     */
    public void setEstadoDeBaja(EstadoPlaya estadoDeBaja) {
	this.estadoDeBaja = estadoDeBaja;
    }

    /**
     * @return the playaSeleccionada
     */
    public Playa getPlayaSeleccionada() {
	return playaSeleccionada;
    }

    /**
     * @param pPlayaSeleccionada
     *            the playaSeleccionada to set
     */
    public void setPlayaSeleccionada(Playa pPlayaSeleccionada) {
	playaSeleccionada = pPlayaSeleccionada;
    }

    /**
     * @return the filteredPlayas
     */
    public List<Playa> getFilteredPlayas() {
	return filteredPlayas;
    }

    /**
     * @param filteredPlayas
     *            the filteredPlayas to set
     */
    public void setFilteredPlayas(List<Playa> filteredPlayas) {
	this.filteredPlayas = filteredPlayas;
    }

    /**
     * @return the barriosOptions
     */
    public SelectItem[] getBarriosOptions() {
	if (barriosOptions == null) {
	    List<Barrio> barrios = new ArrayList<Barrio>();
	    barrios.addAll(getBarrioService().findAll());
	    barriosOptions = new SelectItem[barrios.size() + 1];
	    barriosOptions[0] = new SelectItem("", "Todos");

	    for (int i = 0; i < barrios.size(); i++) {
		barriosOptions[i + 1] = new SelectItem(barrios.get(i), barrios.get(i).getNombre());
	    }
	}

	return barriosOptions;
    }

    /**
     * @param barriosOptions
     *            the barriosOptions to set
     */
    public void setBarriosOptions(SelectItem[] barriosOptions) {
	this.barriosOptions = barriosOptions;
    }

    /**
     * @return the estadosOptions
     */
    public SelectItem[] getEstadosOptions() {
	if (estadosOptions == null) {
	    List<EstadoPlaya> estados = new ArrayList<EstadoPlaya>();
	    estados.addAll(getEstadoPlayaService().findAll());
	    estadosOptions = new SelectItem[estados.size() + 1];
	    estadosOptions[0] = new SelectItem("", "Todos");

	    for (int i = 0; i < estados.size(); i++) {
		estadosOptions[i + 1] = new SelectItem(estados.get(i), estados.get(i).getNombre());
	    }
	}
	return estadosOptions;
    }

    /**
     * @param estadosOptions
     *            the estadosOptions to set
     */
    public void setEstadosOptions(SelectItem[] estadosOptions) {
	this.estadosOptions = estadosOptions;
    }

    /**
     * @return the notificador
     */
    public NotificadorUtil getNotificador() {
	return notificador;
    }

    /**
     * @param notificador
     *            the notificador to set
     */
    public void setNotificador(NotificadorUtil notificador) {
	this.notificador = notificador;
    }

    /**
     * @return the mail
     */
    public Mail getMail() {
	return mail;
    }

    /**
     * @param mail
     *            the mail to set
     */
    public void setMail(Mail mail) {
	this.mail = mail;
    }

    /**
     * @return the asunto
     */
    public String getAsunto() {
	return asunto;
    }

    /**
     * @param asunto
     *            the asunto to set
     */
    public void setAsunto(String asunto) {
	this.asunto = asunto;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
	return mensaje;
    }

    /**
     * @param mensaje
     *            the mensaje to set
     */
    public void setMensaje(String mensaje) {
	this.mensaje = mensaje;
    }

    /**
     * @return the cuit
     */
    public String getCuit() {
	return cuit;
    }

    /**
     * @param cuit
     *            the cuit to set
     */
    public void setCuit(String cuit) {
	this.cuit = cuit;
    }

    /**
     * @return the disponibilidad
     */
    public Integer getDisponibilidad() {
	return disponibilidad;
    }

    /**
     * @param disponibilidad
     *            the disponibilidad to set
     */
    public void setDisponibilidad(Integer disponibilidad) {
	this.disponibilidad = disponibilidad;
    }

    /**
     * @return the direccionBusqueda
     */
    public String getDireccionBusqueda() {
	return direccionBusqueda;
    }

    /**
     * @param direccionBusqueda
     *            the direccionBusqueda to set
     */
    public void setDireccionBusqueda(String direccionBusqueda) {
	this.direccionBusqueda = direccionBusqueda;
    }

    /**
     * @return the domicilio
     */
    public String getDomicilio() {
	return domicilio;
    }

    /**
     * @param domicilio
     *            the domicilio to set
     */
    public void setDomicilio(String domicilio) {
	this.domicilio = domicilio;
    }

    /**
     * @return the nombreComercial
     */
    public String getNombreComercial() {
	return nombreComercial;
    }

    /**
     * @param nombreComercial
     *            the nombreComercial to set
     */
    public void setNombreComercial(String nombreComercial) {
	this.nombreComercial = nombreComercial;
    }

    /**
     * @return the razonSocial
     */
    public String getRazonSocial() {
	return razonSocial;
    }

    /**
     * @param razonSocial
     *            the razonSocial to set
     */
    public void setRazonSocial(String razonSocial) {
	this.razonSocial = razonSocial;
    }

    /**
     * @return the barrio
     */
    public Barrio getBarrio() {
	return barrio;
    }

    /**
     * @param barrio
     *            the barrio to set
     */
    public void setBarrio(Barrio barrio) {
	this.barrio = barrio;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
	return telefono;
    }

    /**
     * @param telefono
     *            the telefono to set
     */
    public void setTelefono(String telefono) {
	this.telefono = telefono;
    }

    /**
     * @return the emailPlaya
     */
    public String getEmailPlaya() {
	return emailPlaya;
    }

    /**
     * @param emailPlaya
     *            the emailPlaya to set
     */
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
}
