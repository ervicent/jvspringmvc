/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package springbatch.demo9;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.file.LineCallbackHandler;
import org.springframework.batch.item.file.LineMapper;

/**
 * Clase que manejará la cabecera del archivo. Lee la cabecera del archivo recibido y la setea
 * en el contexto del step para que otros componentes del step la puedan utilizar.
 * Esta clase implementa LineCallbackHandler. Esta interfaz fuerza implementar el método
 * handleLine(String linea), para procesar la cabecera.
 *
 * @author Andres
 */
public class CabeceraHandler implements LineCallbackHandler {

    /**
     * Line mapper del header. Se utiliza para mapear el registro cabecera al
     * objeto de tipo CabeceraArchivo.
     */
    private LineMapper lineMapper;
    
    /**
     * información de la ejecución del step. Se usará para intercambiar información entre
     * fases del step.
     */
    private StepExecution stepExecution;

    /**
     * Esta es la key bajo la cual se guardará el objeto cabecera en el contexto.
     */
    public static final String KEY_CABECERA = "KEY_CABECERA";

    public StepExecution getStepExecution() {
        return stepExecution;
    }

    /**
     * Método que se llamará automáticamente por el motor de ejecución del job
     * pasándole el StepExecution del job. Para esto se usa la anotación @BeforeStep
     * Además es necesario que esté registrado el bean de esta clase en los listeners del
     * tasklet del step.
     *
     * @param stepExecution - La informacion de ejecucion del step.
     */
    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    /**
     * setter del lineMapper
     * @param lineMapper que se usará para mapear el header a un objeto.
     */
    public void setLineMapper(LineMapper lineMapper) {
        this.lineMapper = lineMapper;
    }

    /**
     * Realiza el manejo de la linea cabecera del archivo.
     * Mapea la línea cabecera con un objeto de la clase CabeceraArchivo y la guarda en el
     * contexto del job para que esté disponible para ser usado por el writer.
     * 
     * @param linea La linea header del archivo.
     */
    public void handleLine(String linea) {
        System.out.println("Cabecera del archivo leída: " + linea);
        try {
            CabeceraArchivo cabeceraArchivo = (CabeceraArchivo) lineMapper.mapLine(linea, 0);
            //guarda el objeto cabecera en el contexto.
            getStepExecution().getExecutionContext().put(KEY_CABECERA, cabeceraArchivo);
        } catch (Exception ex) {
            //Si no se puede mapear la linea, lanza una excepción. El job Abortará.
            throw new RuntimeException(ex);
        }
    }

}
