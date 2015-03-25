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
 * Clase de negocio que contiene los diferentes Estados de publicidad.
 * 
 * @author alejandro
 * @date 02/07/2012
 * 
 */
@Entity
@Table(name = "estado_publicidad", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nombre") })
public class EstadoPublicidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "estadoPublicidadID", nullable = false)
    private Integer id;

    @Column(name = "nombre", unique = true, nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", unique = false, nullable = true)
    private String descripcion;

    /**
     * Constructor con parámetros.
     * 
     * @param id
     * @param nombre
     * @param descripcion
     */
    public EstadoPublicidad(String nombre, String descripcion) {
	this.nombre = nombre;
	this.descripcion = descripcion;
    }

    public EstadoPublicidad() {
	super();
    }

    /**
     * Devuelve el ID del objeto.
     * 
     * @return El ID del objeto.
     */
    public Integer getId() {
	return id;
    }

    /**
     * Devuelve el nombre del objeto.
     * 
     * @return El nombre del objeto.
     */
    public String getNombre() {
	return nombre;
    }

    /**
     * Setea un nuevo nombre al objeto.
     * 
     * @param nombre
     *            El nombre del objeto.
     */
    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    /**
     * Devuelve la descripción del objeto.
     * 
     * @return
     */
    public String getDescripcion() {
	return descripcion;
    }

    /**
     * Setea una nueva descripción al objeto.
     * 
     * @param descripcion
     *            La descripción del objeto.
     */
    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	EstadoPublicidad otroEstado = (EstadoPublicidad) object;
	if (id != otroEstado.id)
	    return false;
	if (nombre == null ? otroEstado.nombre != null : !nombre.equals(otroEstado.nombre))
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "EstadoPublicidad:\t [estadoPublicidadID= " + id + "nombre= " + nombre + "descripcion= " + descripcion
		+ "]";
    }

}
