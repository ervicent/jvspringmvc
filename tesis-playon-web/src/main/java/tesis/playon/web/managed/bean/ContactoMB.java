package tesis.playon.web.managed.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.Mail;
import tesis.playon.web.util.NotificadorUtil;

@ManagedBean(name = "contactoMB")
@RequestScoped
public class ContactoMB implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    private String nombre;
    private String asuntoContacto;
    private String mensaje;
    private String destinatario;
    private String mailContacto;
    private Mail mail = new Mail();

    // private MailManagedBean mailMB=new MailManagedBean();
    private NotificadorUtil notificador;

    public String enviarMailContacto()

    {
	try {
	    mensaje = "\nDatos de contacto: " + "\nNombre: " + getNombre() + "\nE-Mail: " + getMailContacto()
		    + "\nAsunto: " + getAsuntoContacto() + "\n\n\nMENSAJE: " + getMensaje();
	    notificador = new NotificadorUtil();
	    mail.setDestinatario("tesisplayon@gmail.com");
	    mail.setMensaje(getMensaje());
	    mail.setAsunto(getAsuntoContacto());

	    notificador.enviar(getMail());
	    reset();
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje enviado correctamente " + " ",
		    "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    reset();
	    return "contactoend";
	} catch (DataAccessException e) {

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Problemas al enviar su mensaje "
		    + "Vuelva  a intentarlo ", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	    return "ERROR";
	}

    }

    public void reset() {

	this.mensaje = "";
	this.nombre = "";
	this.asuntoContacto = "";
	this.mailContacto = "";

    }

    public Mail getMail() {
	return mail;
    }

    public void setMail(Mail mail) {
	this.mail = mail;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getAsuntoContacto() {
	return asuntoContacto;
    }

    public void setAsuntoContacto(String asuntoContacto) {
	this.asuntoContacto = asuntoContacto;
    }

    public String getMensaje() {
	return mensaje;
    }

    public void setMensaje(String mensaje) {
	this.mensaje = mensaje;
    }

    public String getDestinatario() {
	return destinatario;
    }

    public void setDestinatario(String destinatario) {
	this.destinatario = destinatario;
    }

    public String getMailContacto() {
	return mailContacto;
    }

    public void setMailContacto(String mailContacto) {
	this.mailContacto = mailContacto;
    }

}
