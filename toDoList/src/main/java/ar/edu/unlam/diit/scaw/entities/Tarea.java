package ar.edu.unlam.diit.scaw.entities;

import java.io.Serializable;

	public class Tarea implements Serializable {

		private static final long serialVersionUID = 1L;

		private Integer id;
		private String descripcion;
		private String fecha;
		private Integer creado_por;
		private Integer modificado_por;
		private Integer tipoTarea;
		private Integer estadoTarea;
		private Integer modoTarea;
		private String nombreUsuario;

		public Tarea() {
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public Integer getCreado_por() {
			return creado_por;
		}

		public void setCreado_por(Integer creado_por) {
			this.creado_por = creado_por;
		}

		public Integer getModificado_por() {
			return modificado_por;
		}

		public void setModificado_por(Integer modificado_por) {
			this.modificado_por = modificado_por;
		}

		public Integer getTipoTarea() {
			return tipoTarea;
		}

		public void setTipoTarea(Integer tipoTarea) {
			this.tipoTarea = tipoTarea;
		}

		public Integer getEstadoTarea() {
			return estadoTarea;
		}

		public void setEstadoTarea(Integer estadoTarea) {
			this.estadoTarea = estadoTarea;
		}

		public Integer getModoTarea() {
			return modoTarea;
		}

		public void setModoTarea(Integer modoTarea) {
			this.modoTarea = modoTarea;
		}

		public String getNombreUsuario() {
			return nombreUsuario;
		}

		public void setNombreUsuario(String nombreUsuario) {
			this.nombreUsuario = nombreUsuario;
		}

		public String getFecha() {
			return fecha;
		}

		public void setFecha(String fecha) {
			this.fecha = fecha;
		}

			
	}
