/*
 * === clase ===
 * === implementar los m�todos de la interface "IUsuarioController.java" ===
 * */

package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import beans.Usuario;
import connection.DBConnection;

public class UsuarioController implements IUsuarioController {

	@Override
	public String login(String username, String contrasena) {

		// === crear objeto tipo JSON | para enviar el objeto "usuario" ===
		Gson gson = new Gson();

		// === hacer una conexi�n con la bbdd | crear objeto tipo DBConnection ===
		DBConnection con = new DBConnection();

		// === crear sentencia sql ===
		String sql = "SELECT * FROM usuarios WHERE username = '" + username + "' AND contrasena = '" + contrasena
				+ "' ";

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
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String email = rs.getString("email");
				double saldo = rs.getDouble("saldo");
				boolean premium = rs.getBoolean("premium");

				// === con estos datos | crear un objeto de tipo "Usuario" (benas) ===
				// para guardar la informaci�n
				// este "usuario" es el que va a devolver la informaci�n al Servlet en JSON
				Usuario usuario = new Usuario(username, contrasena, nombre, apellidos, email, saldo, premium);

				// === llamar al m�todo "toJson" y pasarle el "usuario" para enviar los datos
				// ===
				// === convertir el objeto a JSON ===
				return gson.toJson(usuario);

			}
		} catch (Exception e) {
			// si algo falla | mostrar mensaje de la excepci�n
			System.out.println(e.getMessage());
		} finally {
			// === siempre hay que cerrar la conexi�n con la bbdd ===
			con.desconectar();
		}

		// === en caso de que no haya salido del m�todo | no devuelva el JSON ===
		// no existe un usuario con ese username ni con esa contrasenia
		// o que se haya producido un error al conectar con la bbdd
		return "false";
	}

	@Override
	public String register(String username, String contrasena, String nombre, String apellidos, String email,
			double saldo, boolean premium) {

		// === los datos se van a pasar en formato JSON ===
		// crear objeto de tipo Gson para enviar el objeto
		Gson gson = new Gson();

		// === hacer una conexi�n con la bbdd | crear objeto tipo DBConnection ===
		DBConnection con = new DBConnection();

		// === crear sentencia sql ===
		String sql = "INSERT IGNORE INTO usuarios VALUES('" + username + "', '" + contrasena + "', '" + nombre + "', '"
				+ apellidos + "', '" + email + "', '" + saldo + "', '" + premium + "')";

		try {
			// === crear objeto de tipo Statement a partir de la conexi�n de la bbdd ===
			// para poder ejecutar la consulta
			Statement st = con.getConnection().createStatement();
			// === ejecutar la consulta ===
			st.executeUpdate(sql);
			// === con estos datos | crear un objeto de tipo "Usuario" (benas) ===
			// para guardar la informaci�n
			// este "usuario" es el que va a devolver la informaci�n al Servlet en JSON
			Usuario usuario = new Usuario(username, contrasena, nombre, apellidos, email, saldo, premium);
			// === cerrar el Statement ===
			st.close();
			// === llamar al m�todo "toJson" y pasarle el "usuario" para enviar los datos
			// ===
			// === convertir el objeto a JSON ===
			return gson.toJson(usuario);

		} catch (Exception e) {
			// si se produce una excepci�n | mostrar el tipo de error
			System.out.println(e.getMessage());

		} finally {
			// === siempre hay que cerrar la conexi�n con la bbdd ===
			con.desconectar();
		}

		// === en caso de que no haya salido del m�todo | no devuelva el JSON ===
		// no existe un usuario con ese username ni con esa contrasenia
		// o que se haya producido un error al conectar con la bbdd
		return "false";

	}

	@Override
	public String pedir(String username) {

		// === los datos se van a pasar en formato JSON ===
		// crear objeto de tipo Gson para enviar el objeto
		Gson gson = new Gson();

		// === hacer una conexión con la bbdd | crear objeto tipo DBConnection ===
		DBConnection con = new DBConnection();

		// === crear sentencia sql ===
		String sql = "SELECT * FROM usuarios WHERE username = '" + username + "'";

		try {
			// === crear objeto de tipo Statement a partir de la conexi�n de la bbdd ===
			// para poder ejecutar la consulta
			Statement st = con.getConnection().createStatement();
			// === al ser un SELECT necesitamos un ResultSet para guardar el resultado ===
			// de la consulta
			ResultSet rs = st.executeQuery(sql);
			// === recorrer el ResulSet | recorrer los registros que trae la consulta ===
			while (rs.next()) {
				// va a recorrer un s�lo registro
				// === leer/capturar los datos/registros de la tabla que trae la consulta ===
				String contrasena = rs.getString("contrasena");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String email = rs.getString("email");
				double saldo = rs.getDouble("saldo");
				boolean premium = rs.getBoolean("premium");

				// === con estos datos | crear un objeto de tipo "Usuario" (benas) ===
				// para guardar la informaci�n
				// este "usuario" es el que va a devolver la informaci�n al Servlet en JSON
				Usuario usuario = new Usuario(username, contrasena, nombre, apellidos, email, saldo, premium);

				// === llamar al m�todo "toJson" y pasarle el "usuario" para enviar los datos
				// ===
				// === convertir el objeto a JSON ===
				return gson.toJson(usuario);
			}

		} catch (Exception e) {
			// si se produce una excepci�n | mostrar el tipo de error
			System.out.println(e.getMessage());
		} finally {
			// === siempre hay que cerrar la conexi�n con la bbdd ===
			con.desconectar();
		}

		// === en caso de que no haya salido del m�todo | no devuelva el JSON ===
		// no existe un usuario con ese username ni con esa contrasenia
		// o que se haya producido un error al conectar con la bbdd
		return "false";
	}

	@Override
	public String restarDinero(String username, double nuevoSaldo) {

		// === hacer una conexión con la bbdd | crear objeto tipo DBConnection ===
		DBConnection con = new DBConnection();

		// === crear sentencia sql ===
		String sql = "UPDATE IGNORE usuarios SET saldo = " + nuevoSaldo + " WHERE username = '"+username+"'";

		try {
			// === crear objeto de tipo Statement a partir de la conexi�n de la bbdd ===
			// para poder ejecutar la consulta
			Statement st = con.getConnection().createStatement();
			// === ejecutar la consulta ===
			st.executeUpdate(sql);
			
			// === si todo fue bien | devuelve true ===
			return "true";
			

		} catch (Exception e) {
			// si se produce una excepci�n | mostrar el tipo de error
			System.out.println(e.getMessage());
		} finally {
			// === siempre hay que cerrar la conexi�n con la bbdd ===
			con.desconectar();
		}

		// === en caso de que no haya salido del m�todo ===
		return "false";
	}

	@Override
	public String modificar(String username, String nuevaContrasena, String nuevoNombre, String nuevosApellidos,
			String nuevoEmail, double nuevoSaldo, boolean nuevoPremium) {
		
		// === hacer una conexión con la bbdd | crear objeto tipo DBConnection ===
		DBConnection con = new DBConnection();
		
		// === crear sentencia sql ===
		String sql = "Update ignore usuarios set contrasena = '" + nuevaContrasena + "', nombre = '" + nuevoNombre + "', " 
				+ "apellidos = '" + nuevosApellidos + "', email = '" + nuevoEmail + "', saldo = " + nuevoSaldo + ", premium = ";  
		
		// === si el usuario es premium o no | 1 - 0 === 
		if (nuevoPremium == true) {
			sql += " 1 ";
		} else {
			sql += " 0 "; 
		}
		
		// === condición de la consulta sql ===
		// todo lo de antes se va a producir cuando
		sql += " WHRERE username = '" + username + "'";
		
		try {
			// === crear objeto de tipo Statement a partir de la conexi�n de la bbdd ===
			// para poder ejecutar la consulta
			Statement st = con.getConnection().createStatement();
			// === ejecutar la consulta ===
			st.executeUpdate(sql);
			
			// === si todo fue bien | devuelve true ===
			return "true";
			

		} catch (Exception e) {
			// si se produce una excepci�n | mostrar el tipo de error
			System.out.println(e.getMessage());
		} finally {
			// === siempre hay que cerrar la conexi�n con la bbdd ===
			con.desconectar();
		}

		// === en caso de que no haya salido del m�todo ===
		return "false";
		
		
	}

	@Override
	public String verCopias(String username) {
		
		// === hacer una conexión con la bbdd | crear objeto tipo DBConnection ===
		DBConnection con = new DBConnection();
		
		// === crear sentencia sql ===
		String sql = "Select id,count(*) as num_copias from alquiler where username = '" + username + "' group by id;";
		
		// === crear diccionario | almacenar pares de ID´s del libro reservado ===
		// === y número de copias de ese libro que tengo reservado ===
		Map<Integer, Integer> copias = new HashMap<Integer, Integer>();
		
		try {
			// === crear objeto de tipo Statement a partir de la conexi�n de la bbdd ===
			// para poder ejecutar la consulta
			Statement st = con.getConnection().createStatement();
			// === ejecutar la consulta ===
			ResultSet rs = st.executeQuery(sql);
			
			// === recorrer el ResulSet mientras haya registros ===
			while(rs.next()) {
				// capturar/leer los datos de la consulta
				int id= rs.getInt("id");
				int num_copias = rs.getInt("num_copias");
				
				// guardar los datos a la colección Map
				copias.put(id,num_copias);
			}
			
			devolverLibros(username, copias);
			
			// === si todo fue bien | devuelve true ===
			return "true";
			

		} catch (Exception e) {
			// si se produce una excepci�n | mostrar el tipo de error
			System.out.println(e.getMessage());
		} finally {
			// === siempre hay que cerrar la conexi�n con la bbdd ===
			con.desconectar();
		}

		// === en caso de que no haya salido del m�todo ===
		return "false";
		
	}

	@Override
	public String devolverLibros(String username, Map<Integer, Integer> copias) {
		
		// === hacer una conexión con la bbdd | crear objeto tipo DBConnection ===
		DBConnection con = new DBConnection();
				
		try {
			// === recorrer el Map | copias ===
			for (Map.Entry<Integer, Integer> libro: copias.entrySet()) {
				int id = libro.getKey();
				int num_copias = libro.getValue();
				
				// === crear sentencia sql ===
				String sql = "Update ignore libros set copias = (Select copias + " + num_copias + " from libros where id = " + id + ") where id = " + id;
				
				// === crear objeto de tipo Statement a partir de la conexi�n de la bbdd ===
				// para poder ejecutar la consulta
				Statement st = con.getConnection().createStatement();
				// === ejecutar la consulta ===
				st.executeUpdate(sql);
			}
			
			// === llamar al método | eliminar ===
			this.eliminar(username);
									

		} catch (Exception e) {
			// si se produce una excepci�n | mostrar el tipo de error
			System.out.println(e.getMessage());
		} finally {
			// === siempre hay que cerrar la conexi�n con la bbdd ===
			con.desconectar();
		}

		// === en caso de que no haya salido del m�todo ===
		return "false";		
		
	}

	@Override
	public String eliminar(String username) {
		
		// === hacer una conexión con la bbdd | crear objeto tipo DBConnection ===
		DBConnection con = new DBConnection();
		
		// === crear sentencia sql ===
		String sql1 = "Delete from alquiler where username = '" + username + "'";
		String sql2 = "Delete from usuarios where username = '" + username + "'";
		
		try {
			// === crear objeto de tipo Statement a partir de la conexi�n de la bbdd ===
			// para poder ejecutar la consulta
			Statement st = con.getConnection().createStatement();
			// === ejecutar la consulta ===
			st.executeUpdate(sql1);
			st.executeUpdate(sql2);
			
			// === si todo fue bien | devuelve true ===
			return "true";

		} catch (Exception e) {
			// si se produce una excepci�n | mostrar el tipo de error
			System.out.println(e.getMessage());
		} finally {
			// === siempre hay que cerrar la conexi�n con la bbdd ===
			con.desconectar();
		}

		// === en caso de que no haya salido del m�todo ===
		return "false";
	}
	
	

}
