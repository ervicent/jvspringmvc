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

import tesis.playon.web.model.EstadoPublicidad;
import tesis.playon.web.model.FotoPublicidad;
import tesis.playon.web.model.Publicidad;
import tesis.playon.web.service.IEstadoPublicidadService;
import tesis.playon.web.service.IFotoPublicidadService;
import tesis.playon.web.service.IPublicidadService;
import tesis.playon.web.util.WriteImage;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "auditoriaPublicidadesMB")
@ViewScoped
public class AuditoriaPublicidadesManagedBean implements Serializable {

	private static final long serialVersionUID = 1495139340595693159L;

	@ManagedProperty(value = "#{EstadoPublicidadService}")
	IEstadoPublicidadService estadoPublicidadService;

	@ManagedProperty(value = "#{PublicidadService}")
	IPublicidadService publicidadService;

	@ManagedProperty(value = "#{FotoPublicidadService}")
	IFotoPublicidadService fotoPublicidadService;

	EstadoPublicidad estadoPendiente, estadoAprobado, estadoRechazado;

	private static List<Publicidad> publicidadesPendientesList;

	private static List<Publicidad> publicidadesAprobadasList;

	private static List<Publicidad> publicidadRechazadasList;

	public static Publicidad publicidadSelected;

	@PostConstruct
	private void init() {
		estadoPendiente = getEstadoPublicidadService()
				.findByNombreEstadoPublicidad("Pendiente");
		publicidadesPendientesList = getPublicidadService().findByEstado(
				estadoPendiente);
		if (!FacesContext.getCurrentInstance().isPostback()) {
			if (publicidadesPendientesList != null) {
				List<FotoPublicidad> fotosList = new ArrayList<FotoPublicidad>();
				for (Publicidad publicidad : publicidadesPendientesList) {
					fotosList.add(publicidad.getFotoPublicidad());
				}
				WriteImage.writeFotosPublicidad(fotosList);
			}
		}

		estadoAprobado = getEstadoPublicidadService()
				.findByNombreEstadoPublicidad("Aprobada");
		publicidadesAprobadasList = getPublicidadService().findByEstado(
				estadoAprobado);

		estadoRechazado = getEstadoPublicidadService()
				.findByNombreEstadoPublicidad("Rechazada");
		publicidadRechazadasList = getPublicidadService().findByEstado(
				estadoRechazado);
	}

	public void approvePublicidad() {
		try {
			if (publicidadSelected != null) {
				publicidadSelected.setEstado(estadoAprobado);
				getPublicidadService().update(publicidadSelected);

				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						"Se aprobó la publicidad de la empresa: "
								+ publicidadSelected.getNombreEmpresa(), "");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}

			estadoPendiente = getEstadoPublicidadService()
					.findByNombreEstadoPublicidad("Pendiente");
			publicidadesPendientesList = getPublicidadService().findByEstado(
					estadoPendiente);

			estadoAprobado = getEstadoPublicidadService()
					.findByNombreEstadoPublicidad("Aprobada");
			publicidadesAprobadasList = getPublicidadService().findByEstado(
					estadoAprobado);

			estadoRechazado = getEstadoPublicidadService()
					.findByNombreEstadoPublicidad("Rechazada");
			publicidadRechazadasList = getPublicidadService().findByEstado(
					estadoRechazado);

		} catch (Exception e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, no se pudo aprobar la publicidad de la empresa: "
							+ publicidadSelected.getNombreEmpresa(),
					"Por favor, inténtelo más tarde.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void rejectPublicidad() {
		try {
			if (publicidadSelected != null) {
				publicidadSelected.setEstado(estadoRechazado);
				getPublicidadService().update(publicidadSelected);

				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						"Se rechazó la publicidad de la empresa: "
								+ publicidadSelected.getNombreEmpresa(), "");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}

			// estadoPendiente =
			// getEstadoPublicidadService().findByNombreEstadoPublicidad("Pendiente");
			publicidadesPendientesList = getPublicidadService().findByEstado(
					estadoPendiente);

			// estadoAprobado =
			// getEstadoPublicidadService().findByNombreEstadoPublicidad("Aprobada");
			publicidadesAprobadasList = getPublicidadService().findByEstado(
					estadoAprobado);

			// estadoRechazado =
			// getEstadoPublicidadService().findByNombreEstadoPublicidad("Rechazada");
			publicidadRechazadasList = getPublicidadService().findByEstado(
					estadoRechazado);
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, no se pudo rechazar la publicidad de la empresa: "
							+ publicidadSelected.getNombreEmpresa(),
					"Por favor, inténtelo más tarde.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	/**
	 * @return the estadoPublicidadService
	 */
	public IEstadoPublicidadService getEstadoPublicidadService() {
		return estadoPublicidadService;
	}

	/**
	 * @param estadoPublicidadService
	 *            the estadoPublicidadService to set
	 */
	public void setEstadoPublicidadService(
			IEstadoPublicidadService estadoPublicidadService) {
		this.estadoPublicidadService = estadoPublicidadService;
	}

	/**
	 * @return the publicidadService
	 */
	public IPublicidadService getPublicidadService() {
		return publicidadService;
	}

	/**
	 * @param publicidadService
	 *            the publicidadService to set
	 */
	public void setPublicidadService(IPublicidadService publicidadService) {
		this.publicidadService = publicidadService;
	}

	/**
	 * @return the fotoPublicidadService
	 */
	public IFotoPublicidadService getFotoPublicidadService() {
		return fotoPublicidadService;
	}

	/**
	 * @param fotoPublicidadService
	 *            the fotoPublicidadService to set
	 */
	public void setFotoPublicidadService(
			IFotoPublicidadService fotoPublicidadService) {
		this.fotoPublicidadService = fotoPublicidadService;
	}

	/**
	 * @return the publicidadesPendientesList
	 */
	public List<Publicidad> getPublicidadesPendientesList() {
		return publicidadesPendientesList;
	}

	/**
	 * @param publicidadesPendientesList
	 *            the publicidadesPendientesList to set
	 */
	public void setPublicidadesPendientesList(
			List<Publicidad> publicidadesPendientesList) {
		AuditoriaPublicidadesManagedBean.publicidadesPendientesList = publicidadesPendientesList;
	}

	/**
	 * @return the publicidadesAprobadasList
	 */
	public List<Publicidad> getPublicidadesAprobadasList() {
		return publicidadesAprobadasList;
	}

	/**
	 * @param publicidadesAprobadasList
	 *            the publicidadesAprobadasList to set
	 */
	public void setPublicidadesAprobadasList(
			List<Publicidad> publicidadesAprobadasList) {
		AuditoriaPublicidadesManagedBean.publicidadesAprobadasList = publicidadesAprobadasList;
	}

	/**
	 * @return the publicidadRechazadasList
	 */
	public List<Publicidad> getPublicidadRechazadasList() {
		return publicidadRechazadasList;
	}

	/**
	 * @param publicidadRechazadasList
	 *            the publicidadRechazadasList to set
	 */
	public void setPublicidadRechazadasList(
			List<Publicidad> publicidadRechazadasList) {
		AuditoriaPublicidadesManagedBean.publicidadRechazadasList = publicidadRechazadasList;
	}

	/**
	 * @return the publicidadSelected
	 */
	public Publicidad getPublicidadSelected() {
		return publicidadSelected;
	}

	/**
	 * @param publicidadSelected
	 *            the publicidadSelected to set
	 */
	public void setPublicidadSelected(Publicidad publicidadSelected) {
		AuditoriaPublicidadesManagedBean.publicidadSelected = publicidadSelected;
	}

}
