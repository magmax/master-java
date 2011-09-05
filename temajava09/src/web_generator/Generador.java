package web_generator;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Generador
 */
@WebServlet("/Generador")
public class Generador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Generador() {
		super();
		// TODO Auto-generated constructor stub
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
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head><title>Página generada</title></head>");
		out.print("<body>");
		out.print("<h" + request.getParameter("nivel") + ">");
		out.print(request.getParameter("encabezado"));
		out.print("</h" + request.getParameter("nivel") + ">");

		out.print("<br/>");

		String[] estilos = request.getParameterValues("estilo");
		if (estilos == null)
			estilos = new String[0];
		
		for (String item : estilos) {
			out.print("<" + item + ">");
		}
		
		out.print("<span style=\"");

		if ("verde".equals(request.getParameter("color")))
			out.print("color:#0f0;");
		if ("azul".equals(request.getParameter("color")))
			out.print("color:#00f;");
		out.print(request.getParameterValues("color"));

		out.print("\">");
		out.print(request.getParameter("texto"));
		out.print("</span>");

		for (int i = estilos.length-1; i >=0; --i) {
			out.print("</" + estilos[i] + ">");
		}
		
		out.print("<br/>");
		for (String item : request.getParameterMap().keySet()) {
			out.print(item + " -> " + request.getParameter(item) + "<br/>");
		}

		out.print("</body>");
		out.print("</html>");
		out.close();
	}

}
