package testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.ILocalidadDao;
import tesis.playon.web.dao.IPaisDao;
import tesis.playon.web.dao.IProvinciaDao;
import tesis.playon.web.model.Localidad;
import tesis.playon.web.model.Pais;
import tesis.playon.web.model.Provincia;

/**
 * @author Pablo
 * 
 */
public class TestLocalidad {

    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

	IProvinciaDao provinciaDao = (IProvinciaDao) appContext.getBean("provinciaDao");
	IPaisDao paisDao = (IPaisDao) appContext.getBean("paisDao");
	ILocalidadDao localidadDao = (ILocalidadDao) appContext.getBean("localidadDao");

	Pais pais = new Pais("Argentina");
	Pais pais1 = new Pais("Brasil");
	paisDao.save(pais);
	paisDao.save(pais1);
	Provincia provincia = new Provincia("Cordoba", pais);
	Provincia provincia1 = new Provincia("Rio de Janeiro", pais1);
	provinciaDao.save(provincia);
	provinciaDao.save(provincia1);

	System.out.println("\n");
	/** insert **/
	Localidad localidad = new Localidad("Rio IV", provincia);
	Localidad localidad1 = new Localidad("Florianopolis", provincia1);
	localidadDao.save(localidad);
	localidadDao.save(localidad1);
	List<Localidad> localidades = localidadDao.findAll();
	System.out.println("\nNuevos Localidades:");
	for (Localidad _localidad : localidades) {
	    System.out.println(_localidad);
	}

	System.out.println("\n");
	/** select **/
	Localidad otraLocalidad = localidadDao.findByNombreLocalidad("Rio IV");
	System.out.println("\nLocalidad encontrada:\t" + otraLocalidad.toString());

	System.out.println("\n");
	/** update **/
	localidad.setNombre("Calamuchita");
	localidadDao.update(localidad);
	System.out.println("\nLocalidad Modificada:\t" + localidadDao.findByNombreLocalidad("Calamuchita"));

	System.out.println("\n");
	/** delete **/
	localidadDao.delete(localidad);
	localidadDao.delete(localidad1);
	localidades = localidadDao.findAll();
	System.out.println("\nLocalidad restantes:");
	for (Localidad _localidad : localidades) {
	    System.out.println(_localidad);
	}

	provinciaDao.delete(provincia);
	provinciaDao.delete(provincia1);
	paisDao.delete(pais);
	paisDao.delete(pais1);

	System.out.println("\nListo!");
    }
}
