package tesis.playon.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import tesis.playon.web.model.EstadoDenuncia;

/**
 * 
 * @author pablo
 * 
 */
@FacesConverter(value = "estadoDenunciaConverter")
public class EstadoDenunciaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	// TODO Auto-generated method stub
	if (value != null) {
	    String toObject[] = value.split(":");
	    EstadoDenuncia estadoDenuncia = new EstadoDenuncia();
	    estadoDenuncia.setId(Integer.parseInt(toObject[0]));
	    estadoDenuncia.setNombre(toObject[1]);
	    return estadoDenuncia;
	}
	return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	// TODO Auto-generated method stub
	if (value != null) {
	    if (value instanceof EstadoDenuncia) {
		EstadoDenuncia estadoDenuncia = (EstadoDenuncia) value;
		String idEstado = Integer.toString(estadoDenuncia.getId());
		String nombreEstado = estadoDenuncia.getNombre();
		String toString = idEstado + ":" + nombreEstado;
		return toString;
	    } else {
		return "No se pudo parsear el objeto.";
	    }
	}
	return null;
    }

}
