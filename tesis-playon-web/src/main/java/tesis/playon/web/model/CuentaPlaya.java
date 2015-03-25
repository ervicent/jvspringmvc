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
 * Clase de negocio que contiene los diferentes Cuentas de las playas.
 * 
 * @author garribere
 * @date 03/07/2012
 * 
 */
@Entity
@Table(name = "cuenta_playa", catalog = "tesis_playon", uniqueConstraints = { @UniqueConstraint(columnNames = "nroCuenta") })
public class CuentaPlaya implements Serializable {

    private static final long serialVersionUID = -7370950550724631370L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cuentaPlayaID")
    private Integer id;

    @Column(name = "nroCuenta")
    private Integer nroCuenta;

    @Column(name = "fechaCreacion")
    private Timestamp fechaCreacion;

    @Column(name = "saldo")
    private float saldo;

    @OneToOne
    @JoinColumn(name = "playaID")
    private Playa playa;

    public CuentaPlaya() {
	super();
	this.fechaCreacion = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    public CuentaPlaya(Playa playa) {
	super();
	this.fechaCreacion = new Timestamp(Calendar.getInstance().getTimeInMillis());
	this.saldo = 0.0f;
	this.playa = playa;
    }

    public int getNroCuenta() {
	return nroCuenta;
    }

    public void setNroCuenta(int nroCuenta) {
	this.nroCuenta = nroCuenta;
    }

    public Timestamp getFechaCreacion() {
	return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
	this.fechaCreacion = fechaCreacion;
    }

    public float getSaldo() {
	return saldo;
    }

    public void setSaldo(float saldo) {
	this.saldo = saldo;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public Integer getId() {
	return id;
    }

    public boolean equals(Object object) {
	if (object == this)
	    return true;
	if (object == null || getClass() != object.getClass())
	    return false;

	CuentaPlaya otroCuenta = (CuentaPlaya) object;
	if (id != otroCuenta.id)
	    return false;
	if (nroCuenta == null ? otroCuenta.nroCuenta != null : !nroCuenta.equals(otroCuenta.nroCuenta))
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "Cuenta Playa:\t [cuentaPlayaID= " + id + ", nroCuenta= " + nroCuenta + "]";
    }

}