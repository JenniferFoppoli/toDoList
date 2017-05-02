package ar.edu.unlam.diit.scaw.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.diit.scaw.daos.UsuarioDao;
import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioDao usuarioDao;
	
	@Override
	public void agregarUsuario(Usuario usuario) {
		usuarioDao.agregarUsuario(usuario);
	}

	@Override
	public void update(String usuarioAntiguo, String nombreUsuario, int tipo, String condicion) {
		usuarioDao.update(usuarioAntiguo, nombreUsuario, tipo, condicion);
	}
	
	//@Override
	public void eliminarUsuario(String nombreUsuario) {
		usuarioDao.eliminarUsuario(nombreUsuario);
	}
	
	//@Override
	public List<Usuario> modificarUsuario(String nombreUsuario) {
		return usuarioDao.modificarUsuario(nombreUsuario);
	}
	
	//@Override
	public void cambiarEstadoUsuario(int id, String estado) {
		usuarioDao.cambiarEstadoUsuario(id, estado);
	}
	
	@Override
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}
	
	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}	

	public List<Usuario> crearSesion(String nombreUsuario, String clave) {
		return usuarioDao.crearSesion(nombreUsuario, clave);
	}
}
