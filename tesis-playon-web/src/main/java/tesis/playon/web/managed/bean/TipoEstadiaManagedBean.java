/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.TipoEstadia;
import tesis.playon.web.service.ITipoEstadiaService;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "tipoEstadiaMB")
@RequestScoped
public class TipoEstadiaManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    private static final String LISTA_TIPOS_ESTADIA = "tipoestadialist";

    private static final String ERROR = "error";

    @ManagedProperty(value = "#{TipoEstadiaService}")
    ITipoEstadiaService tipoEstadiaService;

    List<TipoEstadia> tipoEstadiaList;

    private String nombre;

    private String descripcion;

    @PostConstruct
    private void init() {
	tipoEstadiaList = new ArrayList<TipoEstadia>();
	tipoEstadiaList = getTipoEstadiaService().findAll();
    }

    public String addTipoEstadia() {
	try {
	    TipoEstadia tipoEstadia = new TipoEstadia();
	    tipoEstadia.setNombre(getNombre());
	    tipoEstadia.setDescripcion(getDescripcion());
	    return LISTA_TIPOS_ESTADIA;
	} catch (DataAccessException e) {
	    e.printStackTrace();
	}
	return ERROR;
    }

    public void deleteTipoEstadia(TipoEstadia tipoEstadia) {
	getTipoEstadiaService().delete(tipoEstadia);
    }

    public void updateTipoEstadia(TipoEstadia tipoEstadia) {
	getTipoEstadiaService().update(tipoEstadia);
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

    public ITipoEstadiaService getTipoEstadiaService() {
	return tipoEstadiaService;
    }

    public List<TipoEstadia> getTipoEstadiaList() {
	return tipoEstadiaList;
    }

    public void setTipoEstadiaService(ITipoEstadiaService tipoEstadiaService) {
	this.tipoEstadiaService = tipoEstadiaService;
    }

    public void setTipoEstadiaList(List<TipoEstadia> tipoEstadiaList) {
	this.tipoEstadiaList = tipoEstadiaList;
    }
}