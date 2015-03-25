package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase de negocio que contiene los diferentes tipos de estadia.
 * 
 * @author garribere
 * @date 05/07/2012
 * 
 */
@Entity
@Table(name = "tipo_estadia", catalog = "tesis_playon")
public class TipoEstadia implements Serializable {

    private static final long serialVersionUID = 6556199133101963829L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "tipoEstadiaID")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    public TipoEstadia() {
    }

    public TipoEstadia(String nombre, String descripcion) {
	this.nombre = nombre;
	this.descripcion = descripcion;
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

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	TipoEstadia otroTipoEstadia = (TipoEstadia) object;
	if (id != otroTipoEstadia.id)
	    return false;
	if (nombre == null ? otroTipoEstadia.nombre != null : !nombre.equals(otroTipoEstadia.nombre))
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "TipoEstadia:\t [tipoEstadiaID=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
    }

}
