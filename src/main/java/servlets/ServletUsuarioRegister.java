/*
 * === Primera capa con la que se va a comunicar el usuario ===
 * === mediante la pantalla de register ===
 * recibir los datos del cliente | username, contrasenia, email... 
 * 
 * */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.UsuarioController;

/**
 * Servlet implementation class ServletUsuarioRegister
 */
@WebServlet("/ServletUsuarioRegister")
public class ServletUsuarioRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUsuarioRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// === llamar a un método del controlador que va a obtener de la bbdd ===
		// === todos los datos de ese usuario ===
		
		// === para poder llamar al método "register" tengo que crear un objeto ===
		// de tipo "UsuarioController"
		UsuarioController usuario = new UsuarioController();
		
		// === recibir/capturar todos los campos de los inputs del formulario de register.html ===
		String username = request.getParameter("username");
		String contrasena = request.getParameter("contrasena");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		double saldo = Double.parseDouble(request.getParameter("saldo"));
		boolean premium = Boolean.parseBoolean(request.getParameter("premium"));
		
		// === invocar método register del UsuarioController.java ===
		// guardar el resultado de lo que me devuelve la invocación al método "register"
		String result = usuario.register(username, contrasena, nombre, apellidos, email, saldo, premium);
		
		// === enviar esa respuesta al front | pantalla html ===
		// === devolver el "result" al cliente | devolver datos ===
		// puede ser true o false | si existen o no los datos
		response.setContentType("text/html;charset=UTF-8");
		// === crear objeto de tipo PrintWriter | para poder enviar ===
		PrintWriter out = response.getWriter(); 
		// === le digo que quiero enviar ===
		out.println(result);
		// === limpiar buffer ===
		out.flush();
		// === cerrar ===
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
