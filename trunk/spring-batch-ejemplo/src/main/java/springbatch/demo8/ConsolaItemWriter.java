/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package springbatch.demo8;

import java.util.Collection;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;

import springbatch.demo10.Planeta;

/**
 *
 * @author ldeseta
 */
public class ConsolaItemWriter extends StepExecutionListenerSupport implements ItemWriter<Planeta> {

	private FlatFileItemWriter<Planeta> archivoPlanoItemWriter;
	ExecutionContext executionContext;
	
    public void write(List<? extends Planeta> item) throws Exception {
        Collection<Planeta> col = (Collection<Planeta>) item;
        for (final Planeta planeta : col) {
            System.out.println("Planeta: " + planeta.getNombre());
            System.out.println("Significado: " + planeta.getSignificado());
            System.out.println("----------------");
        }
        
        archivoPlanoItemWriter.open(executionContext);
        archivoPlanoItemWriter.write(item);
        archivoPlanoItemWriter.close();
    }

	@Override
	public void beforeStep(StepExecution stepExecution) {
		this.executionContext = stepExecution.getExecutionContext();
	}

	/**
	 * @param archivoPlanoItemWriter the archivoPlanoItemWriter to set
	 */
	public void setArchivoPlanoItemWriter(FlatFileItemWriter<Planeta> archivoPlanoItemWriter) {
		this.archivoPlanoItemWriter = archivoPlanoItemWriter;
	}

	/**
	 * @return the archivoPlanoItemWriter
	 */
	public FlatFileItemWriter<Planeta> getArchivoPlanoItemWriter() {
		return archivoPlanoItemWriter;
	}
}
