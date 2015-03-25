/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.Localidad;
import tesis.playon.web.model.Provincia;
import tesis.playon.web.service.ILocalidadService;

/**
 * @author pablo
 *
 */
@ManagedBean(name = "localidadMB")
@RequestScoped
public class LocalidadManagedBean implements Serializable{
    
    private static final long serialVersionUID = -1085389423375986168L;

    private static final String LOCALIDAD_PROVINCIA = "localidadlist";

    private static final String ERROR = "error";

    @ManagedProperty(value = "#{LocalidadService}")
    ILocalidadService localidadService;

    List<Localidad> localidadList;
    
    private Set<Barrio> barrios;

    private String nombre;

    private Provincia provincia;

    public String addLocalidad() {
	try {
	    Localidad localidad = new Localidad();
	    localidad.setNombre(getNombre());
	    localidad.setProvincia(getProvincia());
	    getLocalidadService().save(localidad);
	    return LOCALIDAD_PROVINCIA;
	} catch (DataAccessException e) {
	    e.printStackTrace();
	}
	return ERROR;
    }

    public void deleteLocalidad(Localidad localidad) {
	getLocalidadService().delete(localidad);
    }

    public void updateLocalidad(Localidad localidad) {
	getLocalidadService().update(localidad);
    }

    public void reset() {
	this.setNombre("");
	this.setProvincia(null);
    }

    public ILocalidadService getLocalidadService() {
        return localidadService;
    }

    public void setLocalidadService(ILocalidadService localidadService) {
        this.localidadService = localidadService;
    }

    public List<Localidad> getLocalidadList() {
	localidadList = new  ArrayList<Localidad>();
	localidadList.addAll(getLocalidadService().findAll());
        return localidadList;
    }

    public void setLocalidadList(List<Localidad> localidadList) {
        this.localidadList = localidadList;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Set<Barrio> getBarrios() {
	barrios= new HashSet<Barrio>(0);
	Localidad localidad = getLocalidadService().findByNombreLocalidad(getNombre());
	barrios.addAll(getLocalidadService().findBarrio(localidad));
        return barrios;
    }

    public void setBarrios(Set<Barrio> barrios) {
        this.barrios = barrios;
    }
}