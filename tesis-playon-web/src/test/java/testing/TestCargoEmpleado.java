package testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.ICargoEmpleadoDao;
import tesis.playon.web.model.CargoEmpleado;

/**
 * @author Pablo
 *
 */
public class TestCargoEmpleado {

    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

	ICargoEmpleadoDao cargoEmpladoDao = (ICargoEmpleadoDao)appContext.getBean("cargoEmpleadoDao");
	
	System.out.println("\n");
	/** insert **/
	CargoEmpleado cargo = new CargoEmpleado("Operario", "Operario de Playa");
	CargoEmpleado cargo1 = new CargoEmpleado("Gerente", "Gerente de Playa");
	cargoEmpladoDao.save(cargo);
	cargoEmpladoDao.save(cargo1);
	List<CargoEmpleado> cargos = cargoEmpladoDao.findAll();
	System.out.println("Nuevos Cargos de empleados:");
	for (CargoEmpleado cargoEmpleado : cargos) {
	    System.out.println(cargoEmpleado);
	}
	
	System.out.println("\n");	
	/** select **/
	CargoEmpleado cargoSolicitada = cargoEmpladoDao.findByNombreCargo("Operario");
	System.out.println("Cargo buscado: \t" + cargoSolicitada);
	
	System.out.println("\n");
	/** update **/
	cargo.setNombre("Propietario");
	cargoEmpladoDao.update(cargo);
	System.out.println("Cargo Modificada:\t" + cargoEmpladoDao.findByNombreCargo("Propietario"));

	System.out.println("\n");
	/** delete **/
	cargoEmpladoDao.delete(cargo);
	cargoEmpladoDao.delete(cargo1);
	cargos = cargoEmpladoDao.findAll();
	System.out.println("Marcas de vehiculos restantes:");
	for (CargoEmpleado cargoEmpleado : cargos) {
	    System.out.println(cargoEmpleado);
	}

	System.out.println("\nListo!");
    }
}
