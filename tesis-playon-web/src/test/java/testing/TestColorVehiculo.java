package testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.IColorVehiculoDao;
import tesis.playon.web.model.ColorVehiculo;

/**
 * @author Pablo
 *
 */
public class TestColorVehiculo {

    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

	IColorVehiculoDao colorVehiculoDao = (IColorVehiculoDao)appContext.getBean("colorVehiculoDao");
	
	/** insert **/
	ColorVehiculo cv = new ColorVehiculo("Rojo");
	ColorVehiculo cv1 = new ColorVehiculo("Azul");
	colorVehiculoDao.save(cv);
	colorVehiculoDao.save(cv1);
	System.out.println("\nNuevos Colores:\n");
	List<ColorVehiculo> colores=colorVehiculoDao.findAll();
	for (ColorVehiculo colorVehiculo : colores) {
	    System.out.println(colorVehiculo.toString());
	}
	
	/** select **/
	ColorVehiculo cv2 = colorVehiculoDao.findByNombreColorVehiculo("Azul");
	System.out.println("\nResultado de la busqueda: \t" + cv2.toString());

	
	/** update **/
	cv.setNombre("Amarillo");
	colorVehiculoDao.update(cv);
	cv2 = colorVehiculoDao.findByNombreColorVehiculo("Amarillo");
	System.out.println("\nActualizado: \t" + cv2.toString());
	
	/** delete **/
	colorVehiculoDao.delete(cv);
	colorVehiculoDao.delete(cv1);
	colores=colorVehiculoDao.findAll();
	System.out.println("\nColores Restantes:\n");
	for (ColorVehiculo colorVehiculo : colores) {
	    System.out.println(colorVehiculo.toString());
	}
	    
	System.out.println("Listo!");
    }
}