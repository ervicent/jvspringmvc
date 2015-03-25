package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.EstadoPlaya;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.TipoPago;
import tesis.playon.web.model.TransaccionPlaya;
import tesis.playon.web.service.IBarrioService;
import tesis.playon.web.service.IEstadoPlayaService;
import tesis.playon.web.service.ITipoPagoService;

/**
 * Managed Bean para generar los filtros de las tablas.
 * 
 * @author alejandro
 * 
 */
@ManagedBean(name = "filtrosMB")
@ViewScoped
public class FiltrosManagedBean implements Serializable {

    private static final long serialVersionUID = 939090142822947971L;

    @ManagedProperty(value = "#{EstadoPlayaService}")
    IEstadoPlayaService estadoPlayaService;

    @ManagedProperty(value = "#{BarrioService}")
    IBarrioService barrioService;
    
    @ManagedProperty(value = "#{TipoPagoService}")
    private ITipoPagoService tipoPagoService;
    
    private SelectItem[] tipoPagoOptions;
    
    private SelectItem[] barriosOptions;

    private SelectItem[] estadosOptions;
    
    private SelectItem[] siNoOptions;
    
    private List<Playa> filteredPlayas;
    
    private List<TransaccionPlaya> filteredTransaccionesPlaya;
    
    
    public SelectItem[] getTipoPagoOptions() {
	if (tipoPagoOptions == null) {
	    List<TipoPago> tiposPago = new ArrayList<TipoPago>();
	    tiposPago.addAll(getTipoPagoService().findAll());
	    this.tipoPagoOptions = new SelectItem[tiposPago.size() + 1];
	    tipoPagoOptions[0] = new SelectItem("", "Todos");

	    for (int i = 0; i < tiposPago.size(); i++) {
		tipoPagoOptions[i + 1] = new SelectItem(tiposPago.get(i), tiposPago.get(i).getNombre());
	    }
	}

	return tipoPagoOptions;
    }

    public void setTipoPagoOptions(SelectItem[] tipoPagoOptions) {
	this.tipoPagoOptions = tipoPagoOptions;
    }
    
    /**
     * @return the barriosOptions
     */
    public SelectItem[] getBarriosOptions() {
	if (barriosOptions == null) {
	    List<Barrio> barrios = new ArrayList<Barrio>();
	    barrios.addAll(getBarrioService().findAll());
	    barriosOptions = new SelectItem[barrios.size() + 1];
	    barriosOptions[0] = new SelectItem("", "Todos");

	    for (int i = 0; i < barrios.size(); i++) {
		barriosOptions[i + 1] = new SelectItem(barrios.get(i), barrios.get(i).getNombre());
	    }
	}

	return barriosOptions;
    }

    /**
     * @param barriosOptions
     *            the barriosOptions to set
     */
    public void setBarriosOptions(SelectItem[] barriosOptions) {
	this.barriosOptions = barriosOptions;
    }
    

    /**
     * @return the estadosOptions
     */
    public SelectItem[] getEstadosOptions() {
	if (estadosOptions == null) {
	    List<EstadoPlaya> estados = new ArrayList<EstadoPlaya>();
	    estados.addAll(getEstadoPlayaService().findAll());
	    estadosOptions = new SelectItem[estados.size() + 1];
	    estadosOptions[0] = new SelectItem("", "Todos");

	    for (int i = 0; i < estados.size(); i++) {
		estadosOptions[i + 1] = new SelectItem(estados.get(i), estados.get(i).getNombre());
	    }
	}
	return estadosOptions;
    }

    public SelectItem[] getSiNoOptions() {
	//if (siNoOptions == null) {
	    siNoOptions = new SelectItem[3];
	    siNoOptions[0] = new SelectItem("", "Todos");
	    siNoOptions[1] = new SelectItem(new Boolean(true), "Si");
	    siNoOptions[2] = new SelectItem(new Boolean(false), "No");
	//}
	return siNoOptions;
    }

    public void setSiNoOptions(SelectItem[] siNoOptions) {
        this.siNoOptions = siNoOptions;
    }

    /**
     * @param estadosOptions
     *            the estadosOptions to set
     */
    public void setEstadosOptions(SelectItem[] estadosOptions) {
	this.estadosOptions = estadosOptions;
    }

    public IEstadoPlayaService getEstadoPlayaService() {
        return estadoPlayaService;
    }

    public void setEstadoPlayaService(IEstadoPlayaService estadoPlayaService) {
        this.estadoPlayaService = estadoPlayaService;
    }

    public IBarrioService getBarrioService() {
        return barrioService;
    }

    public void setBarrioService(IBarrioService barrioService) {
        this.barrioService = barrioService;
    }

    public ITipoPagoService getTipoPagoService() {
        return tipoPagoService;
    }

    public void setTipoPagoService(ITipoPagoService tipoPagoService) {
        this.tipoPagoService = tipoPagoService;
    }
    
    public List<Playa> getFilteredPlayas() {
	return filteredPlayas;
    }

    public void setFilteredPlayas(List<Playa> filteredPlayas) {
	this.filteredPlayas = filteredPlayas;
    }

    public List<TransaccionPlaya> getFilteredTransaccionesPlaya() {
        return filteredTransaccionesPlaya;
    }

    public void setFilteredTransaccionesPlaya(List<TransaccionPlaya> filteredTransaccionesPlaya) {
        this.filteredTransaccionesPlaya = filteredTransaccionesPlaya;
    }
}