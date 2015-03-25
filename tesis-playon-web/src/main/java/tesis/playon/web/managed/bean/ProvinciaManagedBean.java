package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.Localidad;
import tesis.playon.web.model.Pais;
import tesis.playon.web.model.Provincia;
import tesis.playon.web.service.IProvinciaService;

@ManagedBean(name = "provinciaMB")
@RequestScoped
public class ProvinciaManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    private static final String PROVINCIA_PAIS = "provincialist";

    private static final String ERROR = "error";

    @ManagedProperty(value = "#{ProvinciaService}")
    IProvinciaService provinciaService;

    List<Provincia> provinciaList;

    private String nombre;

    private Pais pais;
    
    private List<Localidad> localidadList;

    public String addPais() {
	try {
	    Provincia provincia = new Provincia();
	    provincia.setNombre(getNombre());
	    provincia.setPais(getPais());
	    getProvinciaService().save(provincia);
	    return PROVINCIA_PAIS;
	} catch (DataAccessException e) {
	    e.printStackTrace();
	}
	return ERROR;
    }

    public void deleteProvincia(Provincia provincia) {
	getProvinciaService().delete(provincia);
    }

    public void updateProvincia(Provincia provincia) {
	getProvinciaService().update(provincia);
    }

    public void reset() {
	this.setNombre("");
	this.setPais(null);
	this.setLocalidadList(null);    
    }

    public IProvinciaService getProvinciaService() {
	return provinciaService;
    }

    public void setProvinciaService(IProvinciaService provinciaService) {
	this.provinciaService = provinciaService;
    }

    public List<Provincia> getProvinciaList() {
	if(pais != null){
	    provinciaList = getProvinciaService().findProvincias(getPais());
	}
	else
	    provinciaList = new ArrayList<Provincia>();
	return provinciaList;
    }

    public List<Localidad> getLocalidadList() {
   	try
   	{
   	    localidadList = new ArrayList<Localidad>();
            return localidadList;
   	}catch (Exception e) {
   	    return null;
   	}
       }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public void setProvinciaList(List<Provincia> provinciaList) {
        this.provinciaList = provinciaList;
    }

    public void setLocalidadList(List<Localidad> localidadList) {
        this.localidadList = localidadList;
    }
    
}