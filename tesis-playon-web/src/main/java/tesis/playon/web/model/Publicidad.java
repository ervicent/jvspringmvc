package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Clase que contiene las publicidades.
 * 
 * @author alejandro
 * @date 07/07/2012
 */
@Entity
@Table(name = "publicidad", catalog = "tesis_playon")
public class Publicidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "publicidadID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "estadoPublicidadID")
    private EstadoPublicidad estado;

    @Column(name = "nombreEmpresa")
    private String nombreEmpresa;
    
    @Column(name = "nombre")
    private String nombreResponsable;
    
    @Column(name = "apellido")
    private String apellidoResponsable;
    
    @Column(name = "email")
    private String emailRespondable;
    
    @Column(name = "telefono")
    private String telefonoResponsable;
    
    @Column(name = "fechaDesde")
    private Date fechaDesde;
    
    @Column(name = "fechaHasta")
    private Date fechaHasta;
    
    @OneToOne
    @JoinColumn(name = "fotoPublicidadID")
    private FotoPublicidad fotoPublicidad;
    
    @Column(name = "precio")
    private Float precio;
    
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
     * @return the estado
     */
    public EstadoPublicidad getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(EstadoPublicidad estado) {
        this.estado = estado;
    }

    /**
     * @return the nombreEmpresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * @param nombreEmpresa the nombreEmpresa to set
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    /**
     * @return the nombreResponsable
     */
    public String getNombreResponsable() {
        return nombreResponsable;
    }

    /**
     * @param nombreResponsable the nombreResponsable to set
     */
    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    /**
     * @return the apellidoResponsable
     */
    public String getApellidoResponsable() {
        return apellidoResponsable;
    }

    /**
     * @param apellidoResponsable the apellidoResponsable to set
     */
    public void setApellidoResponsable(String apellidoResponsable) {
        this.apellidoResponsable = apellidoResponsable;
    }

    /**
     * @return the emailRespondable
     */
    public String getEmailRespondable() {
        return emailRespondable;
    }

    /**
     * @param emailRespondable the emailRespondable to set
     */
    public void setEmailRespondable(String emailRespondable) {
        this.emailRespondable = emailRespondable;
    }

    /**
     * @return the telefonoResponsable
     */
    public String getTelefonoResponsable() {
        return telefonoResponsable;
    }

    /**
     * @param telefonoResponsable the telefonoResponsable to set
     */
    public void setTelefonoResponsable(String telefonoResponsable) {
        this.telefonoResponsable = telefonoResponsable;
    }

    /**
     * @return the fechaDesde
     */
    public Date getFechaDesde() {
        return fechaDesde;
    }

    /**
     * @param fechaDesde the fechaDesde to set
     */
    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
     * @return the fechaHasta
     */
    public Date getFechaHasta() {
        return fechaHasta;
    }

    /**
     * @param fechaHasta the fechaHasta to set
     */
    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    /**
     * @return the foto
     */
    public FotoPublicidad getFotoPublicidad() {
        return fotoPublicidad;
    }

    /**
     * @param foto the foto to set
     */
    public void setFotoPublicidad(FotoPublicidad foto) {
        this.fotoPublicidad = foto;
    }

    /**
     * @return the precio
     */
    public Float getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    /**
     * Constructor por defecto.
     */
    public Publicidad() {
	super();
    }
    
    /**
     * @param nombreEmpresa
     * @param nombreResponsable
     * @param apellidoResponsable
     * @param fechaDesde
     * @param fechaHasta
     */
    public Publicidad(String nombreEmpresa, String nombreResponsable, String apellidoResponsable, Date fechaDesde,
	    Date fechaHasta) {
	super();
	this.nombreEmpresa = nombreEmpresa;
	this.nombreResponsable = nombreResponsable;
	this.apellidoResponsable = apellidoResponsable;
	this.fechaDesde = fechaDesde;
	this.fechaHasta = fechaHasta;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Publicidad [id=" + id + ", estado=" + estado + ", nombreEmpresa=" + nombreEmpresa
		+ ", nombreResponsable=" + nombreResponsable + ", apellidoResponsable=" + apellidoResponsable
		+ ", emailRespondable=" + emailRespondable + ", telefonoResponsable=" + telefonoResponsable
		+ ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + ", precio=" + precio + "]";
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	Publicidad otroPublicidad = (Publicidad) object;
	if (id != otroPublicidad.id)
	    return false;

	return true;
    }
}