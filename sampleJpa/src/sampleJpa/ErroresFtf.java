package sampleJpa;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the TFT010ERRORES_FTF database table.
 * 
 */
@Entity
@Table(name="TFT010ERRORES_FTF")
@NamedQuery(name="ErroresFtf.findAll", query="SELECT t FROM ErroresFtf t")
public class ErroresFtf implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TFT010_CODERRORES", length=70)
	private String coderrores;

	@Column(name="TFT010_IDFICHERO", length=30)
	private String idfichero;

	@Column(name="TFT010_LINEAERROR", precision=10)
	private BigDecimal lineaerror;

	@Column(name="TFT010_LINFINBLOQ", precision=10)
	private BigDecimal linfinbloq;

	@Column(name="TFT010_LININIBLOQ", precision=10)
	private BigDecimal lininibloq;

	@Column(name="TFT010_NUMREQUERIM", length=15)
	private String numrequerim;

	@Column(name="TFT010_SYSALTA_DIA", nullable=false)
	private Timestamp sysaltaDia;

	@Column(name="TFT010_SYSALTA_OPERADOR", nullable=false, length=8)
	private String sysaltaOperador;

	@Column(name="TFT010_SYSALTA_PROGRAMA", nullable=false, length=30)
	private String sysaltaPrograma;

	@Column(name="TFT010_SYSDIA", nullable=false)
	private Timestamp sysdia;

	@Column(name="TFT010_SYSENTIDAD", nullable=false, length=4)
	private String sysentidad;

	@Column(name="TFT010_SYSOPERADOR", nullable=false, length=8)
	private String sysoperador;

	@Column(name="TFT010_SYSPROGRAMA", nullable=false, length=30)
	private String sysprograma;

	@Column(name="TFT010_TIPINTERV", length=30)
	private String tipinterv;

	public ErroresFtf() {
	}

	public String getCoderrores() {
		return this.coderrores;
	}

	public void setCoderrores(String coderrores) {
		this.coderrores = coderrores;
	}

	public String getIdfichero() {
		return this.idfichero;
	}

	public void setIdfichero(String idfichero) {
		this.idfichero = idfichero;
	}

	public BigDecimal getLineaerror() {
		return this.lineaerror;
	}

	public void setLineaerror(BigDecimal lineaerror) {
		this.lineaerror = lineaerror;
	}

	public BigDecimal getLinfinbloq() {
		return this.linfinbloq;
	}

	public void setLinfinbloq(BigDecimal linfinbloq) {
		this.linfinbloq = linfinbloq;
	}

	public BigDecimal getLininibloq() {
		return this.lininibloq;
	}

	public void setLininibloq(BigDecimal lininibloq) {
		this.lininibloq = lininibloq;
	}

	public String getNumrequerim() {
		return this.numrequerim;
	}

	public void setNumrequerim(String numrequerim) {
		this.numrequerim = numrequerim;
	}

	public Timestamp getSysaltaDia() {
		return this.sysaltaDia;
	}

	public void setSysaltaDia(Timestamp sysaltaDia) {
		this.sysaltaDia = sysaltaDia;
	}

	public String getSysaltaOperador() {
		return this.sysaltaOperador;
	}

	public void setSysaltaOperador(String sysaltaOperador) {
		this.sysaltaOperador = sysaltaOperador;
	}

	public String getSysaltaPrograma() {
		return this.sysaltaPrograma;
	}

	public void setSysaltaPrograma(String sysaltaPrograma) {
		this.sysaltaPrograma = sysaltaPrograma;
	}

	public Timestamp getSysdia() {
		return this.sysdia;
	}

	public void setSysdia(Timestamp sysdia) {
		this.sysdia = sysdia;
	}

	public String getSysentidad() {
		return this.sysentidad;
	}

	public void setSysentidad(String sysentidad) {
		this.sysentidad = sysentidad;
	}

	public String getSysoperador() {
		return this.sysoperador;
	}

	public void setSysoperador(String sysoperador) {
		this.sysoperador = sysoperador;
	}

	public String getSysprograma() {
		return this.sysprograma;
	}

	public void setSysprograma(String sysprograma) {
		this.sysprograma = sysprograma;
	}

	public String getTipinterv() {
		return this.tipinterv;
	}

	public void setTipinterv(String tipinterv) {
		this.tipinterv = tipinterv;
	}

}