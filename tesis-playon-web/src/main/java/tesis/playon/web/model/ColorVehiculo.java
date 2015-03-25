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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Pablo
 * 
 */
@Entity
@Table(name = "color_vehiculo", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nombre") })
public class ColorVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "colorVehiculoID")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "valorHexadecimal")
    private String valorHexadecimal;

    public ColorVehiculo(String nombre) {
	this.nombre = nombre;
    }

    public ColorVehiculo() {
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;

    }

    public String getValorHexadecimal() {
	return valorHexadecimal;
    }

    public void setValorHexadecimal(String valorHexadecimal) {
	this.valorHexadecimal = valorHexadecimal;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	ColorVehiculo otroColor = (ColorVehiculo) object;
	if (id != otroColor.id)
	    return false;
	if (nombre == null ? otroColor.nombre != null : !nombre.equals(otroColor.nombre))
	    return false;
	if (valorHexadecimal == null ? otroColor.valorHexadecimal != null : !valorHexadecimal
		.equals(otroColor.valorHexadecimal))
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "ColorVehiculo:\t[colorVehiculoID= " + id + ", nombre= " + nombre + "]";
    }

}
