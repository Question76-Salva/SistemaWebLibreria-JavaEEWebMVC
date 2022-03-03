package connection;

import java.sql.Connection;
import java.sql.DriverManager;

// =============================================================
// === atributos est�ticos con los par�metros de la conexi�n ===
//==============================================================

public class DBConnection {
	
	// =============================
	// === variables de conexi�n ===
	// =============================	
	static String bd = "libreria_udemy";	// === nombre de la bbdd ===	
	static String port = "3306";			// === puerto mysql ===	
	static String user = "root";			// === nombre usuario mysql ===	
	static String password = "";			// === contrase�a mysql ===
	// === cadena/url de conexi�n ===
	static String url = "jdbc:mysql://localhost:" + port + "/" + bd;
	
	// === crear objeto | tipo Connection ===
	Connection connection = null;
	
	// === constructor | conecta f�sicamente con la bbdd ===
	public DBConnection() {
		try {
			// === registra el driver ===
			Class.forName("com.mysql.cj.jdbc.Driver");
			// === guarda la conexi�n y realiza una conexi�n fisica con la bbdd ===
			connection = DriverManager.getConnection(url, user, password);
			
			// === comprobar la conexi�n ===
			if (connection == null) {
				System.out.println("La conexi�n a " + bd + " ha fallado");
			} else {
				System.out.println("La conexi�n a " + bd + " ha sido satisfactoria");
			}
			
		} catch (Exception e) {
			// si se produce un error | muestra mensaje de error
			System.out.println(e.getMessage());
		}
	}
	
	
	public Connection getConnection() {
		// === m�todo | devuelve el valor de "connection" ===
		return connection;
	}
	
	 
	public void desconectar() {
		// === m�todo | para desconectar | liberar la conexi�n ===
	}
}
