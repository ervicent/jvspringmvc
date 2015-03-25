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
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RateEvent;

import tesis.playon.web.model.Calificacion;
import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.ICalificacionService;
import tesis.playon.web.service.IClienteService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IUsuarioService;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "calificacionMB")
@RequestScoped
public class CalificacionManagedBean implements Serializable {

    private static final long serialVersionUID = 5047262325426946652L;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    @ManagedProperty(value = "#{CalificacionService}")
    ICalificacionService calificacionService;

    @ManagedProperty(value = "#{ClienteService}")
    IClienteService clienteService;

    private Integer calificacionPlaya;

    private Integer calificacion;

    private static Playa playaSelected;

    private List<Calificacion> calificacionListPerfil;

    private List<Calificacion> calificacionList;

    @PostConstruct
    private void init() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario user = getUsuarioService().findByNombreUsuario(userName);
	if (user != null && user.getPlaya() != null) {
	    calificacionList = new ArrayList<Calificacion>();
	    calificacionList = getCalificacionService().findByPlaya(user.getPlaya());
	    this.calificacion = 0;
	    if (calificacionList != null) {
		for (Calificacion calificacion : calificacionList) {
		    this.calificacion += calificacion.getCalificacion();
		}
		calificacion = Math.round(calificacion / calificacionList.size());
	    }
	}
    }

    public void onrate(RateEvent rateEvent) {
	Cliente cliente = null;
	try {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    String userName = facesContext.getExternalContext().getRemoteUser();
	    Usuario user = getUsuarioService().findByNombreUsuario(userName);
	    if (user != null) {
		cliente = getClienteService().findByUsuario(user);
		if (cliente != null) {
		    boolean califico = getCalificacionService().isRate(playaSelected, cliente);
		    if (!califico) {
			Calificacion calificacion = new Calificacion(((Integer) rateEvent.getRating()).intValue(),
				playaSelected, cliente);
			getCalificacionService().save(calificacion);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Se registró la califación exitosamente, ", "puntuación: "
					+ ((Integer) rateEvent.getRating()).intValue());
			FacesContext.getCurrentInstance().addMessage(null, message);

		    } else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
				"Usted ya ha calificado esta playa, ",
				"Sólo se permite calificar una vez la misma playa");
			FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		} else {
		    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
			    "¡Sólo pueden calificar clientes de las playas!", "");
		    FacesContext.getCurrentInstance().addMessage(null, message);
		}
	    } else {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
			"No se pudo registrar su calificación, ", "¡Debe iniciar sesión para poder calificar la playa!");
		FacesContext.getCurrentInstance().addMessage(null, message);
	    }

	} catch (Exception ex) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "¡No se pudo registrar la calificación! ", "Disculpe las molestias ocacionadas");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
    }

    public void findPlayaById() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	if (!facesContext.isPostback()) {
	    String parametroID = facesContext.getExternalContext().getRequestParameterMap().get("id");
	    if (!parametroID.equals("") || parametroID != null) {
		int idPlayaSelected = Integer.parseInt(parametroID);
		playaSelected = getPlayaService().findById(idPlayaSelected);
	    }
	}
    }

    public IPlayaService getPlayaService() {
	return playaService;
    }

    public void setPlayaService(IPlayaService playaService) {
	this.playaService = playaService;
    }

    public ICalificacionService getCalificacionService() {
	return calificacionService;
    }

    public void setCalificacionService(ICalificacionService calificacionService) {
	this.calificacionService = calificacionService;
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public IClienteService getClienteService() {
	return clienteService;
    }

    public void setClienteService(IClienteService clienteService) {
	this.clienteService = clienteService;
    }

    public Integer getCalificacionPlaya() {
	if (playaSelected != null) {
	    calificacionListPerfil = new ArrayList<Calificacion>();
	    calificacionListPerfil = getCalificacionService().findByPlaya(playaSelected);
	    calificacionPlaya = 0;
	    if (calificacionListPerfil != null) {
		for (Calificacion calificacion : calificacionListPerfil) {
		    calificacionPlaya += calificacion.getCalificacion();
		}
		calificacionPlaya = Math.round(calificacionPlaya / calificacionListPerfil.size());
	    }
	}
	return calificacionPlaya;
    }

    public void setCalificacionPlaya(Integer calificacionPlaya) {
	this.calificacionPlaya = calificacionPlaya;
    }

    public Playa getPlayaSelected() {
	return playaSelected;
    }

    public void setPlayaSelected(Playa playaSelected) {
	CalificacionManagedBean.playaSelected = playaSelected;
    }

    public List<Calificacion> getCalificacionListPerfil() {
	return calificacionListPerfil;
    }

    public void setCalificacionListPerfil(List<Calificacion> calificacionList) {
	this.calificacionListPerfil = calificacionList;
    }

    public Integer getCalificacion() {
	return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
	this.calificacion = calificacion;
    }

    public List<Calificacion> getCalificacionList() {
	return calificacionList;
    }

    public void setCalificacionList(List<Calificacion> calificacionList) {
	this.calificacionList = calificacionList;
    }

}