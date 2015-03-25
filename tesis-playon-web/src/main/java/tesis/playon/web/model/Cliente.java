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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Alejandro
 * @date 06/07/2012
 */

@Entity
@Table(name = "cliente", catalog = "tesis_playon")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 2382561437443895633L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "clienteID")
    private Integer id;

    @Column(name = "nroCliente")
    private Integer nroCliente;

    @Column(name = "domicilio")
    private String domicilio;

    @Column(name = "telefono")
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "barrioID", nullable = true)
    private Barrio barrio;

    @OneToOne
    @JoinColumn(name = "cuentaClienteID")
    private CuentaCliente cuentaCliente;

    @OneToOne
    @JoinColumn(name = "usuarioID")
    private Usuario usuario;
    
    public Cliente() {
	this.nroCliente= (int) (Math.random() * 1000) + 1;
    }

    public Cliente(String telefono, String domicilio) {
	this.nroCliente = (int) (Math.random() * 1000) + 1;
	this.telefono = telefono;
	this.domicilio = domicilio;

    }

    public Cliente(String domicilio, String telefono, Barrio barrio, CuentaCliente cuentaCliente,
	    Usuario usuario) {
	this.nroCliente = (int) (Math.random() * 1000) + 1;
	this.domicilio = domicilio;
	this.telefono = telefono;
	this.barrio = barrio;
	this.cuentaCliente = cuentaCliente;
	this.usuario = usuario;
    }

    public Integer getId() {
	return id;
    }

    public Integer getNroCliente() {
	return nroCliente;
    }

    public void setNroCliente(Integer nroCliente) {
	this.nroCliente = nroCliente;
    }

    public String getDomicilio() {
	return domicilio;
    }

    public void setDomicilio(String domicilio) {
	this.domicilio = domicilio;
    }

    public String getTelefono() {
	return telefono;
    }

    public void setTelefono(String telefono) {
	this.telefono = telefono;
    }

    public Barrio getBarrio() {
	return barrio;
    }

    public void setBarrio(Barrio barrio) {
	this.barrio = barrio;
    }

    public CuentaCliente getCuentaCliente() {
	return cuentaCliente;
    }

    public void setCuentaCliente(CuentaCliente cuentaCliente) {
	this.cuentaCliente = cuentaCliente;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	Cliente otroCliente = (Cliente) object;
	if (id != otroCliente.id)
	    return false;
	if (nroCliente == null ? otroCliente.nroCliente != null : !nroCliente.equals(otroCliente.nroCliente))
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "Cliente:\t [clienteID= " + id + ", nroCliente= " + nroCliente + ", Usuario= " + usuario.getNombreUser()
		+ ", Cuenta Cliente= " + cuentaCliente + "]";
    }
}
