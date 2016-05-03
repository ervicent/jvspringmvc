package sampleJpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the TFT031ERRORES_RPP database table.
 *
 */
@Entity
@Table(name="TFT031ERRORES_RPP")
@NamedQuery(name="ErroresRpp.findAll", query="SELECT e FROM ErroresRpp e")
public class ErroresRpp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TFT031_CODENTIDAD", nullable=false, length=5)
	private String codentidad;

	@Column(name="TFT031_CODERROR1", length=3)
	private String coderror1;

	@Column(name="TFT031_CODERROR2", length=3)
	private String coderror2;

	@Column(name="TFT031_CODERROR3", length=3)
	private String coderror3;

	@Column(name="TFT031_ESTADO", length=1)
	private String estado;

	@Column(name="TFT031_FECGENERA", length=8)
	private String fecgenera;

	@Column(name="TFT031_FECPROCESO", length=8)
	private String fecproceso;

	@Column(name="TFT031_NUMCUENTA", length=29)
	private String numcuenta;

	@Column(name="TFT031_NUMDOCUMENTO", length=25)
	private String numdocumento;

	@Column(name="TFT031_NUMREGERROR", length=9)
	private String numregerror;

	@Column(name="TFT031_NUMREGTOTAL", length=9)
	private String numregtotal;

	@Column(name="TFT031_PAIS", length=3)
	private String pais;

	@Column(name="TFT031_TIPDOCUMENTO", length=2)
	private String tipdocumento;

	@Column(name="TFT031_TIPERROR1", length=1)
	private String tiperror1;

	@Column(name="TFT031_TIPERROR2", length=1)
	private String tiperror2;

	@Column(name="TFT031_TIPERROR3", length=1)
	private String tiperror3;

	@Column(name="TFT031_TIPREGISTRO", nullable=false, length=6)
	private String tipregistro;

	public ErroresRpp() {
	}

	public String getCodentidad() {
		return this.codentidad;
	}

	public void setCodentidad(String codentidad) {
		this.codentidad = codentidad;
	}

	public String getCoderror1() {
		return this.coderror1;
	}

	public void setCoderror1(String coderror1) {
		this.coderror1 = coderror1;
	}

	public String getCoderror2() {
		return this.coderror2;
	}

	public void setCoderror2(String coderror2) {
		this.coderror2 = coderror2;
	}

	public String getCoderror3() {
		return this.coderror3;
	}

	public void setCoderror3(String coderror3) {
		this.coderror3 = coderror3;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFecgenera() {
		return this.fecgenera;
	}

	public void setFecgenera(String fecgenera) {
		this.fecgenera = fecgenera;
	}

	public String getFecproceso() {
		return this.fecproceso;
	}

	public void setFecproceso(String fecproceso) {
		this.fecproceso = fecproceso;
	}

	public String getNumcuenta() {
		return this.numcuenta;
	}

	public void setNumcuenta(String numcuenta) {
		this.numcuenta = numcuenta;
	}

	public String getNumdocumento() {
		return this.numdocumento;
	}

	public void setNumdocumento(String numdocumento) {
		this.numdocumento = numdocumento;
	}

	public String getNumregerror() {
		return this.numregerror;
	}

	public void setNumregerror(String numregerror) {
		this.numregerror = numregerror;
	}

	public String getNumregtotal() {
		return this.numregtotal;
	}

	public void setNumregtotal(String numregtotal) {
		this.numregtotal = numregtotal;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getTipdocumento() {
		return this.tipdocumento;
	}

	public void setTipdocumento(String tipdocumento) {
		this.tipdocumento = tipdocumento;
	}

	public String getTiperror1() {
		return this.tiperror1;
	}

	public void setTiperror1(String tiperror1) {
		this.tiperror1 = tiperror1;
	}

	public String getTiperror2() {
		return this.tiperror2;
	}

	public void setTiperror2(String tiperror2) {
		this.tiperror2 = tiperror2;
	}

	public String getTiperror3() {
		return this.tiperror3;
	}

	public void setTiperror3(String tiperror3) {
		this.tiperror3 = tiperror3;
	}

	public String getTipregistro() {
		return this.tipregistro;
	}

	public void setTipregistro(String tipregistro) {
		this.tipregistro = tipregistro;
	}

}