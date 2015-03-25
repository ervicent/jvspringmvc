package testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.IEstadoDenunciaDao;
import tesis.playon.web.model.EstadoDenuncia;

/**
 * @author Pablo
 *
 */
public class TestEstadoDenuncia {
    
    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

	IEstadoDenunciaDao iEstado = (IEstadoDenunciaDao)appContext.getBean("estadoDenunciaDao");
	
	System.out.println("\n");
	/** insert **/
	EstadoDenuncia estado = new EstadoDenuncia("Pendiente", null);
	EstadoDenuncia estado1 = new EstadoDenuncia("Aprobado", null);
	iEstado.save(estado);
	iEstado.save(estado1);
	List<EstadoDenuncia> estados = iEstado.findAll();
	System.out.println("Nuevos Estados de denuncias:");
	for (EstadoDenuncia estadosDenuncia : estados) {
	    System.out.println(estadosDenuncia);
	}
	
	System.out.println("\n");	
	/** select **/
	EstadoDenuncia estadoSolicitado = iEstado.findByNombreEstadoDenuncia("Pendiente");
	System.out.println("Estado de denuncia buscado: \t" + estadoSolicitado);
	
	System.out.println("\n");
	/** update **/
	estado.setNombre("Cancelado");
	iEstado.update(estado);
	System.out.println("Estado de denuncia Modificado:\t" + iEstado.findByNombreEstadoDenuncia("Cancelado"));

	System.out.println("\n");
	/** delete **/
	iEstado.delete(estado);
	iEstado.delete(estado1);
	estados = iEstado.findAll();
	System.out.println("Marcas de vehiculos restantes:");
	for (EstadoDenuncia estadoDenuncia : estados) {
	    System.out.println(estadoDenuncia);
	}

	System.out.println("\nListo!");
    }

}
