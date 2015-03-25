/**
 * 
 */
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
 * Clase de negocio que contiene la denuncia hac�a un vehiculo
 * 
 * @author Pablo
 * 
 */

@Entity
@Table(name = "denuncia_vehiculo", catalog = "tesis_playon")
public class DenunciaVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "denunciaVehiculoID")
    private Integer id;

    @Column(name = "asunto")
    private String asunto;

    @Column(name = "fechaAlta")
    private Date fechaAlta;

    @ManyToOne
    @JoinColumn(name = "vehiculoID")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "playaID")
    private Playa playa;

    @ManyToOne
    @JoinColumn(name = "estadoID")
    private EstadoDenuncia estado;

    public DenunciaVehiculo() {
    }

    public DenunciaVehiculo(String asunto, Date fechaAlta, Vehiculo vehiculo, Playa playa, EstadoDenuncia estado) {
	this.asunto = asunto;
	this.fechaAlta = fechaAlta;
	this.vehiculo = vehiculo;
	this.playa = playa;
	this.estado = estado;
    }

    public String getAsunto() {
	return asunto;
    }

    public void setAsunto(String asunto) {
	this.asunto = asunto;
    }

    public Date getFechaAlta() {
	return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
	this.fechaAlta = fechaAlta;
    }

    public Vehiculo getVehiculo() {
	return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
	this.vehiculo = vehiculo;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public Integer getId() {
	return id;
    }

    public EstadoDenuncia getEstado() {
	return estado;
    }

    public void setEstado(EstadoDenuncia estado) {
	this.estado = estado;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	DenunciaVehiculo otroDenuncia = (DenunciaVehiculo) object;
	if (id != otroDenuncia.id)
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "DenunciaVehiculo:\t [denunciaVehiculoID= " + id + ", asunto= " + asunto + ", fechaAlta= "
		+ fechaAlta.toString() + ", Vehiculo= " + vehiculo.getPatente() + ", Playa= " + playa + "]";
    }

}
