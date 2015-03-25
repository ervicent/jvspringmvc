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
 * Clase de negocio que contiene los comentarios sobre una playa
 * 
 * @author gmorales
 * 
 */
@Entity
@Table(name = "perfil_playa", catalog = "tesis_playon")
public class PerfilPlaya implements Serializable {

    private static final long serialVersionUID = 2382561437443895633L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "perfilPlayaID")
    private Integer id;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "playaID")
    private Playa playa;
    
    @Lob
    @Column(name = "fotoPerfil", columnDefinition="mediumblob")
    private byte[] fotoPerfil;
    
    @Column(name = "nombreFoto")
    private String nombreFoto;

    public PerfilPlaya() {
    }

    public PerfilPlaya( String descripcion, Playa playa) {
	this.descripcion = descripcion;
	this.playa = playa;
    }
    
    public PerfilPlaya(byte[] fotoPerfil) {
	this.fotoPerfil = fotoPerfil;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getNombreFoto() {
        return nombreFoto;
    }

    public void setNombreFoto(String nombreFoto) {
        this.nombreFoto = nombreFoto;
    }

    @Override
    public String toString() {
	return "PerfilPlaya:\t [perfilPlayaID=" + id + ", descripcion=" + descripcion
		+ "]";
    }

}
