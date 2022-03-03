package beans;

// =============================================================
// === mapear la información de la tabla "libros" de la bbdd ===
//==============================================================

public class Libro {

	// ============================================================
	// === atributos | columnas de la tabla "libros" de la bbdd ===
	// ============================================================
	private int id;
	private String titulo;
	private String genero;
	private String autor;
	private int copias;
	private boolean novedad;
	
	// ===================
	// === constructor ===
	// ===================
	public Libro(int id, String titulo, String genero, String autor, int copias, boolean novedad) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.genero = genero;
		this.autor = autor;
		this.copias = copias;
		this.novedad = novedad;
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
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	/**
	 * @return the autor
	 */
	public String getAutor() {
		return autor;
	}

	/**
	 * @param autor the autor to set
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}

	/**
	 * @return the copias
	 */
	public int getCopias() {
		return copias;
	}

	/**
	 * @param copias the copias to set
	 */
	public void setCopias(int copias) {
		this.copias = copias;
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

	// ================
	// === toString ===
	// ================	
	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", genero=" + genero + ", autor=" + autor + ", copias="
				+ copias + ", novedad=" + novedad + "]";
	}

	
		
	
	
}
