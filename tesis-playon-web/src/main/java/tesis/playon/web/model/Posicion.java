package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase de negocio que contiene la posicion de una publicidad
 * 
 * @author gmorales
 * 
 */
@Entity
@Table(name = "posicion", catalog = "tesis_playon")
public class Posicion implements Serializable {

    private static final long serialVersionUID = 2382561437443895633L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "posicionID")
    private Integer id;

    @Column(name = "tamanioKBMax")
    private Integer tamanioKBMax;

    @Column(name = "tamanioX")
    private Integer tamanioX;

    @Column(name = "tamanioY")
    private Integer tamanioY;

    @Column(name = "ubicacion")
    private String ubicacion;

    public Posicion() {
    }

    public Posicion(Integer tamanioKBMax, Integer tamanioX, Integer tamanioY) {
	this.tamanioKBMax = tamanioKBMax;
	this.tamanioX = tamanioX;
	this.tamanioY = tamanioY;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getTamanioKBMax() {
	return tamanioKBMax;
    }

    public void setTamanioKBMax(Integer tamanioKBMax) {
	this.tamanioKBMax = tamanioKBMax;
    }

    public Integer getTamanioX() {
	return tamanioX;
    }

    public void setTamanioX(Integer tamanioX) {
	this.tamanioX = tamanioX;
    }

    public Integer getTamanioY() {
	return tamanioY;
    }

    public void setTamanioY(Integer tamanioY) {
	this.tamanioY = tamanioY;
    }

    public String getUbicacion() {
	return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
	this.ubicacion = ubicacion;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	Posicion otroPosicion = (Posicion) object;
	if (id != otroPosicion.id)
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "Posicion:\t [posicionID=" + id + ", tamanioKBMax=" + tamanioKBMax + ", tamanioX=" + tamanioX
		+ ", tamanioY=" + tamanioY + "]";
    }

}
