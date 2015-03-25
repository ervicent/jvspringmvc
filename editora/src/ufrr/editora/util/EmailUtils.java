package ufrr.editora.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import ufrr.editora.entity.Usuario;
import ufrr.editora.mb.LoginBean;

public class EmailUtils {
	
//	 private static final String HOSTNAME = "mail.ufrr.br";
//	 private static final String USERNAME = "csi";
//	 private static final String PASSWORD = "Csi13/";
//	 private static final String EMAILORIGEM = "csi@ufrr.br";
	 
	 private static final String HOSTNAME = "smtp.gmail.com";
	 private static final String USERNAME = "ufrreditora";
	 private static final String PASSWORD = "sistemaeditora";
	 private static final String EMAILORIGEM = "ufrreditora@gmail.com";
	 
	@SuppressWarnings("deprecation")
	public static Email conectaEmail() throws EmailException {
	 Email email2 = new SimpleEmail();
	 email2.setHostName(HOSTNAME);
	 email2.setSmtpPort(587);
	 email2.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
	 email2.setTLS(true);
	 email2.setFrom(EMAILORIGEM);
	 System.out.println("..." + "HOST: " + HOSTNAME + "\n" + "..." + "EMAIL ORIGEM: " + EMAILORIGEM + "\n" + "..." + "USERNAME: " + USERNAME);
	 return email2;
	 }
	 
	 @SuppressWarnings("unused")
	public static void enviaEmail(ufrr.editora.entity.EnviaEmail email) throws EmailException {
	 Email email2 = new SimpleEmail();
	 email2 = conectaEmail();
	 email2.setSubject(email.getTitulo());
	 email2.setMsg(email.getMensagem());
	 email2.addTo(email.getDestino().getLogin());
	 String resposta = email2.send();
	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "E-MAIL ENVIADO COM SUCESSO PARA: " + email.getDestino().getLogin(), "Informacao"));
	 }
	 
	// servidor de teste
	@SuppressWarnings("unused")
	public static void recuperaSenha(LoginBean usuario) throws EmailException {
		Email email2 = new SimpleEmail();
		email2 = conectaEmail();
		email2.setSubject("Recuperacao de senha ao sistema Editora UFRR-noreply");
		email2.setMsg("Editora UFRR"
				+ "\n"
				+ "siga as instrucoes abaixo para prosseguir com a solicitacao"
				+ "\n"
				+ "\n"
				+ "Clique no link e informe o codigo abaixo: "
				+ "http://csi.ufrr.br:8080/editora/esqueceuSenha.xhtml?faces-redirect=true"
				+ "\n" + "\n" + usuario.getUsuario().getSenha());
		email2.addTo(usuario.getUsuario().getLogin());
		//String resposta = email2.send();
		FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"E-MAIL ENVIADO COM SUCESSO PARA: "
										+ usuario.getUsuario().getLogin(),
								"Informacao"));
	}
	
	// email para confirmação de autorização!
	 @SuppressWarnings("unused")
		public static void confirmaAcesso(Usuario usuario) throws EmailException {
		 Email email2 = new SimpleEmail();
		 email2 = conectaEmail();
		 email2.setSubject("Sistema Editora UFRR-noreply");
		 email2.setMsg("Sua solicitacao de acesso ao sistema editora UFRR foi aceita com sucesso." + "\n" + "Clique no endereco abaixo digite seu email e senha" + "\n" + "\n" +
		 "http://csi.ufrr.br:8080/editora" + "\n" + "\n" + "NAO RESPONDA, EMAIL AUTOMATICO.");
		 email2.addTo(usuario.getLogin());
		 String resposta = email2.send();
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "CONFIRMAÇÃO DE ACESSO ENVIADO PARA O EMAIL: " + usuario.getLogin(), "Informacao"));
		 }
	 
//	 @SuppressWarnings("unused")
//		public static void acessoNaoAutorizado(Usuario usuario) throws EmailException {
//		 Email email2 = new SimpleEmail();
//		 email2 = conectaEmail();
//		 email2.setSubject("Sistema Editora UFRR");
//		 email2.setMsg("Sua solicitacao de acesso ao sistema editora UFRR Não foi autorizado, entre em contato.");
//		 email2.addTo(usuario.getLogin());
//		 String resposta = email2.send();
//		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "CONFIRMAÇÃO DE ACESSO ENVIADO PARA O EMAIL: " + usuario.getLogin(), "Informacao"));
//		 }
}
