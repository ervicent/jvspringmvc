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

/**
 * @author Pablo
 * 
 */
@Entity
@Table(name = "estado_denuncia", catalog = "tesis_playon")
public class EstadoDenuncia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "estadoDenunciaID")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    public EstadoDenuncia(String nombre, String descripcion) {
	this.nombre = nombre;
	this.descripcion = descripcion;
    }

    public EstadoDenuncia() {
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

    public Integer getId() {
	return id;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	EstadoDenuncia otroEstado = (EstadoDenuncia) object;
	if (id != otroEstado.id)
	    return false;
	if (nombre == null ? otroEstado.nombre != null : !nombre.equals(otroEstado.nombre))
	    return false;

	return true;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    @Override
    public String toString() {
	return "EstadoDenuncia:\t [estadoDenunciaID= " + id + ", nombre= " + nombre + ", descripcion= " + descripcion
		+ "]";
    }

}
