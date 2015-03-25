package tesis.playon.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import tesis.playon.web.model.Pais;

@FacesConverter(value = "paisConverter")
public class PaisConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	String toObject[] = value.split(":");
	Pais pais = new Pais();
	pais.setId(Integer.parseInt(toObject[0]));
	pais.setNombre(toObject[1]);
	return pais;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value instanceof Pais) {
	    Pais pais = (Pais) value;
	    String idPais = Integer.toString(pais.getId());
	    String nombrePais = pais.getNombre();
	    String toString = idPais + ":" + nombrePais;
	    return toString;
	} else {
	    return "No se pudo parsear el objeto.";
	}
    }

}
