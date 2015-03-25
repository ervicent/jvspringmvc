package testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.IPaisDao;
import tesis.playon.web.dao.IProvinciaDao;
import tesis.playon.web.model.Pais;
import tesis.playon.web.model.Provincia;

/**
 * @author Pablo
 * 
 */
public class TestProvincia {

    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

	IProvinciaDao provinciaDao = (IProvinciaDao) appContext.getBean("provinciaDao");
	IPaisDao paisDao = (IPaisDao) appContext.getBean("paisDao");

	Pais pais = new Pais("Argentina");
	Pais pais1 = new Pais("Brasil");
	paisDao.save(pais);
	paisDao.save(pais1);

	System.out.println("\n");
	/** insert **/
	Provincia provincia = new Provincia("Cordoba", pais);
	Provincia provincia1 = new Provincia("Rio de Janeiro", pais1);
	provinciaDao.save(provincia);
	provinciaDao.save(provincia1);
	List<Provincia> provincias = provinciaDao.findAll();
	System.out.println("\nNuevos Paises:");
	for (Provincia provincia2 : provincias) {
	    System.out.println(provincia2);
	}

	System.out.println("\n");
	/** select **/
	Provincia otraProvincia = provinciaDao.findByNombreProvincia("Cordoba");
	System.out.println("\nProvincia encontrada:\t" + otraProvincia.toString());

	System.out.println("\n");
	/** update **/
	provincia.setNombre("Buenos Aires");
	provinciaDao.update(provincia);
	System.out.println("\nProvincia Modificada:\t" + provinciaDao.findByNombreProvincia("Buenos Aires"));

	System.out.println("\n");
	/** delete **/
	provinciaDao.delete(provincia);
	provinciaDao.delete(provincia1);
	provincias = provinciaDao.findAll();
	System.out.println("\nProvincias restantes:");
	for (Provincia _provincia : provincias) {
	    System.out.println(_provincia);
	}
	paisDao.delete(pais);
	paisDao.delete(pais1);

	System.out.println("\nListo!");
    }
}
