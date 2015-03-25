package tesis.playon.web.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Almacena las sesiones de conexión de los usuarios Web.
 * 
 * @author alejandro
 * @date 07/07/2012
 */
@Entity
@Table(name = "sesion", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "idSesion")})
public class Sesion implements Serializable {

    private static final long serialVersionUID = 3859234299526922177L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "comentarioID")
    private Integer id;

    @Column(name = "fechaFin")
    private Date fechaFin;

    @Column(name = "fechaInicio")
    private Date fechaInicio;

    @Column(name = "idSesion")
    private String idSesion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioID")
    private Usuario usuario;

    /**
     * 
     * @param fechaInicio Fecha de Inicio de vigencia de la sesión.
     * @param fechaFin Fecha de Fin de Vigencia de la sesión.
     * @param idSesion ID de la sesión del explorador (no de la DB).
     * @param usuario El usuario de la sesión.
     */
    public Sesion(Date fechaInicio, Date fechaFin, String idSesion, Usuario usuario) {
	super();
	this.fechaFin = fechaFin;
	this.fechaInicio = fechaInicio;
	this.idSesion = idSesion;
	this.usuario = usuario;
    }
    /**
     * Devuelve la fecha de fin de vigencia de la sesión. 
     * @return La fecha de fin de vigencia de la sesión.
     */
    public Date getFechaFin() {
	return fechaFin;
    }
    
    /**
     * Setea la fecha de fin de vigencia de la sesión.
     * @param fechaFin La fecha de fin de vigencia de la sesión. 
     */
    public void setFechaFin(Date fechaFin) {
	this.fechaFin = fechaFin;
    }

    /**
     * Devuelve la fecha de inicio de vigencia de la sesión.
     * @return La fecha de inicio de vigencia de la sesión.
     */
    public Date getFechaInicio() {
	return fechaInicio;
    }

    /**
     * Setea la fecha de Inicio de vigencia de la sesión.
     * @param fechaInicio La fecha de Inicio de vigencia de la sesión.
     */
    public void setFechaInicio(Date fechaInicio) {
	this.fechaInicio = fechaInicio;
    }

    /**
     * Devuelve el ID de la sesión del explorador (no de la DB).
     * @return El ID de la sesión del explorador.
     */
    public String getIdSesion() {
	return idSesion;
    }

    /**
     * Setea el ID de la sesión del explorador (no de la DB).
     * @param idSesion El ID de la sesión del explorador (no de la DB).
     */
    public void setIdSesion(String idSesion) {
	this.idSesion = idSesion;
    }

    /**
     * Devuelve el usuario de la sesión.
     * @return El usuario de la sesión.
     */
    public Usuario getUsuario() {
	return usuario;
    }

    /**
     * Setea el usuario de la sesión.
     * @param usuario El usuario de la sesión.
     */
    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    /**
     * Devuelve el id en la Base de Datos de la sesión. 
     * @return El id en la Base de Datos de la sesión.
     */
    public Integer getId() {
	return id;
    }

    @Override
    public String toString() {
	return "Sesion:\t [sesionID=" + id + ", fechaInicio: =" + fechaInicio.toString() + ", fechaFin: = "
		+ fechaFin.toString() + ", idSesion=" + idSesion + "]";
    }
}
