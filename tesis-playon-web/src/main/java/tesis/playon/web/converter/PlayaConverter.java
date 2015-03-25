/**
 * 
 */
package tesis.playon.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import tesis.playon.web.model.Playa;
import tesis.playon.web.service.IPlayaService;

/**
 * @author Pablo
 *
 */
@FacesConverter(value = "playaConverter")
public class PlayaConverter implements Converter{

	IPlayaService playaService;
	
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
    String toObject[] = value.split(":");
	Playa playa = new Playa();
	playa.setId(Integer.parseInt(toObject[0]));
	playa.setNombreComercial(toObject[1]);
	return playa;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value instanceof Playa) {
	    Playa playa = (Playa) value;
	    String idPlaya = Integer.toString(playa.getId());
	    String nombrePlaya = playa.getNombreComercial();
	    String toString = idPlaya + ":" + nombrePlaya;
	    return toString;
	} else {
	    return "No se pudo parsear el objeto.";
	}
    }
}