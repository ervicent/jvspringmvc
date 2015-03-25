package ufrr.editora.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import ufrr.editora.dao.DAO;
import ufrr.editora.entity.EnviaEmail;
import ufrr.editora.entity.Usuario;
import ufrr.editora.util.EmailUtils;

@ManagedBean
@ViewScoped
public class EmailBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;
	
	private EnviaEmail email = new EnviaEmail();
	private DAO<EnviaEmail> dao = new DAO<EnviaEmail>(EnviaEmail.class);
	private List<EnviaEmail> emails;

	/** actions **/
	
	public String enviar() {
		try {
			EmailUtils.enviaEmail(email);
			this.getEmail().setAutorEnvio(this.loginBean.getUsuario());
			DAO<Usuario> udao = new DAO<Usuario>(Usuario.class);
			Usuario u = udao.buscaPorId(this.loginBean.getUsuario().getId());
			u.getEmails().add(email);
			dao.adiciona(email);
			System.out.println("...Email enviado com sucesso");
			this.email = new EnviaEmail();
			return "/pages/email/enviadoComSucesso.xhtml?faces-redirect=true";
		} catch (EmailException ex) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO! OCORREU UM ERRO AO TENTAR ENVIAR O EMAIL.", "Erro"));
			Logger.getLogger(EmailBean.class.getName()).log(Level.ERROR, null, ex);
		}
		return null;
	}
	
	public EnviaEmail getEmail() {
		return email;
	}

	public void setEmail(EnviaEmail email) {
		this.email = email;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public DAO<EnviaEmail> getDao() {
		return dao;
	}

	public void setDao(DAO<EnviaEmail> dao) {
		this.dao = dao;
	}

	public List<EnviaEmail> getEmails() {
		return emails;
	}

	public void setEmails(List<EnviaEmail> emails) {
		this.emails = emails;
	}

}
