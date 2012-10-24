/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package springbatch.demo8;

import org.springframework.batch.item.ItemProcessor;

/**
 * Esta clase sera la encargada de modificar los atributos "nombre" y
 * "significado" del objeto de dominio "Planeta".
 * Lo que se hace como ejemplo es que pase a mayuscula el nombre del planeta y 
 * su significado. Para que el processor sea tomado como tal, debe implementar
 * la interface ItemProcessor, con los Generics indicando lo que recibe y lo que
 * devolvera (en este caso recibe un Planeta y devolvera un Planeta).
 */
public class PlanetaProcessor implements ItemProcessor<Planeta, Planeta> {

    /**
     * Creamos el metodo process que va a tener la logica necesaria para hacer
     * lo que nosotros queremos.
     * (En este caso pasar a mayusculas dos atributos de nuestro objeto.
     * @param planeta - objeto al cual se le cambiaran los atributos.
     * @return planeta.
     */
    public Planeta process(Planeta planeta) {
        planeta.setNombre(planeta.getNombre() == null? "No tiene nombre" : planeta.getNombre().toUpperCase());
        planeta.setSignificado(planeta.getSignificado() == null? "No tiene nombre" : planeta.getSignificado().toUpperCase());
        return planeta;
    }

}
