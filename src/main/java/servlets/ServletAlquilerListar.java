package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.AlquilerController;
import controller.LibroController;

/**
 * Servlet implementation class ServletAlquilarListar
 */
@WebServlet("/ServletAlquilerListar")
public class ServletAlquilerListar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAlquilerListar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// === llamar a un método del controlador que va a obtener de la bbdd ===
		
		// === para poder llamar al método "listarAlquilers" tengo que crear un objeto ===
		// de tipo "AlquilerController"
		AlquilerController alquiler = new AlquilerController();
		
		// === recibir/capturar todos los parámetros que vamos a recibir de la petición AJAX ===
		String username = request.getParameter("username"); 
		
		// === invocar método pedir del AlquilerController.java | listarAlquilers ===
		String alquilerStr = alquiler.listarAlquilers(username);
		
		// === crear objeto de tipo PrintWriter | para poder enviar ===
		PrintWriter out = response.getWriter();
		// === le digo que quiero enviar ===
		out.println(alquilerStr);
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
