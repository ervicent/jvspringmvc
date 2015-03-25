package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.DenunciaVehiculo;
import tesis.playon.web.model.EstadoDenuncia;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.model.Vehiculo;
import tesis.playon.web.service.IDenunciaVehiculoService;
import tesis.playon.web.service.IEstadoDenunciaService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.service.IVehiculoService;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "denunciaVehiculoMB")
@ViewScoped
public class DenunciaVehiculoManagedBean implements Serializable {

    private static final long serialVersionUID = 6773490680356877684L;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    @ManagedProperty(value = "#{DenunciaVehiculoService}")
    IDenunciaVehiculoService denunciaService;

    @ManagedProperty(value = "#{VehiculoService}")
    IVehiculoService vehiculoService;

    @ManagedProperty(value = "#{EstadoDenunciaService}")
    IEstadoDenunciaService estadoDenunciaService;

    private DenunciaVehiculo denuncia;
    private String asunto;
    private static Date fecha;
    private Vehiculo vehiculo;
    private String patente;
    private static Playa playa;
    private EstadoDenuncia estado;

    private static Usuario user;

    @PostConstruct
    public void init() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	user = getUsuarioService().findByNombreUsuario(userName);
	playa = user.getPlaya();
    }

    public String denunciar() {
	try {
	    vehiculo = vehiculoService.findByPatenteVehiculo(getPatente());

	    fecha = new Timestamp(Calendar.getInstance().getTimeInMillis());
	    estado = getEstadoDenunciaService().findByNombreEstadoDenuncia("Pendiente");
	    denuncia = new DenunciaVehiculo(getAsunto(), fecha, vehiculo, playa, estado);
	    getDenunciaService().save(denuncia);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO
		    , "Su denuncia se registró con éxito y se encuentra pendiente de auditoría."
		    , "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    
	    return "denunciarvehiculoend";
	} catch (DataAccessException e) {

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al enviar su denuncia "
		    + "Vuelva  a intentarlo ", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	    return "ERROR";

	}
    }

    public IPlayaService getPlayaService() {
	return playaService;
    }

    public void setPlayaService(IPlayaService playaService) {
	this.playaService = playaService;
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public IDenunciaVehiculoService getDenunciaService() {
	return denunciaService;
    }

    public void setDenunciaService(IDenunciaVehiculoService denunciaService) {
	this.denunciaService = denunciaService;
    }

    public DenunciaVehiculo getDenuncia() {
	return denuncia;
    }

    public void setDenuncia(DenunciaVehiculo denuncia) {
	this.denuncia = denuncia;
    }

    public String getAsunto() {
	return asunto;
    }

    public void setAsunto(String asunto) {
	this.asunto = asunto;
    }

    public Date getFecha() {
	return fecha;
    }

    public void setFecha(Date fecha) {
	DenunciaVehiculoManagedBean.fecha = fecha;
    }

    public Vehiculo getVehiculo() {
	return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
	this.vehiculo = vehiculo;
    }

    public IVehiculoService getVehiculoService() {
	return vehiculoService;
    }

    public void setVehiculoService(IVehiculoService vehiculoService) {
	this.vehiculoService = vehiculoService;
    }

    public String getPatente() {
	return patente;
    }

    public void setPatente(String patente) {
	this.patente = patente;
    }

    public static Usuario getUser() {
	return user;
    }

    public static void setUser(Usuario user) {
	DenunciaVehiculoManagedBean.user = user;
    }

    public static Playa getPlaya() {
	return playa;
    }

    public static void setPlaya(Playa playa) {
	DenunciaVehiculoManagedBean.playa = playa;
    }

    public EstadoDenuncia getEstado() {
	return estado;
    }

    public void setEstado(EstadoDenuncia estado) {
	this.estado = estado;
    }

    public IEstadoDenunciaService getEstadoDenunciaService() {
	return estadoDenunciaService;
    }

    public void setEstadoDenunciaService(IEstadoDenunciaService estadoDenunciaService) {
	this.estadoDenunciaService = estadoDenunciaService;
    }

}
