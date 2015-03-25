package ufrr.editora.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_item_venda")
public class ItemVenda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Item item;
	
	@ManyToOne
	private Venda venda;

	private Integer quantidade;
	
	@Transient
	private Boolean quantidadeN; // variável para não deixar cadastrar produtos com quantidade maior ao estoque

	// get and set

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	public Boolean getQuantidadeN() {
		if (item.getQuantidadeEntrada() - item.getQuantidadeSaida() - quantidade <= 0) 
			return true;
		else 
			return false;
	}

	public void setQuantidadeN(Boolean quantidadeN) {
		this.quantidadeN = quantidadeN;
	}

	// variável para exibir o total valor do produto
	public Double getTotal() {
		if (quantidade != null && item.getValorVenda() != null)
			return quantidade * item.getValorVenda();
		else
			return null;
	}
	
	// variável para exibir o total valor do produto
	public Double getTotalProdutoVenda() {
		if (quantidade != null && item.getValorCusto() != null)
			return quantidade * item.getValorCusto();
		else
			return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result
				+ ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result
				+ ((quantidadeN == null) ? 0 : quantidadeN.hashCode());
		result = prime * result + ((venda == null) ? 0 : venda.hashCode());
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
		ItemVenda other = (ItemVenda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (quantidadeN == null) {
			if (other.quantidadeN != null)
				return false;
		} else if (!quantidadeN.equals(other.quantidadeN))
			return false;
		if (venda == null) {
			if (other.venda != null)
				return false;
		} else if (!venda.equals(other.venda))
			return false;
		return true;
	}	
	
	

}
