/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package springbatch.demo5;

/**
 *
 * @author parivero
 */
import org.compass.annotations.config.CompassAnnotationsConfiguration;
import org.compass.core.Compass;
import org.compass.core.config.CompassConfiguration;
import org.springframework.beans.factory.FactoryBean;


/**
 * Clase que se encarga de construir una instancia de org.compass.core.Compass
 * Esta clase es usada por Spring para poder inyectarle a otro Bean el objeto
 * que crea.
 *
 * @author jubarre
 */
public class CompassFactory implements FactoryBean {


    /**
     * {@inheritDoc}
     */
    public Object getObject() {
        CompassConfiguration conf = 
                new CompassAnnotationsConfiguration().configure("/springbatch/demo5/compass.cfg.xml")
                .setConnection("indice-compass/")
                .addClass(Planeta.class);
        
        return conf.buildCompass();
    }

    /**
     * {@inheritDoc}
     */
    public Class getObjectType() {
        return Compass.class;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSingleton() {
        return true;
    }

}
