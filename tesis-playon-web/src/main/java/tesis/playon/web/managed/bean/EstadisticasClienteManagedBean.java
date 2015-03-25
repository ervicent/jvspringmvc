/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;

import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.Vehiculo;
import tesis.playon.web.service.IClienteService;
import tesis.playon.web.service.ICuentaClienteService;
import tesis.playon.web.service.IDetalleEstadiaService;
import tesis.playon.web.service.ITransaccionClienteService;
import tesis.playon.web.service.IUsuarioService;
import tesis.playon.web.service.IVehiculoService;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "estadisticaClienteMB")
@ViewScoped
public class EstadisticasClienteManagedBean implements Serializable {

    private static final long serialVersionUID = 6507761141792592533L;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{CuentaClienteService}")
    ICuentaClienteService cuentaClienteService;

    @ManagedProperty(value = "#{ClienteService}")
    IClienteService clienteService;

    @ManagedProperty(value = "#{TransaccionClienteService}")
    ITransaccionClienteService transaccionClienteService;

    @ManagedProperty(value = "#{VehiculoService}")
    IVehiculoService vehiculoService;

    @ManagedProperty(value = "#{DetalleEstadiaService}")
    IDetalleEstadiaService detalleEstadiaService;

    private Cliente cliente;

    private Date fechaDesde, fechaHasta;

    private List<Vehiculo> vehiculoList;

    private CartesianChartModel lModelConsumo;

    private String datatipFormat;
    
    private int maximoConsumo;
    
    @PostConstruct
    private void init() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	if (userName != null) {
	    cliente = getClienteService().findByNombreUsuario(userName);
	    vehiculoList = getVehiculoService().findByCliente(cliente.getId());
	}
    }

    public void calcularConsumoPorPeriodo() {
	crearGraficoConsumoPorPerdiodo();
    }

    private void crearGraficoConsumoPorPerdiodo() {
	lModelConsumo = new CartesianChartModel();
	maximoConsumo = 0;
	for (Vehiculo vehiculo : vehiculoList) {
	    LineChartSeries serie = new LineChartSeries();
	    serie.setLabel("Vehículo: " + vehiculo.getPatente());
	    List<String[]> consumoVehiculo = getDetalleEstadiaService().findEstadiasByVehiculoByPeriodo(vehiculo,
		    fechaDesde, fechaHasta);

	    for (int i = 0; i < consumoVehiculo.size(); i++) {
		String[] vConsumo = (String[]) consumoVehiculo.get(i);
		for (int j = 0; j < vConsumo.length; j++) {
		    float consumo = Float.parseFloat(vConsumo[2]);
		    maximoConsumo = (int)consumo > maximoConsumo ? (int)consumo : maximoConsumo;
		    serie.set(String.format("%02d", Integer.parseInt(vConsumo[0])) + "/" + vConsumo[1], consumo);
		}
	    }
	    lModelConsumo.addSeries(serie);
	}
	if(maximoConsumo > 0)
	    maximoConsumo += 5;
    }

    /**
     * @return the usuarioService
     */
    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    /**
     * @param usuarioService
     *            the usuarioService to set
     */
    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    /**
     * @return the cuentaClienteService
     */
    public ICuentaClienteService getCuentaClienteService() {
	return cuentaClienteService;
    }

    /**
     * @param cuentaClienteService
     *            the cuentaClienteService to set
     */
    public void setCuentaClienteService(ICuentaClienteService cuentaClienteService) {
	this.cuentaClienteService = cuentaClienteService;
    }

    /**
     * @return the clienteService
     */
    public IClienteService getClienteService() {
	return clienteService;
    }

    /**
     * @param clienteService
     *            the clienteService to set
     */
    public void setClienteService(IClienteService clienteService) {
	this.clienteService = clienteService;
    }

    /**
     * @return the transaccionClienteService
     */
    public ITransaccionClienteService getTransaccionClienteService() {
	return transaccionClienteService;
    }

    /**
     * @param transaccionClienteService
     *            the transaccionClienteService to set
     */
    public void setTransaccionClienteService(ITransaccionClienteService transaccionClienteService) {
	this.transaccionClienteService = transaccionClienteService;
    }

    /**
     * @return the vehiculoService
     */
    public IVehiculoService getVehiculoService() {
	return vehiculoService;
    }

    /**
     * @param vehiculoService
     *            the vehiculoService to set
     */
    public void setVehiculoService(IVehiculoService vehiculoService) {
	this.vehiculoService = vehiculoService;
    }

    /**
     * @return the detalleEstadiaService
     */
    public IDetalleEstadiaService getDetalleEstadiaService() {
	return detalleEstadiaService;
    }

    /**
     * @param detalleEstadiaService
     *            the detalleEstadiaService to set
     */
    public void setDetalleEstadiaService(IDetalleEstadiaService detalleEstadiaService) {
	this.detalleEstadiaService = detalleEstadiaService;
    }

    public String getDatatipFormat() {
	datatipFormat = "<span style=\"display:none;\">$%.2f</span><span>$%.2f</span>";
	return datatipFormat;
    }

    /**
     * @return the fechaDesde
     */
    public Date getFechaDesde() {
	return fechaDesde;
    }

    /**
     * @param fechaDesde
     *            the fechaDesde to set
     */
    public void setFechaDesde(Date fechaDesde) {
	this.fechaDesde = fechaDesde;
    }

    /**
     * @return the fechaHasta
     */
    public Date getFechaHasta() {
	return fechaHasta;
    }

    /**
     * @param fechaHasta
     *            the fechaHasta to set
     */
    public void setFechaHasta(Date fechaHasta) {
	this.fechaHasta = fechaHasta;
    }

    /**
     * @return the linearModel
     */
    public CartesianChartModel getLinearModel() {
	return lModelConsumo;
    }

    /**
     * @param linearModel
     *            the linearModel to set
     */
    public void setLinearModel(CartesianChartModel linearModel) {
	this.lModelConsumo = linearModel;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
	return cliente;
    }

    /**
     * @param cliente
     *            the cliente to set
     */
    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

    /**
     * @return the vehiculoList
     */
    public List<Vehiculo> getVehiculoList() {
	return vehiculoList;
    }

    /**
     * @param vehiculoList
     *            the vehiculoList to set
     */
    public void setVehiculoList(List<Vehiculo> vehiculoList) {
	this.vehiculoList = vehiculoList;
    }

    /**
     * @return the lModelConsumo
     */
    public CartesianChartModel getlModelConsumo() {
        return lModelConsumo;
    }

    /**
     * @param lModelConsumo the lModelConsumo to set
     */
    public void setlModelConsumo(CartesianChartModel lModelConsumo) {
        this.lModelConsumo = lModelConsumo;
    }

    /**
     * @return the maximoConsumo
     */
    public int getMaximoConsumo() {
        return maximoConsumo;
    }

    /**
     * @param maximoConsumo the maximoConsumo to set
     */
    public void setMaximoConsumo(int maximoConsumo) {
        this.maximoConsumo = maximoConsumo;
    }

}
