/*
 * === Primera capa con la que se va a comunicar el usuario ===
 * === mediante la pantalla de login ===
 * recibir los datos del cliente | username y contrasenia, con esos datos
 * va a invocar al controlador "UsuarioController.java" que va a hacer una
 * consulta a la bbdd, para saber si existe un usuario con esos datos, si 
 * es así nos devuelve los datos de ese usuario y de lo contrario devuelve
 * un valor "false". Eso lo devuelve al Servlet "ServeltUsuarioLogin.java" 
 * y el Servlet lo redirecciona al cliente
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
 * Servlet implementation class ServletUsuarioLogin
 */
@WebServlet("/ServletUsuarioLogin")
public class ServletUsuarioLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUsuarioLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// === crear objeto de tipo UsuarioController | desde aquí invocar método "login" ===
		// de la clase "UsuarioController"
		UsuarioController usuario = new UsuarioController(); 
		// === leer/capturar los parámetros que recibo del cliente | de la pantalla de index.html ===
		String username = request.getParameter("username");
		String contrasena = request.getParameter("contrasena");
		// === una vez que tengo los dos parámetros | invocar al método "login" del UsuarioController ===
		String result = usuario.login(username, contrasena);
		
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
