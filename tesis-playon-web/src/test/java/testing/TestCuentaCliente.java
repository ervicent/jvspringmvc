package testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.ICuentaClienteDao;
import tesis.playon.web.model.CuentaCliente;

/**
 * @author Pablo
 * 
 */
public class TestCuentaCliente {

    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

	ICuentaClienteDao cuentaClienteDao = (ICuentaClienteDao) appContext.getBean("cuentaClienteDao");
	// Cliente cliente= new Cliente();

	// java.sql.Date inicioLocal = new java.sql.Date(0); //Milisegundo cero

	/** insert **/

	// CuentaCliente cuenta = new CuentaCliente(1,100, inicioLocal,cliente);
	List<CuentaCliente> cuentas = cuentaClienteDao.findAll();
	System.out.println("\n CUentas:");
	for (CuentaCliente cuenta2 : cuentas) {
	    System.out.println(cuenta2);
	}

	/** select **/
	CuentaCliente otraCuenta = cuentaClienteDao.findByNroCuenta(1);
	System.out.println("\nEncontrado:\t" + otraCuenta);

	/** update **/
	otraCuenta.setSaldo(250);
	cuentaClienteDao.update(otraCuenta);
	System.out.println("\nCuenta Modificada:\t" + cuentaClienteDao.findByNroCuenta(1));

	System.out.println("\n");
	/** delete **/
	// tipoDocumentoDao.delete(tipoDoc);
	// tipoDocumentoDao.delete(tipoDoc1);
	// documentos = tipoDocumentoDao.findAll();
	// System.out.println("\nRestantes:");
	// for (TipoDoc _tipoDoc : documentos) {
	// System.out.println(_tipoDoc);
	// }

	System.out.println("\nListo!");
    }
}
