package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.EstadoPlaya;
import tesis.playon.web.service.IEstadoPlayaService;

/**
 * 
 * @author alejandro
 *
 */
@ManagedBean(name = "estadoPlayaMB")
@RequestScoped
public class EstadoPlayaManagedBean implements Serializable {

    private static final long serialVersionUID = -5931448005422508808L;

    private static final String LISTA_ESTADOS_PLAYAS = "estadoplayalist";

    private static final String ERROR = "error";

    @ManagedProperty(value = "#{EstadoPlayaService}")
    IEstadoPlayaService estadoPlayaService;

    List<EstadoPlaya> estadoPlayaList;

    private String nombre;

    private String descripcion;

    public String addEstadoPlaya() {
	try {
	    EstadoPlaya estadoPlaya = new EstadoPlaya();
	    estadoPlaya.setNombre(getNombre());
	    estadoPlaya.setDescripcion(getDescripcion());
	    return LISTA_ESTADOS_PLAYAS;
	} catch (DataAccessException e) {
	    e.printStackTrace();
	}
	return ERROR;
    }

    public void deleteEstadoPlaya(EstadoPlaya estadoPlaya) {
	getEstadoPlayaService().delete(estadoPlaya);
    }

    public void updateEstadoPlaya(EstadoPlaya estadoPlaya) {
	getEstadoPlayaService().update(estadoPlaya);
    }

    public void reset() {
	this.setNombre("");
	this.setDescripcion("");
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public IEstadoPlayaService getEstadoPlayaService() {
	return estadoPlayaService;
    }

    public List<EstadoPlaya> getEstadoPlayaList() {
	estadoPlayaList = new ArrayList<EstadoPlaya>();
	estadoPlayaList.addAll(getEstadoPlayaService().findAll());
	return estadoPlayaList;
    }

    public void setEstadoPlayaService(IEstadoPlayaService estadoPlayaService) {
	this.estadoPlayaService = estadoPlayaService;
    }

    public void setEstadoPlayaList(List<EstadoPlaya> estadoPlayaList) {
	this.estadoPlayaList = estadoPlayaList;
    }

}
