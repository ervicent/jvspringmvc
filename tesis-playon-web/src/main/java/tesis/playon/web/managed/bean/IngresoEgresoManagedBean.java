package tesis.playon.web.managed.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import tesis.playon.web.model.Abono;
import tesis.playon.web.model.CargoEmpleado;
import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.CuentaCliente;
import tesis.playon.web.model.CuentaPlaya;
import tesis.playon.web.model.DetalleEstadia;
import tesis.playon.web.model.Empleado;
import tesis.playon.web.model.Estadia;
import tesis.playon.web.model.MarcaVehiculo;
import tesis.playon.web.model.ModeloVehiculo;
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
import tesis.playon.web.service.ICargoEmpleadoService;
import tesis.playon.web.service.ICuentaClienteService;
import tesis.playon.web.service.ICuentaPlayaService;
import tesis.playon.web.service.IDetalleEstadiaService;
import tesis.playon.web.service.IEmpleadoService;
import tesis.playon.web.service.IEstadiaService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IPromocionService;
import tesis.playon.web.service.ITarifaService;
import tesis.playon.web.service.ITipoEstadiaService;
import tesis.playon.web.service.ITipoPagoService;
import tesis.playon.web.service.ITransaccionClienteService;
import tesis.playon.web.service.ITransaccionPlayaService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.service.IVehiculoService;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;

@ManagedBean(name = "ingresoEgresoMB")
@SessionScoped
public class IngresoEgresoManagedBean implements Serializable {

    private static final long serialVersionUID = 2624234897160641014L;

    @ManagedProperty(value = "#{EmpleadoService}")
    IEmpleadoService empleadoService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{CargoEmpleadoService}")
    ICargoEmpleadoService cargoEmpleadoService;

    @ManagedProperty(value = "#{CuentaClienteService}")
    ICuentaClienteService cuentaClienteService;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    @ManagedProperty(value = "#{CuentaPlayaService}")
    ICuentaPlayaService cuentaPlayaService;

    @ManagedProperty(value = "#{VehiculoService}")
    IVehiculoService vehiculoService;

    @ManagedProperty(value = "#{EstadiaService}")
    IEstadiaService estadiaService;

    @ManagedProperty(value = "#{DetalleEstadiaService}")
    IDetalleEstadiaService detalleEstadiaService;

    @ManagedProperty(value = "#{TarifaService}")
    ITarifaService tarifaService;

    @ManagedProperty(value = "#{PromocionService}")
    IPromocionService promocionService;

    @ManagedProperty(value = "#{TipoPagoService}")
    ITipoPagoService tipoPagoService;

    @ManagedProperty(value = "#{TransaccionClienteService}")
    ITransaccionClienteService transaccionClienteService;

    @ManagedProperty(value = "#{TransaccionPlayaService}")
    ITransaccionPlayaService transaccionPlayaService;

    @ManagedProperty(value = "#{TipoEstadiaService}")
    ITipoEstadiaService tipoEstadiaService;

    @ManagedProperty(value = "#{AbonoService}")
    IAbonoService abonoService;

    private List<Tarifa> tarifaPlayaList;

    private Usuario usuario;

    private Usuario usuarioCliente;

    private Empleado empleado;

    private CargoEmpleado cargoEmpleado;

    private Playa playa;

    private CuentaPlaya cuentaPlaya;

    private Estadia estadia;

    private DetalleEstadia detalleEstadia;

    private Tarifa tarifa;

    private Cliente cliente;

    private CuentaCliente cuentaCliente;

    private TransaccionCliente transaccionCliente;

    private TipoPago tipoPago;

    private Vehiculo vehiculo;

    private CategoriaVehiculo categoriaVehiculo;

    private ModeloVehiculo modeloVehiculo;

    private MarcaVehiculo marcaVehiculo;

    private String nombreUsuario;

    private String patente;

    private String fechaIngresoFormateada;

    private String horaIngresoFormateada;

    private boolean existeVehiculo = false;

    private boolean existeTarifa = true;

    private boolean saldoPositvo = false;

    private boolean cobrado = true;

    private boolean importeCalculado;

    private Float importe;

    private List<TipoEstadia> tipoEstadiaList;

    private List<Promocion> promocionesDisponibles;

    private List<TransaccionPlaya> transaccionesPlayas;

    private List<DetalleEstadia> detalles;

    private List<Abono> listadoAbonos;

    private Promocion promocion;

    private Date fechaDesde;

    private Date fechaHasta;

    @PostConstruct
    private void init() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	try {
	    usuario = getUsuarioService().findByNombreUsuario(userName);
	    if (usuario != null) {
		this.empleado = getEmpleadoService().findByUsuario(usuario);
		this.playa = usuario.getPlaya();
		if (playa != null) {
		    this.cuentaPlaya = getCuentaPlayaService().findByPlaya(playa);
		    if (cuentaPlaya == null) {
			cuentaPlaya = new CuentaPlaya(playa);
			getCuentaPlayaService().save(cuentaPlaya);
		    }

		    this.estadia = getEstadiaService().findByPlaya(playa);
		    if (estadia == null) {
			estadia = new Estadia(playa);
			getEstadiaService().save(estadia);
		    }

		    this.cargoEmpleado = empleado.getCargoEmpleado();

		}
	    }
	    tipoEstadiaList = getTipoEstadiaService().findAll();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	this.updateTransaccionesPlayas();
    }

    public void searchVehiculo() {
	limpiar();
	String auxPatente = patente.trim();
	setVehiculo(getVehiculoService().findByPatenteVehiculo(auxPatente.toUpperCase()));

	if (null != vehiculo) {
	    setCategoriaVehiculo(vehiculo.getModeloVehiculo().getCategoriaVehiculo());
	    setModeloVehiculo(vehiculo.getModeloVehiculo());
	    setMarcaVehiculo(modeloVehiculo.getMarcaVehiculo());
	    setCliente(vehiculo.getCliente());

	    if (null != cliente) {
		setUsuarioCliente(cliente.getUsuario());
		setCuentaCliente(getCuentaClienteService().findByNroCuentaCliente(
			cliente.getCuentaCliente().getNroCuenta()));
		if (null != cuentaCliente) {
		    if (cuentaCliente.getSaldo() > 0) {
			saldoPositvo = true;
		    } else {
			saldoPositvo = false;
		    }
		}
	    }

	    setDetalleEstadia(getDetalleEstadiaService().findByVehiculoDetalleEstadia(vehiculo));
	    if (null != detalleEstadia && !detalleEstadia.getCobrado()) {
		cobrado = false;
		Timestamp ts = detalleEstadia.getFechaHoraIngreso();
		fechaIngresoFormateada = new SimpleDateFormat("dd/MM/yyyy").format(ts);
		horaIngresoFormateada = new SimpleDateFormat("HH:mm aa").format(ts);
	    }

	    tarifaPlayaList = getTarifaService().findTarifaVigenteByPlayaAndCategoriaVehiculo(playa,
		    vehiculo.getModeloVehiculo().getCategoriaVehiculo());

	    promocionesDisponibles = getPromocionService().findByCategoria(
		    vehiculo.getModeloVehiculo().getCategoriaVehiculo(), playa);

	    if (tarifaPlayaList == null) {
		setExisteTarifa(false);
		FacesContext.getCurrentInstance().addMessage(
			null,
			new FacesMessage(FacesMessage.SEVERITY_WARN,
				"No existen tarifas registradas para la categoria: " + categoriaVehiculo.getNombre(),
				""));
	    }
	    setExisteVehiculo(true);

	} else {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
		    "No se encontró el vehículo con patente: ", auxPatente);
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
    }

    public void registrarIngresoVehiculo() {
	Timestamp fechaHoraIngreso = new Timestamp(Calendar.getInstance().getTimeInMillis());
	detalleEstadia = new DetalleEstadia(estadia, vehiculo, empleado, fechaHoraIngreso, false);
	getDetalleEstadiaService().save(detalleEstadia);
	Integer disponibilidad = playa.getDisponibilidad() - 1;
	playa.setDisponibilidad(disponibilidad);
	getPlayaService().update(playa);

	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		"Se registró el ingreso exitosamente del vehiculo patente: ", patente);
	FacesContext.getCurrentInstance().addMessage(null, message);
	limpiar();
    }

    public void registrarEgresoVehiculo() {
	// actualizar saldo del cliente
	if (cuentaCliente.getSaldo() < importe) {
	    detalleEstadia.setImporteTotal(cuentaCliente.getSaldo());
	    cuentaCliente.setSaldo(0);
	} else {
	    float saldoActualizado = cuentaCliente.getSaldo() - importe;
	    cuentaCliente.setSaldo(saldoActualizado);
	}
	getCuentaClienteService().update(cuentaCliente);
	// actualizar saldo de la playa
	float saldoPlayaActualizado = cuentaPlaya.getSaldo() + detalleEstadia.getImporteTotal();
	cuentaPlaya.setSaldo(saldoPlayaActualizado);
	getCuentaPlayaService().update(cuentaPlaya);
	// crear la transaccion del cliente
	TipoPago tipoPago = getTipoPagoService().findByNombreTipoPago("Cuenta");
	transaccionCliente = new TransaccionCliente(detalleEstadia.getFechaHoraEgreso(),
		detalleEstadia.getImporteTotal(), tipoPago, cuentaCliente);
	getTransaccionClienteService().save(transaccionCliente);
	// actualizar y cerrar detalle estadia
	detalleEstadia.setTransaccionCliente(transaccionCliente);
	getDetalleEstadiaService().update(detalleEstadia);
	// actualizar lugares en una playa
	Integer disponibilidad = playa.getDisponibilidad() + 1;
	playa.setDisponibilidad(disponibilidad);
	getPlayaService().update(playa);

	// Registrar la transacción playa.
	TransaccionPlaya txPlaya = new TransaccionPlaya();
	txPlaya.setFecha(new Date());
	txPlaya.setDetalleEstadia(detalleEstadia);
	txPlaya.setCuentaPlaya(cuentaPlaya);
	txPlaya.setImporte(importe);
	txPlaya.setTipoPago(tipoPago);
	getTransaccionPlayaService().save(txPlaya);

	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		"Se registró el egreso exitosamente del vehículo patente: ", patente);
	FacesContext.getCurrentInstance().addMessage(null, message);

	limpiar();
    }

    public void calcularImporte() {
	if (tarifaPlayaList != null) {
	    for (Tarifa tarifaAux : tarifaPlayaList) {
		if (tarifaAux.equals(tarifa)) {
		    tarifa = tarifaAux;
		}
	    }

	    TipoEstadia tipoEstadia = tarifa.getTipoEstadia();
	    Timestamp fechaHoraEgreso;

	    // calculo de importe a pagar
	    if ("Por Hora".equals(tipoEstadia.getNombre())) {
		fechaHoraEgreso = new Timestamp(Calendar.getInstance().getTimeInMillis());
		detalleEstadia.setFechaHoraEgreso(fechaHoraEgreso);
		long diff = detalleEstadia.getFechaHoraEgreso().getTime()
			- detalleEstadia.getFechaHoraIngreso().getTime();
		double diferenciaEnHoras = diff / ((double) 1000 * 60 * 60);
		int horasACobrar = (int) diferenciaEnHoras;
		int minutos = (int) ((diferenciaEnHoras - horasACobrar) * 60);
		if (minutos > 10 || horasACobrar == 0)
		    horasACobrar++;
		importe = tarifa.getImporte() * horasACobrar;
		if (promocion != null) {
		    importe = importe * (1 - promocion.getDescuento() / 100);
		}
	    } else if ("Por Mes".equals(tipoEstadia.getNombre())) {
		Calendar calendario = Calendar.getInstance();
		calendario.clear();
		calendario.setTimeInMillis(detalleEstadia.getFechaHoraIngreso().getTime());
		calendario.add(Calendar.MONTH, 1);
		fechaHoraEgreso = new Timestamp(calendario.getTimeInMillis());
		detalleEstadia.setFechaHoraEgreso(fechaHoraEgreso);
		importe = tarifa.getImporte();
		if (promocion != null) {
		    importe = importe * (1 - promocion.getDescuento() / 100);
		}
	    } else if ("Por Noche".equals(tipoEstadia.getNombre())) {
		Calendar calendario = Calendar.getInstance();
		calendario.clear();
		calendario.setTimeInMillis(detalleEstadia.getFechaHoraIngreso().getTime());
		calendario.add(Calendar.HOUR_OF_DAY, 8);
		fechaHoraEgreso = new Timestamp(calendario.getTimeInMillis());
		detalleEstadia.setFechaHoraEgreso(fechaHoraEgreso);
		importe = tarifa.getImporte();
		if (promocion != null) {
		    importe = importe * (1 - promocion.getDescuento() / 100);
		}
	    } else if ("Por Día".equals(tipoEstadia.getNombre())) {
		Calendar calendario = Calendar.getInstance();
		calendario.clear();
		calendario.setTimeInMillis(detalleEstadia.getFechaHoraIngreso().getTime());
		calendario.add(Calendar.DAY_OF_MONTH, 1);
		fechaHoraEgreso = new Timestamp(calendario.getTimeInMillis());
		detalleEstadia.setFechaHoraEgreso(fechaHoraEgreso);
		importe = tarifa.getImporte();
		if (promocion != null) {
		    importe = importe * (1 - promocion.getDescuento() / 100);
		}
	    } else if ("Por Semana".equals(tipoEstadia.getNombre())) {
		Calendar calendario = Calendar.getInstance();
		calendario.clear();
		calendario.setTimeInMillis(detalleEstadia.getFechaHoraIngreso().getTime());
		calendario.add(Calendar.DAY_OF_MONTH, 7);
		fechaHoraEgreso = new Timestamp(calendario.getTimeInMillis());
		detalleEstadia.setFechaHoraEgreso(fechaHoraEgreso);
		importe = tarifa.getImporte();
		if (promocion != null) {
		    importe = importe * (1 - promocion.getDescuento() / 100);
		}
	    }
	    detalleEstadia.setImporteTotal(importe);
	    detalleEstadia.setTarifa(tarifa);
	    detalleEstadia.setPromocion(promocion);
	    detalleEstadia.setCobrado(true);
	    importeCalculado = true;
	}
    }

    public void limpiar() {
	usuarioCliente = null;
	cliente = null;
	cuentaCliente = null;
	vehiculo = null;
	categoriaVehiculo = null;
	modeloVehiculo = null;
	marcaVehiculo = null;
	detalleEstadia = null;
	tarifa = null;
	tarifaPlayaList = null;
	existeVehiculo = false;
	existeTarifa = true;
	saldoPositvo = false;
	importeCalculado = false;
	fechaIngresoFormateada = null;
	horaIngresoFormateada = null;
	cobrado = true;
	importe = 0.0f;
    }

    public IEmpleadoService getEmpleadoService() {
	return empleadoService;
    }

    public void setEmpleadoService(IEmpleadoService empleadoService) {
	this.empleadoService = empleadoService;
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public ICargoEmpleadoService getCargoEmpleadoService() {
	return cargoEmpleadoService;
    }

    public void setCargoEmpleadoService(ICargoEmpleadoService cargoEmpleadoService) {
	this.cargoEmpleadoService = cargoEmpleadoService;
    }

    public ICuentaClienteService getCuentaClienteService() {
	return cuentaClienteService;
    }

    public void setCuentaClienteService(ICuentaClienteService cuentaClienteService) {
	this.cuentaClienteService = cuentaClienteService;
    }

    public IPlayaService getPlayaService() {
	return playaService;
    }

    public void setPlayaService(IPlayaService playaService) {
	this.playaService = playaService;
    }

    public ICuentaPlayaService getCuentaPlayaService() {
	return cuentaPlayaService;
    }

    public void setCuentaPlayaService(ICuentaPlayaService cuentaPlayaService) {
	this.cuentaPlayaService = cuentaPlayaService;
    }

    public IVehiculoService getVehiculoService() {
	return vehiculoService;
    }

    public void setVehiculoService(IVehiculoService vehiculoService) {
	this.vehiculoService = vehiculoService;
    }

    public IEstadiaService getEstadiaService() {
	return estadiaService;
    }

    public void setEstadiaService(IEstadiaService estadiaService) {
	this.estadiaService = estadiaService;
    }

    public IDetalleEstadiaService getDetalleEstadiaService() {
	return detalleEstadiaService;
    }

    public void setDetalleEstadiaService(IDetalleEstadiaService detalleEstadiaService) {
	this.detalleEstadiaService = detalleEstadiaService;
    }

    public ITarifaService getTarifaService() {
	return tarifaService;
    }

    public void setTarifaService(ITarifaService tarifaService) {
	this.tarifaService = tarifaService;
    }

    public ITipoPagoService getTipoPagoService() {
	return tipoPagoService;
    }

    public IAbonoService getAbonoService() {
	return abonoService;
    }

    public void setAbonoService(IAbonoService abonoService) {
	this.abonoService = abonoService;
    }

    public void setTipoPagoService(ITipoPagoService tipoPagoService) {
	this.tipoPagoService = tipoPagoService;
    }

    public ITransaccionClienteService getTransaccionClienteService() {
	return transaccionClienteService;
    }

    public void setTransaccionClienteService(ITransaccionClienteService transaccionClienteService) {
	this.transaccionClienteService = transaccionClienteService;
    }

    public ITipoEstadiaService getTipoEstadiaService() {
	return tipoEstadiaService;
    }

    public void setTipoEstadiaService(ITipoEstadiaService tipoEstadiaService) {
	this.tipoEstadiaService = tipoEstadiaService;
    }

    public ITransaccionPlayaService getTransaccionPlayaService() {
	return transaccionPlayaService;
    }

    public void setTransaccionPlayaService(ITransaccionPlayaService transaccionPlayaService) {
	this.transaccionPlayaService = transaccionPlayaService;
    }

    public IPromocionService getPromocionService() {
	return promocionService;
    }

    public void setPromocionService(IPromocionService promocionService) {
	this.promocionService = promocionService;
    }

    public List<Tarifa> getTarifaPlayaList() {
	return tarifaPlayaList;
    }

    public void setTarifaPlayaList(List<Tarifa> tarifaPlayaList) {
	this.tarifaPlayaList = tarifaPlayaList;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    public Usuario getUsuarioCliente() {
	return usuarioCliente;
    }

    public void setUsuarioCliente(Usuario usuarioCliente) {
	this.usuarioCliente = usuarioCliente;
    }

    public Empleado getEmpleado() {
	return empleado;
    }

    public void setEmpleado(Empleado empleado) {
	this.empleado = empleado;
    }

    public CargoEmpleado getCargoEmpleado() {
	return cargoEmpleado;
    }

    public void setCargoEmpleado(CargoEmpleado cargoEmpleado) {
	this.cargoEmpleado = cargoEmpleado;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public CuentaPlaya getCuentaPlaya() {
	return cuentaPlaya;
    }

    public void setCuentaPlaya(CuentaPlaya cuentaPlaya) {
	this.cuentaPlaya = cuentaPlaya;
    }

    public Estadia getEstadia() {
	return estadia;
    }

    public void setEstadia(Estadia estadia) {
	this.estadia = estadia;
    }

    public DetalleEstadia getDetalleEstadia() {
	return detalleEstadia;
    }

    public void setDetalleEstadia(DetalleEstadia detalleEstadia) {
	this.detalleEstadia = detalleEstadia;
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

    public TipoPago getTipoPago() {
	return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
	this.tipoPago = tipoPago;
    }

    public Vehiculo getVehiculo() {
	return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
	this.vehiculo = vehiculo;
    }

    public CategoriaVehiculo getCategoriaVehiculo() {
	return categoriaVehiculo;
    }

    public void setCategoriaVehiculo(CategoriaVehiculo categoriaVehiculo) {
	this.categoriaVehiculo = categoriaVehiculo;
    }

    public ModeloVehiculo getModeloVehiculo() {
	return modeloVehiculo;
    }

    public void setModeloVehiculo(ModeloVehiculo modeloVehiculo) {
	this.modeloVehiculo = modeloVehiculo;
    }

    public MarcaVehiculo getMarcaVehiculo() {
	return marcaVehiculo;
    }

    public void setMarcaVehiculo(MarcaVehiculo marcaVehiculo) {
	this.marcaVehiculo = marcaVehiculo;
    }

    public String getNombreUsuario() {
	return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
	this.nombreUsuario = nombreUsuario;
    }

    public String getPatente() {
	return patente;
    }

    public void setPatente(String patente) {
	this.patente = patente;
    }

    public String getFechaIngresoFormateada() {
	return fechaIngresoFormateada;
    }

    public void setFechaIngresoFormateada(String fechaIngresoFormateada) {
	this.fechaIngresoFormateada = fechaIngresoFormateada;
    }

    public String getFechaActualFormateada() {

	return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
    }

    public String getHoraIngresoFormateada() {
	return horaIngresoFormateada;
    }

    public void setHoraIngresoFormateada(String horaIngresoFormateada) {
	this.horaIngresoFormateada = horaIngresoFormateada;
    }

    public String getHoraActualFormateada() {
	return new SimpleDateFormat("HH:mm aa").format(Calendar.getInstance().getTime());
    }

    public boolean getExisteVehiculo() {
	return existeVehiculo;
    }

    public void setExisteVehiculo(boolean existeVehiculo) {
	this.existeVehiculo = existeVehiculo;
    }

    public boolean getExisteTarifa() {
	return existeTarifa;
    }

    public void setExisteTarifa(boolean existeTarifa) {
	this.existeTarifa = existeTarifa;
    }

    public boolean getSaldoPositvo() {
	return saldoPositvo;
    }

    public void setSaldoPositvo(boolean saldoPositvo) {
	this.saldoPositvo = saldoPositvo;
    }

    public boolean getCobrado() {
	return cobrado;
    }

    public void setCobrado(boolean cobrado) {
	this.cobrado = cobrado;
    }

    public boolean getImporteCalculado() {
	return importeCalculado;
    }

    public void setImporteCalculado(boolean importeCalculado) {
	this.importeCalculado = importeCalculado;
    }

    public Float getImporte() {
	return importe;
	// return Float.parseFloat(formatter.format(importe));
    }

    public void setImporte(Float importe) {
	this.importe = importe;
    }

    public List<TipoEstadia> getTipoEstadiaList() {
	return tipoEstadiaList;
    }

    public void setTipoEstadiaList(List<TipoEstadia> tipoEstadiaList) {
	this.tipoEstadiaList = tipoEstadiaList;
    }

    public List<Promocion> getPromocionesDisponibles() {
	return promocionesDisponibles;
    }

    public void setPromocionesDisponibles(List<Promocion> promocionesDisponibles) {
	this.promocionesDisponibles = promocionesDisponibles;
    }

    public Promocion getPromocion() {
	return promocion;
    }

    public void setPromocion(Promocion promocion) {
	this.promocion = promocion;
    }

    // método para cancelar la página.
    public String reset() {
	usuarioCliente = null;
	cliente = null;
	cuentaCliente = null;
	vehiculo = null;
	categoriaVehiculo = null;
	modeloVehiculo = null;
	marcaVehiculo = null;
	detalleEstadia = null;
	tarifa = null;
	tarifaPlayaList = null;
	existeVehiculo = false;
	existeTarifa = true;
	saldoPositvo = false;
	importeCalculado = false;
	fechaIngresoFormateada = null;
	horaIngresoFormateada = null;
	cobrado = true;
	importe = 0.0f;
	patente = null;
	return "/playa/ingresoegresovehiculo";
    }

    /***************************** LISTADOS E INFORMES *******************************************/

    public void buscarPorFecha() {

	List<DetalleEstadia> detallesAux = new ArrayList<DetalleEstadia>();

	estadia = getEstadiaService().findByPlaya(playa);
	detalles = getDetalleEstadiaService().findByHorarios(getEstadia(), fechaDesde, fechaHasta);

	for (DetalleEstadia detalleAux : detalles) {
	    if (detalleAux.getFechaHoraIngreso().after(fechaDesde)
		    && detalleAux.getFechaHoraEgreso().before(fechaHasta)) {
		detallesAux.add(detalleAux);

	    }
	}
	detalles = detallesAux;

    }

    public List<DetalleEstadia> getDetalles() {
	Date fechaDesde = (this.fechaDesde != null ? this.fechaDesde : new Date(01012013));
	Date fechaHasta = (this.fechaHasta != null ? this.fechaHasta : Calendar.getInstance().getTime());
	estadia = getEstadiaService().findByPlaya(playa);
	detalles = getDetalleEstadiaService().findByHorarios(getEstadia(), fechaDesde, fechaHasta);

	return detalles;
    }

    public void setDetalles(List<DetalleEstadia> detalles) {
	this.detalles = detalles;
    }

    public List<Abono> getListadoAbonos() {
	listadoAbonos = getAbonoService().findByPlaya(getPlaya());

	return listadoAbonos;
    }

    public void setListadoAbonos(List<Abono> listadoAbonos) {
	this.listadoAbonos = listadoAbonos;
    }

    public void updateListado() {

	fechaDesde = fechaHasta = null;
	this.updateTransaccionesPlayas();
    }

    public List<TransaccionPlaya> updateTransaccionesPlayas() {
	Date fechaDesde = (this.fechaDesde != null ? this.fechaDesde : new Date(01012013));
	Date fechaHasta = (this.fechaHasta != null ? this.fechaHasta : Calendar.getInstance().getTime());
	transaccionesPlayas = getTransaccionPlayaService().findTransaccionesByFecha(getCuentaPlaya(), fechaDesde,
		fechaHasta);
	return transaccionesPlayas;
    }

    public List<TransaccionPlaya> getTransaccionesPlayas() {
	// Date fechaDesde = (this.fechaDesde != null ? this.fechaDesde : new Date(01012013));
	// Date fechaHasta = (this.fechaHasta != null ? this.fechaHasta : Calendar.getInstance().getTime());
	// transaccionesPlayas = getTransaccionPlayaService().findTransaccionesByFecha(getCuentaPlaya(), fechaDesde,
	// fechaHasta);
	return transaccionesPlayas;
    }

    public void setTransaccionesPlayas(List<TransaccionPlaya> transaccionesPlayas) {
	this.transaccionesPlayas = transaccionesPlayas;
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

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
	String sep = File.separator;
	Document pdf = (Document) document;
	pdf.open();
	pdf.setPageSize(PageSize.A4);
	ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
	String logo = extContext.getRealPath("resources" + sep + "images" + sep + "LogoEncabezado3.png");
	pdf.addTitle("Historial de Transacciones");
	pdf.add(Image.getInstance(logo));
    }

    public void preProcesarPDFAbono(Object document) throws IOException, BadElementException, DocumentException {
	String sep = File.separator;
	Document pdf = (Document) document;
	pdf.open();
	pdf.setPageSize(PageSize.A4);
	ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
	String logo = extContext.getRealPath("resources" + sep + "images" + sep + "transacciones.png");
	pdf.addTitle("Abonos");
	pdf.add(Image.getInstance(logo));
    }

}
