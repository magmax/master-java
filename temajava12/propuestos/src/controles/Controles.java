package controles;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controles
 */
@WebServlet("/Controles")
public class Controles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controles() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write("<html>");
		out.write("<head><title>Ficha</title></head>");
		out.write("<body>");
		out.write("<p>Nombre: <b>" + request.getParameter("nombre") +"</b><br/>");
		out.write("<p>Apellido: <b>" + request.getParameter("apellido") +"</b><br/>");
		out.write("<p>Ciudad: <b>" + request.getParameter("ciudad") +"</b><br/>");
		out.write("<p>Temas: <b>"+ Arrays.toString( request.getParameterValues("temas")) +"</b><br/>");
		out.write("</body>");
		out.write("</html>");
	}
}
