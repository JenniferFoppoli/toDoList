package ar.edu.unlam.diit.scaw.daos;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Usuario;

public interface UsuarioDao {

	public void update(String usuarioAntiguo, String nombreUsuario, int tipo, String condicion);
	public void agregarUsuario(Usuario usuario);
	public List<Usuario> findAll();
	public void eliminarUsuario(String nombreUsuario);
	public List<Usuario> modificarUsuario(String nombreUsuario);
	public void cambiarEstadoUsuario(int id, String estado);
	public List<Usuario> crearSesion(String nombreUsuario, String clave);
	
}
