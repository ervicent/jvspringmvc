/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.DenunciaPlaya;
import tesis.playon.web.model.DenunciaVehiculo;
import tesis.playon.web.model.EstadoDenuncia;
import tesis.playon.web.model.EstadoPlaya;
import tesis.playon.web.model.Mail;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.RolesPorUsuario;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IBarrioService;
import tesis.playon.web.service.IDenunciaPlayaService;
import tesis.playon.web.service.IDenunciaVehiculoService;
import tesis.playon.web.service.IEstadoDenunciaService;
import tesis.playon.web.service.IEstadoPlayaService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IRolesPorUsuarioService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.util.NotificadorUtil;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "auditoriaMB")
@ViewScoped
public class AuditoriaManagedBean implements Serializable {

	private static final long serialVersionUID = -1085389423375986168L;

	private static final String ERROR = "error";

	private NotificadorUtil notificador;

	private Mail mail;

	private String asunto;

	private String mensaje;

	@ManagedProperty(value = "#{PlayaService}")
	IPlayaService playaService;

	@ManagedProperty(value = "#{EstadoPlayaService}")
	IEstadoPlayaService estadoPlayaService;

	@ManagedProperty(value = "#{BarrioService}")
	IBarrioService barrioService;

	@ManagedProperty(value = "#{UsuarioService}")
	IUsuarioService usuarioService;

	@ManagedProperty(value = "#{RolesPorUsuarioService}")
	IRolesPorUsuarioService rolesPorUsuarioService;

	@ManagedProperty(value = "#{EstadoDenunciaService}")
	IEstadoDenunciaService estadoDenunciaService;

	@ManagedProperty(value = "#{DenunciaVehiculoService}")
	IDenunciaVehiculoService denunciaVehiculoService;

	@ManagedProperty(value = "#{DenunciaPlayaService}")
	IDenunciaPlayaService denunciaPlayaService;

	public static Playa playaSeleccionada;

	public static DenunciaVehiculo denunciaVehiculoSeleccionada;

	public static DenunciaPlaya denunciaPlayaSeleccionada;

	private String cuit;

	private Integer disponibilidad;

	private String direccionBusqueda;

	private String domicilio;

	private String nombreComercial;

	private String razonSocial;

	private Barrio barrio;

	private String telefono;

	private String emailPlaya;

	List<Playa> playasPendientesList;

	List<Playa> playasRechazadasList;

	List<DenunciaVehiculo> denunciasPendientesVehiculosList;

	List<DenunciaVehiculo> denunciasEnProcesoVehiculosList;

	List<DenunciaVehiculo> denunciasRechazadasVehiculosList;

	List<DenunciaVehiculo> denunciasFinalizadasVehiculosList;

	List<DenunciaPlaya> denunciasPendientesPlayaList;

	List<DenunciaPlaya> denunciasEnProcesoPlayaList;

	List<DenunciaPlaya> denunciasRechazadasPlayaList;

	List<DenunciaPlaya> denunciasFinalizadasPlayaList;

	private List<Playa> filteredPlayas;

	private static String previusPage;

	@SuppressWarnings("unused")
	private SelectItem[] barriosOptions;

	@SuppressWarnings("unused")
	private SelectItem[] estadosOptions;

	/********************* METODOS PARA PLAYAS ****************************************************************/
	public String updatePlayaAuditor() {
		try {
			if (playaSeleccionada.getEstado().getNombre().equals("De Baja")) {
				List<Usuario> userList = getUsuarioService().findByPlaya(
						playaSeleccionada);
				for (Usuario usuario : userList) {
					usuario.setEnable(new Boolean(false));
					getUsuarioService().update(usuario);
				}
			}
			getPlayaService().update(playaSeleccionada);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					playaSeleccionada.getNombreComercial()
							+ " se modificó correctamente", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return previusPage;

		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Error, "
							+ playaSeleccionada.getNombreComercial()
							+ " no se pudo modificar",
					"Por favor, inténtelo más tarde.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}
		return ERROR;
	}

	public void rejectPlayaAuditoria() {
		try {
			EstadoPlaya estado = getEstadoPlayaService()
					.findByNombreEstadoPlaya("Rechazada");
			playaSeleccionada.setEstado(estado);
			getPlayaService().update(playaSeleccionada);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Se rechazó la playa: "
							+ playaSeleccionada.getNombreComercial(), "");
			notificador = new NotificadorUtil();
			mail = new Mail();
			mail.setDestinatario(playaSeleccionada.getEmail());
			asunto = "Su solicitud ha sido rechazada";
			mail.setAsunto(asunto);
			mensaje = " Los datos de su solicitud de playa no son correctos o verdaderos, por lo tanto hemos rechazado su solicitud,"
					+ " comuníquese con el equipo de Playon mediante el siguiente link http://localhost:8080/tesis-playon-web/contact.html";
			mail.setMensaje(mensaje);

			notificador.enviarMailAuditor(mail);

			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, no se pudo rechazar la playa: "
							+ playaSeleccionada.getNombreComercial(),
					"Por favor, inténtelo más tarde.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void approvePlayaAuditoria() {
		try {

			EstadoPlaya estado = getEstadoPlayaService()
					.findByNombreEstadoPlaya("Aprobada");

			playaSeleccionada.setEstado(estado);

			List<Usuario> userList = getUsuarioService().findByPlaya(
					playaSeleccionada);

			for (Usuario usuario : userList) {

				RolesPorUsuario rolUsuario = getRolesPorUsuarioService()
						.findByNombreUsuario(usuario.getNombreUser());

				if (rolUsuario.getRol().equals("ROLE_PLAYA_GERENTE")) {
					usuario.setEnable(new Boolean(true));
					getUsuarioService().update(usuario);
				}
			}
			getPlayaService().update(playaSeleccionada);
			mail = new Mail();
			notificador = new NotificadorUtil();
			asunto = " PLAYON - RED DE PLAYAS DE ESTACIONAMIENTO ";
			mensaje = "¡Felicitaciones la playa de estacionamiento "
					+ playaSeleccionada.getNombreComercial().toUpperCase()
					+ " ha sido aprobada"
					+ " para formar parte nuestro sistema!";
			mail.setAsunto(asunto);
			mail.setMensaje(mensaje);
			mail.setDestinatario(playaSeleccionada.getEmail());
			notificador.enviarMailAuditor(mail);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Se aprobó la playa: "
							+ playaSeleccionada.getNombreComercial(), "");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, no se pudo aprobar la playa: "
							+ playaSeleccionada.getNombreComercial(),
					"Por favor, inténtelo más tarde.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	/************************** METODOS PARA DENUNCIAS VEHICULOS ***************************************/
	public void rejectDenunciaVehiculo() {
		try {
			EstadoDenuncia estado = getEstadoDenunciaService()
					.findByNombreEstadoDenuncia("Rechazada");
			denunciaVehiculoSeleccionada.setEstado(estado);
			getDenunciaVehiculoService().update(denunciaVehiculoSeleccionada);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Se rechazó la denuncia ", "");

			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, no se pudo rechazar la denuncia: " + "",
					"Por favor, inténtelo más tarde.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void approveDenunciaVehiculo() {
		try {
			EstadoDenuncia estado = getEstadoDenunciaService()
					.findByNombreEstadoDenuncia("Aceptada");
			denunciaVehiculoSeleccionada.setEstado(estado);
			getDenunciaVehiculoService().update(denunciaVehiculoSeleccionada);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"La denuncia se dio por FINALIZADA ", "");

			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, no se pudo finalizar la denuncia: " + "",
					"Por favor, inténtelo más tarde.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void procesarDenunciaVehiculo() {
		try {
			EstadoDenuncia estado = getEstadoDenunciaService()
					.findByNombreEstadoDenuncia("En Proceso");
			denunciaVehiculoSeleccionada.setEstado(estado);
			getDenunciaVehiculoService().update(denunciaVehiculoSeleccionada);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"La denuncia se aceptó y será investigada ", "");

			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, no se pudo procesar la denuncia: " + "",
					"Por favor, inténtelo más tarde.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	/*************************** METODOS AUDIT. DENUNCIA A PLAYUAS *****************************/

	public void procesarDenunciaPlaya() {
		try {
			EstadoDenuncia estado = getEstadoDenunciaService()
					.findByNombreEstadoDenuncia("En Proceso");
			denunciaPlayaSeleccionada.setEstado(estado);
			getDenunciaPlayaService().update(denunciaPlayaSeleccionada);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"La denuncia se aceptó y será investigada ", "");

			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, no se pudo procesar la denuncia: " + "",
					"Por favor, inténtelo más tarde.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void rejectDenunciaPlaya() {
		try {
			EstadoDenuncia estado = getEstadoDenunciaService()
					.findByNombreEstadoDenuncia("Rechazada");
			denunciaPlayaSeleccionada.setEstado(estado);
			getDenunciaPlayaService().update(denunciaPlayaSeleccionada);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Se rechazó la denuncia ", "");

			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, no se pudo rechazar la denuncia: " + "",
					"Por favor, inténtelo más tarde.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void approveDenunciaPlaya() {
		try {
			EstadoDenuncia estado = getEstadoDenunciaService()
					.findByNombreEstadoDenuncia("Aceptada");
			denunciaPlayaSeleccionada.setEstado(estado);
			getDenunciaPlayaService().update(denunciaPlayaSeleccionada);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"La denuncia se dio por FINALIZADA ", "");

			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, no se pudo finalizar la denuncia: " + "",
					"Por favor, inténtelo más tarde.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	/********************* SERVICIOS + GET + SET **********************************************/
	public IEstadoDenunciaService getEstadoDenunciaService() {
		return estadoDenunciaService;
	}

	public void setEstadoDenunciaService(
			IEstadoDenunciaService estadoDenunciaService) {
		this.estadoDenunciaService = estadoDenunciaService;
	}

	public IDenunciaVehiculoService getDenunciaVehiculoService() {
		return denunciaVehiculoService;
	}

	public void setDenunciaVehiculoService(
			IDenunciaVehiculoService denunciaVehiculoService) {
		this.denunciaVehiculoService = denunciaVehiculoService;
	}

	public String returnPage() {
		return previusPage;
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

	public IUsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public IRolesPorUsuarioService getRolesPorUsuarioService() {
		return rolesPorUsuarioService;
	}

	public void setRolesPorUsuarioService(
			IRolesPorUsuarioService rolesPorUsuarioService) {
		this.rolesPorUsuarioService = rolesPorUsuarioService;
	}

	/*************************** METODOS GET / SET PARA DENUNCIA DE PLAYA *************************/

	public IDenunciaPlayaService getDenunciaPlayaService() {
		return denunciaPlayaService;
	}

	public void setDenunciaPlayaService(
			IDenunciaPlayaService denunciaPlayaService) {
		this.denunciaPlayaService = denunciaPlayaService;
	}

	public List<DenunciaPlaya> getDenunciasPendientesPlayaList() {
		denunciasPendientesPlayaList = new ArrayList<DenunciaPlaya>();
		EstadoDenuncia estado = new EstadoDenuncia();
		estado = getEstadoDenunciaService().findByNombreEstadoDenuncia(
				"Pendiente");
		denunciasPendientesPlayaList = getDenunciaPlayaService()
				.findByEstadoDenunciaPlayas(estado);
		return denunciasPendientesPlayaList;
	}

	public void setDenunciasPendientesPlayaList(
			List<DenunciaPlaya> denunciasPendientesPlayaList) {
		this.denunciasPendientesPlayaList = denunciasPendientesPlayaList;
	}

	public List<DenunciaPlaya> getDenunciasEnProcesoPlayaList() {
		denunciasEnProcesoPlayaList = new ArrayList<DenunciaPlaya>();
		EstadoDenuncia estado = new EstadoDenuncia();
		estado = getEstadoDenunciaService().findByNombreEstadoDenuncia(
				"En Proceso");
		denunciasEnProcesoPlayaList = getDenunciaPlayaService()
				.findByEstadoDenunciaPlayas(estado);
		return denunciasEnProcesoPlayaList;
	}

	public void setDenunciasEnProcesoPlayaList(
			List<DenunciaPlaya> denunciasEnProcesoPlayaList) {
		this.denunciasEnProcesoPlayaList = denunciasEnProcesoPlayaList;
	}

	public List<DenunciaPlaya> getDenunciasRechazadasPlayaList() {
		denunciasRechazadasPlayaList = new ArrayList<DenunciaPlaya>();
		EstadoDenuncia estado = new EstadoDenuncia();
		estado = getEstadoDenunciaService().findByNombreEstadoDenuncia(
				"Rechazada");
		denunciasRechazadasPlayaList = getDenunciaPlayaService()
				.findByEstadoDenunciaPlayas(estado);
		return denunciasRechazadasPlayaList;
	}

	public void setDenunciasRechazadasPlayaList(
			List<DenunciaPlaya> denunciasRechazadasPlayaList) {
		this.denunciasRechazadasPlayaList = denunciasRechazadasPlayaList;
	}

	public List<DenunciaPlaya> getDenunciasFinalizadasPlayaList() {
		denunciasFinalizadasPlayaList = new ArrayList<DenunciaPlaya>();
		EstadoDenuncia estado = new EstadoDenuncia();
		estado = getEstadoDenunciaService().findByNombreEstadoDenuncia(
				"Aceptada");
		denunciasFinalizadasPlayaList = getDenunciaPlayaService()
				.findByEstadoDenunciaPlayas(estado);
		return denunciasFinalizadasPlayaList;
	}

	public void setDenunciasFinalizadasPlayaList(
			List<DenunciaPlaya> denunciasFinalizadasPlayaList) {
		this.denunciasFinalizadasPlayaList = denunciasFinalizadasPlayaList;
	}

	/******************************* GET/SET AUDIT DE PLAYAS ***********************************************/

	public List<Playa> getPlayasPendientesList() {
		playasPendientesList = new ArrayList<Playa>();
		EstadoPlaya estado = new EstadoPlaya();
		estado = getEstadoPlayaService().findByNombreEstadoPlaya("Pendiente");
		playasPendientesList = getPlayaService().findByEstado(estado);
		return playasPendientesList;
	}

	public void setPlayasPendientesList(List<Playa> playasPendientesList) {
		this.playasPendientesList = playasPendientesList;
	}

	public List<Playa> getPlayasRechazadasList() {
		playasRechazadasList = new ArrayList<Playa>();
		EstadoPlaya estado = new EstadoPlaya();
		estado = getEstadoPlayaService().findByNombreEstadoPlaya("Rechazada");
		playasRechazadasList = getPlayaService().findByEstado(estado);
		return playasRechazadasList;
	}

	public void setPlayasRechazadasList(List<Playa> playasRechazadasList) {
		this.playasRechazadasList = playasRechazadasList;
	}

	public List<Playa> getFilteredPlayas() {
		return filteredPlayas;
	}

	public void setFilteredPlayas(List<Playa> filteredPlayas) {
		this.filteredPlayas = filteredPlayas;
	}

	public Playa getPlayaSeleccionada() {

		return playaSeleccionada;
	}

	public void setPlayaSeleccionada(Playa playaSeleccionada) {
		AuditoriaManagedBean.playaSeleccionada = playaSeleccionada;
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

	public String getDireccionBusqueda() {
		return direccionBusqueda;
	}

	public void setDireccionBusqueda(String direccionBusqueda) {
		this.direccionBusqueda = direccionBusqueda;
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

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public Barrio getBarrio() {
		return barrio;
	}

	public void setBarrio(Barrio barrio) {
		this.barrio = barrio;
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

	public SelectItem[] getBarriosOptions() {
		List<Barrio> barrios = new ArrayList<Barrio>();
		barrios.addAll(getBarrioService().findAll());
		barriosOptions = new SelectItem[barrios.size() + 1];
		SelectItem[] options = new SelectItem[barrios.size() + 1];
		options[0] = new SelectItem("", "Todos");

		for (int i = 0; i < barrios.size(); i++) {
			options[i + 1] = new SelectItem(barrios.get(i), barrios.get(i)
					.getNombre());
		}
		return options;
	}

	public void setBarriosOptions(SelectItem[] barriosOptions) {
		this.barriosOptions = barriosOptions;
	}

	public SelectItem[] getEstadosOptions() {
		List<EstadoPlaya> estados = new ArrayList<EstadoPlaya>();
		estados.addAll(getEstadoPlayaService().findAll());
		estadosOptions = new SelectItem[estados.size() + 1];
		SelectItem[] options = new SelectItem[estados.size() + 1];
		options[0] = new SelectItem("", "Todos");

		for (int i = 0; i < estados.size(); i++) {
			options[i + 1] = new SelectItem(estados.get(i), estados.get(i)
					.getNombre());
		}
		return options;
	}

	public void setEstadosOptions(SelectItem[] estadosOptions) {
		this.estadosOptions = estadosOptions;
	}

	public String getPreviusPage() {
		return previusPage;
	}

	public void setPreviusPage(String previusPage) {
		AuditoriaManagedBean.previusPage = previusPage;
	}

	public List<DenunciaVehiculo> getDenunciasPendientesVehiculosList() {
		denunciasPendientesVehiculosList = new ArrayList<DenunciaVehiculo>();
		EstadoDenuncia estado = new EstadoDenuncia();
		estado = getEstadoDenunciaService().findByNombreEstadoDenuncia(
				"Pendiente");
		denunciasPendientesVehiculosList = getDenunciaVehiculoService()
				.findByEstadoDenunciaVehiculo(estado);
		return denunciasPendientesVehiculosList;
	}

	public void setDenunciasPendientesVehiculosList(
			List<DenunciaVehiculo> denunciasPendientesVehiculosList) {
		this.denunciasPendientesVehiculosList = denunciasPendientesVehiculosList;
	}

	public List<DenunciaVehiculo> getDenunciasRechazadasVehiculosList() {
		denunciasPendientesVehiculosList = new ArrayList<DenunciaVehiculo>();
		EstadoDenuncia estado = new EstadoDenuncia();
		estado = getEstadoDenunciaService().findByNombreEstadoDenuncia(
				"Rechazada");
		denunciasPendientesVehiculosList = getDenunciaVehiculoService()
				.findByEstadoDenunciaVehiculo(estado);
		return denunciasPendientesVehiculosList;
	}

	public void setDenunciasRechazadasVehiculosList(
			List<DenunciaVehiculo> denunciasRechazadasVehiculosList) {
		this.denunciasRechazadasVehiculosList = denunciasRechazadasVehiculosList;
	}

	public List<DenunciaVehiculo> getDenunciasFinalizadasVehiculosList() {
		denunciasFinalizadasVehiculosList = new ArrayList<DenunciaVehiculo>();
		EstadoDenuncia estado = new EstadoDenuncia();
		estado = getEstadoDenunciaService().findByNombreEstadoDenuncia(
				"Aceptada");
		denunciasFinalizadasVehiculosList = getDenunciaVehiculoService()
				.findByEstadoDenunciaVehiculo(estado);
		return denunciasFinalizadasVehiculosList;
	}

	public void setDenunciasFinalizadasVehiculosList(
			List<DenunciaVehiculo> denunciasAceptadasVehiculosList) {
		this.denunciasFinalizadasVehiculosList = denunciasAceptadasVehiculosList;
	}

	public DenunciaVehiculo getDenunciaVehiculoSeleccionada() {
		return denunciaVehiculoSeleccionada;
	}

	public void setDenunciaVehiculoSeleccionada(
			DenunciaVehiculo denunciaVehiculoSeleccionada) {
		AuditoriaManagedBean.denunciaVehiculoSeleccionada = denunciaVehiculoSeleccionada;
	}

	public DenunciaPlaya getDenunciaPlayaSeleccionada() {
		return denunciaPlayaSeleccionada;
	}

	public void setDenunciaPlayaSeleccionada(
			DenunciaPlaya pdenunciaPlayaSeleccionada) {
		AuditoriaManagedBean.denunciaPlayaSeleccionada = pdenunciaPlayaSeleccionada;
	}

	public List<DenunciaVehiculo> getDenunciasEnProcesoVehiculosList() {
		denunciasPendientesVehiculosList = new ArrayList<DenunciaVehiculo>();
		EstadoDenuncia estado = new EstadoDenuncia();
		estado = getEstadoDenunciaService().findByNombreEstadoDenuncia(
				"En Proceso");
		denunciasPendientesVehiculosList = getDenunciaVehiculoService()
				.findByEstadoDenunciaVehiculo(estado);
		return denunciasPendientesVehiculosList;
	}

	public void setDenunciasEnProcesoVehiculosList(
			List<DenunciaVehiculo> denunciasEnProcesoVehiculosList) {
		this.denunciasEnProcesoVehiculosList = denunciasEnProcesoVehiculosList;
	}

}