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
@Table(name = "categoria_vehiculo", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nombre")})
public class CategoriaVehiculo implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "categoriaVehiculoID")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "descripcion", unique = false, nullable = true)
    private String descripcion;

    public CategoriaVehiculo(String nombre, String descripcion) {
	this.nombre = nombre;
	this.descripcion = descripcion;
    }    

    public CategoriaVehiculo() {
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

	CategoriaVehiculo otroCategoriaVehiculo = (CategoriaVehiculo) object;
	if (id != otroCategoriaVehiculo.id)
	    return false;
	if (nombre == null ? otroCategoriaVehiculo.nombre != null : !nombre.equals(otroCategoriaVehiculo.nombre))
	    return false;

	return true;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
	return "CategoriaVehiculo:\t[categoriaVehiculoID= " + id + ", nombre= " + nombre + ", descripcion= " + descripcion + "]";
    }
}