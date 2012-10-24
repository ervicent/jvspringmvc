/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package springbatch.demo5;

import java.util.Collection;
import java.util.List;
import org.springframework.batch.item.ItemWriter;
import springbatch.demo5.Planeta;

/**
 *
 * @author ldeseta
 */
public class ConsolaItemWriter implements ItemWriter<Planeta> {

    
    public void write(List<? extends Planeta> item) throws Exception {
            try {
                Collection<Planeta> col = (Collection<Planeta>) item.iterator().next();

                for (final Planeta planeta : col) {
                    System.out.println("Planeta leido: " + planeta.toString());
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
       }
}