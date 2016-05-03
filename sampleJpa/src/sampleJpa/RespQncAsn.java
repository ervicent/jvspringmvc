package sampleJpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the TFT039RESP_QNC_ASN database table.
 * 
 */
@Entity
@Table(name="TFT039RESP_QNC_ASN")
@NamedQuery(name="RespQncAsn.findAll", query="SELECT r FROM RespQncAsn r")
public class RespQncAsn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="TFT039_CODRETORNO", length=1)
	@Id
	private String codretorno;

	@Column(name="TFT039_ESTADO", length=1)
	private String estado;

	@Column(name="TFT039_MENSAJEASNEF", length=14)
	private String mensajeasnef;

	@Column(name="TFT039_NUMCLIENTE", length=9)
	private String numcliente;

	@Column(name="TFT039_NUMIDTITULAR", length=10)
	private String numidtitular;

	@Column(name="TFT039_NUMINDIVIDUO", length=9)
	private String numindividuo;

	@Column(name="TFT039_TIPCONSULTA", length=1)
	private String tipconsulta;

	@Column(name="TFT039_TOPASNEF", length=1)
	private String topasnef;

	@Column(name="TFT039_TOPDIRECCION", length=1)
	private String topdireccion;

	@Column(name="TFT039_TOPEXCEPASNEF", length=1)
	private String topexcepasnef;

	public RespQncAsn() {
	}

	public String getCodretorno() {
		return this.codretorno;
	}

	public void setCodretorno(String codretorno) {
		this.codretorno = codretorno;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMensajeasnef() {
		return this.mensajeasnef;
	}

	public void setMensajeasnef(String mensajeasnef) {
		this.mensajeasnef = mensajeasnef;
	}

	public String getNumcliente() {
		return this.numcliente;
	}

	public void setNumcliente(String numcliente) {
		this.numcliente = numcliente;
	}

	public String getNumidtitular() {
		return this.numidtitular;
	}

	public void setNumidtitular(String numidtitular) {
		this.numidtitular = numidtitular;
	}

	public String getNumindividuo() {
		return this.numindividuo;
	}

	public void setNumindividuo(String numindividuo) {
		this.numindividuo = numindividuo;
	}

	public String getTipconsulta() {
		return this.tipconsulta;
	}

	public void setTipconsulta(String tipconsulta) {
		this.tipconsulta = tipconsulta;
	}

	public String getTopasnef() {
		return this.topasnef;
	}

	public void setTopasnef(String topasnef) {
		this.topasnef = topasnef;
	}

	public String getTopdireccion() {
		return this.topdireccion;
	}

	public void setTopdireccion(String topdireccion) {
		this.topdireccion = topdireccion;
	}

	public String getTopexcepasnef() {
		return this.topexcepasnef;
	}

	public void setTopexcepasnef(String topexcepasnef) {
		this.topexcepasnef = topexcepasnef;
	}

}