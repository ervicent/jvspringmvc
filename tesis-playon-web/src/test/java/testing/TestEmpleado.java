package testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.ICargoEmpleadoDao;
import tesis.playon.web.dao.IEmpleadoDao;
import tesis.playon.web.dao.IUsuarioDao;
import tesis.playon.web.model.CargoEmpleado;
import tesis.playon.web.model.Empleado;
import tesis.playon.web.model.Usuario;

/**
 * @author Pablo
 * 
 */
public class TestEmpleado {

    public static void main(String[] args) {

	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

	IUsuarioDao usuarioDao = (IUsuarioDao) appContext.getBean("usuarioDao");
	ICargoEmpleadoDao cargoEmpladoDao = (ICargoEmpleadoDao) appContext.getBean("cargoEmpleadoDao");

	Usuario usuario = usuarioDao.findByNombreUsuario("pablo_la_31");
	Usuario usuario1 = usuarioDao.findByNombreUsuario("pablo_la31");
	CargoEmpleado cargoEmpleado = cargoEmpladoDao.findByNombreCargo("Encargado");
	CargoEmpleado cargoEmpleado1 = cargoEmpladoDao.findByNombreCargo("Playero");

	/** TEST EMPLEADO **/
	IEmpleadoDao empleadoDao = (IEmpleadoDao) appContext.getBean("empleadoDao");

	/** insert **/
	Empleado empleado = new Empleado(1002, cargoEmpleado, usuario);
	Empleado empleado1 = new Empleado(1003, cargoEmpleado1, usuario1);
	empleadoDao.save(empleado);
	empleadoDao.save(empleado1);
	List<Empleado> empleados = empleadoDao.findAll();
	System.out.println("\nEmpleados:");
	for (Empleado _empleado : empleados) {
	    System.out.println(_empleado);
	}

	/** select **/
	Empleado otroEmpleado = empleadoDao.findByLegajo(1002);
	System.out.println("\n");
	System.out.println("\nEncontrado:\t" + otroEmpleado);

	/** update **/
	empleado.setLegajo(2000);
	empleadoDao.update(empleado);
	System.out.println("\n");
	System.out.println("\nModificado:\t" + empleadoDao.findByLegajo(2000));

	System.out.println("\n");
	/** delete **/
	empleadoDao.delete(empleado);
	empleadoDao.delete(empleado1);
	empleados = empleadoDao.findAll();
	System.out.println("\n");
	System.out.println("\nRestantes:");
	for (Empleado _empleado : empleados) {
	    System.out.println(_empleado);
	}

	System.out.println("\nListo!");
    }
}
