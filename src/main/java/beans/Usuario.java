package beans;

//===============================================================
//=== mapear la información de la tabla "usuarios" de la bbdd ===
//===============================================================

public class Usuario {
	
	// ==============================================================
	// === atributos | columnas de la tabla "usuarios" de la bbdd ===
	// ==============================================================
	private String username;
	private String contrasena;
	private String nombre;
	private String apellidos;
	private String email;
	private double saldo;
	private boolean premium;
	
	// ===================
	// === constructor ===
	// ===================
	public Usuario(String username, String contrasena, String nombre, String apellidos, String email, double saldo,
			boolean premium) {
		super();
		this.username = username;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.saldo = saldo;
		this.premium = premium;
	}
	
	// =========================
	// === getters & setters ===
	// =========================
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * @param contrasena the contrasena to set
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the saldo
	 */
	public double getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	/**
	 * @return the premium
	 */
	public boolean isPremium() {
		return premium;
	}

	/**
	 * @param premium the premium to set
	 */
	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	// ================
	// === toString ===
	// ================
	@Override
	public String toString() {
		return "Usuario [username=" + username + ", contrasena=" + contrasena + ", nombre=" + nombre + ", apellidos="
				+ apellidos + ", email=" + email + ", saldo=" + saldo + ", premium=" + premium + "]";
	}
	
	
	
	

}
