package sampleJpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the TFT029ERRORES_RAI database table.
 *
 */
@Entity
@Table(name="TFT029ERRORES_RAI")
@NamedQuery(name="ErroresRai.findAll", query="SELECT e FROM ErroresRai e")
public class ErroresRai implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TFT029_CLAVEACT", nullable=false, length=1)
	private String tft029Claveact;

	@Column(name="TFT029_CODENTINFO", nullable=false, length=4)
	private String tft029Codentinfo;

	@Column(name="TFT029_CODREFER", nullable=false, length=15)
	private String tft029Codrefer;

	@Column(name="TFT029_ERRORES", length=17)
	private String tft029Errores;

	@Column(name="TFT029_ESTADO", length=1)
	private String tft029Estado;

	@Column(name="TFT029_SECUENCIA", nullable=false, length=9)
	private String tft029Secuencia;

	@Column(name="TFT029_SYSALTA_DIA", nullable=false)
	private Timestamp tft029SysaltaDia;

	@Column(name="TFT029_SYSALTA_OPERADOR", nullable=false, length=8)
	private String tft029SysaltaOperador;

	@Column(name="TFT029_SYSALTA_PROGRAMA", nullable=false, length=30)
	private String tft029SysaltaPrograma;

	@Column(name="TFT029_SYSDIA", nullable=false)
	private Timestamp tft029Sysdia;

	@Column(name="TFT029_SYSENTIDAD", nullable=false, length=4)
	private String tft029Sysentidad;

	@Column(name="TFT029_SYSOPERADOR", nullable=false, length=8)
	private String tft029Sysoperador;

	@Column(name="TFT029_SYSPROGRAMA", nullable=false, length=30)
	private String tft029Sysprograma;

	@Column(name="TFT029_TIPFICHERO", nullable=false, length=3)
	private String tft029Tipfichero;

	public ErroresRai() {
	}

	public String getTft029Claveact() {
		return this.tft029Claveact;
	}

	public void setTft029Claveact(String tft029Claveact) {
		this.tft029Claveact = tft029Claveact;
	}

	public String getTft029Codentinfo() {
		return this.tft029Codentinfo;
	}

	public void setTft029Codentinfo(String tft029Codentinfo) {
		this.tft029Codentinfo = tft029Codentinfo;
	}

	public String getTft029Codrefer() {
		return this.tft029Codrefer;
	}

	public void setTft029Codrefer(String tft029Codrefer) {
		this.tft029Codrefer = tft029Codrefer;
	}

	public String getTft029Errores() {
		return this.tft029Errores;
	}

	public void setTft029Errores(String tft029Errores) {
		this.tft029Errores = tft029Errores;
	}

	public String getTft029Estado() {
		return this.tft029Estado;
	}

	public void setTft029Estado(String tft029Estado) {
		this.tft029Estado = tft029Estado;
	}

	public String getTft029Secuencia() {
		return this.tft029Secuencia;
	}

	public void setTft029Secuencia(String tft029Secuencia) {
		this.tft029Secuencia = tft029Secuencia;
	}

	public Timestamp getTft029SysaltaDia() {
		return this.tft029SysaltaDia;
	}

	public void setTft029SysaltaDia(Timestamp tft029SysaltaDia) {
		this.tft029SysaltaDia = tft029SysaltaDia;
	}

	public String getTft029SysaltaOperador() {
		return this.tft029SysaltaOperador;
	}

	public void setTft029SysaltaOperador(String tft029SysaltaOperador) {
		this.tft029SysaltaOperador = tft029SysaltaOperador;
	}

	public String getTft029SysaltaPrograma() {
		return this.tft029SysaltaPrograma;
	}

	public void setTft029SysaltaPrograma(String tft029SysaltaPrograma) {
		this.tft029SysaltaPrograma = tft029SysaltaPrograma;
	}

	public Timestamp getTft029Sysdia() {
		return this.tft029Sysdia;
	}

	public void setTft029Sysdia(Timestamp tft029Sysdia) {
		this.tft029Sysdia = tft029Sysdia;
	}

	public String getTft029Sysentidad() {
		return this.tft029Sysentidad;
	}

	public void setTft029Sysentidad(String tft029Sysentidad) {
		this.tft029Sysentidad = tft029Sysentidad;
	}

	public String getTft029Sysoperador() {
		return this.tft029Sysoperador;
	}

	public void setTft029Sysoperador(String tft029Sysoperador) {
		this.tft029Sysoperador = tft029Sysoperador;
	}

	public String getTft029Sysprograma() {
		return this.tft029Sysprograma;
	}

	public void setTft029Sysprograma(String tft029Sysprograma) {
		this.tft029Sysprograma = tft029Sysprograma;
	}

	public String getTft029Tipfichero() {
		return this.tft029Tipfichero;
	}

	public void setTft029Tipfichero(String tft029Tipfichero) {
		this.tft029Tipfichero = tft029Tipfichero;
	}

}