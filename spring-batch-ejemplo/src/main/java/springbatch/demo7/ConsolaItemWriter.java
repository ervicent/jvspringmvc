/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package springbatch.demo7;

import java.util.List;
import org.springframework.batch.item.ItemWriter;

/**
 *
 * @author ldeseta
 */
public class ConsolaItemWriter implements ItemWriter<Planeta> {

    public void write(List<? extends Planeta> item) throws Exception {
        for (Planeta planeta : item) {
            System.out.println("Thread: " + Thread.currentThread().getId() + " - Planeta leido: " + planeta.getNombre());
        }
    }
}
