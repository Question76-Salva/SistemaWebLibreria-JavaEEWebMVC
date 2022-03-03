package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.LibroController;

/**
 * Servlet implementation class ServletLibroAlquilar
 */
@WebServlet("/ServletLibroAlquilar")
public class ServletLibroAlquilar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLibroAlquilar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// === llamar a un método del controlador que va a obtener de la bbdd ===
		
		// === para poder llamar al método "alquilar" tengo que crear un objeto ===
		// de tipo "LibroController"
		LibroController libro = new LibroController();
		
		// === recibir/capturar todos los parámetros que vamos a recibir de la petición AJAX ===
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		
		// === invocar método alquilar del LibroController.java | alquilar ===
		String libroStr = libro.alquilar(id, username);
		
		// === establecer la codificación de la respuesta ===
		response.setContentType("text/html;charset=UTF-8");
		
		// === crear objeto de tipo PrintWriter | para poder enviar ===
		PrintWriter out = response.getWriter();
		// === le digo que quiero enviar ===
		out.println(libroStr);
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
