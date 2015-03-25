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

import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.service.ICategoriaVehiculoService;

/**
 * @author pablo
 *
 */
@ManagedBean(name = "categoriaVehiculoMB")
@RequestScoped
public class CategoriaVehiculoManagedBean implements Serializable{
    
    private static final long serialVersionUID = -1085389423375986168L;

    private static final String LISTA_CATEGORIAS_VEHICULO = "categoriavehiculolist";

    private static final String ERROR = "error";
    
    @ManagedProperty(value = "#{CategoriaVehiculoService}")
    ICategoriaVehiculoService categoriaVehiculoService;
    
    List<CategoriaVehiculo> categoriaVehiculoList;
    
    private String nombre;

    private String descripcion;
    
    @PostConstruct
    private void init(){
	categoriaVehiculoList= new ArrayList<CategoriaVehiculo>();
	categoriaVehiculoList=getCategoriaVehiculoService().findAll();
    }
    
    public String addCategoriaVehiculo() {
	try {
	    CategoriaVehiculo categoria = new CategoriaVehiculo();
	    categoria.setNombre(getNombre());
	    categoria.setDescripcion(getDescripcion());
	    getCategoriaVehiculoService().save(categoria);
	    return LISTA_CATEGORIAS_VEHICULO;
	} catch (DataAccessException e) {
	    e.printStackTrace();
	}
	return ERROR;
    }

    public void deleteCategoriaVehiculo(CategoriaVehiculo categoria) {
	getCategoriaVehiculoService().delete(categoria);
    }

    public void updateCategoriaVehiculo(CategoriaVehiculo categoria) {
	getCategoriaVehiculoService().update(categoria);
    }

    public void reset() {
	this.setNombre("");
	this.setDescripcion("");
    }

    public ICategoriaVehiculoService getCategoriaVehiculoService() {
        return categoriaVehiculoService;
    }

    public void setCategoriaVehiculoService(ICategoriaVehiculoService categoriaVehiculoService) {
        this.categoriaVehiculoService = categoriaVehiculoService;
    }

    public List<CategoriaVehiculo> getCategoriaVehiculoList() {
	return categoriaVehiculoList;
    }

    public void setCategoriaVehiculoList(List<CategoriaVehiculo> categoriaVehiculoList) {
        this.categoriaVehiculoList = categoriaVehiculoList;
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
}