package clases;

public class Trailer extends Transportes {
	
	private double segCarga;

	public Trailer(String identificacion, double capacidad, double cargaMax, boolean refrigeracion, double costoKm, double segCarga) {
		super(identificacion, capacidad, cargaMax, refrigeracion, costoKm, segCarga);
		this.segCarga = segCarga;
	}

	@Override
	public double obtenerCostoDelViaje() {
		double costo = 0;
		costo+=getCostoKm()*getViajeActual().getKm()+getSegCarga()+getAdicionCostoTercerizado();  // se a�ade el costo tercerizado. si es un deposito tercerizado frio entonces sera mayor que cero y se a�ade, sino devuelve el costo comun
		return costo;
	}

	public double getSegCarga() {
		return segCarga;
	}

	public void setSegCarga(double segCarga) {
		this.segCarga = segCarga;
	}
}
