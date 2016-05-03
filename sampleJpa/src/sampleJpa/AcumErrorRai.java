package sampleJpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the TFT040ACUM_ERROR_RAI database table.
 * 
 */
@Entity
@Table(name="TFT040TOTAL_ERROR_RAI")
@NamedQuery(name="AcumErrorRai.findAll", query="SELECT t FROM AcumErrorRai t")
public class AcumErrorRai implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TFT040_CLAVEACT", nullable=false, length=1)
	private String claveact;

	@Column(name="TFT040_CODENTINFO", nullable=false, length=4)
	private String codentinfo;

	@Column(name="TFT040_CODREFER", nullable=false, length=15)
	private String codrefer;

	@Column(name="TFT040_ERRORES", length=17)
	private String errores;

	@Column(name="TFT040_SECUENCIA", nullable=false, length=9)
	private String secuencia;

	@Column(name="TFT040_TIPFICHERO", nullable=false, length=3)
	private String tipfichero;

	public AcumErrorRai() {
	}

	public String getClaveact() {
		return this.claveact;
	}

	public void setClaveact(String claveact) {
		this.claveact = claveact;
	}

	public String getCodentinfo() {
		return this.codentinfo;
	}

	public void setCodentinfo(String codentinfo) {
		this.codentinfo = codentinfo;
	}

	public String getCodrefer() {
		return this.codrefer;
	}

	public void setCodrefer(String codrefer) {
		this.codrefer = codrefer;
	}

	public String getErrores() {
		return this.errores;
	}

	public void setErrores(String errores) {
		this.errores = errores;
	}

	public String getSecuencia() {
		return this.secuencia;
	}

	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	public String getTipfichero() {
		return this.tipfichero;
	}

	public void setTipfichero(String tipfichero) {
		this.tipfichero = tipfichero;
	}

}