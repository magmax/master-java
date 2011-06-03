package ej5;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;


public class ViewBooks extends BodyTagSupport {
	private static final long serialVersionUID = -6814682162435642600L;
	private List<Libro> libros;
	
	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out=pageContext.getOut();
		try {
			out.println("hola");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return super.doStartTag();
	}

	
}
