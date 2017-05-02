package ar.edu.unlam.diit.scaw.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.edu.unlam.diit.scaw.entities.Tarea;
import ar.edu.unlam.diit.scaw.services.TareaService;


@ManagedBean(name = "tareaBean", eager = true)
@RequestScoped
public class TareaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id = null;
	private String descripcion = null;
	private String fecha = null;
	private Integer creado_por = null;
	private Integer modificado_por = null;
	private Integer tipoTarea = null;
	private Integer estadoTarea = null;
	private Integer modoTarea = null;
	private String nombreUsuario = null;
	
	ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"beans.xml"});
	TareaService service = (TareaService) context.getBean("tareaService");
	
	public TareaBean(){
		super();
	}
	
	public String agregarUsuario(String creadoPor) {
		
		Tarea tarea = buildTarea();
		tarea.setCreado_por(Integer.parseInt(creadoPor));
		service.agregarTarea(tarea);
		
		return "listaDeTareas";
	}
	public String deleteTarea(String id) {
		
		service.eliminarTarea(Integer.parseInt(id));				
	
		return "listaDeTareas";
	}
		
	// Para la edicion de una tarea
	public String modificarTarea(String id, String descripcion, String fecha, String modificado_por, String tipoTarea, String estadoTarea, String modoTarea, String nombreUsuario) {		
		this.setId(Integer.parseInt(id));
		this.setDescripcion(descripcion);
		this.setFecha(fecha);
		this.setModificado_por(Integer.parseInt(modificado_por));
		this.setTipoTarea(Integer.parseInt(tipoTarea));
		this.setEstadoTarea(Integer.parseInt(estadoTarea));
		this.setModoTarea(Integer.parseInt(modoTarea));
		this.setNombreUsuario(nombreUsuario);
		
		return "modificarTarea";
	}
	
	// Visualizar tarea
	public String verTarea(String id) {	
		List<Tarea> list = service.buscarTarea(Integer.parseInt(id));

		if(list.isEmpty()) {
			return "listaDeTareas";
		}
		this.setId(list.get(0).getId());
		this.setDescripcion(list.get(0).getDescripcion());
		this.setFecha(list.get(0).getFecha());
		this.setModificado_por(list.get(0).getModificado_por());
		this.setTipoTarea(list.get(0).getTipoTarea());
		this.setEstadoTarea(list.get(0).getEstadoTarea());
		this.setModoTarea(list.get(0).getModoTarea());	
		this.setNombreUsuario(list.get(0).getNombreUsuario());
		
		return "mostrarTarea";
	}
	
	// Actualizar tarea
	public String update(String id, String descripcion, String fecha, String modificado_por, Integer tipoTarea, Integer estadoTarea, Integer modoTarea) {	
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    String idTarea = ec.getRequestParameterMap().get("formId:idAntiguo");
		
		service.update(idTarea, descripcion, fecha, modificado_por, tipoTarea, estadoTarea, modoTarea);
		
		return "listaDeTareas";
	}
	
	// Busca todas las tareas guardadas
	public List<Tarea> getFindAll() {		
		List<Tarea> list = service.findAll();
		return list;
	}
	
	// Busca solo las tareas publicas (para los usuarios no registrados)
	public List<Tarea> getVerTareasPublicas() {	
		List<Tarea> list = service.verTareasPublicas();
		return list;
	}
	
	private Tarea buildTarea() {
		Tarea tarea = new Tarea();
		tarea.setId(this.id);
		tarea.setDescripcion(this.descripcion);
		tarea.setFecha(this.fecha);
		tarea.setModificado_por(this.modificado_por);
		tarea.setCreado_por(this.creado_por);
		tarea.setTipoTarea(this.tipoTarea);
		tarea.setEstadoTarea(this.estadoTarea);
		tarea.setModoTarea(this.modoTarea);
		tarea.setNombreUsuario(this.nombreUsuario);
		
		return tarea;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Integer getModificado_por() {
		return modificado_por;
	}

	public void setModificado_por(Integer modificado_por) {
		this.modificado_por = modificado_por;
	}
	
	public Integer getCreado_por() {
		return creado_por;
	}

	public void setCreado_por(Integer creado_por) {
		this.creado_por = creado_por;
	}
	
	public Integer getTipoTarea() {
		return tipoTarea;
	}

	public void setTipoTarea(Integer tipoTarea) {
		this.tipoTarea = tipoTarea;
	}

	public Integer getEstadoTarea() {
		return estadoTarea;
	}

	public void setEstadoTarea(Integer estadoTarea) {
		this.estadoTarea = estadoTarea;
	}

	public Integer getModoTarea() {
		return modoTarea;
	}

	public void setModoTarea(Integer modoTarea) {
		this.modoTarea = modoTarea;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
}
