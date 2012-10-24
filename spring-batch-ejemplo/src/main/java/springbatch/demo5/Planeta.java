/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package springbatch.demo5;

import org.compass.annotations.Index;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;

/**
 *
 * @author ldeseta
 */
@Searchable
public class Planeta {
    @SearchableId
    private int codigo;
    @SearchableProperty(index = Index.TOKENIZED)
    private String nombre;
    @SearchableProperty
    private long diametro;
    @SearchableProperty(index = Index.TOKENIZED)
    private String tipo;
    @SearchableProperty(index = Index.TOKENIZED)
    private String significado;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public long getDiametro() {
        return diametro;
    }

    public void setDiametro(long diametro) {
        this.diametro = diametro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSignificado() {
        return significado;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        String desc = "Planeta:" + getNombre();
        desc += " - Diametro:" + getDiametro();
        desc += " - Tipo:" + getTipo(); 
        desc += " - Significado:" + getSignificado();
        return desc;
    }        
}
