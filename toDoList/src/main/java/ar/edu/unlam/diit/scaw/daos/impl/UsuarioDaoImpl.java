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
		String sql = "INSERT INTO USUARIOS (USUARIO, CLAVE, TIPO, CONDICION) VALUES (:nombreUsuario, :clave, :tipo, :condicion)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("usuario", usuario.getNombreUsuario());
		params.put("password", usuario.getClave());
		params.put("tipo", usuario.getTipo());
		params.put("aprobado", usuario.getCondicion());
		jdbcTemplate.update(sql, params);
	}
	
	@Override
	public void update(String usuarioAnterior, String nombreUsuario, int tipo, String condicion) {
		String sql = "UPDATE USUARIOS SET USUARIO = :nombreUsuario, TIPO = :tipo, CONDICION = :condicion WHERE USUARIO LIKE :usuarioAnterior";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("usuarioAnterior", usuarioAnterior);
		params.put("nombreUsuario", nombreUsuario);
		params.put("tipo", tipo);
		params.put("condicion", condicion);
		jdbcTemplate.update(sql, params);

	}
	
	@Override
	public void eliminarUsuario(String nombreUsuario) {		
		String sql = "DELETE FROM USUARIOS WHERE USUARIO LIKE :nombreUsuario";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("usuario", nombreUsuario);
		jdbcTemplate.update(sql, params);		
	}

	@Override
	public List<Usuario> modificarUsuario(String nombreUsuario) {		
		String sql = "SELECT * FROM USUARIOS WHERE USUARIO LIKE :nombreuUsuario";		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("usuario", nombreUsuario);		
		List<Usuario> result = jdbcTemplate.query(sql, params, new UsuarioMapper());
		return result;	
	}
	
	@Override
	public void cambiarEstadoUsuario(int id, String estado) {		
		String sql = "UPDATE USUARIOS SET CONDICION = :estado WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("estado", estado);
		params.put("id", id);
		jdbcTemplate.update(sql, params);					
	}
	
	@Override
	public List<Usuario> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();

		String sql = "SELECT * FROM USUARIOs";

		List<Usuario> result = jdbcTemplate.query(sql, params, new UsuarioMapper());

		return result;
	}
				
	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Usuario> crearSesion(String nombreUsuario, String clave){
		String sql = "SELECT * FROM USUARIOS WHERE USUARIO LIKE :nombreUsuario AND CLAVE LIKE :clave AND CONDICION LIKE :condicion";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("usuario", nombreUsuario);
		params.put("password", clave);
		params.put("condicion", "Activo");
		
		List<Usuario> result = jdbcTemplate.query(sql, params, new UsuarioMapper());

		return result;
		
	}

	private static final class UsuarioMapper implements RowMapper<Usuario> {

		public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
			Usuario usuario = new Usuario();
			usuario.setId(rs.getInt("id"));
			usuario.setNombreUsuario(rs.getString("nombreUsuario"));
			usuario.setClave(rs.getString("clave"));
			usuario.setTipo(rs.getInt("tipo"));
			usuario.setCondicion(rs.getString("condicion"));
			return usuario;
		}
	}
}
