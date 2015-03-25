/**
 * 
 */
package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author pablo
 *
 */
@Entity
@Table(name = "foto_publicidad", catalog = "tesis_playon")
public class FotoPublicidad implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "fotoPublicidadID")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Lob
    @Column(name = "image", columnDefinition="mediumblob")
    private byte[] image;
    
    @Column(name = "url")
    private String url;

    /**
     * 
     */
    public FotoPublicidad() {
	super();
    }

    /**
     * @param nombre
     * @param image
     * @param url
     */
    public FotoPublicidad(String nombre, byte[] image, String url) {
	super();
	this.nombre = nombre;
	this.image = image;
	this.url = url;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the image
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "FotoPublicidad [id=" + id + ", nombre=" + nombre + ", image=" + Arrays.toString(image) + ", url=" + url
		+ "]";
    }
    
}
