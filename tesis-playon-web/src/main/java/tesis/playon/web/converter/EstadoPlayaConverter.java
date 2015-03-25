package tesis.playon.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import tesis.playon.web.model.EstadoPlaya;

@FacesConverter(value = "estadoPlayaConverter")
public class EstadoPlayaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	String toObject[] = value.split(":");
	EstadoPlaya estado = new EstadoPlaya();
	estado.setId(Integer.parseInt(toObject[0]));
	estado.setNombre(toObject[1]);
	return estado;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value instanceof EstadoPlaya) {
	    EstadoPlaya estado = (EstadoPlaya) value;
	    String idEstado = Integer.toString(estado.getId());
	    String nombreEstado = estado.getNombre();
	    String toString = idEstado + ":" + nombreEstado;
	    return toString;
	} else {
	    return "No se pudo parsear el objeto.";
	}
    }
}