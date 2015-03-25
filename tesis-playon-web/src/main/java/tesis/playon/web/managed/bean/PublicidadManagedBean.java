/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.primefaces.event.DateSelectEvent;
import org.primefaces.model.UploadedFile;

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
@ManagedBean(name = "publicidadMB")
@ViewScoped
public class PublicidadManagedBean implements Serializable {

    private static final long serialVersionUID = 2077607983768560556L;

    private final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;

    @ManagedProperty(value = "#{EstadoPublicidadService}")
    IEstadoPublicidadService estadoPublicidadService;

    @ManagedProperty(value = "#{PublicidadService}")
    IPublicidadService publicidadService;

    @ManagedProperty(value = "#{FotoPublicidadService}")
    IFotoPublicidadService fotoPublicidadService;

    private String nombreEmpresa;

    private String nombreResponsable;

    private String apellidoResponsable;

    private String emailResponsable;

    private String telefonoResponsable;

    private Date fechaDesde;

    private Date fechaHasta;

    private FotoPublicidad fotoPublicidad;

    private Float precio;

    private Date today;

    private Float costoTotal;

    private String url;

    private UploadedFile foto;
    
    private static List<Publicidad> publicidadList;

    private boolean upload;
    
    private static Publicidad nuevaPublicidad;

    @PostConstruct
    private void init() {
	precio = 20.0f;
	today = new Date();

	publicidadList = getPublicidadService().findAllByEstadoVigente();

	upload = false;
    }

    public void addSolicitudPublicidad() {
	try {
	    fotoPublicidad.setUrl(url);
	    Publicidad publicidad = new Publicidad(nombreEmpresa, nombreResponsable, apellidoResponsable, fechaDesde,
		    fechaHasta);
	    publicidad.setPrecio(precio);
	    publicidad.setTelefonoResponsable(telefonoResponsable);
	    publicidad.setEmailRespondable(emailResponsable);
	    EstadoPublicidad estado = getEstadoPublicidadService().findByNombreEstadoPublicidad("Pendiente");
	    publicidad.setEstado(estado);

	    getFotoPublicidadService().save(fotoPublicidad);

	    publicidad.setFotoPublicidad(fotoPublicidad);
	    nuevaPublicidad = publicidad;
	    //getPublicidadService().save(publicidad);

	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "No se ha podido completar la solicitud. Intente más tarde. Disculpe las molestias ocacionadas.",
		    "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
    }
    
    public void addSolicitudPublicidadExitosa() {
	getPublicidadService().save(nuevaPublicidad);
	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "Se registró la publicidad exitosamente.", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void validateDateInicial(FacesContext context, UIComponent component, Object value) {
	Date dateInicial = (Date) value;
	if (dateInicial != null) {
	    this.setFechaDesde(dateInicial);
	}
    }

    public void validateDate(FacesContext context, UIComponent component, Object value) {
	Date dateFin = (Date) value;
	if (getFechaDesde() != null) {
	    if (dateFin.before(getFechaDesde())) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
			"La fecha de finalización debe ser mayor a la fecha de inicio.", "");
		throw new ValidatorException(message);
	    }
	}
    }

    public void calcularCostoTotal(DateSelectEvent event) {
	if (fechaDesde != null && fechaHasta != null) {
	    costoTotal = ((fechaHasta.getTime() - fechaDesde.getTime()) / MILLSECS_PER_DAY) * precio;
	}
    }

    public void upload() {
	if (foto != null) {
	    
	    fotoPublicidad = new FotoPublicidad(foto.getFileName(), foto.getContents(), url);
	    upload = true;
	    WriteImage.writeFotoTemporal(fotoPublicidad);
	    FacesMessage msg = new FacesMessage("La imagen: " + foto.getFileName() + " se guardó correctamente.", "");
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	    addSolicitudPublicidad();
	}
    }

    public void addHttp() {
	if (this.url.indexOf("http://") == -1)
	    this.url = "http://" + this.url;
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
    public void setEstadoPublicidadService(IEstadoPublicidadService estadoPublicidadService) {
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
    public void setFotoPublicidadService(IFotoPublicidadService fotoPublicidadService) {
	this.fotoPublicidadService = fotoPublicidadService;
    }

    /**
     * @return the nombreEmpresa
     */
    public String getNombreEmpresa() {
	return nombreEmpresa;
    }

    /**
     * @param nombreEmpresa
     *            the nombreEmpresa to set
     */
    public void setNombreEmpresa(String nombreEmpresa) {
	this.nombreEmpresa = nombreEmpresa;
    }

    /**
     * @return the nombreResponsable
     */
    public String getNombreResponsable() {
	return nombreResponsable;
    }

    /**
     * @param nombreResponsable
     *            the nombreResponsable to set
     */
    public void setNombreResponsable(String nombreResponsable) {
	this.nombreResponsable = nombreResponsable;
    }

    /**
     * @return the apellidoResponsable
     */
    public String getApellidoResponsable() {
	return apellidoResponsable;
    }

    /**
     * @param apellidoResponsable
     *            the apellidoResponsable to set
     */
    public void setApellidoResponsable(String apellidoResponsable) {
	this.apellidoResponsable = apellidoResponsable;
    }

    /**
     * @return the emailRespondable
     */
    public String getEmailResponsable() {
	return emailResponsable;
    }

    /**
     * @param emailResponsable
     *            the emailRespondable to set
     */
    public void setEmailResponsable(String emailResponsable) {
	this.emailResponsable = emailResponsable;
    }

    /**
     * @return the telefonoResponsable
     */
    public String getTelefonoResponsable() {
	return telefonoResponsable;
    }

    /**
     * @param telefonoResponsable
     *            the telefonoResponsable to set
     */
    public void setTelefonoResponsable(String telefonoResponsable) {
	this.telefonoResponsable = telefonoResponsable;
    }

    /**
     * @return the fechaDesde
     */
    public Date getFechaDesde() {
	return fechaDesde;
    }

    /**
     * @param fechaDesde
     *            the fechaDesde to set
     */
    public void setFechaDesde(Date fechaDesde) {
	this.fechaDesde = fechaDesde;
    }

    /**
     * @return the fechaHasta
     */
    public Date getFechaHasta() {
	return fechaHasta;
    }

    /**
     * @param fechaHasta
     *            the fechaHasta to set
     */
    public void setFechaHasta(Date fechaHasta) {
	this.fechaHasta = fechaHasta;
    }

    /**
     * @return the fotoPublicidad
     */
    public FotoPublicidad getFotoPublicidad() {
	return fotoPublicidad;
    }

    /**
     * @param fotoPublicidad
     *            the fotoPublicidad to set
     */
    public void setFotoPublicidad(FotoPublicidad fotoPublicidad) {
	this.fotoPublicidad = fotoPublicidad;
    }

    /**
     * @return the precio
     */
    public Float getPrecio() {
	return precio;
    }

    /**
     * @param precio
     *            the precio to set
     */
    public void setPrecio(Float precio) {
	this.precio = precio;
    }

    /**
     * @return the today
     */
    public Date getToday() {
	return today;
    }

    /**
     * @param today
     *            the today to set
     */
    public void setToday(Date today) {
	this.today = today;
    }

    /**
     * @return the costoTotal
     */
    public Float getCostoTotal() {
	return costoTotal;
    }

    /**
     * @param costoTotal
     *            the costoTotal to set
     */
    public void setCostoTotal(Float costoTotal) {
	this.costoTotal = costoTotal;
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

    /**
     * @return the foto
     */
    public UploadedFile getFoto() {
	return foto;
    }

    /**
     * @param foto
     *            the foto to set
     */
    public void setFoto(UploadedFile foto) {
	this.foto = foto;
    }

    /**
     * @return the publicidadParList
     */
    public List<Publicidad> getPublicidadList() {
        return publicidadList;
    }

    /**
     * @param publicidadParList the publicidadParList to set
     */
    public void setPublicidadParList(List<Publicidad> publicidadList) {
        PublicidadManagedBean.publicidadList = publicidadList;
    }

    /**
     * @return the upload
     */
    public boolean isUpload() {
	return upload;
    }

    /**
     * @param upload
     *            the upload to set
     */
    public void setUpload(boolean upload) {
	this.upload = upload;
    }

    /**
     * @return the nuevaPublicidad
     */
    public Publicidad getNuevaPublicidad() {
        return nuevaPublicidad;
    }

    /**
     * @param nuevaPublicidad the nuevaPublicidad to set
     */
    public void setNuevaPublicidad(Publicidad nuevaPublicidad) {
        PublicidadManagedBean.nuevaPublicidad = nuevaPublicidad;
    }

}
