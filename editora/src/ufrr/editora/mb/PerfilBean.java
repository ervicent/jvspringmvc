package ufrr.editora.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ufrr.editora.dao.DAO;
import ufrr.editora.entity.Perfil;

@ManagedBean
@ViewScoped
public class PerfilBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Perfil perfil = new Perfil();
	private List<Perfil> perfis;
	private List<Perfil> perfil5;
	private DAO<Perfil> dao = new DAO<Perfil>(Perfil.class);

	/** Lista Tipos de Produto **/

	public List<Perfil> getPerfis() {
		if (perfis == null) {
			System.out.println("Carregando perfis...");
			perfis = new DAO<Perfil>(Perfil.class).getAllOrder("id");
		}
		return perfis;
	}

	// Exibe uma lista com o id == 4(cliente)
	public List<Perfil> getPerfilCliente() {
		perfil5 = new ArrayList<Perfil>();
		List<Perfil> pf = new ArrayList<Perfil>();
		pf = this.getPerfis();
		for (int i = 0; i < pf.size(); i++) {
			if (pf.get(i).getPerfil().equalsIgnoreCase("cliente")) {
				System.out.println("...Total de Perfil: " + getPerfis().size());
				perfil5.add(pf.get(i));
			}
		}
		return perfil5;
	}

	// Exibe uma lista com o id != 4(cliente)
	public List<Perfil> getSolicitacaoUsuario() {
		perfil5 = new ArrayList<Perfil>();
		List<Perfil> pf = new ArrayList<Perfil>();
		pf = this.getPerfis();
		for (int i = 0; i < pf.size(); i++) {
			if (pf.get(i).getId() != 4) {
				System.out.println("...Total de Perfil diferente de cliente: "
						+ getPerfis().size());
				perfil5.add(pf.get(i));
			}
		}
		return perfil5;
	}

	// Exibe uma lista com o id != 4 e sem o id administrador
	public List<Perfil> getGerente() {
		perfil5 = new ArrayList<Perfil>();
		List<Perfil> pf = new ArrayList<Perfil>();
		pf = this.getPerfis();
		for (int i = 0; i < pf.size(); i++) {
			if (pf.get(i).getId() != 4 && pf.get(i).getId() != 1) {
				perfil5.add(pf.get(i));
			}
		}
		return perfil5;
	}

	/** get and set **/

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Perfil> getPerfil5() {
		return perfil5;
	}

	public void setPerfil5(List<Perfil> perfil5) {
		this.perfil5 = perfil5;
	}

	public DAO<Perfil> getDao() {
		return dao;
	}

	public void setDao(DAO<Perfil> dao) {
		this.dao = dao;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

}
