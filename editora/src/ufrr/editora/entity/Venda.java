package ufrr.editora.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="tb_venda")
public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
		
	@Temporal(TemporalType.DATE)
	private Calendar dataVenda = Calendar.getInstance();
				
	private Integer formaPagamento;
	
	private Integer quantidadeParcela;
	
	private Double valorTotal; // exibe o valor da venda
	
	private Double valorTotalDesconto; // valor da venda com desconto

	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="venda")
	private List<ItemVenda> itensVendas = new ArrayList<ItemVenda>();
	
	@Transient
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="venda")
	private List<Item> itens = new ArrayList<Item>();
	
	@ManyToOne
	private Usuario cliente;
	
	@ManyToOne
	private Usuario vendedor;
	
	private String obs;
	
	private Integer tituloObs;
	
	private Integer operacao; // 1=entrada, 2=saida
	
	private Boolean ativado;
	
	//get and set

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Calendar dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Integer getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(Integer formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Integer getQuantidadeParcela() {
		return quantidadeParcela;
	}

	public void setQuantidadeParcela(Integer quantidadeParcela) {
		this.quantidadeParcela = quantidadeParcela;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Double getValorTotalDesconto() {
		return valorTotalDesconto;
	}

	public void setValorTotalDesconto(Double valorTotalDesconto) {
		this.valorTotalDesconto = valorTotalDesconto;
	}


	public List<ItemVenda> getItensVendas() {
		return itensVendas;
	}

	public void setItensVendas(List<ItemVenda> itensVendas) {
		this.itensVendas = itensVendas;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public Usuario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Integer getTituloObs() {
		return tituloObs;
	}

	public void setTituloObs(Integer tituloObs) {
		this.tituloObs = tituloObs;
	}

	public Integer getOperacao() {
		return operacao;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public void setOperacao(Integer operacao) {
		this.operacao = operacao;
	}
	
	public Boolean getAtivado() {
		return ativado;
	}

	public void setAtivado(Boolean ativado) {
		this.ativado = ativado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result
				+ ((dataVenda == null) ? 0 : dataVenda.hashCode());
		result = prime * result
				+ ((formaPagamento == null) ? 0 : formaPagamento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itens == null) ? 0 : itens.hashCode());
		result = prime * result
				+ ((itensVendas == null) ? 0 : itensVendas.hashCode());
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime * result
				+ ((operacao == null) ? 0 : operacao.hashCode());
		result = prime
				* result
				+ ((quantidadeParcela == null) ? 0 : quantidadeParcela
						.hashCode());
		result = prime * result
				+ ((tituloObs == null) ? 0 : tituloObs.hashCode());
		result = prime * result
				+ ((valorTotal == null) ? 0 : valorTotal.hashCode());
		result = prime
				* result
				+ ((valorTotalDesconto == null) ? 0 : valorTotalDesconto
						.hashCode());
		result = prime * result
				+ ((vendedor == null) ? 0 : vendedor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (dataVenda == null) {
			if (other.dataVenda != null)
				return false;
		} else if (!dataVenda.equals(other.dataVenda))
			return false;
		if (formaPagamento == null) {
			if (other.formaPagamento != null)
				return false;
		} else if (!formaPagamento.equals(other.formaPagamento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itens == null) {
			if (other.itens != null)
				return false;
		} else if (!itens.equals(other.itens))
			return false;
		if (itensVendas == null) {
			if (other.itensVendas != null)
				return false;
		} else if (!itensVendas.equals(other.itensVendas))
			return false;
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		if (operacao == null) {
			if (other.operacao != null)
				return false;
		} else if (!operacao.equals(other.operacao))
			return false;
		if (quantidadeParcela == null) {
			if (other.quantidadeParcela != null)
				return false;
		} else if (!quantidadeParcela.equals(other.quantidadeParcela))
			return false;
		if (tituloObs == null) {
			if (other.tituloObs != null)
				return false;
		} else if (!tituloObs.equals(other.tituloObs))
			return false;
		if (valorTotal == null) {
			if (other.valorTotal != null)
				return false;
		} else if (!valorTotal.equals(other.valorTotal))
			return false;
		if (valorTotalDesconto == null) {
			if (other.valorTotalDesconto != null)
				return false;
		} else if (!valorTotalDesconto.equals(other.valorTotalDesconto))
			return false;
		if (vendedor == null) {
			if (other.vendedor != null)
				return false;
		} else if (!vendedor.equals(other.vendedor))
			return false;
		return true;
	}
	
}
