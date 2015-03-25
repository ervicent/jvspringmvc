/**
 * 
 */
package tesis.playon.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import tesis.playon.web.model.Localidad;
import tesis.playon.web.service.IProvinciaService;

/**
 * @author pablo
 *
 */
@FacesConverter(value = "localidadConverter")
public class LocalidadConverter implements Converter{

    IProvinciaService provinciaService;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	String toObject[] = value.split(":");
	Localidad localidad = new Localidad();
	localidad.setId(Integer.parseInt(toObject[0]));
	localidad.setNombre(toObject[1]);
	localidad.setProvincia(getProvinciaService().findByNombreProvincia(toObject[2]));
	return localidad;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value instanceof Localidad) {
	    Localidad localidad = (Localidad) value;
	    String idLocalidad = Integer.toString(localidad.getId());
	    String nombreLocalidad = localidad.getNombre();
	    String nombreProvincia = localidad.getProvincia().getNombre();
	    String toString = idLocalidad + ":" + nombreLocalidad + ":" + nombreProvincia;
	    return toString;
	} else {
	    return "No se pudo parsear el objeto.";
	}
    }

    public IProvinciaService getProvinciaService() {
        return provinciaService;
    }
    
}
