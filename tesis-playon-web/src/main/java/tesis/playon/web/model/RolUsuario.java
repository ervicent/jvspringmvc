package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author gmorales
 * 
 */
@Entity
@Table(name = "rol_usuario")
public class RolUsuario {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "rolUsuarioID")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    public RolUsuario() {
    }

    public RolUsuario(String nombre) {
	this.nombre = nombre;
    }

    public RolUsuario(String nombre, String descripcion) {
	this.nombre = nombre;
	this.descripcion = descripcion;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
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

	RolUsuario otroRolUsuario = (RolUsuario) object;
	if (id != otroRolUsuario.id)
	    return false;
	if (nombre == null ? otroRolUsuario.nombre != null : !nombre.equals(otroRolUsuario.nombre))
	    return false;

	return true;
    }
    @Override
    public String toString() {
	return "RolUsuario:\t [rolUsuarioID= " + id + ", nombre= " + nombre + ", descripcion= " + descripcion + "]";
    }

}
