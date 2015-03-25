package tesis.playon.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import tesis.playon.web.model.ColorVehiculo;

@FacesConverter(value = "colorVehiculoConverter")
public class ColorVehiculoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	if (!value.equals("-1")) {
	    String toObject[] = value.split(":");
	    if (toObject.length == 3) {
		ColorVehiculo color = new ColorVehiculo();
		color.setId(Integer.parseInt(toObject[0]));
		color.setNombre(toObject[1]);
		color.setValorHexadecimal(toObject[2]);
		return color;
	    }
	}
	return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value instanceof ColorVehiculo) {
	    ColorVehiculo color = (ColorVehiculo) value;
	    String idColor = Integer.toString(color.getId());
	    String nombreBarrio = color.getNombre();
	    String hexa = color.getValorHexadecimal();
	    String toString = idColor + ":" + nombreBarrio + ":" + hexa;
	    return toString;
	} else {
	    // return "No se pudo parsear el objeto.";
	    return "-1";
	}
    }
}