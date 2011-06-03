package ej5;

import java.sql.SQLException;

public class Libro {
	private int id;
	private int idTema;
	private String titulo;
	private Double precio;
	private String autor;
	
	public Libro () {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdTema() {
		return idTema;
	}
	public void setIdTema(int idTema) {
		this.idTema = idTema;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Tema getTema () throws SQLException, ClassNotFoundException {
		Persistence pers = new Persistence();
		return pers.getTemaParaLibro(this);
	}
	
	@Override
	public String toString() {
		return "Libro [id=" + id + ", idTema=" + idTema + ", titulo=" + titulo
				+ ", precio=" + precio + ", autor=" + autor + "]";
	}
	
	
}
