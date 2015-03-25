package testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.ICategoriaVehiculoDao;
import tesis.playon.web.model.CategoriaVehiculo;
/**
 * @author Pablo
 *
 */
public class TestCategoriaVehiculo {

    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

	ICategoriaVehiculoDao iCategoria = (ICategoriaVehiculoDao)appContext.getBean("categoriaVehiculoDao");
	
	System.out.println("\n");
	/** insert **/
	CategoriaVehiculo categoria = new CategoriaVehiculo("Auto", "Ingresan en la categoria de autos");
	CategoriaVehiculo categoria1 = new CategoriaVehiculo("Pick-Up", "Ingresan en la categoria de camionetas");
	iCategoria.save(categoria);
	iCategoria.save(categoria1);
	List<CategoriaVehiculo> categorias = iCategoria.findAll();
	System.out.println("Nuevas Categoria de vehiculos:");
	for (CategoriaVehiculo categoriaVehiculo : categorias) {
	    System.out.println(categoriaVehiculo);
	}
	
	System.out.println("\n");	
	/** select **/
	CategoriaVehiculo categoriaSolicitada = iCategoria.findByNombreCategoriaVehiculo("Auto");
	System.out.println("Categoria buscada: \t" + categoriaSolicitada);
	
	System.out.println("\n");
	/** update **/
	categoria.setNombre("Moto");
	categoria.setDescripcion("Ingresan en la categoria todo tipo de motos");
	iCategoria.update(categoria);
	System.out.println("Categoria Modificada:\t" + iCategoria.findByNombreCategoriaVehiculo("Moto"));

	System.out.println("\n");
	/** delete **/
	iCategoria.delete(categoria);
	iCategoria.delete(categoria1);
	categorias = iCategoria.findAll();
	System.out.println("Categoria de vehiculos restantes:");
	for (CategoriaVehiculo categoriaVehiculo : categorias) {
	    System.out.println(categoriaVehiculo);
	}

	System.out.println("\nListo!");
    }
}
