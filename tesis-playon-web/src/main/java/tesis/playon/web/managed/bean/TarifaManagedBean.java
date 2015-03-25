/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Tarifa;
import tesis.playon.web.model.TipoEstadia;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.ICategoriaVehiculoService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.ITarifaService;
import tesis.playon.web.service.ITipoEstadiaService;
import tesis.playon.web.service.IUsuarioService;

/**
 * @author Pablo
 * 
 */
@ManagedBean(name = "tarifaMB")
@RequestScoped
public class TarifaManagedBean implements Serializable {

	private static final long serialVersionUID = -1085389423375986168L;

	private static final String LISTA_TARIFAS = "tarifalist";

	private static final String ADD_TARIFAS_SUCCESS = "tarifaend";

	private static final String ERROR = "error";

	@ManagedProperty(value = "#{TarifaService}")
	ITarifaService tarifaService;

	@ManagedProperty(value = "#{UsuarioService}")
	IUsuarioService usuarioService;

	@ManagedProperty(value = "#{TipoEstadiaService}")
	ITipoEstadiaService tipoEstadiaService;

	@ManagedProperty(value = "#{CategoriaVehiculoService}")
	ICategoriaVehiculoService categoriaVehiculoService;

	@ManagedProperty(value = "#{PlayaService}")
	IPlayaService playaService;

	List<Tarifa> tarifaList;

	private List<Tarifa> tarifaPlayaList;

	private List<Tarifa> tarifaPlayaLogged;

	private List<Tarifa> promocionesPlayaList;

	private Integer id;

	private Float importe;

	private Boolean vigente;

	private Date fechaAlta;

	private Date fechaBaja;

	private Playa playa;

	private TipoEstadia tipoEstadia;

	private CategoriaVehiculo categoriaVehiculo;

	private static Tarifa tarifaSelected;

	private static Playa playaSelected;

	private Playa playaLogged;

	private String autoPorHora;
	private String utilitarioPorHora;
	private String pickupPorHora;
	private String motoPorHora;

	private String autoPorDia;
	private String utilitarioPorDia;
	private String pickupPorDia;
	private String motoPorDia;

	private String autoPorNoche;
	private String utilitarioPorNoche;
	private String pickupPorNoche;
	private String motoPorNoche;

	private String autoPorSemana;
	private String utilitarioPorSemana;
	private String pickupPorSemana;
	private String motoPorSemana;

	private String autoPorMes;
	private String utilitarioPorMes;
	private String pickupPorMes;
	private String motoPorMes;

	// ATRIBUTO PARA EL FRONTEND

	private List<Tarifa> tarifaListSelected;

	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String userName = facesContext.getExternalContext().getRemoteUser();
		Usuario user = getUsuarioService().findByNombreUsuario(userName);
		if (user != null && user.getPlaya() != null) {
			this.setPlaya(user.getPlaya());
			this.setPlayaLogged(playa);
			tarifaListEmpleados = getTarifaService().findTarifaVigenteByPlaya(
					user.getPlaya());
		}
	}

	public String addTarifaAdmin() {
		try {
			Tarifa tarifa = new Tarifa();

			tarifa.setImporte(getImporte());
			tarifa.setVigente(getVigente());

			tarifa.setPlaya(getPlaya());
			tarifa.setTipoEstadia(getTipoEstadia());
			tarifa.setCategoriaVehiculo(getCategoriaVehiculo());
			getTarifaService().save(tarifa);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Se agregó la tarifa correctamente", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return LISTA_TARIFAS;
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, no se pudo agregar la tarifa"
							+ tarifaSelected.getPlaya().getNombreComercial(),
					"Por favor, intételo más tarde.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}
		return ERROR;
	}

	public String deleteTarifaAdmin(Tarifa tarifa) {
		try {
			tarifa.setFechaBaja(new Date());
			getTarifaService().update(tarifa);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Se borró la tarifa de la playa: "
							+ tarifaSelected.getPlaya().getNombreComercial(),
					"");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return LISTA_TARIFAS;
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, no se pudo borrar la tarifa de la playa: "
							+ tarifaSelected.getPlaya().getNombreComercial(),
					"Por favor, inténtelo más tarde.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return ERROR;
	}

	public void enableTarifa(Tarifa tarifa) {
		try {
			tarifa.setVigente(new Boolean(true));
			getTarifaService().update(tarifa);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Se habilito la tarifa: "
							+ tarifa.getCategoriaVehiculo().getNombre() + ", "
							+ tarifa.getTipoEstadia().getNombre() + ", $"
							+ tarifa.getImporte().toString(), "");

			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, no se pudo habilitar la tarifa: "
							+ tarifa.getCategoriaVehiculo().getNombre() + ", "
							+ tarifa.getTipoEstadia().getNombre() + ", $"
							+ tarifa.getImporte().toString(),
					"Por favor, inténtelo más tarde.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void disableTarifa(Tarifa tarifa) {
		try {
			tarifa.setVigente(new Boolean(false));
			getTarifaService().update(tarifa);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Se deshabilito la tarifa: "
							+ tarifa.getCategoriaVehiculo().getNombre() + ", "
							+ tarifa.getTipoEstadia().getNombre() + ", $"
							+ tarifa.getImporte().toString(), "");

			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, no se pudo deshabilitar la tarifa: "
							+ tarifa.getCategoriaVehiculo().getNombre() + ", "
							+ tarifa.getTipoEstadia().getNombre() + ", $"
							+ tarifa.getImporte().toString(),
					"Por favor, inténtelo más tarde.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public String addTarifas() {
		this.setPlayaByLoggedUser();
		this.deleteTarifasPlayaLogged();
		this.setVigente(new Boolean(true));
		this.setFechaAlta(new Timestamp(Calendar.getInstance()
				.getTimeInMillis()));

		if (autoPorHora != null && !autoPorHora.toString().trim().isEmpty()) {
			this.setTipoEstadia(getTipoEstadiaService()
					.findByNombreTipoEstadia("Por Hora"));
			this.setCategoriaVehiculo(getCategoriaVehiculoService()
					.findByNombreCategoriaVehiculo("Auto"));
			this.setImporte(Float.valueOf(autoPorHora));
			this.addTarifa();
		}
		if (utilitarioPorHora != null
				&& !utilitarioPorHora.toString().trim().isEmpty()) {
			this.setTipoEstadia(getTipoEstadiaService()
					.findByNombreTipoEstadia("Por Hora"));
			this.setCategoriaVehiculo(getCategoriaVehiculoService()
					.findByNombreCategoriaVehiculo("Utilitario"));
			this.setImporte(Float.valueOf(utilitarioPorHora));
			this.addTarifa();
		}
		if (pickupPorHora != null && !pickupPorHora.toString().trim().isEmpty()) {
			this.setTipoEstadia(getTipoEstadiaService()
					.findByNombreTipoEstadia("Por Hora"));
			this.setCategoriaVehiculo(getCategoriaVehiculoService()
					.findByNombreCategoriaVehiculo("PickUp / 4X4"));
			this.setImporte(Float.valueOf(pickupPorHora));
			this.addTarifa();
		}
		if (motoPorHora != null && !motoPorHora.toString().trim().isEmpty()) {
			this.setTipoEstadia(getTipoEstadiaService()
					.findByNombreTipoEstadia("Por Hora"));
			this.setCategoriaVehiculo(getCategoriaVehiculoService()
					.findByNombreCategoriaVehiculo("Moto"));
			this.setImporte(Float.valueOf(motoPorHora));
			this.addTarifa();
		}

		if (autoPorDia != null && !autoPorDia.toString().trim().isEmpty()) {
			// this.setTipoEstadia(getTipoEstadiaService()
			// .findByNombreTipoEstadia("Por Día"));
			this.setTipoEstadia(getTipoEstadiaService().findByIdTipoEstadia(4));
			this.setCategoriaVehiculo(getCategoriaVehiculoService()
					.findByNombreCategoriaVehiculo("Auto"));
			this.setImporte(Float.valueOf(autoPorDia));
			this.addTarifa();
		}
		if (utilitarioPorDia != null
				&& !utilitarioPorDia.toString().trim().isEmpty()) {
			// this.setTipoEstadia(getTipoEstadiaService()
			// .findByNombreTipoEstadia("Por Dí­a"));
			this.setTipoEstadia(getTipoEstadiaService().findByIdTipoEstadia(4));
			this.setCategoriaVehiculo(getCategoriaVehiculoService()
					.findByNombreCategoriaVehiculo("Utilitario"));
			this.setImporte(Float.valueOf(utilitarioPorDia));
			this.addTarifa();
		}
		if (pickupPorDia != null && !pickupPorDia.toString().trim().isEmpty()) {
			// this.setTipoEstadia(getTipoEstadiaService()
			// .findByNombreTipoEstadia("Por Dí­a"));
			this.setTipoEstadia(getTipoEstadiaService().findByIdTipoEstadia(4));
			this.setCategoriaVehiculo(getCategoriaVehiculoService()
					.findByNombreCategoriaVehiculo("PickUp / 4X4"));
			this.setImporte(Float.valueOf(pickupPorDia));
			this.addTarifa();
		}
		if (motoPorDia != null && !motoPorDia.toString().trim().isEmpty()) {
			// this.setTipoEstadia(getTipoEstadiaService()
			// .findByNombreTipoEstadia("Por Dí­a"));
			this.setTipoEstadia(getTipoEstadiaService().findByIdTipoEstadia(4));
			this.setCategoriaVehiculo(getCategoriaVehiculoService()
					.findByNombreCategoriaVehiculo("Moto"));
			this.setImporte(Float.valueOf(motoPorDia));
			this.addTarifa();
		}

		if (autoPorNoche != null && !autoPorNoche.toString().trim().isEmpty()) {
			this.setTipoEstadia(getTipoEstadiaService()
					.findByNombreTipoEstadia("Por Noche"));
			this.setCategoriaVehiculo(getCategoriaVehiculoService()
					.findByNombreCategoriaVehiculo("Auto"));
			this.setImporte(Float.valueOf(autoPorNoche));
			this.addTarifa();
		}
		if (utilitarioPorNoche != null
				&& !utilitarioPorNoche.toString().trim().isEmpty()) {
			this.setTipoEstadia(getTipoEstadiaService()
					.findByNombreTipoEstadia("Por Noche"));
			this.setCategoriaVehiculo(getCategoriaVehiculoService()
					.findByNombreCategoriaVehiculo("Utilitario"));
			this.setImporte(Float.valueOf(utilitarioPorNoche));
			this.addTarifa();
		}
		if (pickupPorNoche != null
				&& !pickupPorNoche.toString().trim().isEmpty()) {
			this.setTipoEstadia(getTipoEstadiaService()
					.findByNombreTipoEstadia("Por Noche"));
			this.setCategoriaVehiculo(getCategoriaVehiculoService()
					.findByNombreCategoriaVehiculo("PickUp / 4X4"));
			this.setImporte(Float.valueOf(pickupPorNoche));
			this.addTarifa();
		}
		if (motoPorNoche != null && !motoPorNoche.toString().trim().isEmpty()) {
			this.setTipoEstadia(getTipoEstadiaService()
					.findByNombreTipoEstadia("Por Noche"));
			this.setCategoriaVehiculo(getCategoriaVehiculoService()
					.findByNombreCategoriaVehiculo("Moto"));
			this.setImporte(Float.valueOf(motoPorNoche));
			this.addTarifa();
		}

		if (autoPorSemana != null && !autoPorSemana.toString().trim().isEmpty()) {
			this.setTipoEstadia(getTipoEstadiaService()
					.findByNombreTipoEstadia("Por Semana"));
			this.setCategoriaVehiculo(getCategoriaVehiculoService()
					.findByNombreCategoriaVehiculo("Auto"));
			this.setImporte(Float.valueOf(autoPorSemana));
			this.addTarifa();
		}
		if (utilitarioPorSemana != null
				&& !utilitarioPorSemana.toString().trim().isEmpty()) {
			this.setTipoEstadia(getTipoEstadiaService()
					.findByNombreTipoEstadia("Por Semana"));
			this.setCategoriaVehiculo(getCategoriaVehiculoService()
					.findByNombreCategoriaVehiculo("Utilitario"));
			this.setImporte(Float.valueOf(utilitarioPorSemana));
			this.addTarifa();
		}
		if (pickupPorSemana != null
				&& !pickupPorSemana.toString().trim().isEmpty()) {
			this.setTipoEstadia(getTipoEstadiaService()
					.findByNombreTipoEstadia("Por Semana"));
			this.setCategoriaVehiculo(getCategoriaVehiculoService()
					.findByNombreCategoriaVehiculo("PickUp / 4X4"));
			this.setImporte(Float.valueOf(pickupPorSemana));
			this.addTarifa();
		}
		if (motoPorSemana != null && !motoPorSemana.toString().trim().isEmpty()) {
			this.setTipoEstadia(getTipoEstadiaService()
					.findByNombreTipoEstadia("Por Semana"));
			this.setCategoriaVehiculo(getCategoriaVehiculoService()
					.findByNombreCategoriaVehiculo("Moto"));
			this.setImporte(Float.valueOf(motoPorSemana));
			this.addTarifa();
		}

		if (autoPorMes != null && !autoPorMes.toString().trim().isEmpty()) {
			this.setTipoEstadia(getTipoEstadiaService()
					.findByNombreTipoEstadia("Por Mes"));
			this.setCategoriaVehiculo(getCategoriaVehiculoService()
					.findByNombreCategoriaVehiculo("Auto"));
			this.setImporte(Float.valueOf(autoPorMes));
			this.addTarifa();
		}
		if (utilitarioPorMes != null
				&& !utilitarioPorMes.toString().trim().isEmpty()) {
			this.setTipoEstadia(getTipoEstadiaService()
					.findByNombreTipoEstadia("Por Mes"));
			this.setCategoriaVehiculo(getCategoriaVehiculoService()
					.findByNombreCategoriaVehiculo("Utilitario"));
			this.setImporte(Float.valueOf(utilitarioPorMes));
			this.addTarifa();
		}
		if (pickupPorMes != null && !pickupPorMes.toString().trim().isEmpty()) {
			this.setTipoEstadia(getTipoEstadiaService()
					.findByNombreTipoEstadia("Por Mes"));
			this.setCategoriaVehiculo(getCategoriaVehiculoService()
					.findByNombreCategoriaVehiculo("PickUp / 4X4"));
			this.setImporte(Float.valueOf(pickupPorMes));
			this.addTarifa();
		}
		if (motoPorMes != null && !motoPorMes.toString().trim().isEmpty()) {
			this.setTipoEstadia(getTipoEstadiaService()
					.findByNombreTipoEstadia("Por Mes"));
			this.setCategoriaVehiculo(getCategoriaVehiculoService()
					.findByNombreCategoriaVehiculo("Moto"));
			this.setImporte(Float.valueOf(motoPorMes));
			this.addTarifa();
		}
		return ADD_TARIFAS_SUCCESS;
	}

	private boolean addTarifa() {
		try {
			Tarifa tarifa = new Tarifa();

			tarifa.setImporte(getImporte());
			tarifa.setVigente(getVigente());

			tarifa.setPlaya(getPlaya());
			tarifa.setTipoEstadia(getTipoEstadia());
			tarifa.setCategoriaVehiculo(getCategoriaVehiculo());
			getTarifaService().save(tarifa);
			return true;
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, no se pudo agregar la tarifa "
					, "Por favor, inténtelo más tarde.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
			return false;
		}
	}

	public void setPlayaByLoggedUser() {
		String nombreUsuario;
		Usuario usuario;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		nombreUsuario = facesContext.getExternalContext().getRemoteUser();
		usuario = getUsuarioService().findByNombreUsuario(nombreUsuario);
		setPlaya(usuario.getPlaya());
	}

	public void deleteTarifa(Tarifa tarifa) {
		// getTarifaService().delete(tarifa);
	}

	public String updateTarifaAdmin(Tarifa tarifa) {
		tarifaSelected = tarifa;
		return "tarifaeditadmin";
	}

	public String updateTarifaAdmin() {
		try {
			getTarifaService().update(tarifaSelected);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"La tarifa se modificó correctamente", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return LISTA_TARIFAS;
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, la tarifa no se pudo modificar",
					"Por favor, intentelo mas tarde.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}
		return ERROR;
	}

	public void updateTarifa(Tarifa tarifa) {
		getTarifaService().update(tarifa);
	}

	public String tomarSeleccionPlaya(Playa playa) {
		playaSelected = playa;
		this.playa = playa;
		if ("Secured".equals(estaLogueado())) {
			return "tarifaplayalist";
		} else {
			return "accessdenied?faces-redirect=true";
		}

	}

	public String estaLogueado() {

		Authentication sc = SecurityContextHolder.getContext()
				.getAuthentication();

		if (!"[ROLE_ADMIN]".equals(sc.getAuthorities().toString())
				&& !"[ROLE_PLAYA_GERENTE]".equals(sc.getAuthorities()
						.toString())
				&& !"[ROLE_CLIENT]".equals(sc.getAuthorities().toString())) {
			return "UnSecured";
		}
		return "Secured";
	}

	public void reset() {
		this.setFechaBaja(null);
		this.setImporte(0.f);
		this.setVigente(false);

	}

	public void findPlayaById() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (!facesContext.isPostback()) {
			String parametroID = facesContext.getExternalContext()
					.getRequestParameterMap().get("id");
			if (!parametroID.equals("") || parametroID != null) {
				int idPlayaSelected = Integer.parseInt(parametroID);
				playaSelected = getPlayaService().findById(idPlayaSelected);
			}
		}
	}

	public ITarifaService getTarifaService() {
		return tarifaService;
	}

	public void setTarifaService(ITarifaService tarifaService) {
		this.tarifaService = tarifaService;
	}

	public ITipoEstadiaService getTipoEstadiaService() {
		return tipoEstadiaService;
	}

	public void setTipoEstadiaService(ITipoEstadiaService tipoEstadiaService) {
		this.tipoEstadiaService = tipoEstadiaService;
	}

	public ICategoriaVehiculoService getCategoriaVehiculoService() {
		return categoriaVehiculoService;
	}

	public void setCategoriaVehiculoService(
			ICategoriaVehiculoService categoriaVehiculoService) {
		this.categoriaVehiculoService = categoriaVehiculoService;
	}

	public IPlayaService getPlayaService() {
		return playaService;
	}

	public void setPlayaService(IPlayaService playaService) {
		this.playaService = playaService;
	}

	public List<Tarifa> getTarifaList() {
		tarifaList = new ArrayList<Tarifa>();
		tarifaList = getTarifaService().findAll();
		return tarifaList;
	}

	public void setTarifaList(List<Tarifa> tarifaList) {
		this.tarifaList = tarifaList;
	}

	private void deleteTarifasPlayaLogged() {
		getTarifaService().deleteTarifasPlaya(playaLogged);
	}

	private void cargarTarifasPlayaLogged() {
		if (this.tarifaPlayaLogged == null)
			return;

		int idTipoEstadiaHora = 1; // getTipoEstadiaService().findByNombreTipoEstadia("Por Hora").getId().intValue();
		int idTipoEstadiaDia = 4; // getTipoEstadiaService().findByNombreTipoEstadia("Por Día").getId().intValue();
		int idTipoEstadiaNoche = 3; // getTipoEstadiaService().findByNombreTipoEstadia("Por Noche").getId().intValue();
		int idTipoEstadiaSemana = 5; // getTipoEstadiaService().findByNombreTipoEstadia("Por Semana").getId().intValue();
		int idTipoEstadiaMes = 2; // getTipoEstadiaService().findByNombreTipoEstadia("Por Mes").getId().intValue();

		int idCategoriaVehiculoAuto = getCategoriaVehiculoService()
				.findByNombreCategoriaVehiculo("Auto").getId().intValue();
		int idCategoriaVehiculoUtilitario = getCategoriaVehiculoService()
				.findByNombreCategoriaVehiculo("Utilitario").getId().intValue();
		int idCategoriaVehiculoPickUp = getCategoriaVehiculoService()
				.findByNombreCategoriaVehiculo("PickUp / 4X4").getId()
				.intValue();
		int idCategoriaVehiculoMoto = getCategoriaVehiculoService()
				.findByNombreCategoriaVehiculo("Moto").getId().intValue();

		int idTipoEstadiaActual;
		int idCategoriaVehiculoActual;

		for (Tarifa tarifa : this.tarifaPlayaLogged) {
			idTipoEstadiaActual = tarifa.getTipoEstadia().getId().intValue();
			idCategoriaVehiculoActual = tarifa.getCategoriaVehiculo().getId()
					.intValue();

			if (idCategoriaVehiculoActual == idCategoriaVehiculoAuto) {
				if (idTipoEstadiaActual == idTipoEstadiaHora) {
					this.autoPorHora = tarifa.getImporte().toString();
				} else if (idTipoEstadiaActual == idTipoEstadiaDia) {
					this.autoPorDia = tarifa.getImporte().toString();
				} else if (idTipoEstadiaActual == idTipoEstadiaNoche) {
					this.autoPorNoche = tarifa.getImporte().toString();
				} else if (idTipoEstadiaActual == idTipoEstadiaSemana) {
					this.autoPorSemana = tarifa.getImporte().toString();
				} else if (idTipoEstadiaActual == idTipoEstadiaMes) {
					this.autoPorMes = tarifa.getImporte().toString();
				}
			} else if (idCategoriaVehiculoActual == idCategoriaVehiculoMoto) {
				if (idTipoEstadiaActual == idTipoEstadiaHora) {
					this.motoPorHora = tarifa.getImporte().toString();
				} else if (idTipoEstadiaActual == idTipoEstadiaDia) {
					this.motoPorDia = tarifa.getImporte().toString();
				} else if (idTipoEstadiaActual == idTipoEstadiaNoche) {
					this.motoPorNoche = tarifa.getImporte().toString();
				} else if (idTipoEstadiaActual == idTipoEstadiaSemana) {
					this.motoPorSemana = tarifa.getImporte().toString();
				} else if (idTipoEstadiaActual == idTipoEstadiaMes) {
					this.motoPorMes = tarifa.getImporte().toString();
				}
			} else if (idCategoriaVehiculoActual == idCategoriaVehiculoUtilitario) {
				if (idTipoEstadiaActual == idTipoEstadiaHora) {
					this.utilitarioPorHora = tarifa.getImporte().toString();
				} else if (idTipoEstadiaActual == idTipoEstadiaDia) {
					this.utilitarioPorDia = tarifa.getImporte().toString();
				} else if (idTipoEstadiaActual == idTipoEstadiaNoche) {
					this.utilitarioPorNoche = tarifa.getImporte().toString();
				} else if (idTipoEstadiaActual == idTipoEstadiaSemana) {
					this.utilitarioPorSemana = tarifa.getImporte().toString();
				} else if (idTipoEstadiaActual == idTipoEstadiaMes) {
					this.utilitarioPorMes = tarifa.getImporte().toString();
				}
			} else if (idCategoriaVehiculoActual == idCategoriaVehiculoPickUp) {
				if (idTipoEstadiaActual == idTipoEstadiaHora) {
					this.pickupPorHora = tarifa.getImporte().toString();
				} else if (idTipoEstadiaActual == idTipoEstadiaDia) {
					this.pickupPorDia = tarifa.getImporte().toString();
				} else if (idTipoEstadiaActual == idTipoEstadiaNoche) {
					this.pickupPorNoche = tarifa.getImporte().toString();
				} else if (idTipoEstadiaActual == idTipoEstadiaSemana) {
					this.pickupPorSemana = tarifa.getImporte().toString();
				} else if (idTipoEstadiaActual == idTipoEstadiaMes) {
					this.pickupPorMes = tarifa.getImporte().toString();
				}
			}
		}
	}

	public List<Tarifa> getTarifaPlayaLogged() {
		tarifaPlayaLogged = new ArrayList<Tarifa>();
		tarifaPlayaLogged = getTarifaService().findByPlaya(playaLogged);
		this.cargarTarifasPlayaLogged();
		return tarifaPlayaLogged;
	}

	public List<Tarifa> getTarifaPlayaList() {
		tarifaPlayaList = new ArrayList<Tarifa>();
		tarifaPlayaList = getTarifaService().findByPlaya(playaSelected);
		return tarifaPlayaList;
	}

	public void setTarifaPlayaList(List<Tarifa> tarifaPlayaList) {
		this.tarifaPlayaList = tarifaPlayaList;
	}

	public Float getImporte() {
		return importe;
	}

	public void setImporte(Float importe) {
		this.importe = importe;
	}

	public Float getImporteAutoPorHora() {
		return importe;
	}

	public void setImporteAutoPorHora(Float importe) {
		this.importe = importe;
	}

	public Boolean getVigente() {
		return vigente;
	}

	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Playa getPlaya() {
		return playa;
	}

	public void setPlaya(Playa playa) {
		this.playa = playa;
	}

	public TipoEstadia getTipoEstadia() {
		return tipoEstadia;
	}

	public void setTipoEstadia(TipoEstadia tipoEstadia) {
		this.tipoEstadia = tipoEstadia;
	}

	public CategoriaVehiculo getCategoriaVehiculo() {
		return categoriaVehiculo;
	}

	public void setCategoriaVehiculo(CategoriaVehiculo categoriaVehiculo) {
		this.categoriaVehiculo = categoriaVehiculo;
	}

	public Integer getId() {
		return id;
	}

	public Tarifa getTarifaSelected() {
		return tarifaSelected;
	}

	public Playa getPlayaSelected() {
		return playaSelected;
	}

	public IUsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public List<Tarifa> getPromocionesPlayaList() {
		promocionesPlayaList = new ArrayList<Tarifa>();
		return promocionesPlayaList;
	}

	public String getAutoPorHora() {
		return autoPorHora;
	}

	public void setAutoPorHora(String autoPorHora) {
		this.autoPorHora = autoPorHora;
	}

	public String getUtilitarioPorHora() {
		return utilitarioPorHora;
	}

	public void setUtilitarioPorHora(String utilitarioPorHora) {
		this.utilitarioPorHora = utilitarioPorHora;
	}

	public String getPickupPorHora() {
		return pickupPorHora;
	}

	public void setPickupPorHora(String pickupPorHora) {
		this.pickupPorHora = pickupPorHora;
	}

	public String getMotoPorHora() {
		return motoPorHora;
	}

	public void setMotoPorHora(String motoPorHora) {
		this.motoPorHora = motoPorHora;
	}

	public String getAutoPorDia() {
		return autoPorDia;
	}

	public void setAutoPorDia(String autoPorDia) {
		this.autoPorDia = autoPorDia;
	}

	public String getUtilitarioPorDia() {
		return utilitarioPorDia;
	}

	public void setUtilitarioPorDia(String utilitarioPorDia) {
		this.utilitarioPorDia = utilitarioPorDia;
	}

	public String getPickupPorDia() {
		return pickupPorDia;
	}

	public void setPickupPorDia(String pickupPorDia) {
		this.pickupPorDia = pickupPorDia;
	}

	public String getMotoPorDia() {
		return motoPorDia;
	}

	public void setMotoPorDia(String motoPorDia) {
		this.motoPorDia = motoPorDia;
	}

	public String getAutoPorSemana() {
		return autoPorSemana;
	}

	public void setAutoPorSemana(String autoPorSemana) {
		this.autoPorSemana = autoPorSemana;
	}

	public String getUtilitarioPorSemana() {
		return utilitarioPorSemana;
	}

	public void setUtilitarioPorSemana(String utilitarioPorSemana) {
		this.utilitarioPorSemana = utilitarioPorSemana;
	}

	public String getPickupPorSemana() {
		return pickupPorSemana;
	}

	public void setPickupPorSemana(String pickupPorSemana) {
		this.pickupPorSemana = pickupPorSemana;
	}

	public String getMotoPorSemana() {
		return motoPorSemana;
	}

	public void setMotoPorSemana(String motoPorSemana) {
		this.motoPorSemana = motoPorSemana;
	}

	public String getAutoPorNoche() {
		return autoPorNoche;
	}

	public void setAutoPorNoche(String autoPorNoche) {
		this.autoPorNoche = autoPorNoche;
	}

	public String getUtilitarioPorNoche() {
		return utilitarioPorNoche;
	}

	public void setUtilitarioPorNoche(String utilitarioPorNoche) {
		this.utilitarioPorNoche = utilitarioPorNoche;
	}

	public String getPickupPorNoche() {
		return pickupPorNoche;
	}

	public void setPickupPorNoche(String pickupPorNoche) {
		this.pickupPorNoche = pickupPorNoche;
	}

	public String getMotoPorNoche() {
		return motoPorNoche;
	}

	public void setMotoPorNoche(String motoPorNoche) {
		this.motoPorNoche = motoPorNoche;
	}

	public String getAutoPorMes() {
		return autoPorMes;
	}

	public void setAutoPorMes(String autoPorMes) {
		this.autoPorMes = autoPorMes;
	}

	public String getUtilitarioPorMes() {
		return utilitarioPorMes;
	}

	public void setUtilitarioPorMes(String utilitarioPorMes) {
		this.utilitarioPorMes = utilitarioPorMes;
	}

	public String getPickupPorMes() {
		return pickupPorMes;
	}

	public void setPickupPorMes(String pickupPorMes) {
		this.pickupPorMes = pickupPorMes;
	}

	public String getMotoPorMes() {
		return motoPorMes;
	}

	public void setMotoPorMes(String motoPorMes) {
		this.motoPorMes = motoPorMes;
	}

	public List<Tarifa> getTarifaListSelected() {
		if (playaSelected != null) {
			tarifaListSelected = getTarifaService().findTarifaVigenteByPlaya(
					playaSelected);
		}
		return tarifaListSelected;
	}

	public void setTarifaListSelected(List<Tarifa> tarifaListSelected) {
		this.tarifaListSelected = tarifaListSelected;
	}

	public void setTarifaSelected(Tarifa tarifaSelected) {
		TarifaManagedBean.tarifaSelected = tarifaSelected;
	}

	public void setPlayaSelected(Playa playaSelected) {
		TarifaManagedBean.playaSelected = playaSelected;
	}

	public Playa getPlayaLogged() {
		return playaLogged;
	}

	public void setPlayaLogged(Playa playaLogged) {
		this.playaLogged = playaLogged;
	}

	private List<Tarifa> tarifaListEmpleados;
	private List<Tarifa> filteredTarifas;

	@SuppressWarnings("unused")
	private SelectItem[] categoriaOptions;

	@SuppressWarnings("unused")
	private SelectItem[] tipoEstadiaOptions;

	public List<Tarifa> getTarifaListEmpleados() {
		return tarifaListEmpleados;
	}

	public void setTarifaListEmpleados(List<Tarifa> tarifaListEmpleados) {
		this.tarifaListEmpleados = tarifaListEmpleados;
	}

	public List<Tarifa> getFilteredTarifas() {
		return filteredTarifas;
	}

	public void setFilteredTarifas(List<Tarifa> filteredTarifas) {
		this.filteredTarifas = filteredTarifas;
	}

	public SelectItem[] getCategoriaOptions() {
		List<CategoriaVehiculo> categorias = new ArrayList<CategoriaVehiculo>();
		categorias = getCategoriaVehiculoService().findAll();
		this.categoriaOptions = new SelectItem[categorias.size() + 1];
		SelectItem[] options = new SelectItem[categorias.size() + 1];
		options[0] = new SelectItem("", "Todos");

		for (int i = 0; i < categorias.size(); i++) {
			options[i + 1] = new SelectItem(categorias.get(i), categorias
					.get(i).getNombre());
		}
		return options;
	}

	public void setCategoriaOptions(SelectItem[] categoriaOptions) {
		this.categoriaOptions = categoriaOptions;
	}

	public SelectItem[] getTipoEstadiaOptions() {
		List<TipoEstadia> tipos = new ArrayList<TipoEstadia>();
		tipos = getTipoEstadiaService().findAll();
		this.categoriaOptions = new SelectItem[tipos.size() + 1];
		SelectItem[] options = new SelectItem[tipos.size() + 1];
		options[0] = new SelectItem("", "Todos");

		for (int i = 0; i < tipos.size(); i++) {
			options[i + 1] = new SelectItem(tipos.get(i), tipos.get(i)
					.getNombre());
		}
		return options;
	}

	public void setTipoEstadiaOptions(SelectItem[] tipoEstadiaOptions) {
		this.tipoEstadiaOptions = tipoEstadiaOptions;
	}

}