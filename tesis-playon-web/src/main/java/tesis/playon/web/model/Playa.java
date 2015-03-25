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

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

import tesis.playon.web.util.LatitudlongitudUtil;
import tesis.playon.web.util.LatitudlongitudUtil.GeoposicionDePlaya;

/**
 * @author Alejandro
 * @date 08/07/2012
 */
@Entity
@Table(name = "playa", catalog = "tesis_playon")
@NamedNativeQueries({ @NamedNativeQuery(name = "callPlayasSP", query = "CALL tesis_playon.busquedaAvanzada(:platitud,:plongitud, :pTipoEstadia, :pCategoriaVehiculo,:pNombreComercial,:pPromociones)", resultClass = Playa.class) })
public class Playa implements Serializable {

    private static final long serialVersionUID = -5969681339752247843L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "playaID")
    private Integer id;

    @Column(name = "cuit", nullable = true)
    private String cuit;

    @Column(name = "disponibilidad", nullable = true)
    private Integer disponibilidad;

    @Column(name = "longitud", nullable = true)
    private Double longitud;

    @Column(name = "latitud", nullable = true)
    private Double latitud;

    @Column(name = "domicilio", nullable = true)
    private String domicilio;

    @Column(name = "nombreComercial")
    private String nombreComercial;

    @Column(name = "razonSocial", nullable = true)
    private String razonSocial;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;
    
    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "barrioID", nullable = true)
    private Barrio barrio;

    @ManyToOne
    @JoinColumn(name = "estadoPlayaID")
    private EstadoPlaya estado;

    public Playa() {
	super();

    }

    public Playa(String cuit, Integer disponibilidad, String domicilio, String nombreComercial, String razonSocial,
	    Barrio barrio, EstadoPlaya estado, String telefono, String email) {
	this.cuit = cuit;
	this.disponibilidad = disponibilidad;
	this.domicilio = domicilio;
	this.nombreComercial = nombreComercial;
	this.razonSocial = razonSocial;
	this.barrio = barrio;
	this.estado = estado;
	this.telefono = telefono;
	this.email = email;
    }

    public Playa(String nombreComercial, EstadoPlaya estado) {
	this.nombreComercial = nombreComercial;
	this.estado = estado;
    }

    public String getCuit() {
	return cuit;
    }

    public void setCuit(String cuit) {
	this.cuit = cuit;
    }

    public Double getLongitud() {
	return longitud;
    }

    public void setLongitud(Double longitud) {
	this.longitud = longitud;
    }

    public Double getLatitud() {
	return latitud;
    }

    public void setLatitud(Double latitud) {
	this.latitud = latitud;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getDisponibilidad() {
	return disponibilidad;
    }

    public void setDisponibilidad(Integer disponibilidad) {
	this.disponibilidad = disponibilidad;
    }

    public String getDomicilio() {
	return domicilio;
    }

    public void setDomicilio(String domicilio) {
	this.domicilio = domicilio;
	LatitudlongitudUtil latLonUtil;
	GeoposicionDePlaya respuesta;

	latLonUtil = new LatitudlongitudUtil();
	try {
	    respuesta = latLonUtil.getLocationFromAddress(domicilio + ", Córdoba, CBA, Argentina");
	    this.latitud = respuesta.getLatitud();
	    this.longitud = respuesta.getLongitud();
	} catch (Exception e) {

	    e.printStackTrace();
	}

    }

    public String getNombreComercial() {
	return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
	this.nombreComercial = nombreComercial;
    }

    public String getRazonSocial() {
	return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
	this.razonSocial = razonSocial;
    }

    public Barrio getBarrio() {
	return barrio;
    }

    public void setBarrio(Barrio barrio) {
	this.barrio = barrio;
    }

    public EstadoPlaya getEstado() {
	return estado;
    }

    public void setEstado(EstadoPlaya estado) {
	this.estado = estado;
    }

    public String getTelefono() {
	return telefono;
    }

    public void setTelefono(String telefono) {
	this.telefono = telefono;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Integer getId() {
	return id;
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

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	Playa otroPlaya = (Playa) object;
	if (id != otroPlaya.id)
	    return false;
	if (nombreComercial == null ? otroPlaya.nombreComercial != null : !nombreComercial
		.equals(otroPlaya.nombreComercial))
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "Playa:\t [playaID=" + id + ", cuit=" + cuit + ", nombreComercial=" + nombreComercial + ", razonSocial="
		+ razonSocial + ", domicilio=" + domicilio + "]";
    }

    public String toString2() {

	return "Nombre: " + nombreComercial + "\nDomicilio: " + domicilio + "\nLugares disponibles: " + disponibilidad;

    }

    public void setId(int id) {
	this.id = id;

    }

    public double getDistanceFrom(double latitud, double longitud) {

	double earthRadius = 6371;

	double dLat = Math.toRadians(this.latitud - latitud);

	double dLng = Math.toRadians(this.longitud - longitud);

	double sindLat = Math.sin(dLat / 2);

	double sindLng = Math.sin(dLng / 2);

	double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) * Math.cos(this.latitud) * Math.cos(latitud);

	double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	double dist = earthRadius * c;

	return dist * 10;

    }

}
