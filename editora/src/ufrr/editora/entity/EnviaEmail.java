package ufrr.editora.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_email")
public class EnviaEmail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="cliente_destino_id")
	private Usuario destino;
	
	private String titulo;
	
	private String mensagem;
	
	@ManyToOne
	@JoinColumn(name="usuario_autor_id")
	private Usuario autorEnvio;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataEnvio = Calendar.getInstance();

	/** Get and Set **/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getDestino() {
		return destino;
	}

	public void setDestino(Usuario destino) {
		this.destino = destino;
	}

	public Calendar getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Calendar dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Usuario getAutorEnvio() {
		return autorEnvio;
	}

	public void setAutorEnvio(Usuario autorEnvio) {
		this.autorEnvio = autorEnvio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((autorEnvio == null) ? 0 : autorEnvio.hashCode());
		result = prime * result
				+ ((dataEnvio == null) ? 0 : dataEnvio.hashCode());
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((mensagem == null) ? 0 : mensagem.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		EnviaEmail other = (EnviaEmail) obj;
		if (autorEnvio == null) {
			if (other.autorEnvio != null)
				return false;
		} else if (!autorEnvio.equals(other.autorEnvio))
			return false;
		if (dataEnvio == null) {
			if (other.dataEnvio != null)
				return false;
		} else if (!dataEnvio.equals(other.dataEnvio))
			return false;
		if (destino == null) {
			if (other.destino != null)
				return false;
		} else if (!destino.equals(other.destino))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mensagem == null) {
			if (other.mensagem != null)
				return false;
		} else if (!mensagem.equals(other.mensagem))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	

}
