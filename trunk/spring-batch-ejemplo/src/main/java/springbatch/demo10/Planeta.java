/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package springbatch.demo10;


/**
 *
 * @author ldeseta
 */
public class Planeta {
    private int codigo;
    private String nombre;
    private long diametro;
    private String tipo;
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
        String desc = "El planeta " + getNombre();
        desc += ", con " + getDiametro() + "km de diametro, ";
        desc += "es de tipo " + getTipo(); 
        desc += ". Su nombre significa " + getSignificado();
        return desc;
    }    

}
