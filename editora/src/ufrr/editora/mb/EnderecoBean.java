package ufrr.editora.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ufrr.editora.dao.DAO;
import ufrr.editora.entity.Endereco;

@ManagedBean
@ViewScoped
public class EnderecoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Endereco endereco = new Endereco();
	private List<Endereco> enderecos;
	private DAO<Endereco> dao = new DAO<Endereco>(Endereco.class);

	/** autocomplets **/
	
	// automcomplete name bairro
	public List<String> autocompletebairro(String nome) {
		List<Endereco> array = dao.getAllByName("bairro", nome);
		ArrayList<String> nomes = new ArrayList<String>();
		for (int i = 0; i < array.size(); i++) {
			nomes.add(array.get(i).getBairro());
		}
		return nomes;
	}
	
	// autocomplete logradouro
	public List<String> autocompletelogradouro(String nome) {
		List<Endereco> array = dao.getAllByName("logradouro", nome);
		ArrayList<String> nomes = new ArrayList<String>();
		for (int i = 0; i < array.size(); i++) {
			nomes.add(array.get(i).getLogradouro());
		}
		return nomes;
	}
	
	// autocomplete municipio
	public List<String> autocompletemunicipio(String nome) {
		List<Endereco> array = dao.getAllByName("municipio", nome);
		ArrayList<String> nomes = new ArrayList<String>();
		for (int i = 0; i < array.size(); i++) {
			nomes.add(array.get(i).getMunicipio());
		}
		return nomes;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public DAO<Endereco> getDao() {
		return dao;
	}

	public void setDao(DAO<Endereco> dao) {
		this.dao = dao;
	}
	
	

}
