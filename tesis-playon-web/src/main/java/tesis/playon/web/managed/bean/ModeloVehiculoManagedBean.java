/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.model.MarcaVehiculo;
import tesis.playon.web.model.ModeloVehiculo;
import tesis.playon.web.service.ICategoriaVehiculoService;
import tesis.playon.web.service.IMarcaVehiculoService;
import tesis.playon.web.service.IModeloVehiculoService;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "modeloVehiculoMB")
@ViewScoped
public class ModeloVehiculoManagedBean implements Serializable {

	private static final long serialVersionUID = -1085389423375986168L;

	@ManagedProperty(value = "#{ModeloVehiculoService}")
	IModeloVehiculoService modeloVehiculoService;

	@ManagedProperty(value = "#{MarcaVehiculoService}")
	IMarcaVehiculoService marcaVehiculoService;

	@ManagedProperty(value = "#{CategoriaVehiculoService}")
	ICategoriaVehiculoService categoriaVehiculoService;

	private ModeloVehiculo modelo;

	private MarcaVehiculo marca;

	private CategoriaVehiculo categoriaVehiculo;

	@SuppressWarnings("unused")
	private ModeloVehiculo modeloNulo;

	@SuppressWarnings("unused")
	private MarcaVehiculo marcaNula;

	private List<MarcaVehiculo> marcasList;

	private List<CategoriaVehiculo> categoriaVehiculoList;;

	private static List<ModeloVehiculo> modelosList;

	private static final String LISTA_MARCA_VEHICULOS = "marcavehiculolist";

	private static final String LISTA_MODELO_VEHICULOS = "modelovehiculolist";

	private static final String ERROR = "error";

	private static List<MarcaVehiculo> marcaVehiculoList;

	private String nombreMarca;

	private static MarcaVehiculo marcaVehiculoSelected;

	private static ModeloVehiculo modeloVehiculoSelected;

	private static CategoriaVehiculo categoriaVehiculoSelected;

	private String descripcionMarca;

	private String nombreModelo;

	private String descripcionModelo;
	


	@PostConstruct
	private void init() {
		marcaVehiculoList = getMarcaVehiculoService().findAll();
		modelosList = getModeloVehiculoService().findAll();
		categoriaVehiculoList = getCategoriaVehiculoService().findAll();
		
		}

	public String addMarcaVehiculo() {
		MarcaVehiculo marcaVehiculo = new MarcaVehiculo();
		try {

			marcaVehiculo.setNombre(getNombreMarca());
			marcaVehiculo.setDescripcion(getDescripcionMarca());
			getMarcaVehiculoService().save(marcaVehiculo);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Se agregó correctamente la marca: "
							+ marcaVehiculo.getNombre(), "");
			FacesContext.getCurrentInstance().addMessage(null, message);

			return LISTA_MARCA_VEHICULOS;
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, no se pudo agregar el rol: "
							+ marcaVehiculo.getNombre(),
					"Por favor, inténtelo más tarde.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}
		return ERROR;
	}

	public String addModeloVehiculo() {
		ModeloVehiculo modeloVehiculo = new ModeloVehiculo();
		try {
			modeloVehiculo.setNombre(getNombreModelo());
			modeloVehiculo.setCategoriaVehiculo(getCategoriaVehiculo());
			modeloVehiculo.setDescripcion(getDescripcionModelo());
			modeloVehiculo.setMarcaVehiculo(getMarca());

			getModeloVehiculoService().save(modeloVehiculo);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Se registró correctamente el modelo de vehículo "
							+ getNombreModelo(), "");
			FacesContext.getCurrentInstance().addMessage(null, message);

			return LISTA_MODELO_VEHICULOS;

		} catch (Exception ex) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, No se pudo registrar el modelo ", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
			ex.printStackTrace();
		}
		return ERROR;
	}

	public String updateVehiculo() {
		try {
			modeloVehiculoSelected
					.setNombre(modeloVehiculoSelected.getNombre());
			modeloVehiculoSelected
					.setCategoriaVehiculo(categoriaVehiculoSelected);
			modeloVehiculoSelected.setDescripcion(modeloVehiculoSelected
					.getDescripcion());
			modeloVehiculoSelected.setMarcaVehiculo(marcaVehiculoSelected);

			getModeloVehiculoService().update(modeloVehiculoSelected);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Se actualizó correctamente el modelo de vehiculo: "
							+ modeloVehiculoSelected.getNombre(), null);
			FacesContext.getCurrentInstance().addMessage(null, message);

			return LISTA_MODELO_VEHICULOS;
		} catch (Exception e) {

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Ya se encuentra registrado el Modelo  "
							+ modeloVehiculoSelected.getNombre(), null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return ERROR;

	}

	public IModeloVehiculoService getModeloVehiculoService() {
		return modeloVehiculoService;
	}

	public void setModeloVehiculoService(
			IModeloVehiculoService modeloVehiculoService) {
		this.modeloVehiculoService = modeloVehiculoService;
	}

	public IMarcaVehiculoService getMarcaVehiculoService() {
		return marcaVehiculoService;
	}

	public void setMarcaVehiculoService(
			IMarcaVehiculoService marcaVehiculoService) {
		this.marcaVehiculoService = marcaVehiculoService;
	}

	public ICategoriaVehiculoService getCategoriaVehiculoService() {
		return categoriaVehiculoService;
	}

	public void setCategoriaVehiculoService(
			ICategoriaVehiculoService categoriaVehiculoService) {
		this.categoriaVehiculoService = categoriaVehiculoService;
	}

	public ModeloVehiculo getModelo() {
		return modelo;

	}

	public void setModelo(ModeloVehiculo modelo) {
		this.modelo = modelo;
	}

	public MarcaVehiculo getMarca() {
		return marca;
	}

	public void setMarca(MarcaVehiculo marca) {
		this.marca = marca;
	}

	public ModeloVehiculo getModeloNulo() {
		return null;
	}

	public void setModeloNulo(ModeloVehiculo modeloNulo) {
		this.modeloNulo = modeloNulo;
	}

	public MarcaVehiculo getMarcaNula() {
		modelosList = new ArrayList<ModeloVehiculo>();
		return null;
	}

	public void setMarcaNula(MarcaVehiculo marcaNula) {
		this.marcaNula = marcaNula;
	}

	public List<MarcaVehiculo> getMarcasList() {
		marcasList = getMarcaVehiculoService().findAll();
		return marcasList;
	}

	public void setMarcasList(List<MarcaVehiculo> marcasList) {
		this.marcasList = marcasList;
	}

	public List<ModeloVehiculo> getModelosList() {

		return modelosList;
	}

	public void setModelosList(List<ModeloVehiculo> modelosList) {
		ModeloVehiculoManagedBean.modelosList = modelosList;
	}

	public List<MarcaVehiculo> getMarcaVehiculoList() {
		return marcaVehiculoList;
	}

	public void setMarcaVehiculoList(List<MarcaVehiculo> marcaVehiculoList) {
		ModeloVehiculoManagedBean.marcaVehiculoList = marcaVehiculoList;
	}

	public String getNombreMarca() {
		return nombreMarca;
	}

	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}

	public MarcaVehiculo getMarcaVehiculoSelected() {
		return marcaVehiculoSelected;
	}

	public void setMarcaVehiculoSelected(MarcaVehiculo marcaVehiculoSelected) {
		ModeloVehiculoManagedBean.marcaVehiculoSelected = marcaVehiculoSelected;
	}

	public CategoriaVehiculo getCategoriaVehiculoSelected() {
		return categoriaVehiculoSelected;
	}

	public void setCategoriaVehiculoSelected(
			CategoriaVehiculo categoriaVehiculoSelected) {
		ModeloVehiculoManagedBean.categoriaVehiculoSelected = categoriaVehiculoSelected;
	}

	public ModeloVehiculo getModeloVehiculoSelected() {
		return modeloVehiculoSelected;
	}

	public void setModeloVehiculoSelected(ModeloVehiculo modeloVehiculoSelected) {
		ModeloVehiculoManagedBean.modeloVehiculoSelected = modeloVehiculoSelected;
	}

	public String getDescripcionMarca() {
		return descripcionMarca;
	}

	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}

	public List<CategoriaVehiculo> getCategoriaVehiculoList() {
		return categoriaVehiculoList;
	}

	public void setCategoriaVehiculoList(
			List<CategoriaVehiculo> categoriaVehiculoList) {
		this.categoriaVehiculoList = categoriaVehiculoList;
	}

	public String getNombreModelo() {
		return nombreModelo;
	}

	public void setNombreModelo(String nombreModelo) {
		this.nombreModelo = nombreModelo;
	}

	public String getDescripcionModelo() {
		return descripcionModelo;
	}

	public void setDescripcionModelo(String descripcionModelo) {
		this.descripcionModelo = descripcionModelo;
	}

	public CategoriaVehiculo getCategoriaVehiculo() {
		return categoriaVehiculo;
	}

	public void setCategoriaVehiculo(CategoriaVehiculo categoriaVehiculo) {
		this.categoriaVehiculo = categoriaVehiculo;
	}

	/******************************** Metodos para modelo de vehiculo ************************/

}
