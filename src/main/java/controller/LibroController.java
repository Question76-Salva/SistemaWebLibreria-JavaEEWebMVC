/*
 * === clase ===
 * === implementar los m�todos de la interface "ILibroController.java" ===
 * */

package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import beans.Libro;

import connection.DBConnection;

public class LibroController implements ILibroController {

	@Override
	public String listar(boolean ordenar, String orden) {

		// === crear objeto tipo JSON | para enviar el objeto "usuario" ===
		Gson gson = new Gson();

		// === hacer una conexi�n con la bbdd | crear objeto tipo DBConnection ===
		DBConnection con = new DBConnection();

		// === crear sentencia sql ===
		String sql = "SELECT * FROM libros";

		// === si están ordenados ===
		if (ordenar == true) {
			// concatenar a la consulta par�metros de ordenaci�n SQL
			sql += " ORDER BY genero " + orden;
		}

		// === como tengo que devolver muchos libros tengo que crear un ArrayList ===
		// donde ir almacenando esos libros | almacenar los libros convertidos a JSON
		List<String> libros = new ArrayList<String>();

		try {
			// === crear objeto de tipo Statement a partir de la conexi�n de la bbdd ===
			// para poder ejecutar la consulta
			Statement st = con.getConnection().createStatement();
			// === al ser un SELECT necesitamos un ResultSet para guardar el resultado ===
			// de la consulta
			ResultSet rs = st.executeQuery(sql);
			// === recorrer el ResulSet | recorrer los registros que trae la consulta ===
			while (rs.next()) {
				// === leer/capturar los datos/registros de la tabla que trae la consulta ===
				// leer los datos de los libros, los registros que devuelve el SELECT
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String genero = rs.getString("genero");
				String autor = rs.getString("autor");
				int copias = rs.getInt("copias");
				boolean novedad = rs.getBoolean("novedad");

				// === con estos datos | crear un objeto de tipo "Libro" (benas) ===
				// para guardar la información
				// este "libro" es el que va a devolver la informaci�n al Servlet en JSON
				Libro libro = new Libro(id, titulo, genero, autor, copias, novedad);

				// === añadir a "libros" el libro convertido a JSON ===
				libros.add(gson.toJson(libro));

			}
		} catch (Exception e) {
			// si algo falla | mostrar mensaje de la excepci�n
			System.out.println(e.getMessage());
		} finally {
			// === siempre hay que cerrar la conexión con la bbdd ===
			con.desconectar();
		}

		// === devolver el ArryList convertido a JSON ===
		return gson.toJson(libros);
	}

	@Override
	public String alquilar(int id, String username) {

		// === crear objeto tipo TimeStamp (fecha) para el alquiler del libro ===
		Timestamp fecha = new Timestamp(new Date().getTime());

		// === hacer una conexi�n con la bbdd | crear objeto tipo DBConnection ===
		DBConnection con = new DBConnection();

		// === crear sentencia sql ===
		String sql = "INSERT IGNORE INTO alquiler VALUES ('" + id + "','" + username + "','" + fecha + "')";

		try {
			// === crear objeto de tipo Statement a partir de la conexi�n de la bbdd ===
			// para poder ejecutar la consulta
			Statement st = con.getConnection().createStatement();
			// === ejecutar la consulta sql ===
			st.executeUpdate(sql);

			// === restar un libro del stock | porque se ha alquilado ===
			String modificar = modificar(id);

			if (modificar == "true") {
				return "true";
			}

		} catch (Exception e) {
			// si algo falla | mostrar mensaje de la excepci�n
			System.out.println(e.getMessage());
		} finally {
			// === siempre hay que cerrar la conexi�n con la bbdd ===
			con.desconectar();
		}

		return "false";

	}

	@Override
	public String modificar(int id) {

		// === hacer una conexi�n con la bbdd | crear objeto tipo DBConnection ===
		DBConnection con = new DBConnection();

		// === crear sentencia sql ===
		String sql = "UPDATE IGNORE libros SET copias = (copias - 1) WHERE id = " + id;

		try {
			// === crear objeto de tipo Statement a partir de la conexi�n de la bbdd ===
			// para poder ejecutar la consulta
			Statement st = con.getConnection().createStatement();
			// === ejecutar la consulta sql ===
			st.executeUpdate(sql);

			// === si todo ha ido bien devuelvo true ===
			return "true";

		} catch (Exception e) {
			// si algo falla | mostrar mensaje de la excepci�n
			System.out.println(e.getMessage());
		} finally {
			// === siempre hay que cerrar la conexi�n con la bbdd ===
			con.desconectar();
		}

		// === si algo falló | devuelve false ===
		return "false";
	}

	@Override
	public String devolver(int id, String username) {
		
		// === hacer una conexi�n con la bbdd | crear objeto tipo DBConnection ===
		DBConnection con = new DBConnection();

		// === crear sentencia sql ===
		String sql = "DELETE IGNORE FROM alquiler WHERE id = " + id + " AND username = '" + username + "' LIMIT 1";

		try {
			// === crear objeto de tipo Statement a partir de la conexi�n de la bbdd ===
			// para poder ejecutar la consulta
			Statement st = con.getConnection().createStatement();
			// === ejecutar la consulta sql ===
			st.executeUpdate(sql);
			
			this.sumarCantidad(id);

			// === si todo ha ido bien devuelvo true ===
			return "true";

		} catch (Exception e) {
			// si algo falla | mostrar mensaje de la excepci�n
			System.out.println(e.getMessage());
		} finally {
			// === siempre hay que cerrar la conexi�n con la bbdd ===
			con.desconectar();
		}
		
		// === si algo falló | devuelve false ===
		return "false";
	}

	@Override
	public String sumarCantidad(int id) {
		
		// === hacer una conexi�n con la bbdd | crear objeto tipo DBConnection ===
		DBConnection con = new DBConnection();
		
		// === crear sentencia sql ===
		String sql = "UPDATE IGNORE libros SET copias = (SELECT copias FROM libros WHERE id = " + id + ") + 1 WHERE id = " + id;
		
		try {
			// === crear objeto de tipo Statement a partir de la conexi�n de la bbdd ===
			// para poder ejecutar la consulta
			Statement st = con.getConnection().createStatement();
			// === ejecutar la consulta sql ===
			st.executeUpdate(sql);
						
			// === si todo ha ido bien devuelvo true ===
			return "true";

		} catch (Exception e) {
			// si algo falla | mostrar mensaje de la excepci�n
			System.out.println(e.getMessage());
		} finally {
			// === siempre hay que cerrar la conexi�n con la bbdd ===
			con.desconectar();
		}
		
		// === si algo falló | devuelve false ===
		return "false";
		
	}

}
