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
 * Servlet implementation class ServletUsuarioPedir
 */
@WebServlet("/ServletUsuarioPedir")
public class ServletUsuarioPedir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUsuarioPedir() {
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
		
		// === recibir/capturar todos los parámetros que vamos a recibir de la petición AJAX ===
		String username = request.getParameter("username");
		
		// === invocar método pedir del UsuarioController.java | pedir ===
		// guardar el resultado de lo que me devuelve la invocación al método "pedir"
		String usuarioStr = usuario.pedir(username);
		
		// === crear objeto de tipo PrintWriter | para poder enviar ===
		PrintWriter out = response.getWriter();
		// === le digo que quiero enviar ===
		out.println(usuarioStr);
		// === limpiar buffer ===
		out.flush();
		// === cerrar ===
		out.close();
		
		// === crear objeto tipo String "usuarioStr" ===
		// === donde llamo al método "pedir" del controlador pasándole ===
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
