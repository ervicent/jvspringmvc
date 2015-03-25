package testing;

//import java.util.List;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.ICategoriaVehiculoDao;
import tesis.playon.web.dao.IPlayaDao;
import tesis.playon.web.dao.ITarifaDao;
import tesis.playon.web.dao.ITipoEstadiaDao;
import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Tarifa;
import tesis.playon.web.model.TipoEstadia;

/**
 * @author Pablo
 * 
 */
public class TestTarifa {

    public static void main(String[] args) {

	/** IMPLEMENTAR LA CLASE PLAYA **/

	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

	IPlayaDao playaDao = (IPlayaDao) appContext.getBean("playaDao");
	ICategoriaVehiculoDao categoriaDao = (ICategoriaVehiculoDao) appContext.getBean("categoriaVehiculoDao");
	ITipoEstadiaDao tipoEstadiaDao = (ITipoEstadiaDao) appContext.getBean("tipoEstadiaDao");

	TipoEstadia tipoEstadia = tipoEstadiaDao.findByNombreTipoEstadia("Por Hora");
	CategoriaVehiculo categoriaVehiculo = categoriaDao.findByNombreCategoriaVehiculo("Auto");
	Playa playa = playaDao.findByNombreComercial("Ituzaingo");

	ITarifaDao tarifaDao = (ITarifaDao) appContext.getBean("tarifaDao");

	/** insert **/
	Tarifa tarifa = new Tarifa((float) 10.50, playa, tipoEstadia, categoriaVehiculo);
	Tarifa tarifa1 = new Tarifa((float) 6.50, playa, tipoEstadia, categoriaVehiculo);
	tarifaDao.save(tarifa);
	tarifaDao.save(tarifa1);
	List<Tarifa> tarifas = tarifaDao.findAll();
	System.out.println("\nTarifas:");
	for (Tarifa _tarifa : tarifas) {
	    System.out.println(_tarifa);
	}

	/** select **/
//	Tarifa otraTarifa = tarifaDao.findByPlayaID(playa);
//	System.out.println("\nEncontrado:\t" + otraTarifa);

	/** update **/
	categoriaVehiculo = categoriaDao.findByNombreCategoriaVehiculo("Moto");
	tarifa.setCategoriaVehiculo(categoriaVehiculo);
	tarifaDao.update(tarifa);
//	System.out.println("\nModificado:\t" + tarifaDao.findByPlayaID(playa));

	System.out.println("\n");
	/** delete **/
	tarifaDao.delete(tarifa);
	tarifaDao.delete(tarifa1);
	tarifas = tarifaDao.findAll();
	System.out.println("\nRestantes:");
	for (Tarifa _tarifa : tarifas) {
	    System.out.println(_tarifa);
	}

	System.out.println("\nListo!");
    }
}
