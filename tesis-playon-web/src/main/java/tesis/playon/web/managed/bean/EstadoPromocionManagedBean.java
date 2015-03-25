package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import tesis.playon.web.model.EstadoPromocion;
import tesis.playon.web.service.IEstadoPromocionService;
/**
 * 
 * @author pablo
 *
 */
@ManagedBean(name = "estadoPromocionMB")
public class EstadoPromocionManagedBean implements Serializable {

    private static final long serialVersionUID = 5540896357926708069L;

    @ManagedProperty(value = "#{EstadoPromocionService}")
    IEstadoPromocionService estadoPromocionService;
    
    //Atributos
    private List<EstadoPromocion> estadoPromocionList;

    @PostConstruct
    private void init(){
	estadoPromocionList = new ArrayList<EstadoPromocion>();
	estadoPromocionList = getEstadoPromocionService().findAll();
    }
    
    public IEstadoPromocionService getEstadoPromocionService() {
        return estadoPromocionService;
    }

    public void setEstadoPromocionService(IEstadoPromocionService estadoPromocionService) {
        this.estadoPromocionService = estadoPromocionService;
    }

    public List<EstadoPromocion> getEstadoPromocionList() {
        return estadoPromocionList;
    }

    public void setEstadoPromocionList(List<EstadoPromocion> estadoPromocionList) {
        this.estadoPromocionList = estadoPromocionList;
    } 
}
