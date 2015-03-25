package ufrr.editora.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ufrr.editora.converter.BaseEntity;

@Entity
@Table(name="tb_produto")
public class Produto implements Serializable, BaseEntity {
		
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome; // SE O PRODUTO FOR LIVRO ESTE VAI SER O TITULO DA OBRA
	
	private String editora;
	
	private String autor;
	
	private Long isbn; // CODIGO DE BARRAS COM 13 DIGITOS
	
	private Integer quantidadeMinima; // QUANTIDADE MÍNIMA NO ESTOQUE
	
	private Boolean ativado;
	
	private Integer edicao;
	
	private String colecao;
	
	@ManyToOne
	@JoinColumn(name="tipo_id")
	private TipoProduto tipo;
	
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	@ManyToOne
	private Usuario usuario;

	
	 /** Get and Set **/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	public TipoProduto getTipo() {
		return tipo;
	}

	public void setTipo(TipoProduto tipo) {
		this.tipo = tipo;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora.toUpperCase();
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor.toUpperCase();
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public Integer getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public void setQuantidadeMinima(Integer quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean getAtivado() {
		return ativado;
	}

	public void setAtivado(Boolean ativado) {
		this.ativado = ativado;
	}

	public Integer getEdicao() {
		return edicao;
	}

	public void setEdicao(Integer edicao) {
		this.edicao = edicao;
	}

	public String getColecao() {
		return colecao;
	}

	public void setColecao(String colecao) {
		this.colecao = colecao.toUpperCase();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ativado == null) ? 0 : ativado.hashCode());
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result
				+ ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((editora == null) ? 0 : editora.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime
				* result
				+ ((quantidadeMinima == null) ? 0 : quantidadeMinima.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Produto other = (Produto) obj;
		if (ativado == null) {
			if (other.ativado != null)
				return false;
		} else if (!ativado.equals(other.ativado))
			return false;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (editora == null) {
			if (other.editora != null)
				return false;
		} else if (!editora.equals(other.editora))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (quantidadeMinima == null) {
			if (other.quantidadeMinima != null)
				return false;
		} else if (!quantidadeMinima.equals(other.quantidadeMinima))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}
