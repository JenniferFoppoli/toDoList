package ar.edu.unlam.diit.scaw.daos;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Tarea;

public interface TareaDao {
	
	public void agregarTarea(Tarea tarea);
	public List<Tarea> findAll();
	public List<Tarea> verTareasPublicas();
	public void eliminarTarea(Integer id);
	public List<Tarea> buscarTarea(Integer id);
	public void update(String id, String descripcion, String fecha, String modificado_por, Integer tipoTarea, Integer estadoTarea, Integer modoTarea);
	
}
