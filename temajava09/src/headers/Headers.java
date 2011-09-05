package headers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Headers
 */
@WebServlet("/Headers")
public class Headers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Headers() {
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
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html><head><title>Headers</title></head><body>");
		out.print("<p>Protocolo: <b>" + request.getProtocol() + "</b></p>");
		out.print("<p>Método: <b>" + request.getMethod() + "</b></p>");
		
		out.print("<table>");
		out.print("<tr><th>Encabezado</th><th>Valor</th></tr>");
		Enumeration<String> headers = request.getHeaderNames();
		while ( headers.hasMoreElements() )
		{
			String item = headers.nextElement();
			out.print("<tr><td>"+item+"</td><td>"+request.getHeader(item) +"</td></tr>");
		}
		out.print("</table>");
		out.print("</body></html>");
	}

}
