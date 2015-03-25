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

import org.hibernate.annotations.Type;

/**
 * Clase de negocio que contiene los usuarios.
 * 
 * @author garribere
 * @date 03/07/2012
 * 
 */
@Entity
@Table(name = "usuario", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "usuario") })
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "usuarioID")
    private Integer id;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column(name = "nroDoc", unique = true)
    private int nroDoc;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "tipoDocID")
    private TipoDoc tipoDoc;

    @ManyToOne
    @JoinColumn(name = "playaID")
    private Playa playa;

    @Column(name = "usuario", unique = true)
    private String nombreUser;

    @Column(name = "enable", columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean enable;
    
    @ManyToOne
    @JoinColumn(name = "fotoUsuarioID")
    private FotoUsuario fotoUsuario;
    
    public Usuario() {
	super();
    }

    public Usuario(String nombre, String apellido, String nombreUser, String password, String email, TipoDoc tipoDoc,
	    int nroDoc, Playa playa) {
	super();
	this.apellido = apellido;
	this.nombre = nombre;
	this.email = email;
	this.nroDoc = nroDoc;
	this.password = password;
	this.tipoDoc = tipoDoc;
	this.playa = playa;
	this.nombreUser = nombreUser;
    }

    public void setID(int id) {
	this.id = id;

    }

    public String getApellido() {
	return apellido;
    }

    public void setApellido(String apellido) {
	this.apellido = apellido;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public int getNroDoc() {
	return nroDoc;
    }

    public void setNroDoc(int nroDoc) {
	this.nroDoc = nroDoc;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public TipoDoc getTipoDoc() {
	return tipoDoc;
    }

    public void setTipoDoc(TipoDoc tipoDoc) {
	this.tipoDoc = tipoDoc;
    }

    public String getNombreUser() {
	return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
	this.nombreUser = nombreUser;
    }

    public Integer getId() {
	return id;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public Boolean getEnable() {
	return enable;
    }

    public void setEnable(Boolean enable) {
	this.enable = enable;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the fotoUsuario
     */
    public FotoUsuario getFotoUsuario() {
        return fotoUsuario;
    }

    /**
     * @param fotoUsuario the fotoUsuario to set
     */
    public void setFotoUsuario(FotoUsuario fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	Usuario otroUsuario = (Usuario) object;
	if (id != otroUsuario.id)
	    return false;
	if (nombreUser == null ? otroUsuario.nombreUser != null : !nombreUser.equals(otroUsuario.nombreUser))
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "Usuario:\t [usuarioID= " + id + ", nombre= " + nombre + ", apellido=" + apellido + ", " + tipoDoc
		+ ", Nro Documento= " + nroDoc + ", user= " + nombreUser + "]";
    }
}