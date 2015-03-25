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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Pablo
 * 
 */
@Entity
@Table(name = "foto", catalog = "tesis_playon")
public class Foto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "fotoID")
    private int id;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "perfilPlayaID")
    private PerfilPlaya perfilPlaya;
    
    @Lob
    @Column(name = "image", columnDefinition="mediumblob")
    private byte[] image;

    public Foto(String descripcion, String link, PerfilPlaya perfilPlaya) {
	this.descripcion = descripcion;
	this.nombre = link;
	this.perfilPlaya = perfilPlaya;
    }

    public Foto() {
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public PerfilPlaya getPerfilPlaya() {
	return perfilPlaya;
    }

    public void setPerfilPlaya(PerfilPlaya perfilPlaya) {
	this.perfilPlaya = perfilPlaya;
    }

    public int getId() {
	return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	Foto otroFoto = (Foto) object;
	if (id != otroFoto.id)
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "Foto:\t [fotoID= " + id + ", descripcion= " + descripcion + ", link= " + nombre + ", perfilPlayaID= "
		+ perfilPlaya.getId() + "]";
    }
}
