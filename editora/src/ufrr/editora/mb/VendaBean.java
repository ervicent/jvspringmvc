package ufrr.editora.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ufrr.editora.dao.DAO;
import ufrr.editora.entity.Item;
import ufrr.editora.entity.ItemVenda;
import ufrr.editora.entity.Usuario;
import ufrr.editora.entity.Venda;
import ufrr.editora.report.Report;
import ufrr.editora.util.Msg;
import ufrr.editora.validator.Validator;

@ManagedBean
@ViewScoped
public class VendaBean implements Serializable {

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	private static final long serialVersionUID = 1L;
	private Venda venda = new Venda();
	private ItemVenda itemVenda = new ItemVenda();
	private Usuario cliente = new Usuario();
	private Item item = new Item();
	private Long idItem;
	private List<ItemVenda> itensVendas;
	private List<Item> itens;
	private List<Venda> vendas;
	private List<Venda> vendas1;
	private List<Venda> caixaEntrada;
	private List<Venda> caixaSaida;
	private DAO<Venda> dao = new DAO<Venda>(Venda.class);
	private DAO<Item> dao2 = new DAO<Item>(Item.class);
	private String search, resultValidarUK;
	private Double totalValor;
	private Double totalValorDesconto;
	private Double desconto;
	public Boolean cadastro = true;
	private Integer box4Search;
	private Validator<Venda> validator;
	private Boolean quantidadeN;

	@Temporal(TemporalType.DATE)
	private Calendar dataInicial = Calendar.getInstance();

	@Temporal(TemporalType.DATE)
	private Calendar dataFinal = Calendar.getInstance();

	@PostConstruct
	public void init() {

		venda = new Venda();
		validator = new Validator<Venda>(Venda.class);
		search = "";
		box4Search = 1;
		box4Search = 2;
		box4Search = 3;
		box4Search = 4;
	}

	/** List of venda **/

	public List<Venda> getVendas() {
		if (vendas == null) {
			System.out.println("Carregando caixa...");
			vendas = new DAO<Venda>(Venda.class).getAllOrder("id");
		}
		return vendas;
	}

	// entrada de dinheiro no caixa
	public List<Venda> getCaixaEntrada() {
		caixaEntrada = new ArrayList<Venda>();
		List<Venda> vs = new ArrayList<Venda>();
		vs = this.getVendas();
		for (int i = 0; i < vs.size(); i++) {
			if (vs.get(i).getOperacao() == 1 && vs.get(i).getAtivado()==true) {
				caixaEntrada.add(vs.get(i));
			}
		}
		return caixaEntrada;
	}

	// dinheiro retirado do caixa
	public List<Venda> getCaixaSaida() {
		caixaSaida = new ArrayList<Venda>();
		List<Venda> vs = new ArrayList<Venda>();
		vs = this.getVendas();
		for (int i = 0; i < vs.size(); i++) {
			if (vs.get(i).getOperacao() == 2) {
				caixaSaida.add(vs.get(i));
			}
		}
		return caixaSaida;
	}

	/** search **/ 

	// pesquisa venda pelo codigo do cliente
	@SuppressWarnings("unchecked")
	public String getVendaByCodigoCliente() {
		try {
			Query query = dao.query("SELECT v FROM Venda v WHERE v.cliente.id=?");
			query.setParameter(1, venda.getCliente().getId());
			vendas1 = query.getResultList();
			if (vendas1.isEmpty()) {
				init();
				Msg.addMsgError("NENHUMA VENDA EFETUADA PARA O CLIENTE INFORMADO");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// pesquisa venda pela data
	@SuppressWarnings("unchecked")
	public String getVendaByData() {
		try {
			Query query = dao
					.query("SELECT v FROM Venda v WHERE v.dataVenda=?");
			query.setParameter(1, venda.getDataVenda());
			vendas = query.getResultList();
			if (getCaixaEntrada().isEmpty()) {
				init();
				Msg.addMsgError("NENHUMA VENDA EFETUADA NA DATA INFORMADA");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Pesquisa venda pelo cliente, data e vendedor
	public String getListaVendaByCDV() {

		if (box4Search.equals(3)) {
			if (search.contains("'") || search.contains("@")
					|| search.contains("*")) {
				init();
				Msg.addMsgError("CONTEM CARACTER(ES) INVALIDO(S)");
				return null;
			} else {
				if (search.length() <= 10) {
					init();
					Msg.addMsgError("INFORME O NOME COMPLETO DO CLIENTE");
					return null;
				} else {
					vendas = dao.getAllByName("obj.vendedor.nome", search);
					if (vendas.isEmpty()) {
						init();
						Msg.addMsgError("NENHUMA VENDA EFETUADA PARA ESTE VENDEDOR");
						return null;
					} else {
						return null;
					}
				}
			}
			// este metodo serve para consultarVendaPorCliente.xhtml
		} else if (box4Search.equals(2)) {
			if (search.length() <= 4) {
				init();
				Msg.addMsgError("INFORME 5 CARACTERES PARA PESQUISA");
				return null;
			} else {
				vendas1 = dao.getAllByName("obj.cliente.nome", search);
				if (vendas1.isEmpty()) {
					init();
					Msg.addMsgError("NENHUMA VENDA EFETUADA PARA ESTE CLIENTE");
					return null;
				} else {
					return null;
				}
			}

		}
		return null;
	}

	// Pesquisa cliente pelo cpf
	public String getListaVendaByCPF() {
		if (box4Search.equals(1)) {
			vendas1 = dao.getAllByName("obj.cliente.cpf", search);
			if (vendas1.isEmpty()) {
				init();
				Msg.addMsgError("NENHUMA VENDA EFETUADA PARA ESTE CPF");
				return null;
			} else {
				return null;
			}
		}
		return null;
	}

	//consulta venda pelo vendedor
	public String getListaVendedorByName() {
		if (box4Search.equals(2)) {
			vendas1 = dao.getAllByName("obj.vendedor.nome", search);
			if (vendas1.isEmpty()) {
				init();
				Msg.addMsgError("NENHUMA VENDA EFETUADA PARA ESTE VENDEDOR");
				return null;
			} else {
				return null;
			}
		}
		return null;
	}

	// Pesquisa venda pelo cpf do fornecedor
	public String getListaVendaFornecedorByCPF() {
		if (box4Search.equals(1)) {
			vendas1 = dao.getAllByName("obj.item.produto.notafiscal.fornecedor.cnpj", search);
			if (vendas1.isEmpty()) {
				init();
				Msg.addMsgError("NENHUMA VENDA EFETUADA PARA ESTE CPF");
				return null;
			} else {
				return null;
			}
		}
		return null;
	}

	// pesquisa as vendas do vendedor pelo código
	@SuppressWarnings("unchecked")
	public void getVendaByVendedorCodigo() {
		if (venda.getVendedor().getId() == 0) {
			Msg.addMsgError("INFORME CORRETAMENTE O CODIGO DO VENDEDOR");

		} else {
			try {
				Query query = dao.query("SELECT v FROM Venda v WHERE v.vendedor.id=? and v.operacao=1");
				query.setParameter(1, venda.getVendedor().getId());
				vendas1 = query.getResultList();
				if (vendas1.isEmpty()) {
					init();
					Msg.addMsgError("NENHUMA VENDA ENCONTRADA");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/** Actions **/

	// saida de dinheiro
	public String addSaida() {
		boolean all = true;

		if (venda.getCliente() != null) {
			Msg.addMsgFatal("ERRO: OPERACAO CANCELADA. NAO IDENTIFIQUE O CLIENTE AO RETIRAR DINHEIRO DO CAIXA, TENTE NOVAMENTE.");
			all = false;
			return "/pages/venda/efetuarVendaAdmin.xhtml";
		}
		if (!all) {
			System.out
			.println("...Erro ao efetuar a saida: retire o nome do cliente");
		} else {

			if (loginBean.getUsuario().getPerfil().getId() == 1) {

				this.getVenda().setVendedor(this.loginBean.getUsuario());
				DAO<Usuario> UDao = new DAO<Usuario>(Usuario.class);
				Usuario u = UDao
						.buscaPorId(this.loginBean.getUsuario().getId());
				u.getVendas().add(venda);
				DAO<Venda> dao = new DAO<Venda>(Venda.class);
				venda.setOperacao(2);
				venda.setTituloObs(1);
				venda.setValorTotalDesconto(getVenda().getValorTotal());
				venda.setAtivado(true);

				Msg.addMsgInfo("RETIRADA EFETUADA COM SUCESSO");
				System.out.println("...saida efetuada com sucesso!!");
				dao.adiciona(venda);
				venda = new Venda();

				return "/pages/venda/efetuarVendaAdmin.xhtml";

			} else {
				Msg.addMsgFatal("RETIRADA NAO PERMITIDA");
			}

		}
		return null;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	// m�todo para efetuar venda (somente para vendedor)
	public String addVenda() {
		boolean all = true;

		if (venda.getCliente() == null) {
			Msg.addMsgError("INFORME O CLIENTE");
			all = false;
		}
		if (venda.getItensVendas().isEmpty()) {
			Msg.addMsgError("NAO FOI POSSIVEL EFETUAR A VENDA SEM PRODUTO");
			all = false;
		}
		if (!all) {
			System.out.println("...Erro ao efetuar a venda: dados incompletos");
		} else {
			for (ItemVenda i : this.getVenda().getItensVendas()) { // verifica se a quantidade do produto informado ultrapasse o disponivel em estoque
				if (i.getItem().getQuantidadeAtual() - i.getQuantidade() <= -1) {
					this.cadastro = false;
					break;
				}
			}
			if (this.cadastro == true) {

				this.getVenda().setVendedor(this.loginBean.getUsuario());
				DAO<Usuario> UDao = new DAO<Usuario>(Usuario.class);
				Usuario u = UDao.buscaPorId(this.loginBean.getUsuario().getId());
				u.getVendas().add(venda);
				DAO<Venda> dao = new DAO<Venda>(Venda.class);
				venda.setOperacao(1);
				venda.setValorTotalDesconto(getValorTotalComDesconto());
				venda.setValorTotal(getValorTotalProdutos());
				venda.setAtivado(true);

				// atualiza a lista de item (quantidade de saida)					
				for (ItemVenda iv : this.getVenda().getItensVendas()) {
					DAO<Item> iDAO = new DAO<Item>(Item.class);
					Item i = iv.getItem();

					i.setQuantidadeSaida(i.getQuantidadeSaida()+iv.getQuantidade());
					iDAO.atualiza(i);
					System.out.println("...atualizou quantidade de saida do item");
				}	

				Msg.addMsgInfo("VENDA EFETUADA COM SUCESSO");
				System.out.println("...venda efetuada com sucesso!!");
				dao.adiciona(venda);
				itemVenda = new ItemVenda();
				venda = new Venda();

				return "/pages/venda/efetuarVenda2.xhtml?faces-redirect=true";

			} else {
				Msg.addMsgFatal("HA NA LISTA UM PRODUTO COM QUANTIDADE INDISPONIVEL NO ESTOQUE, " +
						"COM ISSO NAO SERA POSSIVEL EFETUAR A VENDA. VERIFIQUE A QUANTIDADE DISPONIVEL NO ESTOQUE!");
				System.out.println("...quantidade nao disponivel no estoque");
				itemVenda = new ItemVenda();
				this.cadastro = true;
			}

		}
		return null;
	}

	// metodo para efetuar venda para admin e gerente
	public String addVendaAdmin() {
		boolean all = true;

		if (venda.getCliente() == null) {
			Msg.addMsgError("INFORME O CLIENTE");
			all = false;
		}
		if (venda.getItensVendas().isEmpty()) {
			Msg.addMsgError("NAO FOI POSSIVEL EFETUAR A VENDA SEM PRODUTO");
			all = false;
		}
		if (!all) {
			System.out.println("...Erro ao efetuar a venda: dados incompletos");
		} else {
			for (ItemVenda i : this.getVenda().getItensVendas()) { // verifica se a quantidade de produto informado ultrapasse o dispon�vel em estoque
				if (i.getItem().getQuantidadeAtual() - i.getQuantidade() <= -1) {
					this.cadastro = false;
					break;
				}
			}
			if (this.cadastro == true) {

				this.getVenda().setVendedor(this.loginBean.getUsuario());
				DAO<Usuario> UDao = new DAO<Usuario>(Usuario.class);
				Usuario u = UDao.buscaPorId(this.loginBean.getUsuario().getId());
				u.getVendas().add(venda);
				DAO<Venda> dao = new DAO<Venda>(Venda.class);
				venda.setOperacao(1);
				venda.setValorTotalDesconto(getValorTotalComDesconto());
				venda.setValorTotal(getValorTotalProdutos());
				venda.setAtivado(true);

				// atualiza a lista de item (quantidade de saida)					
				for (ItemVenda iv : this.getVenda().getItensVendas()) {
					DAO<Item> iDAO = new DAO<Item>(Item.class);
					Item i = iv.getItem();

					i.setQuantidadeSaida(i.getQuantidadeSaida()+iv.getQuantidade());
					iDAO.atualiza(i);
					System.out.println("...atualizou quantidade de saida do item");
				}	

				Msg.addMsgInfo("VENDA EFETUADA COM SUCESSO");
				System.out.println("...venda efetuada com sucesso!!");
				dao.adiciona(venda);
				itemVenda = new ItemVenda();
				venda = new Venda();

				return "/pages/venda/efetuarVendaAdmin2.xhtml?faces-redirect=true";

			} else {
				Msg.addMsgFatal("HA NA LISTA UM PRODUTO COM QUANTIDADE INDISPONIVEL NO ESTOQUE, " +
						"COM ISSO NAO SERA POSSIVEL EFETUAR A VENDA. VERIFIQUE A QUANTIDADE DISPONIVEL NO ESTOQUE!");
				System.out.println("...quantidade nao disponivel no estoque");
				itemVenda = new ItemVenda();
				this.cadastro = true;
			}
		}
		return null;
	}

	// metodo para adicionar produto a venda
	public void guardaItem() {
		boolean all = true;
		if (!all) {
			System.out.println("...Erro ao efetuar venda: inconsistencia nos dados do item");
		} else {
			for (ItemVenda i : this.getVenda().getItensVendas()) {
				if (getIdItem().equals(i.getItem().getId())) {
					this.cadastro = false;
					break;
				}
			}
			if (this.cadastro == true) {

				// este for verifica se a quantidade do produto informado esta disponivel no estoque
				// se for menor que 0, error!
				for (ItemVenda i : this.getVenda().getItensVendas()) {
					if (i.getItem().getQuantidadeAtual() - i.getQuantidade() <= -1) {
						this.cadastro = false;
						break;
					}
				}
				if (this.cadastro == true) {

					if (itemVenda.getQuantidade() == null || itemVenda.getQuantidade() == 0) {

						DAO<Item> dao = new DAO<Item>(Item.class);
						Item item = dao.buscaPorId(idItem);
						itemVenda.setItem(item);
						itemVenda.setQuantidade(1);

						venda.getItensVendas().add(itemVenda);
						itemVenda.setVenda(venda);

						itemVenda = new ItemVenda();
						System.out.println("...Produto adicionado a venda com qtd nao informada !! (ok)");

					} else {

						DAO<Item> dao = new DAO<Item>(Item.class);
						Item item = dao.buscaPorId(idItem);
						itemVenda.setItem(item);

						venda.getItensVendas().add(itemVenda);
						itemVenda.setVenda(venda);

						itemVenda = new ItemVenda();
						System.out.println("...Produto adicionado a venda com qtd superior a 1 !! (ok)");

					}

				} else {
					Msg.addMsgFatal("HA NA LISTA UM PRODUTO COM QUANTIDADE INDISPONIVEL NO ESTOQUE, " +
							"COM ISSO NAO SERA POSSIVEL EFETUAR A VENDA. VERIFIQUE A QUANTIDADE DISPONIVEL NO ESTOQUE!");
					System.out.println("...quantidade nao disponivel no estoque");
					itemVenda = new ItemVenda();
					this.cadastro = true;
				}

			} else {
				Msg.addMsgError("PRODUTO NAO PODE SER ADICIONADO NOVAMENTE");
				System.out.println("...produto nao pode ser adicionado novamente");
				itemVenda = new ItemVenda();
				this.cadastro = true;
			}
		}
	}

	// m�todo para editar/alterar venda
	public String desativarVenda() {
		if (venda.getId()!=null && loginBean.getUsuario().getPerfil().getId()==1) {
			DAO<Venda> dao = new DAO<Venda>(Venda.class);
			Msg.addMsgInfo("Venda desativada");
			venda.setAtivado(false);
			dao.atualiza(venda);
			venda = new Venda();
			return "/pages/venda/consultarVenda.xhtml?faces-redirect=true";
		} else {
			System.out.println("...Erro: nao foi possivel desativar venda");
			Msg.addMsgError("SOMENTE PODERÁ DESATIVAR VENDA USUARIO COM PERFIL ADMINISTRADOR");
			return null;
		}
	}

	// metodo para remover o item da lista de itens
	public void removeItemVenda() {
		venda.getItensVendas().remove(itemVenda);
		Msg.addMsgWarn("PRODUTO REMOVIDO DA LISTA");
		System.out.println("...Item removido da venda");

		itemVenda = new ItemVenda();
	}

	// m�todo booleano para informar ao usu�rio a quantidade indispon�vel no
	// estoque
	public Boolean quantidadeN() {
		if (venda.getItensVendas().get(0).getItem().getQuantidadeSaida()
				- venda.getItensVendas().get(1).getItem().getQuantidadeSaida()
				- itemVenda.getQuantidade() < 0) {
			System.out.println("...quantidade negativa");
			setQuantidadeN(true);
			return true;
		} else {
			System.out.println("...quantidade normal");
			setQuantidadeN(false);
			return false;
		}
	}	

	// cancelar venda (para perfil VENDEDOR)
	public String cancelarVenda() {
		if (venda.getCliente().getNome() != null
				|| !venda.getItensVendas().isEmpty()) {
			Msg.addMsgFatal("VENDA CANCELADA");
			return "efetuarVenda.xhtml";
		} else {
			return "efetuarVenda.xhtml?faces-redirect=true";
		}

	}

	// cancelar venda (para perfil GERENTE E ADM)
	public String cancelarVenda2() {
		if (!venda.getItensVendas().isEmpty()) {
			Msg.addMsgFatal("VENDA CANCELADA");
			return "efetuarVenda.xhtml";
		} else {
			return "efetuarVenda.xhtml?faces-redirect=true";
		}

	}

	// cancelar VENDA
	public String cancelarVenda3() {
		if (venda.getCliente().getNome() != null
				|| !venda.getItensVendas().isEmpty()) {
			Msg.addMsgFatal("OPERACAO CANCELADA");
			return "efetuarVendaAdmin.xhtml";
		} else {
			return "efetuarVendaAdmin.xhtml?faces-redirect=true";
		}

	}

	// cancelar venda/saida
	public String cancelarVenda4() {
		Msg.addMsgFatal("OPERACAO CANCELADA");
		return "efetuarVendaAdmin.xhtml";
	}

	// relatorio
	public void imprimirCupom() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		Report report = new Report("Cupom-Fiscal", params);
		report.pdfReport();
		System.out.println("...imprimindo cupom fiscal");
	}

	/** calculo **/

	// exibir o total R$ dos produtos
	public Double getTotal() {
		if (itemVenda.getQuantidade() != null
				&& itemVenda.getItem().getValorVenda() != null)
			return itemVenda.getQuantidade()
					* itemVenda.getItem().getValorVenda();
		else
			return null;
	}

	// exibir a soma do total dos produtos
	public Double getValorTotalProdutos() {
		setTotalValor(00.00);
		for (ItemVenda i : getVenda().getItensVendas()) {
			setTotalValor(getTotalValor() + i.getTotal());
		}
		return totalValor;
	}

	// calculo no valor com desconto
	public Double getValorTotalComDesconto() {
		setTotalValor(00.00);
		if (getValorTotalProdutos() != null && getDesconto1() != null)
			return getValorTotalProdutos() - getDesconto1();
		else
			return null;
	}

	// informa o percentual de desconto
	public Double getDesconto1() {
		if (totalValor != null && desconto != null)
			return totalValor * desconto / 100;
		else
			return null;
	}

	// gerar desconto
	public void gerarDesconto() {
		itemVenda = new ItemVenda();
	}

	// Fluxo de caixa
	public Double getValorTotalEntrada() {
		setTotalValor(00.00);
		for (Venda v : getCaixaEntrada()) {
			setTotalValor(getTotalValor() + v.getValorTotalDesconto());
		}
		return totalValor;
	}

	// Total de compras do Cliente
	public Double getValorTotalCliente() {
		setTotalValor(00.00);
		if (getVendas1() == null) {
			setTotalValor(00.00);
		}else {
			for (Venda v : getVendas1()) {
				setTotalValor(getTotalValor() + v.getValorTotalDesconto());
			}
			return totalValor;
		}
		return totalValor;
	}

	// total de dinheiro retirado
	public Double getValorTotalSaida() {
		setTotalValor(00.00);
		for (Venda v : getCaixaSaida()) {
			setTotalValor(getTotalValor() + v.getValorTotalDesconto());
		}
		return totalValor;
	}

	// total de lucro
	public Double getLucro() {
		setTotalValor(00.00);
		if (getValorTotalSaida() != null && getValorTotalEntrada() != null)
			return getValorTotalEntrada() - getValorTotalSaida();
		else
			return null;
	}

	public Boolean getResult() {
		if (getLucro() >= 0.00)
			return true;
		else
			return false;
	}

	// filtragem para fechar o caixa
	@SuppressWarnings("unchecked")
	public String getCaixaByDate() {
		if (getDataFinal().before(getDataInicial())) {
			Msg.addMsgError("PERIODO INVALIDO");
		} else {
			try {
				Query query = dao
						.query("SELECT v FROM Venda v WHERE v.dataVenda between ? and ? order by v.dataVenda");
				query.setParameter(1, getDataInicial());
				query.setParameter(2, getDataFinal());
				vendas = query.getResultList();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("... erro na consulta do caixa");
			}
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public String getCaixaByDate2() {

		try {
			Query query = dao
					.query("SELECT v FROM Venda v WHERE v.dataVenda = ?");
			query.setParameter(1, venda.getDataVenda());
			vendas = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("... erro na consulta do caixa");
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public String getCaixaByPeriodo() {
		if (getDataFinal().before(getDataInicial())) {
			Msg.addMsgError("PERIODO INVALIDO");
		} else {

			try {
				Query query = dao.query("SELECT v FROM Venda v WHERE v.dataVenda between ? and ? and v.operacao=1"
						+ "and v.tituloObs!=8 order by v.dataVenda");
				query.setParameter(1, getDataInicial());
				query.setParameter(2, getDataFinal());
				vendas1 = query.getResultList();
				if (vendas1.isEmpty()) {
					init();
					Msg.addMsgError("NENHUMA VENDA EFETUADA NO PERIODO INFORMADO");
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("... erro na consulta do caixa");
			}
			return null;
		}
		return null;
	}

	// para o relatorio de venda por periodo
	@SuppressWarnings("unchecked")
	public String getVendaByPeriodo() {
		if (getDataFinal().before(getDataInicial())) {
			Msg.addMsgError("PERIODO INVALIDO");
		} else {
			if(venda.getFormaPagamento().equals(0)) {
				try {
					Query query = dao.query("SELECT v FROM Venda v WHERE v.dataVenda between ? and ? and v.operacao=1"
							+ "and v.tituloObs!=8 order by v.dataVenda");
					query.setParameter(1, getDataInicial());
					query.setParameter(2, getDataFinal());
					vendas1 = query.getResultList();
					if (vendas1.isEmpty()) {
						init();
						Msg.addMsgError("NENHUMA VENDA EFETUADA NO PERIODO INFORMADO");
					}

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("... erro na consulta do caixa");
				}
				return null;
			} else {
				try {
					Query query = dao.query("SELECT v FROM Venda v WHERE v.dataVenda between ? and ? and v.formaPagamento = ? and v.operacao=1"
							+ "and v.tituloObs!=8 order by v.dataVenda");
					query.setParameter(1, getDataInicial());
					query.setParameter(2, getDataFinal());
					query.setParameter(3, venda.getFormaPagamento());
					vendas1 = query.getResultList();
					if (vendas1.isEmpty()) {
						init();
						Msg.addMsgError("NENHUMA VENDA EFETUADA NO PERIODO INFORMADO");
					}

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("... erro na consulta do caixa");
				}
				return null;
			}
		}
		return null;

	} 

	/** get and set **/

	public List<ItemVenda> getItensVendas() {
		return itensVendas;
	}

	public void setItensVendas(List<ItemVenda> itensVendas) {
		this.itensVendas = itensVendas;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public ItemVenda getItemVenda() {
		return itemVenda;
	}

	public void setItemVenda(ItemVenda itemVenda) {
		this.itemVenda = itemVenda;
	}

	public Long getIdItem() {
		return idItem;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public List<Venda> getVendas1() {
		return vendas1;
	}

	public void setVendas1(List<Venda> vendas1) {
		this.vendas1 = vendas1;
	}

	public DAO<Venda> getDao() {
		return dao;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public void setDao(DAO<Venda> dao) {
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

	public void setTotalValor(Double totalValor) {
		this.totalValor = totalValor;
	}

	public Boolean getCadastro() {
		return cadastro;
	}

	public void setCadastro(Boolean cadastro) {
		this.cadastro = cadastro;
	}

	public Integer getBox4Search() {
		return box4Search;
	}

	public void setBox4Search(Integer box4Search) {
		this.box4Search = box4Search;
	}

	public Validator<Venda> getValidator() {
		return validator;
	}

	public void setValidator(Validator<Venda> validator) {
		this.validator = validator;
	}

	public Double getTotalValorDesconto() {
		return totalValorDesconto;
	}

	public void setTotalValorDesconto(Double totalValorDesconto) {
		this.totalValorDesconto = totalValorDesconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getDesconto() {
		return desconto;
	}

	public Calendar getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Calendar dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Calendar getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Calendar dataFinal) {
		this.dataFinal = dataFinal;
	}

	public void setCaixaEntrada(List<Venda> caixaEntrada) {
		this.caixaEntrada = caixaEntrada;
	}

	public void setCaixaSaida(List<Venda> caixaSaida) {
		this.caixaSaida = caixaSaida;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public DAO<Item> getDao2() {
		return dao2;
	}

	public void setDao2(DAO<Item> dao2) {
		this.dao2 = dao2;
	}

	public Boolean getQuantidadeN() {
		return quantidadeN;
	}

	public void setQuantidadeN(Boolean quantidadeN) {
		this.quantidadeN = quantidadeN;
	}


}
