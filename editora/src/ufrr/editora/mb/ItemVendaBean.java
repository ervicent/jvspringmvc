package ufrr.editora.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.Query;

import ufrr.editora.dao.DAO;
import ufrr.editora.entity.Item;
import ufrr.editora.entity.ItemVenda;
import ufrr.editora.entity.NotaFiscal;
import ufrr.editora.util.Msg;

@ManagedBean
@ViewScoped
public class ItemVendaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	private ItemVenda itemVenda = new ItemVenda();
	private Item item = new Item();
	private NotaFiscal notaFiscal = new NotaFiscal();
	private List<ItemVenda> itensVendas;
	private List<ItemVenda> itensVendas1;
	private DAO<ItemVenda> dao = new DAO<ItemVenda>(ItemVenda.class);

	private Integer totalProdutoVendido;
	private Double totalProdutoValor;

	private String search;
	private Integer box4Search;

	private Integer mes;

	private Integer ano;

	@PostConstruct
	public void init() {

		search = "";
		box4Search = 1;
		box4Search = 2;
		box4Search = 3;
	}

	//	
	//	// Exibe uma lista com as solicitacoes de acesso
	//		@SuppressWarnings("unchecked")
	//		public List<Usuario> getContas() {
	//			Query query = dao.query("SELECT i FROM ItemVenda i ORDERBY i.produto.nome, i.produto.isbn");
	//			itensVendas1 = query.getResultList();
	//			return query.getResultList();
	//		}


	/** list **/

	public List<ItemVenda> getItensVendas() {
		if (itensVendas == null) {
			System.out.println("Carregando itens de vendas...");
			itensVendas = new DAO<ItemVenda>(ItemVenda.class).getAllOrder("item.produto.nome, item.produto.isbn");
		}
		return itensVendas;
	}	

	// Pesquisa venda pelo cpf
	public String getListaVendaFornecedorByCPF() {
		if (box4Search.equals(1)) {
			itensVendas1 = dao.getAllByName(
					"obj.item.notaFiscal.fornecedor.cnpj", search);
			if (itensVendas1.isEmpty()) {
				init();
				Msg.addMsgError("NENHUMA VENDA EFETUADA PARA ESTE CPF");
				return null;
			} else {
				return null;
			}
		}
		return null;
	}

	// Pesquisa venda pelo cpf
	public String getListaVendaFornecedorByCNPJ() {
		if (box4Search.equals(2)) {
			itensVendas1 = dao.getAllByName(
					"obj.item.notaFiscal.fornecedor.cnpj", search);
			if (itensVendas1.isEmpty()) {
				init();
				Msg.addMsgError("NENHUMA VENDA EFETUADA PARA ESTE CPF");
				return null;
			} else {
				return null;
			}
		}
		return null;
	}

	// Pesquisa venda pelo cliente, data e vendedor
	public String getListaVendaFornecedorByNome() {

		if (box4Search.equals(4)) {
			if (search.length() <= 15) {
				init();
				Msg.addMsgError("INFORME O NOME CORRETO DO FORNECEDOR");
				return null;
			} else {
				itensVendas1 = dao.getAllByName("obj.item.notaFiscal.fornecedor.nome", search);
				if (itensVendas1.isEmpty()) {
					init();
					Msg.addMsgError("NENHUMA VENDA EFETUADA PARA ESTE FORNECEDOR");
					return null;
				} else {
					return null;
				}
			}
		}
		return null;
	}
	
	// Pesquisa a venda de produto pelo nome
	@SuppressWarnings("unchecked")
	public void getListaVendaByProduto() {
		try {
			Query query = dao.query("SELECT i FROM ItemVenda i WHERE i.item.produto.nome=?");
			query.setParameter(1, item.getProduto().getNome());
			itensVendas1 = query.getResultList();
			if (itensVendas1.isEmpty()) {
				init();
				Msg.addMsgError("NÃO FOI EFETUADA VENDA COM ESTE PRODUTO");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Pesquisa a venda de produto pelo lote
	@SuppressWarnings("unchecked")
	public void getListaVendaByLote() {
		try {
			Query query = dao.query("SELECT i FROM ItemVenda i WHERE i.item.notaFiscal.lote=?");
			query.setParameter(1, item.getNotaFiscal().getLote());
			itensVendas1 = query.getResultList();
			if (itensVendas1.isEmpty()) {
				init();
				Msg.addMsgError("NÃO FOI EFETUADA NENHUMA VENDA DESTE LOTE");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// pesquisa nota pelo numero
	@SuppressWarnings("unchecked")
	public void getListaVendaByNota() {
		if (notaFiscal.getNumero().equals(null) || notaFiscal.getNumero() == 0) {
			Msg.addMsgError("INFORME CORRETAMENTE A NOTA FISCAL");

		} else {
			try {
				Query query = dao
						.query("SELECT i FROM ItemVenda i WHERE i.item.notaFiscal.numero=?");
				query.setParameter(1, notaFiscal.getNumero());
				itensVendas1 = query.getResultList();
				if (itensVendas1.isEmpty()) {
					init();
					Msg.addMsgError("NAO FOI EFETUADA NENHUMA VENDA COM A NOTA INFORMADA");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// prestacao de conta
	// Consulta pelo fornecedor

	// (este metodo deve funcionar, com a consulta do fornecedor e em seguida o mes) - Falta fazer o mes!!!
	public String getLista() {

		if (box4Search.equals(1)) {
			if (search.length() <= 4) {
				init();
				Msg.addMsgError("INFORME 5 CARACTERES PARA PESQUISA");
				return null;
			} else {
				itensVendas1 = dao.getAllByName("obj.item.notaFiscal.fornecedor.nome", search);
				itensVendas1 = dao.getAllOrder("item.produto.nome, item.produto.isbn");
				if (itensVendas1.isEmpty()) {
					init();
					Msg.addMsgError("NENHUMA VENDA EFETUADA!");
				} else {
					return null;
				}
			}

		}
		return null;
	}

	//  Pretacao de conta com fornecedor	
	@SuppressWarnings("unchecked")
	public String getPrestacaoByDate() {
		try {
			Query query = dao.query("SELECT v FROM ItemVenda v WHERE extract(month from v.venda.dataVenda) = ? "
					+ "and extract(year from v.venda.dataVenda) = ? "
					+ "and v.item.notaFiscal.fornecedor.nome = ? and v.venda.tituloObs <> 8"
					+ "order by v.venda.dataVenda");
			query.setParameter(1, getMes());
			query.setParameter(2, getAno());
			query.setParameter(3, notaFiscal.getFornecedor().getNome());
			itensVendas1 = query.getResultList();

			// n�o est� fazendo filtragem com o fornecedor, somente com o m�s e ano!
			// n�o houve sucesso ao tentar fazer pelo sql ex: itemVenda.item.notaFiscal.fornecedor.ano 'returned null' 'item'

			if (itensVendas1.isEmpty()) {
				init();
				Msg.addMsgError("NENHUMA VENDA EFETUADA PARA OS DADOS INFORMADOS");
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("... erro na consulta do caixa");
		}
		return null;
	}

	/** calculo **/

	// metodo para somar a quantidade dos itens vendidos
	public Integer getTotalProdutos() {
		setTotalProdutoVendido(0);
		for (ItemVenda i : getItensVendas()) {
			setTotalProdutoVendido(getTotalProdutoVendido() + i.getQuantidade());
		}
		return totalProdutoVendido;
	}

	// metodo para somar a quantidade dos itens vendidos
	public Double getTotalValor() {
		setTotalProdutoValor(0.00);
		for (ItemVenda i : getItensVendas1()) {
			setTotalProdutoValor(getTotalProdutoValor() + i.getTotalProdutoVenda());
		}
		return totalProdutoValor;
	}


	/** get and set **/

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public ItemVenda getItemVenda() {
		return itemVenda;
	}

	public void setItemVenda(ItemVenda itemVenda) {
		this.itemVenda = itemVenda;
	}

	public List<ItemVenda> getItensVendas1() {
		return itensVendas1;
	}

	public void setItensVendas1(List<ItemVenda> itensVendas1) {
		this.itensVendas1 = itensVendas1;
	}

	public DAO<ItemVenda> getDao() {
		return dao;
	}

	public void setDao(DAO<ItemVenda> dao) {
		this.dao = dao;
	}

	public Integer getTotalProdutoVendido() {
		return totalProdutoVendido;
	}

	public void setTotalProdutoVendido(Integer totalProdutoVendido) {
		this.totalProdutoVendido = totalProdutoVendido;
	}

	public void setItensVendas(List<ItemVenda> itensVendas) {
		this.itensVendas = itensVendas;
	}

	public Double getTotalProdutoValor() {
		return totalProdutoValor;
	}

	public void setTotalProdutoValor(Double totalProdutoValor) {
		this.totalProdutoValor = totalProdutoValor;
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

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

}
