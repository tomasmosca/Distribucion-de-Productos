package clases;

public class MegaTrailer extends Transportes {
	
	private double costoFijo;
	private double comida;

	public MegaTrailer(String identificacion, double capacidad, double cargaMax, boolean refrigeracion, double costoKm, double segCarga, double costoFijo, double comida) {
		super(identificacion, capacidad, cargaMax, refrigeracion, costoKm, segCarga);
		this.costoFijo = costoFijo;
		this.comida = comida;
	}

	public double getCostoFijo() {
		return costoFijo;
	}

	public void setCostoFijo(double costoFijo) {
		this.costoFijo = costoFijo;
	}

	public double getComida() {
		return comida;
	}

	public void setComida(double comida) {
		this.comida = comida;
	}

	@Override
	public double obtenerCostoDelViaje() {
		double costo = 0;
		costo+=getCostoKm()*getViajeActual().getKm()+costoFijo+getSegCarga()+comida+getAdicionCostoTercerizado();
		return costo;
	}

}
