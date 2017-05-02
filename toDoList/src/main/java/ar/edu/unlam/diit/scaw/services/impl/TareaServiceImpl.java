package ar.edu.unlam.diit.scaw.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.diit.scaw.services.TareaService;
import ar.edu.unlam.diit.scaw.daos.TareaDao;
import ar.edu.unlam.diit.scaw.entities.Tarea;




public class TareaServiceImpl implements TareaService {
	@Autowired
	TareaDao tareaDao;
	
	@Override
	public void agregarTarea(Tarea tarea) {
		tareaDao.agregarTarea(tarea);
	}

	//@Override
	public List<Tarea> buscarTarea(Integer id	) {
		return tareaDao.buscarTarea(id);
	}
	@Override
	public void update(String id, String descripcion, String fecha, String modificado_por, Integer tipoTarea, Integer estadoTarea, Integer modoTarea) {
		tareaDao.update(id, descripcion, fecha, modificado_por, tipoTarea, estadoTarea, modoTarea);
	}
	@Override
	public List<Tarea> findAll() {
		return tareaDao.findAll();
	}
	
	@Override
	public List<Tarea> verTareasPublicas() {
		return tareaDao.verTareasPublicas();
	}
	
	public TareaDao getTareaDao() {
		return tareaDao;
	}

	public void setTareaDao(TareaDao tareaDao) {
		this.tareaDao = tareaDao;
	}
	
	//@Override
	public void eliminarTarea(Integer id) {
		tareaDao.eliminarTarea(id);
	}
	
}
