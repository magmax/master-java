package mensajes3;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Guardar
 */
@WebServlet(name = "Mensajes3Guardar", urlPatterns = { "/mensajes3/Mensajes3Guardar" })
public class Guardar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Persistencia persistencia = new Persistencia();
		String driver =  config.getServletContext().getInitParameter("dbdriver");
		String connection = config.getServletContext().getInitParameter("dbconnection");
		
		
		if (driver == null)
			throw new ServletException("El driver no se encontró");
		if (connection == null)
			throw new ServletException("La cadena de conexión no se encontró");
		
		
		persistencia.setDriver( driver );
		persistencia.setConnection ( connection );
		persistencia.createTables();
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public Guardar() {
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
		Persistencia persistencia = new Persistencia();
		
		
		Mensaje msg = (Mensaje) request.getAttribute("msg");
		try {
			persistencia.setDriver(getServletContext().getInitParameter("dbdriver"));
			persistencia.setConnection(getServletContext().getInitParameter("dbconnection"));
			persistencia.create(msg);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		response.sendRedirect("index.html");
	}
}
