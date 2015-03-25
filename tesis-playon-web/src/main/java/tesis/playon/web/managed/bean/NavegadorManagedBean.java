package tesis.playon.web.managed.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * 
 * @author gmorales
 * 
 */
@ManagedBean(name = "navegadorMB")
@SessionScoped
public class NavegadorManagedBean {

    private static final String TO_SECURE_KEY = "ToSecure";

    private static final String TO_UN_SECURE_KEY = "ToUnSecure";

    private String paginaParaNavegar;

    public String navegarA() {
	if (TO_SECURE_KEY.equalsIgnoreCase(paginaParaNavegar)) {
	    return "Secured";
	} else if (TO_UN_SECURE_KEY.equalsIgnoreCase(paginaParaNavegar)) {
	    return "UnSecured";
	} else {
	    return "none";
	}
    }

    public String getPaginaParaNavegar() {
	return paginaParaNavegar;
    }

    public void setPaginaParaNavegar(String paginaParaNavegar) {
	this.paginaParaNavegar = paginaParaNavegar;
    }

}