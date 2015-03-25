package ufrr.editora.entity;

import java.io.Serializable;

import javax.faces.bean.SessionScoped;

@SessionScoped
public class UsuarioLogado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	public boolean isLogado() {
		return usuario != null;
	}
		
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void guardaUsuarioLogado(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
