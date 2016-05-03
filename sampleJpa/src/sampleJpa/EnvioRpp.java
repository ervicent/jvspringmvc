package sampleJpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the TFT007ENVIO_RPP database table.
 *
 */
@Entity
@Table(name="TFT007ENVIO_RPP")
@NamedQuery(name="EnvioRpp.findAll", query="SELECT e FROM EnvioRpp e")
public class EnvioRpp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TFT007_APELLIDO1", length=30)
	private String apellido1;

	@Column(name="TFT007_APELLIDO2", length=30)
	private String apellido2;

	@Column(name="TFT007_CODENTIDAD", nullable=false, length=5)
	private String codentidad;

	@Column(name="TFT007_CODPAIS", length=3)
	private String codpais;

	@Column(name="TFT007_CODPOSTAL", length=5)
	private String codpostal;

	@Column(name="TFT007_CODPROV", length=2)
	private String codprov;

	@Column(name="TFT007_CONTRATO", length=45)
	private String contrato;

	@Column(name="TFT007_DENOMCONTRATO", length=55)
	private String denomcontrato;

	@Column(name="TFT007_DIRECCION", length=110)
	private String direccion;

	@Column(name="TFT007_ESTADO", nullable=false, length=1)
	private String estado;

	@Column(name="TFT007_FECGENERA", length=8)
	private String fecgenera;

	@Column(name="TFT007_FECMEMBRETE", length=8)
	private String fecmembrete;

	@Column(name="TFT007_IDIOMA", length=2)
	private String idioma;

	@Column(name="TFT007_IMPPDTE", length=15)
	private String imppdte;

	@Column(name="TFT007_LOCALIDAD", length=50)
	private String localidad;

	@Column(name="TFT007_NOMBRE1", length=25)
	private String nombre1;

	@Column(name="TFT007_NOMBRE2", length=25)
	private String nombre2;

	@Column(name="TFT007_NUMCUENTA", length=29)
	private String numcuenta;

	@Column(name="TFT007_NUMDIAS", length=2)
	private String numdias;

	@Column(name="TFT007_NUMDOCUMENTO", length=25)
	private String numdocumento;

	@Column(name="TFT007_NUMREGTOTAL", length=9)
	private String numregtotal;

	@Column(name="TFT007_NUMSUCURSAL", length=4)
	private String numsucursal;

	@Column(name="TFT007_PAIS", length=3)
	private String pais;

	@Column(name="TFT007_TELEFONO", length=9)
	private String telefono;

	@Column(name="TFT007_TIPDOCUMENTO", length=2)
	private String tipdocumento;

	@Column(name="TFT007_TIPINTERV", length=2)
	private String tipinterv;

	@Column(name="TFT007_TIPPRODFINAN", length=2)
	private String tipprodfinan;

	@Column(name="TFT007_TIPREGISTRO", nullable=false, length=3)
	private String tipregistro;

	public EnvioRpp() {
	}

	public String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getCodentidad() {
		return this.codentidad;
	}

	public void setCodentidad(String codentidad) {
		this.codentidad = codentidad;
	}

	public String getCodpais() {
		return this.codpais;
	}

	public void setCodpais(String codpais) {
		this.codpais = codpais;
	}

	public String getCodpostal() {
		return this.codpostal;
	}

	public void setCodpostal(String codpostal) {
		this.codpostal = codpostal;
	}

	public String getCodprov() {
		return this.codprov;
	}

	public void setCodprov(String codprov) {
		this.codprov = codprov;
	}

	public String getContrato() {
		return this.contrato;
	}

	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

	public String getDenomcontrato() {
		return this.denomcontrato;
	}

	public void setDenomcontrato(String denomcontrato) {
		this.denomcontrato = denomcontrato;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	public String getFecmembrete() {
		return this.fecmembrete;
	}

	public void setFecmembrete(String fecmembrete) {
		this.fecmembrete = fecmembrete;
	}

	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getImppdte() {
		return this.imppdte;
	}

	public void setImppdte(String imppdte) {
		this.imppdte = imppdte;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getNombre1() {
		return this.nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	public String getNombre2() {
		return this.nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public String getNumcuenta() {
		return this.numcuenta;
	}

	public void setNumcuenta(String numcuenta) {
		this.numcuenta = numcuenta;
	}

	public String getNumdias() {
		return this.numdias;
	}

	public void setNumdias(String numdias) {
		this.numdias = numdias;
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

	public String getNumsucursal() {
		return this.numsucursal;
	}

	public void setNumsucursal(String numsucursal) {
		this.numsucursal = numsucursal;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipdocumento() {
		return this.tipdocumento;
	}

	public void setTipdocumento(String tipdocumento) {
		this.tipdocumento = tipdocumento;
	}

	public String getTipinterv() {
		return this.tipinterv;
	}

	public void setTipinterv(String tipinterv) {
		this.tipinterv = tipinterv;
	}

	public String getTipprodfinan() {
		return this.tipprodfinan;
	}

	public void setTipprodfinan(String tipprodfinan) {
		this.tipprodfinan = tipprodfinan;
	}

	public String getTipregistro() {
		return this.tipregistro;
	}

	public void setTipregistro(String tipregistro) {
		this.tipregistro = tipregistro;
	}

}