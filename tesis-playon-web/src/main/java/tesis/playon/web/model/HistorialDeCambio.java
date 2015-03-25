package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author alejandro
 * @date 09/07/2012
 */
@Entity
@Table(name = "historial_de_cambio", catalog = "tesis_playon")
public class HistorialDeCambio implements Serializable {

    private static final long serialVersionUID = 740960507447874471L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "historialDeCambioID")
    private Integer id;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "fecha")
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "denunciaPlayaID")
    private DenunciaPlaya denunciaPlaya;

    @ManyToOne
    @JoinColumn(name = "denunciaVehiculoID")
    private DenunciaVehiculo denunciaVehiculoID;

    public HistorialDeCambio() {
	super();
    }

    public HistorialDeCambio(String comentario, Date fecha, DenunciaPlaya denunciaPlaya,
	    DenunciaVehiculo denunciaVehiculoID) {
	super();
	this.comentario = comentario;
	this.fecha = fecha;
	this.denunciaPlaya = denunciaPlaya;
	this.denunciaVehiculoID = denunciaVehiculoID;
    }

    public String getComentario() {
	return comentario;
    }

    public void setComentario(String comentario) {
	this.comentario = comentario;
    }

    public DenunciaPlaya getDenunciaPlaya() {
	return denunciaPlaya;
    }

    public void setDenunciaPlaya(DenunciaPlaya denunciaPlaya) {
	this.denunciaPlaya = denunciaPlaya;
    }

    public DenunciaVehiculo getDenunciaVehiculoID() {
	return denunciaVehiculoID;
    }

    public void setDenunciaVehiculoID(DenunciaVehiculo denunciaVehiculoID) {
	this.denunciaVehiculoID = denunciaVehiculoID;
    }

    public Integer getId() {
	return id;
    }

    public Date getFecha() {
	return fecha;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	HistorialDeCambio otroHistorial = (HistorialDeCambio) object;
	if (id != otroHistorial.id)
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "Historial de cambio:\t [hisotorialDeCambioID=" + id + ", comentario=" + comentario + ", fecha="
		+ fecha.toString() + "]";
    }
}
