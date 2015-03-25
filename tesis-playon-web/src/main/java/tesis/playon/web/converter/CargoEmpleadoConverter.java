package tesis.playon.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import tesis.playon.web.model.CargoEmpleado;

@FacesConverter(value = "cargoEmpleadoConverter")
public class CargoEmpleadoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	String toObject[] = value.split(":");
	CargoEmpleado cargo = new CargoEmpleado();
	cargo.setId(Integer.parseInt(toObject[0]));
	cargo.setNombre(toObject[1]);
	return cargo;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value instanceof CargoEmpleado) {
	    CargoEmpleado cargo = (CargoEmpleado) value;
	    String idCargo = Integer.toString(cargo.getId());
	    String nombreCargo = cargo.getNombre();
	    String toString = idCargo + ":" + nombreCargo;
	    return toString;
	} else {
	    return "No se pudo parsear el objeto.";
	}
    }
}