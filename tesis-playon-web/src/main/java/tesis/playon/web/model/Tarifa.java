package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * Clase de negocio que contiene la tarifa de una playa
 * 
 * @author gmorales
 * 
 */
@Entity
@Table(name = "tarifa", catalog = "tesis_playon")
public class Tarifa implements Serializable {

    private static final long serialVersionUID = 2382561437443895633L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "tarifaID")
    private Integer id;

    @Column(name = "importe")
    private Float importe;

    @Column(name = "vigente", columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean vigente;

    @Column(name = "fechaAlta")
    private Date fechaAlta;

    @Column(name = "fechaBaja")
    private Date fechaBaja;

    @ManyToOne
    @JoinColumn(name = "playaID")
    private Playa playa;

    @ManyToOne
    @JoinColumn(name = "tipoEstadiaID")
    private TipoEstadia tipoEstadia;

    @ManyToOne
    @JoinColumn(name = "categoriaVehiculoID")
    private CategoriaVehiculo categoriaVehiculo;

    public Tarifa() {
	this.fechaAlta = new Date();
    }

    public Tarifa(Float importe, Playa playa, TipoEstadia tipoEstadia, CategoriaVehiculo categoriaVehiculo) {
	this.importe = importe;
	this.playa = playa;
	this.tipoEstadia = tipoEstadia;
	this.categoriaVehiculo = categoriaVehiculo;
	this.fechaAlta = new Date();
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Float getImporte() {
	return importe;
    }

    public void setImporte(Float importe) {
	this.importe = importe;
    }

    public Boolean getVigente() {
	return vigente;
    }

    public void setVigente(Boolean vigente) {
	this.vigente = vigente;
    }

    public Date getFechaAlta() {
	return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
	this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
	return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
	this.fechaBaja = fechaBaja;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public TipoEstadia getTipoEstadia() {
	return tipoEstadia;
    }

    public void setTipoEstadia(TipoEstadia tipoEstadia) {
	this.tipoEstadia = tipoEstadia;
    }

    public CategoriaVehiculo getCategoriaVehiculo() {
	return categoriaVehiculo;
    }

    public void setCategoriaVehiculo(CategoriaVehiculo categoriaVehiculo) {
	this.categoriaVehiculo = categoriaVehiculo;
    }

    public boolean equals(Object other) {
		return (id != null && other != null && getClass() == other.getClass()) ? id
				.equals(((Tarifa) other).id) : (other == this);
	}

    @Override
    public String toString() {
	return "Tarifa:\t [tarifaID=" + id + ", importe=" + importe + ", " + playa + ", " + categoriaVehiculo + ", "
		+ tipoEstadia + "]";
    }

}