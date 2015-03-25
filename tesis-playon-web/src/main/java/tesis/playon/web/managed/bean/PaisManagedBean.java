package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.Localidad;
import tesis.playon.web.model.Pais;
import tesis.playon.web.model.Provincia;
import tesis.playon.web.service.IPaisService;
import tesis.playon.web.service.IProvinciaService;

/**
 * 
 * @author gmorales
 * 
 */
@ManagedBean(name = "paisMB")
@ViewScoped
public class PaisManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    // private static final String LISTA_PAIS = "paislist";

    // private static final String ERROR = "error";

    @ManagedProperty(value = "#{PaisService}")
    IPaisService paisService;

    @ManagedProperty(value = "#{ProvinciaService}")
    IProvinciaService provinciaService;

    private Map<String, String> paises = new HashMap<String, String>();

    private Map<String, String> provincias = new HashMap<String, String>();

    private Map<String, Map<String, String>> provinciasData = new HashMap<String, Map<String, String>>();

    private String pais;
    
    @SuppressWarnings("unused")
    private Pais paisSeleccionado;

    private String provincia;

    @SuppressWarnings("unused")
    private Localidad localidad;

    @SuppressWarnings("unused")
    private Barrio barrio;

    List<Localidad> localidadList;

    List<Barrio> barrioList;

    public IPaisService getPaisService() {
	return paisService;
    }

    public void setPaisService(IPaisService paisService) {
	this.paisService = paisService;
    }

    public IProvinciaService getProvinciaService() {
	return provinciaService;
    }

    public void setProvinciaService(IProvinciaService provinciaService) {
	this.provinciaService = provinciaService;
    }

    public Map<String, String> getPaises() {
	List<Pais> paisList = getPaisService().findAll();
	for (Pais pais : paisList) {
	    paises.put(pais.getNombre(), pais.getNombre());

	    Map<String, String> prov = new HashMap<String, String>();
	    List<Provincia> provinciaList = getProvinciaService().findProvincias(pais);
	    for (Provincia provincia : provinciaList) {
		prov.put(provincia.getNombre(), provincia.getNombre());
	    }
	    provinciasData.put(pais.getNombre(), prov);
	}
	return paises;
    }

    public void setPaises(Map<String, String> paises) {
	this.paises = paises;
    }

    public Map<String, String> getProvincias() {
        return provincias;
    }

    public void setProvincias(Map<String, String> provincias) {
        this.provincias = provincias;
    }

    public Map<String, Map<String, String>> getProvinciasData() {
        return provinciasData;
    }

    public void setProvinciasData(Map<String, Map<String, String>> provinciasData) {
        this.provinciasData = provinciasData;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void handlePaisChange() {
	if (pais != null && !pais.equals("")) {
	    provincias = provinciasData.get(pais);
	} else {
	    provincias = new HashMap<String, String>();
	}
    }
}