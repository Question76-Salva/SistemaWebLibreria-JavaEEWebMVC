package test;

import java.sql.ResultSet;
import java.sql.Statement;

import beans.Libro;
import connection.DBConnection;

public class OperacionesBD {

	public static void main(String[] args) {
		
		//actualizarLibro(1, "Histórica");
		listarLibro();
	}
	
	// ==========================
	// === ACTUALIZAR | libro ===
	// ==========================
	// === actualizar libro con el genero que pase por parámetro ===
	public static void actualizarLibro(int id, String genero) {
		// === crear objeto tipo DBConnection | para poder conectar con la bbdd ===
		DBConnection con = new DBConnection();
		// === crear consulta SQL ===
		String sql = "UPDATE libros SET genero = '" + genero + "' WHERE id = " + id;
		
		try {
			// === crear objeto tipo Statement | objeto para crear la consulta ===
			Statement st = con.getConnection().createStatement();
			// === ejecutar la consulta sql ===
			st.executeUpdate(sql);
		} catch (Exception e) {
			// === mostrar el tipo de error | si ocurriera ===
			System.out.println(e.getMessage());
		} finally {
			// === desconectar | conexión bbdd ===
			con.desconectar();
		}
		
		
	}
	
	// =======================
	// === LISTAR | libros ===
	// =======================
	public static void listarLibro() {
		// === crear objeto tipo DBConnection | para poder conectar con la bbdd ===
		DBConnection con = new DBConnection();
		// === crear consulta SQL ===
		String sql = "SELECT * FROM libros";
		
		try {
			// === crear objeto tipo Statement | objeto para crear la consulta ===
			Statement st = con.getConnection().createStatement();
			// === la consulta SELECT devuelve registros ===
			// === hay que guardarlos en un objeto de tipo ResulSet ===
			// === ejecutar la consulta sql ===
			ResultSet rs = st.executeQuery(sql);
			// === recorrer los registros guardardos en el ResulSet ===
			while(rs.next()) {
				// === mientras en el ResultSet haya algún registro ===
				// === capturar los valores de los campos de los registros ===
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String genero = rs.getString("genero");
				String autor = rs.getString("autor");
				int copias = rs.getInt("copias");
				boolean novedad = rs.getBoolean("novedad");
				
				// === con estos datos crear un objeto de tipo "Libro" ===
				Libro libro = new Libro(id, titulo, genero, autor, copias, novedad);
				// === imprmir resultado toString | por consola ===
				System.out.println(libro.toString());
			}
		} catch (Exception e) {
			// === mostrar el tipo de error | si ocurriera ===
			System.out.println(e.getMessage());
		} finally {
			// === desconectar | conexión bbdd ===
			con.desconectar();
		}
	}
	
}
