package testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.IEstadoPlayaDao;
import tesis.playon.web.dao.IPlayaDao;
import tesis.playon.web.model.EstadoPlaya;
import tesis.playon.web.model.Playa;

/**
 * @author Pablo
 * 
 */
public class TestPlaya {

    public static void main(String[] args) {

	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

	IEstadoPlayaDao estadoPlayaDao = (IEstadoPlayaDao) appContext.getBean("estadoPlayaDao");
	EstadoPlaya estado = estadoPlayaDao.findByNombreEstadoPlaya("Pendiente");
	EstadoPlaya estado1 = estadoPlayaDao.findByNombreEstadoPlaya("Aprobada");

	/** TEST PLAYA DE ESTACIONAMIENTO **/
	IPlayaDao playaDao = (IPlayaDao) appContext.getBean("playaDao");

	/** insert **/
	Playa playa = new Playa("Ituzaingo Parking", estado);
	Playa playa1 = new Playa("Nva Cba Parking", estado1);
	playaDao.save(playa);
	playaDao.save(playa1);
	List<Playa> playas = playaDao.findAll();
	System.out.println("\nPlayas:");
	for (Playa _playa : playas) {
	    System.out.println(_playa);
	}

	/** select **/
	Playa otraPlaya = playaDao.findByNombreComercial("Ituzaingo Parking");
	System.out.println("\n");
	System.out.println("\nEncontrada:\t" + otraPlaya);

	/** update **/
	playa.setNombreComercial("Playon");
	playaDao.update(playa);
	System.out.println("\n");
	System.out.println("\nModificado:\t" + playaDao.findByNombreComercial("Playon"));

	System.out.println("\n");
	/** delete **/
	playaDao.delete(playa);
	playaDao.delete(playa1);
	playas = playaDao.findAll();
	System.out.println("\n");
	System.out.println("\nRestantes:");
	for (Playa _playa : playas) {
	    System.out.println(_playa);
	}

	System.out.println("\nListo!");
    }
}
