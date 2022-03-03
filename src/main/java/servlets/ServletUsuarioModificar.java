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
 * Servlet implementation class ServletUsuarioModificar
 */
@WebServlet("/ServletUsuarioModificar")
public class ServletUsuarioModificar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUsuarioModificar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// === llamar a un método del controlador que va a obtener de la bbdd ===
		
		// === para poder llamar al método "modificar" tengo que crear un objeto ===
		// de tipo "UsuarioController.java"
		UsuarioController usuario = new UsuarioController();
		
		// === recibir/capturar todos los parámetros que vamos a recibir de la petición AJAX ===
		String username = request.getParameter("username");
		String contrasena = request.getParameter("contrasena");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		double saldo = Double.parseDouble(request.getParameter("saldo"));
		boolean premium = Boolean.parseBoolean(request.getParameter("premium"));
		
		// === invocar método pedir del UsuarioController.java | modificar ===
		String usuarioStr = usuario.modificar(username, contrasena, nombre, apellidos, email, saldo, premium);
		
		// === establecer la codificación de la respuesta ===
		response.setContentType("text/html;charset=UTF-8");
		
		// === crear objeto de tipo PrintWriter | para poder enviar ===
		PrintWriter out = response.getWriter();
		// === le digo que quiero enviar ===
		out.println(usuarioStr);
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
