package ufrr.editora.mb;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import ufrr.editora.dao.DAO;
import ufrr.editora.entity.Endereco;
import ufrr.editora.entity.EnviaEmail;
import ufrr.editora.entity.Perfil;
import ufrr.editora.entity.Usuario;
import ufrr.editora.util.EmailUtils;
import ufrr.editora.util.Msg;
import ufrr.editora.util.TransformaStringMD5;
import ufrr.editora.validator.Validator;

@ViewScoped
@ManagedBean
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean login;

	private EntityManager entityManager;

	private String senhaCriptografada;

	private Usuario usuario = new Usuario();
	private Perfil perfil = new Perfil();
	private Endereco endereco = new Endereco();
	private List<Perfil> perfis;
	private List<Usuario> usuarios;
	private List<Usuario> usuariosE;
	private List<Usuario> usuariosEmpty; //lista em branco
	private Validator<Usuario> validator;
	private String search;
	private Integer box4Search;

	private EnviaEmail email = new EnviaEmail();
	private DAO<EnviaEmail> dao2 = new DAO<EnviaEmail>(EnviaEmail.class);
	private List<EnviaEmail> emails;

	DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
	private Long usuarioId;

	public Date getPegaDataAtual() {  
		Calendar calendar = new GregorianCalendar();  
		Date date = new Date();  
		calendar.setTime(date);  
		return calendar.getTime();  
	}  

	@Temporal(TemporalType.DATE)
	private Calendar dataAtual = Calendar.getInstance(); // data de hoje

	public Boolean cadastro = true;

	@PostConstruct
	public void init() {

		usuario = new Usuario();
		endereco = new Endereco();
		validator = new Validator<Usuario>(Usuario.class);
		search = "";
		box4Search = 1;
		box4Search = 2;
		box4Search = 3;
	}

	/** Funcao para criar hash da senha informada **/
	public static String md5(String senha) {
		String sen = "";
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
		sen = hash.toString(16);
		return sen;
	}

	public void carregaUsuario() {

		DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
		if (usuarioId != null && usuarioId != 0) {
			this.usuario = dao.buscaPorId(this.usuarioId);
		}
	}

	/** AutoComplets **/

	// AutoComplete Login
	public List<String> autocompletelogin(String nome) {
		List<Usuario> array = dao.getAllByName("login", nome);
		ArrayList<String> nomes = new ArrayList<String>();
		for (int i = 0; i < array.size(); i++) {
			nomes.add(array.get(i).getLogin());
		}
		return nomes;
	}

	// AutoComplete Nome
	public List<String> autocompletenome(String nome) {
		List<Usuario> array = dao.getAllByName("nome", nome);
		ArrayList<String> nomes = new ArrayList<String>();
		for (int i = 0; i < array.size(); i++) {
			nomes.add(array.get(i).getNome());
		}
		return nomes;
	}

	// AutoComplete clientes
	public List<String> autocompleteClientes(String nome) {
		List<Usuario> array = dao.getAllByName("nome", nome);
		ArrayList<String> nomes = new ArrayList<String>();
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i).getPerfil().getId() == 4) {
				nomes.add(array.get(i).getNome());
			}
		}
		return nomes;
	}

	// AutoComplete vendedor
	public List<String> autocompleteVendedor(String nome) {
		List<Usuario> array = dao.getAllByName("nome", nome);
		ArrayList<String> nomes = new ArrayList<String>();
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i).getPerfil().getId() <= 3
					&& array.get(i).getId() != 1) {
				nomes.add(array.get(i).getNome());
			}
		}
		return nomes;
	}

	// AutoComplete cpf
	public List<String> autocompleteCPF(String nome) {
		List<Usuario> array = dao.getAllByName("cpf", nome);
		ArrayList<String> nomes = new ArrayList<String>();
		for (int i = 0; i < array.size(); i++) {
			nomes.add(array.get(i).getCpf());
		}
		return nomes;
	}

	// AutoComplete Nome e CPF juntos (somente para Cliente)
	public List<String> autocomplete(String nome) {
		ArrayList<String> nomes = new ArrayList<String>();
		if (box4Search.equals(1)) {
			if (!search.contains("'")) {
				List<Usuario> array = dao.getAllByName("nome", nome);
				for (int i = 0; i < array.size(); i++) {
					if (array.get(i).getPerfil().getId() == 4 && array.get(i).getId() != 1) {
						nomes.add(array.get(i).getNome());
					}
				}
			}
		} else if (box4Search.equals(2)) {
			if (!search.contains("'")) {
				List<Usuario> array = dao.getAllByName("cpf", nome);
				for (int i = 0; i < array.size(); i++) {
					if (array.get(i).getPerfil().getId() == 4 && array.get(i).getId() != 1) {
						nomes.add(array.get(i).getCpf());
					}
				}
			}
		}
		return nomes;
	}

	/** List of Users **/

	public List<Usuario> getUsuarios() {
		if (usuarios == null) {
			System.out.println("Carregando usuarios...");
			usuarios = new DAO<Usuario>(Usuario.class).getAllOrder("nome, id");
		}
		return usuarios;
	}

	// Exibe uma lista com as solicitacoes de acesso
	@SuppressWarnings("unchecked")
	public List<Usuario> getSolicitacoes() {
		Query query = dao.query("SELECT u FROM Usuario u WHERE u.status = false AND u.aceitaSolicitacao = true AND u.perfil = 4");
		usuarios = query.getResultList();
		return query.getResultList();
	}

	// Exibe uma lista de usuario funcion�rios ativados
	@SuppressWarnings("unchecked")
	public List<Usuario> getAtivados() {
		Query query = dao
				.query("SELECT u FROM Usuario u WHERE u.status = true AND u.perfil <= 3 ORDER BY u.nome");
		usuarios = query.getResultList();
		return query.getResultList();
	}

	// Exibe uma lista de usuario ativados sem clientes e administrador
	@SuppressWarnings("unchecked")
	public List<Usuario> getAtivadosSemAdministrador() {
		Query query = dao
				.query("SELECT u FROM Usuario u WHERE u.status = true AND u.perfil <= 3 and u.perfil != 1 ORDER BY u.nome");
		usuarios = query.getResultList();
		return query.getResultList();
	}

	// Exibe uma lista de usu�rios desativados
	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuariosDesativados() {
		Query query = dao
				.query("SELECT u FROM Usuario u WHERE u.status = false AND u.perfil != 4 ORDER BY u.nome");
		usuarios = query.getResultList();
		return query.getResultList();
	}

	// Exibe uma lista de usu�rios desativados sem administrador
	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuariosDesativados2() {
		Query query = dao
				.query("SELECT u FROM Usuario u WHERE u.status = false AND u.perfil != 4 and u.perfil != 1 ORDER BY u.nome");
		usuarios = query.getResultList();
		return query.getResultList();
	}

	// Exibe uma lista de clientes ativados
	@SuppressWarnings("unchecked")
	public List<Usuario> getClientes() {
		Query query = dao.query("SELECT u FROM Usuario u WHERE u.status = true AND u.perfil = 4 AND u.id <> 1 ORDER BY u.nome");
		usuarios = query.getResultList();
		return query.getResultList();
	}

	// Exibe uma lista de clientes ativados para venda do vendedor e gerente
	@SuppressWarnings("unchecked")
	public List<Usuario> getClientes2() {
		Query query = dao.query("SELECT u FROM Usuario u WHERE u.status = true AND u.perfil = 4 ORDER BY u.nome");
		usuarios = query.getResultList();
		return query.getResultList();
	}

	// Exibe uma lista de clientes ativados
	@SuppressWarnings("unchecked")
	public List<Usuario> getClientesAdm() {
		Query query = dao.query("SELECT u FROM Usuario u WHERE u.status = true AND u.perfil <= 4 ORDER BY u.nome");
		usuarios = query.getResultList();
		return query.getResultList();
	}

	// Exibe uma lista de clientes no geral (ativados e desativados)
	public List<Usuario> getClientesCadastrados() {
		usuariosE = new ArrayList<Usuario>();
		List<Usuario> us = new ArrayList<Usuario>();
		us = this.getUsuarios();
		for (int i = 0; i < us.size(); i++) {
			if (us.get(i).getPerfil().getId() == 4 && us.get(i).getId() != 1) {
				usuariosE.add(us.get(i));
			}
		}
		return usuariosE;
	}

	// Exibe uma lista de clientes para relatorio de cliente por categoria
	public List<Usuario> getClientesCategoria() {
		usuariosE = new ArrayList<Usuario>();
		List<Usuario> us = new ArrayList<Usuario>();
		us = this.getUsuariosE();
		for (int i = 0; i < us.size(); i++) {
			if (us.get(i).getPerfil().getId() == 4 && us.get(i).getId() != 1) {
				usuariosE.add(us.get(i));
			}
		}
		return usuariosE;
	}

	//	where extract(month from u.data_nascimento) = extract(month from CURRENT_DATE)
	//	and extract(day from u.data_nascimento) = extract(day from CURRENT_DATE)

	// Exibe uma lista de clientes aniversatiantes do dia
	@SuppressWarnings("unchecked")
	public List<Usuario> getAniversariantes() {
		Query query = dao.query("SELECT u FROM Usuario u WHERE u.perfil = 4 AND u.id <> 1 " +
				"AND extract(month from u.nascimento) = extract(month from CURRENT_DATE)" +
				"AND extract(day from u.nascimento) = extract(day from CURRENT_DATE) ORDER BY u.nome");
		usuarios = query.getResultList();
		return query.getResultList();
	}

	public List<Usuario> getAniversariantes2() {
		usuariosE = new ArrayList<Usuario>();
		List<Usuario> us = new ArrayList<Usuario>();
		us = this.getUsuarios();
		for (int i = 0; i < us.size(); i++) {
			if (us.get(i).getPerfil().getId() == 4 && us.get(i).getId() != 1
					&& us.get(i).getData()==dataAtual) {
				usuariosE.add(us.get(i));
			}
		}
		return usuariosE;
	}

	// Exibe uma lista de clientes no geral (ativados e desativados) sem o id = 1
	public List<Usuario> getClientesCadastrados2() {
		usuariosE = new ArrayList<Usuario>();
		List<Usuario> us = new ArrayList<Usuario>();
		us = this.getUsuarios();
		for (int i = 0; i < us.size(); i++) {
			if (us.get(i).getStatus() == true && us.get(i).getId() == 2) {
				usuariosE.add(us.get(i));
			}
		}
		return usuariosE;
	}


	/** Consultas **/

	// pesquisa cliente pela categoria
	@SuppressWarnings("unchecked")
	public void getCategoria() {
		try {
			Query query = dao.query("SELECT u FROM Usuario u WHERE u.preferencia=?");
			query.setParameter(1, usuario.getPreferencia());
			usuariosEmpty = query.getResultList();
			if (usuariosEmpty.isEmpty()) {
				init();
				Msg.addMsgError("NENHUM REGISTRO ENCONTRADO");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// pesquisa nota pelo id
	@SuppressWarnings("unchecked")
	public void getClienteById() {
		if (usuario.getId() == null || usuario.getId() == 0) {
			Msg.addMsgError("INFORME CORRETAMENTE O CODIGO DO CLIENTE");

		} else {
			try {
				Query query = dao.query("SELECT u FROM Usuario u WHERE u.id=? and u.id != 1 and u.perfil = 4");
				query.setParameter(1, usuario.getId());
				usuarios = query.getResultList();
				if (usuarios.isEmpty()) {
					init();
					Msg.addMsgError("NENHUM REGISTRO ENCONTRADO");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Pesquisa usu�rio pelo nome
	@SuppressWarnings("unchecked")
	public String getListaUsuariosByName() {
		if (usuario.getNome().contains("'") || usuario.getNome().contains("@")
				|| usuario.getNome().contains("/")
				|| usuario.getNome().contains("*")
				|| usuario.getNome().contains("<")
				|| usuario.getNome().contains(">")
				|| usuario.getNome().contains("#")) {

			Msg.addMsgError("CONTEM CARACTER(ES) INVALIDO(s)");
			return null;
		}
		if (usuario.getNome().length() <= 2) {
			Msg.addMsgError("INFORME PELO MENOS 3 CARACTERES");
			return null;

		} else {
			usuarios = dao.getAllByName("nome", usuario.getNome());
			if (usuarios.isEmpty()) {
				Msg.addMsgError("NENHUM REGISTRO ENCONTRADO");
				return null;

			} else {
				System.out.println("Chegou Aqui... Processando informacoes...");
				try {
					Query query = dao
							.query("SELECT u FROM Usuario u WHERE u.nome LIKE ?");
					query.setParameter(1, usuario.getNome() + "%");
					usuarios = query.getResultList();
					System.out.println("...Usuario encontrado com sucesso");
				} catch (Exception e) {
					e.printStackTrace();
					System.out
					.println("...erro: Usuario nao pode ser pesquisado!");
				}
				return null;
			}
		}
	}

	// Pesquisa usu�rio pelo CPF
	@SuppressWarnings("unchecked")
	public String getListaUsuariosByCPF() {
		if (usuario.getNome().contains("'") || usuario.getNome().contains("@")
				|| usuario.getNome().contains("/")
				|| usuario.getNome().contains("*")
				|| usuario.getNome().contains("<")
				|| usuario.getNome().contains(">")
				|| usuario.getNome().contains("#")) {

			Msg.addMsgError("CONTEM CARACTER(ES) INVALIDO(S)");
			return null;
		}
		if (usuario.getCpf().length() != 14) {
			Msg.addMsgError("INFORME O CPF CORRETO");
			return null;

		} else {
			usuarios = dao.getAllByName("cpf", usuario.getCpf());
			if (usuarios.isEmpty()) {
				Msg.addMsgError("CPF NAO ENCONTRADO");
				return null;

			} else {
				System.out.println("Chegou Aqui... Processando informacoes...");
				try {
					Query query = dao
							.query("SELECT u FROM Usuario u WHERE u.cpf=? and u.perfil = 4");
					query.setParameter(1, usuario.getCpf());
					usuarios = query.getResultList();
					System.out.println("...Usuario encontrado com sucesso");
				} catch (Exception e) {
					e.printStackTrace();
					System.out
					.println("...erro: Usuario nao pode ser pesquisado!");
				}
				return null;
			}
		}
	}

	// Pesquisa Usuario pelo nome e cpf
	public String getListarClientes() {

		if (box4Search.equals(2)) {
			if (search.contains("'") || search.contains("@")
					|| search.contains("*")) {
				init();
				Msg.addMsgError("CONTEM CARACTER(ES) INVALIDOS(S)");
				return null;
			} else {
				if (search.length() <= 10) {
					init();
					Msg.addMsgError("INFORME O CPF CORRETAMENTE");
					System.out.println("...Informe o CPF corretamente para consultar cliente");
					return null;
				} else {
					usuarios = dao.getAllByName("obj.cpf", search);
					if (getClientesCadastrados().isEmpty()) {
						init();
						Msg.addMsgError("NENHUM REGISTRO ENCONTRADO");
						System.out.println("...Cliente nao encontrado");
						return null;
					} else {

					}
				}
			}
		} else if (box4Search.equals(1)) {
			if (search.contains("'") || search.contains("@")
					|| search.contains("*")
					|| search.contains(".")
					|| search.contains("/")
					|| search.contains("-")) {
				init();
				Msg.addMsgError("CONTEM CARACTER(ES) INVALIDO(S)");
				return null;
			} else {
				if (search.length() <= 4) {
					init();
					Msg.addMsgError("INFORME PELO MENOS 5 CARACTERES");
					System.out.println("...Informe pelo menos 5 caracteres para consultar cliente");
					return null;
				} else {
					usuarios = dao.getAllByName("obj.nome", search);
					if (getClientesCadastrados().isEmpty()) {
						init();
						Msg.addMsgError("NENHUM REGISTRO ENCONTRADO");
						System.out.println("...Cliente nao encontrado");
						return null;
					} else {

					}
				}
			}
		}
		return null;
	}

	/** Actions **/

	// aqui funcionario efetua o cadastro do cliente sem incluir a senha de
	// acesso
	public String addClienteIn() {
		try {
			boolean all = true;
			if (!validarNomeUK_login()) {
				all = false;
			}
			if (!validarNomeUK_cpf()) {
				all = false;
			}
			if (!all) {
				System.out
				.println("...erro ao cadastrar, este cliente ja esta cadastrado");
				Msg.addMsgError("ESTE EMAIL OU CPF JA ESTA CADASTRADO. FACA UMA CONSULTA PARA VERIFICAR");
				return "/pages/usuario/cadastrarCliente.xhtml";
			} else {
				usuario.setEndereco(endereco);
				usuario.setStatus(true);
				usuario.setAceitaSolicitacao(true);
				usuario.setEndereco(endereco);
				usuario.setSenha("[{(UFRR000DTI-CSI001EDITORA002SISTEMA003)}]");
				usuario.setSenha(TransformaStringMD5.md5(usuario.getSenha()));
				dao.adiciona(usuario);
				init();
				Msg.addMsgInfo("CADASTRO EFETUADO COM SUCESSO");
				System.out.println("...cadastro efetuado com sucesso!");
			}
		} catch (Exception e) {
			init();
			e.printStackTrace();
			System.out
			.println("...Alguma coisa deu errada ao cadastrar cliente");
		}
		return null;
	}

	// auto cadastro do cliente para comprar produto (nao precisara de permissao
	// do administrador)
	public String addCliente() {
		try {
			boolean all = true;
			if (!validarNomeUK_login()) {
				all = false;
			}
			if (!validarNomeUK_cpf()) {
				all = false;
			}
			if (getUsuario().getTelefone1().equalsIgnoreCase(
					this.getUsuario().getTelefone2())) {
				Msg.addMsgError("NUMERO DE TELEFONES NAO PODEM SER IGUAIS, POR FAVOR INFORME OUTRO");
				System.out.println("...erro: numero de telefones iguais");
			}
			if (!all) {
				System.out
				.println("...erro ao cadastrar, este cliente ja foi cadastrado");
				Msg.addMsgError("EMAIL OU CPF JA ESTA CADASTRADO NO SISTEMA, SE PERSISTIR O PROBLEMA ENTRE EM CONTATO COM A EDITORA");
				return "/pages/usuario/cadastrarCliente.xhtml";
			} else {
				if (getUsuario().getSenha().equalsIgnoreCase(
						this.getUsuario().getRepetirSenha())) {
					usuario.setSenha(TransformaStringMD5.md5(usuario.getSenha()));
					usuario.setStatus(true);
					usuario.setAceitaSolicitacao(true);
					usuario.setEndereco(endereco);
					dao.adiciona(usuario);
					init();
					Msg.addMsgInfo("SEU CADASTRO FOI EFETUADO COM SUCESSO");
					this.usuario = new Usuario();
					System.out
					.println("...cadastro de cliente efetuado com sucesso");
					return "index.xhtml";
				} else {
					System.out.println("...Senhas diferentes");
					Msg.addMsgError("SENHAS DIFERENTES, TENTE NOVAMENTE");
				}
			}
		} catch (Exception e) {
			init();
			e.printStackTrace();
			System.out
			.println("...Alguma coisa deu errada ao cadastrar cliente");
		}
		return null;
	}

	// solicitacao de acesso ao sistema(somente para funcionarios)
	public String addPessoa() {
		try {
			boolean all = true;
			if (!validarNomeUK_login()) {
				all = false;
			}
			if (!validarNomeUK_cpf()) {
				all = false;
			}
			if (!all) {
				System.out
				.println("...erro ao cadastrar, este cliente ja foi cadastrado");
				Msg.addMsgError("CPF OU EMAIL JA CONTEM REGISTRO NO SISTEMA, TENTE OUTRO.");
				return "/pages/usuario/cadastrarCliente.xhtml";
			} else {
				if (getUsuario().getSenha().equalsIgnoreCase(
						this.getUsuario().getRepetirSenha())) {
					usuario.setStatus(false);
					usuario.setAceitaSolicitacao(true);
					usuario.setEndereco(endereco);
					usuario.setSenha(TransformaStringMD5.md5(usuario.getSenha()));
					dao.adiciona(usuario);
					init();
					Msg.addMsgInfo("SOLICITACAO DE ACESSO ENVIADA COM SUCESSO. AGUARDE PERMISSAO PARA ACESSAR O SISTEMA"
							+ " CASO SUA SOLICITAÇÃO FOR ACEITA VOCE RECEBERA UM EMAIL DE CONFIRMACAO");
					this.usuario = new Usuario();
					System.out.println("...Solicitacao enviada");
					return "index.xhtml";
				} else {
					System.out.println("...Senhas diferentes");
					Msg.addMsgError("SENHAS DIFERENTES, TENTE NOVAMENTE");
				}
			}
		} catch (Exception e) {
			init();
			e.printStackTrace();
			System.out
			.println("...Alguma coisa deu errada ao cadastrar cliente");
		}
		return null;
	}

	// N�o aceira solicitacao de usuario
	public String naoAceitar() {
		System.out.println(this.getUsuario().getNome());
		if (this.getUsuario().getPerfil().getId() != 5
				&& this.getUsuario().getPerfil().getId() != null) {
			this.getUsuario().setStatus(false);
			this.getUsuario().setAceitaSolicitacao(false);
			Msg.addMsgFatal("USUARIO: " + getUsuario().getNome()
					+ " SOLICITACAO NAO ACEITA");
			dao.atualiza(usuario);
			System.out.println("...Usuario ativado");

			return "/pages/usuario/autorizarAcesso.xhtml";
		}
		return "/pages/usuario/autorizarAcesso.xhtml";

	}

	// Ativar usuario (permitir acesso)
	public String ativarUsuario() {
		System.out.println(this.getUsuario().getNome());			
		try {

			if (this.getUsuario().getPerfil().getId() != 5
					&& this.getUsuario().getPerfil().getId() != null) {
				this.getUsuario().setStatus(true);
				Msg.addMsgInfo("USUARIO: " + getUsuario().getNome()	+ " ATIVADO COM SUCESSO");
				dao.atualiza(usuario);
				System.out.println("...Usuario ativado");
				EmailUtils.confirmaAcesso(usuario);

				this.getEmail().setAutorEnvio(this.login.getUsuario());
				DAO<Usuario> udao = new DAO<Usuario>(Usuario.class);
				Usuario u = udao.buscaPorId(this.login.getUsuario().getId());
				u.getEmails().add(email);

				email.setDestino(usuario);
				email.setMensagem("Acesso permitido com sucesso");
				email.setTitulo("Solicitacao de acesso");
				dao2.adiciona(email);
				this.usuario = new Usuario();
				System.out.println("...confirmacao de acesso enviado por email");
			} else {
				System.out.println("..Nao foi possivel ativar usuario");
				Msg.addMsgError("USUARIO: " + getUsuario().getNome()
						+ " NAO FOI ATIVADO. TENTE NOVAMENTE");
			}	

			return "/pages/usuario/autorizarAcesso.xhtml";
		} catch (EmailException ex) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO! OCORREU UM ERRO AO TENTAR ENVIAR O EMAIL PARA A CONTA DO USUARIO.","Erro"));
			Logger.getLogger(EmailBean.class.getName()).log(Level.ERROR, null, ex);
		}

		return "/pages/usuario/autorizarAcesso.xhtml";

	}

	public Calendar getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Calendar dataAtual) {
		this.dataAtual = dataAtual;
	}

	// Reativar usuario (exceto solicitacao)
	public String reativarUsuario() {
		if (this.getUsuario().getPerfil().getId() != 5
				&& this.getUsuario().getPerfil().getId() != null) {
			this.getUsuario().setStatus(true);
			Msg.addMsgInfo("USUARIO: " + getUsuario().getNome()
					+ " REATIVADO COM SUCESSO");
			dao.atualiza(usuario);
			System.out.println("...Usuario Reativado");
			return "/pages/usuario/reativarAcesso.xhtml";
		} else {
			System.out.println("..Nao foi possivel reativar usuario");
			Msg.addMsgError("USUARIO: " + getUsuario().getNome()
					+ " NAO FOI POSSIVEL REATIVA-LO. TENTE NOVAMENTE");
		}
		return "/pages/usuario/reativarAcesso.xhtml";

	}

	// Desativar usu�rio
	public String desativarUsuario() {
		if (this.getUsuario().getPerfil().getId() != 5
				&& this.getUsuario().getPerfil().getId() != null) {
			this.getUsuario().setStatus(false);
			Msg.addMsgInfo("USUARIO: " + getUsuario().getNome() + " DESATIVADO");		
			dao.atualiza(usuario);
			this.usuario = new Usuario();
			System.out.println("...Usuario desativado");
			return "/pages/usuario/desativarAcesso.xhtml";
		} else {
			System.out.println("..Nao foi possivel desativar usuario");
			Msg.addMsgError("USUARIO: " + getUsuario().getNome()
					+ " NAO FOI DESATIVADO. TENTE NOVAMENTE");
		}
		return "/pages/usuario/desativarAcesso.xhtml";

	}

	// atualiza perfil
	public void updatePerfil() {
		if (this.getUsuario().getPerfil().getId() != 5
				&& this.getUsuario().getPerfil().getId() != null) {
			this.getUsuario().setStatus(true);
			Msg.addMsgInfo("PERFIL DE " + getUsuario().getNome()
					+ " FOI MODIFICADO PARA "
					+ getUsuario().getPerfil().getPerfil());
			dao.atualiza(usuario);
			System.out.println("...Perfil de usuario: "
					+ getUsuario().getNome() + " modificado");
		} else {
			System.out.println("...Nao foi possivel modificar perfil");
			Msg.addMsgError("NAO FOI POSSIVEL EFETUAR OPERACAO. TENTE NOVAMENTE");
		}
	}

	// update Cliente
	public void updateCliente() {
		if (usuario.getTelefone1()
				.equalsIgnoreCase(getUsuario().getTelefone2())) {
			Msg.addMsgError("NUMEROS DE TELEFONE NAO PODEM SER IGUAIS");
		} else {
			if (usuario.getId() != null) {
				Msg.addMsgInfo("CADASTRO DO CLIENTE: " + getUsuario().getNome()
						+ " FOI ALTERADO COM SUCESSO");
				dao.atualiza(usuario);
				System.out.println("...Cadastro do cliente: "
						+ getUsuario().getNome() + " alterado com sucesso.");
				this.usuario = new Usuario();
			} else {
				System.out
				.println("...Nao foi possavel alterar cadastro do cliente");
				Msg.addMsgError("NAO FOI POSSIVEL EFETUAR OPERACAO. TENTE NOVAMENTE");
			}
		}
	}

	// recuperar acesso no sistema
	public void recuperaSenha() {
		try {
			EmailUtils.recuperaSenha(login);
			System.out.println("...Solicitacao esqueci minha senha enviada com sucesso");
		} catch (EmailException ex) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"OCORREU UM ERRO AO TENTAR ENVIAR EMAIL",
							"Erro"));
			System.out.println("...Erro ao tentar enviar o email para solicitacao esqueci minha senha");
			Logger.getLogger(EmailBean.class.getName()).log(Level.ERROR, null, ex);
		}
	}


	/** valida��o UK Login */

	public boolean validarNomeUK_login() {
		return validator.validarNomeUK("login", usuario.getLogin());
	}

	public void checkNomeUK_login(AjaxBehaviorEvent event) {
		if (validarNomeUK_login()) {
			if (usuario.getLogin().isEmpty()) {
				validator.setResultNome("");
			}
		}
	}

	/** valida��o para n�o repetir CPF */

	public boolean validarNomeUK_cpf() {
		return validator.validarNomeUK("cpf", usuario.getCpf());
	}

	public void checkNomeUK_cpf(AjaxBehaviorEvent event) {
		if (validarNomeUK_cpf()) {
			if (usuario.getCpf().isEmpty()) {
				validator.setResultNome("");
			}
		}
	}

	/** ajax */

	public void checkBox4Search(AjaxBehaviorEvent event) {

	}

	public void checkNome(AjaxBehaviorEvent event) {
		validarNome();
		if (usuario.getNome().isEmpty()) {
			validator.setResultNome("");
		}
	}

	public boolean validarNome() {
		return validator.validarNome(usuario.getNome());
	}

	/** Getters and Setters **/

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	public String getSenhaCriptografada() {
		return senhaCriptografada;
	}

	public void setSenhaCriptografada(String senhaCriptografada) {
		this.senhaCriptografada = senhaCriptografada;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void setUsuariosE(List<Usuario> usuariosE) {
		this.usuariosE = usuariosE;
	}

	public DAO<Usuario> getDao() {
		return dao;
	}

	public void setDao(DAO<Usuario> dao) {
		this.dao = dao;
	}

	public Boolean getCadastro() {
		return cadastro;
	}

	public void setCadastro(Boolean cadastro) {
		this.cadastro = cadastro;
	}

	public Perfil getPerfil() {
		return perfil;
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

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public List<Usuario> getUsuariosE() {
		return usuariosE;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Validator<Usuario> getValidator() {
		return validator;
	}

	public void setValidator(Validator<Usuario> validator) {
		this.validator = validator;
	}

	public EnviaEmail getEmail() {
		return email;
	}

	public void setEmail(EnviaEmail email) {
		this.email = email;
	}

	public DAO<EnviaEmail> getDao2() {
		return dao2;
	}

	public void setDao2(DAO<EnviaEmail> dao2) {
		this.dao2 = dao2;
	}

	public List<EnviaEmail> getEmails() {
		return emails;
	}

	public void setEmails(List<EnviaEmail> emails) {
		this.emails = emails;
	}

	public List<Usuario> getUsuariosEmpty() {
		return usuariosEmpty;
	}

	public void setUsuariosEmpty(List<Usuario> usuariosEmpty) {
		this.usuariosEmpty = usuariosEmpty;
	}
}
