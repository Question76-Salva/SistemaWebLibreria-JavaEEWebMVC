package connection;

import java.sql.Connection;
import java.sql.DriverManager;

// =============================================================
// === atributos estáticos con los parámetros de la conexión ===
//==============================================================

public class DBConnection {
	
	// =============================
	// === variables de conexión ===
	// =============================	
	static String bd = "libreria_udemy";	// === nombre de la bbdd ===	
	static String port = "3306";			// === puerto mysql ===	
	static String user = "root";			// === nombre usuario mysql ===	
	static String password = "";			// === contraseña mysql ===
	// === cadena/url de conexión ===
	static String url = "jdbc:mysql://localhost:" + port + "/" + bd;
	
	// === crear objeto | tipo Connection ===
	Connection connection = null;
	
	// === constructor | conecta físicamente con la bbdd ===
	public DBConnection() {
		try {
			// === registra el driver ===
			Class.forName("com.mysql.cj.jdbc.Driver");
			// === guarda la conexión y realiza una conexión fisica con la bbdd ===
			connection = DriverManager.getConnection(url, user, password);
			
			// === comprobar la conexión ===
			if (connection == null) {
				System.out.println("La conexión a " + bd + " ha fallado");
			} else {
				System.out.println("La conexión a " + bd + " ha sido satisfactoria");
			}
			
		} catch (Exception e) {
			// si se produce un error | muestra mensaje de error
			System.out.println(e.getMessage());
		}
	}
	
	
	public Connection getConnection() {
		// === método | devuelve el valor de "connection" ===
		return connection;
	}
	
	 
	public void desconectar() {
		// === método | para desconectar | liberar la conexión ===
	}
}
