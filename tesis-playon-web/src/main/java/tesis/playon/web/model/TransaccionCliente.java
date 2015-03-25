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
 * @author Pablo
 * 
 */
@Entity
@Table(name = "transaccion_cliente", catalog = "tesis_playon")
public class TransaccionCliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "transaccionClienteID")
    private Integer id;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "importe")
    private float importe;

    @ManyToOne
    @JoinColumn(name = "tipoPagoID", nullable = false)
    private TipoPago tipoPago;

    @ManyToOne
    @JoinColumn(name = "cuentaClienteID", nullable = false)
    private CuentaCliente cuentaCliente;

    public TransaccionCliente() {
    }

    public TransaccionCliente(Date fecha, float importe, TipoPago tipoPago, CuentaCliente cuentaCliente) {
	this.fecha = fecha;
	this.importe = importe;
	this.tipoPago = tipoPago;
	this.cuentaCliente = cuentaCliente;
    }

    public Date getFecha() {
	return fecha;
    }

    public void setFecha(Date fecha) {
	this.fecha = fecha;
    }

    public float getImporte() {
	return importe;
    }

    public void setImporte(float importe) {
	this.importe = importe;
    }

    public TipoPago getTipoPago() {
	return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
	this.tipoPago = tipoPago;
    }

    public CuentaCliente getCuentaCliente() {
	return cuentaCliente;
    }

    public void setCuentaCliente(CuentaCliente cuentaCliente) {
	this.cuentaCliente = cuentaCliente;
    }

    public Integer getId() {
	return id;
    }

    @Override
    public String toString() {
	return "TransaccionCliente:\t [transaccionClienteID= " + id + ", fecha= " + fecha.toString() + ", importe= " + importe + ", "
		+ tipoPago + ", " + cuentaCliente + "]";
    }
}
