/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import tesis.playon.web.model.Foto;
import tesis.playon.web.model.PerfilPlaya;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IFotoService;
import tesis.playon.web.service.IPerfilPlayaService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.util.LatitudlongitudUtil;
import tesis.playon.web.util.LatitudlongitudUtil.GeoposicionDePlaya;
import tesis.playon.web.util.WriteImage;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "perfilPlayaMB")
@ViewScoped
public class PerfilPlayaManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    @ManagedProperty(value = "#{PerfilPlayaService}")
    IPerfilPlayaService perfilPlayaService;

    @ManagedProperty(value = "#{FotoService}")
    IFotoService fotoService;

    private PerfilPlaya perfil;

    private Integer calificacion;

    private List<Foto> fotosList;

    private static UploadedFile fotoPerfilFile;

    private String telefono;

    private String email;

    private Integer disponibilidad;

    private String url;

    // DATOS DE LA FOTO
    private static String title;

    private static String descripcion;

    private Foto fotoSelected;

    // ATRIBUTOS DE LA PLAYA SELECCIONADA
    private static Playa playaSelected;

    private static PerfilPlaya perfilSelected;

    private Integer calificacionSelected;

    private List<Foto> fotosListSelected;

    @PostConstruct
    // METODO PARA INICIALIZAR TODOS LOS ATRIBUTOS
    public void init() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario user = getUsuarioService().findByNombreUsuario(userName);
	if (user != null && user.getPlaya() != null) {
	    this.perfil = getPerfilPlayaService().findByPlaya(user.getPlaya());
	    this.telefono = perfil.getPlaya().getTelefono();
	    this.email = perfil.getPlaya().getEmail();
	    this.disponibilidad = perfil.getPlaya().getDisponibilidad();
	    WriteImage.getFotoPerfilPlaya(perfil);
	    this.coordenadas = initCoordenadas(perfil);
	}
	if (playaSelected != null) {
	    PerfilPlayaManagedBean.perfilSelected = getPerfilPlayaService().findByPlaya(playaSelected);
	    coordenadas = initCoordenadas(perfilSelected);
	    fotosListSelected = getFotoService().findByPlaya(perfilSelected);
	    if (fotosListSelected != null)
		WriteImage.writeFotos(fotosListSelected);
	}
    }

    public void findPlayaById() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	if (!facesContext.isPostback()) {
	    String parametroID = facesContext.getExternalContext().getRequestParameterMap().get("id");
	    if (!parametroID.equals("") || parametroID != null) {
		int idPlayaSelected = Integer.parseInt(parametroID);
		playaSelected = getPlayaService().findById(idPlayaSelected);
		perfilSelected = getPerfilPlayaService().findByPlaya(playaSelected);
		if(perfilSelected != null)
		{
		    fotosListSelected = getFotoService().findByPlaya(perfilSelected);
		    if (fotosListSelected != null)
			WriteImage.writeFotos(fotosListSelected);
		}
	    }
	}
    }

    public String updatePerfil() {
	Playa playa = new Playa();
	try {
	    playa = getPerfil().getPlaya();
	    playa.setTelefono(getTelefono());
	    playa.setEmail(getEmail());
	    playa.setDisponibilidad(getDisponibilidad());
	    playa.setUrl(url);
	    getPerfilPlayaService().update(perfil);
	    getPlayaService().update(playa);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "Se actualizó correctamente el perfil de la playa", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);

	    return "/playa/perfilplaya";
	} catch (Exception ex) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "No se pudo actualizar el perfil de la playa", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    ex.printStackTrace();
	}
	return null;
    }

    public String upload() {
	try {
	    this.perfil.setFotoPerfil(fotoPerfilFile.getContents());
	    this.perfil.setNombreFoto(fotoPerfilFile.getFileName());
	    getPerfilPlayaService().update(this.perfil);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "Se actualizó exitosamente su foto de perfil", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);

	    WriteImage.getFotoPerfilPlaya(this.perfil);

	    return "perfilplayaedit";
	} catch (Exception ex) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo cargar su foto de perfil",
		    "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    ex.printStackTrace();
	}
	return "/error";
    }

    public void handleFileUpload(FileUploadEvent event) {
	UploadedFile file = null;
	byte[] bytes = null;
	try {
	    file = event.getFile();
	    bytes = file.getContents();
	    Foto foto = new Foto();
	    foto.setDescripcion(getDescripcion());
	    foto.setTitle(getTitle());
	    foto.setImage(bytes);
	    foto.setNombre(file.getFileName());
	    foto.setPerfilPlaya(perfil);

	    getFotoService().save(foto);

	    fotosList = getFotoService().findByPlaya(perfil);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se guardó exitosamente la foto "
		    + file.getFileName(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);

	} catch (Exception ex) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se pudo cargar su foto de perfil",
		    "");
	    FacesContext.getCurrentInstance().addMessage("Error", message);
	    ex.printStackTrace();
	}
    }

    public String deleteFoto() {
	ExternalContext extContext = null;
	try {
	    getFotoService().delete(fotoSelected);

	    String sep = File.separator;
	    extContext = FacesContext.getCurrentInstance().getExternalContext();

	    String path = extContext.getRealPath("resources" + sep + "fotos_playas") + sep;

	    File file = new File(path + fotoSelected.getId() + "_" + fotoSelected.getNombre());

	    file.delete();

	    fotosList = getFotoService().findByPlaya(perfil);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se eliminó la foto "
		    + fotoSelected.getNombre(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);

	    return "fotoadd";

	} catch (Exception ex) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "No se pudo borrar la foto de perfil",
		    "");
	    FacesContext.getCurrentInstance().addMessage("Error", message);
	    ex.printStackTrace();
	}
	return "/error";
    }

    private String initCoordenadas(PerfilPlaya perfil) {
	try {
	    advancedModel = new DefaultMapModel();
	    latLonUtil = new LatitudlongitudUtil();
	    // GeoposicionDePlaya
	    respuesta = latLonUtil.getLocationFromAddress(perfil.getPlaya().getDomicilio()
		    + ", Córdoba, CBA, Argentina");
	    coordenadas = respuesta.toString();
	    LatLng coord1 = new LatLng(perfil.getPlaya().getLatitud(), perfil.getPlaya().getLongitud());
	    advancedModel.addOverlay(new Marker(coord1, perfil.getPlaya().toString2(), null,
		    "http://s2.subirimagenes.com/imagen/previo/thump_7891124iconoe.png"));
	    return coordenadas;
	} catch (Exception e) {
	    e.getStackTrace();
	}
	return null;
    }

    public PerfilPlaya getPerfil() {
	return perfil;
    }

    public void setPerfil(PerfilPlaya perfil) {
	this.perfil = perfil;
    }

    public IPerfilPlayaService getPerfilPlayaService() {
	return perfilPlayaService;
    }

    public void setPerfilPlayaService(IPerfilPlayaService perfilPlayaService) {
	this.perfilPlayaService = perfilPlayaService;
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public IPlayaService getPlayaService() {
	return playaService;
    }

    public void setPlayaService(IPlayaService playaService) {
	this.playaService = playaService;
    }

    public IFotoService getFotoService() {
	return fotoService;
    }

    public void setFotoService(IFotoService fotoService) {
	this.fotoService = fotoService;
    }

    public Integer getCalificacion() {
	return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
	this.calificacion = calificacion;
    }

    public List<Foto> getFotosList() {
	fotosList = getFotoService().findByPlaya(perfil);
	WriteImage.writeFotos(fotosList);
	return fotosList;
    }

    public void setFotosList(List<Foto> fotosList) {
	this.fotosList = fotosList;
    }

    public String getTelefono() {
	return telefono;
    }

    public void setTelefono(String telefono) {
	this.telefono = telefono;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Integer getDisponibilidad() {
	return disponibilidad;
    }

    public void setDisponibilidad(Integer disponibilidad) {
	this.disponibilidad = disponibilidad;
    }

    public UploadedFile getFotoPerfilFile() {
	return fotoPerfilFile;
    }

    public void setFotoPerfilFile(UploadedFile fotoPerfilFile) {
	PerfilPlayaManagedBean.fotoPerfilFile = fotoPerfilFile;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	PerfilPlayaManagedBean.title = title;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	PerfilPlayaManagedBean.descripcion = descripcion;
    }

    public Foto getFotoSelected() {
	return fotoSelected;
    }

    public void setFotoSelected(Foto fotoSelected) {
	this.fotoSelected = fotoSelected;
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

    public Playa getPlayaSelected() {
	return playaSelected;
    }

    public void setPlayaSelected(Playa playaSelected) {
	PerfilPlayaManagedBean.playaSelected = playaSelected;
    }

    public PerfilPlaya getPerfilSelected() {
	return perfilSelected;
    }

    public void setPerfilSelected(PerfilPlaya perfilSelected) {
	PerfilPlayaManagedBean.perfilSelected = perfilSelected;
    }

    public Integer getCalificacionSelected() {
	return calificacionSelected;
    }

    public void setCalificacionSelected(Integer calificacionSelected) {
	this.calificacionSelected = calificacionSelected;
    }

    public List<Foto> getFotosListSelected() {
	return fotosListSelected;
    }

    public void setFotosListSelected(List<Foto> fotosListSelected) {
	this.fotosListSelected = fotosListSelected;
    }

    // datos para mostrar en el mapa
    LatitudlongitudUtil latLonUtil;

    private GeoposicionDePlaya respuesta;

    private String coordenadas;

    private String coordenadasSelected;

    private MapModel advancedModel;

    private Marker marker;

    public String getCoordenadas() {
	return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
	this.coordenadas = coordenadas;
    }

    public String getCoordenadasSelected() {
	return coordenadasSelected;
    }

    public void setCoordenadasSelected(String coordenadasSelected) {
	this.coordenadasSelected = coordenadasSelected;
    }

    public GeoposicionDePlaya getRespuesta() {
	return respuesta;
    }

    public void setRespuesta(GeoposicionDePlaya respuesta) {
	this.respuesta = respuesta;
    }

    public LatitudlongitudUtil getLatLonUtil() {
	return latLonUtil;
    }

    public void setLatLonUtil(LatitudlongitudUtil latLonUtil) {
	this.latLonUtil = latLonUtil;
    }

    public MapModel getAdvancedModel() {
	return advancedModel;
    }

    public Marker getMarker() {
	return marker;
    }

    public void setMarker(Marker marker) {
	this.marker = marker;
    }
}