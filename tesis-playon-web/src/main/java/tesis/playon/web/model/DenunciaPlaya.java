/**
 * 
 */
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
import javax.persistence.Table;

/**
 * Clase de negocio que contiene denuncias de los clientes a las playas
 * 
 * @author garribere
 * 
 */

@Entity
@Table(name = "denuncia_playa", catalog = "tesis_playon")
public class DenunciaPlaya implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "denunciaPlayaID")
    private Integer id;

    @Column(name = "asunto")
    private String asunto;

    @Column(name = "fechaAlta")
    private Date fechaAlta;

    @ManyToOne
    @JoinColumn(name = "clienteID")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "playaID")
    private Playa playa;
    
    @ManyToOne
    @JoinColumn(name = "estadoID")
    private EstadoDenuncia estado;

    public DenunciaPlaya() {
    }

    public DenunciaPlaya(String asunto, Date fechaAlta, Playa playa, Cliente cliente) {
	this.asunto = asunto;
	this.fechaAlta = fechaAlta;
	this.playa = playa;
	this.cliente = cliente;
    }

    public String getAsunto() {
	return asunto;
    }

    public void setAsunto(String asunto) {
	this.asunto = asunto;
    }

    public Date getFechaAlta() {
	return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
	this.fechaAlta = fechaAlta;
    }
    
    

    public EstadoDenuncia getEstado() {
        return estado;
    }

    public void setEstado(EstadoDenuncia estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	DenunciaPlaya otroDenuncia = (DenunciaPlaya) object;
	if (id != otroDenuncia.id)
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "DenunciaPlaya:\t [denunciaPlayaID= " + id + ", asunto= " + asunto + ", fechaAlta= "
		+ fechaAlta.toString() + ", Cliente= " + cliente.getNroCliente() + ", " + playa + "]";
    }

}
