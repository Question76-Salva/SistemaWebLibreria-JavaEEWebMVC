package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import com.google.gson.Gson;

import beans.Alquiler;
import connection.DBConnection;

public class AlquilerController implements IAlquilerController {

	@Override
	public String listarAlquilers(String username) {

		// === crear objeto tipo JSON | para enviar el objeto "usuario" ===
		Gson gson = new Gson();

		// === hacer una conexi�n con la bbdd | crear objeto tipo DBConnection ===
		DBConnection con = new DBConnection();

		// === crear sentencia sql ===
		// las reservas se encuentran en la tabla "alquiler"
		// hay que relacionar la tabla "alquiler" con la tabla "libros"
		// para obtener los datos del alquiler de la reserva como de los libros
		// con un INNER JOIN
		// l -> tabla libro | a -> tabla alquiler
		// ON -> relacionar con los ids de ambas tablas
		String sql = "SELECT l.id, l.titulo, l.genero, l.novedad, a.fecha FROM libros l "
				+ "INNER JOIN alquiler a ON l.id = a.id INNER JOIN usuarios u ON a.username = u.username "
				+ "WHERE a.username = '" + username + "'";

		// === como tengo que devolver muchos alquileres tengo que crear un ArrayList ===
		// donde ir almacenando esos alquileres | almacenar los alquileres convertidos a JSON
		List<String> alquileres = new ArrayList<String>();

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
				boolean novedad = rs.getBoolean("novedad");
				Date fechaAlquiler = rs.getDate("fecha"); 

				// === con estos datos | crear un objeto de tipo "Alquiler" (benas) ===
				// para guardar la información este "alquiler" 
				// es el que va a devolver la informaci�n al Servlet en JSON				
				Alquiler alquiler = new Alquiler(id, titulo, fechaAlquiler, novedad, genero);
				
				// === añadir a "alquileres" el alquiler convertido a JSON ===
				alquileres.add(gson.toJson(alquiler));

			}
		} catch (Exception e) {
			// si algo falla | mostrar mensaje de la excepci�n
			System.out.println(e.getMessage());
		} finally {
			// === siempre hay que cerrar la conexión con la bbdd ===
			con.desconectar();
		}
		
		// === devolver el ArryList convertido a JSON ===
		return gson.toJson(alquileres);
	}

}
