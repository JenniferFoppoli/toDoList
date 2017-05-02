package ar.edu.unlam.diit.scaw.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.UsuarioService;

@ManagedBean(name = "usuarioBean", eager = true)
@RequestScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String usuario = null;
	private String clave = null;
	private Integer tipo = null;
	private String condicion = null;
	
	ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"beans.xml"});
	UsuarioService service = (UsuarioService) context.getBean("usuarioService");
	
	
	public UsuarioBean() {
		super();
	}
	
	public String agregarUsuario() {
		
		Usuario usuario = buildUsuario();
		
		service.agregarUsuario(usuario);
		
		return "listaDeUsuarios";
	}
	
	public String modificarUsuario(String usuario, int tipo, String condicion) {
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    String usuarioAntiguo = ec.getRequestParameterMap().get("formId:usuarioAntiguo");
		
		service.modificarUsuario(usuarioAntiguo, usuario, tipo, condicion);
		
		return "listaDeUsuarios";
	}
	
	public String eliminarUsuario(String usuario) {
		
		service.eliminarUsuario(usuario);				
		
		return "listaDeUsuarios";
	}	
	
	public String update(String usuario) {
		List<Usuario> list = service.update(usuario);
		if(list.isEmpty()) {
			return "modificarUsuario";
		}
		
		this.setUsuario(usuario);
		this.setCondicion(list.get(0).getCondicion());
		this.setTipo(list.get(0).getTipo());		
			
		return "listaDeUsuarios";
	}
	
	public String cambiarCondicion(int id, String condicion) {
		
		if (condicion != "") {
			service.cambiarCondicion(id, condicion);				
		}
		return "usuarios";
	}
	
	public List<Usuario> getFindAll() {
		List<Usuario> list = service.findAll();
		return list;
	}
		
	private Usuario buildUsuario() {
		Usuario usuario = new Usuario();
		usuario.setUsuario(this.usuario);
		usuario.setClave(this.clave);
		usuario.setTipo(this.tipo);
		usuario.setCondicion(this.condicion);
		
		return usuario;
	}

	public UsuarioBean(String usuario, String clave, Integer tipo, String condicion) {
		super();
		this.usuario = usuario;
		this.clave = clave;
		this.tipo = tipo;
		this.condicion = condicion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}
	
	// Generacion de la sesion de usuario 
	public String crearSesion(String nombreUsuario, String clave ) throws ServletException { 
		
		List<Usuario> list = service.crearSesion(nombreUsuario, clave);
		
		if(list.isEmpty()) {	// usuario no registrado
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			return "login";
		
		} else {				// usuario registrado en sistema
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			
			//Se crea una nueva sesión para este usuario
			Usuario usuario = new Usuario();
			usuario.setId(list.get(0).getId());
			usuario.setUsuario(list.get(0).getUsuario());
			usuario.setTipo(list.get(0).getTipo());
			usuario.setCondicion(list.get(0).getCondicion());
			
			HttpSession session = request.getSession(true);
			session.setAttribute("id", usuario.getId());
			session.setAttribute("usuario", usuario.getUsuario());
			session.setAttribute("tipo", usuario.getTipo());
			session.setAttribute("condicion", usuario.getCondicion());
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
			
			if (usuario.getTipo() == 1) { 
				return "listaDeUsuarios";	// Administrador -> muestro usuarios					
			} else {
				return "listaDeTareas";	// Usuarios -> muestro tareas
			}
			
		}
	}
	
	public String eliminarSesion() throws ServletException{
				
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
	
		request.logout();
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login";
	}
	
	//este metodo se debe incluir en las vistas para resstringir acceso no autorizado
	public void verificarSesion() throws IOException{
		//TODO: verificar el tipo usuario -> comun/adm -> para ver en que pagina intenta acceder
		if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario") == null) {

			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		}
	}
	
	//este metodo se debe incluir en las vistas para resstringir acceso a usuarios no administradores
	public void verificarUsuario() throws IOException{
		//TODO: verificar el tipo usuario -> comun/adm -> para ver en que pagina intenta acceder
		if(!(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tipo").toString().contentEquals("1"))) {
			// Si el usuario esta registrado y no es administrador es redirigido a la pag de tareas 
			FacesContext.getCurrentInstance().getExternalContext().redirect("tareas.xhtml");
		}
	}

}
