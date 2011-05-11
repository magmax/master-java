package mensajes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Guardar
 */
@WebServlet("/Guardar")
public class Guardar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Guardar() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// recupera el javabean Mensaje
		Mensaje men = (Mensaje) request.getAttribute("mensa");
		// obtiene los parámetros de contexto relativos
		// a la base de datos
		String driver = getServletContext().getInitParameter("driver");
		String cadenacon = getServletContext().getInitParameter("cadenacon");
		Operaciones oper = new Operaciones(driver, cadenacon);
		oper.graba(men);
		response.sendRedirect("inicio.html");
	}

}
