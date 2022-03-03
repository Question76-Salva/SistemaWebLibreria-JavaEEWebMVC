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
 * Servlet implementation class ServletUsuarioEliminar
 */
@WebServlet("/ServletUsuarioEliminar")
public class ServletUsuarioEliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUsuarioEliminar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// === llamar a un método del controlador que va a obtener de la bbdd ===
		
		// === para poder llamar al método "eliminar" tengo que crear un objeto ===
		// de tipo "UsuarioController.java"
		UsuarioController usuario = new UsuarioController();
		
		// === recibir/capturar todos los parámetros que vamos a recibir de la petición AJAX ===
		String username = request.getParameter("username");
		
		// === invocar método pedir del UsuarioController.java | modificar ===
		String usuarioStr = usuario.verCopias(username);
		
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
