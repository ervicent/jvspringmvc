/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.DenunciaPlaya;
import tesis.playon.web.model.EstadoDenuncia;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IClienteService;
import tesis.playon.web.service.IDenunciaPlayaService;
import tesis.playon.web.service.IEstadoDenunciaService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IUsuarioService;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "denunciaPlayaMB")
@ViewScoped
public class DenunciaPlayaManagedBean implements Serializable {

    private static final long serialVersionUID = 1658656193254593451L;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    @ManagedProperty(value = "#{EstadoDenunciaService}")
    IEstadoDenunciaService estadoDenunciaService;

    @ManagedProperty(value = "#{ClienteService}")
    IClienteService clienteService;

    @ManagedProperty(value = "#{DenunciaPlayaService}")
    IDenunciaPlayaService denunciaPlayaService;

    private Cliente cliente;

    private static Playa playaSelected;

    private String motivo;

    private EstadoDenuncia estadoDenuncia;

    @PostConstruct
    public void init() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario user = getUsuarioService().findByNombreUsuario(userName);
	if (user != null && user.getPlaya() == null) {
	    cliente = getClienteService().findByUsuario(user);
	}
	estadoDenuncia = getEstadoDenunciaService().findByNombreEstadoDenuncia("Pendiente");
    }

    public String denunciarPlaya() {
	DenunciaPlaya denuncia = null;
	try {
	    if (playaSelected != null) {
		denuncia = new DenunciaPlaya();
		denuncia.setCliente(cliente);
		denuncia.setEstado(estadoDenuncia);
		denuncia.setFechaAlta(new Date());
		denuncia.setPlaya(playaSelected);
		denuncia.setAsunto(motivo);

		getDenunciaPlayaService().save(denuncia);

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
			"Su denuncia se registró con éxito y se encuentra pendiente de auditoría.", "");
		FacesContext.getCurrentInstance().addMessage(null, message);

		return "viewperfilplaya.html?id=" + playaSelected.getId();
	    } else
		return "index";

	} catch (Exception ex) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Problemas al enviar su denuncia. Vuelva a intentarlo más tarde.", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    ex.printStackTrace();
	}
	return null;
    }

    /**
     * @return the clienteService
     */
    public IClienteService getClienteService() {
	return clienteService;
    }

    /**
     * @param clienteService
     *            the clienteService to set
     */
    public void setClienteService(IClienteService clienteService) {
	this.clienteService = clienteService;
    }

    /**
     * @return the usuarioService
     */
    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    /**
     * @param usuarioService
     *            the usuarioService to set
     */
    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    /**
     * @return the playaService
     */
    public IPlayaService getPlayaService() {
	return playaService;
    }

    /**
     * @param playaService
     *            the playaService to set
     */
    public void setPlayaService(IPlayaService playaService) {
	this.playaService = playaService;
    }

    /**
     * @return the estadoDenunciaService
     */
    public IEstadoDenunciaService getEstadoDenunciaService() {
	return estadoDenunciaService;
    }

    /**
     * @param estadoDenunciaService
     *            the estadoDenunciaService to set
     */
    public void setEstadoDenunciaService(IEstadoDenunciaService estadoDenunciaService) {
	this.estadoDenunciaService = estadoDenunciaService;
    }

    /**
     * @return the denunciaPlayaService
     */
    public IDenunciaPlayaService getDenunciaPlayaService() {
	return denunciaPlayaService;
    }

    /**
     * @param denunciaPlayaService
     *            the denunciaPlayaService to set
     */
    public void setDenunciaPlayaService(IDenunciaPlayaService denunciaPlayaService) {
	this.denunciaPlayaService = denunciaPlayaService;
    }

    /**
     * @return the estadoDenuncia
     */
    public EstadoDenuncia getEstadoDenuncia() {
	return estadoDenuncia;
    }

    /**
     * @param estadoDenuncia
     *            the estadoDenuncia to set
     */
    public void setEstadoDenuncia(EstadoDenuncia estadoDenuncia) {
	this.estadoDenuncia = estadoDenuncia;
    }

    /**
     * @return the playaSelected
     */
    public Playa getPlayaSelected() {
	return playaSelected;
    }

    /**
     * @param playaSelected
     *            the playaSelected to set
     */
    public void setPlayaSelected(Playa playaSelected) {
	DenunciaPlayaManagedBean.playaSelected = playaSelected;
    }

    /**
     * @return the motivo
     */
    public String getMotivo() {
	return motivo;
    }

    /**
     * @param motivo
     *            the motivo to set
     */
    public void setMotivo(String motivo) {
	this.motivo = motivo;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
	return cliente;
    }

    /**
     * @param cliente
     *            the cliente to set
     */
    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

}
