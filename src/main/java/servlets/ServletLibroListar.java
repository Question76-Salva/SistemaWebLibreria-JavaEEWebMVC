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
 * Servlet implementation class ServletLibroListar
 */
@WebServlet("/ServletLibroListar")
public class ServletLibroListar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLibroListar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// === llamar a un método del controlador que va a obtener de la bbdd ===
		
		// === para poder llamar al método "listar" tengo que crear un objeto ===
		// de tipo "LibroController"
		LibroController libro = new LibroController();
		
		// === recibir/capturar todos los parámetros que vamos a recibir de la petición AJAX ===
		boolean ordernar = Boolean.parseBoolean(request.getParameter("ordenar"));
		String orden = request.getParameter("orden"); 
		
		// === invocar método pedir del LibroController.java | listar ===
		String libroStr = libro.listar(ordernar, orden);
		
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
