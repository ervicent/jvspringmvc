package ufrr.editora.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Email;

import ufrr.editora.converter.BaseEntity;

@Entity
@Table(name="tb_usuario")
public class Usuario implements Serializable, BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String cpf;
	
	@Column(name="data_nascimento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date nascimento;
	
	private String universitario; //ALUNO, PROFESSOR, SERVIDOR UFRR, OUTROS
	
	@Column(name="local_trabalho")
	private String localTrabalho;
	
	private String telefone1;
	
	private String telefone2;
	
	@ManyToOne
	private Categoria preferencia;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;
	
	@Email
	private String login; // login = Pessoa.email
	
	private String senha;
	
	@Transient
	private String repetirSenha;
	
	private Boolean status; // ativa
	
	private Boolean aceitaSolicitacao; // n�o aceita solicita��o
	
	@Column(name="esquici_senha")
	private Boolean esqueciSenha;
	
	@ManyToOne
	@JoinColumn(name="perfil_id", columnDefinition="bigint default 4", insertable=false, updatable=true)
	private Perfil perfil;
	
	@Temporal(TemporalType.DATE)
	private Calendar data = Calendar.getInstance(); // data do dia que efetuo cadastro
	
	@Transient
	@OneToMany(cascade=CascadeType.ALL, mappedBy="usuario")
	private Collection<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
	
	@Transient
	@OneToMany(cascade=CascadeType.ALL, mappedBy="usuario")
	private Collection<Produto> produtos = new ArrayList<Produto>();
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="usuario")
	private Collection<Mensagem> mensagens = new ArrayList<Mensagem>();
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="autorEnvio")
	private Collection<EnviaEmail> emails = new ArrayList<EnviaEmail>();
	
	@Transient
	@OneToMany(cascade=CascadeType.ALL, mappedBy="usuario")
	private Collection<TipoProduto> tipos = new ArrayList<TipoProduto>();
	
	@Transient
	@OneToMany(cascade=CascadeType.ALL, mappedBy="usuario")
	private Collection<Categoria> categorias = new ArrayList<Categoria>();
	
	@Transient
	@OneToMany(cascade=CascadeType.ALL, mappedBy="usuario")
	private Collection<NotaFiscal> notasFiscais = new ArrayList<NotaFiscal>();
	
	@Transient
	@OneToMany(cascade=CascadeType.ALL, mappedBy="usuario")
	private Collection<Venda> vendas = new ArrayList<Venda>();
	
	@Transient
	@OneToMany(cascade=CascadeType.ALL, mappedBy="usuario")
	private Collection<Item> itens = new ArrayList<Item>();
	
	@Transient
	@OneToMany(cascade=CascadeType.ALL, mappedBy="usuario")
	private Collection<ItemDevolvido> itensDevolvidos = new ArrayList<ItemDevolvido>();
		
	
//	get and set

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login.toLowerCase();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Collection<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Collection<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public Collection<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Collection<Produto> produtos) {
		this.produtos = produtos;
	}

	public String getRepetirSenha() {
		return repetirSenha;
	}

	public void setRepetirSenha(String repetirSenha) {
		this.repetirSenha = repetirSenha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getUniversitario() {
		return universitario;
	}

	public void setUniversitario(String universitario) {
		this.universitario = universitario;
	}

	public String getLocalTrabalho() {
		return localTrabalho;
	}

	public void setLocalTrabalho(String localTrabalho) {
		this.localTrabalho = localTrabalho.toUpperCase();
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public Collection<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(Collection<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Collection<EnviaEmail> getEmails() {
		return emails;
	}

	public void setEmails(Collection<EnviaEmail> emails) {
		this.emails = emails;
	}

	public Collection<TipoProduto> getTipos() {
		return tipos;
	}

	public void setTipos(Collection<TipoProduto> tipos) {
		this.tipos = tipos;
	}

	public Collection<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(Collection<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Collection<NotaFiscal> getNotasFiscais() {
		return notasFiscais;
	}

	public void setNotasFiscais(Collection<NotaFiscal> notasFiscais) {
		this.notasFiscais = notasFiscais;
	}
		
	public Collection<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(Collection<Venda> vendas) {
		this.vendas = vendas;
	}
	
	public Categoria getPreferencia() {
		return preferencia;
	}

	public void setPreferencia(Categoria preferencia) {
		this.preferencia = preferencia;
	}

	
	public Boolean getEsqueciSenha() {
		return esqueciSenha;
	}

	public void setEsqueciSenha(Boolean esqueciSenha) {
		this.esqueciSenha = esqueciSenha;
	}

	public Boolean getAceitaSolicitacao() {
		return aceitaSolicitacao;
	}

	public void setAceitaSolicitacao(Boolean aceitaSolicitacao) {
		this.aceitaSolicitacao = aceitaSolicitacao;
	}
	
	public Collection<Item> getItens() {
		return itens;
	}

	public void setItens(Collection<Item> itens) {
		this.itens = itens;
	}
		
	public Collection<ItemDevolvido> getItensDevolvidos() {
		return itensDevolvidos;
	}

	public void setItensDevolvidos(Collection<ItemDevolvido> itensDevolvidos) {
		this.itensDevolvidos = itensDevolvidos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((aceitaSolicitacao == null) ? 0 : aceitaSolicitacao
						.hashCode());
		result = prime * result
				+ ((categorias == null) ? 0 : categorias.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((emails == null) ? 0 : emails.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result
				+ ((esqueciSenha == null) ? 0 : esqueciSenha.hashCode());
		result = prime * result
				+ ((fornecedores == null) ? 0 : fornecedores.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itens == null) ? 0 : itens.hashCode());
		result = prime * result
				+ ((itensDevolvidos == null) ? 0 : itensDevolvidos.hashCode());
		result = prime * result
				+ ((localTrabalho == null) ? 0 : localTrabalho.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((mensagens == null) ? 0 : mensagens.hashCode());
		result = prime * result
				+ ((nascimento == null) ? 0 : nascimento.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((notasFiscais == null) ? 0 : notasFiscais.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
		result = prime * result
				+ ((preferencia == null) ? 0 : preferencia.hashCode());
		result = prime * result
				+ ((produtos == null) ? 0 : produtos.hashCode());
		result = prime * result
				+ ((repetirSenha == null) ? 0 : repetirSenha.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((telefone1 == null) ? 0 : telefone1.hashCode());
		result = prime * result
				+ ((telefone2 == null) ? 0 : telefone2.hashCode());
		result = prime * result + ((tipos == null) ? 0 : tipos.hashCode());
		result = prime * result
				+ ((universitario == null) ? 0 : universitario.hashCode());
		result = prime * result + ((vendas == null) ? 0 : vendas.hashCode());
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
		Usuario other = (Usuario) obj;
		if (aceitaSolicitacao == null) {
			if (other.aceitaSolicitacao != null)
				return false;
		} else if (!aceitaSolicitacao.equals(other.aceitaSolicitacao))
			return false;
		if (categorias == null) {
			if (other.categorias != null)
				return false;
		} else if (!categorias.equals(other.categorias))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (emails == null) {
			if (other.emails != null)
				return false;
		} else if (!emails.equals(other.emails))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (esqueciSenha == null) {
			if (other.esqueciSenha != null)
				return false;
		} else if (!esqueciSenha.equals(other.esqueciSenha))
			return false;
		if (fornecedores == null) {
			if (other.fornecedores != null)
				return false;
		} else if (!fornecedores.equals(other.fornecedores))
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
		if (itensDevolvidos == null) {
			if (other.itensDevolvidos != null)
				return false;
		} else if (!itensDevolvidos.equals(other.itensDevolvidos))
			return false;
		if (localTrabalho == null) {
			if (other.localTrabalho != null)
				return false;
		} else if (!localTrabalho.equals(other.localTrabalho))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (mensagens == null) {
			if (other.mensagens != null)
				return false;
		} else if (!mensagens.equals(other.mensagens))
			return false;
		if (nascimento == null) {
			if (other.nascimento != null)
				return false;
		} else if (!nascimento.equals(other.nascimento))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (notasFiscais == null) {
			if (other.notasFiscais != null)
				return false;
		} else if (!notasFiscais.equals(other.notasFiscais))
			return false;
		if (perfil == null) {
			if (other.perfil != null)
				return false;
		} else if (!perfil.equals(other.perfil))
			return false;
		if (preferencia == null) {
			if (other.preferencia != null)
				return false;
		} else if (!preferencia.equals(other.preferencia))
			return false;
		if (produtos == null) {
			if (other.produtos != null)
				return false;
		} else if (!produtos.equals(other.produtos))
			return false;
		if (repetirSenha == null) {
			if (other.repetirSenha != null)
				return false;
		} else if (!repetirSenha.equals(other.repetirSenha))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (telefone1 == null) {
			if (other.telefone1 != null)
				return false;
		} else if (!telefone1.equals(other.telefone1))
			return false;
		if (telefone2 == null) {
			if (other.telefone2 != null)
				return false;
		} else if (!telefone2.equals(other.telefone2))
			return false;
		if (tipos == null) {
			if (other.tipos != null)
				return false;
		} else if (!tipos.equals(other.tipos))
			return false;
		if (universitario == null) {
			if (other.universitario != null)
				return false;
		} else if (!universitario.equals(other.universitario))
			return false;
		if (vendas == null) {
			if (other.vendas != null)
				return false;
		} else if (!vendas.equals(other.vendas))
			return false;
		return true;
	}

	
	
	
			
}
