package tesis.playon.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang.StringUtils;

import tesis.playon.web.model.Promocion;

/**
 * 
 * @author pablo
 * 
 */
@FacesConverter(value = "promocionConverter")
public class PromocionConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
	if (!StringUtils.isEmpty(value)) {
	    String toObject[] = value.split(":");
	    Promocion promocion = new Promocion();
	    if (toObject.length != 3) {
		return null;
	    } else {
		promocion.setId(Integer.parseInt(toObject[0]));
		promocion.setNombre(toObject[1]);
		promocion.setDescuento(Float.parseFloat(toObject[2]));
		return promocion;
	    }
	}
	return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
	if (value != null && !value.equals("")) {
	    if (value instanceof Promocion) {
		Promocion promocion = (Promocion) value;
		String idPromocion = Integer.toString(promocion.getId());
		String nombrePromocion = promocion.getNombre();
		String descuento = Float.toString(promocion.getDescuento());
		String toString = idPromocion + ":" + nombrePromocion + ":" + descuento;
		return toString;
	    } else {
		System.out.println("No se pudo parsear el objeto");
		return null;
	    }
	}
	return null;
    }

}
