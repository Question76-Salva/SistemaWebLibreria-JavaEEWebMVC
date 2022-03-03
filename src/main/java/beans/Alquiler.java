package beans;

import java.sql.Date;

//===============================================================
//=== mapear la informaci�n de la tabla "alquiler" de la bbdd ===
//===============================================================

public class Alquiler {

	// ==============================================================
	// === atributos | columnas de la tabla "alquiler" de la bbdd ===
	// ==============================================================
	private int id;
	private String titulo;
	private Date fechaAlquiler;
	private boolean novedad;
	private String genero;
		
	
	// ===================
	// === constructor ===
	// ===================
	public Alquiler(int id, String titulo, Date fechaAlquiler, boolean novedad, String genero) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.fechaAlquiler = fechaAlquiler;
		this.novedad = novedad;
		this.genero = genero;
	}

	// =========================
	// === getters & setters ===
	// =========================

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}


	/**
	 * @param username the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	/**
	 * @return the fechaAlquiler
	 */
	public Date getFechaAlquiler() {
		return fechaAlquiler;
	}


	/**
	 * @param fechaAlquiler the fechaAlquiler to set
	 */
	public void setFechaAlquiler(Date fechaAlquiler) {
		this.fechaAlquiler = fechaAlquiler;
	}


	/**
	 * @return the novedad
	 */
	public boolean isNovedad() {
		return novedad;
	}


	/**
	 * @param novedad the novedad to set
	 */
	public void setNovedad(boolean novedad) {
		this.novedad = novedad;
	}


	/**
	 * @return the genero
	 */
	public String getGenero() {
		return genero;
	}


	/**
	 * @param genero the genero to set
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

	// ================
	// === toString ===
	// ================
	@Override
	public String toString() {
		return "Alquiler [id=" + id + ", titulo=" + titulo + ", fechaAlquiler=" + fechaAlquiler + ", novedad="
				+ novedad + ", genero=" + genero + "]";
	}
		
	
	
	
	
}
