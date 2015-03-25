package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.Localidad;
import tesis.playon.web.service.IBarrioService;

/**
 * 
 * @author alejandro
 * 
 */

@ManagedBean(name = "barrioMB")
@RequestScoped
public class BarrioManagedBean implements Serializable {

    private static final long serialVersionUID = 6773490680356877684L;

    private static final String LISTA_BARRIO = "barriolist";

    private static final String ERROR = "error";

    @ManagedProperty(value = "#{BarrioService}")
    IBarrioService barrioService;

    List<Barrio> barrioList;

    private String nombre;

    private Localidad localidad;

    public String addBarrio() {
	try {
	    Barrio barrio = new Barrio();
	    barrio.setNombre(getNombre());
	    getBarrioService().save(barrio);
	    return LISTA_BARRIO;
	} catch (DataAccessException e) {
	    e.printStackTrace();
	}
	return ERROR;
    }

    public void deleteBarrio(Barrio barrio) {
	getBarrioService().delete(barrio);
    }

    public void updateBarrio(Barrio barrio) {
	getBarrioService().update(barrio);
    }

    public void reset() {
	this.setNombre("");
	this.setLocalidad(null);
    }

    public IBarrioService getBarrioService() {
	return barrioService;
    }

    public void setBarrioService(IBarrioService barrioService) {
	this.barrioService = barrioService;
    }

    public List<Barrio> getBarrioList() {
	barrioList = new ArrayList<Barrio>();
	barrioList.addAll(getBarrioService().findAll());
	return barrioList;
    }

    public void setBarrioList(List<Barrio> barrioList) {
	this.barrioList = barrioList;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public Localidad getLocalidad() {
	return localidad;
    }

    public void setLocalidad(Localidad localidad) {
	this.localidad = localidad;
    }

}