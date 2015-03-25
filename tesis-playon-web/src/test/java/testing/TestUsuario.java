package testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.ITipoDocDao;
import tesis.playon.web.dao.IUsuarioDao;
import tesis.playon.web.model.TipoDoc;
import tesis.playon.web.model.Usuario;

/**
 * @author Pablo
 * 
 */
public class TestUsuario {

    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

	ITipoDocDao tipoDocumentoDao = (ITipoDocDao) appContext.getBean("tipoDocDao");
	TipoDoc tipoDoc = tipoDocumentoDao.findByNombreTipoDoc("D.N.I.");

	IUsuarioDao usuarioDao = (IUsuarioDao) appContext.getBean("usuarioDao");

	/** insert **/
	Usuario usuario = new Usuario("Pablo", "Moreno", "pablo_la31", "123456", "pablo_la31@hotmail.com", tipoDoc,
		32987654, null);
	Usuario usuario1 = new Usuario("Pablo1", "Moreno1", "pablo_la_31", "123456", "pablola31@hotmail.com", tipoDoc,
		32111111, null);
	usuarioDao.save(usuario);
	usuarioDao.save(usuario1);
	List<Usuario> usuarios = usuarioDao.findAll();
	System.out.println("\nUsuarios:");
	for (Usuario _usuario : usuarios) {
	    System.out.println(_usuario);
	}

	/** select **/
	Usuario otroUsuario = usuarioDao.findByNombreUsuario("pablo_la_31");
	System.out.println("\nEncontrado:\t" + otroUsuario);

	/** update **/
	usuario.setNombre("Carlos");
	usuarioDao.update(usuario);
	System.out.println("\nModificado:\t" + usuarioDao.findByNombreUsuario("pablo_la31"));

	System.out.println("\n");
	/** delete **/
	usuarioDao.delete(usuario);
	usuarioDao.delete(usuario1);
	usuarios = usuarioDao.findAll();
	System.out.println("\nRestantes:");
	for (Usuario _usuario : usuarios) {
	    System.out.println(_usuario);
	}

	System.out.println("\nListo!");
    }
}