/**
 * 
 */
package tesis.playon.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import tesis.playon.web.model.Empleado;

/**
 * @author Pablo
 *
 */
@FacesConverter(value = "empleadoConverter")
public class EmpleadoConverter implements Converter{
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
    String toObject[] = value.split(":");
	Empleado empleado = new Empleado();
	empleado.setId(Integer.parseInt(toObject[0]));
	return empleado;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value instanceof Empleado) {
	    Empleado empleado = (Empleado) value;
	    String idEmpleado = Integer.toString(empleado.getId());
	    String toString = idEmpleado;
	    return toString;
	} else {
	    return "No se pudo parsear el objeto.";
	}
    }
}