package testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.IBarrioDao;
import tesis.playon.web.dao.ILocalidadDao;
import tesis.playon.web.dao.IPaisDao;
import tesis.playon.web.dao.IProvinciaDao;
import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.Localidad;
import tesis.playon.web.model.Pais;
import tesis.playon.web.model.Provincia;

/**
 * @author Pablo
 *
 */
public class TestBarrio {

    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

	IProvinciaDao provinciaDao=(IProvinciaDao)appContext.getBean("provinciaDao");
	IPaisDao paisDao = (IPaisDao) appContext.getBean("paisDao");
	ILocalidadDao localidadDao = (ILocalidadDao) appContext.getBean("localidadDao");
	IBarrioDao barrioDao = (IBarrioDao) appContext.getBean("barrioDao");
	
	Pais pais = new Pais("Argentina");
	Pais pais1 = new Pais("Brasil");
	paisDao.save(pais);
	paisDao.save(pais1);
	Provincia provincia = new Provincia("Cordoba", pais);
	Provincia provincia1 = new Provincia("Rio de Janeiro", pais1);
	provinciaDao.save(provincia);
	provinciaDao.save(provincia1);
	Localidad localidad = new Localidad("Rio IV",provincia);
	Localidad localidad1 = new Localidad("Florianopolis",provincia1);
	localidadDao.save(localidad);
	localidadDao.save(localidad1);
	
	
	System.out.println("\n");
	/** insert **/
	Barrio barrio = new Barrio("Santa Ana",localidad);
	Barrio barrio1 = new Barrio("Santa Isabel",localidad1);
	barrioDao.save(barrio);
	barrioDao.save(barrio1);
	List<Barrio> barrios = barrioDao.findAll();
	System.out.println("\nNuevos Barrios:");
	for (Barrio _barrio : barrios) {
	    System.out.println(_barrio);
	}
	
	System.out.println("\n");	
	/** select **/
	Barrio otroBarrio = barrioDao.findByNombreBarrio("Santa Ana");
	System.out.println("\nBarrio encontrado:\t" + otroBarrio.toString());

	
	System.out.println("\n");
	/** update **/
	barrio.setNombre("San Martin");
	barrioDao.update(barrio);
	System.out.println("\nBarrio Modificado:\t" + barrioDao.findByNombreBarrio("San Martin"));


	System.out.println("\n");
	/** delete **/
	barrioDao.delete(barrio);
	barrioDao.delete(barrio1);
	barrios= barrioDao.findAll();
	System.out.println("\nBarrios restantes:");
	for (Barrio _barrio : barrios) {
	    System.out.println(_barrio);
	}
	
	localidadDao.delete(localidad);
	localidadDao.delete(localidad1);
	provinciaDao.delete(provincia);
	provinciaDao.delete(provincia1);
	paisDao.delete(pais);
	paisDao.delete(pais1);

	
	System.out.println("\nListo!");
    }
}
