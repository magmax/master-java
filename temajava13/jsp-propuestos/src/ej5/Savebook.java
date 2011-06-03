package ej5;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Savebook
 */
@WebServlet("/ej5/Savebook")
public class Savebook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Savebook() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Libro libro = new Libro();
		libro.setTitulo(request.getParameter("titulo"));
		libro.setAutor(request.getParameter("autor"));
		libro.setPrecio(Double.valueOf(request.getParameter("precio")));
		libro.setIdTema(Integer.valueOf(request.getParameter("idtema")));
		
		System.out.println(libro);
		
		Persistence pers = new Persistence();
		try {
			pers.añadirLibro(libro);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ServletException();
		}
		response.sendRedirect("index.html");
	}
}
