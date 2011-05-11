package colores;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Colores
 */
@WebServlet("/Colores")
public class Colores extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Colores() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest (request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String color = request.getParameter("color");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Pintar fondo</title></head>");
		out.println ("<body " + (color != null ? "style=\"background-color:#" +color +"\"": "") +">");
		out.print ("<ul>");
		out.print("<li><a href=\"?color=00f\">Azul</a></li>");
		out.print("<li><a href=\"?color=f00\">Rojo</a></li>");
		out.print("<li><a href=\"?color=ff0\">Amarillo</a></li>");
		out.print ("</ul>");
		out.println ("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest (request, response);
	}

}
