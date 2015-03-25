package testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.IMarcaVehiculoDao;
import tesis.playon.web.dao.IModeloVehiculoDao;
import tesis.playon.web.model.MarcaVehiculo;
import tesis.playon.web.model.ModeloVehiculo;

/**
 * @author Pablo
 * 
 */
public class TestModeloVehiculo {

    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

	IModeloVehiculoDao iModelo = (IModeloVehiculoDao) appContext.getBean("modeloVehiculoDao");
	IMarcaVehiculoDao iMarca = (IMarcaVehiculoDao) appContext.getBean("marcaVehiculoDao");

	System.out.println("\n");
	/** insert **/
	MarcaVehiculo marca = iMarca.findByNombreMarcaVehiculo("Citroen");

	ModeloVehiculo modelo = new ModeloVehiculo(null, marca, "Picasso");
	ModeloVehiculo modelo1 = new ModeloVehiculo(null, marca, "C4");
	iModelo.save(modelo);
	iModelo.save(modelo1);
	List<ModeloVehiculo> modelos = iModelo.findAll();
	System.out.println("Nuevos Modelos de vehiculos:");
	for (ModeloVehiculo modeloVehiculo : modelos) {
	    System.out.println(modeloVehiculo);
	}

	System.out.println("\n");
	/** select **/
	ModeloVehiculo modeloSolicitado = iModelo.findByNombreModeloVehiculo("C4");
	System.out.println("Modelo buscado: \t" + modeloSolicitado);

	System.out.println("\n");
	/** update **/
	modelo.setNombre("Gran Picasso");
	iModelo.update(modelo);
	System.out.println("Modelo Modificado:\t" + iModelo.findByNombreModeloVehiculo("Gran Picasso"));

	System.out.println("\n");
	/** delete **/
	iModelo.delete(modelo);
	iModelo.delete(modelo1);
	modelos = iModelo.findAll();
	System.out.println("Modelos de vehiculos restantes:");
	for (ModeloVehiculo modeloVehiculo : modelos) {
	    System.out.println(modeloVehiculo);
	}

	System.out.println("\nListo!");
    }

}
