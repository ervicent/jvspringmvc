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
import ufrr.editora.entity.Item;
import ufrr.editora.entity.ItemDevolvido;
import ufrr.editora.entity.NotaFiscal;
import ufrr.editora.entity.Produto;
import ufrr.editora.entity.Usuario;
import ufrr.editora.util.Msg;

@ManagedBean
@ViewScoped
public class ItemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	private Item item = new Item();
	private Produto produto = new Produto();
	private NotaFiscal notaFiscal = new NotaFiscal();
	private ItemDevolvido devolvido = new ItemDevolvido();
	private List<Item> itens;
	private List<Item> itens1;
	private DAO<Item> dao = new DAO<Item>(Item.class);
	private DAO<ItemDevolvido> dao2 = new DAO<ItemDevolvido>(ItemDevolvido.class);
	private DAO<Produto> dao3 = new DAO<Produto>(Produto.class);
	private Integer box4Search;
	private String search;

	private Integer totalProduto;

	private Integer retorno;

	@PostConstruct
	public void init() {

		search = "";
		box4Search = 1;
	}

	/** list **/

	public List<Item> getItens() {
		if (itens == null) {
			System.out.println("Carregando itens...");
			itens = new DAO<Item>(Item.class).getAllOrder("produto.nome, notaFiscal.lote");
		}
		return itens;
	}

	// Exibe uma lista de itens
	@SuppressWarnings("unchecked")
	public List<Item> getItens2() {
		Query query = dao.query("SELECT i FROM Item i ORDER BY i.produto.nome");
		itens = query.getResultList();
		System.out.println("Total de Itens: " + getItens().size());
		return query.getResultList();
	}
	
	// Exibe uma lista do estoque
		@SuppressWarnings("unchecked")
		public List<Item> getEstoque2() {
			Query query = dao.query("SELECT i FROM Item i WHERE i.quantidadeEntrada < i.quantidadeSaida ORDER BY i.produto.nome");
			itens1 = query.getResultList();
			System.out.println("Total de Itens: " + getEstoque2().size());
			return query.getResultList();
		}

	// Lista de produtos desativados (sem livros)
	public List<Item> getEstoqueCritico() {
		itens1 = new ArrayList<Item>();
		List<Item> item = new ArrayList<Item>();
		item = this.getItens();
		for (int i = 0; i < item.size(); i++) {
			if (item.get(i).getQuantidadeEntrada() == item.get(i).getQuantidadeSaida()) {
			}else {
				if (item.get(i).getProduto().getQuantidadeMinima()>=item.get(i).getQuantidadeAtual() 
						&& item.get(i).getNotaFiscal().getStatus().equals(true)) {
					itens1.add(item.get(i));
				}	
			}

		}
		return itens1;
	}	

	// estoque que nao aparece valores zerados
	public List<Item> getEstoque() {
		itens1 = new ArrayList<Item>();
		List<Item> item = new ArrayList<Item>();
		item = this.getItens();
		for (int i = 0; i < item.size(); i++) {
			if (item.get(i).getQuantidadeEntrada() <= item.get(i).getQuantidadeSaida()) {

			}else {
				if (item.get(i).getNotaFiscal().getStatus().equals(true)) {
					itens1.add(item.get(i));
				}
			}
		}
		return itens1;
	}


	// lista para a consulta de itens

	public List<Item> getItemNotaFiscal() {
		itens1 = new ArrayList<Item>();
		List<Item> item = new ArrayList<Item>();
		item = this.getItens();
		for (int i = 0; i < item.size(); i++) {
			if (item.get(i).getNotaFiscal().getStatus().equals(true)) {
				itens1.add(item.get(i));
			}
		}
		return itens1;
	}

	/** calculo */

	// metodo para somar a quantidade de entrada
	public Integer getTotalEntrada() {
		setTotalProduto(0);
		for (Item i : getEstoque()) {
			setTotalProduto(getTotalProduto() + i.getQuantidadeEntrada());
		}
		return totalProduto;
	}

	// metodo para somar a quantidade de saida
	public Integer getTotalSaida() {
		setTotalProduto(0);
		for (Item i : getEstoque()) {
			setTotalProduto(getTotalProduto() + i.getQuantidadeSaida());
		}
		return totalProduto;
	}

	// quantidade atual do total
	public Integer getTotalAtual() {
		return getTotalEntrada() - getTotalSaida();

	}

	/** actions **/
	
	// altera preço do item
	
	public void alterItem(){
		if(item.getId()!=null) {
			dao.atualiza(item);
			Msg.addMsgInfo("PRECO MODIFICADO COM SUCESSO");
		} else {
			Msg.addMsgError("NÃO FOI POSSIVEL EFETUAR OPERACAO, TENTE NOVAMENTE.");
		}
	}
	
	/** Serach or List **/

	// Pesquisa produto pelo nome
	@SuppressWarnings("unchecked")
	public void getListaEstoqueByProduto() {
		try {
			Query query = dao.query("SELECT i FROM Item i WHERE i.produto.nome=? and i.quantidadeEntrada > i.quantidadeSaida");
			query.setParameter(1, item.getProduto().getNome());
			itens1 = query.getResultList();
			if (itens1.isEmpty()) {
				init();
				Msg.addMsgError("PRODUTO NÃO ENCONTRADO NO ESTOQUE");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Pesquisa produto pela editora da obra
	@SuppressWarnings("unchecked")
	public void getListaEstoqueByEditora() {
		try {
			Query query = dao.query("SELECT i FROM Item i WHERE i.produto.editora=? and i.quantidadeEntrada > i.quantidadeSaida");
			query.setParameter(1, produto.getEditora());
			itens1 = query.getResultList();
			if (itens1.isEmpty()) {
				init();
				Msg.addMsgError("NENHUMA OBRA COM ESTA EDITORA DISPONÍVEL NO ESTOQUE");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Pesquisa produto pelo autor da obra
	@SuppressWarnings("unchecked")
	public void getListaEstoqueByAutor() {
		try {
			Query query = dao.query("SELECT i FROM Item i WHERE i.produto.autor=? and i.quantidadeEntrada > i.quantidadeSaida");
			query.setParameter(1, produto.getAutor());
			itens1 = query.getResultList();
			if (itens1.isEmpty()) {
				init();
				Msg.addMsgError("NÃO EXISTE NENHUMA OBRA COM ESSE AUTOR DISPONÍVEL NO ESTOQUE NO MOMENTO");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Pesquisa produto pelo nome
	@SuppressWarnings("unchecked")
	public void getListaEstoqueByCategoria() {
		try {
			Query query = dao.query("SELECT i FROM Item i WHERE i.produto.categoria.nome=? and i.quantidadeEntrada > i.quantidadeSaida");
			query.setParameter(1, produto.getCategoria().getNome());
			itens1 = query.getResultList();
			if (itens1.isEmpty()) {
				init();
				Msg.addMsgError("CATEGORIA INFORMADA NÃO ENCONTRADA NO ESTOQUE");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Pesquisa venda pela categoria --apagar este se o de cima funcionar
	public String getListaEstoqueCategoria() {
		itens1 = dao.getAllByName("obj.item.produto.categoria.nome", search);
		if (itens1.isEmpty()) {
			init();
			Msg.addMsgError("CATEGORIA INFORMADA NÃO ENCONTRADA NO ESTOQUE");
			return null;
		} else {
			return null;
		}
	}

//	Pesquisa produto pelo nome
//	public String getListaEstoqueByProduto2() {
//		if (box4Search.equals(1)) {
//			itens1 = dao.getAllByName("obj.item.produto.autor", search);
//			if (itens1.isEmpty()) {
//				init();
//				Msg.addMsgError("PRODUTO NÃO ENCONTRADO NO ESTOQUE");
//				return null;
//			} else {
//				return null;
//			}
//		}
//		return null;
//	}

	// devolve ao estoque quantidade informada
	// somente caso de troca ou devolucao

	public String updateItem() {
		if (item.getId() != null) {
			if(item.getQuantidadeSaida()==0) {
				Msg.addMsgFatal("OPERACAO INDISPONIVEL! NAO HOUVE VENDA DESTE PRODUTO PARA DEVOLUCAO");
			}else {
				this.getDevolvido().setUsuario(this.loginBean.getUsuario());
				DAO<Usuario> UDao = new DAO<Usuario>(Usuario.class);
				Usuario u = UDao.buscaPorId(this.loginBean.getUsuario().getId());
				u.getItensDevolvidos().add(devolvido);

				Msg.addMsgInfo("PRODUTO DEVOLVIDO AO ESTOQUE");
				System.out.println("...produto devolvido ao estoque");
				item.setQuantidadeSaida(getItem().getQuantidadeSaida()-getRetorno());
				devolvido.setItem(item);
				devolvido.setRetorno(getRetorno());
				dao.atualiza(item);
				dao2.adiciona(devolvido);
				this.item = new Item();
				this.devolvido = new ItemDevolvido();
			}
		} else {
			System.out.println("...Nao foi possivel devolver produto ao estoque");
			Msg.addMsgFatal("NAO FOI POSSIVEL CONCLUIR OPERACAO");
		}
		return "/pages/estoque/devolverEstoque.xhtml";
	}

	// devolve ao estoque quantidade informada
	public void getDevolverEstoque() {
		if (item.getId()!= null) {
			dao.atualiza(item);

		} else {
			Msg.addMsgFatal("NAO FOI POSSIVEL CONCLUIR OPERACAO");
		}

	}
	
//	aucomplete produto nome
	public List<String> autocomplete(String nome) {
		List<Produto> array = dao3.getAllByName("nome", nome);
		ArrayList<String> nomes = new ArrayList<String>();
		for (int i = 0; i < array.size(); i++) {
			nomes.add(array.get(i).getNome());
		}
		return nomes;
	}	


	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public DAO<Item> getDao() {
		return dao;
	}

	public void setDao(DAO<Item> dao) {
		this.dao = dao;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Integer getTotalProduto() {
		return totalProduto;
	}

	public void setTotalProduto(Integer totalProduto) {
		this.totalProduto = totalProduto;
	}

	public List<Item> getItens1() {
		return itens1;
	}

	public void setItens1(List<Item> itens1) {
		this.itens1 = itens1;
	}

	public Integer getRetorno() {
		return retorno;
	}

	public void setRetorno(Integer retorno) {
		this.retorno = retorno;
	}

	public ItemDevolvido getDevolvido() {
		return devolvido;
	}

	public void setDevolvido(ItemDevolvido devolvido) {
		this.devolvido = devolvido;
	}

	public DAO<ItemDevolvido> getDao2() {
		return dao2;
	}

	public void setDao2(DAO<ItemDevolvido> dao2) {
		this.dao2 = dao2;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Integer getBox4Search() {
		return box4Search;
	}

	public void setBox4Search(Integer box4Search) {
		this.box4Search = box4Search;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public DAO<Produto> getDao3() {
		return dao3;
	}

	public void setDao3(DAO<Produto> dao3) {
		this.dao3 = dao3;
	}

}
