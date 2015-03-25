package ufrr.editora.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ufrr.editora.converter.BaseEntity;

@Entity
@Table(name = "tb_item")
public class Item implements Serializable, BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer quantidadeEntrada;

	private Integer quantidadeSaida;

	private Double valorCusto;

	private Double valorVenda;

	@ManyToOne
	private Produto produto;

	@ManyToOne
	private NotaFiscal notaFiscal;

	@Transient
	private Double totalValor;
	
	@Transient
	@ManyToOne
	private Usuario usuario;
	
	@Transient
	@OneToOne(cascade = CascadeType.ALL)
	private ItemDevolvido devolvido;

	// get and set

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidadeEntrada() {
		return quantidadeEntrada;
	}

	public void setQuantidadeEntrada(Integer quantidadeEntrada) {
		this.quantidadeEntrada = quantidadeEntrada;
	}

	public Integer getQuantidadeSaida() {
		return quantidadeSaida;
	}

	public void setQuantidadeSaida(Integer quantidadeSaida) {
		this.quantidadeSaida = quantidadeSaida;
	}

	public Double getValorCusto() {
		return valorCusto;
	}

	public void setValorCusto(Double valorCusto) {
		this.valorCusto = valorCusto;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Double getTotalValor() {
		return totalValor;
	}

	public void setTotalValor(Double totalValor) {
		this.totalValor = totalValor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ItemDevolvido getDevolvido() {
		return devolvido;
	}

	public void setDevolvido(ItemDevolvido devolvido) {
		this.devolvido = devolvido;
	}

	// vari�vel para exibir o total de quantidade atual
	public Double getTotal() {
		if (quantidadeEntrada != null && valorCusto != null)
			return quantidadeEntrada * valorCusto;
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
	
	// vari�vel para exibir o total de quantidade atual
	public Integer getQuantidadeAtual() {
		if (quantidadeEntrada != null && quantidadeSaida != null)
			return quantidadeEntrada - quantidadeSaida;
		else
			return null;
	}
	
	public Integer getQuantidadeAtual2() {
		if (getQuantidadeAtual() <= getProduto().getQuantidadeMinima())
			return getQuantidadeAtual();
		else
			return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((devolvido == null) ? 0 : devolvido.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((notaFiscal == null) ? 0 : notaFiscal.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime
				* result
				+ ((quantidadeEntrada == null) ? 0 : quantidadeEntrada
						.hashCode());
		result = prime * result
				+ ((quantidadeSaida == null) ? 0 : quantidadeSaida.hashCode());
		result = prime * result
				+ ((totalValor == null) ? 0 : totalValor.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result
				+ ((valorCusto == null) ? 0 : valorCusto.hashCode());
		result = prime * result
				+ ((valorVenda == null) ? 0 : valorVenda.hashCode());
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
		Item other = (Item) obj;
		if (devolvido == null) {
			if (other.devolvido != null)
				return false;
		} else if (!devolvido.equals(other.devolvido))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (notaFiscal == null) {
			if (other.notaFiscal != null)
				return false;
		} else if (!notaFiscal.equals(other.notaFiscal))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (quantidadeEntrada == null) {
			if (other.quantidadeEntrada != null)
				return false;
		} else if (!quantidadeEntrada.equals(other.quantidadeEntrada))
			return false;
		if (quantidadeSaida == null) {
			if (other.quantidadeSaida != null)
				return false;
		} else if (!quantidadeSaida.equals(other.quantidadeSaida))
			return false;
		if (totalValor == null) {
			if (other.totalValor != null)
				return false;
		} else if (!totalValor.equals(other.totalValor))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (valorCusto == null) {
			if (other.valorCusto != null)
				return false;
		} else if (!valorCusto.equals(other.valorCusto))
			return false;
		if (valorVenda == null) {
			if (other.valorVenda != null)
				return false;
		} else if (!valorVenda.equals(other.valorVenda))
			return false;
		return true;
	}	
	
	
	
}
