/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import tesis.playon.web.model.CuentaPlaya;
import tesis.playon.web.service.ICuentaPlayaService;
import tesis.playon.web.service.IPlayaService;

/**
 * @author pablo
 *
 */
@ManagedBean(name = "cuentaPlayaMB")
@ViewScoped
public class CuentaPlayaManagedBean implements Serializable{

    private static final long serialVersionUID = 6421275264626039536L;

    @ManagedProperty(value = "#{CuentaPlayaService}")
    ICuentaPlayaService cuentaPlayaService;
    
    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;
    
    private static List<CuentaPlaya> cuentaPlayaList;
    
    private static CuentaPlaya cuentaSelected;

    private List<CuentaPlaya> filteredCuentaPlaya;
    
    @PostConstruct
    private void init()
    {
	cuentaPlayaList = getCuentaPlayaService().findAll();
    }
    /**
     * @return the cuentaPlayaService
     */
    public ICuentaPlayaService getCuentaPlayaService() {
        return cuentaPlayaService;
    }

    /**
     * @param cuentaPlayaService the cuentaPlayaService to set
     */
    public void setCuentaPlayaService(ICuentaPlayaService cuentaPlayaService) {
        this.cuentaPlayaService = cuentaPlayaService;
    }

    /**
     * @return the playaService
     */
    public IPlayaService getPlayaService() {
        return playaService;
    }

    /**
     * @param playaService the playaService to set
     */
    public void setPlayaService(IPlayaService playaService) {
        this.playaService = playaService;
    }

    /**
     * @return the cuentaPlayaList
     */
    public List<CuentaPlaya> getCuentaPlayaList() {
        return cuentaPlayaList;
    }

    /**
     * @param cuentaPlayaList the cuentaPlayaList to set
     */
    public void setCuentaPlayaList(List<CuentaPlaya> cuentaPlayaList) {
        CuentaPlayaManagedBean.cuentaPlayaList = cuentaPlayaList;
    }
    /**
     * @return the cuentaSelected
     */
    public CuentaPlaya getCuentaSelected() {
        return cuentaSelected;
    }
    /**
     * @param cuentaSelected the cuentaSelected to set
     */
    public void setCuentaSelected(CuentaPlaya cuentaSelected) {
        CuentaPlayaManagedBean.cuentaSelected = cuentaSelected;
    }
    /**
     * @return the filteredCuentaPlaya
     */
    public List<CuentaPlaya> getFilteredCuentaPlaya() {
        return filteredCuentaPlaya;
    }
    /**
     * @param filteredCuentaPlaya the filteredCuentaPlaya to set
     */
    public void setFilteredCuentaPlaya(List<CuentaPlaya> filteredCuentaPlaya) {
        this.filteredCuentaPlaya = filteredCuentaPlaya;
    }
    
}
