/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import tesis.playon.web.model.FotoPublicidad;
import tesis.playon.web.model.Publicidad;
import tesis.playon.web.service.IFotoPublicidadService;
import tesis.playon.web.service.IPublicidadService;
import tesis.playon.web.util.WriteImage;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "fotoPublicidadMB")
@ViewScoped
public class FotoPublicidadManagedBean implements Serializable {

    private static final long serialVersionUID = 7191837117945230509L;

    @ManagedProperty(value = "#{FotoPublicidadService}")
    IFotoPublicidadService fotoPublicidadService;

    @ManagedProperty(value = "#{PublicidadService}")
    IPublicidadService publicidadService;

    private static List<FotoPublicidad> fotoPublicidadImparList, fotoPublicidadParList;

    private static List<Publicidad> publicidadList;

    @PostConstruct
    private void init() {
	List<Publicidad> publicidadList = getPublicidadService().findAllByEstadoVigente();

	fotoPublicidadParList = new ArrayList<FotoPublicidad>();
	fotoPublicidadImparList = new ArrayList<FotoPublicidad>();

	if (publicidadList != null) {

	    for (int i = 0; i < publicidadList.size(); i++) {
		if (publicidadList.get(i).getId() % 2 == 0)
		    fotoPublicidadParList.add(publicidadList.get(i).getFotoPublicidad());
		else
		    fotoPublicidadImparList.add(publicidadList.get(i).getFotoPublicidad());
	    }
	}
	if (fotoPublicidadParList != null) {
	    WriteImage.writeFotosPublicidad(fotoPublicidadParList);
	}
	if (fotoPublicidadImparList != null) {
	    WriteImage.writeFotosPublicidad(fotoPublicidadImparList);
	}

    }

    /**
     * @return the fotoPublicidadService
     */
    public IFotoPublicidadService getFotoPublicidadService() {
	return fotoPublicidadService;
    }

    /**
     * @param fotoPublicidadService
     *            the fotoPublicidadService to set
     */
    public void setFotoPublicidadService(IFotoPublicidadService fotoPublicidadService) {
	this.fotoPublicidadService = fotoPublicidadService;
    }

    /**
     * @return the publicidadService
     */
    public IPublicidadService getPublicidadService() {
	return publicidadService;
    }

    /**
     * @param publicidadService
     *            the publicidadService to set
     */
    public void setPublicidadService(IPublicidadService publicidadService) {
	this.publicidadService = publicidadService;
    }

    /**
     * @return the fotoPublicidadImparList
     */
    public List<FotoPublicidad> getFotoPublicidadImparList() {
        return fotoPublicidadImparList;
    }

    /**
     * @param fotoPublicidadImparList the fotoPublicidadImparList to set
     */
    public void setFotoPublicidadImparList(List<FotoPublicidad> fotoPublicidadImparList) {
        FotoPublicidadManagedBean.fotoPublicidadImparList = fotoPublicidadImparList;
    }

    /**
     * @return the fotoPublicidadParList
     */
    public List<FotoPublicidad> getFotoPublicidadParList() {
        return fotoPublicidadParList;
    }

    /**
     * @param fotoPublicidadParList the fotoPublicidadParList to set
     */
    public void setFotoPublicidadParList(List<FotoPublicidad> fotoPublicidadParList) {
        FotoPublicidadManagedBean.fotoPublicidadParList = fotoPublicidadParList;
    }

    /**
     * @return the publicidadList
     */
    public List<Publicidad> getPublicidadList() {
	return publicidadList;
    }

    /**
     * @param publicidadList
     *            the publicidadList to set
     */
    public void setPublicidadList(List<Publicidad> publicidadList) {
	FotoPublicidadManagedBean.publicidadList = publicidadList;
    }
}
