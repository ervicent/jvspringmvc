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

import tesis.playon.web.model.Comentario;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IComentarioService;
import tesis.playon.web.service.IPerfilPlayaService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.util.WriteImage;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "comentarioMB")
@ViewScoped
public class ComentarioManagedBean implements Serializable {

    private static final long serialVersionUID = 6773490680356877684L;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    @ManagedProperty(value = "#{PerfilPlayaService}")
    IPerfilPlayaService perfilPlayaService;

    @ManagedProperty(value = "#{ComentarioService}")
    IComentarioService comentarioService;

    private static Playa playaSelected;

    private List<Comentario> comentariosList;

    private List<Comentario> comentariosListPerfil;

    private static String previusPage;

    // ATRIBUTOS PARA LA CREACION DE UN COMENTARIO
    private String comentario;
    
    private Comentario comentarioDenunciado;
    
    private Usuario usuario;

    @PostConstruct
    public void init() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario user = getUsuarioService().findByNombreUsuario(userName);
	this.setUsuario(user);
	if (user != null && user.getPlaya() != null) {
	    comentariosList = getComentarioService().findByPlaya(user.getPlaya());
	}	
    }

    public void addComentario() {
	Comentario comentario = null;
	try {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    String userName = facesContext.getExternalContext().getRemoteUser();
	    Usuario user = getUsuarioService().findByNombreUsuario(userName);
	    if (user != null) {
		if (user.getPlaya() == null) {
		    comentario = new Comentario(user, getComentario(), playaSelected, new Boolean(true));
		    getComentarioService().save(comentario);
		    comentariosListPerfil = getComentarioService().findByPlaya(playaSelected);
		} else {
		    if (playaSelected == null) {
			comentario = new Comentario(user, getComentario(), user.getPlaya(), new Boolean(true));
			getComentarioService().save(comentario);
			comentariosListPerfil = getComentarioService().findByPlaya(user.getPlaya());
		    } else {
			comentario = new Comentario(user, getComentario(), playaSelected, new Boolean(true));
			getComentarioService().save(comentario);
			comentariosListPerfil = getComentarioService().findByPlaya(user.getPlaya());
		    }
		}

		setComentario(null);

		FacesContext.getCurrentInstance().addMessage("messageComentario",
			new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró exitosamente su comentario", ""));
		
	    } else {
		FacesContext.getCurrentInstance().addMessage(
			"messageComentario",
			new FacesMessage(FacesMessage.SEVERITY_WARN, "No se pudó registrar su comentario",
				"¡Debe iniciar sesión para poder comentar la playa!"));
	    }

	} catch (Exception e) {
	    FacesContext.getCurrentInstance().addMessage(
		    "messageComentario",
		    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudó registrar su comentario",
			    "Disculpe las molestias ocacionadas"));
	}
    }

    public void addComentarioPerfil() {
	Comentario comentario = null;
	try {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    String userName = facesContext.getExternalContext().getRemoteUser();
	    Usuario user = getUsuarioService().findByNombreUsuario(userName);
	    if (user != null) {
		if (user.getPlaya() == null) {
		    comentario = new Comentario(user, getComentario(), playaSelected, new Boolean(true));
		    getComentarioService().save(comentario);
		    comentariosList = getComentarioService().findByPlaya(playaSelected);
		} else {
		    if (playaSelected == null) {
			comentario = new Comentario(user, getComentario(), user.getPlaya(), new Boolean(true));
			getComentarioService().save(comentario);
			comentariosList = getComentarioService().findByPlaya(user.getPlaya());
		    } else {
			comentario = new Comentario(user, getComentario(), playaSelected, new Boolean(true));
			getComentarioService().save(comentario);
			comentariosList = getComentarioService().findByPlaya(user.getPlaya());
		    }
		}

		setComentario(null);

		FacesContext.getCurrentInstance().addMessage("messageComentario",
			new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró exitosamente su comentario", ""));
	    } else {
		FacesContext.getCurrentInstance().addMessage(
			"messageComentario",
			new FacesMessage(FacesMessage.SEVERITY_WARN, "No se pudó registrar su comentario",
				"¡Debe iniciar sesión para poder comentar la playa!"));
	    }

	} catch (Exception e) {
	    FacesContext.getCurrentInstance().addMessage(
		    "messageComentario",
		    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudó registrar su comentario",
			    "Disculpe las molestias ocacionadas"));
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
    
    public void denunciarComentario(){
	try{
	    if(comentarioDenunciado != null){
		comentarioDenunciado.setHabilitado(new Boolean(false));
		getComentarioService().update(comentarioDenunciado);
		
		FacesMessage message = new FacesMessage(
			FacesMessage.SEVERITY_INFO,
			"Se denunció correctamente el comentario", "");
		FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	}catch(Exception ex){
	    FacesContext.getCurrentInstance().addMessage(
		    "messageComentario",
		    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudó denunciar el comentario",
			    "Disculpe las molestias ocacionadas"));
	    ex.printStackTrace();
	}
    }
    
    public String denunciarComentarioGerente(){
	try{
	    if(comentarioDenunciado != null){
		comentarioDenunciado.setHabilitado(new Boolean(false));
		getComentarioService().update(comentarioDenunciado);
		
		FacesMessage message = new FacesMessage(
			FacesMessage.SEVERITY_INFO,
			"Se denunció correctamente el comentario", "");
		FacesContext.getCurrentInstance().addMessage(null, message);
		
		return "comentariolist";
	    }
	}catch(Exception ex){
	    FacesContext.getCurrentInstance().addMessage(
		    "messageComentario",
		    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudó denunciar el comentario",
			    "Disculpe las molestias ocacionadas"));
	    ex.printStackTrace();
	}
	return null;
    }

    public IPlayaService getPlayaService() {
	return playaService;
    }

    public void setPlayaService(IPlayaService playaService) {
	this.playaService = playaService;
    }

    public IPerfilPlayaService getPerfilPlayaService() {
	return perfilPlayaService;
    }

    public void setPerfilPlayaService(IPerfilPlayaService perfilPlayaService) {
	this.perfilPlayaService = perfilPlayaService;
    }

    public IComentarioService getComentarioService() {
	return comentarioService;
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public void setComentarioService(IComentarioService comentarioService) {
	this.comentarioService = comentarioService;
    }

    public Playa getPlayaSelected() {
	comentariosListPerfil = new ArrayList<Comentario>();
	if (playaSelected != null) {
	    comentariosListPerfil = getComentarioService().findByPlaya(playaSelected);
	    if (comentariosListPerfil != null) {
		for (Comentario comentario : comentariosListPerfil) {
		    WriteImage.getFotoPerfilUsuario(comentario.getUsuario());
		}
	    }
	}
	return playaSelected;
    }

    public void setPlayaSelected(Playa playaSelected) {
	ComentarioManagedBean.playaSelected = playaSelected;
    }

    public List<Comentario> getComentariosList() {
	return comentariosList;
    }

    public void setComentariosList(List<Comentario> comentariosList) {
	this.comentariosList = comentariosList;
    }

    public List<Comentario> getComentariosListPerfil() {
	comentariosListPerfil = new ArrayList<Comentario>();
	if (playaSelected != null) {
	    comentariosListPerfil = getComentarioService().findByPlaya(playaSelected);
	    if (comentariosListPerfil != null) {
		for (Comentario comentario : comentariosListPerfil) {
		    WriteImage.getFotoPerfilUsuario(comentario.getUsuario());
		}
	    }
	}
	return comentariosListPerfil;
    }

    public void setComentariosListPerfil(List<Comentario> comentariosListPerfil) {
	this.comentariosListPerfil = comentariosListPerfil;
    }

    public String getComentario() {
	return comentario;
    }

    public void setComentario(String comentario) {
	this.comentario = comentario;
    }

    public String getPreviusPage() {
	return previusPage;
    }

    public void setPreviusPage(String previusPage) {
	ComentarioManagedBean.previusPage = previusPage;
    }

    /**
     * @return the comentarioDenunciado
     */
    public Comentario getComentarioDenunciado() {
        return comentarioDenunciado;
    }

    /**
     * @param comentarioDenunciado the comentarioDenunciado to set
     */
    public void setComentarioDenunciado(Comentario comentarioDenunciado) {
        this.comentarioDenunciado = comentarioDenunciado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
