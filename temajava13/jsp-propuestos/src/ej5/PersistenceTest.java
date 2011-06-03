package ej5;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

public class PersistenceTest {
	@Test
	public void testAñadirLibro() throws SQLException, ClassNotFoundException {
		Persistence sut = new Persistence();
		Libro libro = new Libro();
		libro.setTitulo("Fundación");
		libro.setAutor("Isaac Asimov");
		libro.setPrecio(6.00);
		libro.setIdTema(1);
		
		sut.añadirLibro(libro);
	}

	@Test
	public void testGetLibros() throws SQLException, ClassNotFoundException {
		Persistence sut = new Persistence();
		assertTrue(sut.getLibros().size() > 0);
		
		for (Libro libro : sut.getLibros())
			System.out.println(libro);
	}

	@Test
	public void testGetTemas() throws SQLException, ClassNotFoundException {
		Persistence sut = new Persistence();
		assertTrue(sut.getTemas().size()>0);
	}
	
	@Test
	public void testAñadirTemas() throws SQLException, ClassNotFoundException {
		Persistence.añadirTema("esto es un tema");
	}

}
