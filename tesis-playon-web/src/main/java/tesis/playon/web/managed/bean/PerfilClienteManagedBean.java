/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.FotoUsuario;
import tesis.playon.web.model.TipoDoc;
import tesis.playon.web.model.TransaccionCliente;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IClienteService;
import tesis.playon.web.service.IFotoUsuarioService;
import tesis.playon.web.service.ITransaccionClienteService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.util.WriteImage;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "perfilClienteMB")
@ViewScoped
public class PerfilClienteManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{ClienteService}")
    IClienteService clienteService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;
    
    @ManagedProperty(value = "#{FotoUsuarioService}")
    IFotoUsuarioService fotoUsuarioService;
    
    @ManagedProperty(value = "#{TransaccionClienteService}")
    ITransaccionClienteService transaccionClienteService;

    private String apellido;

    private String nombre;

    private String email;

    private TipoDoc tipoDoc;

    private Integer nroDocumento;

    private String domicilio;

    private String telefono;

    private Barrio barrio;

    private Cliente cliente;

    private Usuario usuario;

    private UploadedFile fotoPerfilFile;
    
    private TransaccionCliente ultimaRecarga;

    @PostConstruct
    private void init() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario user = getUsuarioService().findByNombreUsuario(userName);
	if (user != null) {
	    this.usuario = user;
	    this.cliente = getClienteService().findByUsuario(user);
	    WriteImage.getFotoPerfilUsuario(user);
	    this.apellido = user.getApellido();
	    this.nombre = user.getNombre();
	    this.email = user.getEmail();
	    this.tipoDoc = user.getTipoDoc();
	    this.nroDocumento = user.getNroDoc();
	    this.domicilio = cliente.getDomicilio();
	    this.telefono = cliente.getTelefono();
	    this.barrio = cliente.getBarrio();
	    ultimaRecarga = getTransaccionClienteService().getUltimaTransaccion(cliente.getCuentaCliente());
	}
    }

    public String upload() {
	try {
	    
	    FotoUsuario foto = new FotoUsuario(fotoPerfilFile.getContents());
	    getFotoUsuarioService().save(foto);
	    
	    FotoUsuario fotoAntigua = cliente.getUsuario().getFotoUsuario();
	    WriteImage.borrarFotoPerfilUsuarioAntigua(cliente.getUsuario());
	    this.cliente.getUsuario().setFotoUsuario(foto);
	    getUsuarioService().update(this.cliente.getUsuario());
	    usuario = cliente.getUsuario();
	    
	    if(fotoAntigua != null){
		getFotoUsuarioService().delete(fotoAntigua);
	    }
	    
	    WriteImage.getFotoPerfilUsuario(this.cliente.getUsuario());
	    
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "Se actualizó exitosamente su foto de perfil", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);

	    return "perfilclienteedit";
	} catch (Exception ex) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo cargar su foto de perfil",
		    "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    ex.printStackTrace();
	}
	return "/error";
    }

    public String updateCliente() {
	try {
	    this.cliente.setBarrio(getBarrio());
	    this.cliente.setDomicilio(getDomicilio());
	    this.cliente.setTelefono(getTelefono());
	    this.cliente.getUsuario().setApellido(getApellido());
	    this.cliente.getUsuario().setEmail(getEmail());
	    this.cliente.getUsuario().setNombre(getNombre());
	    this.cliente.getUsuario().setNroDoc(getNroDocumento());
	    this.cliente.getUsuario().setTipoDoc(getTipoDoc());

	    getUsuarioService().update(this.cliente.getUsuario());
	    getClienteService().update(this.cliente);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "Se actualizó exitosamente su perfil.", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    
	    return "perfilcliente";
	} catch (Exception ex) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "ERROR, No se pudo modificar el perfil, Disculpe las molestias ocacionadas", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    ex.printStackTrace();
	}
	return "/error";
    }

    public IClienteService getClienteService() {
	return clienteService;
    }

    public void setClienteService(IClienteService clienteService) {
	this.clienteService = clienteService;
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
     * @param fotoUsuarioService the fotoUsuarioService to set
     */
    public void setFotoUsuarioService(IFotoUsuarioService fotoUsuarioService) {
        this.fotoUsuarioService = fotoUsuarioService;
    }

    /**
     * @return the transaccionClienteService
     */
    public ITransaccionClienteService getTransaccionClienteService() {
        return transaccionClienteService;
    }

    /**
     * @param transaccionClienteService the transaccionClienteService to set
     */
    public void setTransaccionClienteService(ITransaccionClienteService transaccionClienteService) {
        this.transaccionClienteService = transaccionClienteService;
    }

    public Cliente getCliente() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
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

    public TipoDoc getTipoDoc() {
	return tipoDoc;
    }

    public void setTipoDoc(TipoDoc tipoDoc) {
	this.tipoDoc = tipoDoc;
    }

    public String getDomicilio() {
	return domicilio;
    }

    public void setDomicilio(String domicilio) {
	this.domicilio = domicilio;
    }

    public String getTelefono() {
	return telefono;
    }

    public void setTelefono(String telefono) {
	this.telefono = telefono;
    }

    public Barrio getBarrio() {
	return barrio;
    }

    public void setBarrio(Barrio barrio) {
	this.barrio = barrio;
    }

    public Integer getNroDocumento() {
	return nroDocumento;
    }

    public void setNroDocumento(Integer nroDocumento) {
	this.nroDocumento = nroDocumento;
    }

    /**
     * @return the ultimaRecarga
     */
    public TransaccionCliente getUltimaRecarga() {
        return ultimaRecarga;
    }

    /**
     * @param ultimaRecarga the ultimaRecarga to set
     */
    public void setUltimaRecarga(TransaccionCliente ultimaRecarga) {
        this.ultimaRecarga = ultimaRecarga;
    }

    public UploadedFile getFotoPerfilFile() {
	return fotoPerfilFile;
    }

    public void setFotoPerfilFile(UploadedFile fotoPerfilFile) {
	this.fotoPerfilFile = fotoPerfilFile;
    }

}
