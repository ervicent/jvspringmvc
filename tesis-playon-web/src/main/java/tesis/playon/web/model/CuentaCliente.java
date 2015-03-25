package tesis.playon.web.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Clase de negocio que contiene los diferentes Cuentas de los cliente.
 * @author alejandro
 * @date 08/07/2012
 */
@Entity
@Table(name = "cuenta_cliente", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nroCuenta") })
public class CuentaCliente implements Serializable{

    private static final long serialVersionUID = -5062229388596781952L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="cuentaClienteID")
    private Integer id;
    
    @Column(name="nroCuenta")
    private Integer nroCuenta;
    
    @Column(name="saldo")
    private float saldo;
    
    @Column(name="fechaCreacion")
    private Timestamp fechaCreacion;
    
    @OneToOne
    @JoinColumn(name="clienteID")
    private Cliente cliente;

    public CuentaCliente() {
	super();
	this.fechaCreacion = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    public CuentaCliente(Cliente cliente) {
	super();
	this.saldo = 0.0f;
	this.fechaCreacion = new Timestamp(Calendar.getInstance().getTimeInMillis());
	this.cliente = cliente;
    }

    public Integer getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(Integer nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }
    
    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	CuentaCliente otroCuenta = (CuentaCliente) object;
	if (id != otroCuenta.id)
	    return false;
	if (nroCuenta == null ? otroCuenta.nroCuenta != null : !nroCuenta.equals(otroCuenta.nroCuenta))
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "Cuenta Cliente:\t [cuentaClienteID= " + id + ", nroCuenta= " + nroCuenta + "]";
    }
}
