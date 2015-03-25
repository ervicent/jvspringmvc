/**
 * 
 */
package tesis.playon.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import tesis.playon.web.model.Provincia;
import tesis.playon.web.service.IPaisService;

/**
 * @author pablo
 *
 */
@FacesConverter(value = "provinciaConverter")
public class ProvinciaConvert implements Converter{

    IPaisService paisService;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	String toObject[] = value.split(":");
	Provincia provincia = new Provincia();
	provincia.setId(Integer.parseInt(toObject[0]));
	provincia.setNombre(toObject[1]);
	provincia.setPais(getPaisService().findByNombrePais(toObject[2]));
	return provincia;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value instanceof Provincia) {
	    Provincia provincia = (Provincia) value;
	    String idProvincia = Integer.toString(provincia.getId());
	    String nombreProvincia = provincia.getNombre();
	    String nombrePais = provincia.getPais().getNombre();
	    String toString = idProvincia + ":" + nombreProvincia + ":" + nombrePais;
	    return toString;
	} else {
	    return "No se pudo parsear el objeto.";
	}
    }

    public IPaisService getPaisService() {
        return paisService;
    }
    
}
