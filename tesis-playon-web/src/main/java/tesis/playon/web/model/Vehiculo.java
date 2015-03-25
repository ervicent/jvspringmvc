/**
 * 
 */
package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Pablo
 * 
 */
@Entity
@Table(name = "vehiculo", catalog = "tesis_playon", uniqueConstraints = {
	@UniqueConstraint(columnNames = "codigoBarra"), @UniqueConstraint(columnNames = "patente") })
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "vehiculoID")
    private Integer id;

    @Column(name = "anio")
    private int anio;

    @Column(name = "codigoBarra")
    private String codigoBarra;

    @ManyToOne
    @JoinColumn(name = "colorVehiculoID")
    private ColorVehiculo colorVehiculo;

    @Column(name = "habilitado")
    private boolean habilitado;

    @ManyToOne
    @JoinColumn(name = "modeloVehiculoID")
    private ModeloVehiculo modeloVehiculo;

    @Column(name = "patente")
    private String patente;

    @ManyToOne
    @JoinColumn(name = "clienteID")
    private Cliente cliente;

    public Vehiculo(int anio, String codigoBarra, ColorVehiculo colorVehiculo,
	    boolean habilitado, ModeloVehiculo modeloVehiculo, String patente, Cliente cliente) {
	this.anio = anio;
	this.codigoBarra = codigoBarra;
	this.colorVehiculo = colorVehiculo;
	this.habilitado = habilitado;
	this.modeloVehiculo = modeloVehiculo;
	this.patente = patente;
	this.cliente = cliente;
    }

    public Vehiculo(){}

    public String getCodigoBarra() {
	return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
	this.codigoBarra = codigoBarra;
    }

    public ColorVehiculo getColorVehiculo() {
	return colorVehiculo;
    }

    public void setColorVehiculo(ColorVehiculo colorVehiculo) {
	this.colorVehiculo = colorVehiculo;
    }

    public boolean isHabilitado() {
	return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
	this.habilitado = habilitado;
    }

    public ModeloVehiculo getModeloVehiculo() {
	return modeloVehiculo;
    }

    public void setModeloVehiculo(ModeloVehiculo modeloVehiculo) {
	this.modeloVehiculo = modeloVehiculo;
    }

    public String getPatente() {
	return patente;
    }

    public void setPatente(String patente) {
	this.patente = patente;
    }

    public Cliente getCliente() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public int getAnio() {
	return anio;
    }

    public void setAnio(int anio) {
	this.anio = anio;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	Vehiculo otroVehiculo = (Vehiculo) object;
	if (id != otroVehiculo.id)
	    return false;
	if (patente == null ? otroVehiculo.patente != null : !patente.equals(otroVehiculo.patente))
	    return false;

	return true;
    }

    
    
    @Override
    public String toString() {
	return "Vehiculo:\t [vehiculoID=" + id + ", anio=" + anio + ", codigoBarra=" + codigoBarra + ", habilitado="
		+ habilitado + ", patente=" + patente + "]";
    }

}
