/**
 * 
 */
package tesis.playon.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang.StringUtils;

import tesis.playon.web.model.CategoriaVehiculo;

/**
 * @author Pablo
 * 
 */
@FacesConverter(value = "categoriaVehiculoConverter")
public class CategoriaVehiculoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	if (!StringUtils.isEmpty(value)) {
	    String toObject[] = value.split(":");
	    if (toObject.length != 2) {
//		CategoriaVehiculo cv = new CategoriaVehiculo("Todas",null);
//		cv.setId(0);
		return null;
	    } else {
		CategoriaVehiculo categoriaVehiculo = new CategoriaVehiculo();
		categoriaVehiculo.setId(Integer.parseInt(toObject[0]));
		categoriaVehiculo.setNombre(toObject[1]);
		return categoriaVehiculo;
	    }
	}
	return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value != null && !value.equals("")) {
	    if (value instanceof CategoriaVehiculo) {
		CategoriaVehiculo categoriaVehiculo = (CategoriaVehiculo) value;
		String idCategoriaVehiculo = Integer.toString(categoriaVehiculo.getId());
		String nombreCategoriaVehiculo = categoriaVehiculo.getNombre();
		String toString = idCategoriaVehiculo + ":" + nombreCategoriaVehiculo;
		return toString;
	    } else {
		System.out.println("No se pudo parsear el objeto");
		return null;
	    }
	}
	return null;
    }

}
