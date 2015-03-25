package tesis.playon.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import tesis.playon.web.model.MarcaVehiculo;

@FacesConverter(value = "marcaVehiculoConverter")
public class MarcaVehiculoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
	if (value != null) {
	    String toObject[] = value.split(":");
	    if (toObject.length == 2) {
		MarcaVehiculo marca = new MarcaVehiculo();
		marca.setId(Integer.parseInt(toObject[0].toString()));
		marca.setNombre(toObject[1].toString());
		return marca;
	    } else {
		throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_WARN,
			"Debe seleccionar una opción", null));
		// return null;
	    }
	}
	return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException{
	// if (value != null) {
	if (value instanceof MarcaVehiculo) {
	    MarcaVehiculo marca = (MarcaVehiculo) value;
	    String idMarca = Integer.toString(marca.getId());
	    String nombreMarca = marca.getNombre();
	    String toString = idMarca + ":" + nombreMarca;
	    return toString;
	}
	/**
	 * else { System.out.println("No se pudo parsear el objeto"); return null; }
	 */
	// }
	return null;
    }

}
