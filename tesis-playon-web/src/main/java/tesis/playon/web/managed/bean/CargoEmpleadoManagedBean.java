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

import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.CargoEmpleado;
import tesis.playon.web.service.ICargoEmpleadoService;

/**
 * @author Pablo
 *
 */
@ManagedBean(name = "cargoEmpleadoMB")
@RequestScoped
public class CargoEmpleadoManagedBean implements Serializable{

    private static final long serialVersionUID = -1085389423375986168L;

    private static final String LISTA_CARGO_EMPLEADOS = "cargoempleadolist";

    private static final String ERROR = "error";

    @ManagedProperty(value = "#{CargoEmpleadoService}")
    ICargoEmpleadoService cargoEmpleadoService;

    List<CargoEmpleado> cargoEmpleadoList;

    private String nombre;

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

    private String descripcion;

    public ICargoEmpleadoService getCargoEmpleadoService() {
        return cargoEmpleadoService;
    }

    public void setCargoEmpleadoService(ICargoEmpleadoService cargoEmpleadoService) {
        this.cargoEmpleadoService = cargoEmpleadoService;
    }

    public List<CargoEmpleado> getCargoEmpleadoList() {
	cargoEmpleadoList = new ArrayList<CargoEmpleado>();
	cargoEmpleadoList.addAll(getCargoEmpleadoService().findAll());
        return cargoEmpleadoList;
    }

    public void setCargoEmpleadoList(List<CargoEmpleado> cargoEmpleadoList) {
        this.cargoEmpleadoList = cargoEmpleadoList;
    }

    public String addCargoEmpleado() {
	try {
	    CargoEmpleado cargoEmpleado = new CargoEmpleado();
	    cargoEmpleado.setNombre(getNombre());
	    cargoEmpleado.setDescripcion(getDescripcion());
	    getCargoEmpleadoService().save(cargoEmpleado);
	    return LISTA_CARGO_EMPLEADOS;
	} catch (DataAccessException e) {
	    e.printStackTrace();
	}
	return ERROR;
    }
}