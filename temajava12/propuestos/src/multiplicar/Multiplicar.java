package multiplicar;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Multiplicar
 */
@WebServlet("/Multiplicar")
public class Multiplicar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Multiplicar() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest (request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest (request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String param = request.getParameter("numero");
		int numero = 0;
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			numero = Integer.parseInt(param);
		} catch (NumberFormatException e) {
			printInvalidNumber(out);
			return;
		}
		
		if (numero < 0)
		{
			printInvalidNumber(out);
			return;
		}
		printTabla (out, numero);
	}

	private void printTabla(PrintWriter out, int number) {
		printHeader(out);
		out.print("<table>");
		for ( int i = 1; i <= 10; ++i)
		{
			out.print("<tr>");
			out.print("<td>");
			out.print(number + "x" + i);
			out.print("</td>");
			out.print("<td>");
			out.print(number* i);
			out.print("</td>");
			out.print("</tr>");
		}
		out.print("</table>");
		printFooter(out);
	}

	private void printHeader(PrintWriter out) {
		out.print("<html>");
		out.print("<head>");
		out.print("</head>");
		out.print("<body>");
	}
	
	private void printFooter(PrintWriter out) {
		out.print("</body>");
		out.print("</html>");
	}
	
	private void printInvalidNumber(PrintWriter out) {
		printHeader(out);
		out.print("<p>El número enviado no es válido</p>");
		printFooter(out);
	}

}
