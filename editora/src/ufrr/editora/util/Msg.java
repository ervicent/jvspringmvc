package ufrr.editora.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Msg {
	
	public static void addMsgInfo(String msg){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg, ""));
	}
	
	public static void addMsgWarn(String msg){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,msg, ""));
	}
	
	public static void addMsgError(String msg){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,msg, ""));
	}
	
	public static void addMsgFatal(String msg){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,msg, ""));
	}	

}
