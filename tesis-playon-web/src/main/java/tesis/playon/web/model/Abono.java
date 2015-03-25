package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author alejandro
 * @date 07/07/2012
 */
@Entity
@Table(name = "abono", catalog = "tesis_playon")
public class Abono implements Serializable {

    private static final long serialVersionUID = 7220407400717111379L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "abonoID")
    private Integer id;

    @Column(name = "fechaVigenciaDesde")
    private Date fechaVigenciaDesde;

    @Column(name = "fechaVigenciaHasta")
    private Date fechaVigenciaHasta;

    @ManyToOne
    @JoinColumn(name = "tarifaID")
    private Tarifa tarifa;

    @ManyToOne
    @JoinColumn(name = "vehiculoID")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "playaID")
    private Playa playa;
    
    @ManyToOne
    @JoinColumn(name = "promocionID")
    private Promocion promocion;

    /**
     * Constructor por defecto.
     */
    public Abono() {
	super();
    }

    /**
     * Constructor con parámetros.
     * 
     * @param fechaVigenciaDesde
     * @param fechaVigenciaHasta
     * @param tarifa
     * @param cliente
     * @param playa
     */
    public Abono(Date fechaVigenciaDesde, Date fechaVigenciaHasta, Tarifa tarifa,  Playa playa) {
	super();
	this.fechaVigenciaDesde = fechaVigenciaDesde;
	this.fechaVigenciaHasta = fechaVigenciaHasta;
	this.tarifa = tarifa;
	this.playa = playa;
    }

    public Date getFechaVigenciaDesde() {
	return fechaVigenciaDesde;
    }

    public void setFechaVigenciaDesde(Date fechaVigenciaDesde) {
	this.fechaVigenciaDesde = fechaVigenciaDesde;
    }

    public Date getFechaVigenciaHasta() {
	return fechaVigenciaHasta;
    }

    public void setFechaVigenciaHasta(Date fechaVigenciaHasta) {
	this.fechaVigenciaHasta = fechaVigenciaHasta;
    }

    public Tarifa getTarifa() {
	return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
	this.tarifa = tarifa;
    }

    public Playa getPlaya() {
	return playa;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public Integer getId() {
	return id;
    }

    public Promocion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "Abono:\t [abonoID=" + id + ", fechaVigenciaDesde=" + fechaVigenciaDesde.toString()
		+ ", fechaVigenciaHasta=" + fechaVigenciaHasta.toString() + ", " + vehiculo.toString() + ", ="
		+ playa.toString() + "]";
    }
}
