package tesis.playon.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.model.MarcaVehiculo;
import tesis.playon.web.model.ModeloVehiculo;

@FacesConverter(value = "modeloVehiculoConverter")
public class ModeloVehiculoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
	if (value != null) {
	    String toObject[] = value.split(":");
	    if (toObject.length == 6) {
		ModeloVehiculo modelo = new ModeloVehiculo();
		modelo.setId(Integer.parseInt(toObject[0].toString()));
		modelo.setNombre(toObject[1].toString());

		MarcaVehiculo marca = new MarcaVehiculo();
		marca.setNombre(toObject[3].toString());
		marca.setId(Integer.parseInt(toObject[2].toString()));
		modelo.setMarcaVehiculo(marca);

		CategoriaVehiculo categoria = new CategoriaVehiculo();
		categoria.setId(Integer.parseInt(toObject[4].toString()));
		categoria.setNombre(toObject[5].toString());
		modelo.setCategoriaVehiculo(categoria);
		return modelo;
	    } else {
		throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_WARN,
			"Debe seleccionar una opción", null));
	    }
	}
	return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
	// if (value != null && !value.equals("")) {
	if (value instanceof ModeloVehiculo) {
	    ModeloVehiculo modelo = (ModeloVehiculo) value;
	    String idModelo = Integer.toString(modelo.getId());
	    String nombreModelo = modelo.getNombre();
	    String idMarca = Integer.toString(modelo.getMarcaVehiculo().getId());
	    String marca = modelo.getMarcaVehiculo().getNombre();
	    String idCategoria = Integer.toString(modelo.getCategoriaVehiculo().getId());
	    String nombreCategoria = modelo.getCategoriaVehiculo().getNombre();

	    String toString = idModelo + ":" + nombreModelo + ":" + idMarca + ":" + marca + ":" + idCategoria + ":"
		    + nombreCategoria;
	    return toString;
	}
	return null;
    }

}
