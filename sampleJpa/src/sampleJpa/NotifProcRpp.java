package sampleJpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the TFT033NOTIF_PROC_RPP database table.
 *
 */
@Entity
@Table(name="TFT033NOTIF_PROC_RPP")
@NamedQuery(name="NotifProcRpp.findAll", query="SELECT n FROM NotifProcRpp n")
public class NotifProcRpp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TFT033_CODBARRAS", length=20)
	private String codbarras;

	@Column(name="TFT033_CODENTIDAD", nullable=false, length=5)
	private String codentidad;

	@Column(name="TFT033_ESTADO", length=1)
	private String estado;

	@Column(name="TFT033_ESTADODEV", length=20)
	private String estadodev;

	@Column(name="TFT033_FECACCION", length=8)
	private String fecaccion;

	@Column(name="TFT033_FECDEVOLUCION", length=8)
	private String fecdevolucion;

	@Column(name="TFT033_FECFINPERIOD", length=8)
	private String fecfinperiod;

	@Column(name="TFT033_FECGENERA", length=8)
	private String fecgenera;

	@Column(name="TFT033_FECINIPERIOD", length=8)
	private String feciniperiod;

	@Column(name="TFT033_FECPROCESO", length=8)
	private String fecproceso;

	@Column(name="TFT033_IDENTIFICAC", length=6)
	private String identificac;

	@Column(name="TFT033_MOTDEVOLUCION", length=15)
	private String motdevolucion;

	@Column(name="TFT033_NUMCUENTA", length=29)
	private String numcuenta;

	@Column(name="TFT033_NUMDOCUMENTO", length=25)
	private String numdocumento;

	@Column(name="TFT033_NUMREGTOTAL", length=9)
	private String numregtotal;

	@Column(name="TFT033_PAIS", length=3)
	private String pais;

	@Column(name="TFT033_TIPDOCUMENTO", length=2)
	private String tipdocumento;

	@Column(name="TFT033_TIPREGISTRO", nullable=false, length=6)
	private String tipregistro;

	@Column(name="TFT033_USUARIO", length=20)
	private String usuario;

	public NotifProcRpp() {
	}

	public String getCodbarras() {
		return this.codbarras;
	}

	public void setCodbarras(String codbarras) {
		this.codbarras = codbarras;
	}

	public String getCodentidad() {
		return this.codentidad;
	}

	public void setCodentidad(String codentidad) {
		this.codentidad = codentidad;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstadodev() {
		return this.estadodev;
	}

	public void setEstadodev(String estadodev) {
		this.estadodev = estadodev;
	}

	public String getFecaccion() {
		return this.fecaccion;
	}

	public void setFecaccion(String fecaccion) {
		this.fecaccion = fecaccion;
	}

	public String getFecdevolucion() {
		return this.fecdevolucion;
	}

	public void setFecdevolucion(String fecdevolucion) {
		this.fecdevolucion = fecdevolucion;
	}

	public String getFecfinperiod() {
		return this.fecfinperiod;
	}

	public void setFecfinperiod(String fecfinperiod) {
		this.fecfinperiod = fecfinperiod;
	}

	public String getFecgenera() {
		return this.fecgenera;
	}

	public void setFecgenera(String fecgenera) {
		this.fecgenera = fecgenera;
	}

	public String getFeciniperiod() {
		return this.feciniperiod;
	}

	public void setFeciniperiod(String feciniperiod) {
		this.feciniperiod = feciniperiod;
	}

	public String getFecproceso() {
		return this.fecproceso;
	}

	public void setFecproceso(String fecproceso) {
		this.fecproceso = fecproceso;
	}

	public String getIdentificac() {
		return this.identificac;
	}

	public void setIdentificac(String identificac) {
		this.identificac = identificac;
	}

	public String getMotdevolucion() {
		return this.motdevolucion;
	}

	public void setMotdevolucion(String motdevolucion) {
		this.motdevolucion = motdevolucion;
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

	public String getTipregistro() {
		return this.tipregistro;
	}

	public void setTipregistro(String tipregistro) {
		this.tipregistro = tipregistro;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}