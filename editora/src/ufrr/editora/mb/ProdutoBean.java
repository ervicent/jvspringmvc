package ufrr.editora.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;

import ufrr.editora.dao.DAO;
import ufrr.editora.entity.Produto;
import ufrr.editora.entity.Usuario;
import ufrr.editora.util.Msg;
import ufrr.editora.validator.Validator;

@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	private Produto produto = new Produto();
	private List<Produto> produtos;
	private List<Produto> produtos1;
	private DAO<Produto> dao = new DAO<Produto>(Produto.class);
	private Validator<Produto> validator;
	private String search;
	private Integer box4Search;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long autoIncrement;

	@PostConstruct
	public void init() {

		produto = new Produto();
		validator = new Validator<Produto>(Produto.class);
		search = "";
	}

	/** List Products **/

	public List<Produto> getProdutos() {
		if (produtos == null) {
			System.out.println("Carregando produtos...");
			produtos = new DAO<Produto>(Produto.class).getAllOrder("tipo.nome, nome, ativado");
			System.out.println("Total de Produto Cadastrados: " + getProdutos().size());
		}
		return produtos;
	}

	// lista todos os produtos ativados
	public List<Produto> getAtivados() {
		produtos1 = new ArrayList<Produto>();
		List<Produto> ps = new ArrayList<Produto>();
		ps = this.getProdutos();
		for (int i = 0; i < ps.size(); i++) {
			if (ps.get(i).getAtivado().equals(true)) {
				produtos1.add(ps.get(i));
			}
		}
		return produtos1;
	}

	// Lista de livros ativados
	public List<Produto> getLivrosAtivados() {
		produtos1 = new ArrayList<Produto>();
		List<Produto> ps = new ArrayList<Produto>();
		ps = this.getProdutos();
		for (int i = 0; i < ps.size(); i++) {
			if (ps.get(i).getAtivado().equals(true)
					&& ps.get(i).getTipo().getId() == 1) {
				produtos1.add(ps.get(i));
			}
		}
		return produtos1;
	}

	// Lista de produtos ativados (sem livros)
	public List<Produto> getProdutosAtivados() {
		produtos1 = new ArrayList<Produto>();
		List<Produto> ps = new ArrayList<Produto>();
		ps = this.getProdutos();
		for (int i = 0; i < ps.size(); i++) {
			if (ps.get(i).getAtivado().equals(true)
					&& ps.get(i).getTipo().getId() != 1) {
				produtos1.add(ps.get(i));
			}
		}
		return produtos1;
	}

	// Lista de livros desativados
	public List<Produto> getLivrosDesativados() {
		produtos1 = new ArrayList<Produto>();
		List<Produto> ps = new ArrayList<Produto>();
		ps = this.getProdutos();
		for (int i = 0; i < ps.size(); i++) {
			if (ps.get(i).getAtivado().equals(false)
					&& ps.get(i).getTipo().getId() == 1) {
				produtos1.add(ps.get(i));
			}
		}
		return produtos1;
	}

	// Lista de produtos desativados (sem livros)
	public List<Produto> getProdutosDesativados() {
		produtos1 = new ArrayList<Produto>();
		List<Produto> ps = new ArrayList<Produto>();
		ps = this.getProdutos();
		for (int i = 0; i < ps.size(); i++) {
			if (ps.get(i).getAtivado().equals(false)
					&& ps.get(i).getTipo().getId() != 1) {
				produtos1.add(ps.get(i));
			}
		}
		return produtos1;
	}

	// Exibe uma lista com o id tipoProduto == 1
	public List<Produto> getTiposId1() {
		produtos1 = new ArrayList<Produto>();
		for (Produto p : this.getProdutos()) {
			if (p.getTipo().getId() == 1 && p.getAtivado().equals(true)) {
				produtos1.add(p);
			}
		}
		return produtos1;
	}

	// Exibe uma lista com o id tipoProduto != 1
	public List<Produto> getTipoOutros() {
		produtos1 = new ArrayList<Produto>();
		for (Produto p : this.getProdutos()) {
			if (p.getTipo().getId() != 1 && p.getAtivado().equals(true)) {
				produtos1.add(p);
			}
		}
		return produtos1;
	}
	
	@SuppressWarnings("unchecked")
	public String getProdutoByIsbn() {

		try {
			Query query = dao.query("SELECT p FROM Produto p WHERE p.isbn=?");
			query.setParameter(1, produto.getIsbn());
			produtos = query.getResultList();
			if (produtos.isEmpty()) {
				init();
				Msg.addMsgError("NENHUM REGISTRO ENCONTRADO");
				produtos = query.getResultList();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String getProdutoById() {

		try {
			Query query = dao.query("SELECT p FROM Produto p WHERE p.id=? and p.isbn is null");
			query.setParameter(1, produto.getId());
			produtos = query.getResultList();
			if (produtos.isEmpty()) {
				init();
				Msg.addMsgError("NENHUM REGISTRO ENCONTRADO");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Pesquisa produtos pela descri��o e isbn
	public String getListaProdutos() {

		if (box4Search.equals(1)) {
			if (search.contains("'") || search.contains("@")
					|| search.contains("/") || search.contains("*")) {
				init();
				Msg.addMsgError("CONTEM CARACTERES(ES) INVALIDO(S)");
				return null;
			} else {
				if (search.length() <= 4) {
					init();
					Msg.addMsgError("INFORME PELO MENOS 5 CARACTERES");
					return null;
				} else {
					produtos = dao.getAllByName("obj.nome", search);
					if (getAtivados().isEmpty()) {
						init();
						Msg.addMsgError("NENHUM REGISTRO ENCONTRADO");
					} else {
						return null;
					}
				}
			}

		}
		else if (box4Search.equals(4)) {
			if (search.contains("'") || search.contains("@")
					|| search.contains("/") || search.contains("*")) {
				init();
				Msg.addMsgError("CONTEM CARACTER(ES) INVALIDO(S)");
				return null;
		}else {
			if (search.length() <= 4) {
				init();
				Msg.addMsgError("INFORME PELO MENOS 5 CARACTERES");
				return null;
			} else {
				produtos = dao.getAllByName("obj.autor", search);
				if (getAtivados().isEmpty()) {
					init();
					Msg.addMsgError("NENHUM REGISTRO ENCONTRADO");
				} else {
					return null;
					}
				}
			}
		}
		return null;
	}

	/** actions **/

	// Cadastrar livro
	public String addProduto() {
		try {
			boolean all = true;
			if (!validarNome_editora()) {
				all = false;
				Msg.addMsgError("CAMPO EDITORA NAO PODE SER VAZIO");
			}
			if (!validarNome_nome()) {
				all = false;
				Msg.addMsgError("CAMPO TITULO DA OBRA NAO PODE SER VAZIO");
			}
			if (!validarIntegerUK_isbn()) {
				all = false;
				Msg.addMsgError("ESTA OBRA JA FOI REGISTRADA");
			}
			if (!all) {
				System.out
						.println("...Erro ao cadastrar obra: produto ja existe");
				return null;
			} else {
				this.getProduto().setUsuario(this.loginBean.getUsuario());
				DAO<Usuario> UDao = new DAO<Usuario>(Usuario.class);
				Usuario u = UDao.buscaPorId(this.loginBean.getUsuario().getId());
				u.getProdutos().add(produto);
				produto.setAtivado(true);
				dao.adiciona(produto);
				this.produto = new Produto();
				init();
				Msg.addMsgInfo("CADASTRO EFETUADO COM SUCESSO");
				System.out
						.println("...cadastro de produto efetuado com sucesso!");
			}
		} catch (Exception e) {
			init();
			e.printStackTrace();
			System.out
					.println("...Alguma coisa deu errada ao cadastrar produto");
		}
		return null;
	}

	// Cadastrar produto do tipo Outros
	public String addOutros() {
		try {
			boolean all = true;
			if (!validarNameUK_nome()) {
				all = false;
				Msg.addMsgError("UM PRODUTO COM A MESMA DESCRICAO JA FOI REGISTRADO NO SISTEMA");
				return null;
			}
			if (!all) {
				System.out.println("...Erro ao cadastrar produto: produto ja existe");
				return null;
			} else {
				this.getProduto().setUsuario(this.loginBean.getUsuario());
				DAO<Usuario> UDao = new DAO<Usuario>(Usuario.class);
				Usuario u = UDao.buscaPorId(this.loginBean.getUsuario().getId());
				u.getProdutos().add(produto);
				produto.setAtivado(true);
				
				produto.setIsbn((long)(getProdutos().size()+1));
				dao.adiciona(produto);
				this.produto = new Produto();
				init();
				Msg.addMsgInfo("CADASTRO EFETUADO COM SUCESSO");
				System.out.println("...cadastro de produto efetuado com sucesso!");
				
				return "/pages/produto/cadastrarProdutoOutros.xhtml";
			}
		} catch (Exception e) {
			init();
			e.printStackTrace();
			System.out
					.println("...Alguma coisa deu errada ao cadastrar produto");
		}
		return null;
	}

	// Edita dados do produto tipo livro
	public String alterProduto() {
		try {
			boolean all = true;
			if (!validarNome_editora()) {
				all = false;
				Msg.addMsgError("CAMPO EDITORA NAO PODE SER VAZIO");
			}
			if (!validarNome_nome()) {
				all = false;
				Msg.addMsgError("CAMPO TITULO DA OBRA NAO PODE SER VAZIO");
			}
			if (!all) {
				System.out
						.println("...Erro ao cadastrar produto: este produto ja existe");
				return null;
			} else {
				dao.atualiza(produto);
				this.produto = new Produto();
				init();
				Msg.addMsgInfo("DADOS DA OBRA ALTERADO COM SUCESSO");
				System.out.println("...produto editado com sucesso!");
			}
		} catch (Exception e) {
			init();
			e.printStackTrace();
			System.out.println("...Alguma coisa deu errada ao editar produto");
		}
		return null;
	}

	// Edita dados do produto tipo outros
	public String alterOutros() {
		try {
			boolean all = true;
			if (!validarNome_nome()) {
				all = false;
				Msg.addMsgError("CAMPO DESCRICAO NAO PODE SER VAZIO");
			}
			if (!all) {
				System.out
						.println("...Erro ao cadastrar produto: nome nao pode ser vazio");
				return null;
			} else {
				dao.atualiza(produto);
				this.produto = new Produto();
				init();
				Msg.addMsgInfo("DADOS DO PRODUTO ALTERADO COM SUCESSO");
				System.out.println("...produto editado com sucesso!");
			}
		} catch (Exception e) {
			init();
			e.printStackTrace();
			System.out.println("...Alguma coisa deu errada ao editar produto");
		}
		return null;
	}

	// ativar/desativar produto
	public void alterStatus() {
		if (produto.getAtivado()==true) {
			Msg.addMsgInfo("PRODUTO DESATIVADO");
			produto.setAtivado(false);
			dao.atualiza(produto);
		}else {
			Msg.addMsgInfo("PRODUTO REATIVADO");
			produto.setAtivado(true);
			dao.atualiza(produto);
		}
	}

	/** ajax */

	public void checkBox4Search(AjaxBehaviorEvent event) {

	}

	public void checkEditora(AjaxBehaviorEvent event) {
		validarEditora();
		if (produto.getEditora().isEmpty()) {
			validator.setResultNome("");
		}
	}

	/** validacao UK ISBN */

	public boolean validarIntegerUK_isbn() {
		return validator.validarIntegerUK("isbn", produto.getIsbn());
	}

	public void checkNomeUK_nome(AjaxBehaviorEvent event) {
		if (validarNameUK_nome()) {
			if (produto.getNome().isEmpty()) {
				validator.setResultNome("");
			}
		}
	}

	/** validacao UK ISBN */

	public boolean validarNameUK_nome() {
		return validator.validarNomeUK("nome", produto.getNome());
	}

	public void checkNomeUK_descricao(AjaxBehaviorEvent event) {
		if (validarNameUK_nome()) {
			if (produto.getEditora().isEmpty()) {
				validator.setResultNome("");
			}
		}
	}

	/** validacao EMPTY */

	public boolean validarNome() {
		return validator.validarNome(produto.getNome());
	}

	public boolean validarEditora() {
		return validator.validarNome(produto.getEditora());
	}

	/** autocomplete **/
	
//	public List<Long> autocompleteIsbn(Long numero) {
//		List<Produto> array = dao.getAllByLong("isbn", numero);
//		ArrayList<Long> ns = new ArrayList<Long>();
//		for (int i = 0; i < array.size(); i++) {
//			ns.add(array.get(i).getIsbn());
//		}
//		return ns;
//	}

	public List<String> autocomplete(String nome) {
		List<Produto> array = dao.getAllByName("nome", nome);
		ArrayList<String> nomes = new ArrayList<String>();
		for (int i = 0; i < array.size(); i++) {
			nomes.add(array.get(i).getNome());
		}
		return nomes;
	}

	public List<String> autocompleteautor(String nome) {
		List<Produto> array = dao.getAllByName("autor", nome);
		ArrayList<String> nomes = new ArrayList<String>();
		for (int i = 0; i < array.size(); i++) {
			nomes.add(array.get(i).getAutor());
		}
		return nomes;
	}

	public List<String> autocompleteeditora(String nome) {
		List<Produto> array = dao.getAllByName("editora", nome);
		ArrayList<String> nomes = new ArrayList<String>();
		for (int i = 0; i < array.size(); i++) {
			nomes.add(array.get(i).getEditora());
		}
		return nomes;
	}
	
	// AutoComplete de nome e isbn

		public List<String> autocompleteNome(String nome) {
			ArrayList<String> nomes = new ArrayList<String>();
			if (box4Search.equals(2)) {
				if (!search.contains("'")) {
					List<Produto> array = dao.getAllByName("nome", nome);
					for (int i = 0; i < array.size(); i++) {
						nomes.add(array.get(i).getNome());
					}
				}
			} else if (box4Search.equals(1)) {
				if (!search.contains("'")) {
					List<Produto> array = dao.getAllByName("editora", nome);
					for (int i = 0; i < array.size(); i++) {
						nomes.add(array.get(i).getEditora());
					}
				}
			}
			return nomes;
		}

	/** Get and Set **/

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public DAO<Produto> getDao() {
		return dao;
	}

	public void setDao(DAO<Produto> dao) {
		this.dao = dao;
	}

	public List<Produto> getProdutos1() {
		return produtos1;
	}

	public void setProdutos1(List<Produto> produtos1) {
		this.produtos1 = produtos1;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Validator<Produto> getValidator() {
		return validator;
	}

	public void setValidator(Validator<Produto> validator) {
		this.validator = validator;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Integer getBox4Search() {
		return box4Search;
	}

	public Long getAutoIncrement() {
		return autoIncrement;
	}

	public void setAutoIncrement(Long autoIncrement) {
		this.autoIncrement = autoIncrement;
	}

	public void setBox4Search(Integer box4Search) {
		this.box4Search = box4Search;
	}

	public void checkNome_editora(AjaxBehaviorEvent event) {
		validarNome_editora();
	}

	public boolean validarNome_editora() {
		return validator.validarNome(produto.getEditora());
	}

	public void checkNome_nome(AjaxBehaviorEvent event) {
		validarNome_nome();
	}

	public boolean validarNome_nome() {
		return validator.validarNome(produto.getNome());
	}

}
