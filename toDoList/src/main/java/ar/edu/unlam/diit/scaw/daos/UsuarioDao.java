package ar.edu.unlam.diit.scaw.daos;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Usuario;

public interface UsuarioDao {

	public void agregarUsuario(Usuario usuario);
	public void modificarUsuario(String usuarioAntiguo, String usuario, int tipo, String condicion);
	public void eliminarUsuario(String usuario);
	public void cambiarCondicion(int id, String condicion);
	public List<Usuario> crearSesion(String usrName, String password);
	public List<Usuario> findAll();
	public List<Usuario> update(String usuario);
}
