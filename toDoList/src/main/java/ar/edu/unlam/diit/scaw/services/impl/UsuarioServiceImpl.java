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
	public void modificarUsuario(String usuarioAntiguo, String usuario, int tipo, String condicion) {
		usuarioDao.modificarUsuario(usuarioAntiguo, usuario, tipo, condicion);
	}
	
	//@Override
	public void eliminarUsuario(String usuario) {
		usuarioDao.eliminarUsuario(usuario);
	}
	
	//@Override
	public List<Usuario> update(String usrName) {
		return usuarioDao.update(usrName);
	}
	
	//@Override
	public void cambiarCondicion(int id, String condicion) {
		usuarioDao.cambiarCondicion(id, condicion);
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

	public List<Usuario> crearSesion(String usrName, String password) {
		return usuarioDao.crearSesion(usrName, password);
	}
}
