package sampleJpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the TFT032NOTIF_DEVUE_RPP database table.
 *
 */
@Entity
@Table(name="TFT032NOTIF_DEVUE_RPP")
@NamedQuery(name="NotifDevueRpp.findAll", query="SELECT n FROM NotifDevueRpp n")
public class NotifDevueRpp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TFT032_FECDEVOLUCION", length=8)
	private String fecdevolucion;

	@Column(name="TFT032_CODBARRAS", length=20)
	private String codbarras;

	@Column(name="TFT032_CODENTIDAD", nullable=false, length=5)
	private String codentidad;

	@Column(name="TFT032_ESTADO", length=1)
	private String estado;

	@Column(name="TFT032_FECGENERA", length=8)
	private String fecgenera;

	@Column(name="TFT032_FECPROCESO", length=8)
	private String fecproceso;

	@Column(name="TFT032_MOTDEVOLUCION", length=15)
	private String motdevolucion;

	@Column(name="TFT032_NUMCUENTA", length=29)
	private String numcuenta;

	@Column(name="TFT032_NUMDOCUMENTO", length=25)
	private String numdocumento;

	@Column(name="TFT032_NUMREGTOTAL", length=9)
	private String numregtotal;

	@Column(name="TFT032_PAIS", length=3)
	private String pais;

	@Column(name="TFT032_TIPDOCUMENTO", length=2)
	private String tipdocumento;

	@Column(name="TFT032_TIPREGISTRO", nullable=false, length=6)
	private String tipregistro;

	public NotifDevueRpp() {
	}

	public String getFecdevolucion() {
		return this.fecdevolucion;
	}

	public void setFecdevolucion(String fecdevolucion) {
		this.fecdevolucion = fecdevolucion;
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

}