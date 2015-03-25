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
 * @author pablo
 *
 */
@Entity
@Table(name = "calificacion", catalog = "tesis_playon")
public class Calificacion implements Serializable{
    
 private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "calificacionID")
    private Integer id;

    @Column(name = "calificacion")
    private Integer calificacion;
    
    @ManyToOne
    @JoinColumn(name = "playaID")
    private Playa playa;
    
    @ManyToOne
    @JoinColumn(name = "clienteID")
    private Cliente cliente;

    public Calificacion() {
    }

    public Calificacion(Integer calificacion, Playa playa, Cliente cliente) {
	super();
	this.calificacion = calificacion;
	this.playa = playa;
	this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
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

    @Override
    public String toString() {
	return "Calificacion [id=" + id + ", calificacion=" + calificacion + ", playa=" + playa + ", cliente="
		+ cliente + "]";
    }
}
