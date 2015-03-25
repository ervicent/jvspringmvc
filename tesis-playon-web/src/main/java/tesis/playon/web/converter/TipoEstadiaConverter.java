package tesis.playon.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang.StringUtils;

import tesis.playon.web.model.TipoEstadia;

/**
 * 
 * @author Pablo
 * 
 */
@FacesConverter(value = "tipoEstadiaConverter")
public class TipoEstadiaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	if (!StringUtils.isEmpty(value)) {
	    String toObject[] = value.split(":");
	    if (toObject.length != 2) {
//		TipoEstadia te= new TipoEstadia("Todas",null);
//		te.setId(0);
		return null;
	    } else {
		TipoEstadia tipoEstadia = new TipoEstadia();
		tipoEstadia.setId(Integer.parseInt(toObject[0]));
		tipoEstadia.setNombre(toObject[1]);
		return tipoEstadia;
	    }
	}
	return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value != null && !value.equals("")) {
	    if (value instanceof TipoEstadia) {
		TipoEstadia tipoEstadia = (TipoEstadia) value;
		String idTipoEstadia = Integer.toString(tipoEstadia.getId());
		String nombreTipoEstadia = tipoEstadia.getNombre();
		String toString = idTipoEstadia + ":" + nombreTipoEstadia;
		return toString;
	    } else {
		System.out.println("No se pudo parsear el objeto");
		return null;
	    }
	}
	return null;
    }
}