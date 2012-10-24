/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package springbatch.demo5;

import java.util.Collection;
import java.util.List;
import org.compass.core.CompassCallback;
import org.compass.core.CompassException;
import org.compass.core.CompassSession;
import org.compass.spring.CompassDaoSupport;
import org.springframework.batch.item.ItemWriter;

/**
 *
 * @author parivero
 */
public class CompassItemWriter extends CompassDaoSupport implements ItemWriter<Planeta> {


    public void write(List<? extends Planeta> item) throws Exception {

        Collection<Planeta> col = (Collection<Planeta>) item;
            for (final Planeta planeta : col) {
                getCompassTemplate().execute((CompassCallback<Planeta>) new CompassCallback<Planeta>() {
                    public Planeta doInCompass(CompassSession session) throws CompassException {
                        try{
                            session.save(planeta);
                            System.out.println("Guardando Planeta : "+planeta.toString());
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                         return null;
                    }
                });
        }
    }
}
