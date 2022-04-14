package clases;

public class Viaje {
	
	private int Km;
	private String destino;
	
	public Viaje(String destino,int Km) {
		this.destino = destino;
		this.Km = Km;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public int getKm() {
		return Km;
	}

	public void setKm(int km) {
		Km = km;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append((destino));
		return builder.toString();
	}

}
