package testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.IEstadoPlayaDao;
import tesis.playon.web.model.EstadoPlaya;

/**
 * @author Pablo
 *
 */
public class TestEstadoPlaya {

    public static void main(String[] args) {
	
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
	
	/** TEST ESTADO PLAYA DE ESTACIONAMIENTO **/
	IEstadoPlayaDao estadoPlayaDao = (IEstadoPlayaDao) appContext.getBean("estadoPlayaDao");

	/** insert **/
	EstadoPlaya estadoPlaya = new EstadoPlaya("Habilitada", "Esta habilitada");
	EstadoPlaya estadoPlaya1 = new EstadoPlaya("Inhabilitada", "Esta habilitada");
	estadoPlayaDao.save(estadoPlaya);
	estadoPlayaDao.save(estadoPlaya1);
	List<EstadoPlaya> estados = estadoPlayaDao.findAll();
	System.out.println("\nEstados de Playa:");
	for (EstadoPlaya _estado : estados) {
	    System.out.println(_estado);
	}

	/** select **/
	EstadoPlaya otroEstado = estadoPlayaDao.findByNombreEstadoPlaya("Pendiente");
	System.out.println("\nEncontrado:\t" + otroEstado);
	
	/** update **/
	estadoPlaya.setNombre("Rechazada");
	estadoPlayaDao.update(estadoPlaya);
	System.out.println("\n");
	System.out.println("\nModificado:\t" + estadoPlayaDao.findByNombreEstadoPlaya("Rechazada"));
	
	System.out.println("\n");
	/** delete **/
	estadoPlayaDao.delete(estadoPlaya);
	estadoPlayaDao.delete(estadoPlaya1);
	estados = estadoPlayaDao.findAll();
	System.out.println("\n");
	System.out.println("\nRestantes:");
	for (EstadoPlaya _estado : estados) {
	    System.out.println(_estado);
	}

	System.out.println("\nListo!");
    }
}
