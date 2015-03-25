package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Clase de negocio que contiene los diferentes Paises.
 * 
 * @author gmorales
 * @date 05/07/2012
 * 
 */
@Entity
@Table(name = "barrio", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nombre"),
	@UniqueConstraint(columnNames = "localidadID") })
public class Barrio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "barrioID")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "localidadID")
    private Localidad localidad;
    
    public Barrio() {
    }

    public Barrio(String nombre, Localidad localidad) {
	this.nombre = nombre;
	this.localidad = localidad;
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

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }
    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	Barrio otroBarrio = (Barrio) object;
	if (id != otroBarrio.id)
	    return false;
	if (nombre == null ? otroBarrio.nombre != null : !nombre.equals(otroBarrio.nombre))
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "Barrio:\t [barrioID= " + id + ", nombre= " + nombre + ", " + localidad +"]";
    }

}
