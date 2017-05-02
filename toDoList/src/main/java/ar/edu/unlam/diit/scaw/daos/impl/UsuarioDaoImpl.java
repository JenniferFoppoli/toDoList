package ar.edu.unlam.diit.scaw.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import ar.edu.unlam.diit.scaw.daos.UsuarioDao;
import ar.edu.unlam.diit.scaw.entities.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	public UsuarioDaoImpl() {
		super();
	}

	@Override
	public void agregarUsuario(Usuario usuario) {

		String sql = "INSERT INTO USUARIO (USUARIO, CLAVE, TIPO, CONDICION) VALUES (:usuario, :clave, :tipo, :condicion)";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("usuario", usuario.getUsuario());
		params.put("clave", usuario.getClave());
		params.put("tipo", usuario.getTipo());
		params.put("condicion", usuario.getCondicion());
		jdbcTemplate.update(sql, params);
	}
	
	@Override
	public void modificarUsuario(String usuarioAntiguo, String usuario, int tipo, String condicion) {

		String sql = "UPDATE USUARIO SET USUARIO = :usuario, TIPO = :tipo, CONDICION = :condicion WHERE USUARIO LIKE :usuarioAntiguo";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("usuarioAntiguo", usuarioAntiguo);
		params.put("usuario", usuario);
		params.put("tipo", tipo);
		params.put("condicion", condicion);
		jdbcTemplate.update(sql, params);

	}
	
	@Override
	public void eliminarUsuario(String usuario) {		
		String sql = "DELETE FROM USUARIO WHERE USUARIO LIKE :usuario";

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("usuario", usuario);
		jdbcTemplate.update(sql, params);		
	}

	@Override
	public void cambiarCondicion(int id, String condicion) {		
		String sql = "UPDATE USUARIO SET CONDICION = :condicion WHERE ID = :id";

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("condicion", condicion);
		params.put("id", id);
		jdbcTemplate.update(sql, params);					
	} 
	
	@Override
	public List<Usuario> crearSesion(String usuario, String clave){
		String sql = "SELECT * FROM USUARIO WHERE USUARIO LIKE :usuario AND CLAVE LIKE :clave AND CONDICION LIKE :condicion";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("usuario", usuario);
		params.put("clave", clave);
		params.put("condicion", "Activo");
		
		List<Usuario> result = jdbcTemplate.query(sql, params, new UsuarioMapper());

		return result;
		
	}
	
	@Override
	public List<Usuario> update(String usuario) {		
		String sql = "SELECT * FROM USUARIO WHERE USUARIO LIKE :usuario";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("usuario", usuario);
		
		List<Usuario> result = jdbcTemplate.query(sql, params, new UsuarioMapper());

		return result;	
	}

	
	@Override
	public List<Usuario> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();

		String sql = "SELECT * FROM USUARIO";

		List<Usuario> result = jdbcTemplate.query(sql, params, new UsuarioMapper());

		return result;
	}
				
	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

	private static final class UsuarioMapper implements RowMapper<Usuario> {

		public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
			Usuario usuario = new Usuario();
			usuario.setId(rs.getInt("id"));
			usuario.setUsuario(rs.getString("usuario"));
			usuario.setClave(rs.getString("clave"));
			usuario.setTipo(rs.getInt("tipo"));
			usuario.setCondicion(rs.getString("condicion"));
			return usuario;
		}
	}
}
