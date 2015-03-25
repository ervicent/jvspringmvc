package ufrr.editora.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import ufrr.editora.dao.DAO;
import ufrr.editora.entity.TipoProduto;
import ufrr.editora.entity.Usuario;
import ufrr.editora.util.Msg;

@ManagedBean
@ViewScoped
public class TipoProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	private TipoProduto tipo = new TipoProduto();
	private List<TipoProduto> tipos;
	private List<TipoProduto> tipo1;
	private DAO<TipoProduto> dao = new DAO<TipoProduto>(TipoProduto.class);
	public Boolean cadastro = true;

	/** Lista Tipos de Produto **/

	public List<TipoProduto> getTipos() {
		if (tipos == null) {
			System.out.println("Carregando tipo de produto...");
			tipos = new DAO<TipoProduto>(TipoProduto.class).getAllOrder("nome");
		}
		return tipos;
	}

	// Exibe uma lista com o id == 1
	public List<TipoProduto> getId1() {
		tipo1 = new ArrayList<TipoProduto>();
		List<TipoProduto> tp = new ArrayList<TipoProduto>();
		tp = this.getTipos();
		for (int i = 0; i < tp.size(); i++) {
			if (tp.get(i).getId() == 1) {
				tipo1.add(tp.get(i));
			}
		}
		return tipo1;
	}

	// Exibe uma lista com o id != 1
	public List<TipoProduto> getOutros() {
		tipo1 = new ArrayList<TipoProduto>();
		List<TipoProduto> tp = new ArrayList<TipoProduto>();
		tp = this.getTipos();
		for (int i = 0; i < tp.size(); i++) {
			if (tp.get(i).getId() != 1) {
				tipo1.add(tp.get(i));
			}
		}
		return tipo1;
	}

	/** actions **/

	public void addTipoProduto() {
		for (TipoProduto tipos : this.getTipos()) {
			if (tipos.getNome().equalsIgnoreCase(this.getTipo().getNome())) {
				this.cadastro = false;
				break;
			}
		}
		if (this.cadastro == true) {
			if (this.tipo.getNome().length() > 31) {
				Msg.addMsgError("NOME DO TIPO DE PRODUTO PASSOU DO TAMANHO PERMITIDO, RETIRE ALGUNS CARACTERES");
			} else {
				if (tipo.getId() == null) {
					this.getTipo().setUsuario(this.loginBean.getUsuario());
					DAO<Usuario> UDao = new DAO<Usuario>(Usuario.class);
					Usuario u = UDao.buscaPorId(this.loginBean.getUsuario().getId());
					u.getTipos().add(tipo);
					Msg.addMsgInfo("CADASTRO REALIZADO COM SUCESSO");
					dao.adiciona(tipo);
					this.tipo = new TipoProduto();
				} else {
					Msg.addMsgInfo("CADASTRO REALIZADO COM SUCESSO");
					dao.atualiza(tipo);
				}
			}
		} else {
			Msg.addMsgError("ESTE NOME JA FOI REGISTRADO");
		}
		tipos = dao.getAllOrder("nome");
		this.cadastro = true;
	}

	/** get and set **/

	public TipoProduto getTipo() {
		return tipo;
	}

	public void setTipo(TipoProduto tipo) {
		this.tipo = tipo;
	}

	public void setTipos(List<TipoProduto> tipos) {
		this.tipos = tipos;
	}

	public DAO<TipoProduto> getDao() {
		return dao;
	}

	public void setDao(DAO<TipoProduto> dao) {
		this.dao = dao;
	}

	public Boolean getCadastro() {
		return cadastro;
	}

	public void setCadastro(Boolean cadastro) {
		this.cadastro = cadastro;
	}

	public List<TipoProduto> getTipo1() {
		return tipo1;
	}

	public void setTipo1(List<TipoProduto> tipo1) {
		this.tipo1 = tipo1;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	

}
