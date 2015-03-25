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
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author pablo
 *
 */
@Entity
@Table(name = "foto_usuario", catalog = "tesis_playon")
public class FotoUsuario implements Serializable{

    private static final long serialVersionUID = -4110802485388470399L;
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "fotoUsuarioID")
    private Integer id;
    
    @Lob
    @Column(name = "fotoUsuario", columnDefinition="mediumblob")
    private byte[] fotoUsuario;
    
    public FotoUsuario(){
	
    }
    
    public FotoUsuario(byte[] foto){
	this.fotoUsuario = foto;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the fotoUsuario
     */
    public byte[] getFotoUsuario() {
        return fotoUsuario;
    }

    /**
     * @param fotoUsuario the fotoUsuario to set
     */
    public void setFotoUsuario(byte[] fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }
    
}
