
package tesis.playon.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import tesis.playon.web.model.EstadoPromocion;
/**
 * 
 * @author pablo
 *
 */
@FacesConverter(value="estadoPromocionConverter")
public class EstadoPromocionConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	// TODO Auto-generated method stub
	if (value != null) {
	    String toObject[] = value.split(":");
	    EstadoPromocion estadoPromocion = new EstadoPromocion();
	    estadoPromocion.setId(Integer.parseInt(toObject[0]));
	    estadoPromocion.setNombre(toObject[1]);
	    return estadoPromocion;
	}
	return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	// TODO Auto-generated method stub
	if (value != null) {
	    if (value instanceof EstadoPromocion) {
		EstadoPromocion estadoPromocion = (EstadoPromocion) value;
		String idEstado = Integer.toString(estadoPromocion.getId());
		String nombreEstado = estadoPromocion.getNombre();
		String toString = idEstado + ":" + nombreEstado;
		return toString;
	    } else {
		return "No se pudo parsear el objeto.";
	    }
	}
	return null;
    }

}
