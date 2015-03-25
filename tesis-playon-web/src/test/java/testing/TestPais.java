package testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.IPaisDao;
import tesis.playon.web.model.Pais;

/**
 * @author Pablo
 * 
 */
public class TestPais {

    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
	// Test para Pais
	IPaisDao paisDao = (IPaisDao) appContext.getBean("paisDao");

	/** insert **/
	Pais pais = new Pais("Argentina");
	Pais pais1 = new Pais("Brasil");
	pais.setNombre("Argentina");
	paisDao.save(pais);
	paisDao.save(pais1);
	List<Pais> paises = paisDao.findAll();
	System.out.println("\nNuevos Paises:");
	for (Pais pais2 : paises) {
	    System.out.println(pais2);
	}

	/** select **/
	Pais otroPais = paisDao.findByNombrePais("Argentina");
	System.out.println("\nPais encontrado:\t" + otroPais);

	/** update **/
	otroPais.setNombre("Republica Argentina");
	paisDao.update(otroPais);
	System.out.println("\nPais Modificado:\t" + paisDao.findByNombrePais("Republica Argentina"));

	System.out.println("\n");
	/** delete **/
	paisDao.delete(pais);
	paisDao.delete(pais1);
	paises = paisDao.findAll();
	System.out.println("\nPaises restantes:");
	for (Pais _pais : paises) {
	    System.out.println(_pais);
	}

	System.out.println("\nListo!");
    }
}
