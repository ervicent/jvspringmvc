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
import ufrr.editora.entity.Endereco;
import ufrr.editora.entity.Fornecedor;
import ufrr.editora.entity.Usuario;
import ufrr.editora.util.Msg;
import ufrr.editora.validator.Validator;

@ManagedBean
@ViewScoped
public class FornecedorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	private Fornecedor fornecedor = new Fornecedor();
	private Endereco endereco = new Endereco();
	private List<Fornecedor> fornecedores;
	private List<Fornecedor> fornecedoresD;
	private DAO<Fornecedor> dao = new DAO<Fornecedor>(Fornecedor.class);
	private Validator<Fornecedor> validator;
	private String search;
	private Integer box4Search;

	@PostConstruct
	public void init() {

		fornecedor = new Fornecedor();
		endereco = new Endereco();
		validator = new Validator<Fornecedor>(Fornecedor.class);
		search = "";
		box4Search = 1;
		box4Search = 2;
	}

	/** Lista de Fornecedores **/

	public List<Fornecedor> getFornecedores() {
		if (fornecedores == null) {
			System.out.println("Carregando Fornecedores...");
			fornecedores = new DAO<Fornecedor>(Fornecedor.class).getAllOrder("nome");
		}
		return fornecedores;
	}

	// Lista de fornecedores com CPF
	public List<Fornecedor> getCpfs() {
		fornecedoresD = new ArrayList<Fornecedor>();
		List<Fornecedor> fs = new ArrayList<Fornecedor>();
		fs = this.getFornecedores();
		for (int i = 0; i < fs.size(); i++) {
			if (fs.get(i).getCnpj().length() == 14) {
				fornecedoresD.add(fs.get(i));
			}
		}
		return fornecedoresD;
	}

	// Lista de fornecedores com CNPJ
	public List<Fornecedor> getCnpjs() {
		fornecedoresD = new ArrayList<Fornecedor>();
		List<Fornecedor> fs = new ArrayList<Fornecedor>();
		fs = this.getFornecedores();
		for (int i = 0; i < fs.size(); i++) {
			if (fs.get(i).getCnpj().length() == 18) {
				fornecedoresD.add(fs.get(i));
			}
		}
		return fornecedoresD;
	}

	// Lista de todos os fornecedores
	public List<Fornecedor> getListaFornecedores() {
		fornecedoresD = new ArrayList<Fornecedor>();
		List<Fornecedor> fs = new ArrayList<Fornecedor>();
		fs = this.getFornecedores();
		for (int i = 0; i < fs.size(); i++) {
			if (fs.get(i).getId() != null) {
				fornecedoresD.add(fs.get(i));
			}
		}
		return fornecedoresD;
	}

	// Pesquisa Fornecedor pelo nome e cnpj/cpf
	public String getListaFornecedorByName() {

		if (box4Search.equals(1)) {
			if (search.contains("'") || search.contains("@")
					|| search.contains("/") || search.contains("*")) {
				init();
				Msg.addMsgError("Contem caracter(es) invalido(s)");
				return null;
			} else {
				if (search.length() <= 4) {
					init();
					Msg.addMsgError("INFORME PELO MENOS 5 CARACTERES");
					return null;
				} else {
					fornecedores = dao.getAllByName("obj.nome", search);
					if (fornecedores.isEmpty()) {
						init();
						Msg.addMsgError("NENHUM REGISTRO ENCONTRADO");
					} else {
						return null;
					}
				}
			}
		}
			else if (box4Search.equals(2)) {
				if (search.length() != 18) {
					init();
					Msg.addMsgError("INFORME O CNPJ CORRETO");
					return null;
				} else {
					fornecedores = dao.getAllByName("obj.cnpj", search);
					if (fornecedores.isEmpty()) {
						init();
						Msg.addMsgError("NENHUM REGISTRO ENCONTRADO");
					} else {
						return null;
					}
				}
		} else {
			if (search.length() != 14) {
				init();
				Msg.addMsgError("INFORME O CPF CORRETO");
				return null;
			} else {
				fornecedores = dao.getAllByName("obj.cnpj", search);
				if (fornecedores.isEmpty()) {
					init();
					Msg.addMsgError("NENHUM REGISTRO ENCONTRADO");
				} else {
					return null;
				}
			}
		}
		return null;
	}

	/** Autocompletes **/

	// AutoComplete de nome e cnpj

	public List<String> autocomplete(String nome) {
		ArrayList<String> nomes = new ArrayList<String>();
		if (box4Search.equals(1)) {
			if (!search.contains("'")) {
				List<Fornecedor> array = dao.getAllByName("nome", nome);
				for (int i = 0; i < array.size(); i++) {
					nomes.add(array.get(i).getNome());
				}
			}
		} else if (box4Search.equals(2)) {
			if (!search.contains("'")) {
				List<Fornecedor> array = dao.getAllByName("cnpj_cpf", nome);
				for (int i = 0; i < array.size(); i++) {
					nomes.add(array.get(i).getCnpj());
				}
			}
		}
		return nomes;
	}
	
	// AutoComplete de nome
	public List<String> autocompleteFornecedor(String nome) {
		List<Fornecedor> array = dao.getAllByName("nome", nome);
		ArrayList<String> nomes = new ArrayList<String>();
		for (int i = 0; i < array.size(); i++) {
			nomes.add(array.get(i).getNome());
		}
		return nomes;
	}

	/** actions **/

	// Cadastra fornecedor
	public String addFornecedor() {
		try {
			boolean all = true;
			if (!validarNomeUK_nome()) {
				all = false;
				Msg.addMsgError("NOME JA REGISTRADO NO SISTEMA, TENTE OUTRO.");
			}
			if (!validarNomeUK_cnpj()) {
				all = false;
				Msg.addMsgError("Nº DO CPF OU CNPJ JA POSSUI REGISTRO NO SISTEMA");
			}
//			if (!validarNome_agencia()) {
//				all = false;
//				Msg.addMsgError("Agencia n�o pode ser vazio");
//			}
//			if (!validarNome_conta()) {
//				all = false;
//				Msg.addMsgError("N�mero da conta n�o pode ser vazio");
//			}
//			if (!validarNome_titular()) {
//				all = false;
//				Msg.addMsgError("Titular da conta n�o pode ser vazio");
//			}
			if (fornecedor.getEmail().equalsIgnoreCase(getFornecedor().getEmail2())
				|| fornecedor.getEmail().equalsIgnoreCase(getFornecedor().getEmail3())) {
				all = false;
				Msg.addMsgError("ENDERECO DE EMAIL NAO PODE SE REPETIR");
			}
			if (!all) {
				System.out.println("...Erro ao cadastrar fornecedor: dados faltam ser preenchidos ou fornecedor ja existe");
				return "/pages/forncedor/cadastrarFornecedor.xhtml";
			} else {					
				if (fornecedor.getBanco().equals(3) && fornecedor.getOperacao()==null
						|| fornecedor.getBanco().equals(3) && fornecedor.getOperacao()==null) {
					Msg.addMsgError("Campo Op nao pode ser vazio");
					return null;	
				}	
					if (fornecedor.getBanco().equals(1) && fornecedor.getOperacao()!=null
							|| fornecedor.getBanco().equals(2) && fornecedor.getOperacao()!=null
							|| fornecedor.getBanco().equals(4) && fornecedor.getOperacao()!=null
							|| fornecedor.getBanco().equals(5) && fornecedor.getOperacao()!=null) {
						Msg.addMsgError("Campo Op serve somente para a opcao de banco Caixa");
						return null;	
				}else
				this.getFornecedor().setUsuario(this.loginBean.getUsuario());
				DAO<Usuario> UDao = new DAO<Usuario>(Usuario.class);
				Usuario u = UDao.buscaPorId(this.loginBean.getUsuario().getId());
				u.getFornecedores().add(fornecedor);
				fornecedor.setEndereco(endereco);
				dao.adiciona(fornecedor);
				this.endereco = new Endereco();
				this.fornecedor = new Fornecedor();
				init();
				Msg.addMsgInfo("CADASTRO EFETUADO COM SUCESSO");
				System.out.println("...cadastro efetuado com sucesso!");
			}
		} catch (Exception e) {
			init();
			e.printStackTrace();
			System.out
					.println("...Alguma coisa deu errada ao cadastrar fornecedor");
		}
		return null;
	}

	// metodo para alterar o fornecedor
	public String alterFornecedor() {
		try {
			boolean all = true;
//			if (!validarNome_agencia()) {
//				all = false;
//				Msg.addMsgError("Agencia n�o pode ser vazio");
//			}
//			if (!validarNome_conta()) {
//				all = false;
//				Msg.addMsgError("N�mero da conta n�o pode ser vazio");
//			}
//			if (!validarNome_titular()) {
//				all = false;
//				Msg.addMsgError("Titular da conta n�o pode ser vazio");
//			}
			if (!all) {
				System.out.println("...Erro ao cadastrar fornecedor: dados faltam ser preenchidos ou fornecedor ja existe");
				return "/pages/forncedor/cadastrarFornecedor.xhtml";
			} else {					
				if (fornecedor.getBanco().equals(3) && fornecedor.getOperacao().isEmpty()) {
					Msg.addMsgError("Campo Op nao pode ser vazio");
					return null;	
				}else
					if (fornecedor.getBanco().equals(3)) {
						dao.atualiza(fornecedor);
						this.fornecedor = new Fornecedor();
						init();
						Msg.addMsgInfo("ATUALIZACAO REALIZADA COM SUCESSO");
						System.out.println("...alteracao de fornecedor efetuada com sucesso!");
						
					} else {
						fornecedor.setOperacao("000");
						dao.atualiza(fornecedor);
						this.fornecedor = new Fornecedor();
						init();
						Msg.addMsgInfo("ATUALIZACAO REALIZADA COM SUCESSO");
						System.out.println("...alteracao de fornecedor efetuada com sucesso!");

					}
			}
		} catch (Exception e) {
			init();
			e.printStackTrace();
			System.out
					.println("...Alguma coisa deu errada ao cadastrar fornecedor");
		}
		return null;
	}

	/** ajax */

	public void checkBox4Search(AjaxBehaviorEvent event) {

	}

	public void checkNomeComercial(AjaxBehaviorEvent event) {
		validarNome();
		if (fornecedor.getNome().isEmpty()) {
			validator.setResultNome("");
		}
	}

	/** validacao UK Nome */

	public boolean validarNomeUK_nome() {
		return validator.validarNomeUK("nome", fornecedor.getNome());
	}

	public void checkNomeUK_nome(AjaxBehaviorEvent event) {
		if (validarNomeUK_nome()) {
			if (fornecedor.getNome().isEmpty()) {
				validator.setResultNome("");
			}
		}
	}

	/** valida��o UK CPF / CNPJ */

	public boolean validarNome() {
		return validator.validarNome(fornecedor.getNome());
	}

	public boolean validarNomeUK_cnpj() {
		return validator.validarNomeUK("cnpj_cpf", fornecedor.getCnpj());
	}

	public void checkNomeUK_cpf(AjaxBehaviorEvent event) {
		if (validarNomeUK_cnpj()) {
			if (fornecedor.getCnpj().isEmpty()) {
				validator.setResultNome("");
			}
		}
	}

	/** Get and Set **/

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public DAO<Fornecedor> getDao() {
		return dao;
	}

	public void setDao(DAO<Fornecedor> dao) {
		this.dao = dao;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public List<Fornecedor> getFornecedoresD() {
		return fornecedoresD;
	}

	public void setFornecedoresD(List<Fornecedor> fornecedoresD) {
		this.fornecedoresD = fornecedoresD;
	}

	public Validator<Fornecedor> getValidator() {
		return validator;
	}

	public void setValidator(Validator<Fornecedor> validator) {
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

	public void setBox4Search(Integer box4Search) {
		this.box4Search = box4Search;
	}

	public void checkNome_agencia(AjaxBehaviorEvent event) {
		validarNome_agencia();
	}

	public boolean validarNome_agencia() {
		return validator.validarNome(fornecedor.getAgencia());
	}

	public void checkNome_conta(AjaxBehaviorEvent event) {
		validarNome_conta();
	}

	public boolean validarNome_conta() {
		return validator.validarNome(fornecedor.getConta());
	}

	public void checkNome_titular(AjaxBehaviorEvent event) {
		validarNome_titular();
	}

	public boolean validarNome_titular() {
		return validator.validarNome(fornecedor.getTitularConta());
	}

}
