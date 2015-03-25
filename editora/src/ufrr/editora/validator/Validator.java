package ufrr.editora.validator;

import java.util.Date;

import javax.persistence.Query;

import ufrr.editora.dao.GenericDAOImp;

public class Validator<T> {

	/** START VARIABLES */

	private String resultNome, resultStatus, resultTpun, resultCodigoRegistro,
			resultCnpj, resultCpf, resultProcedimentoPrincipal,
			resultProcedimentoSecundario1, resultProcedimentoSecundario2,
			resultLaboratorio, resultUnidadeMedicamento, resultPrincipioAtivo,
			resultQuantidadeCritica, resultObservacao, resultTplo,
			resultLogradouro, resultBairro, resultCep, resultUnif,
			resultCidade, resultDddResidencial, resultTelefoneResidencial,
			resultDddCelular, resultTelefoneCelular, resultDddRecado,
			resultTelefoneRecado, resultNomeRecado, resultDtNascimento,
			resultCor, resultSexo, resultNomeMae, resultProfissao,
			resultUnifNaturalidade, resultUnifProcedencia, resultRg,
			resultComplemento, resultNumero, resultProtocoloCecor, resultDtInicio, resultDtTermino,
			resultHCM, resultConvenioOrg, resultConvenioTrat, resultUnidadeTrat, resultStatuss, resultProcedimento, resultExistenciaTratamento;
	
	
	private GenericDAOImp<T, Integer> dao;
	private Class<T> persClass;
	
	/** END VARIABLES */

	/** START CONSTRUCTOR */

	public Validator(Class<T> classe) {
		this.persClass = classe;
	}

	/** END CONSTRUCTOR */

	/** START VALIDATIONS */
	
	public boolean validarIntegerUK(String string, Long long1) {
			dao = new GenericDAOImp<T, Integer>(persClass);
			Query q = dao.query("SELECT u FROM " + persClass.getSimpleName()
					+ " u WHERE " + string + " = ?");
			q.setParameter(1, long1);
			if (!q.getResultList().isEmpty()) {
				resultNome = "Nome já registrado";
				return false;
			} else {
				resultNome = "";
				return true;
			}
		}

	public boolean validarNomeUK(String field, String nome) {
		if (nome.isEmpty()) {
			resultNome = "Informe o nome";
			return false;
		} else {
			dao = new GenericDAOImp<T, Integer>(persClass);
			Query q = dao.query("SELECT u FROM " + persClass.getSimpleName()
					+ " u WHERE " + field + " = ?");
			q.setParameter(1, nome);
			if (!q.getResultList().isEmpty()) {
				resultNome = "Nome já registrado";
				return false;
			} else {
				resultNome = "";
				return true;
			}
		}
	}

	public boolean validarNome(String nome) {
		if (nome.isEmpty()) {
			resultNome = "Informe o nome";
			return false;
		} else {
			if (nome.contains("'") || nome.contains("*")) {
				resultNome = "Contem caractere(s) invalido(s)";
				return false;
			} else {
				resultNome = "";
				return true;
			}
		}
	}

	public boolean validarObservacao(String obs) {
		if (obs.isEmpty()) {
			resultObservacao = "Informe a observaÃ§Ã£o";
			return false;
		} else {
			if (obs.contains("'") || obs.contains("*")) {
				resultObservacao = "Contï¿½m caractï¿½re(s) invï¿½lido(s)";
				return false;
			} else {
				resultObservacao = "";
				return true;
			}
		}
	}

	public boolean validarStatus(Object stat) {
		if (stat == null) {
			resultStatus = "Selecione um status";
			return false;
		} else {
			resultStatus = "";
			return true;
		}
	}

	public boolean validarTpun(Object tpun) {
		if (tpun == null) {
			resultTpun = "Selecione um tipo de unidade";
			return false;
		} else {
			resultTpun = "";
			return true;
		}
	}

	public boolean validarCodigoRegistro(String field, Integer codigo) {
		if (codigo == null || codigo == 0) {
			resultCodigoRegistro = "O registro nï¿½o pode ser nulo";
			codigo = null;
			return false;
		} else {
			dao = new GenericDAOImp<T, Integer>(persClass);
			Query q = dao.query("SELECT u FROM " + persClass.getSimpleName()
					+ " u WHERE " + field + " = ?");
			q.setParameter(1, codigo);
			if (!q.getResultList().isEmpty()) {
				resultCodigoRegistro = "Cï¿½digo jï¿½ registrado";
				return false;
			} else {
				resultCodigoRegistro = "";
				return true;
			}
		}
	}

	public boolean validarCnpj(String field, String cnpj) {
		if (cnpj.contains(".") || cnpj.contains("/") || cnpj.contains("-")) {
			resultCnpj = "Informe somente nï¿½meros";
			return false;
		} else {
			if (cnpj.length() < 14) {
				resultCnpj = "CNPJ incompleto";
				return false;
			} else {
				if (!CpfCnpjValidator.isValid(cnpj)) {
					resultCnpj = "CNPJ invï¿½lido";
					return false;
				} else {
					dao = new GenericDAOImp<T, Integer>(persClass);
					Query q = dao.query("SELECT u FROM "
							+ persClass.getSimpleName() + " u WHERE " + field
							+ " = ?");
					q.setParameter(1, cnpj);
					if (!q.getResultList().isEmpty()) {
						resultCnpj = "CNPJ jï¿½ registrado";
						return false;
					} else {
						resultCnpj = "";
						return true;
					}
				}
			}
		}
	}

	public boolean validarCpf(String field, String cpf) {
		if (cpf.contains(".") || cpf.contains("/") || cpf.contains("-")) {
			resultCpf = "Informe somente nï¿½meros";
			return false;
		} else {
			if (cpf.length() < 11) {
				resultCpf = "CPF incompleto";
				return false;
			} else {
				if (!CpfCnpjValidator.isValid(cpf)) {
					resultCpf = "CPF invï¿½lido";
					return false;
				} else {
					dao = new GenericDAOImp<T, Integer>(persClass);
					Query q = dao.query("SELECT u FROM "
							+ persClass.getSimpleName() + " u WHERE " + field
							+ " = ?");
					q.setParameter(1, cpf);
					if (!q.getResultList().isEmpty()) {
						resultCpf = "CPF jï¿½ registrado";
						return false;
					} else {
						resultCpf = "";
						return true;
					}
				}
			}
		}
	}

	public boolean validarEndCidade(String end) {
		if (end.isEmpty()) {
			resultCidade = "Informe o Cidade ";
			return false;
		} else {
			resultCidade = "";
			return true;
		}
	}

	public boolean validarEndTplo(Object obj) {
		if (obj == null) {
			resultTplo = "Selecione um Tipo/End";
			return false;
		} else {
			resultTplo = "";
			return true;
		}
	}

	public boolean validarEndLougradouro(String end) {
		if (end.isEmpty()) {
			resultLogradouro = "Informe o Endereco";
			return false;
		} else {
			resultLogradouro = "";
			return true;
		}
	}

	public boolean validarEndBairro(String end) {
		if (end.isEmpty()) {
			resultBairro = "Informe o Bairro";
			return false;
		} else {
			resultBairro = "";
			return true;
		}
	}

	public boolean validarEndCep(String end) {
		if (end.isEmpty()) {
			resultCep = "Informe o CEP";
			return false;
		} else {
			resultCep = "";
			return true;
		}
	}

	public boolean validarEndUf(Object end) {
		if (end == null) {
			resultUnif = "Selecione uma UF";
			return false;
		} else {
			resultUnif = "";
			return true;
		}
	}

	public boolean validarLaboratorio(Object obj) {
		if (obj == null) {
			resultLaboratorio = "Selecione um laboratï¿½rio";
			return false;
		} else {
			resultLaboratorio = "";
			return true;
		}
	}

	public boolean validarUnidadeMedicamento(Object obj) {
		if (obj == null) {
			resultUnidadeMedicamento = "Selecione uma unidade";
			return false;
		} else {
			resultUnidadeMedicamento = "";
			return true;
		}
	}

	public boolean validarPrincipioAtivo(Object obj) {
		if (obj == null) {
			resultPrincipioAtivo = "Informe o principio ativo";
			return false;
		} else {
			resultPrincipioAtivo = "";
			return true;
		}
	}

	public boolean validarQuantidadeCritica(Integer qtd) {
		try {
			if (qtd == 0 || qtd == null) {
				resultQuantidadeCritica = "Informe a quantidade crï¿½tica";
				return false;
			} else if (qtd < 0) {
				resultQuantidadeCritica = "A quantidade crï¿½tica deve ser um nï¿½mero maior que zero";
				return false;
			} else {
				resultQuantidadeCritica = "";
				return true;
			}
		} catch (NumberFormatException e) {
			resultQuantidadeCritica = "Informe somente nï¿½meros";
			return false;
		}
	}

	public boolean validarCor(Object o) {
		if (o == null) {
			resultCor = "Informe a Cor";
			return false;
		} else {
			resultCor = "";
			return true;
		}
	}

	public boolean validarDtNascimento(Date d) {
		if (d == null) {
			resultDtNascimento = "Informe a data de nascimento";
			return false;
		} else {
			resultDtNascimento = "";
			return true;
		}
	}

	public boolean validarSexo(Object o) {
		if (o == null) {
			resultSexo = "Informe o sexo";
			return false;
		} else {
			resultSexo = "";
			return true;
		}
	}

	public boolean validarNomeMae(String nm) {
		if (nm.isEmpty()) {
			resultNomeMae = "Informe o nome da mï¿½e";
			return false;
		} else if (nm.contains("'")) {
			resultNomeMae = "Caractï¿½re invï¿½lido";
			return false;
		} else {
			resultNomeMae = "";
			return true;
		}
	}

	public boolean validarProfissao(String p) {
		if (p.isEmpty()) {
			resultProfissao = "Informe a profissï¿½o, mesmo se aposentado";
			return false;
		} else if (!p.contains("'")) {
			resultProfissao = "";
			return true;
		} else {
			resultProfissao = "Caractï¿½re invï¿½lido";
			return false;
		}
	}

	public boolean validarUnifNaturalidade(Object o) {
		if (o == null) {
			resultUnifNaturalidade = "Informe a naturalidade";
			return false;
		} else {
			resultUnifNaturalidade = "";
			return true;
		}
	}

	public boolean validarUnifProcedencia(Object o) {
		if (o == null) {
			resultUnifProcedencia = "Informe a procedencia";
			return false;
		} else {
			resultUnifProcedencia = "";
			return true;
		}
	}

	public boolean validarRg(String rg) {
		if (rg.isEmpty()) {
			resultRg = "Informe o RG";
			return false;
		} else if (rg.contains("'")) {
			resultRg = "Caractï¿½re invï¿½lido";
			return false;
		} else {
			resultRg = "";
			return true;
		}
	}

	/* START endereco validations */

	public boolean validarTipoLogradouro(Object o) {
		if (o == null) {
			resultTplo = "Informe o tipo de logradouro";
			return false;
		} else {
			resultTplo = "";
			return true;
		}
	}

	public boolean validarLogradouro(String l) {
		if (l.isEmpty()) {
			resultLogradouro = "Informe o logradouro";
			return false;
		} else if (l.contains("'")) {
			resultLogradouro = "Caractï¿½re invï¿½lido";
			return false;
		} else {
			resultLogradouro = "";
			return true;
		}
	}

	public boolean validarNumero(Integer o) {
		try {
			if (o == 0 || o == null) {
				resultNumero = "Informe o nï¿½mero";
				return false;
			} else if (o.toString().contains("'")) {
				resultNumero = "Caractï¿½re invï¿½lido";
				return false;
			} else {
				resultNumero = "";
				return true;
			}
		} catch (NumberFormatException e) {
			resultNumero = "Informe somente nï¿½meros";
			return false;
		}
	}

	public boolean validarBairro(String o) {
		if (o.isEmpty()) {
			resultBairro = "Informe o bairro";
			return false;
		} else if (o.contains("'")) {
			resultBairro = "Caractï¿½re invï¿½lido";
			return false;
		} else {
			resultBairro = "";
			return true;
		}
	}

	public boolean validarCep(String o) {
		if (o.isEmpty()) {
			resultCep = "Informe o CEP";
			return false;
		} else if (o.contains("'") || o.contains("-")) {
			resultCep = "Caractï¿½re invï¿½lido";
			return false;
		} else {
			resultCep = "";
			return true;
		}
	}

	public boolean validarComplemento(String o) {
		if (o.contains("'")) {
			resultComplemento = "Caractï¿½re invï¿½lido";
			return false;
		} else {
			resultComplemento = "";
			return true;
		}
	}

	public boolean validarUnif(Object o) {
		if (o == null) {
			resultUnif = "Informe a UF";
			return false;
		} else {
			resultUnif = "";
			return true;
		}
	}

	public boolean validarCidade(String o) {
		if (o.isEmpty()) {
			resultCidade = "Informe a cidade";
			return false;
		} else if (o.contains("'")) {
			resultCidade = "Caractï¿½re invï¿½lido";
			return false;
		} else {
			resultCidade = "";
			return true;
		}
	}

	/* END endereco validations */

	public boolean validarDddResidencial(Integer i) {
		try {
			if (i == null || i == 0) {
				resultTelefoneResidencial = "Informe o telefone completo";
				return false;
			} else if(i < 10 || i > 99) {
				resultTelefoneResidencial = "DDD invï¿½lido";
				return false;
			} else {
				return true;
			}
		} catch (NumberFormatException e) {
			resultTelefoneResidencial = "Informe somente nï¿½meros";
			return false;
		}

	}

	public boolean validarDddCelular(Integer i) {
		try {
			if (i == null || i == 0) {
				resultTelefoneCelular = "Informe o celular completo";
				return false;
			} else if(i < 10 || i > 99) {
				resultTelefoneCelular = "DDD invï¿½lido";
				return false;
			} else {
				return true;
			}
		} catch (NumberFormatException e) {
			resultDddCelular = "Informe somente nï¿½meros";
			return false;
		}
	}

	public boolean validarDddRecado(Integer i) {
		try {
			if (i == null || i == 0) {
				resultTelefoneRecado = "Informe o tel. para recado completo";
				return false;
			} else if(i < 10 || i > 99) {
				resultTelefoneRecado = "DDD invï¿½lido";
				return false;
			} else {
				return true;
			}
		} catch (NumberFormatException e) {
			resultDddRecado = "Informe somente nï¿½meros";
			return false;
		}
	}

	public boolean validarTelefoneResidencial(String telefone) {
		if (telefone.isEmpty() || telefone.length() < 8) {
			resultTelefoneResidencial = "Informe o telefone completo";
			return false;
		} else if (telefone.contains("'") || telefone.contains("-")
				|| telefone.contains(".")) {
			resultTelefoneResidencial = "Informe somente nï¿½meros";
			return false;
		} else {
			resultTelefoneResidencial = "";
			return true;
		}

	}

	public boolean validarTelefoneCelular(String o) {
		if (o.isEmpty() || o.length() < 8) {
			resultTelefoneCelular = "Informe o celular completo";
			return false;
		} else if (o.contains("'") || o.contains("-") || o.contains(".")) {
			resultTelefoneCelular = "Informe somente nï¿½meros";
			return false;
		} else {
			resultTelefoneCelular = "";
			return true;
		}
	}

	public boolean validarTelefoneRecado(String o) {
		if (o.isEmpty() || o.length() < 8) {
			resultTelefoneRecado = "Informe o tel. para recado completo";
			return false;
		} else if (o.contains("'") || o.contains("-") || o.contains(".")) {
			resultTelefoneRecado = "Informe somente nï¿½meros";
			return false;
		} else {
			resultTelefoneRecado = "";
			return true;
		}
	}

	public boolean validarNomeRecado(String p) {
		if (p.isEmpty()) {
			resultNomeRecado = "Informe o nome da pessoa para recado";
			return false;
		} else if (p.contains("'")) {
			resultNomeRecado = "Caractï¿½re invï¿½lido";
			return false;
		} else {
			resultNomeRecado = "";
			return true;
		}
	}

	public boolean validarProtocoloCecor(Integer prot) {
		if (prot == null || prot == 0) {
			resultProtocoloCecor = "Informe o nï¿½mero de protocolo";
			return false;
		} else {
			dao = new GenericDAOImp<T, Integer>(persClass);
			Query q = dao
					.query("select u from Paciente u where u.protocoloCecor = ?");
			q.setParameter(1, prot);
			if (!q.getResultList().isEmpty()) {
				resultProtocoloCecor = "Protocolo jï¿½ registrado";
				return false;
			} else {
				resultProtocoloCecor = "";
				return true;
			}
		}
	}
	
	/* Tratamento */
	
	public boolean validarDtInicio(Date d) {
		if (d == null) {
			resultDtInicio = "Informe a data de inicio.";
			return false;
		} else {
			resultDtInicio = "";
			return true;
		}
	}
	public boolean validarDtFim(Date inicio, Date fim) {
		if (fim == null) {
			resultDtTermino = "Informe a data do tï¿½rmino";
			return false;
		} else if(inicio == null) {
			resultDtTermino = "Informe tambï¿½m a data de inicio";
			return false;
		} else if(fim.before(inicio)) {
			resultDtTermino = "A data de tï¿½rmino deve ser posterior ï¿½ de inicio";
			return false;
		} else {
			resultDtTermino = "";
			return true;
		}
	}
	
	public boolean validarHCM(Integer i){
		if (i == null || i == 0){
			resultHCM = "Informe o HCM.";
			return false;
		}else {
			resultHCM = "";
			return true;
		}
	}
	public boolean validarConvenioOrg(Object o){
		if(o == null){
			resultConvenioOrg = "Informe o Convenio";
			return false;
		}else {
			resultConvenioOrg = "";
			return true;
		}
	}
	
	public boolean validarConvenioTrat(Object o){
		if(o == null){
			resultConvenioTrat = "Informe o Convenio.";
			return false;
		}else {
			resultConvenioTrat = "";
			return true;
		}
	}

	public boolean validarUnidade(Object o){
		if(o == null){
			resultUnidadeTrat = "Informe a Unidade.";
			return  false;
		}else {
			resultUnidadeTrat = "";
			return true;
		}
		
	}
	public boolean validarProcedimento (Object o){
		if(o == null){
			resultProcedimentoPrincipal = "Informe o Procedimento Principal.";
			return false;
		}else {
			resultProcedimentoPrincipal = "";
			return true;
		}
	}
	
	public boolean validarExistenciaTratamento(Integer hcm, Integer cecor) {
		try {
			dao = new GenericDAOImp<T, Integer>(persClass);
			Query q = dao.query("select e from PacienteProcedimento e where e.protocoloHcm = ? and e.paciente.protocoloCecor = ?");
			q.setParameter(1, hcm);
			q.setParameter(2, cecor);
			
			if(q.getResultList().isEmpty()) {
				resultExistenciaTratamento = "";
				return true;
			} else {
				resultExistenciaTratamento = "HCM jï¿½ existente para o protocolo do paciente";
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/** END VALIDATIONS */

	/** START GETTERS AND SETTERS */

	public String getResultNome() {
		return resultNome;
	}

	public void setResultNome(String resultNome) {
		this.resultNome = resultNome;
	}

	public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getResultTpun() {
		return resultTpun;
	}

	public void setResultTpun(String resultTpun) {
		this.resultTpun = resultTpun;
	}

	public String getResultCodigoRegistro() {
		return resultCodigoRegistro;
	}

	public void setResultCodigoRegistro(String resultCodigoRegistro) {
		this.resultCodigoRegistro = resultCodigoRegistro;
	}

	public String getResultCnpj() {
		return resultCnpj;
	}

	public void setResultCnpj(String resultCnpj) {
		this.resultCnpj = resultCnpj;
	}

	public String getResultCpf() {
		return resultCpf;
	}

	public void setResultCpf(String resultCpf) {
		this.resultCpf = resultCpf;
	}

	public String getResultProcedimentoPrincipal() {
		return resultProcedimentoPrincipal;
	}

	public void setResultProcedimentoPrincipal(
			String resultProcedimentoPrincipal) {
		this.resultProcedimentoPrincipal = resultProcedimentoPrincipal;
	}

	public String getResultProcedimentoSecundario1() {
		return resultProcedimentoSecundario1;
	}

	public void setResultProcedimentoSecundario1(
			String resultProcedimentoSecundario1) {
		this.resultProcedimentoSecundario1 = resultProcedimentoSecundario1;
	}

	public String getResultProcedimentoSecundario2() {
		return resultProcedimentoSecundario2;
	}

	public void setResultProcedimentoSecundario2(
			String resultProcedimentoSecundario2) {
		this.resultProcedimentoSecundario2 = resultProcedimentoSecundario2;
	}

	public String getResultLaboratorio() {
		return resultLaboratorio;
	}

	public void setResultLaboratorio(String resultLaboratorio) {
		this.resultLaboratorio = resultLaboratorio;
	}

	public String getResultUnidadeMedicamento() {
		return resultUnidadeMedicamento;
	}

	public void setResultUnidadeMedicamento(String resultUnidadeMedicamento) {
		this.resultUnidadeMedicamento = resultUnidadeMedicamento;
	}

	public String getResultPrincipioAtivo() {
		return resultPrincipioAtivo;
	}

	public void setResultPrincipioAtivo(String resultPrincipioAtivo) {
		this.resultPrincipioAtivo = resultPrincipioAtivo;
	}

	public String getResultQuantidadeCritica() {
		return resultQuantidadeCritica;
	}

	public void setResultQuantidadeCritica(String resultQuantidadeCritica) {
		this.resultQuantidadeCritica = resultQuantidadeCritica;
	}

	public String getResultObservacao() {
		return resultObservacao;
	}

	public void setResultObservacao(String resultObservacao) {
		this.resultObservacao = resultObservacao;
	}

	public String getResultTplo() {
		return resultTplo;
	}

	public void setResultTplo(String resultTplo) {
		this.resultTplo = resultTplo;
	}

	public String getResultLogradouro() {
		return resultLogradouro;
	}

	public void setResultLogradouro(String resultLogradouro) {
		this.resultLogradouro = resultLogradouro;
	}

	public String getResultBairro() {
		return resultBairro;
	}

	public void setResultBairro(String resultBairro) {
		this.resultBairro = resultBairro;
	}

	public String getResultCep() {
		return resultCep;
	}

	public void setResultCep(String resultCep) {
		this.resultCep = resultCep;
	}

	public String getResultUnif() {
		return resultUnif;
	}

	public void setResultUnif(String resultUnif) {
		this.resultUnif = resultUnif;
	}

	public String getResultCidade() {
		return resultCidade;
	}

	public void setResultCidade(String resultCidade) {
		this.resultCidade = resultCidade;
	}

	public String getResultDddResidencial() {
		return resultDddResidencial;
	}

	public void setResultDddResidencial(String resultDddResidencial) {
		this.resultDddResidencial = resultDddResidencial;
	}

	public String getResultTelefoneResidencial() {
		return resultTelefoneResidencial;
	}

	public void setResultTelefoneResidencial(String resultTelefoneResidencial) {
		this.resultTelefoneResidencial = resultTelefoneResidencial;
	}

	public String getResultDddCelular() {
		return resultDddCelular;
	}

	public void setResultDddCelular(String resultDddCelular) {
		this.resultDddCelular = resultDddCelular;
	}

	public String getResultTelefoneCelular() {
		return resultTelefoneCelular;
	}

	public void setResultTelefoneCelular(String resultTelefoneCelular) {
		this.resultTelefoneCelular = resultTelefoneCelular;
	}

	public String getResultDddRecado() {
		return resultDddRecado;
	}

	public void setResultDddRecado(String resultDddRecado) {
		this.resultDddRecado = resultDddRecado;
	}

	public String getResultTelefoneRecado() {
		return resultTelefoneRecado;
	}

	public void setResultTelefoneRecado(String resultTelefoneRecado) {
		this.resultTelefoneRecado = resultTelefoneRecado;
	}

	public String getResultNomeRecado() {
		return resultNomeRecado;
	}

	public void setResultNomeRecado(String resultNomeRecado) {
		this.resultNomeRecado = resultNomeRecado;
	}

	public String getResultDtNascimento() {
		return resultDtNascimento;
	}

	public void setResultDtNascimento(String resultDtNascimento) {
		this.resultDtNascimento = resultDtNascimento;
	}

	public String getResultCor() {
		return resultCor;
	}

	public void setResultCor(String resultCor) {
		this.resultCor = resultCor;
	}

	public String getResultSexo() {
		return resultSexo;
	}

	public void setResultSexo(String resultSexo) {
		this.resultSexo = resultSexo;
	}

	public String getResultNomeMae() {
		return resultNomeMae;
	}

	public void setResultNomeMae(String resultNomeMae) {
		this.resultNomeMae = resultNomeMae;
	}

	public String getResultProfissao() {
		return resultProfissao;
	}

	public void setResultProfissao(String resultProfissao) {
		this.resultProfissao = resultProfissao;
	}

	public String getResultUnifNaturalidade() {
		return resultUnifNaturalidade;
	}

	public void setResultUnifNaturalidade(String resultUnifNaturalidade) {
		this.resultUnifNaturalidade = resultUnifNaturalidade;
	}

	public String getResultUnifProcedencia() {
		return resultUnifProcedencia;
	}

	public void setResultUnifProcedencia(String resultUnifProcedencia) {
		this.resultUnifProcedencia = resultUnifProcedencia;
	}

	public String getResultRg() {
		return resultRg;
	}

	public void setResultRg(String resultRg) {
		this.resultRg = resultRg;
	}

	public String getResultComplemento() {
		return resultComplemento;
	}

	public void setResultComplemento(String resultComplemento) {
		this.resultComplemento = resultComplemento;
	}

	public String getResultNumero() {
		return resultNumero;
	}

	public void setResultNumero(String resultNumero) {
		this.resultNumero = resultNumero;
	}

	public String getResultProtocoloCecor() {
		return resultProtocoloCecor;
	}

	public void setResultProtocoloCecor(String resultProtocoloCecor) {
		this.resultProtocoloCecor = resultProtocoloCecor;
	}

	public String getResultDtInicio() {
		return resultDtInicio;
	}

	public void setResultDtInicio(String resultDtInicio) {
		this.resultDtInicio = resultDtInicio;
	}

	public String getResultHCM() {
		return resultHCM;
	}

	public void setResultHCM(String resultHCM) {
		this.resultHCM = resultHCM;
	}

	public String getResultConvenioOrg() {
		return resultConvenioOrg;
	}

	public void setResultConvenioOrg(String resultConvenioOrg) {
		this.resultConvenioOrg = resultConvenioOrg;
	}

	public String getResultConvenioTrat() {
		return resultConvenioTrat;
	}

	public void setResultConvenioTrat(String resultConvenioTrat) {
		this.resultConvenioTrat = resultConvenioTrat;
	}

	public String getResultUnidadeTrat() {
		return resultUnidadeTrat;
	}

	public void setResultUnidadeTrat(String resultUnidadeTrat) {
		this.resultUnidadeTrat = resultUnidadeTrat;
	}

	public String getResultStatuss() {
		return resultStatuss;
	}

	public void setResultStatuss(String resultStatuss) {
		this.resultStatuss = resultStatuss;
	}

	public String getResultProcedimento() {
		return resultProcedimento;
	}

	public void setResultProcedimento(String resultProcedimento) {
		this.resultProcedimento = resultProcedimento;
	}

	public String getResultExistenciaTratamento() {
		return resultExistenciaTratamento;
	}

	public void setResultExistenciaTratamento(String resultExistenciaTratamento) {
		this.resultExistenciaTratamento = resultExistenciaTratamento;
	}

	public String getResultDtTermino() {
		return resultDtTermino;
	}

	public void setResultDtTermino(String resultDtTermino) {
		this.resultDtTermino = resultDtTermino;
	}

}
