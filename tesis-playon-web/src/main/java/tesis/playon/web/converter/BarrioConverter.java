package tesis.playon.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import tesis.playon.web.model.Barrio;

@FacesConverter(value = "barrioConverter")
public class BarrioConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	String toObject[] = value.split(":");
	Barrio barrio = new Barrio();
	barrio.setId(Integer.parseInt(toObject[0]));
	barrio.setNombre(toObject[1]);
	return barrio;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value instanceof Barrio) {
	    Barrio barrio = (Barrio) value;
	    String idBarrio = Integer.toString(barrio.getId());
	    String nombreBarrio = barrio.getNombre();
	    String toString = idBarrio + ":" + nombreBarrio;
	    return toString;
	} else {
	    return "No se pudo parsear el objeto.";
	}
    }
}