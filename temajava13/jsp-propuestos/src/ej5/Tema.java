package ej5;

public class Tema {
	int id;
	String tema;
	
	public Tema() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	
	public String toString () {
		return this.tema;
	}
}
