package sampleJpa;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the TFT008FICHERO_FTF database table.
 * 
 */
@Entity
@Table(name="TFT008FICHERO_FTF")
@NamedQuery(name="FicheroFtf.findAll", query="SELECT t FROM FicheroFtf t")
public class FicheroFtf implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TFT008_CAMBIONPROD", length=38)
	private String cambionprod;

	@Column(name="TFT008_CODPAIEXP", length=2)
	private String codpaiexp;

	@Column(name="TFT008_CODPAINAC", length=2)
	private String codpainac;

	@Column(name="TFT008_CODPAIRES", length=2)
	private String codpaires;

	@Column(name="TFT008_ESTADO", length=1)
	private String estado;

	@Column(name="TFT008_FECALTINT", length=8)
	private String fecaltint;

	@Column(name="TFT008_FECAPERT", length=8)
	private String fecapert;

	@Column(name="TFT008_FECBAJINT", length=8)
	private String fecbajint;

	@Column(name="TFT008_FECCANC", length=8)
	private String feccanc;

	@Column(name="TFT008_FECNAC", length=8)
	private String fecnac;

	@Column(name="TFT008_IDFICHERO", length=30)
	private String idfichero;

	@Column(name="TFT008_IDREQUERIM", length=15)
	private String idrequerim;

	@Column(name="TFT008_LINEA", precision=10)
	private BigDecimal linea;

	@Column(name="TFT008_NIFENTDECL", length=9)
	private String nifentdecl;

	@Column(name="TFT008_NIFENTDECLANT", length=9)
	private String nifentdeclant;

	@Column(name="TFT008_NIFENTNUMANT", length=9)
	private String nifentnumant;

	@Column(name="TFT008_NOMBRE", length=178)
	private String nombre;

	@Column(name="TFT008_NUMDOCUM", length=30)
	private String numdocum;

	@Column(name="TFT008_NUMFICH", length=3)
	private String numfich;

	@Column(name="TFT008_NUMFICHANT", length=3)
	private String numfichant;

	@Column(name="TFT008_NUMPROD", length=38)
	private String numprod;

	@Column(name="TFT008_PERDECLANT", length=6)
	private String perdeclant;

	@Column(name="TFT008_PERIODECL", length=6)
	private String periodecl;

	@Column(name="TFT008_REGPROD", length=10)
	private String regprod;

	@Column(name="TFT008_REGTOT", length=10)
	private String regtot;

	@Column(name="TFT008_SECUENCIA", length=10)
	private String secuencia;

	@Column(name="TFT008_TIPDOCUM", length=2)
	private String tipdocum;

	@Column(name="TFT008_TIPFICHERO", length=1)
	private String tipfichero;

	@Column(name="TFT008_TIPINTANT", length=3)
	private String tipintant;

	@Column(name="TFT008_TIPINTERV", length=3)
	private String tipinterv;

	@Column(name="TFT008_TIPOACCION", length=1)
	private String tipoaccion;

	@Column(name="TFT008_TIPOCAMBIONPROD", length=2)
	private String tipocambionprod;

	@Column(name="TFT008_TIPONUMPROD", length=2)
	private String tiponumprod;

	@Column(name="TFT008_TIPOPROD", length=2)
	private String tipoprod;

	@Column(name="TFT008_TIPOREG", length=2)
	private String tiporeg;

	public FicheroFtf() {
	}

	public String getCambionprod() {
		return this.cambionprod;
	}

	public void setCambionprod(String cambionprod) {
		this.cambionprod = cambionprod;
	}

	public String getCodpaiexp() {
		return this.codpaiexp;
	}

	public void setCodpaiexp(String codpaiexp) {
		this.codpaiexp = codpaiexp;
	}

	public String getCodpainac() {
		return this.codpainac;
	}

	public void setCodpainac(String codpainac) {
		this.codpainac = codpainac;
	}

	public String getCodpaires() {
		return this.codpaires;
	}

	public void setCodpaires(String codpaires) {
		this.codpaires = codpaires;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFecaltint() {
		return this.fecaltint;
	}

	public void setFecaltint(String fecaltint) {
		this.fecaltint = fecaltint;
	}

	public String getFecapert() {
		return this.fecapert;
	}

	public void setFecapert(String fecapert) {
		this.fecapert = fecapert;
	}

	public String getFecbajint() {
		return this.fecbajint;
	}

	public void setFecbajint(String fecbajint) {
		this.fecbajint = fecbajint;
	}

	public String getFeccanc() {
		return this.feccanc;
	}

	public void setFeccanc(String feccanc) {
		this.feccanc = feccanc;
	}

	public String getFecnac() {
		return this.fecnac;
	}

	public void setFecnac(String fecnac) {
		this.fecnac = fecnac;
	}

	public String getIdfichero() {
		return this.idfichero;
	}

	public void setIdfichero(String idfichero) {
		this.idfichero = idfichero;
	}

	public String getIdrequerim() {
		return this.idrequerim;
	}

	public void setIdrequerim(String idrequerim) {
		this.idrequerim = idrequerim;
	}

	public BigDecimal getLinea() {
		return this.linea;
	}

	public void setLinea(BigDecimal linea) {
		this.linea = linea;
	}

	public String getNifentdecl() {
		return this.nifentdecl;
	}

	public void setNifentdecl(String nifentdecl) {
		this.nifentdecl = nifentdecl;
	}

	public String getNifentdeclant() {
		return this.nifentdeclant;
	}

	public void setNifentdeclant(String nifentdeclant) {
		this.nifentdeclant = nifentdeclant;
	}

	public String getNifentnumant() {
		return this.nifentnumant;
	}

	public void setNifentnumant(String nifentnumant) {
		this.nifentnumant = nifentnumant;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumdocum() {
		return this.numdocum;
	}

	public void setNumdocum(String numdocum) {
		this.numdocum = numdocum;
	}

	public String getNumfich() {
		return this.numfich;
	}

	public void setNumfich(String numfich) {
		this.numfich = numfich;
	}

	public String getNumfichant() {
		return this.numfichant;
	}

	public void setNumfichant(String numfichant) {
		this.numfichant = numfichant;
	}

	public String getNumprod() {
		return this.numprod;
	}

	public void setNumprod(String numprod) {
		this.numprod = numprod;
	}

	public String getPerdeclant() {
		return this.perdeclant;
	}

	public void setPerdeclant(String perdeclant) {
		this.perdeclant = perdeclant;
	}

	public String getPeriodecl() {
		return this.periodecl;
	}

	public void setPeriodecl(String periodecl) {
		this.periodecl = periodecl;
	}

	public String getRegprod() {
		return this.regprod;
	}

	public void setRegprod(String regprod) {
		this.regprod = regprod;
	}

	public String getRegtot() {
		return this.regtot;
	}

	public void setRegtot(String regtot) {
		this.regtot = regtot;
	}

	public String getSecuencia() {
		return this.secuencia;
	}

	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	public String getTipdocum() {
		return this.tipdocum;
	}

	public void setTipdocum(String tipdocum) {
		this.tipdocum = tipdocum;
	}

	public String getTipfichero() {
		return this.tipfichero;
	}

	public void setTipfichero(String tipfichero) {
		this.tipfichero = tipfichero;
	}

	public String getTipintant() {
		return this.tipintant;
	}

	public void setTipintant(String tipintant) {
		this.tipintant = tipintant;
	}

	public String getTipinterv() {
		return this.tipinterv;
	}

	public void setTipinterv(String tipinterv) {
		this.tipinterv = tipinterv;
	}

	public String getTipoaccion() {
		return this.tipoaccion;
	}

	public void setTipoaccion(String tipoaccion) {
		this.tipoaccion = tipoaccion;
	}

	public String getTipocambionprod() {
		return this.tipocambionprod;
	}

	public void setTipocambionprod(String tipocambionprod) {
		this.tipocambionprod = tipocambionprod;
	}

	public String getTiponumprod() {
		return this.tiponumprod;
	}

	public void setTiponumprod(String tiponumprod) {
		this.tiponumprod = tiponumprod;
	}

	public String getTipoprod() {
		return this.tipoprod;
	}

	public void setTipoprod(String tipoprod) {
		this.tipoprod = tipoprod;
	}

	public String getTiporeg() {
		return this.tiporeg;
	}

	public void setTiporeg(String tiporeg) {
		this.tiporeg = tiporeg;
	}

}