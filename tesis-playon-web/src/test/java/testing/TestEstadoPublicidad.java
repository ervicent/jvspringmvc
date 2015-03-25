package testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.IEstadoPublicidadDao;
import tesis.playon.web.model.EstadoPublicidad;

/**
 * @author Gonza
 * 
 */

public class TestEstadoPublicidad {
    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

	IEstadoPublicidadDao estadoPublicidadDao = (IEstadoPublicidadDao) appContext.getBean("estadoPublicidadDao");

	/** insert **/
	EstadoPublicidad estado = new EstadoPublicidad("Pendiente", "Esta pendiente de aprobacion");
	estadoPublicidadDao.save(estado);
	EstadoPublicidad estado1 = new EstadoPublicidad("Publicado", "Esta publicado");
	estadoPublicidadDao.save(estado1);
	List<EstadoPublicidad> listaEstadoPublicidad = estadoPublicidadDao.findAll();
	for (EstadoPublicidad ep : listaEstadoPublicidad) {
	    System.out.println(ep.toString());
	}

	/** select **/
	EstadoPublicidad estados = estadoPublicidadDao.findByNombreEstadoPublicidad("Publicado");
	System.out.println(estados);

	/** update **/
	estado.setNombre("Expirado");
	estadoPublicidadDao.update(estado);
	System.out.println("Actualizado:\n");
	List<EstadoPublicidad> listaEstadoPublicidad1 = estadoPublicidadDao.findAll();
	for (EstadoPublicidad ep : listaEstadoPublicidad1) {
	    System.out.println(ep.toString());
	}

	/** delete **/
	List<EstadoPublicidad> listaEstadoPublicidad2 = estadoPublicidadDao.findAll();
	for (EstadoPublicidad ep : listaEstadoPublicidad2) {
	    estadoPublicidadDao.delete(ep);
	}

	System.out.println("Listo pendejo");
    }
}
