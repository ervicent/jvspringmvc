package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author alejandro
 * @date 09/07/2012
 */
@Entity
@Table(name = "liquidacion", catalog = "tesis_playon")
public class Liquidacion implements Serializable {

    private static final long serialVersionUID = 298942841995116227L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "liquidacionID")
    private Integer id;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "fechaDesde")
    private Date fechaDesde;

    @Column(name = "fechaHasta")
    private Date fechaHasta;

    @Column(name = "importeTotal")
    private float importeTotal;

    @OneToOne
    @JoinColumn(name = "estadiaID")
    private Estadia estadia;

    public Liquidacion() {
	super();
    }

    public Liquidacion(Date fecha, Date fechaDesde, Date fechaHasta, float importeTotal, Estadia estadia) {
	super();
	this.fecha = fecha;
	this.fechaDesde = fechaDesde;
	this.fechaHasta = fechaHasta;
	this.importeTotal = importeTotal;
	this.estadia = estadia;
    }

    public Date getFecha() {
	return fecha;
    }

    public void setFecha(Date fecha) {
	this.fecha = fecha;
    }

    public Date getFechaDesde() {
	return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
	this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
	return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
	this.fechaHasta = fechaHasta;
    }

    public float getImporteTotal() {
	return importeTotal;
    }

    public void setImporteTotal(float importeTotal) {
	this.importeTotal = importeTotal;
    }

    public Estadia getEstadia() {
	return estadia;
    }

    public void setEstadia(Estadia estadia) {
	this.estadia = estadia;
    }

    public Integer getId() {
	return id;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	Liquidacion otroLiquidacion = (Liquidacion) object;
	if (id != otroLiquidacion.id)
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "Lquidacion:\t [liquidacionID=" + id + ", fecha=" + fecha.toString() + ", importeTotal=" + importeTotal
		+ ", fechaDesde=" + fechaDesde.toString() + ", fechaHasta=" + fechaHasta.toString() + "]";
    }
}