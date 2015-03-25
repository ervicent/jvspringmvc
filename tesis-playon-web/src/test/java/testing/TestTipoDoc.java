package testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tesis.playon.web.dao.ITipoDocDao;
import tesis.playon.web.model.TipoDoc;

/**
 * @author Pablo
 *
 */
public class TestTipoDoc {
    
    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");


	ITipoDocDao tipoDocumentoDao = (ITipoDocDao) appContext.getBean("tipoDocDao");

	/** insert **/
	TipoDoc tipoDoc = new TipoDoc("DNI",null);
	TipoDoc tipoDoc1 = new TipoDoc("LE",null);
	tipoDocumentoDao.save(tipoDoc);
	tipoDocumentoDao.save(tipoDoc1);
	List<TipoDoc> documentos = tipoDocumentoDao.findAll();
	System.out.println("\nDocumentos:");
	for (TipoDoc tipoDoc2 : documentos) {
	    System.out.println(tipoDoc2);
	}

	/** select **/
	TipoDoc otroDoc = tipoDocumentoDao.findByNombreTipoDoc("LE");
	System.out.println("\nEncontrado:\t" + otroDoc);
	
	/** update **/
	otroDoc.setNombre("LC");
	tipoDocumentoDao.update(otroDoc);
	System.out.println("\nModificado:\t" + tipoDocumentoDao.findByNombreTipoDoc("LC"));
	
	System.out.println("\n");
	/** delete **/
	tipoDocumentoDao.delete(tipoDoc);
	tipoDocumentoDao.delete(tipoDoc1);
	documentos = tipoDocumentoDao.findAll();
	System.out.println("\nRestantes:");
	for (TipoDoc _tipoDoc : documentos) {
	    System.out.println(_tipoDoc);
	}

	System.out.println("\nListo!");
    }
}
