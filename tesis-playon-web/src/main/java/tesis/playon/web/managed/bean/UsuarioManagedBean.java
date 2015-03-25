package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;
import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.FotoUsuario;
import tesis.playon.web.model.Mail;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.TipoDoc;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IFotoUsuarioService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.util.NotificadorUtil;
import tesis.playon.web.util.WriteImage;

@ManagedBean(name = "usuarioMB")
@ViewScoped
public class UsuarioManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    private static final String LISTA_USUARIOS = "usuariolist";

    private static final String ERROR = "error";

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{FotoUsuarioService}")
    IFotoUsuarioService fotoUsuarioService;

    private static List<Usuario> usuarioList;

    private List<Usuario> filteredUsuario;

    private String passViejo;

    private String passActual;

    private String apellido;

    private String nombre;

    private String email;

    private Integer nroDoc;

    private String password;

    private String nombreUser;

    private TipoDoc tipoDoc;

    private Playa playa;

    private static Usuario usuarioSelected;

    private Usuario usuarioLoggeado;

    private String passwordActual;

    private UploadedFile fotoPerfilFile;

    @PostConstruct
    private void init() {
	usuarioList = getUsuarioService().findAll();
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	if (userName != null) {
	    usuarioLoggeado = getUsuarioService().findByNombreUsuario(userName);
	}
    }

    public String addUsuarioAdmin() {
	Usuario usuario = new Usuario();
	try {

	    usuario.setNombre(getNombre());
	    usuario.setApellido(getApellido());
	    usuario.setEmail(getEmail());
	    usuario.setNroDoc(getNroDoc());
	    usuario.setPassword(getPassword());
	    usuario.setNombreUser(getNombreUser());
	    usuario.setTipoDoc(getTipoDoc());
	    usuario.setPlaya(getPlaya());
	    usuario.setEnable(new Boolean("true"));
	    getUsuarioService().save(usuario);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se agregó correctamente el usuario: "
		    + usuario.getNombreUser(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_USUARIOS;

	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo agregar el usuario: " + usuario.getNombreUser(),
		    "Por favor, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo agregar el usuario. Nombre de usuario o mail Duplicados", "Usuario duplicado");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
	return ERROR;
    }

    public String deleteUsuarioAdmin() {
	try {
	    if (usuarioSelected != null) {
		usuarioSelected.setEnable(new Boolean("false"));

		getUsuarioService().update(usuarioSelected);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se dió de baja al usuario: "
			+ usuarioSelected.getNombreUser(), "");
		FacesContext.getCurrentInstance().addMessage(null, message);
		return LISTA_USUARIOS;
	    }
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo dar de baja al usuario: " + usuarioSelected.getNombreUser(),
		    "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
	return ERROR;
    }

    public void deleteUsuario(Usuario usuario) {
	getUsuarioService().delete(usuario);
    }

    public void updateUsuario() {
	try {
	    if (usuarioSelected != null) {
		getUsuarioService().update(usuarioSelected);

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, usuarioSelected.getNombreUser()
			+ " se actualizó correctamente", "");
		FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	} catch (Exception ex) {

	}

    }

    public String updateUsuarioAdmin() {
	try {
	    if (usuarioSelected != null) {
		getUsuarioService().update(usuarioSelected);

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, usuarioSelected.getNombreUser()
			+ " se actualizó correctamente", "");
		FacesContext.getCurrentInstance().addMessage(null, message);

		return LISTA_USUARIOS;
	    }

	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, "
		    + usuarioSelected.getNombreUser() + " no se pudo modificar", "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo agregar el cliente. Nombre de usuario o mail Duplicados", "Usuario duplicado");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
	return ERROR;
    }

    public String recuperarPass() {
	Usuario usu = new Usuario();

	try {

	    usu = getUsuarioService().findByNombreUsuario(getNombreUser());
	    Mail mail = new Mail();
	    NotificadorUtil notificador = new NotificadorUtil();
	    mail.setAsunto("Contraseña de Playon - Red de plaayas");
	    mail.setDestinatario(usu.getEmail());
	    mail.setMensaje("Estimado: " + usu.getNombre()
		    + " su contraseña de Playón - Red de playas es la siguiente: " + usu.getPassword());
	    notificador.enviar(mail);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "Se envió correctamente su contraseña: " + usu.getApellido() + " " + usu.getNombre(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);

	    return "recuperarpasswordend";
	}

	catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error el usuario no existe, Por favor, inténtelo más tarde.", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();

	}
	return "ERROR";

    }

    public String modificarPassword() {
	try {
	    if (usuarioLoggeado != null) {
		if (usuarioLoggeado.getPassword().equals(passwordActual)) {
		    usuarioLoggeado.setPassword(getPassword());

		    getUsuarioService().update(usuarioLoggeado);

		    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
			    "Se actualizó correctamente la contraseña del usuario: " + usuarioLoggeado.getApellido()
				    + " " + usuarioLoggeado.getNombre(), "");
		    FacesContext.getCurrentInstance().addMessage(null, message);
		    return "cambiarpasswordend";
		} else {
		    FacesMessage message = new FacesMessage(
			    FacesMessage.SEVERITY_WARN,
			    "Por favor, ingrese correctamente su contraseña actual para actualizar su nueva contraseña",
			    "");
		    FacesContext.getCurrentInstance().addMessage(null, message);
		}
	    }
	    return "";
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "No se pudo modificar su contraseña, Por favor, inténtelo más tarde.", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	    return "ERROR";
	}
    }

    public void generarNuevaContrasenaUsuario() {
	try {
	    if (usuarioSelected != null) {
		char[] elementos = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', '�', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
			'y', 'z' };

		char[] conjunto = new char[8];
		for (int i = 0; i < 8; i++) {
		    int el = (int) (Math.random() * 37);
		    conjunto[i] = (char) elementos[el];
		}
		String pass = new String(conjunto);

		Mail mail = new Mail();
		mail.setAsunto("Nueva clave de acceso");
		mail.setDestinatario(usuarioSelected.getEmail());
		mail.setMensaje("Estimado: " + usuarioSelected.getNombre()
			+ " se ha generado una nueva clave para que acceda a nuestro sistema.\n\nLa nueva clave es: "
			+ pass + " \n\n ¡Muchas gracias!");
		NotificadorUtil notificador = new NotificadorUtil();
		notificador.enviar(mail);

		usuarioSelected.setPassword(pass);
		getUsuarioService().update(usuarioSelected);

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
			"Se generó correctamente la nueva clave del cliente: " + usuarioSelected.getApellido() + " "
				+ usuarioSelected.getNombre(), "");
		FacesContext.getCurrentInstance().addMessage(null, message);

	    }
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo generar la clave de acceso del cliente.", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}

    }

    public void upload() {
	try {

	    FotoUsuario foto = new FotoUsuario(fotoPerfilFile.getContents());
	    getFotoUsuarioService().save(foto);

	    FotoUsuario fotoAntigua = usuarioLoggeado.getFotoUsuario();
	    WriteImage.borrarFotoPerfilUsuarioAntigua(usuarioLoggeado);
	    this.usuarioLoggeado.setFotoUsuario(foto);
	    getUsuarioService().update(usuarioLoggeado);

	    if (fotoAntigua != null) {
		getFotoUsuarioService().delete(fotoAntigua);
	    }

	    WriteImage.getFotoPerfilUsuario(usuarioLoggeado);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "Se actualizó exitosamente su foto de perfil", null);
	    FacesContext.getCurrentInstance().addMessage(null, message);
	} catch (Exception ex) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo cargar su foto de perfil",
		    "");
	    FacesContext.getCurrentInstance().addMessage("", message);
	    ex.printStackTrace();
	}
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    /**
     * @return the fotoUsuarioService
     */
    public IFotoUsuarioService getFotoUsuarioService() {
	return fotoUsuarioService;
    }

    /**
     * @param fotoUsuarioService
     *            the fotoUsuarioService to set
     */
    public void setFotoUsuarioService(IFotoUsuarioService fotoUsuarioService) {
	this.fotoUsuarioService = fotoUsuarioService;
    }

    public List<Usuario> getUsuarioList() {
	return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
	UsuarioManagedBean.usuarioList = usuarioList;
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

    public Usuario getUsuarioSelected() {
	return usuarioSelected;
    }

    public void setUsuarioSelected(Usuario usuarioSelected) {
	UsuarioManagedBean.usuarioSelected = usuarioSelected;
    }

    public String getPassViejo() {
	return passViejo;
    }

    public void setPassViejo(String passViejo) {
	this.passViejo = passViejo;
    }

    public String getPassActual() {
	passActual = usuarioSelected.getPassword();
	return passActual;
    }

    public void setPassActual(String passActual) {
	this.passActual = passActual;
    }

    /**
     * @return the usuarioLoggeado
     */
    public Usuario getUsuarioLoggeado() {
	return usuarioLoggeado;
    }

    /**
     * @param usuarioLoggeado
     *            the usuarioLoggeado to set
     */
    public void setUsuarioLoggeado(Usuario usuarioLoggeado) {
	this.usuarioLoggeado = usuarioLoggeado;
    }

    /**
     * @return the filteredUsuario
     */
    public List<Usuario> getFilteredUsuario() {
	return filteredUsuario;
    }

    /**
     * @param filteredUsuario
     *            the filteredUsuario to set
     */
    public void setFilteredUsuario(List<Usuario> filteredUsuario) {
	this.filteredUsuario = filteredUsuario;
    }

    /**
     * @return the passwordActual
     */
    public String getPasswordActual() {
	return passwordActual;
    }

    /**
     * @param passwordActual
     *            the passwordActual to set
     */
    public void setPasswordActual(String passwordActual) {
	this.passwordActual = passwordActual;
    }

    /**
     * @return the fotoPerfilFile
     */
    public UploadedFile getFotoPerfilFile() {
	return fotoPerfilFile;
    }

    /**
     * @param fotoPerfilFile
     *            the fotoPerfilFile to set
     */
    public void setFotoPerfilFile(UploadedFile fotoPerfilFile) {
	this.fotoPerfilFile = fotoPerfilFile;
    }
}