package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.LibroController;
import controller.UsuarioController;

/**
 * Servlet implementation class ServletUsuarioRestarDinero
 */
@WebServlet("/ServletUsuarioRestarDinero")
public class ServletUsuarioRestarDinero extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUsuarioRestarDinero() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// === llamar a un método del controlador que va a obtener de la bbdd ===
		
		// === para poder llamar al método "restarDinero" tengo que crear un objeto ===
		// de tipo "UsuarioController"
		UsuarioController usuario = new UsuarioController();
		
		// === recibir/capturar todos los parámetros que vamos a recibir de la petición AJAX ===
		String username = request.getParameter("username");
		double saldo = Double.parseDouble(request.getParameter("saldo"));
		
		// === invocar método restarDinero del UusarioController.java | restarDinero ===
		String usuarioStr = usuario.restarDinero(username, saldo);
		
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
