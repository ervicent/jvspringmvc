/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import tesis.playon.web.model.ColorVehiculo;
import tesis.playon.web.service.IColorVehiculoService;

/**
 * @author eric
 * 
 */
@ManagedBean(name = "colorVehiculoMB")
@RequestScoped
public class ColorVehiculoManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;
    // private static final String LISTA_COLOR_VEHICULO = "colorvehiculolist";
    // private static final String ERROR = "error";

    @ManagedProperty(value = "#{ColorVehiculoService}")
    IColorVehiculoService colorVehiculoService;

    private List<ColorVehiculo> colorVehiculoList;
    private ColorVehiculo colorNulo;

    private String nombre;

    // para modificar un Color
    // private static ColorVehiculo colorSelected;

    public void deleteColorVehiculo(ColorVehiculo color) {
	getColorVehiculoService().delete(color);
    }

    // public String addColorVehiculo() {
    // try {
    // ColorVehiculo colorVehiculo = new ColorVehiculo();
    // colorVehiculo.setNombre(getNombre());
    // getColorVehiculoService().save(colorVehiculo);
    //
    // FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se agregó correctamente : "
    // + colorVehiculo.getNombre(), "");
    // FacesContext.getCurrentInstance().addMessage(null, message);
    // return LISTA_COLOR_VEHICULO;
    // } catch (DataAccessException e) {
    // FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, no se pudo agregar: "
    // + colorVehiculo.getNombre(), "Por favos, intentelo mas tarde.");
    // FacesContext.getCurrentInstance().addMessage(null, message);
    // e.printStackTrace();
    // }
    // return ERROR;
    // }
    //
    //
    // public String updateColorVehiculo(ColorVehiculo color) {
    // colorSelected = color;
    // return "colorvehiculoedit";
    // }
    //
    // public String updateColorVehiculo() {
    // try {
    // getColorVehiculoService().update(colorSelected);
    // FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, colorSelected.getNombre()
    // + " se modificó correctamente", "");
    // FacesContext.getCurrentInstance().addMessage(null, message);
    // return LISTA_COLOR_VEHICULO;
    // } catch (DataAccessException e) {
    // FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, "
    // + colorSelected.getNombre() + " no se pudo modificar", "Por favor, intentelo mas tarde.");
    // FacesContext.getCurrentInstance().addMessage(null, message);
    // e.printStackTrace();
    // }
    // return ERROR;
    // }
    //
    // public String modificarColorVehiculo(ColorVehiculo colorVeh) {
    // colorSelected = colorVeh;
    // return "colorvehiculoedit";
    // }

    public IColorVehiculoService getColorVehiculoService() {
	return colorVehiculoService;
    }

    public void setColorVehiculoService(IColorVehiculoService colorVehiculoService) {
	this.colorVehiculoService = colorVehiculoService;
    }

    public List<ColorVehiculo> getColorVehiculoList() {
	colorVehiculoList = new ArrayList<ColorVehiculo>();
	colorVehiculoList.addAll(getColorVehiculoService().findAll());
	return colorVehiculoList;
    }

    public void setColorVehiculoList(List<ColorVehiculo> colorVehiculoList) {
	this.colorVehiculoList = colorVehiculoList;
    }

    public ColorVehiculo getColorNulo() {
	colorNulo = new ColorVehiculo();
	colorNulo.setId(0);
	colorNulo.setNombre("Seleccione un color");
	return colorNulo;
    }

    public void setColorNulo(ColorVehiculo colorNulo) {
	this.colorNulo = colorNulo;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public void reset() {
	this.setNombre("");
    }

}