/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import tesis.playon.web.model.Abono;
import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.CuentaCliente;
import tesis.playon.web.model.CuentaPlaya;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Promocion;
import tesis.playon.web.model.Tarifa;
import tesis.playon.web.model.TipoEstadia;
import tesis.playon.web.model.TipoPago;
import tesis.playon.web.model.TransaccionCliente;
import tesis.playon.web.model.TransaccionPlaya;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.model.Vehiculo;
import tesis.playon.web.service.IAbonoService;
import tesis.playon.web.service.ICuentaClienteService;
import tesis.playon.web.service.ICuentaPlayaService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IPromocionService;
import tesis.playon.web.service.ITarifaService;
import tesis.playon.web.service.ITipoEstadiaService;
import tesis.playon.web.service.ITipoPagoService;
import tesis.playon.web.service.ITransaccionClienteService;
import tesis.playon.web.service.ITransaccionPlayaService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.service.IVehiculoService;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "abonoMB")
@ViewScoped
public class AbonoManagedBean implements Serializable {

    private static final long serialVersionUID = 7775997635327144533L;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{VehiculoService}")
    IVehiculoService vehiculoService;

    @ManagedProperty(value = "#{TipoEstadiaService}")
    ITipoEstadiaService tipoEstadiaService;

    @ManagedProperty(value = "#{AbonoService}")
    IAbonoService abonoService;

    @ManagedProperty(value = "#{TarifaService}")
    ITarifaService tarifaService;

    @ManagedProperty(value = "#{PromocionService}")
    IPromocionService promocionService;

    @ManagedProperty(value = "#{CuentaClienteService}")
    ICuentaClienteService cuentaClienteService;

    @ManagedProperty(value = "#{CuentaPlayaService}")
    ICuentaPlayaService cuentaPlayaService;

    @ManagedProperty(value = "#{TransaccionClienteService}")
    ITransaccionClienteService transaccionClienteService;

    @ManagedProperty(value = "#{TransaccionPlayaService}")
    ITransaccionPlayaService transaccionPlayaService;

    @ManagedProperty(value = "#{TipoPagoService}")
    ITipoPagoService tipoPagoService;

    private Date fechaDesde;

    private Date fechaHasta;

    private Tarifa tarifa;

    private Cliente cliente;

    private Playa playa;

    private String patente;

    private Promocion promocion;

    private Vehiculo vehiculo;

    private Usuario usuario;

    private List<Promocion> promocionesDisponibles;

    private List<Abono> abonadosEnLaPlaya;

    private Usuario usuarioLoggeadoPlaya;

    private Usuario usuarioLoggeadoCliente;

    private Playa playaLoggeada;

    private Date today;

    private Float importeConDescuento;

    @PostConstruct
    private void init() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario user = getUsuarioService().findByNombreUsuario(userName);
	if (user != null && user.getPlaya() != null) {
	    usuarioLoggeadoPlaya = user;
	    playaLoggeada = user.getPlaya();
	    abonadosEnLaPlaya = getAbonoService().findByPlaya(playaLoggeada);
	}
	if (user != null && user.getPlaya() == null) {
	    usuarioLoggeadoCliente = user;
	}
	today = new Date();
    }

    public String abonoAddFromPlaya() {
	Abono abono;
	try {

	    if (getAbonoService().existeAbonoVehiculo(vehiculo, playaLoggeada) == false) {

		abono = new Abono(getFechaDesde(), getFechaHasta(), getTarifa(), playaLoggeada);
		abono.setVehiculo(getVehiculo());
		abono.setPromocion(getPromocion());

		CuentaCliente cuentaCliente = new CuentaCliente();
		cuentaCliente = vehiculo.getCliente().getCuentaCliente();

		float nuevoSaldo;

		if (cuentaCliente.getSaldo() >= getTarifa().getImporte()) {

		    if (getPromocion() != null) {
			nuevoSaldo = cuentaCliente.getSaldo()
				- (getTarifa().getImporte() * ((getPromocion().getDescuento()) / 100 + 1));
		    } else {
			nuevoSaldo = cuentaCliente.getSaldo() - (getTarifa().getImporte());
		    }

		    // Grabo el abono
		    getAbonoService().save(abono);

		    // Actualizo la cuenta de cliente.
		    cuentaCliente.setSaldo(nuevoSaldo);
		    getCuentaClienteService().update(cuentaCliente);

		    // Creo la transacción de la playa
		    TransaccionPlaya txPlaya = new TransaccionPlaya();
		    CuentaPlaya cuentaPlaya = getCuentaPlayaService().findByPlaya(playaLoggeada);
		    txPlaya.setCuentaPlaya(cuentaPlaya);
		    txPlaya.setFecha(new Date());

		    float importe;
		    if (getPromocion() != null) {
			importe = getTarifa().getImporte() * ((getPromocion().getDescuento()) / 100 + 1);
		    } else {
			importe = getTarifa().getImporte();
		    }
		    txPlaya.setImporte(importe);

		    TipoPago tipoPagoCuenta = getTipoPagoService().findByNombreTipoPago("Cuenta");
		    txPlaya.setTipoPago(tipoPagoCuenta);

		    getTransaccionPlayaService().save(txPlaya);

		    // Creo la transacción cliente
		    TransaccionCliente transaccionCliente = new TransaccionCliente();
		    transaccionCliente.setCuentaCliente(cuentaCliente);
		    transaccionCliente.setFecha(new Date());
		    transaccionCliente.setImporte(-importe);
		    transaccionCliente.setTipoPago(tipoPagoCuenta);

		    getTransaccionClienteService().save(transaccionCliente);

		    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
			    "Se registró exitosamente el abono mensual", "");
		    FacesContext.getCurrentInstance().addMessage(null, message);

		    return "abonoaddend";
		} else {
		    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
			    "No posee saldo suficiente para efectuar el abono mensual.", "");
		    FacesContext.getCurrentInstance().addMessage(null, message);
		}

	    } else {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
			"Ya existe un abonado en el período indicado. ¡Verifique las fechas!", "");
		FacesContext.getCurrentInstance().addMessage(null, message);
	    }

	} catch (Exception ex) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo registrar el abono mensual, Disculpe las molestias ocacionadas.", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    ex.printStackTrace();
	}
	return null;
    }
    
    
    public String abonoAddFromPlayaGerente() {
    	Abono abono;
    	try {

    	    if (getAbonoService().existeAbonoVehiculo(vehiculo, playaLoggeada) == false) {

    		abono = new Abono(getFechaDesde(), getFechaHasta(), getTarifa(), playaLoggeada);
    		abono.setVehiculo(getVehiculo());
    		abono.setPromocion(getPromocion());

    		CuentaCliente cuentaCliente = new CuentaCliente();
    		cuentaCliente = vehiculo.getCliente().getCuentaCliente();

    		float nuevoSaldo;

    		if (cuentaCliente.getSaldo() >= getTarifa().getImporte()) {

    		    if (getPromocion() != null) {
    			nuevoSaldo = cuentaCliente.getSaldo()
    				- (getTarifa().getImporte() * ((getPromocion().getDescuento()) / 100 + 1));
    		    } else {
    			nuevoSaldo = cuentaCliente.getSaldo() - (getTarifa().getImporte());
    		    }

    		    // Grabo el abono
    		    getAbonoService().save(abono);

    		    // Actualizo la cuenta de cliente.
    		    cuentaCliente.setSaldo(nuevoSaldo);
    		    getCuentaClienteService().update(cuentaCliente);

    		    // Creo la transacción de la playa
    		    TransaccionPlaya txPlaya = new TransaccionPlaya();
    		    CuentaPlaya cuentaPlaya = getCuentaPlayaService().findByPlaya(playaLoggeada);
    		    txPlaya.setCuentaPlaya(cuentaPlaya);
    		    txPlaya.setFecha(new Date());

    		    float importe;
    		    if (getPromocion() != null) {
    			importe = getTarifa().getImporte() * ((getPromocion().getDescuento()) / 100 + 1);
    		    } else {
    			importe = getTarifa().getImporte();
    		    }
    		    txPlaya.setImporte(importe);

    		    TipoPago tipoPagoCuenta = getTipoPagoService().findByNombreTipoPago("Cuenta");
    		    txPlaya.setTipoPago(tipoPagoCuenta);

    		    getTransaccionPlayaService().save(txPlaya);

    		    // Creo la transacción cliente
    		    TransaccionCliente transaccionCliente = new TransaccionCliente();
    		    transaccionCliente.setCuentaCliente(cuentaCliente);
    		    transaccionCliente.setFecha(new Date());
    		    transaccionCliente.setImporte(-importe);
    		    transaccionCliente.setTipoPago(tipoPagoCuenta);

    		    getTransaccionClienteService().save(transaccionCliente);

    		    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
    			    "Se registró exitosamente el abono mensual", "");
    		    FacesContext.getCurrentInstance().addMessage(null, message);

    		    return "abonoaddendgerente";
    		} else {
    		    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
    			    "No posee saldo suficiente para efectuar el abono mensual.", "");
    		    FacesContext.getCurrentInstance().addMessage(null, message);
    		}

    	    } else {
    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
    			"Ya existe un abonado en el período indicado. ¡Verifique las fechas!", "");
    		FacesContext.getCurrentInstance().addMessage(null, message);
    	    }

    	} catch (Exception ex) {
    	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
    		    "Error, no se pudo registrar el abono mensual, Disculpe las molestias ocacionadas.", "");
    	    FacesContext.getCurrentInstance().addMessage(null, message);
    	    ex.printStackTrace();
    	}
    	return null;
    }

    public void validateVehiculo(FacesContext context, UIComponent component, Object value) {
	String patente = value.toString();
	if (patente != null) {
	    vehiculo = getVehiculoService().findByPatenteVehiculo(patente.toUpperCase());
	    if (vehiculo != null) {
		usuario = vehiculo.getCliente().getUsuario();
		TipoEstadia tipoEstadia = getTipoEstadiaService().findByNombreTipoEstadia("Por Mes");
		CategoriaVehiculo categoriaVehiculo = vehiculo.getModeloVehiculo().getCategoriaVehiculo();
		tarifa = getTarifaService().findTarifaVigenteByPlayaAndCategoriaAndTipoEstadia(playaLoggeada,
			categoriaVehiculo, tipoEstadia);
		if (tarifa == null) {
		    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe tarifa mensual "
			    + "para la categoría " + tipoEstadia, "");
		    throw new ValidatorException(message);
		} else {
		    promocionesDisponibles = getPromocionService().findByPlayaAndTarifa(playaLoggeada, tarifa);
		}

	    } else {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
			"No existe el vehiculo con patente: " + patente, "");
		throw new ValidatorException(message);
	    }
	}
    }

    public void validateFechaVencimiento(FacesContext context, UIComponent component, Object value) {

	String fechaInicio = new SimpleDateFormat("dd/MM/yyyy").format(value);
	String[] dataTemp = fechaInicio.split("/");
	Calendar c = Calendar.getInstance();
	c.set(Integer.parseInt(dataTemp[2]), Integer.parseInt(dataTemp[1]) - 1, Integer.parseInt(dataTemp[0]));
	c.add(Calendar.MONTH, 1);
	fechaHasta = c.getTime();
	// System.out.println(fechaHasta);
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

    public Tarifa getTarifa() {
	return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
	this.tarifa = tarifa;
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

    public String getPatente() {
	return patente;
    }

    public void setPatente(String patente) {
	this.patente = patente;
    }

    public ITarifaService getTarifaService() {
	return tarifaService;
    }

    public void setTarifaService(ITarifaService tarifaService) {
	this.tarifaService = tarifaService;
    }

    public IVehiculoService getVehiculoService() {
	return vehiculoService;
    }

    public void setVehiculoService(IVehiculoService vehiculoService) {
	this.vehiculoService = vehiculoService;
    }

    public IPlayaService getPlayaService() {
	return playaService;
    }

    public void setPlayaService(IPlayaService playaService) {
	this.playaService = playaService;
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public ITipoEstadiaService getTipoEstadiaService() {
	return tipoEstadiaService;
    }

    public void setTipoEstadiaService(ITipoEstadiaService tipoEstadiaService) {
	this.tipoEstadiaService = tipoEstadiaService;
    }

    public IAbonoService getAbonoService() {
	return abonoService;
    }

    public void setAbonoService(IAbonoService abonoService) {
	this.abonoService = abonoService;
    }

    public IPromocionService getPromocionService() {
	return promocionService;
    }

    public void setPromocionService(IPromocionService promocionService) {
	this.promocionService = promocionService;
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

    public ITransaccionPlayaService getTransaccionPlayaService() {
	return transaccionPlayaService;
    }

    public void setTransaccionPlayaService(ITransaccionPlayaService transaccionPlayaService) {
	this.transaccionPlayaService = transaccionPlayaService;
    }

    public ICuentaPlayaService getCuentaPlayaService() {
	return cuentaPlayaService;
    }

    public void setCuentaPlayaService(ICuentaPlayaService cuentaPlayaService) {
	this.cuentaPlayaService = cuentaPlayaService;
    }

    public ITipoPagoService getTipoPagoService() {
	return tipoPagoService;
    }

    public void setTipoPagoService(ITipoPagoService tipoPagoService) {
	this.tipoPagoService = tipoPagoService;
    }

    public Promocion getPromocion() {
	return promocion;
    }

    public void setPromocion(Promocion promocion) {
	this.promocion = promocion;
    }

    public List<Promocion> getPromocionesDisponibles() {
	return promocionesDisponibles;
    }

    public void setPromocionesDisponibles(List<Promocion> promocionesDisponibles) {
	this.promocionesDisponibles = promocionesDisponibles;
    }

    public List<Abono> getAbonadosEnLaPlaya() {
	return abonadosEnLaPlaya;
    }

    public void setAbonadosEnLaPlaya(List<Abono> abonadosEnLaPlaya) {
	this.abonadosEnLaPlaya = abonadosEnLaPlaya;
    }

    public List<Abono> getAbonadosEnLaPlayaActivos() {
	Date hoy = new Date();
	List<Abono> abonadosEnLaPlayaActivos = new LinkedList<Abono>();

	for (Abono aux : abonadosEnLaPlaya) {

	    if (aux.getFechaVigenciaHasta().after(hoy))
		abonadosEnLaPlayaActivos.add(aux);

	}

	return abonadosEnLaPlayaActivos;
    }

    public Vehiculo getVehiculo() {
	return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
	this.vehiculo = vehiculo;
    }

    public Usuario getUsuarioLoggeadoPlaya() {
	return usuarioLoggeadoPlaya;
    }

    public void setUsuarioLoggeadoPlaya(Usuario usuarioLoggeadoPlaya) {
	this.usuarioLoggeadoPlaya = usuarioLoggeadoPlaya;
    }

    public Usuario getUsuarioLoggeadoCliente() {
	return usuarioLoggeadoCliente;
    }

    public void setUsuarioLoggeadoCliente(Usuario usuarioLoggeadoCliente) {
	this.usuarioLoggeadoCliente = usuarioLoggeadoCliente;
    }

    public Playa getPlayaLoggeada() {
	return playaLoggeada;
    }

    public void setPlayaLoggeada(Playa playaLoggeada) {
	this.playaLoggeada = playaLoggeada;
    }

    public Date getToday() {
	return today;
    }

    public void setToday(Date today) {
	this.today = today;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    public Float getImporteConDescuento() {
	if (promocion != null) {
	    importeConDescuento = ((100 - promocion.getDescuento()) / 100) * tarifa.getImporte();
	} else
	    importeConDescuento = null;
	return importeConDescuento;
    }

    public void setImporteConDescuento(Float importeConDescuento) {
	this.importeConDescuento = importeConDescuento;
    }

}
