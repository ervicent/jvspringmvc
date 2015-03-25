/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.CuentaCliente;
import tesis.playon.web.model.TipoPago;
import tesis.playon.web.model.TransaccionCliente;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IClienteService;
import tesis.playon.web.service.ICuentaClienteService;
import tesis.playon.web.service.ITipoPagoService;
import tesis.playon.web.service.ITransaccionClienteService;
import tesis.playon.web.service.IUsuarioService;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "cuentaClienteMB")
@ViewScoped
public class CuentaClienteManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{ClienteService}")
    IClienteService clienteService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{CuentaClienteService}")
    ICuentaClienteService cuentaClienteService;

    @ManagedProperty(value = "#{TipoPagoService}")
    ITipoPagoService tipoPagoService;

    @ManagedProperty(value = "#{TransaccionClienteService}")
    ITransaccionClienteService transaccionClienteService;

    private static Cliente cliente;

    private double importe;

    private CuentaCliente cuentaCliente;

    private TransaccionCliente transaccionCliente;

    private static Float saldo = 0.0f;

    private static List<TransaccionCliente> transacciones;

    private TipoPago tipoPago;

    private Date fechaDesde;

    private Date fechaHasta;

    private static List<CuentaCliente> cuentaClienteList;

    private List<CuentaCliente> filteredCuentaClientes;

    private static CuentaCliente cuentaSelected;

    @PostConstruct
    private void init() {
	this.fechaDesde = new Date();
	this.fechaHasta = new Date();
	this.fechaDesde = DateUtils.setDays(this.fechaDesde, 1);
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario usuario = getUsuarioService().findByNombreUsuario(userName);
	if (usuario != null) {
	    cliente = getClienteService().findByUsuario(usuario);
	    cuentaCliente = cliente != null ? cliente.getCuentaCliente() : null;
	}
	cuentaClienteList = getCuentaClienteService().findAll();
    }

    public void cargarSaldo() {
	try {
	    float saldoCliente = cliente.getCuentaCliente().getSaldo();
	    cliente.getCuentaCliente().setSaldo(saldoCliente + saldo);
	    getCuentaClienteService().update(cliente.getCuentaCliente());

	    transaccionCliente = new TransaccionCliente();
	    Timestamp fecha = new Timestamp(Calendar.getInstance().getTimeInMillis());
	    transaccionCliente.setFecha(fecha);
	    transaccionCliente.setImporte(getSaldo());
	    transaccionCliente.setCuentaCliente(cliente.getCuentaCliente());
	    TipoPago tipoPago = getTipoPagoService().findByNombreTipoPago("DineroMail");
	    transaccionCliente.setTipoPago(tipoPago);
	    getTransaccionClienteService().save(transaccionCliente);

	    FacesContext.getCurrentInstance().addMessage(
		    null,
		    new FacesMessage(FacesMessage.SEVERITY_INFO, "Transacción exitosa. Se agregó a su cuenta $"
			    + getSaldo() + " ARG. ¡Muchas Gracias!", ""));

	} catch (DataAccessException e) {
	    e.printStackTrace();
	}
    }

    public List<TransaccionCliente> getTransacciones() {
	Date fechaDesde = (this.fechaDesde != null ? this.fechaDesde : new Date(01012012));
	Date fechaHasta = (this.fechaHasta != null ? this.fechaHasta : Calendar.getInstance().getTime());
	transacciones = getTransaccionClienteService().findTransaccionesByFecha(cliente.getCuentaCliente(), fechaDesde,
		fechaHasta);
	return transacciones;
    }

    public void setTransacciones(List<TransaccionCliente> transacciones) {
	CuentaClienteManagedBean.transacciones = transacciones;
    }

    public IClienteService getClienteService() {
	return clienteService;
    }

    public void setClienteService(IClienteService clienteService) {
	this.clienteService = clienteService;
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public ICuentaClienteService getCuentaClienteService() {
	return cuentaClienteService;
    }

    public void setCuentaClienteService(ICuentaClienteService cuentaClienteService) {
	this.cuentaClienteService = cuentaClienteService;
    }

    public ITransaccionClienteService getTransaccionClienteService() {
	return transaccionClienteService;
    }

    public void setTransaccionClienteService(ITransaccionClienteService transaccionClienteService) {
	this.transaccionClienteService = transaccionClienteService;
    }

    public Cliente getCliente() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	CuentaClienteManagedBean.cliente = cliente;
    }

    public TipoPago getTipoPago() {
	return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
	this.tipoPago = tipoPago;
    }

    public Float getSaldo() {
	return saldo;
    }

    public void setSaldo(Float saldo) {
	CuentaClienteManagedBean.saldo = saldo;
    }

    public double getImporte() {
	return importe;
    }

    public void setImporte(double importe) {
	this.importe = importe;
    }

    public ITipoPagoService getTipoPagoService() {
	return tipoPagoService;
    }

    public void setTipoPagoService(ITipoPagoService tipoPagoService) {
	this.tipoPagoService = tipoPagoService;
    }

    public CuentaCliente getCuentaCliente() {
	return cuentaCliente;
    }

    public void setCuentaCliente(CuentaCliente cuentaCliente) {
	this.cuentaCliente = cuentaCliente;
    }

    public TransaccionCliente getTransaccionCliente() {
	return transaccionCliente;
    }

    public void setTransaccionCliente(TransaccionCliente transaccionCliente) {
	this.transaccionCliente = transaccionCliente;
    }

    public Date getFechaDesde() {
	return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
	this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
	return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
	this.fechaHasta = fechaHasta;
    }

    public void updateListado() {
	this.fechaDesde = null;
	this.fechaHasta = null;
    }

    public void listadoTransaccionesPDF(Object document) throws IOException, BadElementException, DocumentException {

	Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 18, Font.BOLD, Color.BLACK);

	Paragraph titulo = new Paragraph();
	titulo.add(new Paragraph("Listado de Transacciones entre el  " + rangoFechas(), fuenteNegra18));
	agregarLineasEnBlanco(titulo, 2);
	titulo.setAlignment(Element.ALIGN_CENTER);
	String sep = File.separator;
	Document pdf = (Document) document;
	pdf.open();
	pdf.setPageSize(PageSize.A4);
	ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
	String logo = extContext.getRealPath("resources" + sep + "images" + sep + "transacciones.png");
	pdf.addTitle("Listado de empleados");
	pdf.add(Image.getInstance(logo));
	pdf.add(titulo);
    }

    private static void agregarLineasEnBlanco(Paragraph parrafo, int nLineas) {
	for (int i = 0; i < nLineas; i++)
	    parrafo.add(new Paragraph(" "));
    }

    private String rangoFechas() {
	Date desde = this.getFechaDesde();
	Date hasta = this.getFechaHasta();
	SimpleDateFormat formateada = new SimpleDateFormat("dd/MM/yyyy");
	String sDesde = formateada.format(desde);
	String sHasta = formateada.format(hasta);
	String concat = sDesde + " y el " + sHasta;
	return concat;

    }

    @SuppressWarnings("unused")
    private String fechaActual() {
	Date hoy = new Date();
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	String fecha = formato.format(hoy);
	return fecha;

    }

    /**
     * @return the cuentaClienteList
     */
    public List<CuentaCliente> getCuentaClienteList() {
	return cuentaClienteList;
    }

    /**
     * @param cuentaClienteList
     *            the cuentaClienteList to set
     */
    public void setCuentaClienteList(List<CuentaCliente> cuentaClienteList) {
	CuentaClienteManagedBean.cuentaClienteList = cuentaClienteList;
    }

    /**
     * @return the filteredCuentaClientes
     */
    public List<CuentaCliente> getFilteredCuentaClientes() {
	return filteredCuentaClientes;
    }

    /**
     * @param filteredCuentaClientes
     *            the filteredCuentaClientes to set
     */
    public void setFilteredCuentaClientes(List<CuentaCliente> filteredCuentaClientes) {
	this.filteredCuentaClientes = filteredCuentaClientes;
    }

    /**
     * @return the cuentaSelected
     */
    public CuentaCliente getCuentaSelected() {
	return cuentaSelected;
    }

    /**
     * @param cuentaSelected
     *            the cuentaSelected to set
     */
    public void setCuentaSelected(CuentaCliente cuentaSelected) {
	CuentaClienteManagedBean.cuentaSelected = cuentaSelected;
    }

}
