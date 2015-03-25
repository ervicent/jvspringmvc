package testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.IMarcaVehiculoDao;
import tesis.playon.web.model.MarcaVehiculo;

/**
 * @author Pablo
 * 
 */
public class TestMarcaVehiculo {

    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

	IMarcaVehiculoDao iMarca = (IMarcaVehiculoDao) appContext.getBean("marcaVehiculoDao");

	System.out.println("\n");
	/** insert **/
	MarcaVehiculo marca = new MarcaVehiculo("Subaru", null);
	MarcaVehiculo marca1 = new MarcaVehiculo("Lexus", null);
	iMarca.save(marca);
	iMarca.save(marca1);
	List<MarcaVehiculo> marcas = iMarca.findAll();
	System.out.println("Nuevas Marcas de vehiculos:");
	for (MarcaVehiculo marcaVehiculo : marcas) {
	    System.out.println(marcaVehiculo);
	}

	System.out.println("\n");
	/** select **/
	MarcaVehiculo marcaSolicitada = iMarca.findByNombreMarcaVehiculo("Ford");
	System.out.println("Marca buscada: \t" + marcaSolicitada);

	System.out.println("\n");
	/** update **/
	marca.setNombre("Ferrari");
	iMarca.update(marca);
	System.out.println("Marca Modificada:\t" + iMarca.findByNombreMarcaVehiculo("Ferrari"));

	System.out.println("\n");
	/** delete **/
	iMarca.delete(marca);
	iMarca.delete(marca1);
	marcas = iMarca.findAll();
	System.out.println("Marcas de vehiculos restantes:");
	for (MarcaVehiculo marcaVehiculo : marcas) {
	    System.out.println(marcaVehiculo);
	}

	System.out.println("\nListo!");
    }
}
