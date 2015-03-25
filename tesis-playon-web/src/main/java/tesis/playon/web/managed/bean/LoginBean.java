package tesis.playon.web.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.RememberMeServices;

import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IUsuarioService;

/**
 * 
 * @author gmorales
 * 
 */
@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginBean {

    private static final String ROLE_ADMIN = "[ROLE_ADMIN]";

    private static final String ROLE_AUDITOR = "[ROLE_AUDITOR]";

    private static final String ROLE_CONTADOR = "[ROLE_CONTADOR]";

    private static final String ROLE_USER = "[ROLE_USER]";

    private static final String ROLE_CLIENT = "[ROLE_CLIENT]";

    private static final String ROLE_PLAYA_EMPLEADO = "[ROLE_PLAYA_EMPLEADO]";

    private static final String ROLE_PLAYA_GERENTE = "[ROLE_PLAYA_GERENTE]";

    private String usuario = null;

    private Usuario usuarioLogueado = null;

    private String password = null;

    private String rol = null;

    private String recordar = null;

    private Boolean logueado = false;

    @ManagedProperty(value = "#{authenticationManager}")
    private AuthenticationManager authenticationManager = null;

    @ManagedProperty(value = "#{rememberMeServices}")
    private RememberMeServices rememberMeServices = null;

    @ManagedProperty(value = "#{customJDBCUserService}")
    private UserDetailsService userDetailsService = null;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    public String login() {
	Authentication result = null;
	RequestContext context = RequestContext.getCurrentInstance();
	try {
	    if ("TRUE".equalsIgnoreCase(this.getRecordar())) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(getUsuario());
		RememberMeAuthenticationToken rememberMeAuthenticationToken = new RememberMeAuthenticationToken(
			"jsfspring-sec", userDetails, userDetails.getAuthorities());
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
			.getExternalContext().getRequest();
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
			.getExternalContext().getResponse();
		rememberMeServices.loginSuccess(httpServletRequest, httpServletResponse, rememberMeAuthenticationToken);
		result = rememberMeAuthenticationToken;
		usuarioLogueado = getUsuarioService().findByNombreUsuario(usuario);
	    } else {
		Authentication request = new UsernamePasswordAuthenticationToken(this.getUsuario(), this.getPassword());
		result = authenticationManager.authenticate(request);
		usuarioLogueado = getUsuarioService().findByNombreUsuario(usuario);
	    }
	    SecurityContextHolder.getContext().setAuthentication(result);
	    setLogueado(true);
	    if (context != null) {
		context.addCallbackParam("logueado", logueado);
	    }
	    setRol(result.getAuthorities().toString());
	    if (ROLE_ADMIN.equals(result.getAuthorities().toString())) {
		return "SecuredAdmin";
	    } else if (ROLE_AUDITOR.equals(result.getAuthorities().toString())) {
		return "SecuredAuditor";
	    } else if (ROLE_CONTADOR.equals(result.getAuthorities().toString())) {
		return "SecuredContador";
	    } else if (ROLE_PLAYA_GERENTE.equals(result.getAuthorities().toString())) {
		return "SecuredGerentePlaya";
	    } else if (ROLE_USER.equals(result.getAuthorities().toString())) {
		return "SecuredUser";
	    } else if (ROLE_PLAYA_EMPLEADO.equals(result.getAuthorities().toString())) {
		return "SecuredEmpleadoPlaya";
	    } else if (ROLE_CLIENT.equals(result.getAuthorities().toString())) {
		return "SecuredClient";
	    } else {
		return "SecuredClient";
	    }
	} catch (AuthenticationException e) {
	    e.printStackTrace();
	    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
		    "Usuario o contraseña incorrecto", "");
	    FacesContext.getCurrentInstance().addMessage("", fm);
	    if (context != null) {
		context.addCallbackParam("logueado", logueado);
	    }
	    return "UnSecured";
	}
    }

    public String logout() {
	SecurityContextHolder.clearContext();
	// Delete Cookies
	HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
		.getExternalContext().getRequest();
	HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
		.getExternalContext().getResponse();
	Cookie cookie = new Cookie("SPRING_SECURITY_REMEMBER_ME_COOKIE", null);
	cookie.setMaxAge(0);
	cookie.setPath(httpServletRequest.getContextPath().length() > 0 ? httpServletRequest.getContextPath() : "/");
	httpServletResponse.addCookie(cookie);
	setLogueado(false);
	setRol(null);
	setUsuario(null);
	return "LoggedOut";
	// return "index?faces-redirect=true";
    }

    public String cancel() {
	return "index";
    }

    public AuthenticationManager getAuthenticationManager() {
	return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
	this.authenticationManager = authenticationManager;
    }

    public RememberMeServices getRememberMeServices() {
	return rememberMeServices;
    }

    public void setRememberMeServices(RememberMeServices rememberMeServices) {
	this.rememberMeServices = rememberMeServices;
    }

    public UserDetailsService getUserDetailsService() {
	return userDetailsService;
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

    public void setUserDetailsService(UserDetailsService userDetailsService) {
	this.userDetailsService = userDetailsService;
    }

    public String getUsuario() {
	return usuario;
    }

    public void setUsuario(String usuario) {
	this.usuario = usuario;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getRecordar() {
	return recordar;
    }

    public void setRecordar(String recordar) {
	this.recordar = recordar;
    }

    public String getRol() {
	return rol;
    }

    public void setRol(String rol) {
	this.rol = rol;
    }

    public Boolean getLogueado() {
	return logueado;
    }

    public void setLogueado(Boolean logueado) {
	this.logueado = logueado;
    }

    /**
     * @return the usuarioLogueado
     */
    public Usuario getUsuarioLogueado() {
	return usuarioLogueado;
    }

    /**
     * @param usuarioLogueado
     *            the usuarioLogueado to set
     */
    public void setUsuarioLogueado(Usuario usuarioLogueado) {
	this.usuarioLogueado = usuarioLogueado;
    }

}