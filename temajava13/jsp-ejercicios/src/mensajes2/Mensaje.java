package mensajes2;

public class Mensaje {
	private String remite;
	private String destino;
	private String texto;

	public Mensaje() {
	} // constructor sin parámetros

	public String getRemite() {
		return remite;
	}

	public void setRemite(String remite) {
		this.remite = remite;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
}
