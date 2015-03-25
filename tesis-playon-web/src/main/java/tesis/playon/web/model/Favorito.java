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

/**
 * @author Pablo
 * 
 */
@Entity
@Table(name = "favorito", catalog = "tesis_playon")
public class Favorito implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "favoritoID")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "playaID")
    private Playa playa;

    @ManyToOne
    @JoinColumn(name = "clienteID")
    private Cliente cliente;

    public Favorito(Playa playa, Cliente cliente) {
	this.playa = playa;
	this.cliente = cliente;
    }

    public Favorito() {
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public Cliente getCliente() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	Favorito otroFavorito = (Favorito) object;

	if (playa.equals(otroFavorito.getPlaya()) && cliente.equals(otroFavorito.getCliente()))
	    return true;
	return false;
    }

    @Override
    public String toString() {
	return "Favorito:\t [nombre playa= " + playa.getNombreComercial() + ", nroCliente= "
		+ cliente.getNroCliente() + "]";
    }
}