/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package springbatch.demo9;

import java.util.Collection;
import java.util.List;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;

/**
 *
 * @author ldeseta
 */
public class ConsolaItemWriter implements ItemWriter<Planeta> {

    private StepExecution stepExecution;
    
    private int numeroInformado=1;

    public int getNumeroInformado() {
        return numeroInformado;
    }

    public void setNumeroInformado(int numeroInformado) {
        this.numeroInformado = numeroInformado;
    }

    
    public StepExecution getStepExecution() {
        return stepExecution;
    }

    public void write(List<? extends Planeta> item) throws Exception {

        CabeceraArchivo cabeceraArchivo = (CabeceraArchivo) getStepExecution().getExecutionContext().get(CabeceraHandler.KEY_CABECERA);
        Collection<Planeta> col = (Collection<Planeta>) item;
        for (final Planeta planeta : col) {
            System.out.println("Registro "+getNumeroInformado()+ " de " +
                    cabeceraArchivo.getCantidadRegistros() + ". " +
                    cabeceraArchivo.getDescripcion() + ": "+ planeta.getNombre());
            setNumeroInformado(getNumeroInformado()+1);
        }
    }

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

}
