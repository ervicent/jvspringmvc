/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package springbatch.demo5;

import java.util.ArrayList;
import org.compass.core.CompassCallback;
import org.compass.core.CompassDetachedHits;
import org.compass.core.CompassHits;
import org.compass.core.CompassQuery;
import org.compass.core.CompassSession;
import org.compass.spring.CompassDaoSupport;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import springbatch.demo5.Planeta;

/**
 *
 * @author parivero
 */
public class IndiceItemReader extends CompassDaoSupport implements ItemReader {

    private int corrida = 0;

    public Object read() throws Exception, UnexpectedInputException, ParseException {
        /**
         * Esto es solo para que corra una vez
         */
         if (corrida == 0) {
            corrida++;
            /**
             * Buscamos todos los planetas de tipo gaseoso y excluimos Martes !!!!
             */
            System.out.println("Buscamos todos los planetas de tipo gaseoso y excluimos Martes");
            return buscarPaginado("nombre:* AND tipo:gaseoso AND -martes");
         }
        return null;
    }

    
    /**
     * Hace una busqueda paginada en el indice, En caso de que el query para la
     * busqueda este vacio devuelve una collection vacia.
     *
     * @param queryIndice
     *            queryIndice
     * @return ArrayList
     */
    private ArrayList<Planeta> buscarPaginado(String queryIndice) {
        CompassDetachedHits results = null;
        final String query = queryIndice;
        final int from = 0;
        final int size = 100;
        ArrayList<Planeta> planeta = new ArrayList<Planeta>();

        try {
            // Esto se hace para poder buscar por OR
            results = getCompassTemplate().execute(new CompassCallback<CompassDetachedHits>() {

                public CompassDetachedHits doInCompass(final CompassSession session) {
                    // Obtengo el query por medio del queryBuilder asi puedo pedirle
                    // que use el dafult OR
                    CompassQuery compassQuery = session.queryBuilder().queryString(query).useOrDefaultOperator().toQuery();
                    // No uso el compass template por que no respeta el query.
                    CompassHits activeHits = compassQuery.hits();
                    CompassDetachedHits detachedHits = null;
                    // Work around a compass bug.
                    if (activeHits.getLength() > (from + size)) {
                        detachedHits = activeHits.detach(from, size);
                    } else if (activeHits.getLength() > from) {
                        detachedHits = activeHits.detach(from, (activeHits.getLength() - from));
                    }
                    return detachedHits;
                }
            });

            // Si la busqueda devolvio algo lo proceso, si no devuelvo una
            // collection vacia.
            if (results != null) {
                for (int i = 0; i < results.getHits().length; i++) {
                    planeta.add((Planeta) results.getHits()[i].getData());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planeta;
    }


}
