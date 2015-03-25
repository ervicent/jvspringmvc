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

//import javax.persistence.FetchType;

/**
 * @author Pablo
 * 
 */
@Entity
@Table(name = "modelo_vehiculo", catalog = "tesis_playon", uniqueConstraints = {
		@UniqueConstraint(columnNames = "nombre"),
		@UniqueConstraint(columnNames = "marcaVehiculoID") })
public class ModeloVehiculo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "modeloVehiculoID")
	private Integer id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;

	@ManyToOne
	@JoinColumn(name = "marcaVehiculoID")
	private MarcaVehiculo marcaVehiculo;

	@ManyToOne
	@JoinColumn(name = "categoriaID")
	private CategoriaVehiculo categoriaVehiculo;

	public ModeloVehiculo(String descripcion, MarcaVehiculo marcaVehiculo,
			String nombre) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.marcaVehiculo = marcaVehiculo;
	}

	public ModeloVehiculo() {
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

	public MarcaVehiculo getMarcaVehiculo() {
		return marcaVehiculo;
	}

	public void setMarcaVehiculo(MarcaVehiculo marcaVehiculo) {
		this.marcaVehiculo = marcaVehiculo;
	}

	public Integer getId() {
		return id;
	}

	public CategoriaVehiculo getCategoriaVehiculo() {
		return categoriaVehiculo;
	}

	public void setCategoriaVehiculo(CategoriaVehiculo categoriaVehiculo) {
		this.categoriaVehiculo = categoriaVehiculo;
	}

	public boolean equals(Object other) {
		return (id != null && other != null && getClass() == other.getClass()) ? id
				.equals(((ModeloVehiculo) other).id) : (other == this);
	}

	@Override
	public String toString() {
		return "ModeloVehiculo:\t [modeloVehiculoID= " + id + ", nombre= "
				+ nombre + ", descripcion= " + descripcion + ", "
				+ marcaVehiculo.toString() + "]";
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
