package testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.IEstadoPromocionDao;
import tesis.playon.web.model.EstadoPromocion;

/**
 * @author Gonza
 * 
 */

public class TestEstadoPromocion {
    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

	IEstadoPromocionDao estadoPromocionDao = (IEstadoPromocionDao) appContext.getBean("estadoPromocionDao");

	/** insert **/
	EstadoPromocion estado = new EstadoPromocion("Pendiente", "Esta pendiente de aprobacion");
	estadoPromocionDao.save(estado);
	EstadoPromocion estado1 = new EstadoPromocion("Publicado", "Esta publicado");
	estadoPromocionDao.save(estado1);
	List<EstadoPromocion> listaEstadoPromocion = estadoPromocionDao.findAll();
	for (EstadoPromocion ep : listaEstadoPromocion) {
	    System.out.println(ep.toString());
	}

	/** select **/
	EstadoPromocion estados = estadoPromocionDao.findByNombreEstadoPromocion("Publicado");
	System.out.println(estados);

	/** update **/
	estado.setNombre("Expirado");
	estadoPromocionDao.update(estado);
	System.out.println("Actualizado:\n");
	List<EstadoPromocion> listaEstadoPromocion1 = estadoPromocionDao.findAll();
	for (EstadoPromocion ep : listaEstadoPromocion1) {
	    System.out.println(ep.toString());
	}

	/** delete **/
	List<EstadoPromocion> listaEstadoPromocion2 = estadoPromocionDao.findAll();
	for (EstadoPromocion ep : listaEstadoPromocion2) {
	    estadoPromocionDao.delete(ep);
	}

	System.out.println("Listo pendejo");
    }
}
