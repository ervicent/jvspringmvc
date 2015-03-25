package ufrr.editora.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.Query;

import ufrr.editora.dao.DAO;
import ufrr.editora.entity.Fornecedor;
import ufrr.editora.entity.Item;
import ufrr.editora.entity.NotaFiscal;
import ufrr.editora.entity.Produto;
import ufrr.editora.entity.Usuario;
import ufrr.editora.util.Msg;
import ufrr.editora.validator.Validator;

@ManagedBean
@ViewScoped
public class NotaFiscalBean implements Serializable {

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	private static final long serialVersionUID = 1L;
	private Fornecedor fornecedor = new Fornecedor();
	private NotaFiscal notaFiscal = new NotaFiscal();
	private Produto produto = new Produto();
	private Item item = new Item();
	private Long idProduto;
	private List<Item> itens;
	private List<NotaFiscal> notasFiscais;
	private List<NotaFiscal> notasFiscais1;
	private DAO<NotaFiscal> dao = new DAO<NotaFiscal>(NotaFiscal.class);
	private DAO<Produto> daoP = new DAO<Produto>(Produto.class);
	private String search, resultValidarUK;
	private Double totalValor;
	public Boolean cadastro = true;
	private Integer box4Search;
	private Validator<NotaFiscal> validator;
	

	@PostConstruct
	public void init() {

		notaFiscal = new NotaFiscal();
		validator = new Validator<NotaFiscal>(NotaFiscal.class);
		search = "";
		box4Search = 1;
		box4Search = 2;
	}

	// pesquisa nota pelo numero
	@SuppressWarnings("unchecked")
	public void getNotaFiscalByNumero() {
		if (notaFiscal.getNumero().equals(null) || notaFiscal.getNumero() == 0) {
			Msg.addMsgError("INFORME CORRETAMENTE A NOTA FISCAL");

		} else {
			try {
				Query query = dao
						.query("SELECT n FROM NotaFiscal n WHERE n.numero=?");
				query.setParameter(1, notaFiscal.getNumero());
				notasFiscais = query.getResultList();
				if (notasFiscais.isEmpty()) {
					init();
					Msg.addMsgError("NENHUM REGISTRO ENCONTRADO");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// pesquisa nota pelo Lote
		@SuppressWarnings("unchecked")
		public void getNotaFiscalByLote() {
			if (notaFiscal.getLote().equals(null) || notaFiscal.getLote() == 0) {
				Msg.addMsgError("INFOME CORRETAMENTE O LOTE");

			} else {
				try {
					Query query = dao
							.query("SELECT n FROM NotaFiscal n WHERE n.lote=?");
					query.setParameter(1, notaFiscal.getLote());
					notasFiscais = query.getResultList();
					if (notasFiscais.isEmpty()) {
						init();
						Msg.addMsgError("NENHUM REGISTRO ENCONTRADO");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	// Pesquisa nota fiscal pelo numero e fornecedor
	public String getListaNotaFiscalByNumero() {

		if (box4Search.equals(1)) {
			if (search.contains("'") || search.contains("@")
					|| search.contains("/") || search.contains("*")) {
				init();
				Msg.addMsgError("COMTEM CARACTER(ER) INVALIDO(S)");
				return null;
			} else {
				if (search.length() <= 0) {
					init();
					Msg.addMsgError("NUMERO INVALIDO. PREENCHA CORRETAMENTE O CAMPO MARCADO PARA PESQUISA");
					return null;
				} else {
					notasFiscais = dao.getAllByName("obj.numero", search);
					if (notasFiscais.isEmpty()) {
						init();
						Msg.addMsgError("NENHUM REGISTRO ENCONTRADO");
					} else {
						return null;
					}
				}
			}
		} else if (box4Search.equals(2)) {
			if (search.length() <= 4) {
				init();
				Msg.addMsgError("INFORME 5 CARACTERES PARA PESQUISA");
				return null;
			} else {
				notasFiscais = dao.getAllByName("obj.fornecedor.nome", search);
				if (notasFiscais.isEmpty()) {
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

	/** List NF **/

	public List<NotaFiscal> getNotasFiscais() {
		if (notasFiscais == null) {
			System.out.println("Carregando notas fiscais...");
			notasFiscais = new DAO<NotaFiscal>(NotaFiscal.class)
					.getAllOrder("fornecedor.nome, numero, status");
		}
		return notasFiscais;
	}
	
	public List<Item> getItens() {
		if (itens == null) {
			System.out.println("Carregando itens...");
			itens = new DAO<Item>(Item.class).getAllOrder("produto.nome");
		}
		return itens;
	}

	// lista as notas ativadas
	public List<NotaFiscal> getAtivados() {
		notasFiscais1 = new ArrayList<NotaFiscal>();
		List<NotaFiscal> ps = new ArrayList<NotaFiscal>();
		ps = this.getNotasFiscais();
		for (int i = 0; i < ps.size(); i++) {
			if (ps.get(i).getStatus().equals(true)) {
				notasFiscais1.add(ps.get(i));
			}
		}
		return notasFiscais1;
	}
	
	// Reinicia o numero do lote a cada ano
		@SuppressWarnings("unchecked")
		public List<NotaFiscal> getCount() {
			Query query = dao.query("SELECT n FROM NotaFiscal n "
					+ "WHERE EXTRACT(year FROM n.dataEntrada) = extract(year from CURRENT_DATE)");
			notasFiscais = query.getResultList();
			System.out.println("Total de notas por ano: " + notasFiscais.size());
			return query.getResultList();
		}
		
	/** Actions **/

	// m�todo para cadastrar nota
	public String addNota() {
		boolean all = true;
		if (!validarNota()) {
			all = false;
		}
		if (notaFiscal.getValor() == null || notaFiscal.getValor() == 0) {
			Msg.addMsgError("INFORME O VALOR TOTAL DA NOTA FISCAL");
			all = false;
		}
		if (notaFiscal.getItens().isEmpty()) {
			Msg.addMsgError("NAO FOI POSSIVEL CADASTRAR NOTA FISCAL SEM PRODUTO");
			all = false;
		}
		if (!all) {
			System.out
					.println("...Erro ao cadastrar nota: nota fiscal ja existe");
		} else {
			if (notaFiscal.getValor().equals(getValorTotalProdutos())) {
				this.getNotaFiscal().setUsuario(this.loginBean.getUsuario());
				DAO<Usuario> UDao = new DAO<Usuario>(Usuario.class);
				Usuario u = UDao
						.buscaPorId(this.loginBean.getUsuario().getId());
				u.getNotasFiscais().add(notaFiscal);
				DAO<NotaFiscal> dao = new DAO<NotaFiscal>(NotaFiscal.class);
				Msg.addMsgInfo("NOTA FISCAL CADASTRADA COM SUCESSO");
							
				notaFiscal.setLote((long) (getCount().size()+1)); // gera o numero do lote
				System.out.println("...Total de notas: " + getCount().size()); // mostra o total de lotes pelo ano atual
					
				notaFiscal.setStatus(true);
				dao.adiciona(notaFiscal);
				item = new Item();
				notaFiscal = new NotaFiscal();
				System.out.println("...Nota fiscal cadastrada com sucesso");
				return "/pages/notafiscal/cadastrarNotaFiscal.xhtml";
			} else {
				System.out
						.println("...Erro: o valor da nota fiscal deve ser o mesmo ao total de itens");
				Msg.addMsgError("VALOR TOTAL DA NOTA DEVE SER IGUAL AO VALOR DOS PRODUTOS SOMADOS");
				return null;
			}
		}
		return null;
	}

	// m�todo para adicionar itens a nota fiscal
	public void guardaItem() {
		boolean all = true;
		if (item.getQuantidadeEntrada() == null || item.getQuantidadeEntrada() == 0) {
			Msg.addMsgError("INFORME A QUANTIDADE");
			all = false;
		}
		if (item.getValorCusto() == null || item.getValorCusto() == 0.00) {
			Msg.addMsgError("INFORME O VALOR DE CUSTO");
			all = false;
		}
		if (item.getValorVenda() == null || item.getValorVenda() == 0.00) {
			Msg.addMsgError("INFORME O VALOR DE VENDA");
			all = false;
		}
		
		if (!all) {
			System.out.println("...Erro ao cadastrar nota: inconsistencia nos dados do item");
		} else {
			for (Item i : this.getNotaFiscal().getItens()) {
				if (getIdProduto().equals(i.getProduto().getId())) {
					this.cadastro = false;
					break;
				}
			}
			if (this.cadastro == true) {

				DAO<Produto> dao = new DAO<Produto>(Produto.class);

				Produto produto = dao.buscaPorId(idProduto);
				item.setProduto(produto);

				item.setQuantidadeSaida(0);
				notaFiscal.getItens().add(item);
				item.setNotaFiscal(notaFiscal);

				item = new Item();
				System.out.println("...Item adicionado com sucesso");
				this.cadastro = true;

			} else {
				Msg.addMsgError("ESTE PRODUTO JA FOI ADICIONADO");
				System.out.println("...Este produto ja foi adicionado");
				item = new Item();
				this.cadastro = true;
			}
		}
	}
			
	
	// cancelar cadastro de nota fiscal
		public String cancelarNotaFiscal() {
			if (notaFiscal.getNumero() != null) {
				Msg.addMsgFatal("Nota fiscal cancelada");
				return "cadastrarNotaFiscal.xhtml";
			}else {
				return "cadastrarNotaFiscal.xhtml";
			}
		}

	// m�todo para editar/alterar nota
	public String alterNota() {
		boolean all = true;
		if (notaFiscal.getValor() == null || notaFiscal.getValor() == 0) {
			Msg.addMsgError("INFORME O VALOR TOTAL DA NOTA FISCAL");
			all = false;
		}
		if (notaFiscal.getItens().isEmpty()) {
			Msg.addMsgError("NAO FOI POSSIVEL CADASTRAR NOTA FISCAL SEM PRODUTO");
			all = false;
		}
		if (!all) {
			System.out
					.println("...Erro ao cadastrar nota: nota fiscal ja existe");
		} else {
			if (notaFiscal.getValor().equals(getValorTotalProdutos())) {
				DAO<NotaFiscal> dao = new DAO<NotaFiscal>(NotaFiscal.class);
				Msg.addMsgInfo("NOTA FISCAL ALTERADA COM SUCESSO");
				dao.atualiza(notaFiscal);
				notaFiscal = new NotaFiscal();
				return "/pages/notafiscal/cadastrarNotaFiscal.xhtml";
			} else {
				System.out
						.println("...Erro: o valor da nota fiscal deve ser o mesmo ao total de itens");
				Msg.addMsgError("O VALOR TOTAL DA NOTA FISCAL DEVE SER IGUAL AO VALOR DOS PRODUTOS SOMADOS");
				return null;
			}
		}
		return null;
	}

	// m�todo para remover o item da lista de itens no cadastro de nota fiscal
	public void removeItem() {
		notaFiscal.getItens().remove(item);
		Msg.addMsgWarn("ITEM REMOVIDO");
		System.out.println("Item removido...");

		item = new Item();
	}

	// desativar/ativar nota fiscal
	public String alterStatus() {
		if (notaFiscal.getStatus().equals(true)) {
			notaFiscal.setStatus(false);
			dao.atualiza(notaFiscal);
			Msg.addMsgInfo("NOTA FISCAL: " + getNotaFiscal().getNumero() + "\n"
					+ "FORNECEDOR: "
					+ getNotaFiscal().getFornecedor().getNome() + "\n"
					+ "DESATIVADA COM SUCESSO");
			System.out.println("...Nota fiscal desativada");
			return "/pages/notafiscal/consultarNotaFiscal.xhtml";
		} else {
			notaFiscal.setStatus(true);
			dao.atualiza(notaFiscal);
			Msg.addMsgInfo("NOTA FISCAL: " + getNotaFiscal().getNumero() + "\n"
					+ "FORNECEDOR: "
					+ getNotaFiscal().getFornecedor().getNome() + "\n"
					+ "REATIVADA COM SUCESSO");
			System.out.println("...Nota fiscal ativada");
			return "/pages/notafiscal/consultarNotaFiscal.xhtml";
		}
	}
	
	//link para fornecedor
	public String linkFornecedor() {
		return "/pages/fornecedor/cadastrarJFornecedor.xhtml?faces.redirect=true";
	}

	/** validations **/
	
//	public boolean validarAddItem() {
//		@SuppressWarnings("unused")
//		boolean var = getIdProduto()==getItem().getProduto().getId();
//			
//		Query q = dao.query("SELECT i FROM Item i WHERE produto and quantidadeEntrada = quantidadeSaida"); //achar um m�todo certo
//				
//		if (!q.getResultList().isEmpty()) {
//			Msg.addMsgError("Este produto encontra-se em estoque, é preciso esgota-lo para nova entrada");
//			return true;
//		} else {
//			resultValidarUK = "";
//			return false;
//		}
//	}

	// valida��o para n�o cadastrar n� de nota fiscal para o mesmo fornecedor
	public boolean validarNota() {
		Query q = dao
				.query("SELECT n FROM NotaFiscal n WHERE numero = ? and fornecedor = ? and status = true");
		q.setParameter(1, notaFiscal.getNumero());
		q.setParameter(2, notaFiscal.getFornecedor());

		if (!q.getResultList().isEmpty()) {
			Msg.addMsgError("ESTA NOTA JA POSSUI REGISTRO NO SISTEMA");
			return false;
		} else {
			resultValidarUK = "";
			return true;
		}
	}

	// vari�vel para exibir o total R$ dos produtos
	public Double getTotal() {
		if (item.getQuantidadeEntrada() != null && item.getValorCusto() != null)
			return item.getQuantidadeEntrada() * item.getValorCusto();
		else
			return null;
	}

	// vari�vel para exibir a soma do total dos produtos
	public Double getValorTotalProdutos() {
		setTotalValor(00.00);
		for (Item i : getNotaFiscal().getItens()) {
			setTotalValor(getTotalValor() + i.getTotal());
		}
		return totalValor;
	}
	

	/** get and set **/

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public DAO<NotaFiscal> getDao() {
		return dao;
	}

	public void setDao(DAO<NotaFiscal> dao) {
		this.dao = dao;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getResultValidarUK() {
		return resultValidarUK;
	}

	public void setResultValidarUK(String resultValidarUK) {
		this.resultValidarUK = resultValidarUK;
	}

	public Double getTotalValor() {
		return totalValor;
	}

	public Boolean getCadastro() {
		return cadastro;
	}

	public void setCadastro(Boolean cadastro) {
		this.cadastro = cadastro;
	}

	public void setTotalValor(Double totalValor) {
		this.totalValor = totalValor;
	}

	public void setNotasFiscais(List<NotaFiscal> notasFiscais) {
		this.notasFiscais = notasFiscais;
	}

	public List<NotaFiscal> getNotasFiscais1() {
		return notasFiscais1;
	}

	public void setNotasFiscais1(List<NotaFiscal> notasFiscais1) {
		this.notasFiscais1 = notasFiscais1;
	}

	public Integer getBox4Search() {
		return box4Search;
	}

	public void setBox4Search(Integer box4Search) {
		this.box4Search = box4Search;
	}

	public Validator<NotaFiscal> getValidator() {
		return validator;
	}

	public void setValidator(Validator<NotaFiscal> validator) {
		this.validator = validator;
	}

	public DAO<Produto> getDaoP() {
		return daoP;
	}

	public void setDaoP(DAO<Produto> daoP) {
		this.daoP = daoP;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	

}
