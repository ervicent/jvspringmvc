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
 * 
 * @author alejandro
 * @date 10/07/2012
 */
@Entity
@Table(name = "transaccion_playa", catalog = "tesis_playon")
public class TransaccionPlaya implements Serializable {

    private static final long serialVersionUID = 1342434976138010777L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "transaccionPlayaID")
    private Integer id;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "importe")
    private float importe;

    @ManyToOne
    @JoinColumn(name = "tipoPagoID", nullable = false)
    private TipoPago tipoPago;

    @ManyToOne
    @JoinColumn(name = "cuentaPlayaID", nullable = false)
    private CuentaPlaya cuentaPlaya;

    @ManyToOne
    @JoinColumn(name = "liquidacionID", nullable = false)
    private Liquidacion liquidacion;

    @ManyToOne
    @JoinColumn(name = "detalleEstadiaID", nullable = false)
    private DetalleEstadia detalleEstadia;

    public TransaccionPlaya() {
	super();
    }

    public TransaccionPlaya(Date fecha, float importe, TipoPago tipoPago, CuentaPlaya cuentaPlaya,
	    Liquidacion liquidacion, DetalleEstadia detalleEstadia) {
	super();
	this.fecha = fecha;
	this.importe = importe;
	this.tipoPago = tipoPago;
	this.cuentaPlaya = cuentaPlaya;
	this.liquidacion = liquidacion;
	this.detalleEstadia = detalleEstadia;
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

    public CuentaPlaya getCuentaPlaya() {
	return cuentaPlaya;
    }

    public void setCuentaPlaya(CuentaPlaya cuentaPlaya) {
	this.cuentaPlaya = cuentaPlaya;
    }

    public Liquidacion getLiquidacion() {
	return liquidacion;
    }

    public void setLiquidacion(Liquidacion liquidacion) {
	this.liquidacion = liquidacion;
    }

    public DetalleEstadia getDetalleEstadia() {
	return detalleEstadia;
    }

    public void setDetalleEstadia(DetalleEstadia detalleEstadia) {
	this.detalleEstadia = detalleEstadia;
    }

    public Integer getId() {
	return id;
    }

    @Override
    public String toString() {
	return "TransaccionPlaya:\t [transaccionPlayaID= " + id + ", fecha= " + fecha.toString() + ", importe= " + importe
		+ ", tipoPago" + tipoPago.toString() + ", " + cuentaPlaya.toString() + "]";
    }
}
