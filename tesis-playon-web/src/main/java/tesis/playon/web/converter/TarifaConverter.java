package tesis.playon.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang.StringUtils;

import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.model.Tarifa;
import tesis.playon.web.model.TipoEstadia;

/**
 * 
 * @author gmorales
 * 
 */
@FacesConverter(value = "tarifaConverter")
public class TarifaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
	if (!StringUtils.isEmpty(value)) {
	    String toObject[] = value.split(":");
	    if (toObject.length != 5) {
		throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_WARN,
			"Debe seleccionar una opción", null));
		 //return null;
	    } else {
		Tarifa tarifa = new Tarifa();
		tarifa.setId(Integer.parseInt(toObject[0]));
		tarifa.setImporte(Float.parseFloat(toObject[1]));
		CategoriaVehiculo categoria = new CategoriaVehiculo();
		categoria.setId(Integer.parseInt(toObject[2].toString()));
		categoria.setNombre(toObject[3].toString());
		tarifa.setCategoriaVehiculo(categoria);
		TipoEstadia tipo = new TipoEstadia();
		tipo.setNombre(toObject[4].toString());
		tarifa.setTipoEstadia(tipo);
		return tarifa;
	    }
	}
	return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value != null && !value.equals("")) {
	    if (value instanceof Tarifa) {
		Tarifa tarifa = (Tarifa) value;
		String idTarifa = Integer.toString(tarifa.getId());
		String importeTarifa = Float.toString(tarifa.getImporte());
		String idCategoria = tarifa.getCategoriaVehiculo().getId().toString();
		String nombreCategoria = tarifa.getCategoriaVehiculo().getNombre();
		//String idTipoEstadia = tarifa
		String tipoEstadia = tarifa.getTipoEstadia().getNombre();
		String toString = idTarifa + ":" + importeTarifa + ":" + idCategoria + ":" + nombreCategoria + ":" + tipoEstadia;
		return toString;
	    } else {
		System.out.println("No se pudo parsear el objeto");
		return null;
	    }
	}
	return null;
    }
}