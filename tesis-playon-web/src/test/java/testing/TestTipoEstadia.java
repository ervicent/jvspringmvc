package testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.ITipoEstadiaDao;
import tesis.playon.web.model.TipoEstadia;

/**
 * @author Pablo
 * 
 */
public class TestTipoEstadia {

    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
	ITipoEstadiaDao tipoEstadiaDao = (ITipoEstadiaDao) appContext.getBean("tipoEstadiaDao");

	/** insert **/
	TipoEstadia tipoEstadia = new TipoEstadia("Por hora", null);
	TipoEstadia tipoEstadia1 = new TipoEstadia("mensual", null);
	tipoEstadiaDao.save(tipoEstadia);
	tipoEstadiaDao.save(tipoEstadia1);
	List<TipoEstadia> tipos = tipoEstadiaDao.findAll();
	System.out.println("\nTipos de Estadia:");
	for (TipoEstadia tipo : tipos) {
	    System.out.println(tipo);
	}

	/** select **/
	TipoEstadia otroTipo = tipoEstadiaDao.findByNombreTipoEstadia("mensual");
	System.out.println("\nEncontrado:\t" + otroTipo);

	/** update **/
	otroTipo.setNombre("Nocturna");
	tipoEstadiaDao.update(otroTipo);
	System.out.println("\nModificado:\t" + tipoEstadiaDao.findByNombreTipoEstadia("Nocturna"));

	System.out.println("\n");
	/** delete **/
	tipoEstadiaDao.delete(tipoEstadia);
	tipoEstadiaDao.delete(tipoEstadia1);
	tipos = tipoEstadiaDao.findAll();
	System.out.println("\nRestantes:");
	for (TipoEstadia tipo : tipos) {
	    System.out.println(tipo);
	}

	System.out.println("\nListo!");
    }
}