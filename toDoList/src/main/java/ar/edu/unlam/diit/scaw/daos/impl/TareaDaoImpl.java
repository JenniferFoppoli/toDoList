package ar.edu.unlam.diit.scaw.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import ar.edu.unlam.diit.scaw.daos.TareaDao;
import ar.edu.unlam.diit.scaw.entities.Tarea;


public class TareaDaoImpl implements TareaDao {
	
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	public TareaDaoImpl(){
		super();
	}
	
	@Override
	public void agregarTarea(Tarea tarea) {
		String sql = "INSERT INTO TAREAS (ID, DESCRIPCION, FECHA, CREADO_POR, IDESTADO, IDTIPO, IDMODO) VALUES (:id, :descripcion, :fecha, :creado_por, :estadoTarea, :tipoTarea, :modoTarea)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", tarea.getId());
		params.put("descripcion", tarea.getDescripcion());
		params.put("fecha", tarea.getFecha());
		params.put("creado_por", tarea.getCreado_por());
		params.put("modificado_por", tarea.getModificado_por());
		params.put("tipoTarea", tarea.getTipoTarea());
		params.put("estadoTarea", tarea.getEstadoTarea());
		params.put("modoTarea", tarea.getModoTarea());
		jdbcTemplate.update(sql, params);

	}
	
	@Override
	public List<Tarea> buscarTarea(Integer id) {		
		String sql = "SELECT T.*, U.USUARIO FROM TAREAS T INNER JOIN USUARIOS U ON U.ID = T.CREADO_POR WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<Tarea> result = jdbcTemplate.query(sql, params, new TareaMapper());
		return result;	
	}
	
	@Override
	public void update(String id, String descripcion, String fecha, String modificado_por, Integer tipoTarea, Integer estadoTarea, Integer modoTarea) { 
		String sql = "UPDATE TAREAS SET DESCRIPCION = :descripcion, FECHA = :fecha, MODIFICADO_POR = :modificado_por, IDTIPO = :tipoTarea, IDESTADO = :estadoTarea, IDMODO = :idModo  WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", Integer.parseInt(id));
		params.put("descripcion", descripcion);
		params.put("fecha", fecha);
		params.put("modificado_por", modificado_por);
		params.put("tipoTarea", tipoTarea);
		params.put("estado", estadoTarea);
		params.put("modo", modoTarea);
		
		jdbcTemplate.update(sql, params);
	}
	
	@Override
	public void eliminarTarea(Integer id) {		
		String sql = "DELETE FROM TAREAS WHERE id = :idTarea";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idTarea", id);
		jdbcTemplate.update(sql, params);		
	}
	
	@Override
	public List<Tarea> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT T.*, U.USUARIO FROM TAREAS T INNER JOIN USUARIOS U ON U.ID = T.CREADO_POR";
		List<Tarea> result = jdbcTemplate.query(sql, params, new TareaMapper());
		return result;
	}
	
	@Override
	public List<Tarea> verTareasPublicas() {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT T.*, U.USUARIO FROM TAREAS T INNER JOIN USUARIOS U ON U.ID = T.CREADO_POR WHERE T.IDTIPO = 2";
		List<Tarea> result = jdbcTemplate.query(sql, params, new TareaMapper());
		return result;
	}

	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final class TareaMapper implements RowMapper<Tarea> {

		public Tarea mapRow(ResultSet rs, int rowNum) throws SQLException {
			Tarea tarea = new Tarea();
			tarea.setId(rs.getInt("id"));
			tarea.setDescripcion(rs.getString("descripcion"));
			tarea.setFecha(rs.getString("fecha"));
			tarea.setCreado_por(rs.getInt("creado_por"));
			tarea.setModificado_por(rs.getInt("modificado_por"));
			tarea.setTipoTarea(rs.getInt("tipoTarea"));
			tarea.setEstadoTarea(rs.getInt("estadoTarea"));
			tarea.setModoTarea(rs.getInt("modoTarea"));
			tarea.setNombreUsuario(rs.getString("nombreUsuario"));
			return tarea;
		}
	}
}
