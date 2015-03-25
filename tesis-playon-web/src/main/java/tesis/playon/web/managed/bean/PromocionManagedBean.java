package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.model.EstadoPromocion;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Promocion;
import tesis.playon.web.model.Tarifa;
import tesis.playon.web.model.TipoEstadia;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.ICategoriaVehiculoService;
import tesis.playon.web.service.IEstadoPromocionService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.IPromocionService;
import tesis.playon.web.service.ITarifaService;
import tesis.playon.web.service.ITipoEstadiaService;
import tesis.playon.web.service.IUsuarioService;

/**
 * 
 * @author pablo
 * 
 */
@ManagedBean(name = "promocionMB")
@ViewScoped
public class PromocionManagedBean implements Serializable {

    private static final long serialVersionUID = 6826797453747733748L;

    @ManagedProperty(value = "#{EstadoPromocionService}")
    IEstadoPromocionService estadoPromocionService;

    @ManagedProperty(value = "#{PromocionService}")
    IPromocionService promocionService;

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    @ManagedProperty(value = "#{TarifaService}")
    ITarifaService tarifaService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{TipoEstadiaService}")
    ITipoEstadiaService tipoEstadiaService;

    @ManagedProperty(value = "#{CategoriaVehiculoService}")
    ICategoriaVehiculoService categoriaVehiculoService;

    // Atributtes Promocion

    private String descripcion;

    private Float descuento;

    private Date fechaAlta;

    private Date fechaInicio;

    private Date fechaFin;

    private Date horaInicio;

    private Date horaFin;

    private static Date horaInicioSelected;

    private static Date horaFinSelected;

    private Float montoFijo;

    private Float montoConDescuento;

    private String nombre;

    private static Tarifa tarifa;

    private Playa playa;

    private EstadoPromocion estadoPromocion;

    private Date today;

    private List<Promocion> promocionPlayaList;

    private static Promocion promocionSelected;

    private List<Promocion> promocionPlayaListSelected;

    private static Playa playaSelected;

    private List<Promocion> promocionListEmpleado;

    private boolean incluyeHorario = false;

    @PostConstruct
    private void init() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario user = getUsuarioService().findByNombreUsuario(userName);
	if (user != null && user.getPlaya() != null) {
	    this.playa = user.getPlaya();
	    promocionPlayaList = getPromocionService().findByPlaya(playa);
	    EstadoPromocion estado = getEstadoPromocionService().findByNombreEstadoPromocion("Vigente");
	    promocionListEmpleado = getPromocionService().findByPlaya(playa, estado);
	}
	today = new Date();
    }

    public String addPromocion() {
	Promocion promocion = null;

	try {
	    promocion = new Promocion();
	    promocion.setDescripcion(getDescripcion());
	    promocion.setDescuento(getDescuento());
	    promocion.setFechaAlta(new Date());
	    promocion.setFechaFin(getFechaFin());
	    promocion.setFechaInicio(getFechaInicio());

	    if (horaInicio != null && horaFin != null) {
		SimpleDateFormat formateador = new SimpleDateFormat("hh:mm a");
		if (!formateador.format(horaInicio).equals("12:00 AM") && !formateador.format(horaFin).equals("12:00 AM")) {
		    promocion.setHoraInicio(horaInicio);
		    promocion.setHoraFin(horaFin);
		}
	    }
	    promocion.setMontoFijo(getTarifa().getImporte());
	    promocion.setNombre(getNombre());
	    promocion.setPlaya(getPlaya());
	    promocion.setTarifa(getTarifa());

	    promocion.setEstadoPromocion(getEstadoPromocionService().findByNombreEstadoPromocion("Vigente"));

	    getPromocionService().save(promocion);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "Se registró exitosamente la promoción: " + promocion.getNombre(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);

	    return "promocionaddend";
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo registrar la promoción, Disculpe las molestias ocacionadas.", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}
	return null;
    }

    public String updatePromocion() {
	try {
	    Promocion promo = new Promocion();
	    promo = promocionSelected;

	    getPromocionService().update(promo);

	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "Se actualizó exitosamente la promoción: " + promocionSelected.getNombre(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);

	    return "promocioneslist";
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo modificar la promoción, Disculpe las molestias ocacionadas.", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}
	return "error";
    }

    public void deletePromocion() {
	try {
	    if (promocionSelected != null) {
		EstadoPromocion estado = getEstadoPromocionService().findByNombreEstadoPromocion("De baja");
		promocionSelected.setEstadoPromocion(estado);

		getPromocionService().update(promocionSelected);

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
			"Se dió de baja la promoción correctamente.", "");
		FacesContext.getCurrentInstance().addMessage(null, message);

	    }
	} catch (Exception ex) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo dar de baja la promoción, Disculpe las molestias ocacionadas.", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    ex.printStackTrace();
	}
    }

    public void calcularDescuento() {
	this.montoConDescuento = tarifa.getImporte() * (1 - (descuento / 100));
    }

    public void seleccionarTarifa(Tarifa tarifaParameter) {
	if (tarifaParameter != null) {
	    tarifa = tarifaParameter;
	    this.montoFijo = tarifaParameter.getImporte();
	}
    }

    public void validateDateInicial(FacesContext context, UIComponent component, Object value) {
	Date dateInicial = (Date) value;
	if (dateInicial != null) {
	    this.setFechaInicio(dateInicial);
	}
    }

    public void validateDate(FacesContext context, UIComponent component, Object value) {
	Date dateFin = (Date) value;
	if (getFechaInicio() != null) {
	    if (dateFin.before(getFechaInicio())) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
			"La fecha de finalización debe ser mayor a la fecha de inicio.", null);
		throw new ValidatorException(message);
	    }
	}
    }

    public void findPlayaById() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	if (!facesContext.isPostback()) {
	    String parametroID = facesContext.getExternalContext().getRequestParameterMap().get("id");
	    if (!parametroID.equals("") || parametroID != null) {
		int idPlayaSelected = Integer.parseInt(parametroID);
		playaSelected = getPlayaService().findById(idPlayaSelected);
	    }
	}
    }

    public void toggleTypes(ValueChangeEvent event) {
	incluyeHorario = !incluyeHorario;
	FacesContext.getCurrentInstance().renderResponse();
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public IPlayaService getPlayaService() {
	return playaService;
    }

    public void setPlayaService(IPlayaService playaService) {
	this.playaService = playaService;
    }

    public ITarifaService getTarifaService() {
	return tarifaService;
    }

    public void setTarifaService(ITarifaService tarifaService) {
	this.tarifaService = tarifaService;
    }

    public IEstadoPromocionService getEstadoPromocionService() {
	return estadoPromocionService;
    }

    public void setEstadoPromocionService(IEstadoPromocionService estadoPromocionService) {
	this.estadoPromocionService = estadoPromocionService;
    }

    public ITipoEstadiaService getTipoEstadiaService() {
	return tipoEstadiaService;
    }

    public void setTipoEstadiaService(ITipoEstadiaService tipoEstadiaService) {
	this.tipoEstadiaService = tipoEstadiaService;
    }

    public ICategoriaVehiculoService getCategoriaVehiculoService() {
	return categoriaVehiculoService;
    }

    public void setCategoriaVehiculoService(ICategoriaVehiculoService categoriaVehiculoService) {
	this.categoriaVehiculoService = categoriaVehiculoService;
    }

    public IPromocionService getPromocionService() {
	return promocionService;
    }

    public void setPromocionService(IPromocionService promocionService) {
	this.promocionService = promocionService;
    }

    // ============================ GETTER && SETTER =======================================

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public Float getDescuento() {
	return descuento;
    }

    public void setDescuento(Float descuento) {
	this.descuento = descuento;
    }

    public Date getFechaAlta() {
	return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
	this.fechaAlta = fechaAlta;
    }

    public Date getFechaInicio() {
	return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
	this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
	return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
	this.fechaFin = fechaFin;
    }

    public Float getMontoFijo() {
	return montoFijo;
    }

    public void setMontoFijo(Float montoFijo) {
	this.montoFijo = montoFijo;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public Tarifa getTarifa() {
	return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
	PromocionManagedBean.tarifa = tarifa;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public EstadoPromocion getEstadoPromocion() {
	return estadoPromocion;
    }

    public void setEstadoPromocion(EstadoPromocion estadoPromocion) {
	this.estadoPromocion = estadoPromocion;
    }

    public Float getMontoConDescuento() {
	if (promocionSelected != null) {
	    this.montoConDescuento = promocionSelected.getTarifa().getImporte()
		    * (1 - (promocionSelected.getDescuento() / 100));
	}
	return montoConDescuento;
    }

    public void setMontoConDescuento(Float montoConDescuento) {
	this.montoConDescuento = montoConDescuento;
    }

    public Date getToday() {
	return today;
    }

    public void setToday(Date today) {
	this.today = today;
    }

    public List<Promocion> getPromocionPlayaList() {
	return promocionPlayaList;
    }

    public void setPromocionPlayaList(List<Promocion> promocionPlayaList) {
	this.promocionPlayaList = promocionPlayaList;
    }

    public List<Promocion> getPromocionPlayaListSelected() {
	if (playaSelected != null) {
	    estadoPromocion = getEstadoPromocionService().findByNombreEstadoPromocion("Vigente");
	    promocionPlayaListSelected = getPromocionService().findByPlaya(playaSelected, estadoPromocion);
	}
	return promocionPlayaListSelected;
    }

    public void setPromocionPlayaListSelected(List<Promocion> promocionPlayaListSelected) {
	this.promocionPlayaListSelected = promocionPlayaListSelected;
    }

    public Promocion getPromocionSelected() {
	return promocionSelected;
    }

    public void setPromocionSelected(Promocion promocionSelected) {
	PromocionManagedBean.promocionSelected = promocionSelected;
    }

    public Playa getPlayaSelected() {
	return playaSelected;
    }

    public void setPlayaSelected(Playa playaSelected) {
	PromocionManagedBean.playaSelected = playaSelected;
    }

    public Date getHoraInicio() {
	return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
	this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
	return horaFin;
    }

    public void setHoraFin(Date horaFin) {
	this.horaFin = horaFin;
    }

    public Date getHoraInicioSelected() {
	return horaInicioSelected;
    }

    public void setHoraInicioSelected(Date horaInicioSelected) {
	PromocionManagedBean.horaInicioSelected = horaInicioSelected;
    }

    public Date getHoraFinSelected() {
	return horaFinSelected;
    }

    public void setHoraFinSelected(Date horaFinSelected) {
	PromocionManagedBean.horaFinSelected = horaFinSelected;
    }

    // private int getHora(Time time) {
    // String horaCompleta = time.toString();
    // String toObject[] = horaCompleta.split(":");
    // return Integer.parseInt(toObject[0]);
    // }
    //
    // private int getMinutos(Time time) {
    // String horaCompleta = time.toString();
    // String toObject[] = horaCompleta.split(":");
    // return Integer.parseInt(toObject[1]);
    // }
    //
    // private int getSegundos(Time time) {
    // String horaCompleta = time.toString();
    // String toObject[] = horaCompleta.split(":");
    // return Integer.parseInt(toObject[2]);
    // }

    public List<Promocion> getPromocionListEmpleado() {
	return promocionListEmpleado;
    }

    public void setPromocionListEmpleado(List<Promocion> promocionListEmpleado) {
	this.promocionListEmpleado = promocionListEmpleado;
    }

    private List<Tarifa> filteredPromociones;

    @SuppressWarnings("unused")
    private SelectItem[] categoriaOptions;

    @SuppressWarnings("unused")
    private SelectItem[] tipoEstadiaOptions;

    public List<Tarifa> getFilteredPromociones() {
	return filteredPromociones;
    }

    public void setFilteredPromociones(List<Tarifa> filteredPromociones) {
	this.filteredPromociones = filteredPromociones;
    }

    public SelectItem[] getCategoriaOptions() {
	List<CategoriaVehiculo> categorias = new ArrayList<CategoriaVehiculo>();
	categorias = getCategoriaVehiculoService().findAll();
	this.categoriaOptions = new SelectItem[categorias.size() + 1];
	SelectItem[] options = new SelectItem[categorias.size() + 1];
	options[0] = new SelectItem("", "Todos");

	for (int i = 0; i < categorias.size(); i++) {
	    options[i + 1] = new SelectItem(categorias.get(i), categorias.get(i).getNombre());
	}
	return options;
    }

    public void setCategoriaOptions(SelectItem[] categoriaOptions) {
	this.categoriaOptions = categoriaOptions;
    }

    public SelectItem[] getTipoEstadiaOptions() {
	List<TipoEstadia> tipos = new ArrayList<TipoEstadia>();
	tipos = getTipoEstadiaService().findAll();
	this.categoriaOptions = new SelectItem[tipos.size() + 1];
	SelectItem[] options = new SelectItem[tipos.size() + 1];
	options[0] = new SelectItem("", "Todos");

	for (int i = 0; i < tipos.size(); i++) {
	    options[i + 1] = new SelectItem(tipos.get(i), tipos.get(i).getNombre());
	}
	return options;
    }

    public void setTipoEstadiaOptions(SelectItem[] tipoEstadiaOptions) {
	this.tipoEstadiaOptions = tipoEstadiaOptions;
    }

    /**
     * @return the incluyeHorario
     */
    public boolean isIncluyeHorario() {
	return incluyeHorario;
    }

    /**
     * @param incluyeHorario
     *            the incluyeHorario to set
     */
    public void setIncluyeHorario(boolean incluyeHorario) {
	this.incluyeHorario = incluyeHorario;
    }
}