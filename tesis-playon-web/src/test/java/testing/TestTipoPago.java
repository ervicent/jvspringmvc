package testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.ITipoPagoDao;
import tesis.playon.web.model.TipoPago;

/**
 * @author Pablo
 *
 */
public class TestTipoPago {

    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
	ITipoPagoDao tipoPagoDao = (ITipoPagoDao) appContext.getBean("tipoPagoDao");

	/** insert **/
	TipoPago tipoPago = new TipoPago("Sistema Playon", null);
	TipoPago tipoPago1 = new TipoPago("Efectivo", null);
	tipoPagoDao.save(tipoPago);
	tipoPagoDao.save(tipoPago1);
	List<TipoPago> tipos = tipoPagoDao.findAll();
	System.out.println("\nTipos de Pago:");
	for (TipoPago tipo : tipos) {
	    System.out.println(tipo);
	}

	/** select **/
	TipoPago otroTipo = tipoPagoDao.findByNameTipoPago("Efectivo");
	System.out.println("\nEncontrado:\t" + otroTipo);
	
	/** update **/
	otroTipo.setNombre("Tarjeta");
	tipoPagoDao.update(otroTipo);
	System.out.println("\nModificado:\t" + tipoPagoDao.findByNameTipoPago("Tarjeta"));
	
	System.out.println("\n");
	/** delete **/
	tipoPagoDao.delete(tipoPago);
	tipoPagoDao.delete(tipoPago1);
	tipos = tipoPagoDao.findAll();
	System.out.println("\nRestantes:");
	for (TipoPago tipo : tipos) {
	    System.out.println(tipo);
	}

	System.out.println("\nListo!");
    }
}
