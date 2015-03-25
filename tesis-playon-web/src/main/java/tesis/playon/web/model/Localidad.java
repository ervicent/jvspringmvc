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
 * Clase de negocio que contiene los diferentes localidades.
 * 
 * @author gmorales
 * @date 05/07/2012
 * 
 */
@Entity
@Table(name = "localidad", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nombre"),
	@UniqueConstraint(columnNames = "provinciaID") })
public class Localidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "localidadID")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "provinciaID")
    private Provincia provincia;

    public Localidad() {
    }

    public Localidad(String nombre, Provincia provincia) {
	this.nombre = nombre;
	this.provincia = provincia;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public Provincia getProvincia() {
	return provincia;
    }

    public void setProvincia(Provincia provincia) {
	this.provincia = provincia;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	Localidad otroLoc = (Localidad) object;
	if (id != otroLoc.id)
	    return false;
	if (nombre == null ? otroLoc.nombre != null : !nombre.equals(otroLoc.nombre))
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "Localidad:\t [localidadID= " + id + ", nombre= " + nombre + ", " + provincia.toString() + "]";
    }
}