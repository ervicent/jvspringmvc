package tesis.playon.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import tesis.playon.web.model.TipoPago;

@FacesConverter(value = "tipoPagoConverter")
public class TipoPagoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	String toObject[] = value.split(":");
	TipoPago pais = new TipoPago();
	pais.setId(Integer.parseInt(toObject[0]));
	pais.setNombre(toObject[1]);
	return pais;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value instanceof TipoPago) {
	    TipoPago tipo = (TipoPago) value;
	    String idTipo = Integer.toString(tipo.getId());
	    String nombreTipo = tipo.getNombre();
	    String toString = idTipo + ":" + nombreTipo;
	    return toString;
	} else {
	    return "No se pudo parsear el objeto.";
	}
    }

}
