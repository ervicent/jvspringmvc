/**
 * 
 */
package testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.IEstadiaDao;
import tesis.playon.web.dao.IPlayaDao;
import tesis.playon.web.model.Estadia;
import tesis.playon.web.model.Playa;

/**
 * @author Pablo
 *
 */
public class TestEstadia {
    
    public static void main(String[] args) {

	/** IMPLEMENTAR LA CLASE PLAYA **/

	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

	IPlayaDao playaDao = (IPlayaDao) appContext.getBean("playaDao");
	Playa playa = playaDao.findByNombreComercial("Ituzaingo");
	Playa playa1 = playaDao.findByNombreComercial("Alvear");

	IEstadiaDao estadiaDao = (IEstadiaDao) appContext.getBean("estadiaDao");

	/** insert **/
	Estadia estadia = new Estadia(playa);
	Estadia estadia1 = new Estadia(playa1);
	estadiaDao.save(estadia);
	estadiaDao.save(estadia1);
	List<Estadia> estadias = estadiaDao.findAll();
	System.out.println("\nEstadias:");
	for (Estadia _estadia : estadias) {
	    System.out.println(_estadia);
	}

	/** select **/
//	Estadia otraEstadia = estadiaDao.findByPlaya(playa);
//	System.out.println("\nEncontrado:\t" + otraEstadia);


	System.out.println("\n");
	/** delete **/
	estadiaDao.delete(estadia);
	estadiaDao.delete(estadia1);
	estadias = estadiaDao.findAll();
	System.out.println("\nRestantes:");
	for (Estadia _estadia : estadias) {
	    System.out.println(_estadia);
	}

	System.out.println("\nListo!");
    }
}
