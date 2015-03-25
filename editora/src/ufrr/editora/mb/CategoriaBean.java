package ufrr.editora.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import ufrr.editora.dao.DAO;
import ufrr.editora.entity.Categoria;
import ufrr.editora.entity.Usuario;
import ufrr.editora.util.Msg;
import ufrr.editora.validator.Validator;

@ManagedBean
@ViewScoped
public class CategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;
	
	private Categoria categoria = new Categoria();
	private List<Categoria> categorias;
	private List<Categoria> categoriasD;
	private DAO<Categoria> dao = new DAO<Categoria>(Categoria.class);
	private Validator<Categoria> validator;
	private String box4Search;
	
	@PostConstruct
	public void init() {

		categoria = new Categoria();
		validator = new Validator<Categoria>(Categoria.class);
		box4Search = "nome";
	}


	/** Lista de categorias **/
	
	public List<Categoria> getCategorias() {
		if (categorias == null) {
			System.out.println("Carregando categorias...");
			categorias = new DAO<Categoria>(Categoria.class).getAllOrder("nome");
		}
		return categorias;
	}
	
	// Exibe uma lista com as categorias
		public List<Categoria> getCategorias2() {
			categoriasD = new ArrayList<Categoria>();
			List<Categoria> c = new ArrayList<Categoria>();
			c = this.getCategorias();
			for (int i = 0; i < c.size(); i++) {
				if (c.get(i).getNome()!=null) {
					categoriasD.add(c.get(i));
				}
			}
			return categoriasD;
		}
	
	/** actions **/

	// Cadastra categoria
	public String addCategoria() {
		try {
			boolean all = true;
			if (!validarNomeUK_nome()) {
				all = false;
				Msg.addMsgError("ESTA CATEGORIA JA TEM REGISTRO NO SISTEMA");
			}
			if (!all) {
				System.out.println("...Erro ao cadastrar categoria: nome ja existe");
				return null;
			} else {	
				this.getCategoria().setUsuario(this.loginBean.getUsuario());
				DAO<Usuario> UDao = new DAO<Usuario>(Usuario.class);
				Usuario u = UDao.buscaPorId(this.loginBean.getUsuario().getId());
				u.getCategorias().add(categoria);
				dao.adiciona(categoria);
				this.categoria = new Categoria();
				init();
				Msg.addMsgInfo("CADASTRO EFETUADO COM SUCESSO");
				System.out.println("...cadastro efetuado com sucesso!");
			}
		} catch (Exception e) {
			init();
			e.printStackTrace();
			System.out
					.println("...Alguma coisa deu errada ao cadastrar categoria");
		}
		categorias = dao.getAllOrder("nome");
		return null;
	}
	
	/** valida��o UK nome da categoria */

	public boolean validarNomeUK_nome() {
		return validator.validarNomeUK("nome", categoria.getNome());
	}

	public void checkNomeUK_nome(AjaxBehaviorEvent event) {
		if (validarNomeUK_nome()) {
			if (categoria.getNome().isEmpty()) {
				validator.setResultNome("");
			}
		}
	}
	
	// AutoComplete nome
	public List<String> autoComplete(String nome) {
		List<Categoria> array = dao.getAllByName("nome", nome);
		ArrayList<String> nomes = new ArrayList<String>();
		for (int i = 0; i < array.size(); i++) {
			nomes.add(array.get(i).getNome());
		}
		return nomes;
	}
	
	/** getters and setters **/

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Categoria> getCategoriasD() {
		return categoriasD;
	}

	public void setCategoriasD(List<Categoria> categoriasD) {
		this.categoriasD = categoriasD;
	}

	public DAO<Categoria> getDao() {
		return dao;
	}

	public void setDao(DAO<Categoria> dao) {
		this.dao = dao;
	}

	public Validator<Categoria> getValidator() {
		return validator;
	}

	public void setValidator(Validator<Categoria> validator) {
		this.validator = validator;
	}

	public String getBox4Search() {
		return box4Search;
	}

	public void setBox4Search(String box4Search) {
		this.box4Search = box4Search;
	}
	
	public LoginBean getLoginBean() {
		return loginBean;
	}


	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

}
