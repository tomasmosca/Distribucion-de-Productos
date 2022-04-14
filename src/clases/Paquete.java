package clases;

public class Paquete {
	
	private double peso;
	private double volumen;
	private String destino;
	private boolean refrigeracion;
	
	public Paquete(String destino,double peso,double volumen,boolean refrigeracion) {
		this.destino = destino;
		this.peso = peso;
		this.volumen = volumen;
		this.refrigeracion = refrigeracion;
	}
	
	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getVolumen() {
		return volumen;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public boolean isRefrigeracion() {
		return refrigeracion;
	}

	public void setRefrigeracion(boolean refrigeracion) {
		this.refrigeracion = refrigeracion;
	}
}
