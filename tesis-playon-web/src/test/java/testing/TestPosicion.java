package testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.IPosicionDao;
import tesis.playon.web.model.Posicion;

/**
 * @author Pablo
 *
 */
public class TestPosicion {

    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
	IPosicionDao posicionDao = (IPosicionDao) appContext.getBean("posicionDao");

	/** insert **/
	Posicion posicion = new Posicion(10,1,1);
	posicion.setUbicacion("centro");
	posicionDao.save(posicion);
	List<Posicion> posiciones = posicionDao.findAll();
	
	for (Posicion pos : posiciones) {
	    System.out.println(pos);

	/** select **/
	Posicion otraPos = posicionDao.findByUbicacion("Centro");
	System.out.println("\nEncontrado:\t" + otraPos);
	
	/** update **/
	otraPos.setTamanioKBMax(20);
	otraPos.setTamanioX(100);
	otraPos.setTamanioY(200);
	otraPos.setUbicacion("Arriba");
	
	posicionDao.update(otraPos);
	System.out.println("\nModificada:\t" + posicionDao.findByUbicacion("Arriba"));
	
	System.out.println("\n");
	/** delete **/
	posicionDao.delete(otraPos);
	posicionDao.delete(posicion);
	
	System.out.println("\nListo!");
	}
    }
}
